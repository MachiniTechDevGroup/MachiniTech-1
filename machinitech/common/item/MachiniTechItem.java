package machinitech.common.item;

import machinitech.common.core.MachiniTechCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachiniTechItem extends Item {

	@SideOnly(Side.CLIENT)
	public Icon icon;
	public MachiniTechItem(int par1) {
		super(par1);
		this.setCreativeTab(MachiniTechCore.ctitem);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icon = reg.registerIcon(MachiniTechCore.ModID + ":" + this.getUnlocalizedName().substring(5));
	}
}
