import eu.the5zig.mod.util.IKeybinding;

/**
 * Created by 5zig.
 * All rights reserved © 2015
 */
public class Keybinding extends ben implements IKeybinding {

	public Keybinding(String description, int keyCode, String category) {
		super(description, keyCode, category);
	}

	public boolean isPressed() {
		return g();
	}

	public int getKeyCode() {
		return j();
	}

	@Override
	public int compareTo(ben o) {
		return super.a(o);
	}
}
