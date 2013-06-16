package machinitech.common.block;

import machinitech.common.core.MachiniTechCore;
import machinitech.common.item.IngotMachiniTech;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MachiniTechOre extends MachiniTechBlock {
	public static MachiniTechOre[] ores = new MachiniTechOre[OreMachiniTech.NUM_ORES];
	public MachiniTechOre (OreMachiniTech base) {
		super (base.getID(), Material.rock);
		this.setHardness(3f);
		this.setResistance(10f);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName(MachiniTechCore.ModID + ".ore" + (base.getID() - OreMachiniTech.Ore_ID));
		LanguageRegistry.addName(this, base.getName() + " Ore");
		OreDictionary.registerOre("ore" + base.getName(), new ItemStack(this));
		this.setCreativeTab(MachiniTechCore.ct);
		ores[base.getID() - OreMachiniTech.Ore_ID] = this;
		GameRegistry.addSmelting(base.getID(), new ItemStack(this), 1f);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", base.getHarv());
	}
}