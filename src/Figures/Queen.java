package Figures;

import ChessBoard.ChessBoard;
import ChessBoard.Player;

import java.util.ArrayList;

public class Queen extends Figure {
    //Конструктор: инициализирующий фигуру ФЕРЗЬ
    public Queen(ChessBoard board, Player player){
        super("queen", board, player);
    }

    /*
     * Все возможные перемещения для этой фигуры в текущей координате
     *      (2)  (3)  (4)
     *        @   @   @
     *         @  @  @         Q: ферзь
     *          @ @ @          @: возможные перемещения
     *   (1)  @ @ Q @ @  (5)
     *          @ @ @
     *         @  @  @
     *        @   @   @
     *      (6)  (7)  (8)
     */

    public ArrayList<Coordinate> getPossibleMoveCoordinate() {
        int current_x_coord = this.x_coordinate;
        int current_y_coord = this.y_coordinate;
        ChessBoard board = this.getChessBoard();
        ArrayList<Coordinate> coords = new ArrayList<>();

        int i, j;
        //добавить в coords, если координаты действительны; возвращает значение true, если на пути другая фигура
        //1
        for(i = current_x_coord - 1; i >= 0; i--){
            if(step(coords, i, current_y_coord))
                break;
        }
        //2
        for(i = current_x_coord - 1, j = current_y_coord + 1; i >= 0 && j < board.getHeight(); i--, j++){
            if(step(coords, i, j))
                break;
        }
        //3
        for(i = current_y_coord + 1 ; i < board.getHeight(); i++){
            if(step(coords, current_x_coord, i))
                break;
        }
        //4
        for(i = current_x_coord + 1, j = current_y_coord + 1; i < board.getWidth() && j < board.getHeight(); i++, j++){
            if(step(coords, i, j))
                break;
        }
        //5
        for(i = current_x_coord + 1; i < board.getWidth(); i++){
            if(step(coords, i, current_y_coord))
                break;
        }
        //6
        for(i = current_x_coord - 1, j = current_y_coord - 1; i >= 0 && j >= 0; i--, j--){
            if(step(coords, i, j))
                break;
        }
        //7
        for(i = current_y_coord - 1; i >= 0; i--){
            if(step(coords, current_x_coord, i))
                break;
        }
        //8
        for(i = current_x_coord + 1, j = current_y_coord - 1; i < board.getWidth() && j >= 0; i++, j--){
            if(step(coords, i, j))
                break;
        }
        return  coords;
    }
}