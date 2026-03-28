package cn.ussshenzhou.nowaterupdate.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * @author USS_Shenzhou
 */
@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @ModifyArg(method = "pick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;pick(DFZ)Lnet/minecraft/world/phys/HitResult;"), index = 2)
    private static boolean noWaterUpdateCanPickWater(boolean withLiquids, @Local(argsOnly = true) Entity cameraEntity) {
        if (cameraEntity instanceof Player player && player.isCreative()) {
            return !cameraEntity.isEyeInFluid(FluidTags.WATER);
        }
        return withLiquids;
    }
}
