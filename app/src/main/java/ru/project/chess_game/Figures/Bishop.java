package ru.project.chess_game.Figures;

import java.util.ArrayList;

import ru.project.chess_game.Location;
import ru.project.chess_game.Position;

public class Bishop extends Figures{

    public Bishop(boolean white) {
        super(white);//вызываем конструктор родительского класса Figures для опредеоения поля white
    }

    //переопределение метода PossibleMoves из Figures
    @Override
    public ArrayList<Location> possibleMoves(Location location , Position[][] board , boolean hasKingMoved , boolean Rook_0_Move, boolean Rook_7_Move, boolean isKingInDanger){
        ArrayList<Location> possibleMoves = new ArrayList<>();
        Location loc;


        //вправо вверх
        for(int i = 1; i < 8; i++){
            if((location.getX() +i ) < 8 && (location.getY() + i) < 8){
                if(board[location.getX() + i][location.getY() + i].getFigures() == null)/*если на пути нет других фигур*/{
                    loc = new Location(location.getX() + i, location.getY() + i);
                    possibleMoves.add(loc);//добавляем в список
                } else{
                    if(board[location.getX() + i][location.getY() + i].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                        loc = new Location(location.getX() + i, location.getY() + i);
                        possibleMoves.add(loc);//добавляем в список
                    }
                    break;
                }
            }
        }

        //влево вверх
        for(int i = 1; i < 8; i++){
            if((location.getX() - i) >= 0 && (location.getY() + i) < 8){
                if(board[location.getX() - i][location.getY() + i].getFigures() == null)/*если на пути нет других фигур*/{
                    loc = new Location(location.getX() - i, location.getY() + i);
                    possibleMoves.add(loc);//добавляем в список
                } else{
                    if(board[location.getX() - i][location.getY() + i].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                        loc = new Location(location.getX() - i, location.getY() + i);
                        possibleMoves.add(loc);//добавляем в список
                    }
                    break;
                }

            }
        }

        //влево вниз
        for(int i = 1; i < 8; i++){
            if((location.getX() - i) >= 0 && (location.getY() - i) >= 0){
                if(board[location.getX() - i][location.getY() - i].getFigures() == null)/*если на пути нет других фигур*/{
                    loc = new Location(location.getX() - i, location.getY() - i);
                    possibleMoves.add(loc);//добавляем в список
                } else{
                    if(board[location.getX() - i][location.getY() - i].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                        loc = new Location(location.getX() - i, location.getY()-  i);
                        possibleMoves.add(loc);//добавляем в список
                    }
                    break;
                }

            }
        }

        //вправо вниз
        for(int i = 1; i < 8; i++){
            if((location.getX() + i) < 8 && (location.getY() - i) >= 0){
                if(board[location.getX() + i][location.getY() - i].getFigures() == null)/*если на пути нет других фигур*/{
                    loc = new Location(location.getX() + i, location.getY() - i);
                    possibleMoves.add(loc);//добавляем в список
                } else{
                    if(board[location.getX()+i][location.getY()-i].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                        loc = new Location(location.getX() + i, location.getY() - i);
                        possibleMoves.add(loc);//добавляем в список
                    }
                    break;
                }

            }
        }


        return possibleMoves;//возвращаем список ходов для слона
    }
}