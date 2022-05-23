package Figures;

//Класс координат. Этот класс используется для хранения координат x, y для фигур
public class Coordinate {
    private int x, y;

    public Coordinate() {
        this.x = -1;
        this.y = -1;
    }

    //Конструктор: установить х и у
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
}