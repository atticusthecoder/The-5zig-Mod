package eu.the5zig.mod.chat.network.filetransfer;

import eu.the5zig.mod.The5zigMod;
import eu.the5zig.mod.chat.entity.FileMessage;
import eu.the5zig.mod.chat.network.packets.PacketFileTransferChunk;
import eu.the5zig.mod.chat.network.packets.PacketFileTransferStart;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileUploadTask {

	public static final int MAX_LENGTH = 5000000;
	private final int CHUNK_SIZE = 32000;
	private final int fileId;
	private final FileMessage message;

	private File file;

	private boolean uploading = false;

	public FileUploadTask(int fileId, String fileName, FileMessage message) throws IOException {
		this.fileId = fileId;
		this.message = message;
		file = new File(The5zigMod.getModDirectory(),
				"media/" + The5zigMod.getDataManager().getUniqueId().toString() + "/" + The5zigMod.getDataManager().getFileTransferManager().getFileName(message) + "/" + fileName);
		if (!file.exists())
			throw new FileNotFoundException(file.getAbsolutePath());
		long fileLength = file.length();
		int parts = (int) Math.ceil((double) fileLength / (double) CHUNK_SIZE);
		if (fileLength > MAX_LENGTH)
			throw new IllegalArgumentException("Image too large!");

		The5zigMod.getNetworkManager().sendPacket(new PacketFileTransferStart(fileId, parts, CHUNK_SIZE));
	}

	public void initSend() throws IOException {
		uploading = true;
		new Thread("Upload Thread") {

			private final Thread instance = this;
			private int index;
			private int parts;

			@Override
			public void run() {
				long fileLength = file.length();
				parts = (int) Math.ceil((double) fileLength / (double) CHUNK_SIZE);
				if (fileLength > 5000000)
					throw new IllegalArgumentException("Image too large!");

				InputStream is = null;
				try {
					is = new FileInputStream(file);
					byte[] buffer = new byte[CHUNK_SIZE];
					int l;
					while ((l = is.read(buffer)) > 0) {
						if (!uploading)
							throw new IllegalStateException("Upload Aborted");

						// the clone in this line is very, very, VERY important! If you don't clone the
						// buffer, then the buffer gets corrupted!!!
						The5zigMod.getNetworkManager().sendPacket(new PacketFileTransferChunk(fileId, index, new Chunk(buffer.clone(), l)), new ChannelFutureListener() {
							@Override
							public void operationComplete(ChannelFuture channelFuture) throws Exception {
								message.setPercentage((float) (index - 1) / (float) parts);
								synchronized (instance) {
									instance.notifyAll();
								}
							}
						});
						index++;
						synchronized (instance) {
							wait();
						}
					}
					The5zigMod.getConversationManager().setImageUploaded(message);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					IOUtils.closeQuietly(is);
				}
			}
		}.start();
	}

	public void abortUpload() {
		uploading = false;
	}

	public class Chunk {

		private byte[] data;
		private int length;

		public Chunk(byte[] data, int length) {
			this.data = data;
			this.length = length;
		}

		public byte[] getData() {
			return data;
		}

		public int getLength() {
			return length;
		}
	}
}
