package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class WallBlock extends BaseGameObject {

    public WallBlock() {
        this.setSize(new Size(1,1));
    }

    public static Character CHARACTER = '1';

    @Override
    public Character getCharacter() {
        return CHARACTER;
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.BLOCK;
    }
}
