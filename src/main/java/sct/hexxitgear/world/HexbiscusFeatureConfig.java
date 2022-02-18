package sct.hexxitgear.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record HexbiscusFeatureConfig(IntProvider height) implements FeatureConfig {

	public static final Codec<HexbiscusFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			IntProvider.VALUE_CODEC.fieldOf("height").forGetter(HexbiscusFeatureConfig::height)
	).apply(instance, instance.stable(HexbiscusFeatureConfig::new)));
	
}
