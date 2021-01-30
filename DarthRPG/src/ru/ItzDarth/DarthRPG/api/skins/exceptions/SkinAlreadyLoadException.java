package ru.ItzDarth.DarthRPG.api.skins.exceptions;

@SuppressWarnings("serial")
public class SkinAlreadyLoadException extends Exception {
    public SkinAlreadyLoadException(String message) {
        super(message);
    }
}
