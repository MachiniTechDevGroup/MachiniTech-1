package machinitech.common.addon;

import machinitech.common.core.MachiniTechCore;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class AddonManager {
	public static Block fasthopper = new BlockHopperFast(MachiniTechCore.config.get("Addons", "Fast Hopper ID", 700).getInt(700));
	public AddonManager() {
		if (MachiniTechCore.config.get("Addons", "Enable Fast Hopper", true).getBoolean(true)) {
			GameRegistry.registerTileEntity(TileEntityHopperFast.class, "hopperFast");
			fasthopper.setUnlocalizedName(MachiniTechCore.ModID + ".hopperfast");
			LanguageRegistry.addName(fasthopper, "Fast Hopper");
			GameRegistry.registerBlock(fasthopper, fasthopper.getUnlocalizedName().substring(5));
			MachiniTechCore.config.save();
		}
	}

}
