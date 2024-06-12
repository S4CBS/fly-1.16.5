package ru.yanes.fly.procedures;

import ru.yanes.fly.FlyModVariables;
import ru.yanes.fly.FlyMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

public class FlyprocjoinintheworldProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				FlyMod.LOGGER.warn("Failed to load dependency entity for procedure Flyprocjoinintheworld!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(FlyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new FlyModVariables.PlayerVariables())).fly_on_off == 1) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.allowFlying = (true);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
		}
	}
}
