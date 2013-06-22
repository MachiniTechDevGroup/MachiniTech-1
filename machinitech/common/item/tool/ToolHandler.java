package machinitech.common.item.tool;

import machinitech.common.block.OreMachiniTech;
import machinitech.common.core.MachiniTechCore;
import machinitech.common.item.IngotMachiniTech;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

public class ToolHandler {
	public static final int pickIDdef = 1000;
	public static final int axeIDdef = 1100;
	public static final int swordIDdef = 1200;
	public static final int shovIDdef = 1300;
	public static final int hoeIDdef = 1400;
	private static int numTools = 0;
	public static EnumToolMaterial[] materials;
	public static MachiniTechPickaxe[] picks;
	public static MachiniTechShovel[] spades;
	public static MachiniTechAxe[] axes;
	public static MachiniTechHoe[] hoes;
	public static MachiniTechSword[] swords;
	private static ToolHandler instance = new ToolHandler();
	public static void makeTools(OreMachiniTech[] ores) {
		for (OreMachiniTech o : ores) {
			if (o.getTool()) {
				numTools++;
			}
		}
		materials = new EnumToolMaterial[numTools];
		picks = new MachiniTechPickaxe[numTools];
		spades = new MachiniTechShovel[numTools];
		axes = new MachiniTechAxe[numTools];
		hoes = new MachiniTechHoe[numTools];
		swords = new MachiniTechSword[numTools];
		for (int i = 0; i < numTools; i++) {
			OreMachiniTech ore = OreMachiniTech.ores[i];
			materials[i] = EnumHelper.addToolMaterial(ore.getName(), ore.getHarv(), 250 * ore.getHarv(), 2 * (ore.getHarv() - 1), ore.getHarv(), 12);
			IngotMachiniTech ingot = IngotMachiniTech.ingots[i];
			picks[i] = new MachiniTechPickaxe(materials[i], ingot);
			picks[i].setCreativeTab(MachiniTechCore.ctitem);
			spades[i] = new MachiniTechShovel(materials[i], ingot);
			spades[i].setCreativeTab(MachiniTechCore.ctitem);
			axes[i] = new MachiniTechAxe(materials[i], ingot);
			axes[i].setCreativeTab(MachiniTechCore.ctitem);
			hoes[i] = new MachiniTechHoe(materials[i], ingot);
			hoes[i].setCreativeTab(MachiniTechCore.ctitem);
			swords[i] = new MachiniTechSword(materials[i], ingot);
			swords[i].setCreativeTab(MachiniTechCore.ctitem);
		}
		System.out.println(materials[0].getDamageVsEntity());
	}
	public static int getNumTools() {
		return numTools;
	}
}
