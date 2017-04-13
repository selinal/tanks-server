package com.sbt.codeit.common;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.nio.file.Paths;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class WorldMap {

    private Table<Integer, Integer, Character> map;

    public WorldMap() {
        map = HashBasedTable.create(40, 40);
    }

    public WorldMap(String filename) {
        Paths.get();
        map.get(1,2).compareTo('>');
    }
}
