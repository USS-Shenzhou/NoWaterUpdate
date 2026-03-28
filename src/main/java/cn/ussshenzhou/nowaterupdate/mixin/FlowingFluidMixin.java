package cn.ussshenzhou.nowaterupdate.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author USS_Shenzhou
 */
@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {

    @Inject(method = "canMaybePassThrough", at = @At("HEAD"), cancellable = true)
    private void noWaterUpdateDisableWaterUpdate(BlockGetter level, BlockPos sourcePos, BlockState sourceState, Direction direction, BlockPos testPos, BlockState testState, FluidState testFluidState, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof WaterFluid) {
            cir.setReturnValue(false);
        }
    }
}
