package eu.the5zig.mod.gui.ts;

import eu.the5zig.teamspeak.api.Channel;
import eu.the5zig.teamspeak.api.Client;
import eu.the5zig.teamspeak.api.ClientType;
import eu.the5zig.teamspeak.api.Group;
import eu.the5zig.teamspeak.util.Callback;

import java.awt.image.BufferedImage;
import java.util.List;

public class DummyClient implements Client {

	private String name;

	public DummyClient(String name) {
		this.name = name;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public int getDatabaseId() {
		return 0;
	}

	@Override
	public String getUniqueId() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDisplayName() {
		return name;
	}

	@Override
	public ClientType getType() {
		return ClientType.NORMAL;
	}

	@Override
	public Channel getChannel() {
		return null;
	}

	@Override
	public BufferedImage getIcon() {
		return null;
	}

	@Override
	public int getIconId() {
		return 0;
	}

	@Override
	public BufferedImage getAvatar() {
		return null;
	}

	@Override
	public boolean isTalking() {
		return false;
	}

	@Override
	public boolean isWhispering() {
		return false;
	}

	@Override
	public boolean isInputMuted() {
		return false;
	}

	@Override
	public boolean isOutputMuted() {
		return false;
	}

	@Override
	public boolean hasInputHardware() {
		return true;
	}

	@Override
	public boolean hasOutputHardware() {
		return true;
	}

	@Override
	public int getTalkPower() {
		return 0;
	}

	@Override
	public boolean isTalker() {
		return false;
	}

	@Override
	public boolean isPrioritySpeaker() {
		return false;
	}

	@Override
	public boolean isRecording() {
		return false;
	}

	@Override
	public boolean isChannelCommander() {
		return false;
	}

	@Override
	public boolean isMuted() {
		return false;
	}

	@Override
	public boolean isAway() {
		return false;
	}

	@Override
	public String getAwayMessage() {
		return null;
	}

	@Override
	public List<? extends Group> getServerGroups() {
		return null;
	}

	@Override
	public Group getChannelGroup() {
		return null;
	}

	@Override
	public void joinChannel(Channel channel) {

	}

	@Override
	public void joinChannel(Channel channel, String password) {

	}

	@Override
	public void joinChannel(Channel channel, Callback<Integer> errorCallback) {

	}

	@Override
	public void joinChannel(Channel channel, String password, Callback<Integer> errorCallback) {

	}

	@Override
	public void addToServerGroup(Group group) {

	}

	@Override
	public void removeFromServerGroup(Group group) {

	}

	@Override
	public void setChannelGroup(Channel channel, Group group) {

	}

	@Override
	public void poke(String message) {

	}

	@Override
	public void kickFromChannel(String reason) {

	}

	@Override
	public void kickFromServer(String reason) {

	}

	@Override
	public void banFromServer(String reason, int time) {

	}

	@Override
	public void mute() {

	}

	@Override
	public void unMute() {

	}

	@Override
	public int compareTo(Client o) {
		return 0;
	}
}
