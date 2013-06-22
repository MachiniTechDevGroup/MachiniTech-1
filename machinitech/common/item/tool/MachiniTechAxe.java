package machinitech.common.item.tool;

import machinitech.common.core.MachiniTechCore;
import machinitech.common.item.IngotMachiniTech;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachiniTechAxe extends ItemAxe {

	@SideOnly(Side.CLIENT)
	private Icon icon;
	public MachiniTechAxe(EnumToolMaterial par2EnumToolMaterial, IngotMachiniTech base) {
		super(ToolHandler.axeIDdef + ToolHandler.getNumTools(), par2EnumToolMaterial);
		this.setUnlocalizedName(MachiniTechCore.ModID + ".axe" + (ToolHandler.getNumTools() - 1));
		GameRegistry.registerItem(this, this.getUnlocalizedName().substring(5));
		GameRegistry.addRecipe(new ShapedOreRecipe(this, true, "ii ", "is ", " s ", Character.valueOf('i'), "ingot" + base.getName(), Character.valueOf('s'), Item.stick));
		GameRegistry.addRecipe(new ShapedOreRecipe(this, true, " ii", " si", " s ", Character.valueOf('i'), "ingot" + base.getName(), Character.valueOf('s'), Item.stick));
		LanguageRegistry.addName(this, base.getName() + " Axe");
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icon = reg.registerIcon(MachiniTechCore.ModID + ":" + this.getUnlocalizedName().substring(5));
	}
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int i) {
		return this.icon;
	}
}
