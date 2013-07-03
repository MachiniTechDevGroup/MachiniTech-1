package machinitech.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class SlotSmeltInput extends Slot {

	public SlotSmeltInput(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}
	@Override
	public boolean isItemValid(ItemStack stack) {
		return FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
	}

}
