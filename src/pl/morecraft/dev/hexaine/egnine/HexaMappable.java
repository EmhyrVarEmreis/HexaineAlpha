package pl.morecraft.dev.hexaine.egnine;

import java.awt.*;
import java.util.Collection;
import java.util.Iterator;

public interface HexaMappable {

    public Iterator<Hexagon> getIterator();

    public int getWidth();

    public int getHeight();

    public Hexagon getElement( Point p );

    public Hexagon getElement( int q, int r );

    public Hexagon getElement( int x, int y, int z );

    public Hexagon putElement( Point p, Hexagon e );

    public Hexagon putElement( int q, int r, Hexagon e );

    public Hexagon putElement( int x, int y, int z, Hexagon e );

    public Collection<Hexagon> getValues();

}
