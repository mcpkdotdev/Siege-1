package net.siegerpg.siege.core.items.implemented.misc.skills.archer

import net.siegerpg.siege.core.items.types.misc.CustomSkill
import net.siegerpg.siege.core.skills.archer.CriticalShot
import net.siegerpg.siege.core.skills.archer.SerpentineArrow
import org.bukkit.inventory.ItemStack

class SerpentineArrow() : CustomSkill(
		customModelData = 920007,
		skill = SerpentineArrow(),
                                     ) {


	constructor(level : Int) : this() {
		this.level = level
		this.serialize()
	}

	constructor(item : ItemStack) : this() {
		this.item = item
		deserialize()
	}

}