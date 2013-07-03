package machinitech.common.tile;

import machinitech.common.block.MachineSmelterSmall;
import machinitech.common.item.MachiniTechCoil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntitySmelter extends MachiniTechMachine implements ISidedInventory, ITankContainer {

	//The different Item slots
	/*
	 * Old version
	 * 0-7 Fuel
	 * 8 Coil
	 * 9-16 Main Input
	 * 17-22 Liquid I/O Slots
	 * 23 Box
	 * 24 Main Output
	 */
	/*
	 * New version
	 * 0-7 Main Input
	 * 8 Box
	 * 9-16 Main Output
	 * 17 Fuel
	 * 18 Coil
	 * 19-24 Liquid I/O Slots
	 */
	private ItemStack[] inv = new ItemStack[25];
	public int furnaceBurnTime = 0;
	private short coilTier = 0;
	private LiquidTank tank;
	private LiquidTank another;
	public short furnaceCookTime = 0;
	private Container cont;
	/**
	 * The number of ticks that the furnace will keep burning
	 */
	public int currentItemBurnTime = 0;
	/**
	 * Name, used in renaming
	 */
	private String name;
	/**
	 * The amount of buckets that each liquid tank can hold
	 */
	private static final int TANK_CAP = 8;
	/**
	 * The amount of time an item cooks for
	 */
	private short itemCookTime = 200;
	public TileEntitySmelter() {
		super(0);
		this.itemCookTime = (short)(200 / (coilTier + 1));
		tank = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME * TANK_CAP);
		another = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME * TANK_CAP);
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.inv = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inv.length)
            {
                this.inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
        this.itemCookTime = par1NBTTagCompound.getShort("ItemCookTime");
        this.coilTier = par1NBTTagCompound.getShort("CoilTier");
        this.currentItemBurnTime = this.getItemBurnTime(this.inv[17], this.coilTier + 1);

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.name = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
        par1NBTTagCompound.setShort("ItemCookTime", this.itemCookTime);
        par1NBTTagCompound.setShort("CoilTier", this.coilTier);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inv.length; ++i) {
            if (this.inv[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inv[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized()) {
            par1NBTTagCompound.setString("CustomName", this.name);
        }
    }
    
    public int getCookProgressScaled(int par1) {
        return this.furnaceCookTime * par1 / this.itemCookTime;
    }

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack stack = getStackInSlot(i);
		if (i == 18) {
			this.coilTier = 0;
			this.itemCookTime = 200;
		}
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
		if (i == 18) {
			if (this.getStackInSlot(18) != null) {
				this.coilTier = (short)((MachiniTechCoil)itemstack.getItem()).getTier(itemstack.getItemDamage());
				this.itemCookTime = (short)(200 / (this.coilTier + 1));
			}
		}
	}

	@Override
	public String getInvName() {
		return "Smelter";
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
		
	}

	@Override
	public void closeChest() {
		
	}
	
	public static int getItemBurnTime(ItemStack par0ItemStack, int tier) {
		if (par0ItemStack == null) {
			return 0;
		} else {
			int var1 = par0ItemStack.getItem().itemID;
			Item var2 = par0ItemStack.getItem();
			if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null) {
				Block var3 = Block.blocksList[var1];
				if (var3 == Block.woodSingleSlab) {
					return tier * 150;
				}
				if (var3.blockMaterial == Material.wood) {
					return tier * 300;
				}
			}
			if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD"))
				return tier * 200;
			if (var1 == Item.stick.itemID)
				return tier * 100;
			if (var1 == Item.coal.itemID)
				return tier * 1600;
			if (var1 == Item.bucketLava.itemID)
				return tier * 20000;
			if (var1 == Block.sapling.blockID)
				return tier * 100;
			if (var1 == Item.blazeRod.itemID)
				return tier * 2400;
			return tier * GameRegistry.getFuelValue(par0ItemStack);
		}
    }
	
	@Override
	public void updateEntity() {
		boolean active = this.isActive();
        boolean update = false;
        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = getItemBurnTime(this.inv[17], this.coilTier + 1);
                this.furnaceBurnTime = getItemBurnTime(this.inv[17], this.coilTier + 1);
                if (this.furnaceBurnTime > 0) {
                    update = true;
                    //Reduce the fuel slot
                    if (this.inv[17] != null) {
                        --this.inv[17].stackSize;
                        if (this.inv[17].stackSize == 0) {
                            this.inv[17] = this.inv[17].getItem().getContainerItemStack(inv[17]);
                        }
                    }
                }
            }
            if (this.isActive() && this.canSmelt()) {
                ++this.furnaceCookTime;
                //Done smelting?
                if (this.furnaceCookTime == this.itemCookTime) {
                    this.furnaceCookTime = 0;
                    this.smeltItems();
                    update = true;
                }
            }
            else {
                this.furnaceCookTime = 0;
            }
            if (active != this.furnaceBurnTime > 0) {
                update = true;
                MachineSmelterSmall.update(!active, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (update) {
            this.onInventoryChanged();
        }
	}
	/**
	 * Returns true only if all eight slots of the smelter can smelt an item without interference
	 * Does not account for fuel
	 * @return A boolean determining whether the furnace can smelt
	 */
	private boolean canSmelt() {
		int goodSlots = 0;
		for (int s = 0; s < 8; s++) {
			ItemStack in = this.getStackInSlot(s);
			ItemStack out = this.getStackInSlot(s + 9);
			ItemStack res = FurnaceRecipes.smelting().getSmeltingResult(in);
			if (in == null) {//If one input is null, YOU SHALL NOT SMELT
				return false;
			}
			if (out == null) {
				goodSlots++;
				continue;
			} else if (res != null && res.stackSize + out.stackSize <= out.getMaxStackSize() && out.isItemEqual(res)) {
				goodSlots++;
			}
		}
		return goodSlots == 8;
	}
	
	private void smeltItems() {
		if (this.canSmelt()) {
			for (int s = 0; s < 8; s++) {
				ItemStack in = getStackInSlot(s);
				ItemStack out = getStackInSlot(s + 9);
				ItemStack res = FurnaceRecipes.smelting().getSmeltingResult(in);
				if (this.inv[s + 9] == null) {//If nothing, copy item and quantity of the result
		               this.inv[s + 9] = res.copy();
				} else if (this.inv[s + 9].isItemEqual(res)) {//If the ItemStacks are the same, add the quantity
		               inv[s + 9].stackSize += res.stackSize;
		        }
				//Reduce the input
				--this.inv[s].stackSize;
		        if (this.inv[s].stackSize <= 0) {
		            this.inv[s] = null;
		        }
			}
		}
	}
	public int getBurnTimeRemainingScaled(int par1) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = this.itemCookTime;
        }
        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }
	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		if (i >= 0 && i <= 7) {
			if (FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null) {
				return true;
			}
		}
		if (i == 17 && getItemBurnTime(itemstack, 1) != 0) {
			return true;
		}
		if (i == 18 && this.getStackInSlot(18) == null) {
			return true;
		}
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		int[] res = null;
		switch(var1) {//pl means placeholder. Because Java is kinda stupid and only allows {x, y, z} in array initialisers.
		case 0:
			int[] pl1 = {9, 10, 11, 12, 13, 14, 15, 16};
			res = pl1;
			break;
		case 1:
			int[] pl2 = {0, 1, 2, 3, 4, 5, 6, 7};
			res = pl2;
			break;
		default:
			res = new int[2];
			res[0] = 17;
			res[1] = 18;
			break;//Even though it's not really necessary
		}
		return res;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		if (side == 1) {
			if (slot >= 0 && slot <= 7) {
				return FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null;
			}
		} else if (side == 0) {
			return false;
		} else {
			if (slot == 17) {
				return getItemBurnTime(itemstack, 1) > 0;
			} else if (slot == 18) {
				return this.getStackInSlot(18) == null;
			}
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		if (side == 0) {
			return slot >= 9 && slot <= 16;
		} else {
			return false;
		}
	}

	public boolean isActive() {
		return this.furnaceBurnTime > 0;
	}
	
	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction)
	{
		return new ILiquidTank[] { tank, another };
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String displayName) {
		this.name = displayName;
	}

}
