package ru.ItzDarth.DarthCore.utils.gson;

import com.google.gson.GsonBuilder;

@FunctionalInterface
public interface GsonBuilderModifiers {
    public GsonBuilder modify(GsonBuilder builder);
}