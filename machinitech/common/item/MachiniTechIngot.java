package machinitech.common.item;

import machinitech.common.block.OreMachiniTech;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MachiniTechIngot extends MachiniTechItem {
	public static MachiniTechIngot[] ingots = new MachiniTechIngot[OreMachiniTech.NUM_ORES];
	public MachiniTechIngot(int par1, IngotMachiniTech base) {
		super(par1);
		LanguageRegistry.addName(this, base.getName() + " Ingot");
		OreDictionary.registerOre("ingot" + base.getName(), new ItemStack(this));
		ingots[base.getID() - OreMachiniTech.Ore_ID - IngotMachiniTech.Ingot_Offset] = this;
	}
}