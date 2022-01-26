package net.siegerpg.siege.core.drops.materials;

import net.siegerpg.siege.core.drops.BlockDropTable;
import net.siegerpg.siege.core.drops.Reward;
import net.siegerpg.siege.core.items.implemented.misc.materials.drops.blocks.Pebble;
import org.bukkit.Material;

public class STONE extends BlockDropTable {

	public STONE() {

		super(100, Material.STONE, 0, 1, 0, 0, new Reward[] {
				new Reward(new Pebble()
						           .getUpdatedItem(false), 100.0),
				new Reward(new Pebble()
						           .getUpdatedItem(false), 10.0),
				new Reward(new Pebble()
						           .getUpdatedItem(false), 0.1)
		});
	}

}
