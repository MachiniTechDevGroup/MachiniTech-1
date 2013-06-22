package machinitech.common.block;

import machinitech.common.tile.TileEntitySmelterSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachineSmelterSmall extends MachiniTechBlockContainer {

	@SideOnly(Side.CLIENT)
	/*
	 * 0 Bottom
	 * 1 Top
	 * 2-5 Sides (Not sure which)
	 */
	private Icon[] icons = new Icon[6];
	@Override
	public TileEntity createNewTileEntity(World world) {
		try {
			return new TileEntitySmelterSmall();
		} catch (Exception e) {
			System.out.println("Oh my! Something's gone wrong!");
			throw new RuntimeException(e);
		}
	}
	@Override
	public boolean hasTileEntity (int metadata) {
		return true;
	}
	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			TileEntitySmelterSmall ts = (TileEntitySmelterSmall) world.getBlockTileEntity(par2, par3, par4);
		}
		return true;
	}
}
