package ru.project.chess_game.Figures;

import java.util.ArrayList;

import ru.project.chess_game.Location;
import ru.project.chess_game.Position;

public class King extends Figures {

    public King(boolean white) {
        super(white);
    }

    @Override
    public ArrayList<Location> possibleMoves(Location location , Position[][] board , boolean hasKingMoved , boolean Rook_0_Move, boolean Rook_7_Move, boolean isKingInDanger){
        ArrayList<Location> PossibleMoves = new ArrayList<>();
        Location loc;

        if((location.getX() + 1) < 8 && (location.getY() + 1) < 8){
            if(board[location.getX() + 1][location.getY() + 1].getFigures() == null){
                loc = new Location(location.getX() + 1 , location.getY() + 1);
                PossibleMoves.add(loc);
            }else{
                if(board[location.getX()+1][location.getY()+1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(location.getX()+1 , location.getY()+1);
                    PossibleMoves.add(loc);
                }
            }
        }

        if((location.getY()+1)<8){
            if(board[location.getX()][location.getY()+1].getFigures() == null){
                loc = new Location(location.getX() , location.getY()+1);
                PossibleMoves.add(loc);
            }else{
                if(board[location.getX()][location.getY()+1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(location.getX() , location.getY()+1);
                    PossibleMoves.add(loc);
                }
            }
        }

        if((location.getX()-1) >= 0 && (location.getY()+1)<8){
            if(board[location.getX()-1][location.getY()+1].getFigures() == null){
                loc = new Location(location.getX()-1 , location.getY()+1);
                PossibleMoves.add(loc);
            }else{
                if(board[location.getX()-1][location.getY()+1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(location.getX()-1 , location.getY()+1);
                    PossibleMoves.add(loc);
                }
            }
        }

        if((location.getX()+1) < 8){
            if(board[location.getX() + 1][location.getY()].getFigures() == null){
                loc = new Location(location.getX() + 1 , location.getY());
                PossibleMoves.add(loc);
            }else{
                if(board[location.getX() + 1][location.getY()].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(location.getX() + 1 , location.getY());
                    PossibleMoves.add(loc);
                }
            }
        }

        if(location.getX() > 0){ // проверяем, что король не находится на краю доски
            if((location.getX() - 1) < 8){
                if(board[location.getX() - 1][location.getY()].getFigures() == null){
                    loc = new Location(location.getX() - 1 , location.getY());
                    PossibleMoves.add(loc);
                }else{
                    if(board[location.getX()-1][location.getY()].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                        loc = new Location(location.getX()-1 , location.getY());
                        PossibleMoves.add(loc);
                    }
                }
            }
        }
        if((location.getX()+1) < 8 && (location.getY()-1)>=0){
            if(board[location.getX()+1][location.getY()-1].getFigures() == null){
                loc = new Location(location.getX()+1 , location.getY()-1);
                PossibleMoves.add(loc);
            }else{
                if(board[location.getX()+1][location.getY()-1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(location.getX()+1 , location.getY()-1);
                    PossibleMoves.add(loc);
                }
            }
        }

        if((location.getY()-1)>=0){
            if(board[location.getX()][location.getY()-1].getFigures() == null){
                loc = new Location(location.getX() , location.getY()-1);
                PossibleMoves.add(loc);
            }else{
                if(board[location.getX()][location.getY()-1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(location.getX() , location.getY()-1);
                    PossibleMoves.add(loc);
                }
            }
        }

        if (location.getX() > 0 && location.getY() > 0){//тут проверка на выход из массива
            if((location.getX()-1) <8 && (location.getY()-1)>=0) {
                if (board[location.getX() - 1][location.getY() - 1].getFigures() == null) {
                    loc = new Location(location.getX() - 1, location.getY() - 1);
                    PossibleMoves.add(loc);
                } else {
                    if (board[location.getX() - 1][location.getY() - 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()) {
                        loc = new Location(location.getX() - 1, location.getY() - 1);
                        PossibleMoves.add(loc);
                    }
                }
            }
        }
        if(!hasKingMoved && !isKingInDanger) {
            if (board[location.getX()][location.getY()].getFigures().isWhite()) {

                if ((location.getX() == 4) && (location.getY() == 7) && (board[5][7].getFigures() == null) && board[6][7].getFigures() == null) {
                    if(!Rook_7_Move) {
                        loc = new Location(location.getX() + 2, location.getY());
                        PossibleMoves.add(loc);
                    }
                }

                if ((location.getX() == 4) && (location.getY() == 7) && (board[3][7].getFigures() == null) && board[2][7].getFigures() == null && board[1][7].getFigures() == null) {
                    if(!Rook_0_Move) {
                        loc = new Location(location.getX() - 2, location.getY());
                        PossibleMoves.add(loc);
                    }
                }

            } else {

                if ((location.getX() == 4) && (location.getY() == 0) && (board[5][0].getFigures() == null) && board[6][0].getFigures() == null) {
                    if(!Rook_7_Move) {
                        loc = new Location(location.getX() + 2, location.getY());
                        PossibleMoves.add(loc);
                    }
                }

                if ((location.getX() == 4) && (location.getY() == 0) && (board[3][0].getFigures() == null) && board[2][0].getFigures() == null && board[1][0].getFigures() == null) {
                    if(!Rook_0_Move) {
                        loc = new Location(location.getX() - 2, location.getY());
                        PossibleMoves.add(loc);
                    }
                }

            }
        }


        return PossibleMoves;//возвращаем список ходов для короля
    }
}