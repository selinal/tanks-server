package com.sbt.codeit.common.exception;

import com.sbt.codeit.common.model.GameObject;

/**
 * Created by sbt-selin-an on 14.04.2017.
 */
public class ObjectDrawException extends Exception {

    GameObject gameObject;

    public ObjectDrawException(GameObject object) {
        gameObject = object;
    }

    @Override
    public String getMessage() {
        return "Can not write " + gameObject.toString() + " on map.";
    }
}
