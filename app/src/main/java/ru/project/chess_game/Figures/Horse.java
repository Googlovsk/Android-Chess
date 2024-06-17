package ru.project.chess_game.Figures;

import java.util.ArrayList;

import ru.project.chess_game.Location;
import ru.project.chess_game.Position;

public class Horse extends Figures{

    public Horse(boolean white) {
        super(white);//вызываем конструктор родительского класса Figures для опредеоения поля white
    }

    //переопределение метода PossibleMoves из Figures
    @Override
    public ArrayList<Location> possibleMoves(Location location , Position[][] board , boolean hasKingMoved , boolean Rook_0_Move, boolean Rook_7_Move, boolean isKingInDanger){
        ArrayList<Location> possibleMoves = new ArrayList<>();
        possibleMoves.clear();
        Location loc;


        //на две клетки вправо и одну вниз
        if (location.getX() + 2 < 8 && location.getY() - 1 >= 0){
            if(board[location.getX() + 2][location.getY() - 1].getFigures() == null)/*если на пути нет других фигур*/{
                loc = new Location(location.getX() + 2, location.getY() - 1);
                possibleMoves.add(loc);//добавляем в список
            } else{
                if(board[location.getX() + 2][location.getY() - 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                    loc = new Location(location.getX() + 2, location.getY() - 1);
                    possibleMoves.add(loc);//добавляем в список
                }
            }
        }

        //на одну клетку вправо и две вниз
        if (location.getX() + 1 < 8 && location.getY() - 2 >= 0){
            if(board[location.getX() + 1][location.getY() - 2].getFigures() == null)/*если на пути нет других фигур*/{
                loc = new Location(location.getX() + 1, location.getY() - 2);
                possibleMoves.add(loc);//добавляем в список
            } else{
                if(board[location.getX() + 1][location.getY() - 2].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                    loc = new Location(location.getX() + 1, location.getY() - 2);
                    possibleMoves.add(loc);//добавляем в список
                }
            }
        }

        //на две клетки влево и одну вниз
        if (location.getX() - 2 >= 0 && location.getY() - 1 >= 0){
            if(board[location.getX() - 2][location.getY() - 1].getFigures() == null)/*если на пути нет других фигур*/{
                loc = new Location(location.getX() - 2, location.getY() - 1);
                possibleMoves.add(loc);//добавляем в список
            } else{
                if(board[location.getX() - 2][location.getY() - 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                    loc = new Location(location.getX() - 2, location.getY() - 1);
                    possibleMoves.add(loc);//добавляем в список
                }
            }
        }

        //на одну клетку вправо и две вниз
        if (location.getX() - 1 >= 0  && location.getY() - 2 >= 0){
            if(board[location.getX() - 1][location.getY() - 2].getFigures() == null)/*если на пути нет других фигур*/{
                loc = new Location(location.getX() - 1, location.getY() - 2);
                possibleMoves.add(loc);//добавляем в список
            } else{
                if(board[location.getX() - 1][location.getY() - 2].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                    loc = new Location(location.getX() - 1, location.getY() - 2);
                    possibleMoves.add(loc);//добавляем в список
                }
            }
        }

        //на две клетки вправо и одну вверх
        if (location.getX() + 2 < 8 && location.getY() + 1 < 8){
            if(board[location.getX() + 2][location.getY() + 1].getFigures() == null)/*если на пути нет других фигур*/{
                loc = new Location(location.getX() + 2, location.getY() + 1);
                possibleMoves.add(loc);//добавляем в список
            } else{
                if(board[location.getX() + 2][location.getY() + 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                    loc = new Location(location.getX() + 2, location.getY() + 1);
                    possibleMoves.add(loc);//добавляем в список
                }
            }
        }

        //на одну клетку вправо и две вверх
        if (location.getX() + 1 < 8 && location.getY() + 2 < 8){
            if(board[location.getX() + 1][location.getY() + 2].getFigures() == null)/*если на пути нет других фигур*/{
                loc = new Location(location.getX() + 1, location.getY() + 2);
                possibleMoves.add(loc);//добавляем в список
            } else{
                if(board[location.getX() + 1][location.getY() + 2].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                    loc = new Location(location.getX() + 1, location.getY() + 2);
                    possibleMoves.add(loc);//добавляем в список
                }
            }
        }

        //на две клетки влево и одну вверх
        if (location.getX() - 2 >= 0 && location.getY() + 1 < 8){
            if(board[location.getX() - 2][location.getY() + 1].getFigures() == null)/*если на пути нет других фигур*/{
                loc = new Location(location.getX() - 2, location.getY() + 1);
                possibleMoves.add(loc);//добавляем в список
            } else{
                if(board[location.getX() - 2][location.getY() + 1].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                    loc = new Location(location.getX() - 2, location.getY() + 1);
                    possibleMoves.add(loc);//добавляем в список
                }
            }
        }

        //на одну клетку влево и две вверх
        if (location.getX() - 1 >= 0 && location.getY() + 2 < 8){
            if(board[location.getX() - 1][location.getY() + 2].getFigures() == null)/*если на пути нет других фигур*/{
                loc = new Location(location.getX() - 1, location.getY() + 2);
                possibleMoves.add(loc);//добавляем в список
            } else{
                if(board[location.getX() - 1][location.getY() + 2].getFigures().isWhite() != board[location.getX()][location.getY()].getFigures().isWhite())/*если на пути есть фигура противоположного цвета*/{
                    loc = new Location(location.getX() - 1, location.getY() + 2);
                    possibleMoves.add(loc);//добавляем в список
                }
            }
        }

        return possibleMoves;//возвращаем список ходов для коня
    }
}
