package machinitech.common.container;

import machinitech.common.item.MachiniTechCoil;
import machinitech.common.tile.TileEntitySmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerSmelter extends MachiniTechContainer {
	
	private TileEntitySmelter te;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
	
	public ContainerSmelter(TileEntitySmelter te, InventoryPlayer inventory) {
		this.te = te;
		//Smelting input slots
		for (int h = 0; h < 3; h++) {
			this.addSlotToContainer(new SlotSmeltInput(te, h, 18 * h + 8, 20));
		}
		this.addSlotToContainer(new SlotSmeltInput(te, 3, 8, 38));
		this.addSlotToContainer(new SlotSmeltInput(te, 4, 44, 38));
		for (int h = 0; h < 3; h++) {
			this.addSlotToContainer(new SlotSmeltInput(te, h + 5, 18 * h + 8, 56));
		}
		//Box slot
		this.addSlotToContainer(new Slot(te, 8, 80, 38));
		//Smelting output slots
		for (int h = 0; h < 3; h++) {
			this.addSlotToContainer(new SlotOutput(te, h + 9, 18 * h + 62, 20));
		}
		this.addSlotToContainer(new SlotOutput(te, 12, 62, 38));
		this.addSlotToContainer(new SlotOutput(te, 13, 98, 38));
		for (int h = 0; h < 3; h++) {
			this.addSlotToContainer(new SlotOutput(te, h + 14, 18 * h + 62, 56));
		}
		//Fuel slot
		this.addSlotToContainer(new SlotFuel(te, 17, 134, 77));
		//Coil slot
		this.addSlotToContainer(new SlotHeatCoil(te, 18, 152, 77));
		//Dummy slots, will become slots for liquid input later
		for (int x = 0; x < 6; x++) {
			this.addSlotToContainer(new SlotDummy(te, x + 19, 18 * x + 8, 77));
		}
		
		//Add the player's inventory
		int var3;

		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 105 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(inventory, var3, 8 + var3 * 18, 163));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return te.isUseableByPlayer(entityplayer);
	}
	
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.te.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.te.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.te.currentItemBurnTime);
    }
	
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if (this.lastCookTime != this.te.furnaceCookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.te.furnaceCookTime);
            }
            if (this.lastBurnTime != this.te.furnaceBurnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.te.furnaceBurnTime);
            }
            if (this.lastItemBurnTime != this.te.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.te.currentItemBurnTime);
            }
        }
        this.lastCookTime = this.te.furnaceCookTime;
        this.lastBurnTime = this.te.furnaceBurnTime;
        this.lastItemBurnTime = this.te.currentItemBurnTime;
    }
	
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.te.furnaceCookTime = (short) par2;
        }
        if (par1 == 1) {
            this.te.furnaceBurnTime = par2;
        }
        if (par1 == 2){
            this.te.currentItemBurnTime = par2;
        }
    }
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotnum) {
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotnum);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (slotnum >= 9 && slotnum <= 16) {//Output slots
                if (!this.mergeItemStack(itemstack1, 25, 61, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotnum > 24) {//Player Inventory
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {//Input
                    if (!this.mergeItemStack(itemstack1, 0, 8, false)) {
                        return null;
                    }
                } else if (TileEntityFurnace.isItemFuel(itemstack1)) {//Fuel
                    if (!this.mergeItemStack(itemstack1, 17, 18, false)) {
                        return null;
                    }
                } else if (itemstack1.getItem() instanceof MachiniTechCoil) {//Coil
                	if (!this.mergeItemStack(itemstack1, 18, 19, false)) {
                		return null;
                	}
                } else if (slotnum >= 25 && slotnum < 52) {//Place stuff in hotbar
                    if (!this.mergeItemStack(itemstack1, 52, 61, false)) {
                        return null;
                    }
                } else if (slotnum >= 52 && slotnum < 61) {//Place stuff in inventory
                	if (!this.mergeItemStack(itemstack1, 25, 52, false)) {
                		return null;
                	}
                }	
            } else if (!this.mergeItemStack(itemstack1, 25, 61, false)) {//All others
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
	}
	
}
