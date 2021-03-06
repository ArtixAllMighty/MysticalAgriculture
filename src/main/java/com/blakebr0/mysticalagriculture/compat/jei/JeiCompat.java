package com.blakebr0.mysticalagriculture.compat.jei;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.crafting.MysticalRecipeManager;
import com.blakebr0.mysticalagriculture.crafting.SpecialRecipeTypes;
import com.blakebr0.mysticalagriculture.item.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JeiCompat implements IModPlugin {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(
                new InfusionCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_ALTAR.get()), InfusionCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_PEDESTAL.get()), InfusionCategory.UID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        MysticalRecipeManager manager = MysticalRecipeManager.getInstance();

        registration.addRecipes(manager.getRecipes(SpecialRecipeTypes.INFUSION).values(), InfusionCategory.UID);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        ModItems.SOUL_JAR.ifPresent(jar ->
            registration.registerSubtypeInterpreter(jar, stack -> {
                IMobSoulType type = MobSoulUtils.getType(stack);
                return type != null ? type.getEntityIds().toString() : "";
            })
        );
    }
}
