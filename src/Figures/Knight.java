package Figures;

import ChessBoard.ChessBoard;
import ChessBoard.Player;

import java.util.ArrayList;

public class Knight extends Figure {
    //Конструктор: инициализирующий фигуру КОНЬ
    public Knight(ChessBoard board, Player player) {
        super("knight", board, player);
    }

    /**
     *  Все возможные перемещения для этой фигуры в текущей координате
     *
     *      (2) @     @ (3)
     *    (1) @         @ (4)    K: конь
     *             P             @: возможные перемещения (траектория "Г")
     *    (5) @         @ (8)
     *      (6) @     @ (7)
     */
    public ArrayList<Coordinate> getPossibleMoveCoordinate() {
        ChessBoard board = this.getChessBoard();
        ArrayList<Coordinate> coords = new ArrayList<>();
        int x, y;

        //добавить в coords, если фигура может переместиться в эти координаты
        //1
        x = this.x_coordinate - 2;
        y = this.y_coordinate + 1;
        if(x >= 0 && y < board.getHeight())
            step(coords, x, y);

        //2
        x = this.x_coordinate - 1;
        y = this.y_coordinate + 2;
        if(x >= 0 && y < board.getHeight())
            step(coords, x, y);

        //3
        x = this.x_coordinate + 1;
        y = this.y_coordinate + 2;
        if(x < board.getWidth() && y < board.getHeight())
            step(coords, x, y);

        //4
        x = this.x_coordinate + 2;
        y = this.y_coordinate + 1;
        if(x < board.getWidth() && y < board.getHeight())
            step(coords, x, y);

        //5
        x = this.x_coordinate - 2;
        y = this.y_coordinate - 1;
        if(x >= 0 && y >= 0 )
            step(coords, x, y);

        //6
        x = this.x_coordinate - 1;
        y = this.y_coordinate - 2;
        if(x >= 0 && y >= 0)
            step(coords, x, y);

        //7
        x = this.x_coordinate + 1;
        y = this.y_coordinate - 2;
        if(x < board.getWidth() && y >= 0){
            step(coords, x, y);
        }
        //8
        x = this.x_coordinate + 2;
        y = this.y_coordinate - 1;
        if(x < board.getWidth() && y >= 0)
            step(coords, x, y);

        return coords;
    }
}