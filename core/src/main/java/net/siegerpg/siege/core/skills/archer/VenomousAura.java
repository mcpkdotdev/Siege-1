package net.siegerpg.siege.core.skills.archer;

import net.siegerpg.siege.core.skills.Skill;
import net.siegerpg.siege.core.skills.SkillClass;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;

public class VenomousAura extends Skill {

	final int initCooldown = 60 * 1000;
	final int initManaCost = 150;
	final int initGoldCost = 5000;
	final double damageMulti = 1.5; //amount to multiply damage by
	private final String identifier = "2_A_3";
	private final SkillClass skillClass = SkillClass.ARCHER;
	private final String name = "Venomous Aura";
	private final List< String > description =
			List.of(
					"Increase your damage by +50% and",
					"poison yourself for 30 seconds"
			       );

	@Override
	public String getName(int level) {

		return this.name + " Lvl. " + level;
	}

	@Override
	public List< String > getDescription(int level) {

		return List.of(
				"Increase your damage by +" + ((getDamageMulti(level) - 1) * 100) + "% and",
				"poison yourself for 30 seconds"
		              );
	}


	@Override
	public Duration getCooldown(int level) {

		int time = (int) (this.initCooldown + Math.ceil(this.initCooldown * level * 0.03));
		return Duration.ofMillis(time);
	}

	@Override
	public double getManaCost(int level) {

		return (int) (this.initManaCost + Math.ceil(this.initManaCost * level * 0.05));
	}


	public double getDamageMulti(int level) {

		return (this.damageMulti) + (level * 0.05);
	}

	@Override
	public int getGoldCost(int level) {

		return (int) (this.initGoldCost * level * 5.0);
	}

	@Override
	public boolean trigger(@NotNull Player player, int level) {
		// First we check if the cooldown and mana are respected (we run the code common to all skills)
		// If the trigger() method returns false it means that the execution was not successful (for example the cooldown wasn't finished) so we stop executing and return false
		return super.trigger(player, level);

		// Handling of the skill goes here
	}

	@Override
	public void triggerEnd(@NotNull Player player, int level) {

		super.triggerEnd(player, level);

	}

}
