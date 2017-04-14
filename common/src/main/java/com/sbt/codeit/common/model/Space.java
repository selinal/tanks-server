package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Space extends BaseGameObject {
    public static final Character CHARACTER = '0';

    @Override
    public GameObjectType getType() {
        return GameObjectType.SPACE;
    }

    @Override
    public Character getCharacter() {
        return CHARACTER;
    }
}
