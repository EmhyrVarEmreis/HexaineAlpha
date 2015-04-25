package pl.morecraft.dev.hexaine.egnine;

import java.awt.*;

public class Hexagon {

    private int x;
    private int y;
    private int z;
    private Color color;

    public Hexagon( int q, int r ) {
        super();
        this.x = q;
        this.y = -q - r;
        this.z = r;
    }

    public Hexagon( int x, int y, int z ) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return this.x;
    }

    public void setX( int x ) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY( int y ) {
        this.y = y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ( int z ) {
        this.z = z;
    }

    public int getQ() {
        return this.x;
    }

    public void setQ( int q ) {
        this.x = q;
    }

    public int getR() {
        return this.z;
    }

    public void setR( int r ) {
        this.z = r;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor( Color color ) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "[" + this.x + "," + this.z + "]";
        // return "[" + this.x + "," + this.y + "," + this.z + "]";
    }

}
