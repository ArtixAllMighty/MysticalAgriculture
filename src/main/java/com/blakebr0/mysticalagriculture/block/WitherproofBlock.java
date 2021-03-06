package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class WitherproofBlock extends BaseBlock {
    public WitherproofBlock() {
        super(Material.ROCK, SoundType.STONE, 20.0F, 2000.0F);
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 1;
    }

    @Override
    public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion) { }

    @Override
    public boolean canDropFromExplosion(Explosion explosion) {
        return false;
    }
}
