package Figures;

import ChessBoard.ChessBoard;
import ChessBoard.Player;
import java.util.ArrayList;

public class Rook extends Figure {
    //Конструктор: инициализирующий фигуру ЛАДЬЯ
    public Rook(ChessBoard board, Player player){
        super("rook", board, player);
    }

    /*
     * Все возможные перемещения для этой фигуры в текущей координате
     *           (2)
     *            @
     *            @            R: ладья
     *            @            @: возможные перемещения
     *  (1) @ @ @ R @ @ @ (3)
     *            @
     *            @
     *            @
     *           (4)
     */

    public ArrayList<Coordinate> getPossibleMoveCoordinate() {
        int current_x_coord = this.x_coordinate;
        int current_y_coord = this.y_coordinate;
        ChessBoard board = this.getChessBoard();
        ArrayList<Coordinate> coords = new ArrayList<>();

        int i;
        //добавить в coords, если координаты действительны; возвращает значение true, если на пути другая фигура
        //1
        for(i = current_x_coord - 1; i >= 0; i--){
            if(step(coords, i, current_y_coord))
                break;
        }
        //2
        for(i = current_y_coord + 1 ; i < board.getHeight(); i++){
            if(step(coords, current_x_coord, i))
                break;
        }
        //3
        for(i = current_x_coord + 1; i < board.getWidth(); i++){
            if(step(coords, i, current_y_coord))
                break;
        }
        //4
        for(i = current_y_coord - 1; i >= 0; i--){
            if(step(coords, current_x_coord, i))
                break;
        }
        return coords;
    }
}