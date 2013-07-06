package machinitech.common.core;

import machinitech.MachiniTech;
import machinitech.client.gui.GuiHelper;
import machinitech.common.addon.AddonManager;
import machinitech.common.addon.TileEntityHopperFast;
import machinitech.common.block.MachineSmelterSmall;
import machinitech.common.block.MachiniTechBlockContainer;
import machinitech.common.block.MachiniTechOre;
import machinitech.common.block.MachiniTechOreStorageBlock;
import machinitech.common.block.OreMachiniTech;
import machinitech.common.item.IngotMachiniTech;
import machinitech.common.item.MachiniTechCoil;
import machinitech.common.item.MachiniTechCoilComponent;
import machinitech.common.item.MachiniTechIngot;
import machinitech.common.item.MachiniTechItem;
import machinitech.common.tile.TileEntitySmelter;
import machinitech.common.world.MachiniTechWorldGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class MachiniTechCore {
	
	//All the IMPORTANT stuff goes here
	public static final String ModID = "MachiniTech";
	
	public static CreativeTabs ctblock = new CreativeTabMachiniTech("MachiniTech Blocks");
	public static CreativeTabs ctitem = new CreativeTabMachiniTech("MachiniTech Items");
	public static CreativeTabs ctmachine = new CreativeTabMachiniTech("MachiniTech Machines");
	
	public static MachiniTechOre[] ores = new MachiniTechOre[OreMachiniTech.NUM_ORES];
	public static MachiniTechIngot[] ingots = new MachiniTechIngot[OreMachiniTech.NUM_ORES];
	public static MachiniTechOreStorageBlock[] orestore = new MachiniTechOreStorageBlock[OreMachiniTech.NUM_ORES];
	
	public static MachineSmelterSmall smelter;
	public static MachineSmelterSmall smelteractive;
	
	public static MachiniTechCoilComponent coilcomp;
	public static MachiniTechCoil coil;
	
	public static TileEntityHopperFast fasthopper;
	
	public static Configuration config;
	
	public static AddonManager addon;
	
	public static void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		config.save();
	}
	public static void init(MachiniTech mod) {
		OreMachiniTech.createOres();
		MachiniTechBlockContainer.prepareBlocks();
		
		for (int i = 0; i < OreMachiniTech.NUM_ORES; i++) {
			ores[i] = new MachiniTechOre(OreMachiniTech.ores[i]);
			ingots[i] = new MachiniTechIngot(IngotMachiniTech.ingots[i]);
			orestore[i] = new MachiniTechOreStorageBlock(OreMachiniTech.ores[i]);
		}
		
		coilcomp = new MachiniTechCoilComponent();
		coil = new MachiniTechCoil();
		MachiniTechItem.addMachiniTechItems();
		
		GameRegistry.registerWorldGenerator(new MachiniTechWorldGenerator());
		GameRegistry.registerTileEntity(TileEntitySmelter.class, "SmelterSmall");
		NetworkRegistry.instance().registerGuiHandler(mod, new GuiHelper());
		
		smelter = new MachineSmelterSmall(false);
		smelteractive = new MachineSmelterSmall(true);
		
		addon = new AddonManager();
	}
	public static void postInit() {
		
	}
}
