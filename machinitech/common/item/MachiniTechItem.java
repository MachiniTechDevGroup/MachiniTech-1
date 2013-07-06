package machinitech.common.item;

import static machinitech.common.core.MachiniTechCore.config;
import static machinitech.common.core.MachiniTechCore.ModID;
import machinitech.common.core.MachiniTechCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachiniTechItem extends Item {
	
	public static Item smeltercore;
	public static Item ash;

	public static void addMachiniTechItems() {
		smeltercore = new MachiniTechItem(config.get("Item ID", "Smelter Core", 1600).getInt(1600)).setUnlocalizedName(ModID + ".itemsmeltcore");
		LanguageRegistry.addName(smeltercore, "Smelter Core");
		GameRegistry.registerItem(smeltercore, smeltercore.getUnlocalizedName().substring(5));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(smeltercore), "iii", "ibi", "iii", Character.valueOf('i'), "ingotCopper", Character.valueOf('b'), MachiniTechCore.orestore[0]));
		
		ash = new MachiniTechItem(config.get("Item ID", "Ash", 1603).getInt(1603)).setUnlocalizedName(ModID + ".itemash");
		LanguageRegistry.addName(ash, "Ash");
		GameRegistry.registerItem(ash, ash.getUnlocalizedName().substring(5));
		OreDictionary.registerOre("itemAsh", ash); //Does anybody else register ash in the OreDict?
		
		config.save();
	}
	
	@SideOnly(Side.CLIENT)
	private Icon icon;
	public MachiniTechItem(int par1) {
		super(par1);
		this.setCreativeTab(MachiniTechCore.ctitem);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icon = reg.registerIcon(ModID + ":" + this.getUnlocalizedName().substring(5));
	}
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int i) {
		return this.icon;
	}
}
