package machinitech.common.core;

import machinitech.MachiniTech;
import machinitech.common.block.MachiniTechOre;
import machinitech.common.block.MachiniTechOreStorageBlock;
import machinitech.common.block.OreMachiniTech;
import machinitech.common.item.IngotMachiniTech;
import machinitech.common.item.MachiniTechIngot;
import machinitech.common.world.MachiniTechWorldGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class MachiniTechCore {
	
	public static final String ModID = "MachiniTech";
	public static CreativeTabs ctblock = new CreativeTabMachiniTech("MachiniTech Blocks");
	public static CreativeTabs ctitem = new CreativeTabMachiniTech("MachiniTech Items");
	public static MachiniTechOre[] ores = new MachiniTechOre[OreMachiniTech.NUM_ORES];
	public static MachiniTechIngot[] ingots = new MachiniTechIngot[OreMachiniTech.NUM_ORES];
	public static MachiniTechOreStorageBlock[] orestore = new MachiniTechOreStorageBlock[OreMachiniTech.NUM_ORES];
	
	public static Configuration config;
	
	public static void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		config.save();
	}
	public static void init(MachiniTech mod) {
		OreMachiniTech.createOres();
		for (int i = 0; i < OreMachiniTech.NUM_ORES; i++) {
			ores[i] = new MachiniTechOre(OreMachiniTech.ores[i]);
			ingots[i] = new MachiniTechIngot(IngotMachiniTech.ingots[i]);
			orestore[i] = new MachiniTechOreStorageBlock(OreMachiniTech.ores[i]);
		}
		GameRegistry.registerWorldGenerator(new MachiniTechWorldGenerator());
	}
	public static void postInit() {
		
	}
}
