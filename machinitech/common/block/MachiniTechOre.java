package machinitech.common.block;

import machinitech.MachiniTech;
import machinitech.common.core.MachiniTechCore;
import machinitech.common.item.IngotMachiniTech;
import machinitech.common.item.MachiniTechIngot;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MachiniTechOre extends MachiniTechBlock {
	public MachiniTechOre (OreMachiniTech base) {
		super (base.getID(), Material.rock);
		this.setHardness(3f);
		this.setResistance(10f);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName(MachiniTechCore.ModID + ".ore" + (base.getID() - OreMachiniTech.Ore_ID));
		LanguageRegistry.addName(this, base.getName() + " Ore");
		OreDictionary.registerOre("ore" + base.getName(), new ItemStack(this));
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", base.getHarv());
		GameRegistry.registerBlock(this, this.getUnlocalizedName().substring(5));
	}
}