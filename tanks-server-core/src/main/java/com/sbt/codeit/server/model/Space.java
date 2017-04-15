package com.sbt.codeit.server.model;

import com.sbt.codeit.common.model.GameObjectType;

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
