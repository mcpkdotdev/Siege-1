package net.siegerpg.siege.core.items.implemented.weapons.melee.heavy.doubleBladedAxes

import net.siegerpg.siege.core.items.CustomItemUtils
import net.siegerpg.siege.core.items.enums.Rarity
import net.siegerpg.siege.core.items.types.weapons.CustomMeleeWeapon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class HealingDoubleBladedAxe() : CustomMeleeWeapon(
		name = "Healing Double Bladed Axe",
		customModelData = 130005,
		description = listOf("You're really only going to use one side"),
		levelRequirement = 23,
		material = Material.WOODEN_AXE,
		baseStats = CustomItemUtils.statMap(strength = 35.0, regeneration = 5.0),
		attackSpeed = 0.7
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