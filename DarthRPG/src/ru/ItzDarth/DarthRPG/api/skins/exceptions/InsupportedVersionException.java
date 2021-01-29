package ru.ItzDarth.DarthRPG.api.skins.exceptions;

@SuppressWarnings("serial")
public class InsupportedVersionException extends Exception {

    public InsupportedVersionException(String message){
        super(message);
    }
}
