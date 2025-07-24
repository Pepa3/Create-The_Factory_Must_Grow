package com.drmangotea.tfmg.content.electricity.connection.cable_hub;

import com.drmangotea.tfmg.content.electricity.base.ElectricBlockEntity;
import com.drmangotea.tfmg.content.electricity.base.IElectric;
import com.drmangotea.tfmg.registry.TFMGBlockEntities;
import com.drmangotea.tfmg.registry.TFMGBlocks;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CableHubBlock extends Block implements IBE<CableHubBlockEntity>, IWrenchable {
    public CableHubBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void onPlace(BlockState pState, Level level, BlockPos pos, BlockState pOldState, boolean pIsMoving) {
        for(Direction facing : Direction.values()) {
            BlockState neighbourState = level.getBlockState(pos.relative(facing));
             if (neighbourState.is(TFMGBlocks.NEON_TUBE.get())) {
                 level.setBlockAndUpdate(pos.relative(facing), neighbourState.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(facing.getOpposite()), true));
             }
        }
        withBlockEntityDo(level,pos, IElectric::onPlaced);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        IBE.onRemove(state, level, pos, newState);
    }
    @Override
    public Class<CableHubBlockEntity> getBlockEntityClass() {
        return CableHubBlockEntity.class;
    }
    @Override
    public BlockEntityType<? extends CableHubBlockEntity> getBlockEntityType() {
        return TFMGBlockEntities.CABLE_HUB.get();
    }
}
