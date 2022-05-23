package Figures;

import ChessBoard.ChessBoard;
import ChessBoard.Player;
import java.util.ArrayList;

public class Pawn extends Figure {
    private boolean first_time_move; //впервые ли пешка делает ход, если да - она может продвинуться на 2 клетки вперед

    //Конструктор: инициализирующий фигуру ПЕШКА
    public Pawn(ChessBoard board, Player player) {
        super("pawn", board, player);
        this.first_time_move = true;
    }

    public boolean isFirstTimeMove(){
        return this.first_time_move;
    }

    public void setFirst_time_move(boolean flag){
        this.first_time_move = flag;
    }

    /*
     * Все возможные перемещения для этой фигуры в текущей координате
     *
     *       @             X @ X         @: возможные перемещения
     *     X @ X             P           P: пешка
     *       P                           X: фигура противника
     */

    public ArrayList<Coordinate> getPossibleMoveCoordinate() {
        int current_x_coord = this.x_coordinate;
        int current_y_coord = this.y_coordinate;
        ChessBoard board = this.getChessBoard();
        ArrayList<Coordinate> coords = new ArrayList<>();

        int possible_move = (this.player == Player.WHITE) ? 1 : -1;  //если фигура белая, то +1, противном случае -1
        if (board.getFigureAtCoordinate(current_x_coord, current_y_coord + possible_move) != null) { //если ячейка впереди занята
            //ничего не делать
        } else
            coords.add(new Coordinate(current_x_coord, current_y_coord + possible_move));  //переместиться на не занятую ячейку

        if (this.first_time_move &&  //если ход первый => переместиться на 2 клетки (если они не заняты)
                board.getFigureAtCoordinate(current_x_coord, current_y_coord + possible_move) == null &&
                board.getFigureAtCoordinate(current_x_coord, current_y_coord + possible_move * 2) == null) {
            coords.add(new Coordinate(current_x_coord, current_y_coord + possible_move * 2));
        }

        if (board.getFigureAtCoordinate(current_x_coord - 1, current_y_coord + possible_move) != null &&
                board.getFigureAtCoordinate(current_x_coord - 1, current_y_coord + possible_move).player != this.player) {  //слева есть фигура противника
            coords.add(new Coordinate(current_x_coord - 1, current_y_coord + possible_move));
        }

        if (board.getFigureAtCoordinate(current_x_coord + 1, current_y_coord + possible_move) != null &&
                board.getFigureAtCoordinate(current_x_coord + 1, current_y_coord + possible_move).player != this.player) {  //справа есть фигура противника
            coords.add(new Coordinate(current_x_coord + 1, current_y_coord + possible_move));
        }
        return coords;
    }

    /**
     * Установить координаты для пешки
     * изменить флаг first_time_move  при необходимости
     * @param x         координаты х, куда поместить пешку
     * @param y         координаты у, куда поместить пешку
     * @return
     */
    public boolean setCoordinate(int x, int y) {
        if (this.x_coordinate == -1 || this.y_coordinate == -1)  //первый ход
            this.first_time_move = true;
        else
            this.first_time_move = false;
        return super.setCoordinate(x, y);
    }

    /**
     * Установить координаты для пешки без изменения флага "первого перемещения"
     * * @param x координата x для размещения фрагмента
     * * @param y координата y для размещения фрагмента
     * * @возврат
     *
     * @param x     координата х для размещения фигуры
     * @param y     координата у для размещения фигуры
     * @return
     */
    public boolean setCoordinateWithoutChangingFirstTimeMoveFlag(int x, int y){
        return super.setCoordinate(x, y);
    }
}