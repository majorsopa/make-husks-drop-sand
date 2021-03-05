package net.husksthatdropsand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.util.Identifier;

public class GetFarmedSand implements ModInitializer {

    private static final Identifier HUSK_LOOT_TABLE_ID = new Identifier("minecraft", "entities/husk");


    @Override
    public void onInitialize() {
        modifyLootTables();
    }


    private void modifyLootTables() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
            if (HUSK_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                        .with(ItemEntry.builder(Items.SAND))
                        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(-2.0f, 4.0f)).build());

                supplier.withPool(poolBuilder.build());
            }
        }));
    }
}
