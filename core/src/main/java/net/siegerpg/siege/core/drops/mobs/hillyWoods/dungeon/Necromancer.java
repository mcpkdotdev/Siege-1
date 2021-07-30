package net.siegerpg.siege.core.drops.mobs.hillyWoods.dungeon;

import net.siegerpg.siege.core.drops.MobDropTable;
import net.siegerpg.siege.core.drops.Reward;
import net.siegerpg.siege.core.items.implemented.misc.keys.hillyWoods.NecromancerKey;
import net.siegerpg.siege.core.items.implemented.misc.keys.hillyWoods.RockSpiritKey;
import net.siegerpg.siege.core.items.implemented.misc.materials.drops.blocks.MetalScrap;
import net.siegerpg.siege.core.items.implemented.misc.materials.drops.blocks.Pebble;
import net.siegerpg.siege.core.items.implemented.misc.materials.drops.blocks.Seed;
import net.siegerpg.siege.core.items.implemented.misc.materials.drops.mobs.Bone;
import net.siegerpg.siege.core.items.implemented.misc.statgems.healthGems.FlawedHealthGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.healthGems.SimpleHealthGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.luckGems.FlawedLuckGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.luckGems.SimpleLuckGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.regenerationGems.CrackedRegenerationGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.regenerationGems.FlawedRegenerationGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.regenerationGems.SimpleRegenerationGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.strengthGems.CrackedStrengthGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.strengthGems.FlawedStrengthGem;
import net.siegerpg.siege.core.items.implemented.misc.statgems.strengthGems.SimpleStrengthGem;
import net.siegerpg.siege.core.items.implemented.misc.wands.RockWand;
import net.siegerpg.siege.core.items.implemented.weapons.ranged.PebbleShooter;
import net.siegerpg.siege.core.utils.Utils;

public class Necromancer extends MobDropTable {
    public Necromancer() {
        super("Necromancer", 1200, 1400, 2200, 2500, new Reward[]{
                new Reward(Bone.Companion.tier(1).getUpdatedItem(false).asQuantity(32), 40.0),
                new Reward(Bone.Companion.tier(2).getUpdatedItem(false).asQuantity(8), 50.0),
                new Reward(Bone.Companion.tier(3).getUpdatedItem(false).asQuantity(4), 20.0),
                new Reward(MetalScrap.Companion.tier(1).getUpdatedItem(false).asQuantity(32), 40.0),
                new Reward(MetalScrap.Companion.tier(2).getUpdatedItem(false).asQuantity(8), 50.0),
                new Reward(MetalScrap.Companion.tier(3).getUpdatedItem(false).asQuantity(4), 20.0),

                new Reward(new CrackedStrengthGem(Utils.randRarity()).getUpdatedItem(false), 80.0),
                new Reward(new FlawedStrengthGem(Utils.randRarity()).getUpdatedItem(false), 60.0),
                new Reward(new SimpleStrengthGem(Utils.randRarity()).getUpdatedItem(false), 40.0),
                new Reward(new FlawedHealthGem(Utils.randRarity()).getUpdatedItem(false), 60.0),
                new Reward(new SimpleHealthGem(Utils.randRarity()).getUpdatedItem(false), 40.0),
                new Reward(new NecromancerKey(0).getUpdatedItem(false), 10.0)

        });
    }
}