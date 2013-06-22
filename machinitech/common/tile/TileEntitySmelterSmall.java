package machinitech.common.tile;

import machinitech.common.core.MachiniTechCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class TileEntitySmelterSmall extends MachiniTechMachine implements ISidedInventory {

	//The different Item slots
	/*
	 * 0-7 Fuel
	 * 8 Coil
	 * 9-16 Main Input
	 * 17-22 I/O Slots
	 * 23 Box
	 * 24 CircleThingy
	 */
	private ItemStack[] inv = new ItemStack[25];
	public TileEntitySmelterSmall() {
		super(0);
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack stack = getStackInSlot(i);
        if (stack != null) {
                if (stack.stackSize <= j) {
                        setInventorySlotContents(i, null);
                } else {
                        stack = stack.splitStack(j);
                        if (stack.stackSize == 0) {
                                setInventorySlotContents(i, null);
                        }
                }
        }
        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack stack = getStackInSlot(i);
        if (stack != null) {
                setInventorySlotContents(i, null);
        }
        return stack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inv[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
                itemstack.stackSize = getInventoryStackLimit();
        }
		
	}

	@Override
	public String getInvName() {
		return MachiniTechCore.ModID + ".tileentitysmeltersmall";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false; //For now, automation should be kept at bay until we can actually get something done. :)
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

}
