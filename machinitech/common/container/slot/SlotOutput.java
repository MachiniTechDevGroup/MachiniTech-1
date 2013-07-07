package machinitech.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutput extends Slot {
	public SlotOutput(IInventory par1iInventory, int id, int x, int y) {
		super(par1iInventory, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack item) {
		return false;
	}
}
