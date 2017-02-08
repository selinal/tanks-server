package com.sbt.codeit.core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by sbt-galimov-rr on 08.02.2017.
 */
public class MapLoader {

    private static ArrayList<ArrayList<Character>> map = new ArrayList<>();

    public synchronized static ArrayList<ArrayList<Character>> getMap() {
        if(map.isEmpty()) {
            FileHandle mapFile = Gdx.files.internal("map.txt");
            new BufferedReader(mapFile.reader()).lines().forEach(line ->
                    map.add(line.chars().mapToObj(ch -> (char) ch).collect(Collectors.toCollection(ArrayList::new))));
        }
        return map;
    }

}
