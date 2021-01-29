package ru.ItzDarth.DarthRPG.api.skins.exceptions;

@SuppressWarnings("serial")
public class InvalidMojangPlayerException extends Exception {
    public InvalidMojangPlayerException(String message) {
        super(message);
    }
}
