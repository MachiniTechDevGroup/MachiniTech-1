package machinitech.common.item;

import java.util.List;

import machinitech.common.block.OreMachiniTech;
import machinitech.common.core.MachiniTechCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachiniTechCoilComponent extends MachiniTechItem {
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	public MachiniTechCoilComponent() {
		super(MachiniTechCore.config.get("Item ID", "Coil Component", 1601).getInt(1601));
		MachiniTechCore.config.save();
		this.setHasSubtypes(true);
		this.setUnlocalizedName(MachiniTechCore.ModID + ".coilcomp");
		for (int i = 0; i < OreMachiniTech.NUM_ORES; i++) {
			if (OreMachiniTech.ores[i].getTool()) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this, 1, i), "i i", " i ", "i i", Character.valueOf('i'), "ingot" + OreMachiniTech.ores[i].getName()));
				LanguageRegistry.addName(new ItemStack(this, 1, i), OreMachiniTech.ores[i].getName() + " Coil Component");
				GameRegistry.registerItem(this, this.getUnlocalizedName(new ItemStack(this, 1, i)).substring(5));
			}
		}
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister reg) {
		icons = new Icon[OreMachiniTech.NUM_ORES];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = reg.registerIcon(MachiniTechCore.ModID + ":" + this.getUnlocalizedName().substring(5) + i);
		}
	}
	public String getUnlocalizedName(ItemStack par1ItemStack) {
        return MachiniTechCore.ModID + ".coilcomp" + par1ItemStack.getItemDamage();
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
}
