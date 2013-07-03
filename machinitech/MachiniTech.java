package machinitech;

import machinitech.common.core.MachiniTechCore;
import machinitech.common.core.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "modMachiniTech", name = "MachiniTech", version = "1.0.2.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class MachiniTech {
	@Instance("modMachiniTech")
	public static MachiniTech instance;
	
	@SidedProxy(clientSide="machinitech.common.core.proxy.ClientProxy", serverSide="machinitech.common.core.proxy.CommonProxy")
	public static CommonProxy proxy;
	  public static MachiniTechCore core = new MachiniTechCore();

	  @Mod.PreInit
	  public void preInit(FMLPreInitializationEvent event) {
		  core.preInit(event);
	  }

	  @Mod.Init
	  public void init(FMLInitializationEvent event) {
		  
	    core.init(this);
	  }
	  @Mod.PostInit
	  public void postInit(FMLPostInitializationEvent event) {
	    //core.postInit();
	  }
	  @Mod.ServerStarting
	  public void serverStarting(FMLServerStartingEvent event) {
	    //core.serverStarting(event.getServer());
	  }
}
