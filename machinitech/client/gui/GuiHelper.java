package machinitech.client.gui;

import machinitech.common.container.ContainerSmelter;
import machinitech.common.tile.TileEntitySmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHelper implements IGuiHandler {
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof TileEntitySmelter) {
			return new ContainerSmelter((TileEntitySmelter)tile, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof TileEntitySmelter) {
			return new GuiSmelter((TileEntitySmelter)tile, player.inventory);
		}
		return null;
	}

}
