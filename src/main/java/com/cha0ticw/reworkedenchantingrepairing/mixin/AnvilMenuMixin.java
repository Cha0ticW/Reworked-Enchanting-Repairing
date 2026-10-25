package com.cha0ticw.reworkedenchantingrepairing.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @Shadow @Final private DataSlot cost;

    @Inject(method = "mayPickup", at = @At("HEAD"), cancellable = true)
    private void allowFreeRepairs(Player player, boolean hasStack,
                                  CallbackInfoReturnable<Boolean> cir) {
        if (hasStack && this.cost.get() == 0) {
            cir.setReturnValue(true);
        }
    }
}