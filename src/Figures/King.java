package Figures;

import ChessBoard.ChessBoard;
import ChessBoard.Player;
import java.util.ArrayList;

public class King extends Figure {
    //Конструктор: инициализирующий фигуру КОРОЛЬ
    public King(ChessBoard board, Player player) {
        super("king", board, player);
    }

    /*
     * Все возможные перемещения для этой фигуры в текущей координате
     *   (2) (3) (4)
     *      @ @ @           K: король
     * (1)  @ K @  (5)      @: возможные перемещения
     *      @ @ @
     *   (6) (7) (8)
     */
    public ArrayList<Coordinate> getPossibleMoveCoordinate() {
        int current_x_coord = this.x_coordinate;
        int current_y_coord = this.y_coordinate;
        ChessBoard board = this.board;
        ArrayList<Coordinate> coords = new ArrayList<>();

        //добавить в coords, если координаты действительны
        //1
        step(coords, current_x_coord - 1, current_y_coord);

        //2
        step(coords, current_x_coord - 1, current_y_coord + 1);

        //3
        step(coords, current_x_coord, current_y_coord + 1);

        //4
        step(coords, current_x_coord + 1, current_y_coord + 1);

        //5
        step(coords, current_x_coord + 1, current_y_coord);

        //6
        step(coords, current_x_coord - 1, current_y_coord - 1);

        //7
        step(coords, current_x_coord, current_y_coord - 1);

        //8
        step(coords, current_x_coord + 1, current_y_coord - 1);

        return coords;
    }

    /*
     * Проверить, находится ли король в шахе
     * Вернуть значение true, если король под шахом; в противном случае - false
     */
    public boolean isInCheck() {
        ChessBoard board = this.board;
        ArrayList<Figure> opponent_figures;

        //получить фигуры противника
        if (this.player == Player.WHITE)
            opponent_figures = this.board.getBlack_figures();
        else
            opponent_figures = this.board.getWhite_figures();
        for (Figure f : opponent_figures) {  //получить фигуру противника
            if (f.x_coordinate == -1 || f.y_coordinate == -1)
                continue;
            ArrayList<Coordinate> coords = f.getPossibleMoveCoordinate();
            for (Coordinate coord : coords) {
                if (coord.getX() == this.x_coordinate && coord.getY() == this.y_coordinate)
                    return true;
            }
        }
        return false;
    }
}