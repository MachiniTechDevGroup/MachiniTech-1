package machinitech.common.container.slot;

import machinitech.common.item.MachiniTechCoil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotHeatCoil extends Slot {

	public SlotHeatCoil(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof MachiniTechCoil;
	}
	@Override
	public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
		return false;
	}
}
