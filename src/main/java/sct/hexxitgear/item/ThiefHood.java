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

package sct.hexxitgear.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPart.Cuboid;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.mixin.LivingEntityMixin;
import sct.hexxitgear.render.HexxitGearHelmetModel;

public class ThiefHood extends ArmorItem implements HexxitGearHelmetModel {

	private final Identifier texture;
	
	private final ModelPart hood;
	/*
	private final ModelPart mask;
	
	private final ModelPart fold1;
	private final ModelPart fold2;
	private final ModelPart fold3;*/
	
	
	public ThiefHood(ArmorMaterial material, EquipmentSlot slot) {
		super(material, slot, new Item.Settings().group(HexxitGear.ITEM_GROUP));
		texture = new Identifier("hexxitgear", "textures/maps/hood_helmet.png");
		
		List<Cuboid> cuboids = new ArrayList<>();
		
		cuboids.add(new Cuboid(0, 0, -4F, -7.5F, -4F, 8, 8, 8, 0, 0, 0, false, 64, 64));
		
		// Folds
		cuboids.add(new Cuboid(48, 0, -3.5F, -8.8F, 5F, 7, 5, 1, 0, 0, 0, false, 64, 64));
		cuboids.add(new Cuboid(48, 6, -3F, -8.6F, 6F, 6, 3, 1, 0, 0, 0, false, 64, 64));
		cuboids.add(new Cuboid(48, 11, -1.333333F, -8.5F, 7F, 3, 1, 1, 0, 0, 0, false, 64, 64));
		
		// Mask
		cuboids.add(new Cuboid(0, 33, -5F, -9F, -5F, 10, 9, 10, 0, 0, 0, false, 64, 64));
		
		hood = new ModelPart(cuboids, new HashMap<>());
		
		/*
		hood = new ModelPart(64, 64, 0, 0);
		hood.addCuboid(-4F, -7.5F, -4F, 8, 8, 8);
		
		fold1 = new ModelPart(64, 64, 48, 0);
		fold1.addCuboid(-3.5F, -8.8F, 5F, 7, 5, 1);
		
		fold2 = new ModelPart(64, 64, 48, 6);
		fold2.addCuboid(-3F, -8.6F, 6F, 6, 3, 1);
		
		fold3 = new ModelPart(64, 64, 48, 11);
		fold3.addCuboid(-1.333333F, -8.5F, 7F, 3, 1, 1);
		
		mask = new ModelPart(64, 64, 0, 33);
		mask.addCuboid(-5F, -9F, -5F, 10, 9, 10);

		hood.addChild(mask);
		hood.addChild(fold1);
		hood.addChild(fold2);
		hood.addChild(fold3);*/
	}
	
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!(entity instanceof PlayerEntity)) {
			return;
		}
		
		PlayerEntity player = (PlayerEntity) entity;
		
		Item head = player.getEquippedStack(EquipmentSlot.HEAD).getItem();
		Item chest = player.getEquippedStack(EquipmentSlot.CHEST).getItem();
		Item legs = player.getEquippedStack(EquipmentSlot.LEGS).getItem();
		Item feet = player.getEquippedStack(EquipmentSlot.FEET).getItem();
		
		if(head == HexxitGear.THIEF_HELMET && chest == HexxitGear.THIEF_CHEST && legs == HexxitGear.THIEF_LEGS && feet == HexxitGear.THIEF_BOOTS) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 0, false, false, true));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0, false, false, true));
		}
	}
	

	@Override
	public <T extends LivingEntity, A extends BipedEntityModel<T>> void renderHelmet(MatrixStack matrices, VertexConsumerProvider vertexConsumer, T entity, int light, A model) {
		model.getHead().rotate(matrices);
		matrices.scale(1.05F, 1.05F, 1.05F);
		
		VertexConsumer vertex = vertexConsumer.getBuffer(model.getLayer(texture));
		hood.render(matrices, vertex, light, OverlayTexture.DEFAULT_UV, 1F, 1F, 1F, 1F);
		
		matrices.scale(1F, 1F, 1F);
	}

}
