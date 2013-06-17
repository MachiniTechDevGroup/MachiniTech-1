package machinitech.common.block;

import machinitech.common.core.MachiniTechCore;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MachiniTechOreStorageBlock extends MachiniTechBlock {
	public MachiniTechOreStorageBlock(OreMachiniTech base) {
		super(base.getID() + OreMachiniTech.NUM_ORES, Material.iron);
		GameRegistry.addRecipe(new ShapedOreRecipe(this, true, "iii", "i i", "iii", Character.valueOf('i'), "ingot" + base.getName()));
		this.setResistance(10f);
		this.setHardness(5f);
		this.setStepSound(soundMetalFootstep);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", base.getHarv());
		this.setUnlocalizedName(MachiniTechCore.ModID + ".orestore" + (base.getID() - OreMachiniTech.Ore_ID));
		LanguageRegistry.addName(this, base.getName() + " Block");
		GameRegistry.registerBlock(this, this.getUnlocalizedName().substring(5));
		GameRegistry.addShapelessRecipe(new ItemStack(MachiniTechCore.ingots[base.getID() - OreMachiniTech.Ore_ID], 8), this);
	}
}
