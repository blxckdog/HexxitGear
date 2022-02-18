/*
 * HexxitGear
 * Copyright (C) 2013  Ryan Cohen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package sct.hexxitgear;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import sct.hexxitgear.item.ScaleHelmet;
import sct.hexxitgear.item.ThiefHood;
import sct.hexxitgear.item.TribalSkull;
import sct.hexxitgear.material.ScaleArmorMaterial;
import sct.hexxitgear.material.ThiefArmorMaterial;
import sct.hexxitgear.material.TribalArmorMaterial;
import sct.hexxitgear.world.HexbiscusFeature;
import sct.hexxitgear.world.HexbiscusFeatureConfig;

@SuppressWarnings("deprecation")
public class HexxitGear implements ModInitializer {

	public static final String MODID = "hexxitgear";
	public static final String MODNAME = "Hexxit Gear";
	public static final String VERSION = "3.0.0";
	
	public static final ItemGroup ITEM_GROUP = createCreativeTab();
	
	public static final Block HEXBISCUS_FLOWER = createHexbiscusBlock();
	public static final Feature<HexbiscusFeatureConfig> HEXBISCUS_FEATURE = new HexbiscusFeature(HexbiscusFeatureConfig.CODEC);
	public static final ConfiguredFeature<?,?> HEXBISCUS_CONFIG = HEXBISCUS_FEATURE.configure(new HexbiscusFeatureConfig(ConstantIntProvider.create(10)));
	
	public static final ArmorMaterial TRIBAL_ARMOR = new TribalArmorMaterial();
	public static final ArmorMaterial THIEF_ARMOR = new ThiefArmorMaterial();
	public static final ArmorMaterial SCALE_ARMOR = new ScaleArmorMaterial();
	
	public static final Item TRIBAL_HELMET = new TribalSkull(TRIBAL_ARMOR, EquipmentSlot.HEAD);
	public static final Item TRIBAL_CHEST = createItem(TRIBAL_ARMOR, EquipmentSlot.CHEST);
	public static final Item TRIBAL_LEGS = createItem(TRIBAL_ARMOR, EquipmentSlot.LEGS);
	public static final Item TRIBAL_BOOTS = createItem(TRIBAL_ARMOR, EquipmentSlot.FEET);
	
	public static final Item THIEF_HELMET = new ThiefHood(THIEF_ARMOR, EquipmentSlot.HEAD);
	public static final Item THIEF_CHEST = createItem(THIEF_ARMOR, EquipmentSlot.CHEST);
	public static final Item THIEF_LEGS = createItem(THIEF_ARMOR, EquipmentSlot.LEGS);
	public static final Item THIEF_BOOTS = createItem(THIEF_ARMOR, EquipmentSlot.FEET);
	
	public static final Item SCALE_HELMET = new ScaleHelmet(SCALE_ARMOR, EquipmentSlot.HEAD);
	public static final Item SCALE_CHEST = createItem(SCALE_ARMOR, EquipmentSlot.CHEST);
	public static final Item SCALE_LEGS = createItem(SCALE_ARMOR, EquipmentSlot.LEGS);
	public static final Item SCALE_BOOTS = createItem(SCALE_ARMOR, EquipmentSlot.FEET);
	
	public static final Item HEXICAL_DIAMOND = new Item(new Item.Settings().group(ITEM_GROUP));
	public static final Item HEXICAL_ESSENCE = new Item(new Item.Settings().group(ITEM_GROUP));
	
	
	private static Item createItem(ArmorMaterial material, EquipmentSlot slot) {
		return new ArmorItem(material, slot, new Item.Settings().group(ITEM_GROUP));
	}
	
	private static ItemGroup createCreativeTab() {
		return FabricItemGroupBuilder.create(
				new Identifier("hexxitgear", "item_group"))
				.icon(() -> new ItemStack(TRIBAL_HELMET))
				.build();
	}
	
	
	private static Block createHexbiscusBlock() {
		return new FlowerBlock(
				StatusEffects.POISON, 
				7, 
				FabricBlockSettings.of(Material.PLANT)
						.sounds(BlockSoundGroup.GRASS)
						.breakInstantly()
						.noCollision()
						.nonOpaque()
						.build()
				);
	}
	

	@Override
	public void onInitialize() {
		// Register all armors and items
		Registry.register(Registry.ITEM, new Identifier(MODID, "tribal_helmet"), TRIBAL_HELMET);
		Registry.register(Registry.ITEM, new Identifier(MODID, "tribal_chest"), TRIBAL_CHEST);
		Registry.register(Registry.ITEM, new Identifier(MODID, "tribal_legs"), TRIBAL_LEGS);
		Registry.register(Registry.ITEM, new Identifier(MODID, "tribal_boots"), TRIBAL_BOOTS);
		
		Registry.register(Registry.ITEM, new Identifier(MODID, "thief_helmet"), THIEF_HELMET);
		Registry.register(Registry.ITEM, new Identifier(MODID, "thief_chest"), THIEF_CHEST);
		Registry.register(Registry.ITEM, new Identifier(MODID, "thief_legs"), THIEF_LEGS);
		Registry.register(Registry.ITEM, new Identifier(MODID, "thief_boots"), THIEF_BOOTS);
		
		Registry.register(Registry.ITEM, new Identifier(MODID, "scale_helmet"), SCALE_HELMET);
		Registry.register(Registry.ITEM, new Identifier(MODID, "scale_chest"), SCALE_CHEST);
		Registry.register(Registry.ITEM, new Identifier(MODID, "scale_legs"), SCALE_LEGS);
		Registry.register(Registry.ITEM, new Identifier(MODID, "scale_boots"), SCALE_BOOTS);
		
		Registry.register(Registry.ITEM, new Identifier(MODID, "hexical_diamond"), HEXICAL_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier(MODID, "hexical_essence"), HEXICAL_ESSENCE);
		
		Registry.register(Registry.BLOCK, new Identifier(MODID, "hexbiscus"), HEXBISCUS_FLOWER);
		Registry.register(Registry.ITEM, new Identifier(MODID, "hexbiscus"), new BlockItem(HEXBISCUS_FLOWER, new Item.Settings().group(ITEM_GROUP)));

		// Register Hexbiscus block and world generation feature
		RegistryKey<ConfiguredFeature<?,?>> hexbiscusKey = RegistryKey.of(
				Registry.CONFIGURED_FEATURE_KEY, 
				new Identifier(MODID, "hexbiscus_gen")
		);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, hexbiscusKey.getValue(), HEXBISCUS_CONFIG);
		
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, hexbiscusKey);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), HexxitGear.HEXBISCUS_FLOWER);
	}
	
}
