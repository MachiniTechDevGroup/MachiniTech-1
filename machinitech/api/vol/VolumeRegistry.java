package machinitech.api.vol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * A class containing static methods relating to volume based crafting
 * @author getmemoney
 */
public class VolumeRegistry {

	private static HashMap<List<Integer>, Integer> vol = new HashMap<List<Integer>, Integer>();

	private VolumeRegistry() {// Overrides default. Can't instantiate MWHAHAHAHAHAHAHAHAHA
		throw new RuntimeException("Cannot instantiate machinitech.api.vol.VolumeRegistry");//Clever.
	}

	/**
	 * Adds volume to the registry for the given item with id and meta
	 * @param id The ID of the item
	 * @param meta The metadata of the item. Pass {@code OreDictionary.WILDCARD_VALUE} to make a metadata-insensitive version
	 * @param vol The volume of the item
	 */
	public static void addVolumetoItem(int id, int meta, int vl) {
		vol.put(Arrays.asList(id, meta), vl);
	}

	/**
	 * Adds volume to the registry for the given {@code ItemStack}
	 * @param stack The {@code ItemStack} to add volume to
	 * @param meta true if metadata-sensitive, false if metadata-insensitive
	 * @param vl The volume of the item
	 */
	public static void addVolumetoItem(ItemStack stack, boolean meta, int vl) {
		addVolumetoItem(stack.itemID, (meta ? stack.getItemDamage() : OreDictionary.WILDCARD_VALUE), vl);
	}

	/**
	 * Retrieves the volume of the given item with id and meta
	 * @param id The ID of the item
	 * @param meta The metadata of the item
	 * @return The volume of the item, or items if {@code addVolumetoItem} was called with metadata parametre {@code OreDictionary.WILDCARD_VALUE}, or 0 if there is none
	 */
	public static int getVolumeofItem(int id, int meta) {
		if (vol.containsKey(Arrays.asList(id, meta))) {
			return vol.get(Arrays.asList(id, meta));
		} else if (vol.containsKey(Arrays.asList(id, OreDictionary.WILDCARD_VALUE))) {
			return vol.get(Arrays.asList(id, OreDictionary.WILDCARD_VALUE));
		}
		return 0;
	}

	/**
	 * Retrieves the volume of the give {@code ItemStack}
	 * @param stack The {@code ItemStack} to get the volume of
	 * @return The volume of the item, or items if {@code addVolumetoItem} was called with metadata parametre {@code OreDictionary.WILDCARD_VALUE}, or 0 if there is none
	 */
	public static int getVolumeofItem(ItemStack stack) {
		return getVolumeofItem(stack.itemID, stack.getItemDamage());
	}

}