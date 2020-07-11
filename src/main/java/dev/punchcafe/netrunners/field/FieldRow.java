package dev.punchcafe.netrunners.field;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FieldRow<T> {
    private final Tile<T>[] tiles;

    public FieldRow(int size){

        tiles = new Tile[size];
        for(int i = 0; i < tiles.length; i++){
            tiles[i] = new Tile();
        }
    }

    public int size(){
        return tiles.length;
    }

    public Tile<T> getTile(int index){
        return tiles[index];
    }

    public List<T> getContentsOfRow(){
        return Arrays.stream(tiles).map(Tile::getContents).collect(Collectors.toList());
    }

    public T getContentsOfTile(int index) {
        return tiles[index].getContents();
    }

    public void setInTile(T obj, int index){
        if(index >= tiles.length){
            throw new RuntimeException();
        }
        tiles[index].setContents(obj);
    }
}
