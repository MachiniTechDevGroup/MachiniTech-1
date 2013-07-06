package machinitech.common.addon;

import java.util.Random;

import machinitech.common.core.MachiniTechCore;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHopperFast extends BlockHopper {
    private final Random field_94457_a = new Random();
    @SideOnly(Side.CLIENT)
    private Icon hopperIcon;
    @SideOnly(Side.CLIENT)
    private Icon hopperTopIcon;
    @SideOnly(Side.CLIENT)
    private Icon hopperInsideIcon;

    public BlockHopperFast(int par1) {
        super(par1);
        this.setHardness(3.0F).setResistance(8.0F).setStepSound(soundWoodFootstep);
        this.setCreativeTab(MachiniTechCore.ctmachine);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityHopperFast();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
        super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving, par6ItemStack);
        if (par6ItemStack.hasDisplayName()) {
            TileEntityHopperFast TileEntityHopperFast = getHopperTile(par1World, par2, par3, par4);
            TileEntityHopperFast.setInventoryName(par6ItemStack.getDisplayName());
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.updateMetadata(par1World, par2, par3, par4);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par1World.isRemote) {
            return true;
        }
        else {
            TileEntityHopperFast fast = getHopperTile(par1World, par2, par3, par4);
            if (fast != null) {
                par5EntityPlayer.displayGUIHopper(fast);
            }
            return true;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        this.updateMetadata(par1World, par2, par3, par4);
    }

    /**
     * Updates the Metadata to include if the Hopper gets powered by Redstone or not
     */
    private void updateMetadata(World par1World, int par2, int par3, int par4) {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        int i1 = getDirectionFromMetadata(l);
        boolean flag = !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4);
        boolean flag1 = getIsBlockNotPoweredFromMetadata(l);
        if (flag != flag1) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 | (flag ? 0 : 8), 4);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        TileEntityHopperFast hopperfast = (TileEntityHopperFast)par1World.getBlockTileEntity(par2, par3, par4);
        if (hopperfast != null) {
            for (int j1 = 0; j1 < hopperfast.getSizeInventory(); ++j1) {
                ItemStack itemstack = hopperfast.getStackInSlot(j1);
                if (itemstack != null) {
                    float f = this.field_94457_a.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.field_94457_a.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.field_94457_a.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int k1 = this.field_94457_a.nextInt(21) + 10;
                        if (k1 > itemstack.stackSize) {
                            k1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= k1;
                        EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.field_94457_a.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.field_94457_a.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.field_94457_a.nextGaussian() * f3);
                        par1World.spawnEntityInWorld(entityitem);
                    }
                }
            }
            par1World.func_96440_m(par2, par3, par4, par5);
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    @SideOnly(Side.CLIENT)
    @Override
    /**
     * Gets the icon name of the ItemBlock corresponding to this block. Used by hoppers.
     */
    public String getItemIconName() {
        return "hopper";
    }
    public static TileEntityHopperFast getHopperTile(IBlockAccess par0IBlockAccess, int par1, int par2, int par3) {
        return (TileEntityHopperFast)par0IBlockAccess.getBlockTileEntity(par1, par2, par3);
    }
}
