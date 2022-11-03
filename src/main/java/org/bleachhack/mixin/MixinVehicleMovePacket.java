package org.bleachhack.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import org.bleachhack.module.mods.HumanBypass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VehicleMoveC2SPacket.class)
public class MixinVehicleMovePacket {

    // Anti-human bypass for X
    @Redirect(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getX()D"))
    public double getX(Entity instance) {
        return HumanBypass.roundCoordinate(instance.getX());
    }

    // Anti-human bypass for Z
    @Redirect(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getZ()D"))
    public double getZ(Entity instance) {
        return HumanBypass.roundCoordinate(instance.getZ());
    }
}
