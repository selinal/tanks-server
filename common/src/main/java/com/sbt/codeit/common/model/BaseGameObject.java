package com.sbt.codeit.common.model;

import com.sbt.codeit.common.IdGenerator;

import java.util.Random;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public abstract class BaseGameObject implements GameObject {

    protected Size size = new Size(1, 1);
    protected String name = "BaseGameObject";
    private int id = IdGenerator.INSTANCE.getId();

    @Override
    public int getId() {
        return id;
    }

}
