package sct.hexxitgear.world;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import sct.hexxitgear.HexxitGear;

public class HexbiscusFeature {

	private final RandomPatchFeatureConfig config;
	private final ConfiguredFeature<?, ?> feature;
	
	
	public HexbiscusFeature() {
		/*
		config = new RandomPatchFeatureConfig.Builder(
				new SimpleBlockStateProvider(HexxitGear.HEXBISCUS_FLOWER.getDefaultState()), 
				SimpleBlockPlacer.INSTANCE
		).tries(2).build();
		
		feature = Feature.FLOWER.configure(config)
				.decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE)
				.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP);
		*/
		config = null;
		feature = null;
	}
	
	
	public ConfiguredFeature<?, ?> getFeature() {
		return feature;
	}
	
}
