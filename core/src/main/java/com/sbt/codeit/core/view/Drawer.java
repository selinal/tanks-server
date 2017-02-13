package com.sbt.codeit.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sbt.codeit.core.model.Bullet;
import com.sbt.codeit.core.model.Tank;
import com.sbt.codeit.core.model.World;
import com.sbt.codeit.core.util.FieldHelper;

import java.util.ArrayList;

/**
 * Created by sbt-galimov-rr on 08.02.2017.
 */
public class Drawer {

    private World world;
    private Texture wall;
    private Texture bulletTexture;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private float cellSize;
    private final TextureRegion[][] tanks;

    public Drawer(World world) {
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        wall = new Texture(Gdx.files.internal("brick.jpg"));
        bulletTexture = new Texture(Gdx.files.internal("bullet.png"));
        tanks = TextureRegion.split(new Texture(Gdx.files.internal("tanks.png")), 60, 80);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        cellSize = Gdx.graphics.getHeight() / (float) FieldHelper.FIELD_HEIGHT;
    }

    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawMap();
        drawTanks();
        batch.end();
        drawInfoPanel();
    }

    private void drawInfoPanel() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        float rightEdgeOfField = FieldHelper.FIELD_WIDTH * cellSize;
        shapeRenderer.rect(rightEdgeOfField, 0, Gdx.graphics.getWidth() - rightEdgeOfField, Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    private void drawMap() {
        ArrayList<ArrayList<Character>> map = world.getField();
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                if (map.get(y).get(x).equals('#')) {
                    batch.draw(wall, x * cellSize, y * cellSize, cellSize, cellSize, 0, 0,
                            wall.getWidth() / Tank.SIZE, wall.getHeight() / Tank.SIZE, false, false);
                }
            }
        }
    }

    private void drawTanks() {
        for (Tank tank : world.getTanks()) {
            batch.draw(tanks[tank.getColor()][tank.getModel()], tank.getX() * cellSize, tank.getY() * cellSize, cellSize * Tank.SIZE / 2, cellSize * Tank.SIZE / 2,
                    cellSize * Tank.SIZE, cellSize * Tank.SIZE, 1, 1, tank.getDirection().toRotation());
            drawBullets(tank);
        }
    }

    private void drawBullets(Tank tank) {
        tank.getBullets().stream().filter(Bullet::isAvailable).forEach(bullet ->
                batch.draw(bulletTexture, bullet.getX() * cellSize, bullet.getY() * cellSize, cellSize / 2, cellSize / 2, cellSize, cellSize, 1, 1,
                        bullet.getDirection().toRotation(), 0, 0, bulletTexture.getWidth(), bulletTexture.getHeight(), false, false)
        );
    }

}
