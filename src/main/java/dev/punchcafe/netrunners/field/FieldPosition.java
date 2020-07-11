package dev.punchcafe.netrunners.field;

public class FieldPosition<T> {

    public static <T, C> FieldPosition<C> convertFieldPosition(FieldPosition<T> originalPosition, C newContent){
        return new FieldPosition<>(newContent, originalPosition.getRow(), originalPosition.getIndex());
    }

    T content;
    int row;
    int index;

    public FieldPosition(T content, int row, int index){
        this.content = content;
        this.row = row;
        this.index = index;
    }

    public int getRow(){
        return this.row;
    }

    public int getIndex(){
        return this.index;
    }

    public T getContent(){
        return this.content;
    }
}
