package ChessBoard;

import Figures.*;

import java.util.ArrayList;

public class ChessBoard {
    protected int width, height;    //ширина, высота
    private Figure tiles[][];   //массив, предназначен для хранения фигур
    protected Figure king1; //король для игрока1 - White
    protected Figure king2; //король для игрока2 - Black
    private ArrayList<Figure> white_figures; //список, в котором хранятся все белые фигуры
    private ArrayList<Figure> black_figures; //список, в котором хранятся все черные фигуры
    protected int turns;  //ход

    /**
     * Построить шахматную доску с учетом ширины и высоты
     *
     * Система координат
     *  +
     *  |
     *  |
     *  |
     * (0, 0) ------>  +
     */
    public ChessBoard(int width, int height) {
        //установить ширину и высоту шахматной доски
        this.width = width;
        this.height = height;

        //инициализировать массив для хранения фигур
        this.tiles = new Figure[height][];
        for (int i = 0; i < height; i++) {
            this.tiles[i] = new Figure[width];
        }

        //инициализировать белые фигуры и черные фигуры
        this.white_figures = new ArrayList<>();
        this.black_figures = new ArrayList<>();

        //инициализировать другие переменные
        this.king1 = null;
        this.king2 = null;
        this.turns = 0; //если четное число - ходят белые, 0 - черные
    }


    //Добавить белую или черную фигуру в список
    public void addFigureToList(Figure f) {
        if (f.getPlayer() == Player.WHITE) {
            this.white_figures.add(f);
        } else {
            this.black_figures.add(f);
        }
    }

    //Вернуть список всех белых фигур
    public ArrayList<Figure> getWhite_figures() {
        return this.white_figures;
    }

    //Вернуть список всех черных фигур
    public ArrayList<Figure> getBlack_figures() {
        return this.black_figures;
    }


    //Получить белого короля
    public Figure getKing1() {
        return this.king1;
    }

    //Установить белого короля
    public void setKing1(Figure f) {
        this.king1 = f;
    }

    //Получить черного короля
    public Figure getKing2() {
        return this.king2;
    }

    // Установить черного короля
    public void setKing2(Figure f) {
        this.king2 = f;
    }

    /*
     * Вернуть фигуру по заданным координатам
     * если в этих координатах нет фигуры (или эти координаты недействительны) - вернуть null
     */
    public Figure getFigureAtCoordinate(int x, int y) {
        if (x >= this.width || x < 0 || y >= this.height || y < 0) //за пределами доски
            return null;
        return this.tiles[y][x];
    }

    //Хранит фигуру по координатам (x, y)
    public void setFigureAtCoordinate(Figure f, int x, int y) {
        this.tiles[y][x] = f;
    }

    // Получить ширину шахматной доски
    public int getWidth() {
        return this.width;
    }

    // Получить высоту шахматной доски
    public int getHeight() {
        return this.height;
    }

    // Удалить фигуру с шахматной доски
    public void removeFigure(Figure f) {
        int x = f.getX_coordinate();
        int y = f.getY_coordinate();
        this.tiles[y][x] = null;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getTurns() {
        return this.turns;
    }

    //Ход игрока
    public Player getPlayerForThisTurn() {
        if (this.turns % 2 == 0) {
            return Player.WHITE; //ходят белые
        }
        return Player.BLACK; //ходят черные
    }

    /*
     * Проверить, может ли игрок переместить фигуру
     * Если игрок не может переместить ни одну фигуру, то вернуть значение true
     */
    public boolean playerCannotMove(Player player) {
        ArrayList<Figure> player_figures = ((player == Player.WHITE) ? this.getWhite_figures() : this.getBlack_figures());
        for (Figure f : player_figures) {
            if (f.getX_coordinate() == -1 || f.getY_coordinate() == -1)
                continue;
            ArrayList<Coordinate> coords = f.getPossibleMoveCoordinate();
            for (Coordinate coord : coords) {
                if (f.isSuicideMove(coord.getX(), coord.getY()) == false)
                    return false;
            }
        }
        return true;
    }

    /*
     * Проверить, находится ли король игрока в тупике
     * Если не проверено - проверить, есть ли какой-либо ход
     * Если перемещения нет (т.е. в тупике), вернуть значение true; в противном случае - false.
     */
    public boolean isStalemate(Player player) {
        King king = player == Player.WHITE ? (King) this.king1 : (King) this.king2;
        if (king.isInCheck() == false) {
            if (playerCannotMove(player))
                return true;
            return false;
        } else {
            return false;
        }
    }

    // Создать стандартную шахматную доску 8 x 8
    public void generateStandardBoard() {
        Figure figure;
        // ===============
        //  player1 white
        // ===============
        figure = new Rook(this, Player.WHITE); //a1 white rook
        figure.setCoordinate(0, 0);

        figure = new Knight(this, Player.WHITE); //b1 white knight
        figure.setCoordinate(1, 0);

        figure = new Bishop(this, Player.WHITE); //c1 white bishop
        figure.setCoordinate(2, 0);

        figure = new Queen(this, Player.WHITE); //d1 white queen
        figure.setCoordinate(3, 0);

        figure = new King(this, Player.WHITE);   //e1 white king
        figure.setCoordinate(4, 0);

        figure = new Bishop(this, Player.WHITE); //f1 white bishop
        figure.setCoordinate(5, 0);

        figure = new Knight(this, Player.WHITE); //g1 white knight
        figure.setCoordinate(6, 0);

        figure = new Rook(this, Player.WHITE);   //h1 white rook
        figure.setCoordinate(7, 0);

        for (int x = 0; x < 8; x++) {
            figure = new Pawn(this, Player.WHITE); //a2 - h2 white pawns
            figure.setCoordinate(x, 1);
        }

        // ===============
        //  player2 black
        // ===============
        figure = new Rook(this, Player.BLACK); //a8 black rook
        figure.setCoordinate(0, 7);

        figure = new Knight(this, Player.BLACK); //b8 black knight
        figure.setCoordinate(1, 7);

        figure = new Bishop(this, Player.BLACK); //c8 black bishop
        figure.setCoordinate(2, 7);

        figure = new Queen(this, Player.BLACK); //d8 black queen
        figure.setCoordinate(3, 7);

        figure = new King(this, Player.BLACK);   //e8 black king
        figure.setCoordinate(4, 7);

        figure = new Bishop(this, Player.BLACK); //f8 black bishop
        figure.setCoordinate(5, 7);

        figure = new Knight(this, Player.BLACK); //g8 black knight
        figure.setCoordinate(6, 7);

        figure = new Rook(this, Player.BLACK);   //h8 black rook
        figure.setCoordinate(7, 7);

        for (int y = 0; y < 8; y++) {
            figure = new Pawn(this, Player.BLACK);   //a7 - h7 black pawns
            figure.setCoordinate(1, y);
        }
    }
}