package Figures;

import ChessBoard.ChessBoard;
import ChessBoard.Player;
import java.util.ArrayList;

public abstract class Figure {
    protected String figure_name; //король, ладья, слон, ферзь, конь, пешка
    protected int x_coordinate, y_coordinate;
    protected Player player;     //white, black
    protected ChessBoard board;

    //Инициализация фигуры
    public Figure(String figure_name, ChessBoard board, Player player) {
        this.figure_name = figure_name;
        this.x_coordinate = -1;    //-1 означает, что фигура еще не стоит на шахматной доске
        this.y_coordinate = -1;
        this.board = board;
        this.player = player;
        this.board.addFigureToList(this);    //Добавить фигуру в список
    }

    /*
     * Установить координаты фигуры на шахматной доске (куда поставлена фигура)
     * Если в указанные координаты можно поставить фигуру - вернуть true, в противном случае false
     */
    public boolean setCoordinate(int x, int y) {
        if (x < 0 || x >= this.board.getWidth() || y < 0 || y >= this.board.getHeight()
                || this.board.getFigureAtCoordinate(x, y) != null) //координата, выходящая за пределы доски
            return false;
        if (this.x_coordinate != -1 && this.y_coordinate != -1)  //фигура сейчас не инициализирована
            this.removeSelf(); //удалить фигуру из текущих координат
        //установить координаты
        this.x_coordinate = x;
        this.y_coordinate = y;
        //сохранить фигуру на доске
        this.board.setFigureAtCoordinate(this, x, y);
        return true;
    }

    //Фигура удаляется с шахматной доски
    public void removeSelf() {
        this.board.removeFigure(this);
        this.x_coordinate = -1;
        this.y_coordinate = -1;
    }

    public ChessBoard getChessBoard() {
        return this.board;
    }

    public void setFigure(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getX_coordinate() {
        return this.x_coordinate;
    }

    public int getY_coordinate() {
        return this.y_coordinate;
    }

    public String getFigure_name() {
        return this.figure_name;
    }

    /*
     * Проверить может ли фигура переместится на указанные координаты
     * Если фигура может переместиться в эти координаты - сохранить эти координаты
     * Если фигура не может двигаться дальше, т.к. на пути к координате стоит другая фигура, которая
     * блокирует путь => вернуть true; в противном случае вернуть false
     */
    public boolean step(ArrayList<Coordinate> coords, int x, int y) {
        if (x < 0 || y < 0 || x >= this.board.getWidth() || y >= this.board.getHeight())
            return false;
        if (this.board.getFigureAtCoordinate(x, y) == null) { //свободная клетка
            coords.add(new Coordinate(x, y));
            return false;
        } else if (this.board.getFigureAtCoordinate(x, y).player != this.player) {  //в клетке - противник
            coords.add(new Coordinate(x, y));
            return true;
        } else
            return true;
    }

    /**
     * Проверить, является ли это самоубийственным шагом
     * После перемещения фигуры в координаты (move_to_x, move_to_y), проверить, находится ли король под шахом.
     * Вернуть true, если этот ход приведет к проверке короля
     */
    public boolean isSuicideMove(int move_to_x, int move_to_y) {
        Player current_player = this.player;
        int current_x_coord = this.x_coordinate;
        int current_y_coord = this.y_coordinate;
        boolean is_suicide = false;

        Figure king = (current_player == Player.WHITE ? this.board.getKing1() : this.board.getKing2());

        Figure remove_figure = this.board.getFigureAtCoordinate(move_to_x, move_to_y);

        ArrayList<Figure> opponent_figures = (current_player == Player.WHITE ?
                this.board.getBlack_figures() : this.board.getWhite_figures()); //получить фигуру противника

        if (remove_figure != null)
            remove_figure.removeSelf();  //временно удалить себя
        if (this.figure_name.equals("pawn"))  //если это пешка, то не хотим менять флаг "первого перемещения"
            ((Pawn) this).setCoordinateWithoutChangingFirstTimeMoveFlag(move_to_x, move_to_y);
        else
            this.setCoordinate(move_to_x, move_to_y); //Переместить фигуру в эту координату;

        for (Figure opponent_figure : opponent_figures) {
            if (opponent_figure.getX_coordinate() == -1 || opponent_figure.getY_coordinate() == -1)
                continue;
            ArrayList<Coordinate> coords = opponent_figure.getPossibleMoveCoordinate();
            for (Coordinate coord : coords) {
                if (coord.getX() == king.getX_coordinate() && coord.getY() == king.getY_coordinate()) {
                    is_suicide = true;
                    break;
                }
            }
        }
        //восстановить удаленную фигуру и поместить
        if (this.figure_name.equals("pawn"))
            ((Pawn) this).setCoordinateWithoutChangingFirstTimeMoveFlag(current_x_coord, current_y_coord);
        else
            this.setCoordinate(current_x_coord, current_y_coord);
        if (remove_figure != null) {
            if (remove_figure.getFigure_name().equals("pawn"))
                ((Pawn) remove_figure).setCoordinateWithoutChangingFirstTimeMoveFlag(move_to_x, move_to_y);
            else
                remove_figure.setCoordinate(move_to_x, move_to_y);
        }
        return is_suicide;
    }

    /*
     * Получить возможные координаты перемещения для этой фигуры
     * Поскольку эта функция реализована в каждом подклассе, она вернет значение null.
     * Возвращает объект ArrayList<Coordinate>, содержащий все возможные координаты перемещения.
     */
    public abstract ArrayList<Coordinate> getPossibleMoveCoordinate();
}