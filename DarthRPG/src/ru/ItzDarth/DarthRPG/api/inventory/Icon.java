package ru.ItzDarth.DarthRPG.api.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Icon {

    public final ItemStack itemStack;

    public final List<ClickAction> clickActions = new ArrayList<>();

    public Icon(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Icon addClickAction(ClickAction clickAction) {
        this.clickActions.add(clickAction);
        return this;
    }

    public List<ClickAction> getClickActions() {
        return this.clickActions;
    }
}