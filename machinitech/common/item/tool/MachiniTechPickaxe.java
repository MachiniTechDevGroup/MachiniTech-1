package machinitech.common.item.tool;

import machinitech.common.block.OreMachiniTech;
import machinitech.common.core.MachiniTechCore;
import machinitech.common.item.IngotMachiniTech;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.Icon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachiniTechPickaxe extends ItemPickaxe {

	@SideOnly(Side.CLIENT)
	private Icon icon;
	public MachiniTechPickaxe(EnumToolMaterial par2EnumToolMaterial, OreMachiniTech base) {
		super(ToolHandler.pickIDdef + (base.getID() - OreMachiniTech.Ore_ID), par2EnumToolMaterial);
		this.setUnlocalizedName(MachiniTechCore.ModID + ".pick" + (base.getID() - OreMachiniTech.Ore_ID));
		GameRegistry.registerItem(this, this.getUnlocalizedName().substring(5));
		GameRegistry.addRecipe(new ShapedOreRecipe(this, true, "iii", " s ", " s ", Character.valueOf('i'), "ingot" + base.getName(), Character.valueOf('s'), Item.stick));
		LanguageRegistry.addName(this, base.getName() + " Pickaxe");
		MinecraftForge.setToolClass(this, "pickaxe", base.getHarv());
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
