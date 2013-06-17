package machinitech.common.item;

import machinitech.common.block.OreMachiniTech;

public class IngotMachiniTech {

	public String Name;
	private int id;
	public static final int Ingot_Offset = 4000;
	public static final IngotMachiniTech[] ingots = new IngotMachiniTech[OreMachiniTech.NUM_ORES];
	public IngotMachiniTech(OreMachiniTech ore) {
		this.id = ore.getID() + Ingot_Offset;
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
