package dev.punchcafe.netrunners.field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.punchcafe.netrunners.field.GraphConstructorUtils.assignDown;
import static dev.punchcafe.netrunners.field.GraphConstructorUtils.assignHorizontal;

public class Field<T> {

    FieldRow<T>[] rows;

    Map<Tile<T>, FieldPosition<Tile<T>>> tileFieldPositionCache = new HashMap<>();


    public Field(){
        rows = new FieldRow[9];
        for(int i = -4; i < 5; i++){
            int rowNumber = i+4;
            FieldRow<T> row = new FieldRow(5 - Math.abs(i));
            rows[rowNumber] = row;
        }

        for(int i = 0; i < rows.length; i++){
            // Assign tiles their graph relationships
            assignHorizontal(rows[i]);
            if(i < rows.length -1){
                assignDown(rows[i], rows[i+1]);
            }
        }
    }

    public int numberOfRows(){
        return rows.length;
    }

    public int getRowLength(int rowNumber){
        return rows[rowNumber].size();
    }

    public List<T> getRow(int row){
        return rows[row].getContentsOfRow();
    }

    public FieldPosition<T> getPositionOf(T obj){
        for(int i = 0; i < rows.length; i++){
            for(int j = 0; j < rows[i].size(); i++){
                if(obj == rows[i].getTile(i).getContents()){
                    return new FieldPosition<>(obj, i, j);
                }
            }
        }
        return null;
    }

    public FieldPosition<Tile<T>> getPositionOfTile(Tile<T> tile){
        if(tileFieldPositionCache.get(tile) != null){
            return tileFieldPositionCache.get(tile);
        }
        for(int i = 0; i < rows.length; i++){
            for(int j = 0; j < rows[i].size(); j++){
                if(tile == rows[i].getTile(j)){
                    FieldPosition<Tile<T>> fieldPosition = new FieldPosition<>(tile, i, j);
                    tileFieldPositionCache.put(tile, fieldPosition);
                    return fieldPosition;
                }
            }
        }
        return null;
    }

    public T getContents(int row, int index){
        return this.rows[row].getContentsOfTile(index);
    }

    public Tile<T> getTile(int row, int index){
        return this.rows[row].getTile(index);
    }

    public void setContents(T obj, int row, int index){
        this.rows[row].setInTile(obj, index);
    };
}
