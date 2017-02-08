package com.sbt.codeit.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

import static com.sbt.codeit.core.util.MapLoader.getMap;

/**
 * Created by sbt-galimov-rr on 08.02.2017.
 */
public class Drawer {

    private Texture texture;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private int cellSize;

    public Drawer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        texture = new Texture(Gdx.files.internal("brick.jpg"));
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        cellSize = Gdx.graphics.getHeight() / getMap().size();
    }

    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawMap();
        batch.end();
        drawInfoPanel();
    }

    private void drawInfoPanel() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        int rightEdgeOfField = getMap().get(0).size() * cellSize;
        shapeRenderer.rect(rightEdgeOfField, 0, Gdx.graphics.getWidth() - rightEdgeOfField, Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    private void drawMap() {
        ArrayList<ArrayList<Character>> map = getMap();
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                if(map.get(y).get(x).equals('#')) {
                    batch.draw(texture, x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

}
