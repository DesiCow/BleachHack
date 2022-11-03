package org.bleachhack.module.mods;

import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public class HumanBypass extends Module {
    public HumanBypass() {
        super("HumanBypass", KEY_UNBOUND, ModuleCategory.LIVEOVERFLOW, "Mimics Bot Movement to bypass Anti Human Plugins");
    }

    public static double roundCoordinate(double n) {
        n = Math.round(n * 100) / 100d;
        return Math.nextAfter(n, n + Math.signum(n));
    }

    public static void onPositionPacket(Args args) {
        double x = args.get(0);
        double y = args.get(1);
        double z = args.get(2);

        x = roundCoordinate(x);
        z = roundCoordinate(z);

        args.set(0, x);
        args.set(1, y);
        args.set(2, z);
    }
}