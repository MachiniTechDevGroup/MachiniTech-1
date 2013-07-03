package machinitech.common.block;

import machinitech.common.core.MachiniTechCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class MachiniTechBlockContainer extends BlockContainer {
	private static int BlockContainer_ID_Def;
	private static int Num_Machines = 0;
	public static void prepareBlocks() {
		BlockContainer_ID_Def = MachiniTechCore.config.get("Block ID", "FirstBlockContainer", 600).getInt();
		MachiniTechCore.config.save();
	}
	protected MachiniTechBlockContainer() {
		super(Num_Machines++ + BlockContainer_ID_Def, Material.circuits);
		this.setCreativeTab(MachiniTechCore.ctmachine);
	}
	public static int getNumMachines () {
		return Num_Machines;
	}
}
