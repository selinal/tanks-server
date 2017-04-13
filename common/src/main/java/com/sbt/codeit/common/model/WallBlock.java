package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class WallBlock extends BaseGameObject {

    public WallBlock() {
        this.size.width = 1;
        this.size.height = 1;
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.BLOCK;
    }
}
