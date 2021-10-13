package net.siegerpg.siege.core.items.implemented.armor.helmet.ironHelmets

import net.siegerpg.siege.core.items.CustomItemUtils
import net.siegerpg.siege.core.items.enums.Rarity
import net.siegerpg.siege.core.items.types.armor.CustomHelmet
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ToughIronHelmet() : CustomHelmet(
    name = "Tough Iron Helmet",
    customModelData = 1,
    description = listOf("Thick skull"),
    levelRequirement = 34,
    material = Material.IRON_HELMET,
    baseStats = CustomItemUtils.statMap(health = 10.0, toughness = 100.0),
) {

    constructor(quality: Int): this() {
        this.quality = quality
        this.rarity = Rarity.getFromInt(quality)
        this.serialize()
    }

    constructor(item: ItemStack): this() {
        this.item = item
        deserialize()
    }

}