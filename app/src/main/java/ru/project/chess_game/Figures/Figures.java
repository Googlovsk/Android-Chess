package ru.project.chess_game.Figures;

import java.util.ArrayList;

import ru.project.chess_game.Location;
import ru.project.chess_game.Position;

public class Figures {
    private boolean white;
    Figures(boolean white){
        this.white = white;
    }
    public ArrayList<Location> possibleMoves(Location location, Position[][] board, boolean hasKingMoving, boolean Rook_0_Move , boolean Rook_7_Move, boolean isKingDanger){
        ArrayList<Location> possibleMoves = new ArrayList<>();
        Location loc;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                loc = new Location(i,j);
                possibleMoves.add(loc);
            }
        }
        return possibleMoves;
    }
    public boolean isWhite(){return white;}
}
