package com.sbt.codeit.server.model;


import com.sbt.codeit.common.model.GameObject;
import com.sbt.codeit.common.model.GameObjectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Wall extends BaseGameObject {

    private List<GameObject> wallList = new ArrayList<>(9);

    public Wall(int width, int height) {
//        this.size.x_size = x_size;
//        this.size.y_size = y_size;
//        for (int i = 0; i < this.size.x_size + this.size.y_size; i++) {
//            wallList.add(new WallBlock());
//        }
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.WALL;
    }

    @Override
    public Character getCharacter() {
        return null;
    }

    public void swapBlock(GameObject blockForSwap, GameObject block){

    }
}
