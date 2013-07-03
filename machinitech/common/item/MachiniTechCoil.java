package machinitech.common.item;

import java.util.List;

import machinitech.common.block.OreMachiniTech;
import machinitech.common.core.MachiniTechCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachiniTechCoil extends MachiniTechItem {
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	private int[] tier = new int[OreMachiniTech.NUM_ORES];
	public MachiniTechCoil() {
		super(MachiniTechCore.config.get("Item ID", "Coil ID", 1500).getInt(1500));
		this.setHasSubtypes(true);
		this.setUnlocalizedName(MachiniTechCore.ModID + ".coil");
		this.setMaxStackSize(1);
		for (int i = 0; i < OreMachiniTech.NUM_ORES; i++) {
			if (OreMachiniTech.ores[i].getTool()) {
				GameRegistry.addShapedRecipe(new ItemStack(this, 1, i), "ccc", "c c", "ccc", Character.valueOf('c'), new ItemStack(MachiniTechCore.coilcomp, 1, i));
				LanguageRegistry.addName(new ItemStack(this, 1, i), OreMachiniTech.ores[i].getName() + " Coil");
				tier[i] = OreMachiniTech.ores[i].getHarv();
				GameRegistry.registerItem(this, this.getUnlocalizedName(new ItemStack(this, 1, i)).substring(5));
			}
		}
		MachiniTechCore.config.save();
	}
	/*@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		
	}*/
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister reg) {
		icons = new Icon[OreMachiniTech.NUM_ORES];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = reg.registerIcon(MachiniTechCore.ModID + ":" + this.getUnlocalizedName().substring(5) + i);
		}
	}
	public String getUnlocalizedName(ItemStack par1ItemStack) {
        return MachiniTechCore.ModID + ".coil" + par1ItemStack.getItemDamage();
    }
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int i) {
		return this.icons[i];
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < OreMachiniTech.NUM_ORES; i++) {
			if (OreMachiniTech.ores[i].getTool()) {
				par3List.add(new ItemStack(par1, 1, i));
			}
		}
    }
	public int getTier(int d) {
		return tier[d];
	}

}
