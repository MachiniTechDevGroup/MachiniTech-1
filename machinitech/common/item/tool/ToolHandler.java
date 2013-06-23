package machinitech.common.item.tool;

import machinitech.common.block.OreMachiniTech;
import machinitech.common.core.MachiniTechCore;
import machinitech.common.item.IngotMachiniTech;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

import static machinitech.common.core.MachiniTechCore.config;

public class ToolHandler {
	public static final int pickIDdef = config.get("Item ID", "FIrstPick", 1000).getInt(1000);
	public static final int axeIDdef = config.get("Item ID", "FIrstAxe", 1100).getInt(1100);
	public static final int swordIDdef = config.get("Item ID", "FIrstSword", 1200).getInt(1200);
	public static final int shovIDdef = config.get("Item ID", "FIrstSpade", 1300).getInt(1300);
	public static final int hoeIDdef = config.get("Item ID", "FIrstHoe", 1400).getInt(1400);
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
			materials[i] = EnumHelper.addToolMaterial(ore.getName(), ore.getHarv(), 250 * ore.getHarv(), 2 * (ore.getHarv() + 1), ore.getHarv(), 12);
			IngotMachiniTech ingot = IngotMachiniTech.ingots[i];
			picks[i] = new MachiniTechPickaxe(materials[i], ore);
			picks[i].setCreativeTab(MachiniTechCore.ctitem);
			spades[i] = new MachiniTechShovel(materials[i], ore);
			spades[i].setCreativeTab(MachiniTechCore.ctitem);
			axes[i] = new MachiniTechAxe(materials[i], ore);
			axes[i].setCreativeTab(MachiniTechCore.ctitem);
			hoes[i] = new MachiniTechHoe(materials[i], ingot);
			hoes[i].setCreativeTab(MachiniTechCore.ctitem);
			swords[i] = new MachiniTechSword(materials[i], ingot);
			swords[i].setCreativeTab(MachiniTechCore.ctitem);
			System.out.println("Going through loop " + i + " time(s) to make " + ingot.getName() + " tools.");
		}
		System.out.println("Changing the configs for the tools WILL BREAK EXISTING WORLDS. BE SURE THAT YOUR WORLD HAS THE TOOLS YOU WANT ENABLED BEFORE DOING ANYTHING ELSE! YOU HAVE BEEN WARNED!");
	}
	public static int getNumTools() {
		return numTools;
	}
}
