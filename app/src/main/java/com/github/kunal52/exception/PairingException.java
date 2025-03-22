package com.github.kunal52.exception;

public class PairingException extends Exception {
    public PairingException(String str) {
        super(str);
    }

    public PairingException(String str, Exception exc) {
        super(str, exc);
    }
}
