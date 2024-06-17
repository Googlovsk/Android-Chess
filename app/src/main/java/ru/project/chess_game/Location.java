package ru.project.chess_game;

public class Location {
    private int x;
    private int y;
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }
    void setX (int x){
        this.x = x;
    }
    void setY (int y){
        this.y = y;
    }
    public int getX(){return x;}
    public int getY(){return y;}
}
