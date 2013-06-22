package machinitech.common.item;

import machinitech.common.block.OreMachiniTech;
import machinitech.common.core.MachiniTechCore;

public class IngotMachiniTech {
	public String Name;
	private int id;
	public static final int Ingot_ID = MachiniTechCore.config.get("Item ID", "FirstIngot", 4500).getInt();
	public static final IngotMachiniTech[] ingots = new IngotMachiniTech[OreMachiniTech.NUM_ORES];
	public IngotMachiniTech(OreMachiniTech ore) {
		this.id = ore.getID() - ore.Ore_ID + Ingot_ID;
		this.Name = ore.Name;
	}
	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.Name;
	}
	public static void createIngots(OreMachiniTech[] ores) {
		for (int i = 0; i < ores.length; i++) {
			ingots[i] = new IngotMachiniTech(ores[i]);
		}
	}
}
