package machinitech.common.block;

import machinitech.common.core.MachiniTechCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachiniTechBlock extends Block {
	@SideOnly(Side.CLIENT)
	public Icon icon;
	public MachiniTechBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(MachiniTechCore.ctblock);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icon = reg.registerIcon(MachiniTechCore.ModID + ":" + this.getUnlocalizedName().substring(5));
	}
}
