package net.siegerpg.siege.core.items.implemented.misc.skills.mage

import net.siegerpg.siege.core.items.types.misc.CustomSkill
import net.siegerpg.siege.core.skills.archer.CriticalShot
import net.siegerpg.siege.core.skills.archer.VenomousAura
import net.siegerpg.siege.core.skills.mage.IceBolt
import net.siegerpg.siege.core.skills.mage.ZombiePuppet
import org.bukkit.inventory.ItemStack

class ZombiePuppet() : CustomSkill(
		customModelData = 930014,
		skill = ZombiePuppet(),
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