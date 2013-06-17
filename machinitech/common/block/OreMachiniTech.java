package machinitech.common.block;

import machinitech.common.item.IngotMachiniTech;

public class OreMachiniTech {
		public String Name;
		private Params p;
		private int id, meta;
		private int harv;
		public static final int NUM_ORES = 4;
		public static final int Ore_ID = 500;
		public static final OreMachiniTech[] ores = new OreMachiniTech[NUM_ORES];
		public static void createOres() {
			ores[0] = new OreMachiniTech (500, 0, "Copper", 1, new OreMachiniTech.Params(1, 63, 15, 4, 12));
			ores[1] = new OreMachiniTech (501, 0, "Tin", 1, new OreMachiniTech.Params(16, 48, 8, 4, 8));
			ores[2] = new OreMachiniTech (502, 0, "Lead", 1, new OreMachiniTech.Params(16, 24, 2, 4, 8));
			ores[3] = new OreMachiniTech (503, 0, "Nickel", 2, new OreMachiniTech.Params(16, 64, 4, 8, 12));
			IngotMachiniTech.createIngots(ores);
		}
		public OreMachiniTech (int i, int m, String n, int h, Params p) {
			this.Name = n;
			this.p = p;
			this.id = i;
			this.harv = h;
			this.meta = m;
		}
		public static OreMachiniTech getBasedIDMeta(int id, int m) {
			for (int i = 0; i < ores.length; i++) {
				if (id == ores[i].id && m == ores[i].meta) {
					return ores[i];
				}
			}
			return null;
		}
		public static class Params {
			int l, h, n, ol, oh;
			public Params(int l, int h, int n, int ol, int oh) {
				this.l = l;
				this.h = h;
				this.n = n;
				this.ol = ol;
				this.oh = oh + 1;
			}
			public int getLow() {
				return l;
			}
			public int getHigh() {
				return h;
			}
			public int getNum() {
				return n;
			}
			public int getOresLow() {
				return ol;
			}
			public int getOresHigh() {
				return oh;
			}
		}
		public int getID() {
			return this.id;
		}
		public int getMeta() {
			return this.meta;
		}
		public int getHarv() {
			return this.harv;
		}
		public String getName() {
			return this.Name;
		}
		public Params getParams() {
			return this.p;
		}
}
