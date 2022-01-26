package net.siegerpg.siege.core.drops.mobs.hillyWoods.bosses;

import net.siegerpg.siege.core.drops.MobDropTable;
import net.siegerpg.siege.core.drops.Reward;
import net.siegerpg.siege.core.items.implemented.armor.boots.BoneBoots;
import net.siegerpg.siege.core.items.implemented.armor.boots.boneBoots.*;
import net.siegerpg.siege.core.items.implemented.armor.chestplate.BoneChestplate;
import net.siegerpg.siege.core.items.implemented.armor.chestplate.boneChestplates.*;
import net.siegerpg.siege.core.items.implemented.armor.helmet.BoneHelmet;
import net.siegerpg.siege.core.items.implemented.armor.helmet.boneHelmets.*;
import net.siegerpg.siege.core.items.implemented.armor.leggings.BoneLeggings;
import net.siegerpg.siege.core.items.implemented.armor.leggings.boneLeggings.*;
import net.siegerpg.siege.core.items.implemented.misc.keys.crate.MobKey;
import net.siegerpg.siege.core.items.implemented.misc.keys.hillyWoods.DavyJonesKey;
import net.siegerpg.siege.core.items.implemented.misc.materials.drops.mobs.Bone;
import net.siegerpg.siege.core.items.implemented.misc.materials.drops.mobs.Leather;
import net.siegerpg.siege.core.items.implemented.weapons.melee.heavy.DoubleBladedAxe;
import net.siegerpg.siege.core.items.implemented.weapons.melee.heavy.doubleBladedAxes.*;
import net.siegerpg.siege.core.miscellaneous.Utils;

public class Orc extends MobDropTable {

	public Orc() {

		super("Orc", 55, 59, 60, 64, new Reward[] {
				new Reward(new Leather().getUpdatedItem(false), 50.0),
				new Reward(new Leather().getUpdatedItem(false).asQuantity(4), 5.0),
				new Reward(new Bone().getUpdatedItem(false), 50.0),
				new Reward(new Bone().getUpdatedItem(false).asQuantity(4), 5.0),

				new Reward(new BoneHelmet(50).getUpdatedItem(false), 0.5),
				new Reward(new BoneChestplate(50).getUpdatedItem(false), 0.5),
				new Reward(new BoneLeggings(50).getUpdatedItem(false), 0.5),
				new Reward(new BoneBoots(50).getUpdatedItem(false), 0.5),

				new Reward(new ToughBoneHelmet(30).getUpdatedItem(false), 0.2),
				new Reward(new ToughBoneChestplate(30).getUpdatedItem(false), 0.2),
				new Reward(new ToughBoneLeggings(30).getUpdatedItem(false), 0.2),
				new Reward(new ToughBoneBoots(30).getUpdatedItem(false), 0.2),

				new Reward(new HealingBoneHelmet(30).getUpdatedItem(false), 0.2),
				new Reward(new HealingBoneChestplate(30).getUpdatedItem(false), 0.2),
				new Reward(new HealingBoneLeggings(30).getUpdatedItem(false), 0.2),
				new Reward(new HealingBoneBoots(30).getUpdatedItem(false), 0.2),

				new Reward(new HealthyBoneHelmet(30).getUpdatedItem(false), 0.2),
				new Reward(new HealthyBoneChestplate(30).getUpdatedItem(false), 0.2),
				new Reward(new HealthyBoneLeggings(30).getUpdatedItem(false), 0.2),
				new Reward(new HealthyBoneBoots(30).getUpdatedItem(false), 0.2),

				new Reward(new StrongBoneHelmet(30).getUpdatedItem(false), 0.2),
				new Reward(new StrongBoneChestplate(30).getUpdatedItem(false), 0.2),
				new Reward(new StrongBoneLeggings(30).getUpdatedItem(false), 0.2),
				new Reward(new StrongBoneBoots(30).getUpdatedItem(false), 0.2),

				new Reward(new LuckyBoneHelmet(30).getUpdatedItem(false), 0.2),
				new Reward(new LuckyBoneChestplate(30).getUpdatedItem(false), 0.2),
				new Reward(new LuckyBoneLeggings(30).getUpdatedItem(false), 0.2),
				new Reward(new LuckyBoneBoots(30).getUpdatedItem(false), 0.2),

				new Reward(new BoneHelmet(Utils.randRarity()).getUpdatedItem(false), 1.0),
				new Reward(new BoneChestplate(Utils.randRarity()).getUpdatedItem(false), 1.0),
				new Reward(new BoneLeggings(Utils.randRarity()).getUpdatedItem(false), 1.0),
				new Reward(new BoneBoots(Utils.randRarity()).getUpdatedItem(false), 1.0),

				new Reward(new ToughBoneHelmet(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new ToughBoneChestplate(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new ToughBoneLeggings(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new ToughBoneBoots(Utils.randRarity()).getUpdatedItem(false), 0.1),

				new Reward(new HealingBoneHelmet(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new HealingBoneChestplate(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new HealingBoneLeggings(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new HealingBoneBoots(Utils.randRarity()).getUpdatedItem(false), 0.1),

				new Reward(new HealthyBoneHelmet(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new HealthyBoneChestplate(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new HealthyBoneLeggings(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new HealthyBoneBoots(Utils.randRarity()).getUpdatedItem(false), 0.1),

				new Reward(new StrongBoneHelmet(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new StrongBoneChestplate(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new StrongBoneLeggings(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new StrongBoneBoots(Utils.randRarity()).getUpdatedItem(false), 0.1),

				new Reward(new LuckyBoneHelmet(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new LuckyBoneChestplate(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new LuckyBoneLeggings(Utils.randRarity()).getUpdatedItem(false), 0.1),
				new Reward(new LuckyBoneBoots(Utils.randRarity()).getUpdatedItem(false), 0.1),

				new Reward(new DoubleBladedAxe(80).getUpdatedItem(false), 0.5),
				new Reward(new LuckyDoubleBladedAxe(50).getUpdatedItem(false), 0.1),
				new Reward(new ToughDoubleBladedAxe(50).getUpdatedItem(false), 0.1),
				new Reward(new StrongDoubleBladedAxe(50).getUpdatedItem(false), 0.1),
				new Reward(new HealingDoubleBladedAxe(50).getUpdatedItem(false), 0.1),
				new Reward(new HealthyDoubleBladedAxe(50).getUpdatedItem(false), 0.1),

				new Reward(new DoubleBladedAxe(Utils.randRarity()).getUpdatedItem(false), 1.5),
				new Reward(new LuckyDoubleBladedAxe(Utils.randRarity()).getUpdatedItem(false), 0.2),
				new Reward(new ToughDoubleBladedAxe(Utils.randRarity()).getUpdatedItem(false), 0.2),
				new Reward(new StrongDoubleBladedAxe(Utils.randRarity()).getUpdatedItem(false), 0.2),
				new Reward(new HealingDoubleBladedAxe(Utils.randRarity()).getUpdatedItem(false), 0.2),
				new Reward(new HealthyDoubleBladedAxe(Utils.randRarity()).getUpdatedItem(false), 0.2),

				new Reward(new DavyJonesKey(0).getUpdatedItem(false), 3.0),
				new Reward(new MobKey(0).getUpdatedItem(false), 1.0),

				});
	}

}
