package ru.project.chess_game.Figures;

import java.util.ArrayList;

import ru.project.chess_game.Location;
import ru.project.chess_game.Position;

public class Rook extends Figures{

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public ArrayList<Location> possibleMoves(Location location , Position[][] board , boolean hasKingMoved , boolean Rook_0_Move, boolean Rook_7_Move, boolean isKingInDanger){

        ArrayList<Location> PossibleMoves = new ArrayList<>();
        PossibleMoves.clear();
        Location loc ;

        //вправо
        for(int i=(location.getX()+1) ; i<8 ;i++){
            if(board[i][location.getY()].getFigures() == null){
                loc = new Location(i , location.getY());
                PossibleMoves.add(loc);
            }else{
                if(board[i][location.getY()].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(i , location.getY());
                    PossibleMoves.add(loc);
                }
                break;
            }
        }
        //влево
        for(int i=(location.getX()-1) ; i>=0 ; i--){
            if(board[i][location.getY()].getFigures() == null){
                loc = new Location(i , location.getY());
                PossibleMoves.add(loc);
            }else{
                if(board[i][location.getY()].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(i , location.getY());
                    PossibleMoves.add(loc);
                }
                break;
            }
        }
        //вверх
        for(int i=(location.getY()-1) ; i>=0 ; i--){
            if(board[location.getX()][i].getFigures() == null){
                loc = new Location( location.getX() , i);
                PossibleMoves.add(loc);
            }else{
                if(board[location.getX()][i].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location( location.getX() , i);
                    PossibleMoves.add(loc);
                }
                break;
            }
        }
        //вниз
        for(int i=(location.getY()+1) ; i<8 ;i++){
            if(board[location.getX()][i].getFigures() == null){
                loc = new Location(location.getX() , i);
                PossibleMoves.add(loc);
            }else{
                if(board[location.getX()][i].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite()){
                    loc = new Location(location.getX() , i);
                    PossibleMoves.add(loc);
                }
                break;
            }
        }
        return PossibleMoves;//возвращаем список ходов для ладьи
    }
}
