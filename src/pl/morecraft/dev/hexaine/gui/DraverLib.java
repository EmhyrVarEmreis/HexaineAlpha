package pl.morecraft.dev.hexaine.gui;

import pl.morecraft.dev.hexaine.egnine.HexaMappable;
import pl.morecraft.dev.hexaine.egnine.HexaMath;
import pl.morecraft.dev.hexaine.egnine.Hexagon;

import java.awt.*;
import java.util.Iterator;

public class DraverLib {

    private static int DELTA_TMP = 0;
    private static int[] ITV = new int[]{
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };


    public static void repaintImageA( Graphics graphics, HexaMappable map, int drawSize, double drawInc, boolean paintColorMap, int height, int width ) {

        DraverLib.DELTA_TMP = (int) ( drawSize / 2.0 * Math.sqrt( 3.0 ) );

        Graphics2D g = (Graphics2D) graphics;

        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );
        g.setColor( Color.BLACK );

        if ( paintColorMap ) {
            DraverLib.paintColours( graphics, width, height, map, drawSize );
        }

        g.setColor( Color.BLACK );

        Iterator<Hexagon> it = map.getIterator();
        Hexagon hex;

        while ( it.hasNext() ) {
            hex = it.next();
            int[] px = new int[]{
                    0, DraverLib.DELTA_TMP, DraverLib.DELTA_TMP * 2, DraverLib.DELTA_TMP * 2, DraverLib.DELTA_TMP, 0
            };
            int[] py = new int[]{
                    drawSize / 4, 0, drawSize / 4, (int) ( drawSize / 4 * 3 * drawInc ), (int) ( drawSize + ( drawSize / 4 * 3 * ( drawInc - 1.0 ) ) ), (int) ( drawSize / 4 * 3 * drawInc )
            };
            Polygon p = DraverLib.transformToPolygon( px, py, 6, hex.getQ(), hex.getR(), DraverLib.DELTA_TMP, drawSize, drawInc );
            g.drawString( hex.getQ() + "," + hex.getR(), p.xpoints[0] + 6, p.ypoints[0] + 12 );
            g.drawPolygon( p );
        }

    }

    public static void repaintImageB( Graphics graphics, HexaMappable map, int drawSize, double drawInc, boolean paintColorMap, int height, int width ) {

		/*
         * ITV[0] = H
		 * ITV[1] = H/2
		 * ITV[2] = H/4
		 * ITV[3] = D = DELTA
		 * ITV[4] = D/2
		 */

        if ( DraverLib.ITV[0] == 0 ) {
            DraverLib.ITV[0] = drawSize;
            DraverLib.ITV[1] = DraverLib.ITV[0] / 2;
            DraverLib.ITV[2] = DraverLib.ITV[0] / 4;
            DraverLib.ITV[3] = (int) ( drawSize / 2.0 * Math.sqrt( 3.0 ) );
            DraverLib.ITV[4] = DraverLib.ITV[3] / 2;
        }

        Graphics2D g = (Graphics2D) graphics;

        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );
        g.setColor( Color.BLACK );

        if ( paintColorMap ) {
            DraverLib.paintColours( graphics, width, height, map, drawSize );
        }

        g.setColor( Color.BLACK );

        //for ( Hexagon hex : map.getValues() ) {
        //    ;
        // }

    }

    public static void paintColours( Graphics graphics, int width, int height, HexaMappable map, int drawSize ) {
        Graphics2D g = (Graphics2D) graphics;
        for ( int x = 0; x < width; x++ ) {
            for ( int y = 0; y < height; y++ ) {
                double[] p = DraverLib.posToHexPosQR( new Point( x, y ), drawSize );
                double[] a = HexaMath.hex_round( new double[]{
                        p[0], p[1]
                } );
                Hexagon h = map.getElement( (int) a[0], (int) a[1] );
                if ( h != null ) {
                    g.setColor( h.getColor() );
                    g.drawLine( x, y, x, y );
                }
            }
        }
    }

    public static double[] posToHexPosQR( Point e, int drawSize ) {
        e.y -= ( drawSize - 30 );
        e.x -= ( drawSize - 10 );

        e.y *= 2;
        e.x *= 1.019;

        double q = ( e.x * Math.sqrt( 3.0 ) / 3.0 - e.y / 3.0 ) / drawSize;
        double r = e.y * 2.0 / 3.0 / drawSize;
        return new double[]{
                q, r
        };
    }

    private static Polygon transformToPolygon( int[] px, int[] py, int n, int vx, int vy, int delta, int drawSize, double drawInc ) {

        for ( int i = 0; i < n; i++ ) {
            px[i] += vx * 2 * delta + vy * delta;
            py[i] += vy * drawSize / 4 * 3 * drawInc;
        }

        return new Polygon( px, py, 6 );
    }

}
