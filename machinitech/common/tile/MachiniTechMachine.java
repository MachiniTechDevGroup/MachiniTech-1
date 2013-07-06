package machinitech.common.tile;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public abstract class MachiniTechMachine extends MachiniTechTileEntity {
	private int Tier;
	public short progress;
	public MachiniTechMachine (int t) {
		this.Tier = t;
	}
	public int getTier() {
		return this.Tier;
	}
}
