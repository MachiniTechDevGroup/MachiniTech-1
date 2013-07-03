package machinitech.client.gui;

import machinitech.common.container.ContainerSmelter;
import machinitech.common.tile.TileEntitySmelter;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiSmelter extends MachiniTechGuiContainer {

	private TileEntitySmelter entity;

	public GuiSmelter(TileEntitySmelter entity, InventoryPlayer inventory) {
		super(new ContainerSmelter(entity, inventory));
		this.ySize = 186;
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(TextureHelper.GUI_SMELTER);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

		int progress = this.entity.getCookProgressScaled(55);
		int heat = this.entity.getBurnTimeRemainingScaled(12);
		
		if (entity.isActive()) {
			this.drawTexturedModalRect(x + 116, y + 78 + 12 - heat, 176, 59 - heat, 14, heat + 2);
		}
		
		this.drawTexturedModalRect(x + 116, y + 19, 176, 62, 14, progress);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		fontRenderer.drawString(entity.getInvName(), 56, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 5, 4210752);
	}

}
