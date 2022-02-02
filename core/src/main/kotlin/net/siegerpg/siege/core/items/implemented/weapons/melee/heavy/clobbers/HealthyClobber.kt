package net.siegerpg.siege.core.items.implemented.weapons.melee.heavy.clobbers

import net.siegerpg.siege.core.items.CustomItemUtils
import net.siegerpg.siege.core.items.enums.Rarity
import net.siegerpg.siege.core.items.types.weapons.CustomMeleeWeapon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class HealthyClobber() : CustomMeleeWeapon(
		name = "Healthy Clobber",
		customModelData = 130009,
		description = listOf("Let's go clobbing!"),
		levelRequirement = 50,
		material = Material.WOODEN_AXE,
		baseStats = CustomItemUtils.statMap(strength = 46.0, health = 10.0),
		attackSpeed = 0.9
                                          ) {

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