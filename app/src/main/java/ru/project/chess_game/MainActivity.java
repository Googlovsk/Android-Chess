package ru.project.chess_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.project.chess_game.Figures.Bishop;
import ru.project.chess_game.Figures.Figures;
import ru.project.chess_game.Figures.Horse;
import ru.project.chess_game.Figures.King;
import ru.project.chess_game.Figures.Pawn;
import ru.project.chess_game.Figures.Queen;
import ru.project.chess_game.Figures.Rook;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public boolean isGameEnded = false;
    public boolean white_rook_on_0_pos_isMoved = false; //Определяет, ходила ли белая ладья 0 позиции
    public boolean white_rook_on_7_pos_isMoved = false; //Определяет, ходила ли белая ладья 7 позиции
    public boolean black_rook_on_0_pos_isMoved = false; //Определяет, ходила ли чёрная ладья 0 позиции
    public boolean black_rook_on_7_pos_isMoved = false; //Определяет, ходила ли чёрная ладья 7 позиции
    public boolean WhiteKingIsMoved = false; //Определяет, ходил ли белый король
    public boolean BlackKingIsMoved = false; //Определяет, ходил ли чёрный король
    public boolean kingInDanger=false;
    public Location clickedPosition = new Location(0, 0);
    public Position[][] Board = new Position[8][8];
    public Position[][] Board2 = new Position[8][8];//нужен для обновления позиций фигур
    public TextView[][] DisplayBoard = new TextView[8][8];
    public TextView[][] DisplayBoardBackground = new TextView[8][8];
    public Location lastPos = null ;
    public ViewGroup container;
    public Boolean AnythingSelected = false;
    public Boolean FirstPlayerTurn;
    public ArrayList<Location> listOfLocation = new ArrayList<>();
    public String color=null;
    public LinearLayout revenge_menu;
    public LinearLayout pawn_morphing;
    Figures white_king;
    Figures white_queen;
    Figures white_bishop_1;
    Figures white_bishop_2;
    Figures white_horse_1;
    Figures white_horse_2;
    Figures white_rook_1;
    Figures white_rook_2;
    Figures white_pawn_1;
    Figures white_pawn_2;
    Figures white_pawn_3;
    Figures white_pawn_4;
    Figures white_pawn_5;
    Figures white_pawn_6;
    Figures white_pawn_7;
    Figures white_pawn_8;
    Figures black_king;
    Figures black_queen;
    Figures black_bishop_1;
    Figures black_bishop_2;
    Figures black_horse_1;
    Figures black_horse_2;
    Figures black_rook_1;
    Figures black_rook_2;
    Figures black_pawn_1;
    Figures black_pawn_2;
    Figures black_pawn_3;
    Figures black_pawn_4;
    Figures black_pawn_5;
    Figures black_pawn_6;
    Figures black_pawn_7;
    Figures black_pawn_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        drawTextOnBoard();
        pawn_morphing = findViewById(R.id.pawn_morphing);
        pawn_morphing.setVisibility(View.INVISIBLE);

        View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                hideNavigationBar();
            }
        });
        hideNavigationBar();
    }
    private void hideNavigationBar() {
        int flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(flags);
    }

    private TextView createTextView(int width, int height, String text) {//Макет для текствьюшек на доске
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height, 1f);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(12, 12, 0, 0);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/press_start_2p.ttf"));
        textView.setText(text);
        textView.setTextSize(16);
        textView.setTextColor(getResources().getColor(R.color.colorBoardDark));
        return textView;
    }

    /**
     * Метод отрисовки цифр и букв на доске
     * */
    private void drawTextOnBoard() {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] numbers = {"8", "7", "6", "5", "4", "3", "2", "1"};
        LinearLayout board_text_top = findViewById(R.id.board_abcdefgh);
        LinearLayout board_text_bottom = findViewById(R.id.board_abcdefgh_bottom);
        LinearLayout board_text_left = findViewById(R.id.board_12345678);
        LinearLayout board_text_right = findViewById(R.id.board_12345678_right);

        int width_43dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 43, getResources().getDisplayMetrics());
        int height_33dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 33, getResources().getDisplayMetrics());
        int width_33dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 33, getResources().getDisplayMetrics());
        int height_43dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 43, getResources().getDisplayMetrics());

        for (String letter : letters) {
            TextView textViewTop = createTextView(width_43dp, height_33dp, letter);
            textViewTop.setPadding(0,12,0,0);
            textViewTop.setScaleX(-1);
            textViewTop.setScaleY(-1);
            board_text_top.addView(textViewTop);

            TextView textViewBottom = createTextView(width_43dp, height_33dp, letter);

            textViewBottom.setPadding(0,12,0,0);
            board_text_bottom.addView(textViewBottom);
        }

        for (String number : numbers) {
            TextView textViewLeft = createTextView(width_33dp, height_43dp, number);
            board_text_left.addView(textViewLeft);

            TextView textViewRight = createTextView(width_33dp, height_43dp, number);
            textViewRight.setScaleX(-1);
            textViewRight.setScaleY(-1);
            board_text_right.addView(textViewRight);
        }
    }
    private void initialize() {

        //присваивание булевого значения white обоим командам

        white_king = new King(true);
        white_queen = new Queen(true);
        white_bishop_1 = new Bishop(true);
        white_bishop_2 = new Bishop(true);
        white_horse_1 = new Horse(true);
        white_horse_2 = new Horse(true);
        white_rook_1 = new Rook(true);
        white_rook_2 = new Rook(true);
        white_pawn_1 = new Pawn(true);
        white_pawn_2 = new Pawn(true);
        white_pawn_3 = new Pawn(true);
        white_pawn_4 = new Pawn(true);
        white_pawn_5 = new Pawn(true);
        white_pawn_6 = new Pawn(true);
        white_pawn_7 = new Pawn(true);
        white_pawn_8 = new Pawn(true);

        black_king = new King(false);
        black_queen = new Queen(false);
        black_bishop_1 = new Bishop(false);
        black_bishop_2 = new Bishop(false);
        black_horse_1 = new Horse(false);
        black_horse_2 = new Horse(false);
        black_rook_1 = new Rook(false);
        black_rook_2 = new Rook(false);
        black_pawn_1 = new Pawn(false);
        black_pawn_2 = new Pawn(false);
        black_pawn_3 = new Pawn(false);
        black_pawn_4 = new Pawn(false);
        black_pawn_5 = new Pawn(false);
        black_pawn_6 = new Pawn(false);
        black_pawn_7 = new Pawn(false);
        black_pawn_8 = new Pawn(false);

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Board[i][j] = new Position(null);
                Board2[i][j] = new Position(null);
            }
        }
        // объявление белой команды
        Board[0][7].setFigures(white_rook_1);
        Board[1][7].setFigures(white_horse_1);
        Board[2][7].setFigures(white_bishop_1);
        Board[3][7].setFigures(white_queen);
        Board[4][7].setFigures(white_king);
        Board[5][7].setFigures(white_bishop_2);
        Board[6][7].setFigures(white_horse_2);
        Board[7][7].setFigures(white_rook_2);

        Board[0][6].setFigures(white_pawn_1);
        Board[1][6].setFigures(white_pawn_2);
        Board[2][6].setFigures(white_pawn_3);
        Board[3][6].setFigures(white_pawn_4);
        Board[4][6].setFigures(white_pawn_5);
        Board[5][6].setFigures(white_pawn_6);
        Board[6][6].setFigures(white_pawn_7);
        Board[7][6].setFigures(white_pawn_8);

        // объявление чёрной команды
        Board[0][0].setFigures(black_rook_1);
        Board[1][0].setFigures(black_horse_1);
        Board[2][0].setFigures(black_bishop_1);
        Board[3][0].setFigures(black_queen);
        Board[4][0].setFigures(black_king);
        Board[5][0].setFigures(black_bishop_2);
        Board[6][0].setFigures(black_horse_2);
        Board[7][0].setFigures(black_rook_2);

        Board[0][1].setFigures(black_pawn_1);
        Board[1][1].setFigures(black_pawn_2);
        Board[2][1].setFigures(black_pawn_3);
        Board[3][1].setFigures(black_pawn_4);
        Board[4][1].setFigures(black_pawn_5);
        Board[5][1].setFigures(black_pawn_6);
        Board[6][1].setFigures(black_pawn_7);
        Board[7][1].setFigures(black_pawn_8);

        // общий префикс для айдишек
        String cellIdPrefix = "cell_";
        String cellBackgroundIdPrefix = "cell_background_";
        // объявление линий по буквам
        String[] lines = {"a", "b", "c", "d", "e", "f", "j", "k"};//да да, я очень хорошо знаю аглицкий язык, поэтому вместо "g" и "h" написал j и k
        // объявление ячеек для каждой линии
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < 8; j++) {
                String cellId = cellIdPrefix + lines[i] + (j+1);
                String cellBackgroundId = cellBackgroundIdPrefix + lines[i] + (j+1);

                DisplayBoard[j][i] = findViewById(getResources().getIdentifier(cellId, "id", getPackageName()));
                DisplayBoardBackground[j][i] = findViewById(getResources().getIdentifier(cellBackgroundId, "id", getPackageName()));
            }
        }
        /*проверка каждой клетки на наличие фигуры
        * если в клетке есть фигура, она копируется в соответствующую клетку Board2
        * если фигуры нет, она так же копируется, но со значением null*/
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(Board[i][j].getFigures() == null){
                    Board2[i][j].setFigures(null);
                }else{
                    Board2[i][j].setFigures(Board[i][j].getFigures());
                }
            }
        }
        AnythingSelected = false;//ни одна фигура не выбрана
        FirstPlayerTurn = true;//первыми ходят белые
        setBoard();//выставляем фигуры
    }

    private void setBoard() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Figures figures = Board[i][j].getFigures();

                int typeOfFigure;
                /*определяет тип фигуры, которая находится в текущей позиции доски.
                  Например, typeOfFigure=0 соответствует фигуре Короля, typeOfFigure=1 соответствует фигуре Ферзя и т.д.*/
                if (Board[i][j].getFigures() != null) {
                    if (figures instanceof King) typeOfFigure = 0;
                    else if (figures instanceof Queen) typeOfFigure = 1;
                    else if (figures instanceof Rook) typeOfFigure = 2;
                    else if (figures instanceof Bishop) typeOfFigure = 3;
                    else if (figures instanceof Horse) typeOfFigure = 4;
                    else if (figures instanceof Pawn) typeOfFigure = 5;
                    else typeOfFigure = 6;

                    switch (typeOfFigure) {
                        case 0:
                            if (figures.isWhite()) {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.white_king);
                            } else {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.black_king);
                            }
                            break;

                        case 1:
                            if (figures.isWhite()) {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.white_queen);
                            } else {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.black_queen);
                            }
                            break;

                        case 2:
                            if (figures.isWhite()) {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.white_rook);
                            } else {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.black_rook);
                            }
                            break;

                        case 3:
                            if (figures.isWhite()) {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.white_bishop);
                            } else {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.black_bishop);
                            }
                            break;

                        case 4:
                            if (figures.isWhite()) {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.white_horse);
                            } else {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.black_horse);
                            }
                            break;

                        case 5:
                            if (figures.isWhite()) {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.white_pawn);
                            } else {
                                DisplayBoard[i][j].setBackgroundResource(R.drawable.black_pawn);
                            }
                            break;

                        default:

                    }
                }else{
                    DisplayBoard[i][j].setBackgroundResource(0);
                }
            }
        }
        kingInDanger = isKingInDanger(); //проверяем, находится ли король под угрозой
    }

    @Override
    public void onClick(View view) {
        if (!isGameEnded) {
            Map<Integer, Location> cellMap = new HashMap<>();
            cellMap.put(R.id.cell_a1, new Location(0, 0));
            cellMap.put(R.id.cell_a2, new Location(1, 0));
            cellMap.put(R.id.cell_a3, new Location(2, 0));
            cellMap.put(R.id.cell_a4, new Location(3, 0));
            cellMap.put(R.id.cell_a5, new Location(4, 0));
            cellMap.put(R.id.cell_a6, new Location(5, 0));
            cellMap.put(R.id.cell_a7, new Location(6, 0));
            cellMap.put(R.id.cell_a8, new Location(7, 0));
            cellMap.put(R.id.cell_b1, new Location(0, 1));
            cellMap.put(R.id.cell_b2, new Location(1, 1));
            cellMap.put(R.id.cell_b3, new Location(2, 1));
            cellMap.put(R.id.cell_b4, new Location(3, 1));
            cellMap.put(R.id.cell_b5, new Location(4, 1));
            cellMap.put(R.id.cell_b6, new Location(5, 1));
            cellMap.put(R.id.cell_b7, new Location(6, 1));
            cellMap.put(R.id.cell_b8, new Location(7, 1));
            cellMap.put(R.id.cell_c1, new Location(0, 2));
            cellMap.put(R.id.cell_c2, new Location(1, 2));
            cellMap.put(R.id.cell_c3, new Location(2, 2));
            cellMap.put(R.id.cell_c4, new Location(3, 2));
            cellMap.put(R.id.cell_c5, new Location(4, 2));
            cellMap.put(R.id.cell_c6, new Location(5, 2));
            cellMap.put(R.id.cell_c7, new Location(6, 2));
            cellMap.put(R.id.cell_c8, new Location(7, 2));
            cellMap.put(R.id.cell_d1, new Location(0, 3));
            cellMap.put(R.id.cell_d2, new Location(1, 3));
            cellMap.put(R.id.cell_d3, new Location(2, 3));
            cellMap.put(R.id.cell_d4, new Location(3, 3));
            cellMap.put(R.id.cell_d5, new Location(4, 3));
            cellMap.put(R.id.cell_d6, new Location(5, 3));
            cellMap.put(R.id.cell_d7, new Location(6, 3));
            cellMap.put(R.id.cell_d8, new Location(7, 3));
            cellMap.put(R.id.cell_e1, new Location(0, 4));
            cellMap.put(R.id.cell_e2, new Location(1, 4));
            cellMap.put(R.id.cell_e3, new Location(2, 4));
            cellMap.put(R.id.cell_e4, new Location(3, 4));
            cellMap.put(R.id.cell_e5, new Location(4, 4));
            cellMap.put(R.id.cell_e6, new Location(5, 4));
            cellMap.put(R.id.cell_e7, new Location(6, 4));
            cellMap.put(R.id.cell_e8, new Location(7, 4));
            cellMap.put(R.id.cell_f1, new Location(0, 5));
            cellMap.put(R.id.cell_f2, new Location(1, 5));
            cellMap.put(R.id.cell_f3, new Location(2, 5));
            cellMap.put(R.id.cell_f4, new Location(3, 5));
            cellMap.put(R.id.cell_f5, new Location(4, 5));
            cellMap.put(R.id.cell_f6, new Location(5, 5));
            cellMap.put(R.id.cell_f7, new Location(6, 5));
            cellMap.put(R.id.cell_f8, new Location(7, 5));
            cellMap.put(R.id.cell_j1, new Location(0, 6));
            cellMap.put(R.id.cell_j2, new Location(1, 6));
            cellMap.put(R.id.cell_j3, new Location(2, 6));
            cellMap.put(R.id.cell_j4, new Location(3, 6));
            cellMap.put(R.id.cell_j5, new Location(4, 6));
            cellMap.put(R.id.cell_j6, new Location(5, 6));
            cellMap.put(R.id.cell_j7, new Location(6, 6));
            cellMap.put(R.id.cell_j8, new Location(7, 6));
            cellMap.put(R.id.cell_k1, new Location(0, 7));
            cellMap.put(R.id.cell_k2, new Location(1, 7));
            cellMap.put(R.id.cell_k3, new Location(2, 7));
            cellMap.put(R.id.cell_k4, new Location(3, 7));
            cellMap.put(R.id.cell_k5, new Location(4, 7));
            cellMap.put(R.id.cell_k6, new Location(5, 7));
            cellMap.put(R.id.cell_k7, new Location(6, 7));
            cellMap.put(R.id.cell_k8, new Location(7, 7));

            if (cellMap.containsKey(view.getId())) {
                clickedPosition = cellMap.get(view.getId());
            }
        }
        if (!AnythingSelected) {

            if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures() == null) {//проверка на наличие фигуры
                isKingInDanger();
                return;
            }else{
                if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite() != FirstPlayerTurn){
                    isKingInDanger();
                    return;
                }else{
                    listOfLocation.clear();

                    //проверяет принадлежность фигуры  к игроку, который ходит в данный момент
                    if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite()) {
                        listOfLocation = Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().possibleMoves(clickedPosition, Board, WhiteKingIsMoved, white_rook_on_0_pos_isMoved, white_rook_on_7_pos_isMoved, kingInDanger);
                    }else{
                        listOfLocation = Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().possibleMoves(clickedPosition, Board, BlackKingIsMoved, black_rook_on_0_pos_isMoved, black_rook_on_7_pos_isMoved, kingInDanger);
                    }
                    //Подсвечивает квадраты на которые возможен ход и фон выбранной фигуры
                    DisplayBoardBackground[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.color.colorSelected);
                    setColorAtPossiblePosition(listOfLocation);
                    AnythingSelected = true;

                }
            }
        } else {
            if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures() == null){//является ли выбранная клетка пустой
                if(moveIsPossible(listOfLocation , clickedPosition)){//возможен ли ход
                    if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures() instanceof King){//если фигура на выбранной клетке Король
                        if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite() != FirstPlayerTurn){//цвет фигуры противоположен цвету хода
                            isGameEnded=true;//короля съели, игра завершена
                            postGameRotation();//вызов реваншного меню
                        }
                    }
                    castlingControl:
                    {
                        //если король белый и его позиция x=6 y=7 выполняется короткая рокировка
                        if (Board[lastPos.getX()][lastPos.getY()].getFigures() instanceof King && Board[lastPos.getX()][lastPos.getY()].getFigures().isWhite()) {
                            if (clickedPosition.getX() == 6 && clickedPosition.getY() == 7) {
                                Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                Board[5][7].setFigures(Board[7][7].getFigures());//ставит ладью на новую позицию
                                Board[7][7].setFigures(null);//убирает ладью с предыдущей позиции
                                Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                WhiteKingIsMoved = true;//теперь рокировка невозможна
                                break castlingControl;//выход из функции
                            } else {
                                //иначе если позиция x=2 y=7 выполняется длинная рокировка
                                if (clickedPosition.getX() == 2 && clickedPosition.getY() == 7) {
                                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                    Board[3][7].setFigures(Board[0][7].getFigures());//ставит ладью на новую позицию
                                    Board[0][7].setFigures(null);//убирает ладью с предыдущей позиции
                                    Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                    WhiteKingIsMoved = true;//теперь рокировка невозможна
                                    break castlingControl;//выход из функции
                                }
                            }
                        }
                        //если король черный и его позиция x=6 y=0 выполняется короткая рокировка
                        else if (Board[lastPos.getX()][lastPos.getY()].getFigures() instanceof King && !Board[lastPos.getX()][lastPos.getY()].getFigures().isWhite()) {
                            if (clickedPosition.getX() == 6 && clickedPosition.getY() == 0) {
                                Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                Board[5][0].setFigures(Board[7][0].getFigures());//ставит ладью на новую позицию
                                Board[7][0].setFigures(null);//убирает ладью с предыдущей позиции
                                Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                BlackKingIsMoved = true;//теперь рокировка невозможна
                                break castlingControl;//выход из функции
                            } else {
                                //иначе если позиция x=2 y=0 выполняется длинная рокировка
                                if (clickedPosition.getX() == 2 && clickedPosition.getY() == 0) {
                                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                    Board[3][0].setFigures(Board[0][0].getFigures());//ставит ладью на новую позицию
                                    Board[0][0].setFigures(null);//убирает ладью с предыдущей позиции
                                    Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                    BlackKingIsMoved = true;//теперь рокировка невозможна
                                    break castlingControl;//выход из функции
                                }
                            }
                        }
                        if (Board[lastPos.getX()][lastPos.getY()].getFigures() instanceof King) {
                            if (Board[lastPos.getX()][lastPos.getY()].getFigures().isWhite()) {
                                WhiteKingIsMoved = true;//если король уже сходил, рокировка блокируется
                            } else {
                                BlackKingIsMoved = true;//аналогично для черного короля
                            }
                            //премещает короля на новую позицию
                            Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                            //убирает короля со старой позиции
                            Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                        }
                        else if(Board[lastPos.getX()][lastPos.getY()].getFigures() instanceof Rook) {
                            if(Board[lastPos.getX()][lastPos.getY()].getFigures().isWhite()){
                                if(lastPos.getX() == 7 && lastPos.getY() == 7){
                                    white_rook_on_7_pos_isMoved = true;//если белая ладья в позиции x=7 y=7 уже сделала ход, рокировка для неё блокируется
                                }
                                else if(lastPos.getX() == 0 && lastPos.getY() == 7){
                                    white_rook_on_0_pos_isMoved = true;//если белая ладья в позиции x=0 y=7 уже сделала ход, рокировка для неё блокируется
                                }
                            }
                            else{
                                if(lastPos.getX() == 0 && lastPos.getY() == 0){
                                    black_rook_on_0_pos_isMoved = true;//если черная ладья в позиции x=0 y=0 уже сделала ход, рокировка для неё блокируется
                                }
                                else if(lastPos.getX() == 7 && lastPos.getY() == 0){
                                    black_rook_on_7_pos_isMoved = true;//если черная ладья в позиции x=7 y=0 уже сделала ход, рокировка для неё блокируется
                                }
                            }
                            //ставит ладью на новую позицию
                            Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                            //убирает ладью со старой позиции
                            Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                        }
                        else{
                            //если фигура не является ни королем ни ладьёй, то просто перемещается на новую позицию
                            Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                            //убирает фигуру со старой позиции
                            Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                        }
                    }
                    isKingInDanger();//вызов проверки на опасность для короля
                    resetColorAtPossiblePosition(listOfLocation);//вызов метода для подсвечивания возможных ходов
                    DisplayBoard[lastPos.getX()][lastPos.getY()].setBackgroundResource(0);
                    resetColorAtLastPosition(lastPos);//восстанавливает предыдущий цвет квадрата
                    figuresRotation();//поворачивает фигуры для удобства
                    AnythingSelected = false;//ни одна фигура не выбрана
                    FirstPlayerTurn = !FirstPlayerTurn;//предает ход следующему игроку
                    checkOfPawnMorphing();//проверка на возможность превращения пешки
                }else{
                    resetColorAtLastPosition(lastPos);//восстанавливает предыдущий цвет квадрата
                    resetColorAtPossiblePosition(listOfLocation);//вызов метода для подсвечивания возможных ходов
                    AnythingSelected = false;//ни одна фигура не выбрана
                }
            }else{
                if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures() == null) {//проверяет пуста ли ячейка куда сходил игрок
                    isKingInDanger();//вызов проверки на угрозу королю
                    return;

                }else{
                    if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures() != null){//есть ли фигура в ячейке
                        if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite() != FirstPlayerTurn){//того же ли цвета фигура что и цвет хода
                            if(moveIsPossible(listOfLocation , clickedPosition)){/*проверяет, является ли перемещение возможным
                            если да, выполняется код который перемещает фигуру на новую позицию и переводит AnythingSelected и FirstPlayerTurn в занчение false*/
                                if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures() instanceof King){//если выбранная фигура это король
                                    if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite() != FirstPlayerTurn){
                                        isGameEnded=true;//завершение сессии
                                        postGameRotation();//вызов реваншного меню
                                    }
                                }
                                castlingControl:
                                {
                                    //если король белый и его позиция x=6 y=7 выполняется короткая рокировка
                                    if (Board[lastPos.getX()][lastPos.getY()].getFigures() instanceof King && Board[lastPos.getX()][lastPos.getY()].getFigures().isWhite()) {
                                        if (clickedPosition.getX() == 6 && clickedPosition.getY() == 7) {
                                            Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                            Board[5][7].setFigures(Board[7][7].getFigures());//ставит ладью на новую позицию
                                            Board[7][7].setFigures(null);//убирает ладью с предыдущей позиции
                                            Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                            WhiteKingIsMoved = true;//теперь рокировка невозможна
                                            break castlingControl;//выход из функции
                                        } else {
                                            //иначе если позиция x=2 y=7 выполняется длинная рокировка
                                            if (clickedPosition.getX() == 2 && clickedPosition.getY() == 7) {
                                                Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                                Board[3][7].setFigures(Board[0][7].getFigures());//ставит ладью на новую позицию
                                                Board[0][7].setFigures(null);//убирает ладью с предыдущей позиции
                                                Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                                WhiteKingIsMoved = true;//теперь рокировка невозможна
                                                break castlingControl;//выход из функции
                                            }
                                        }
                                    }
                                    //если король черный и его позиция x=6 y=0 выполняется короткая рокировка
                                    else if (Board[lastPos.getX()][lastPos.getY()].getFigures() instanceof King && !Board[lastPos.getX()][lastPos.getY()].getFigures().isWhite()) {
                                        if (clickedPosition.getX() == 6 && clickedPosition.getY() == 0) {
                                            Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                            Board[5][0].setFigures(Board[7][0].getFigures());//ставит ладью на новую позицию
                                            Board[7][0].setFigures(null);//убирает ладью с предыдущей позиции
                                            Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                            BlackKingIsMoved = true;//теперь рокировка невозможна
                                            break castlingControl;//выход из функции
                                        } else {
                                            //иначе если позиция x=2 y=0 выполняется длинная рокировка
                                            if (clickedPosition.getX() == 2 && clickedPosition.getY() == 0) {
                                                Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                                Board[3][0].setFigures(Board[0][0].getFigures());//ставит ладью на новую позицию
                                                Board[0][0].setFigures(null);//убирает ладью с предыдущей позиции
                                                Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                                BlackKingIsMoved = true;//теперь рокировка невозможна
                                                break castlingControl;//выход из функции
                                            }
                                        }
                                    }
                                    if (Board[lastPos.getX()][lastPos.getY()].getFigures() instanceof King) {
                                        if (Board[lastPos.getX()][lastPos.getY()].getFigures().isWhite()) {
                                            WhiteKingIsMoved = true;//если белый король уже сходил, рокировка блокируется
                                        } else {
                                            BlackKingIsMoved = true;//если черный король уже сходил, рокировка блокируется
                                        }
                                        //премещает короля на новую позицию
                                        Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                        //убирает короля со старой позиции
                                        Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                    }
                                    else if(Board[lastPos.getX()][lastPos.getY()].getFigures() instanceof Rook) {
                                        if(Board[lastPos.getX()][lastPos.getY()].getFigures().isWhite()){
                                            if(lastPos.getX() == 7 && lastPos.getY() == 7){
                                                white_rook_on_7_pos_isMoved = true;//если белая ладья в позиции x=7 y=7 уже сделала ход, рокировка для неё блокируется
                                            }
                                            else if(lastPos.getX() == 0 && lastPos.getY() == 7){
                                                white_rook_on_0_pos_isMoved = true;//если белая ладья в позиции x=0 y=7 уже сделала ход, рокировка для неё блокируется
                                            }
                                        }
                                        else{
                                            if(lastPos.getX() == 0 && lastPos.getY() == 0){
                                                black_rook_on_0_pos_isMoved = true;//если белая ладья в позиции x=0 y=0 уже сделала ход, рокировка для неё блокируется
                                            }
                                            else if(lastPos.getX() == 7 && lastPos.getY() == 0){
                                                black_rook_on_7_pos_isMoved = true;//если белая ладья в позиции x=7 y=0 уже сделала ход, рокировка для неё блокируется
                                            }
                                        }
                                        //ставит ладью на новую позицию
                                        Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                        //убирает ладью со старой позиции
                                        Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                    }
                                    else{
                                        //если фигура не является ни королем ни ладьёй, то просто перемещается на новую позицию
                                        Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(Board[lastPos.getX()][lastPos.getY()].getFigures());
                                        //убирает фигуру со старой позиции
                                        Board[lastPos.getX()][lastPos.getY()].setFigures(null);
                                    }
                                }
                                resetColorAtPossiblePosition(listOfLocation);//подсвечивает возможные ходы
                                DisplayBoard[lastPos.getX()][lastPos.getY()].setBackgroundResource(0);
                                resetColorAtLastPosition(lastPos);//восстанавливает цвет на предыдущей позиции
                                figuresRotation();//преворачивает фигуры
                                AnythingSelected = false;//ни одна фигура не выбрана
                                FirstPlayerTurn = !FirstPlayerTurn;//передает ход следующему игроку
                                checkOfPawnMorphing();//проверка на возможность превращения пешки
                            }else{
                                resetColorAtLastPosition(lastPos);//подсвечивает возможные ходы
                                resetColorAtPossiblePosition(listOfLocation);//восстанавливает цвет на предыдущей позиции
                                AnythingSelected = false;//ни одна фигура не выбрана
                            }
                        }else{
                            if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite() != FirstPlayerTurn){
                                isKingInDanger();//вызов проверки на угрозу королю
                                return;
                            }

                            resetColorAtLastPosition(lastPos);//восстанавливает цвет на предыдущей позиции
                            resetColorAtPossiblePosition(listOfLocation);//подсвечивает возможные ходы

                            listOfLocation.clear();
                            if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite()) {
                                listOfLocation = Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().possibleMoves(clickedPosition, Board, WhiteKingIsMoved, white_rook_on_0_pos_isMoved, white_rook_on_7_pos_isMoved, kingInDanger);
                            }else{
                                listOfLocation = Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().possibleMoves(clickedPosition, Board, BlackKingIsMoved, black_rook_on_0_pos_isMoved, black_rook_on_7_pos_isMoved, kingInDanger);
                            }
                            DisplayBoardBackground[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.color.colorSelected);
                            setColorAtPossiblePosition(listOfLocation);
                            AnythingSelected = true;
                        }
                    }
                }
            }
        }
        isKingInDanger();//вызов проверки на угрозу королю
        lastPos = new Location(clickedPosition.getX(), clickedPosition.getY());
        setBoard();//обновление доски
    }
    /**Метод превращения пешки*/
    @SuppressLint("NonConstantResourceId")
    public void pawnMorphing(View view){
        int x = view.getId();
        switch (x){
            case R.id.pawnMorphing_to_queen:
                if(clickedPosition.getY() == 0){
                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(new Queen(true));
                    DisplayBoard[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.drawable.white_queen);
                }else{
                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(new Queen(false));
                    DisplayBoard[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.drawable.black_queen);
                }
                break;
            case R.id.pawnMorphing_to_rook:
                if(clickedPosition.getY() == 0){
                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(new Rook(true));
                    DisplayBoard[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.drawable.white_rook);
                }else{
                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(new Rook(false));
                    DisplayBoard[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.drawable.black_rook);
                }
                break;
            case R.id.pawnMorphing_to_bishop:
                if(clickedPosition.getY() == 0){
                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(new Bishop(true));
                    DisplayBoard[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.drawable.white_bishop);
                }else{
                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(new Bishop(false));
                    DisplayBoard[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.drawable.black_bishop);
                }
                break;
            case R.id.pawnMorphing_to_horse:
                if(clickedPosition.getY() == 0){
                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(new Horse(true));
                    DisplayBoard[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.drawable.white_horse);
                }else{
                    Board[clickedPosition.getX()][clickedPosition.getY()].setFigures(new Horse(false));
                    DisplayBoard[clickedPosition.getX()][clickedPosition.getY()].setBackgroundResource(R.drawable.black_horse);
                }
                break;
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                DisplayBoard[i][j].setClickable(true);
            }
        }
        pawn_morphing.setVisibility(View.INVISIBLE);
    }
    private void resetColorAtPossiblePosition(ArrayList<Location> listOfLocation) {
        for(int i=0; i<listOfLocation.size(); i++){
            if((listOfLocation.get(i).getX() + listOfLocation.get(i).getY())%2==0){
                DisplayBoardBackground[listOfLocation.get(i).getX()][listOfLocation.get(i).getY()].setBackgroundResource(R.color.colorBoardDark);
            }else {
                DisplayBoardBackground[listOfLocation.get(i).getX()][listOfLocation.get(i).getY()].setBackgroundResource(R.color.colorBoardLight);
            }
        }
    }
    void setColorAtPossiblePosition(ArrayList<Location> list){

        for(int i=0; i<list.size(); i++){
            if(color==null){
                if(Board[list.get(i).getX()][list.get(i).getY()].getFigures() == null){
                    DisplayBoardBackground[list.get(i).getX()][list.get(i).getY()].setBackgroundResource(R.color.colorPositionAvailable);
                }else{
                    DisplayBoardBackground[list.get(i).getX()][list.get(i).getY()].setBackgroundResource(R.color.colorDanger);
                }
            }
        }
    }
    private boolean moveIsPossible(ArrayList<Location> figures, Location location) {
        boolean Possible = false;
        for(int i = 0; i < figures.size(); i++){
            if(figures.get(i).getX() == location.getX()  &&  figures.get(i).getY() == location.getY()){
                Possible = true;
                break;
            }
        }
        return Possible;
    }
    private void resetColorAtLastPosition(Location lastPos){
        if((lastPos.getX() + lastPos.getY()) % 2==0){
            DisplayBoardBackground[lastPos.getX()][lastPos.getY()].setBackgroundResource(R.color.colorBoardDark);
        }else {
            DisplayBoardBackground[lastPos.getX()][lastPos.getY()].setBackgroundResource(R.color.colorBoardLight);
        }
    }
    /**проверяет, находится ли король в опасности
    проходит по игровому полю и для каждой фигуры (не равной null) вызывает метод PossibleMoves(), для получения списока возможных ходов.
    проверяет, есть ли ходы на короля противоположной стороны и помечает соответствующую клетку на доске красным
    если король в опасности, метод возвращает true, нет - false*/
    private boolean isKingInDanger(){
        ArrayList<Location> List = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(Board[i][j].getFigures() != null){
                    List.clear();
                    Location loc = new Location(i,j);
                    if(Board[i][j].getFigures().isWhite()) {
                        List = Board[i][j].getFigures().possibleMoves(loc, Board, WhiteKingIsMoved, white_rook_on_0_pos_isMoved, white_rook_on_7_pos_isMoved, kingInDanger);
                    }
                    else{
                        List = Board[i][j].getFigures().possibleMoves(loc, Board, BlackKingIsMoved, black_rook_on_0_pos_isMoved, black_rook_on_7_pos_isMoved, kingInDanger);
                    }
                    for (int x=0;x<List.size();x++){
                        if(Board[List.get(x).getX()][List.get(x).getY()].getFigures() instanceof King){

                            if((List.get(x).getX()+List.get(x).getY())%2==0){
                                DisplayBoardBackground[List.get(x).getX()][List.get(x).getY()].setBackgroundResource(R.color.colorBoardDark);
                            }else{
                                DisplayBoardBackground[List.get(x).getX()][List.get(x).getY()].setBackgroundResource(R.color.colorBoardLight);
                            }

                            if(Board[i][j].getFigures().isWhite() != Board[List.get(x).getX()][List.get(x).getY()].getFigures().isWhite()){
                                DisplayBoardBackground[List.get(x).getX()][List.get(x).getY()].setBackgroundResource(R.color.colorKingInDanger);
                            }
                            return true;
                        }
                        else{
                            if(Board[i][j].getFigures() instanceof King){
                                if((i+j)%2==0){
                                    DisplayBoardBackground[i][j].setBackgroundResource(R.color.colorBoardDark);
                                }else{
                                    DisplayBoardBackground[i][j].setBackgroundResource(R.color.colorBoardLight);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    /*проверяет, является ли фигура на выбранной позиции пешкой
     если да, то проверяет, достигла ли пешка конца доски
     если это так, то появляется диалоговое окно для выбора новой фигуры
     игровое поле становятся некликабельными, чтобы небыло глитчей
     далее вызывается проверка на опасность для короля*/
    private void checkOfPawnMorphing(){
        if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures() instanceof Pawn){
            if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite()){
                if(clickedPosition.getY() == 0){//если позиция пешки рана 0, вызывается диалоговое окно. т.е если белая пешка дошла до противоположного конца доски
                    pawn_morphing.setVisibility(View.VISIBLE);
                    container = findViewById(R.id.pawn_morphing);
                }
            }else{
                if(clickedPosition.getY() == 7){//если позиция пешки рана 7, вызывается диалоговое окно и переворачивается для удобства 2 игрока.
                    pawn_morphing.setVisibility(View.VISIBLE);
                    container = findViewById(R.id.pawn_morphing);
                    pawn_morphing.setRotation(180);
                }
            }
        }
        //это делает некликабельным игровое поле на время выбора новой фигуры.
        if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures() instanceof Pawn){
            if(Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite()){
                if(clickedPosition.getY() == 0){
                    for (int i = 0; i < 8; i++){
                        for (int j = 0; j < 8; j++){
                            DisplayBoard[i][j].setClickable(false);
                        }
                    }
                }
            }else{
                if(clickedPosition.getY() == 7){
                    for (int i = 0; i < 8; i++){
                        for (int j = 0; j < 8; j++){
                            DisplayBoard[i][j].setClickable(false);
                        }
                    }
                }
            }
        }
        isKingInDanger();
    }
    @SuppressLint("DiscouragedApi")
    private void figuresRotation(){ //переворачивает фигуры при каждом ходе
        //Если сходила фигура белая, поворот на 180, если черная, возвращает на 0 | Запомнить. ? - if, : - else
        int rotationAngle = FirstPlayerTurn && AnythingSelected == Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite() ? 180 : 0;
        for (int i = 0; i < 8; i++) {
            DisplayBoard[i][0] = findViewById(getResources().getIdentifier("cell_a" + (i+1), "id", getPackageName()));//"defType:" определяет тип запрашиваемого ресурса, по умолчанию это id
            DisplayBoard[i][0].setRotation(rotationAngle);
            DisplayBoard[i][1] = findViewById(getResources().getIdentifier("cell_b" + (i+1), "id", getPackageName()));
            DisplayBoard[i][1].setRotation(rotationAngle);
            DisplayBoard[i][2] = findViewById(getResources().getIdentifier("cell_c" + (i+1), "id", getPackageName()));
            DisplayBoard[i][2].setRotation(rotationAngle);
            DisplayBoard[i][3] = findViewById(getResources().getIdentifier("cell_d" + (i+1), "id", getPackageName()));
            DisplayBoard[i][3].setRotation(rotationAngle);
            DisplayBoard[i][4] = findViewById(getResources().getIdentifier("cell_e" + (i+1), "id", getPackageName()));
            DisplayBoard[i][4].setRotation(rotationAngle);
            DisplayBoard[i][5] = findViewById(getResources().getIdentifier("cell_f" + (i+1), "id", getPackageName()));
            DisplayBoard[i][5].setRotation(rotationAngle);
            DisplayBoard[i][6] = findViewById(getResources().getIdentifier("cell_j" + (i+1), "id", getPackageName()));
            DisplayBoard[i][6].setRotation(rotationAngle);
            DisplayBoard[i][7] = findViewById(getResources().getIdentifier("cell_k" + (i+1), "id", getPackageName()));
            DisplayBoard[i][7].setRotation(rotationAngle);
        }
    }
    private void postGameRotation() {
        Dialog dialog = new Dialog(this, R.style.Theme_Chess_game);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        dialog.setContentView(R.layout.dialog_game_status);
        dialog.setCancelable(true);

        revenge_menu = dialog.findViewById(R.id.dialog_revenge_menu);
        ImageView imageView = dialog.findViewById(R.id.image_win_status);

        int backgroundResource = Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite() ? R.drawable.black_win : R.drawable.white_win;
        imageView.setBackgroundResource(backgroundResource);

        if (!Board[clickedPosition.getX()][clickedPosition.getY()].getFigures().isWhite()) {
            revenge_menu.setRotation(180);
        }
        dialog.show();
    }
    public void onRevenge(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClose(View view){
        //onBackPressed();
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}