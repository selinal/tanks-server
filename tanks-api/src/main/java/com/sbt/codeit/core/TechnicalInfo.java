package com.sbt.codeit.core;

/**
 * Created by sbt-selin-an on 15.04.2017.
 */
public enum TechnicalInfo {

    REGISTRATION,
    REGISTRATION_OK,
    REGISTRATION_FAIL;

    @Override
    public String toString() {
        return this + "=";
    }

    public static TechnicalInfo parseString(String string) {
        return valueOf(string.substring(0, string.indexOf("=")));
    }
}
