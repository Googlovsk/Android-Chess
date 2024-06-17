package ru.project.chess_game;

import ru.project.chess_game.Figures.Figures;
public class Position {
    private Figures figures;
    Position(Figures figures){
        this.figures = figures;
    }
    public Figures getFigures(){
        return figures;
    }
    void setFigures(Figures figures){
        this.figures = figures;
    }
}
