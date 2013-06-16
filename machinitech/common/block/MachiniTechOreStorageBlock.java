package machinitech.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class MachiniTechOreStorageBlock extends MachiniTechBlock {

	public MachiniTechOreStorageBlock(int par1, OreMachiniTech base) {
		super(par1, Material.iron);
		GameRegistry.addShapedRecipe(new ItemStack(this), "iii", "i i", "iii", "i", "ingot" + base.getName());
	}

}
