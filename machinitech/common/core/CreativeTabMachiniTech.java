package machinitech.common.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import machinitech.common.block.OreMachiniTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMachiniTech extends CreativeTabs {
	//@SideOnly(Side.CLIENT)
	//private int DispID;
	public CreativeTabMachiniTech (String s) {
		super(s);
		/*if (s.equals("MachiniTech Blocks")) {
			this.DispID = 500;
		} else if (s.equals("MachiniTech Items")) {
			this.DispID = 4500;
		}*/
	}
/*	@SideOnly(Side.CLIENT)
	public void setID(int id) {
		this.DispID = id;
	}
	@SideOnly(Side.CLIENT)*/

    /**
     * the itemID for the item to be displayed on the tab
     */
    /*public int getTabIconItemIndex()
    {
        return this.DispID;
    }*/
}
