package sct.hexxitgear.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import sct.hexxitgear.HexxitGear;

@Mixin(ArmorItem.class)
public abstract class ThiefArmorSpeedMixin {

	@Final @Shadow
	private static UUID[] MODIFIERS;
	
	@Final @Shadow @Mutable
	private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
	
	
	@Inject(at = @At(value = "RETURN"), method = "<init>")
	private void init(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings, CallbackInfo info) {
		if(material != HexxitGear.THIEF_ARMOR) {
			return;
		}
		
		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		
		// Copy old attribute modifiers
		attributeModifiers.forEach(builder::put);
		
		builder.put(
				EntityAttributes.GENERIC_MOVEMENT_SPEED,
				new EntityAttributeModifier(
						"Thief Armor Speed", 
						.005F, 
						EntityAttributeModifier.Operation.ADDITION
				)
		);
		
		attributeModifiers = builder.build();
	}
	
}
