package pl.morecraft.dev.hexaine.main;

import pl.morecraft.dev.hexaine.egnine.Hexagon;
import pl.morecraft.dev.hexaine.egnine.StorageEarly;
import pl.morecraft.dev.hexaine.gui.MainFrame;

public class Main {

    public static void main( String[] args ) {

        // Generate some elements

        StorageEarly map = new StorageEarly();

        Main.makeRectangle( 25, 25, map );

        System.out.println( map );

        MainFrame app = new MainFrame( map );
        app.setVisible( true );

    }

    public static void makeRectangle( int width, int height, StorageEarly map ) {
        int q;
        int r;
        for ( int i = 0; i < height; i++ ) {
            for ( int j = 0; j < width; j++ ) {
                q = j - i / 2;
                r = i;
                Hexagon h = new Hexagon( q, r );
                h.setColor( map.getAndSetNextColor() );
                map.putElement( q, r, h );
            }
        }
    }

}
