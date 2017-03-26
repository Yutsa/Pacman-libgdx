package com.univ_lorraine.pacman.model;

/**
 * @author Édouard WILLISSECK
 */

public class Vector2D {
    public int x;
    public int y;

    public Vector2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D position) {
        setX(position.getX());
        setY(position.getY());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean equals(Vector2D o) {
        return x == o.x && y == o.y;
    }
}
