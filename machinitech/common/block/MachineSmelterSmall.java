package machinitech.common.block;

import java.util.Random;

import machinitech.MachiniTech;
import machinitech.common.core.MachiniTechCore;
import machinitech.common.item.MachiniTechCoil;
import machinitech.common.item.MachiniTechItem;
import machinitech.common.tile.TileEntitySmelter;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachineSmelterSmall extends MachiniTechBlockContainer {

	private static boolean keepFurnaceInventory;
	private boolean isActive;
	@SideOnly(Side.CLIENT)
	/*
	 * 0 Bottom
	 * 1 Top
	 * 2 Front (I think)
	 * 3 Sides
	 */
	private Icon front;
	private Icon top;
	private Icon bottom;
	private Icon side;
	private Icon sideact;
	private Icon frontact;
	private static boolean recipeAdded = false;
	private final Random furnaceRand = new Random();
	public MachineSmelterSmall(boolean active) {
		super();
		this.setTickRandomly(true);
		this.isActive = active;
		this.setUnlocalizedName(MachiniTechCore.ModID + ".blocktilesmeltersmall" + (active ? "" : "active"));
		GameRegistry.registerBlock(this, this.getUnlocalizedName().substring(5));
		this.setResistance(10f);
		this.setLightValue(active ? 0f: .875f);
		this.setHardness(2f);
		this.setTickRandomly(true);
		this.setStepSound(soundStoneFootstep);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);
		LanguageRegistry.addName(this, "Small Smelter");
		if (!recipeAdded) {
			GameRegistry.addShapedRecipe(new ItemStack(this), "fff", "fcf", "fff", Character.valueOf('f'), Block.furnaceIdle, Character.valueOf('c'), MachiniTechItem.smeltercore);
			recipeAdded = true;
		}
	}
	public MachineSmelterSmall() {
		super();
		this.setTickRandomly(true);
		this.setUnlocalizedName(MachiniTechCore.ModID + ".blocktilesmeltersmall");
		GameRegistry.registerBlock(this, this.getUnlocalizedName().substring(5));
		this.setResistance(10f);
		this.setHardness(2f);
		this.setTickRandomly(true);
		this.setStepSound(soundStoneFootstep);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);
		LanguageRegistry.addName(this, "Small Smelter");
		if (!recipeAdded) {
			GameRegistry.addShapedRecipe(new ItemStack(this), "fff", "f f", "fff", Character.valueOf('f'), Block.furnaceIdle);
			recipeAdded = true;
		}
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySmelter();
	}
	@Override
	public boolean hasTileEntity (int metadata) {
		return true;
	}
	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par5EntityPlayer.isSneaking()) {
			return false;
		}
		if (par5EntityPlayer.getCurrentEquippedItem() != null) {
			if (par5EntityPlayer.getCurrentEquippedItem().getItem() instanceof MachiniTechCoil){
				return false;
			}
		}
		if (!world.isRemote) {
			TileEntitySmelter te = (TileEntitySmelter) world.getBlockTileEntity(par2, par3, par4);
			if (te != null) {
                par5EntityPlayer.openGui(MachiniTech.instance, MachiniTechCore.config.get("GUI ID", "Smelter", 0).getInt(0), world, par2, par3, par4);
                MachiniTechCore.config.save();
            }
		}
		return true;
	}
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }
	private void setDefaultDirection(World par1World, int par2, int par3, int par4) {
        if (!par1World.isRemote) {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;
            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1]) {
                b0 = 3;
            }
            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l]) {
                b0 = 2;
            }
            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1]) {
                b0 = 5;
            }
            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1]) {
                b0 = 4;
            }
            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }
	public static void update(boolean active, World par1World, int xCoord, int yCoord, int zCoord) {
		int l = par1World.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = par1World.getBlockTileEntity(xCoord, yCoord, zCoord);
        keepFurnaceInventory = true;
        if (active) {
            par1World.setBlock(xCoord, yCoord, zCoord, MachiniTechCore.smelteractive.blockID);
        }
        else {
            par1World.setBlock(xCoord, yCoord, zCoord, MachiniTechCore.smelter.blockID);
        }
        keepFurnaceInventory = false;
        par1World.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, l, 2);
        if (tileentity != null) {
            tileentity.validate();
            par1World.setBlockTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
	}
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (this.isActive) {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            float f = (float)par2 + 0.5F;
            float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)par4 + 0.5F;
            float f3 = 0.52F;
            float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
            if (l == 4) {
                par1World.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5) {
                par1World.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2) {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3) {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        if (!keepFurnaceInventory) {
            TileEntitySmelter tileentity = (TileEntitySmelter)par1World.getBlockTileEntity(par2, par3, par4);
            if (tileentity != null) {
                for (int j1 = 0; j1 < tileentity.getSizeInventory(); ++j1) {
                    ItemStack itemstack = tileentity.getStackInSlot(j1);
                    if (itemstack != null) {
                        float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        while (itemstack.stackSize > 0) {
                            int k1 = this.furnaceRand.nextInt(21) + 10;
                            if (k1 > itemstack.stackSize) {
                                k1 = itemstack.stackSize;
                            }
                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }
                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.furnaceRand.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.furnaceRand.nextGaussian() * f3);
                            par1World.spawnEntityInWorld(entityitem);
                        }
                    }
                }
                par1World.func_96440_m(par2, par3, par4, par5);
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (l == 0) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }
        if (l == 1) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }
        if (l == 2) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }
        if (l == 3) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }
        if (par6ItemStack.hasDisplayName()) {
            ((TileEntitySmelter)par1World.getBlockTileEntity(par2, par3, par4)).setName(par6ItemStack.getDisplayName());
        }
    }
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		front = reg.registerIcon(MachiniTechCore.ModID + ":" + MachiniTechCore.ModID + ".tilesmelterfront");
		top = reg.registerIcon(MachiniTechCore.ModID + ":" + MachiniTechCore.ModID + ".tilesmeltertop");
		bottom = reg.registerIcon(MachiniTechCore.ModID + ":" + MachiniTechCore.ModID + ".tilesmelterbottom");
		side = reg.registerIcon(MachiniTechCore.ModID + ":" + MachiniTechCore.ModID + ".tilesmelterside");
		frontact = reg.registerIcon(MachiniTechCore.ModID + ":" + MachiniTechCore.ModID + ".tilesmelterfrontact");
		sideact = reg.registerIcon(MachiniTechCore.ModID + ":" + MachiniTechCore.ModID + ".tilesmeltersideact");
	}
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		if (this.isActive) {
			if (par1 == 3 && par2 == 0) {
				return frontact;
			}
			return par1 == 0 ? bottom : (par1 == 1 ? top : (par1 != par2 ? sideact : frontact));
		} else {
			if (par1 == 3 && par2 == 0) {
				return front;
			}
			return par1 == 0 ? bottom : (par1 == 1 ? top : (par1 != par2 ? side : front));
		}
	}
	public int idDropped(int par1, Random par2Random, int par3) {
        return MachiniTechCore.smelter.blockID;
    }
}
