package ru.ItzDarth.DarthRPG.api.skins.skins;

public class Skin {

    private String value;
    private String signature;

    public Skin(String value, String signature) {
        this.value = value;
        this.signature = signature;
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }
}
