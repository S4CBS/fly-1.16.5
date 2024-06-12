package ru.yanes.fly.procedures;

import ru.yanes.fly.FlyModVariables;
import ru.yanes.fly.FlyMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class FlyProcProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				FlyMod.LOGGER.warn("Failed to load dependency entity for procedure FlyProc!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (FlyModVariables.fly_on_off == 0) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowFlying = (true);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			FlyModVariables.fly_on_off = 1;
		} else if (FlyModVariables.fly_on_off == 1) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowFlying = (false);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.isFlying = (false);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			FlyModVariables.fly_on_off = 0;
		}
	}
}
