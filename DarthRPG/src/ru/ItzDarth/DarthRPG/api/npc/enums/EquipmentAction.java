package ru.ItzDarth.DarthRPG.api.npc.enums;

public enum EquipmentAction {

    HAND(0),
    BOOTS(1),
    LEGGINGS(2),
    CHESTPLATE(3),
    HELMET(4);

    private Integer value;

    EquipmentAction(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
