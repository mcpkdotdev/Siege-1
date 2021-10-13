package net.siegerpg.siege.core.items.implemented.misc.wands.earthernStaffs

import net.siegerpg.siege.core.items.CustomItemUtils
import net.siegerpg.siege.core.items.enums.Rarity
import net.siegerpg.siege.core.items.types.misc.CustomWand
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ToughEarthernStaff() : CustomWand(
    name = "Tough Earthern Staff",
    customModelData = 140010,
    description = listOf("More powerful than it's", "puny wand counterpart"),
    levelRequirement = 37,
    material = Material.WOODEN_HOE,
    baseStats = CustomItemUtils.statMap(strength = 25.0, luck = 6.0, toughness = 70.0),
    range = 17,
    red = 0,
    green = 204,
    blue = 0,
    damageRadius = 2.0
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