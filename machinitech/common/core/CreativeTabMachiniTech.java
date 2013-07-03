package machinitech.common.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import machinitech.common.block.OreMachiniTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabMachiniTech extends CreativeTabs {
	private int DispID;
	private String tabLabel;
	public CreativeTabMachiniTech (String s) {
		super(s);
		this.tabLabel = s;
		if (s.equals("MachiniTech Blocks")) {
			this.DispID = 500;
		} else if (s.equals("MachiniTech Items")) {
			this.DispID = 4500;
		} else if (s.equals("MachiniTech Machines")) {
			this.DispID = 700;
		}
	}
	@SideOnly(Side.CLIENT)
	public void setID(int id) {
		this.DispID = id;
	}
	@SideOnly(Side.CLIENT)
    public int getTabIconItemIndex() {
        return this.DispID;
    }
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
        return new ItemStack(getTabIconItem());
    }
	@SideOnly(Side.CLIENT)
    public String getTabLabel() {
        return this.tabLabel;
    }
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        Item i = null;
        switch (this.DispID) {
        case 4500:
        	i = MachiniTechCore.ingots[0];
        	break;
        case 500:
        	i = new ItemStack(MachiniTechCore.ores[0]).getItem();
        	break;
        case 700:
        	i = new ItemStack(MachiniTechCore.smelter).getItem();
        	break;
        }
        return i;
    }
}
