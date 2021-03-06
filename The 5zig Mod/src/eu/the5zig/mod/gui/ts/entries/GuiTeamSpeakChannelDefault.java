package eu.the5zig.mod.gui.ts.entries;

import eu.the5zig.mod.The5zigMod;
import eu.the5zig.mod.gui.Gui;
import eu.the5zig.mod.render.Base64Renderer;
import eu.the5zig.teamspeak.TeamSpeak;
import eu.the5zig.teamspeak.api.Channel;
import eu.the5zig.teamspeak.api.ChannelCodec;
import eu.the5zig.teamspeak.api.OwnClient;
import eu.the5zig.teamspeak.api.ServerTab;

import java.awt.image.BufferedImage;

public class GuiTeamSpeakChannelDefault extends GuiTeamSpeakChannel {

	private final String serverUniqueId;

	public GuiTeamSpeakChannelDefault(Channel channel, String serverUniqueId) {
		super(channel);
		this.serverUniqueId = serverUniqueId;
	}

	@Override
	public void render(int x, int y, int width, int height) {
		final BufferedImage icon = channel.getIcon();
		if (icon != null) {
			Base64Renderer renderer = Base64Renderer.getRenderer(icon, "ts/" + serverUniqueId + "/icon_" + channel.getIconId());
			renderer.renderImage(x + width - 11, y + 1, 10, 10);
			width -= 10;
		}
		ServerTab selectedTab = TeamSpeak.getClient().getSelectedTab();
		if (selectedTab != null && selectedTab.getSelf() != null && selectedTab.getSelf().getTalkPower() < channel.getNeededTalkPower()) {
			width -= 10;
		}
		if (channel.getCodec() == ChannelCodec.OPUS_MUSIC) {
			width -= 10;
		}
		if (channel.isDefault()) {
			width -= 10;
		}

		The5zigMod.getVars().drawString(The5zigMod.getVars().shortenToWidth(channel.getName(), width - 25), x + 14, y + 2);
	}

	@Override
	public void renderIcons(int x, int y, int width, int height) {
		if (channel.getIcon() != null) {
			width -= 10;
		}
		OwnClient self = TeamSpeak.getClient().getSelectedTab().getSelf();
		if (self != null && self.getTalkPower() < channel.getNeededTalkPower()) {
			Gui.drawModalRectWithCustomSizedTexture(x + width - 11, y, 2 * 128 / 12, 6 * 128 / 12, 128 / 12, 128 / 12, 2048 / 12, 2048 / 12);
			width -= 10;
		}
		if (channel.getCodec() == ChannelCodec.OPUS_MUSIC) {
			Gui.drawModalRectWithCustomSizedTexture(x + width - 11, y, 4 * 128 / 12, 6 * 128 / 12, 128 / 12, 128 / 12, 2048 / 12, 2048 / 12);
			width -= 10;
		}
		if (channel.isDefault()) {
			Gui.drawModalRectWithCustomSizedTexture(x + width - 11, y, 3 * 128 / 12, 3 * 128 / 12, 128 / 12, 128 / 12, 2048 / 12, 2048 / 12);
		}
		drawChannelIcon(channel, x, y);
	}

	@Override
	public void renderDragging(int x, int y, int width, int height) {
		The5zigMod.getVars().drawString(The5zigMod.getVars().shortenToWidth(channel.getName(), width - 25), x + 14, y + 2);
	}

	@Override
	public void renderDraggingIcons(int x, int y, int width, int height) {
		drawChannelIcon(channel, x, y);
	}

	@Override
	public boolean canBeCollapsed() {
		return !channel.getClients().isEmpty() || !channel.getChildren().isEmpty();
	}

	public static void drawChannelIcon(Channel channel, int x, int y) {
		int u, v;
		if (channel.getClients().size() == channel.getMaxClients()) {
			if (channel.hasSubscribed()) {
				u = 4;
				v = 2;
			} else {
				u = 3;
				v = 2;
			}
		} else if (channel.requiresPassword()) {
			if (channel.hasSubscribed()) {
				u = 8;
				v = 2;
			} else {
				u = 7;
				v = 2;
			}
		} else if (channel.hasSubscribed()) {
			u = 0;
			v = 2;
		} else {
			u = 6;
			v = 2;
		}
		Gui.drawModalRectWithCustomSizedTexture(x, y, u * 128 / 12, v * 128 / 12, 128 / 12, 128 / 12, 2048 / 12, 2048 / 12);
	}
}
