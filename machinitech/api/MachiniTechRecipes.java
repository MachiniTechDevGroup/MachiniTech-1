package machinitech.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * A class containing static methods which interact with MachiniTech machines by providing recipes
 * @author getmemoney
 */
public class MachiniTechRecipes {
	private static MachiniTechRecipes instance = new MachiniTechRecipes();
	private static MachiniTechRecipes.SmelterRecipes smelter = instance.new SmelterRecipes();
	public MachiniTechRecipes() {
		//Nothing here.
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
		smelter.addSmelterRecipe(id, meta, output, heat);
	}
	
	/**
	 * Adds a recipe to the smelter's list, ready to be used in the smelter
	 * For convenience, this is included, calling addSmelterRecipe with 0 as the metadata parametre
	 * @param id The Item ID of the input
	 * @param output An ItemStack representing the output
	 * @param heat The smelting temperature of the item
	 */
	public static void addSmelterRecipe(int id, ItemStack output, int heat) {
		smelter.addSmelterRecipe(id, 0, output, heat);
	}
	
	/**
	 * Retrieves the smelting result of input
	 * @param input The Input ItemStack
	 * @return An ItemStack representing the output that the Smelter gives in case of using input
	 */
	public static ItemStack getSmeltingResult(ItemStack input) {
		return smelter.getSmeltingResult(input);
	}
	
	/**
	 * Retrieves the smelting temperature of input
	 * @param input The Input ItemStack
	 * @return An int representing the smelting temperature of the item
	 */
	public static int getSmeltingHeat(ItemStack input) {
		return smelter.getSmeltingHeat(input).intValue();
	}

	private class SmelterRecipes {
		private HashMap<List<Integer>, ItemStack> SmeltingList = new HashMap<List<Integer>, ItemStack>();
		private HashMap<List<Integer>, Integer> SmeltingHeat = new HashMap<List<Integer>, Integer>();
		SmelterRecipes() {//Adds smelter recipes, will be done later
			
		}
		
		/**
		 * Adds a recipe to the smelter's list, ready to be used in the smelter
		 * This version is metadata sensitive
		 * @param id The Item ID of the input
		 * @param meta The Item Metadata of the Input
		 * @param output An ItemStack representing the output
		 * @param heat The smelting temperature of the item
		 */
		void addSmelterRecipe(int id, int meta, ItemStack output, int heat) {
			SmeltingList.put(Arrays.asList(id, meta), output);
		}
		
		/**
		 * Retrieves the smelting result of input
		 * @param input The Input ItemStack
		 * @return An ItemStack representing the output that the Smelter gives in case of using input
		 */
		ItemStack getSmeltingResult(ItemStack input) {
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
		Integer getSmeltingHeat(ItemStack input) {
			if (input == null) {
				return null;
			}
			Integer temp = (Integer)SmeltingHeat.get(Arrays.asList(input.itemID, input.getItemDamage()));
			if (temp != null) {
				return temp;
			}
			return null;
		}
	}

}
