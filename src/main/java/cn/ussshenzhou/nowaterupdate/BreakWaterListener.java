package cn.ussshenzhou.nowaterupdate;

import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * @author USS_Shenzhou
 */
@EventBusSubscriber
public class BreakWaterListener {

    @SubscribeEvent
    public static void allowBreakWater(PlayerInteractEvent.LeftClickBlock event) {
        var level = event.getLevel();
        if (event.getAction() == PlayerInteractEvent.LeftClickBlock.Action.START && event.getEntity().isCreative() && !level.isClientSide() && level.getBlockState(event.getPos()).is(Blocks.WATER)) {
            level.setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 3);
        }
    }
}
