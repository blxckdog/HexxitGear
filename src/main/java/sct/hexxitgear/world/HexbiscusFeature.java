package sct.hexxitgear.world;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import sct.hexxitgear.HexxitGear;

public class HexbiscusFeature extends Feature<HexbiscusFeatureConfig>{
	
	public HexbiscusFeature(Codec<HexbiscusFeatureConfig> configCodec) {
		super(configCodec);
		
		/*
		config = new RandomPatchFeatureConfig.Builder(
				new SimpleBlockStateProvider(HexxitGear.HEXBISCUS_FLOWER.getDefaultState()), 
				SimpleBlockPlacer.INSTANCE
		).tries(2).build();
		
		feature = Feature.FLOWER.configure(config)
				.decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE)
				.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP);
		*/
	}


	@Override
	public boolean generate(FeatureContext<HexbiscusFeatureConfig> context) {
		BlockPos topPos = context.getWorld().getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, context.getOrigin());
		
		context.getWorld().setBlockState(topPos.up(1), HexxitGear.HEXBISCUS_FLOWER.getDefaultState(), 0);
		
		return true;
	}
	
}
