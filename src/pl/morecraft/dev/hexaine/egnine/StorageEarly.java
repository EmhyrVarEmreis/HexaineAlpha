package pl.morecraft.dev.hexaine.egnine;

import java.awt.*;
import java.util.*;

public class StorageEarly implements HexaMappable {

    private HashMap<Point, Hexagon> map;
    private int width;
    private int height;
    private Set<Color> colors;

    public StorageEarly() {
        this.map = new HashMap<>();
        this.colors = new HashSet<>();
    }

    @Override
    public Hexagon getElement( Point p ) {
        return this.map.get( p );
    }

    @Override
    public Hexagon getElement( int q, int r ) {
        return this.map.get( new Point( q, r ) );
    }

    @Override
    public Hexagon getElement( int x, int y, int z ) {
        return this.map.get( new Point( x, z ) );
    }

    @Override
    public Hexagon putElement( Point p, Hexagon e ) {
        this.width = ( p.y > this.width - 1 ) ? p.y + 1 : this.width;
        this.height = ( p.x > this.height - 1 ) ? p.x + 1 : this.height;
        return this.map.put( p, e );
    }

    @Override
    public Hexagon putElement( int q, int r, Hexagon e ) {
        this.width = ( r > this.width - 1 ) ? r + 1 : this.width;
        this.height = ( q > this.height - 1 ) ? q + 1 : this.height;
        return this.map.put( new Point( q, r ), e );
    }

    @Override
    public Hexagon putElement( int x, int y, int z, Hexagon e ) {
        this.width = ( z > this.width - 1 ) ? z + 1 : this.width;
        this.height = ( x > this.height - 1 ) ? x + 1 : this.height;
        return this.map.put( new Point( x, z ), e );
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    public void setWidth( int width ) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    @Override
    public String toString() {
        String s = "";
        int i = 0;
        for ( Hexagon h : this.map.values() ) {
            i++;
            s += h.toString();
            if ( i % this.width == 0 ) {
                s += "\n";
            }
        }
        return s;
    }

    @Override
    public Iterator<Hexagon> getIterator() {
        return this.map.values().iterator();
    }

    @Override
    public Collection<Hexagon> getValues() {
        return this.map.values();
    }

    public Color getAndSetNextColor() {
        Color c = this.next666Color();
        this.colors.add( c );
        return c;
    }

    public Color next666Color() {
        Color c;
        do {
            c = this.randomColor();
        } while ( this.colors.contains( c ) );
        return c;
    }

    private Color randomColor() {
        Random rand = new Random();
        return new Color( rand.nextFloat(), rand.nextFloat(), rand.nextFloat() );
    }

}
