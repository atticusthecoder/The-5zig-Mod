package eu.the5zig.mod.chat.network.packets;

import eu.the5zig.mod.I18n;
import eu.the5zig.mod.The5zigMod;
import eu.the5zig.mod.chat.entity.User;
import eu.the5zig.mod.chat.network.util.PacketUtil;
import eu.the5zig.util.minecraft.ChatColor;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 5zig.
 * All rights reserved © 2015
 */
public class PacketFriendRequestList implements Packet {

	private List<User> friendRequests;

	@Override
	public void read(ByteBuf buffer) throws IOException {
		int size = buffer.readInt();
		friendRequests = new ArrayList<User>(size);
		for (int i = 0; i < size; i++) {
			User friendRequest = PacketBuffer.readUser(buffer);
			friendRequests.add(friendRequest);
		}
	}

	@Override
	public void write(ByteBuf buffer) throws IOException {
	}

	@Override
	public void handle() {
		PacketUtil.ensureMainThread(this);

		The5zigMod.getFriendManager().setFriendRequests(friendRequests);
		if (friendRequests.size() > 0)
			The5zigMod.getOverlayMessage().displayMessageAndSplit(ChatColor.GREEN + I18n.translate("friend.new_requests", friendRequests.size()));
	}
}
