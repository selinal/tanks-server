package com.sbt.codeit.common.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Wall extends BaseGameObject {

    private List<GameObject> wallList = new ArrayList<>(9);

    public Wall(int width, int height) {
        this.size.width = width;
        this.size.height = height;
        for (int i = 0; i < this.size.width + this.size.height; i++) {
            wallList.add(new WallBlock());
        }
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.WALL;
    }

    public void swapBlock(GameObject blockForSwap, GameObject block){

    }
}
