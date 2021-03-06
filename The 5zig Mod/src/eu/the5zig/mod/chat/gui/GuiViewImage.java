package eu.the5zig.mod.chat.gui;

import eu.the5zig.mod.I18n;
import eu.the5zig.mod.MinecraftFactory;
import eu.the5zig.mod.The5zigMod;
import eu.the5zig.mod.chat.entity.ImageMessage;
import eu.the5zig.mod.gui.Gui;
import eu.the5zig.mod.gui.elements.IButton;
import eu.the5zig.mod.util.GLUtil;

import java.awt.*;
import java.io.File;

public class GuiViewImage extends Gui {

	private final Object resourceLocation;
	private final ImageMessage.ImageData imageData;
	private final String path;
	private int x, width, height;

	public GuiViewImage(Gui lastScreen, Object resourceLocation, ImageMessage.ImageData imageData, String path) {
		super(lastScreen);
		this.resourceLocation = resourceLocation;
		this.imageData = imageData;
		this.path = path;
	}

	@Override
	public void initGui() {
		addButton(MinecraftFactory.getVars().createButton(200, getWidth() / 2 + 5, getHeight() - 32, 150, 20, MinecraftFactory.getVars().translate("gui.done")));
		addButton(MinecraftFactory.getVars().createButton(100, getWidth() / 2 - 155, getHeight() - 32, 150, 20, I18n.translate("file_selector.open")));

		int realWidth = imageData.getRealWidth();
		int realHeight = imageData.getRealHeight();
		int maxWidth = getWidth() - 10;
		int maxHeight = getHeight() - 36 - 30;

		while (realWidth > maxWidth || realHeight > maxHeight) {
			realWidth /= 1.1;
			realHeight /= 1.1;
		}
		width = realWidth;
		height = realHeight;
		x = (getWidth() - width) / 2;
	}

	@Override
	protected void actionPerformed(IButton button) {
		if (button.getId() == 100) {
			Desktop desktop = Desktop.getDesktop();
			File dirToOpen;
			try {
				dirToOpen = new File(path);
				desktop.open(dirToOpen);
			} catch (Exception e) {
				The5zigMod.logger.error("Could not open Image", e);
			}
		}
	}

	@Override
	protected void drawScreen(int mouseX, int mouseY, float partialTicks) {
		The5zigMod.getVars().bindTexture(resourceLocation);
		GLUtil.color(1, 1, 1, 1);
		drawModalRectWithCustomSizedTexture(x, 30, 0, 0, width, height, width, height);
	}

	@Override
	public String getTitleName() {
		return "The 5zig Mod - " + imageData.getHash();
	}
}
