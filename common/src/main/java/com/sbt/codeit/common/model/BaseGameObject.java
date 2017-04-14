package com.sbt.codeit.common.model;

import com.sbt.codeit.common.GeneratorHelper;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public abstract class BaseGameObject implements GameObject {

    private Size size = new Size(1, 1);
    private String name = "BaseGameObject";
    private int id = GeneratorHelper.INSTANCE.getId();

    @Override
    public int getId() {
        return id;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
