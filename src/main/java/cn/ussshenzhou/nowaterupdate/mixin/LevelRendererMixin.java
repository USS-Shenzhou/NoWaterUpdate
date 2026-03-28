package cn.ussshenzhou.nowaterupdate.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author USS_Shenzhou
 */
@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @WrapOperation(method = "extractBlockOutline", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;"))
    private VoxelShape noWaterUpdateRenderOutlineForWater(BlockState instance, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext, Operation<VoxelShape> original) {
        if (instance.is(Blocks.WATER)) {
            return Shapes.block();
        }
        return original.call(instance, blockGetter, blockPos, collisionContext);
    }
}
