package net.siegerpg.siege.core.items.implemented.misc.materials.drops.blocks

import net.siegerpg.siege.core.items.enums.Rarity
import net.siegerpg.siege.core.items.types.misc.CustomMaterial
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class CharredStick() : CustomMaterial(
		name = "Charred Stick",
		customModelData = 320016,
		description = listOf("A burnt stick"),
		levelRequirement = 0,
		material = Material.FLINT,
		) {
	override fun getSellValue() : Int {
		return 15
	}
	constructor(quality : Int) : this() {
		this.quality = quality
		this.rarity = Rarity.getFromInt(quality)
		this.serialize()
	}

	constructor(item : ItemStack) : this() {
		this.item = item
		deserialize()
	}

}