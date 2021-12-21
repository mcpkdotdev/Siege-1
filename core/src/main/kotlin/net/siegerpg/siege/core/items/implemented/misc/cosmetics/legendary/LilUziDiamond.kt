package net.siegerpg.siege.core.items.implemented.misc.cosmetics.legendary

import net.siegerpg.siege.core.items.enums.Rarity
import net.siegerpg.siege.core.items.types.misc.Cosmetic
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class LilUziDiamond() : Cosmetic(
		name = "Lil Uzi Diamond",
		customModelData = 750003,
		description = listOf("Strong forehead"),
		material = Material.KNOWLEDGE_BOOK,
                                ) {

	constructor(quality : Int) : this() {
		this.quality = 100
		this.rarity = Rarity.LEGENDARY
		this.serialize()
	}

	constructor(item : ItemStack) : this() {
		this.item = item
		deserialize()
	}

}