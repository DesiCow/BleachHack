package bleach.hack.mixin;

import bleach.hack.BleachHack;
import bleach.hack.event.events.EventPreTick;
import bleach.hack.event.events.EventTick;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import bleach.hack.utils.BleachQueue;
import bleach.hack.utils.file.BleachFileReader;

@Mixin(ClientPlayerEntity.class)
public class MixinPlayerEntity {
	
	@Inject(at = @At("RETURN"), method = "tick()V")
	public void tick(CallbackInfo info) {
		try {
			if(MinecraftClient.getInstance().player.age % 100 == 0) {
				BleachFileReader.saveModules();
				BleachFileReader.saveSettings();
				BleachFileReader.saveBinds();
				BleachFileReader.saveClickGui();
			}
			
			BleachQueue.nextQueue();
		}catch(Exception e) {}
		BleachHack.getEventBus().post(new EventTick());
	}
	
	@Inject(at = @At("HEAD"), method = "tick()V")
	public void tick2(CallbackInfo info) {
		BleachHack.getEventBus().post(new EventPreTick());
	}
}
