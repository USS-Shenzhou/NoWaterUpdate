package cn.ussshenzhou.nowaterupdate.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * @author USS_Shenzhou
 */
@Mixin(BucketItem.class)
public class BucketItemMixin {

    @Shadow
    @Final
    public Fluid content;

    @ModifyArg(method = "use", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/item/BucketItem;getPlayerPOVHitResult(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/ClipContext$Fluid;)Lnet/minecraft/world/phys/BlockHitResult;"),
            index = 2)
    private ClipContext.Fluid noWaterUpdateAllowPlaceWaterOnWater(ClipContext.Fluid result, @Local(argsOnly = true) Player player) {
        return (player.isCreative() && this.content == Fluids.WATER) ? ClipContext.Fluid.WATER : result;
    }
}
