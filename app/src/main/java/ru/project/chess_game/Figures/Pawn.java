package ru.project.chess_game.Figures;

import java.util.ArrayList;

import ru.project.chess_game.Location;
import ru.project.chess_game.Position;

public class Pawn extends Figures {
    public Pawn(boolean white) {
        super(white);//вызываем конструктор родительского класса Figures для опредеоения поля white
    }
    //переопределение метода PossibleMoves из Figures
    @Override
    public ArrayList<Location> possibleMoves(Location location , Position[][] board , boolean hasKingMoved , boolean Rook_0_Move, boolean Rook_7_Move, boolean isKingInDanger){

        ArrayList<Location> possibleMoves = new ArrayList<>();
        possibleMoves.clear();
        Location loc;

        if(board[location.getX()][location.getY()].getFigures().isWhite()){

            if(location.getX()<8 && location.getX()>=0 && (location.getY()-1)<8 && (location.getY()-1)>=0){
                if(board[location.getX()][location.getY()-1].getFigures()==null){
                    loc=new Location(location.getX() , location.getY() - 1);
                    possibleMoves.add(loc);

                    if((location.getY() == 6) && (board[location.getX()][location.getY() - 2].getFigures() == null)){
                        loc = new Location(location.getX(), location.getY() - 2);
                        possibleMoves.add(loc);
                    }
                }
            }

            if((location.getX()+1)<8 && (location.getX()+1)>=0 && (location.getY()-1)<8 && (location.getY()-1)>=0) {
                if (board[location.getX() + 1][location.getY() - 1].getFigures() != null) {
                    if(board[location.getX() + 1][location.getY() - 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                        loc = new Location(location.getX() + 1, location.getY() - 1);
                        possibleMoves.add(loc);
                    }
                }

            }

            if((location.getX()-1)<8 && (location.getX()-1)>=0 && (location.getY()-1)<8 && (location.getY()-1)>=0) {
                if (board[location.getX() - 1][location.getY() - 1].getFigures() != null) {
                    if(board[location.getX() - 1][location.getY() - 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                        loc = new Location(location.getX() - 1, location.getY() - 1);
                        possibleMoves.add(loc);
                    }
                }
            }

        }else{

            if((location.getX())<8 && (location.getX())>=0 && (location.getY()+1)<8 && (location.getY()+1)>=0) {
                if (board[location.getX()][location.getY() + 1].getFigures() == null) {
                    loc = new Location(location.getX(), location.getY() + 1);
                    possibleMoves.add(loc);

                    if(location.getY() == 1 && (board[location.getX()][location.getY() + 2].getFigures() == null)){
                        loc = new Location(location.getX(), location.getY() + 2);
                        possibleMoves.add(loc);
                    }
                }
            }

            if((location.getX()+1)<8 && (location.getX()+1)>=0 && (location.getY()+1)<8 && (location.getY()+1)>=0) {
                if (board[location.getX() + 1][location.getY() + 1].getFigures() != null) {
                    if(board[location.getX() + 1][location.getY() + 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                        loc = new Location(location.getX() + 1, location.getY() + 1);
                        possibleMoves.add(loc);
                    }
                }
            }

            if((location.getX()-1)<8 && (location.getX()-1)>=0 && (location.getY()+1)<8 && (location.getY()+1)>=0) {
                if (board[location.getX() - 1][location.getY() + 1].getFigures() != null) {
                    if(board[location.getX() - 1][location.getY() + 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                        loc = new Location(location.getX() - 1, location.getY() + 1);
                        possibleMoves.add(loc);
                    }
                }
            }

        }
        return possibleMoves;//возвращаем список ходов для пешки
    }
}