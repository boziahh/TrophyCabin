package com.booziii.TrophyCabin.HuntingTable;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record Quest(
                ResourceLocation targetId,
                int goal,
                ResourceLocation costItem,
                int costCount,
                ResourceLocation rewardItem,
                int rewardCount) {

        public static final List<Quest> ALL = List.of(
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "bison"),
                                        25,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "spruce_sign"),
                                        1,
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "bison_trophy"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "deer"),
                                        25,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "spruce_sign"),
                                        1,
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "deer_trophy"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "red_wolf"),
                                        25,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "spruce_sign"),
                                        1,
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "red_wolf_trophy"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("primal", "bear"),
                                        25,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "brown_carpet"),
                                        8,
                                        ResourceLocation.fromNamespaceAndPath("trophycabin", "brown_bear_rug"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("primal", "bear"),
                                        25,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "black_carpet"),
                                        8,
                                        ResourceLocation.fromNamespaceAndPath("trophycabin", "black_bear_rug"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("primal", "bear"),
                                        25,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "white_carpet"),
                                        8,
                                        ResourceLocation.fromNamespaceAndPath("trophycabin", "white_bear_rug"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "cod"),
                                        25,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "spruce_sign"),
                                        1,
                                        ResourceLocation.fromNamespaceAndPath("trophycabin", "cod_trophy"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "salmon"),
                                        25,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "spruce_sign"),
                                        1,
                                        ResourceLocation.fromNamespaceAndPath("trophycabin", "salmon_trophy"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "fox"),
                                        10,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "leather"),
                                        5,
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "fur_cloak"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("primal", "shark"),
                                        10,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "iron_ingot"),
                                        4,
                                        ResourceLocation.fromNamespaceAndPath("trophycabin", "goose_statue"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "rabbit"),
                                        100,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "white_banner"),
                                        1,
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "bunny_stalker_banner"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "cod"),
                                        100,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "light_blue_banner"),
                                        1,
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "cod_catcher_banner"),
                                        1),
                        new Quest(
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "wolf"),
                                        100,
                                        ResourceLocation.fromNamespaceAndPath("minecraft", "yellow_banner"),
                                        1,
                                        ResourceLocation.fromNamespaceAndPath("wildernature", "wolf_trapper_banner"),
                                        1)

        );
}
