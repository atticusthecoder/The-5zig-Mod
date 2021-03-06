package eu.the5zig.mod.server;

import eu.the5zig.mod.The5zigMod;

public class Server implements IServer {

	private String host;
	private int port;
	private long time;

	private transient boolean renderPotionEffects = true;
	private transient boolean renderArmor = true;
	private transient boolean renderPotionIndicator = true;
	private transient boolean renderSaturation = true;
	private transient boolean autoReconnect = true;

	// Default constructor for gson deserialization. Without this one, all server setting fields won't be initialized.
	public Server() {
	}

	public Server(String host, int port) {
		this.host = host;
		this.port = port;
		this.time = System.currentTimeMillis();
		The5zigMod.getLastServerConfig().getConfigInstance().setLastServer(this);
		save();
	}

	private void save() {
		The5zigMod.getLastServerConfig().saveConfig();
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public long getLastTimeJoined() {
		return time;
	}

	@Override
	public boolean isRenderPotionEffects() {
		return renderPotionEffects;
	}

	@Override
	public void setRenderPotionEffects(boolean renderPotionEffects) {
		this.renderPotionEffects = renderPotionEffects;
	}

	@Override
	public boolean isRenderArmor() {
		return renderArmor;
	}

	@Override
	public void setRenderArmor(boolean renderArmor) {
		this.renderArmor = renderArmor;
	}

	@Override
	public boolean isRenderPotionIndicator() {
		return renderPotionIndicator;
	}

	@Override
	public void setRenderPotionIndicator(boolean renderPotionIndicator) {
		this.renderPotionIndicator = renderPotionIndicator;
	}

	@Override
	public boolean isRenderSaturation() {
		return renderSaturation;
	}

	@Override
	public void setRenderSaturation(boolean renderSaturation) {
		this.renderSaturation = renderSaturation;
	}

	@Override
	public boolean isAutoReconnecting() {
		return autoReconnect;
	}

	@Override
	public void setAutoReconnecting(boolean autoReconnecting) {
		this.autoReconnect = autoReconnecting;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Server server = (Server) o;

		if (port != server.port)
			return false;
		return host.equals(server.host);

	}

	@Override
	public String toString() {
		return "Server{" +
				"host='" + host + '\'' +
				", port=" + port +
				'}';
	}
}