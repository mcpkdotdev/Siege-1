package net.siegerpg.siege.core.items.implemented.misc.cosmetics.common

import net.siegerpg.siege.core.items.enums.Rarity
import net.siegerpg.siege.core.items.types.misc.Cosmetic
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class StrawHat() : Cosmetic(
    name = "Straw Hat",
    customModelData = 710012,
    description = listOf(""),
    material = Material.KNOWLEDGE_BOOK,
) {

    constructor(quality: Int): this() {
        this.quality = 0
        this.rarity = Rarity.COMMON
        this.serialize()
    }

    constructor(item: ItemStack): this() {
        this.item = item
        deserialize()
    }

}