package machinitech.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import machinitech.common.block.OreMachiniTech;
import machinitech.common.core.MachiniTechCore;
import net.minecraft.item.ItemStack;

/**
 * A class containing static methods which interact with MachiniTech machines by providing recipes
 * @author getmemoney
 */
public class MachiniTechRecipes {
	private static HashMap<List<Integer>, ItemStack> SmeltingList = new HashMap<List<Integer>, ItemStack>();
	private static HashMap<List<Integer>, Integer> SmeltingHeat = new HashMap<List<Integer>, Integer>();
	/**
	 * Adds recipes
	 */
	public static void addRecipes() {
		for (int i = 0; i < OreMachiniTech.NUM_ORES; i++) {
			OreMachiniTech ore = OreMachiniTech.ores[i];
			addSmelterRecipe(ore.getID(), ore.getMeta(), new ItemStack(MachiniTechCore.ingots[i]), ore.getProps().getSmeltingHeat());
		}
	}
	
	
	/**
	 * Adds a recipe to the smelter's list, ready to be used in the smelter
	 * This version is metadata sensitive
	 * @param id The Item ID of the input
	 * @param meta The Item Metadata of the Input
	 * @param output An ItemStack representing the output
	 * @param heat The smelting temperature of the item
	 */
	public static void addSmelterRecipe(int id, int meta, ItemStack output, int heat) {
		SmeltingList.put(Arrays.asList(id, meta), output);
		SmeltingHeat.put(Arrays.asList(id, meta), heat);
		System.out.println("Adding smelting recipe to make " + output.getDisplayName() + " by smelting id " + id + " and meta " + meta + " with " + heat + " heat");
	}
	
	/**
	 * Adds a recipe to the smelter's list, ready to be used in the smelter
	 * This version is for convenience
	 * @param id The Item ID of the input
	 * @param output An ItemStack representing the output
	 * @param heat The smelting temperature of the item
	 */
	public static void addSmelterRecipe(int id, ItemStack output, int heat) {
		addSmelterRecipe(id, 0, output, heat);
	}
	
	/**
	 * Retrieves the smelting result of input
	 * @param input The Input ItemStack
	 * @return An ItemStack representing the output that the Smelter gives in case of using input
	 */
	public static ItemStack getSmeltingResult(ItemStack input) {
		if (input == null) {
			return null;
		}
		ItemStack ret = (ItemStack)SmeltingList.get(Arrays.asList(input.itemID, input.getItemDamage()));
		if (ret != null) {
			return ret;
		}
		return null;
	}
	
	/**
	 * Retrieves the smelting temperature of input
	 * @param input The Input ItemStack
	 * @return An int representing the smelting temperature of the item
	 */
	public static int getSmeltingHeat(ItemStack input) {
		if (input == null || input.getItem() == null) {
            return 0;
        }
        int ret = 0;
        if (SmeltingHeat.containsKey(Arrays.asList(input.itemID, input.getItemDamage()))) {
        	ret = SmeltingHeat.get(Arrays.asList(input.itemID, input.getItemDamage()));
        }
        return (ret < 0 ? 0 : ret);
	}

}
