package machinitech.common.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotPhantom extends Slot {
	public SlotPhantom(IInventory par1iInventory, int id, int x, int y) {
		super(par1iInventory, id, x, y);
	}

	@Override
	public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
		return true;
	}
	@Override
	public int getSlotStackLimit() {
        return 1;
    }
}
