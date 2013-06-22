package machinitech.common.item;

import machinitech.common.block.OreMachiniTech;
import machinitech.common.core.MachiniTechCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachiniTechIngot extends MachiniTechItem {
	public MachiniTechIngot(IngotMachiniTech base) {
		super(base.getID());
		System.out.println(base.getName());
		this.setUnlocalizedName(MachiniTechCore.ModID + ".ingot" + (base.getID() - IngotMachiniTech.Ingot_ID));
		GameRegistry.addSmelting(OreMachiniTech.Ore_ID + base.getID() - IngotMachiniTech.Ingot_ID, new ItemStack(this), 1f);
		LanguageRegistry.addName(this, base.getName() + " Ingot");
		OreDictionary.registerOre("ingot" + base.getName(), new ItemStack(this));
	}
}