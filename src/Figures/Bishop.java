package Figures;

import ChessBoard.ChessBoard;
import ChessBoard.Player;
import java.util.ArrayList;

public class Bishop extends Figure {
    //Конструктор: инициализирующий фигуру СЛОН
    public Bishop(ChessBoard board, Player player) {
        super("bishop", board, player);
    }

    /*
     * Все возможные перемещения для этой фигуры в текущей координате
     * (1)      (2)
     *    @     @      B: слон
     *     @   @       @: возможные перемещения
     *      @ @
     *       P
     *      @ @
     *     @   @
     *    @     @
     * (3)      (4)
     */
    public ArrayList<Coordinate> getPossibleMoveCoordinate() {
        int current_x_coord = this.x_coordinate;
        int current_y_coord = this.y_coordinate;
        ChessBoard board = this.board;
        ArrayList<Coordinate> coords = new ArrayList<>();

        int i, j;
        //добавить в coords, если координаты действительны; возвращает значение true, если на пути стоит фигура
        //1
        for (i = current_x_coord - 1, j = current_y_coord + 1; i >= 0 && j < board.getHeight(); i--, j++) {
            if (step(coords, i, j))
                break;
        }
        //2
        for (i = current_x_coord + 1, j = current_y_coord + 1; i < board.getWidth() && j < board.getHeight(); i++, j++) {
            if (step(coords, i, j))
                break;
        }
        //3
        for (i = current_x_coord - 1, j = current_y_coord - 1; i >= 0 && j >= 0; i--, j--) {
            if (step(coords, i, j))
                break;
        }
        //4
        for (i = current_x_coord + 1, j = current_y_coord - 1; i < board.getWidth() && j >= 0; i++, j--) {
            if (step(coords, i, j))
                break;
        }
        return coords;
    }
}