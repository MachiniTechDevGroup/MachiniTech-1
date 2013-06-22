package machinitech.common.tile;

public class MachiniTechMachine extends MachiniTechTileEntity {
	private int Tier;
	public MachiniTechMachine (int t) {
		this.Tier = t;
	}
	public int getTier() {
		return this.Tier;
	}
}
