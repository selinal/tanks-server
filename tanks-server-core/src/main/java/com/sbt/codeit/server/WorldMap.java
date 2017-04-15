package com.sbt.codeit.server;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.sbt.codeit.server.model.Space;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class WorldMap implements Table<Integer, Integer, Character> {

    private Table<Integer, Integer, Character> table;
    private int rows ;
    private int cols ;

    public WorldMap() {
        this.table = HashBasedTable.create();
    }

    public WorldMap(int rows, int cols) {
        this.table = HashBasedTable.create(rows, cols);
        this.rows=rows;
        this.cols=cols;
        cleanTheWorld();
    }

    public void cleanTheWorld(){
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                table.put(r, c, Space.CHARACTER);
            }
        }
    }

    public int getMapWidth() {
        return cols;
    }

    public int getMapHeight() {
        return rows;
    }

    @Override
    public boolean contains(Object rowKey, Object columnKey) {
        return table.contains(rowKey, columnKey);
    }

    @Override
    public boolean containsRow(Object rowKey) {
        return table.containsRow(rowKey);
    }

    @Override
    public boolean containsColumn(Object columnKey) {
        return table.containsColumn(columnKey);
    }

    @Override
    public boolean containsValue(Object value) {
        return table.containsValue(value);
    }

    @Override
    public Character get(Object rowKey, Object columnKey) {
        return table.get(rowKey, columnKey);
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public void clear() {
        table.clear();
    }

    @Override
    public Character put(Integer rowKey, Integer columnKey, Character value) {
        return table.put(rowKey, columnKey, value);
    }

    @Override
    public void putAll(Table table) {
        table.putAll(table);
    }

    @Override
    public Character remove(Object rowKey, Object columnKey) {
        return table.remove(rowKey, columnKey);
    }

    @Override
    public Map<Integer, Character> row(Integer rowKey) {
        return table.row(rowKey);
    }

    @Override
    public Map<Integer, Character> column(Integer columnKey) {
        return table.column(columnKey);
    }

    @Override
    public Set<Cell<Integer, Integer, Character>> cellSet() {
        return table.cellSet();
    }

    @Override
    public Set<Integer> rowKeySet() {
        return table.rowKeySet();
    }

    @Override
    public Set<Integer> columnKeySet() {
        return table.columnKeySet();
    }

    @Override
    public Collection<Character> values() {
        return table.values();
    }

    @Override
    public Map<Integer, Map<Integer, Character>> rowMap() {
        return table.rowMap();
    }

    @Override
    public Map<Integer, Map<Integer, Character>> columnMap() {
        return table.columnMap();
    }

    public void printToStream(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream, true);
        for (int r = 0; r < getMapHeight(); r++) {
            writer.println(table.row(r).values().toString());
        }
    }
}
