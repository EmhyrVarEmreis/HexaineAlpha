package pl.morecraft.dev.hexaine.gui;

import pl.morecraft.dev.hexaine.egnine.HexaMappable;
import pl.morecraft.dev.hexaine.egnine.HexaMath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

public class Drawer extends JComponent implements MouseMotionListener {

    /**
     *
     */
    private static final long serialVersionUID = -1783966337999156222L;

    public static int DRAW_MODE = 0;
    public static int DRAW_SIZE = 52;
    public static double DRAW_INC = 1.5;
    public static Point startPoint = new Point( 0, 0 );
    public static int DELTA = 0;
    private StatusBar bar = null;
    private Image imageBuffer = null;
    private boolean paintColorMap = false;

    private HexaMappable map;

    public Drawer( HexaMappable map, StatusBar bar ) {
        super();

        Drawer.DELTA = (int) ( Drawer.DRAW_SIZE / 2.0 * Math.sqrt( 3.0 ) );

        this.map = map;
        this.bar = bar;
        this.setPreferredSize( new Dimension( map.getWidth() * Drawer.DELTA * 2 + Drawer.DELTA + 1, map.getHeight() * Drawer.DRAW_SIZE ) );
        this.setSize( this.getPreferredSize() );

        // this.imageBuffer = this.getOptimizedImage( this.getWidth(), this.getHeight(), Transparency.OPAQUE );
        this.imageBuffer = this.toCompatibleImage( new BufferedImage( this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB ) );
        // this.imageBuffer = new BufferedImage( this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB );

        this.addMouseMotionListener( this );

        DraverLib.repaintImageA( this.imageBuffer.getGraphics(), map, Drawer.DRAW_SIZE, Drawer.DRAW_INC, this.paintColorMap, this.imageBuffer.getHeight( null ),
                this.imageBuffer.getWidth( null ) );

        this.bar.setFPS( this.measureFPS( 250 ) );

    }

    @Override
    public void paintComponent( Graphics graphics ) {
        Graphics2D g = (Graphics2D) graphics;
        g.drawImage( this.imageBuffer, 0, 0, null );
    }

    @Override
    public void mouseDragged( MouseEvent e ) {

    }

    @Override
    public void mouseMoved( MouseEvent e ) {
        this.bar.setPosX( e.getX() );
        this.bar.setPosY( e.getY() );

        double[] p = DraverLib.posToHexPosQR( e.getPoint(), Drawer.DRAW_SIZE );
        double[] a = HexaMath.hex_round( new double[]{
                p[0], p[1]
        } );

        this.bar.setCurrent( this.map.getElement( (int) a[0], (int) a[1] ) );

        this.bar.repaint();

    }

    private BufferedImage getOptimizedImage( int width, int height, int transparencyMode ) {
        GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage new_image = gfx_config.createCompatibleImage( width, height/*
                                                                                 * ,
																				 * transparencyMode
																				 */ );
        Graphics2D g2d = (Graphics2D) new_image.getGraphics();
        g2d.dispose();

        return new_image;
    }

    @SuppressWarnings( "unused" )
    private BufferedImage toCompatibleImage( BufferedImage image ) {
        // obtain the current system graphical settings
        GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

		/*
         * if image is already compatible and optimized for current system
		 * settings, simply return it
		 */
        if ( image.getColorModel().equals( gfx_config.getColorModel() ) ) {
            return image;
        }

        // image is not optimized, so create a new image that is
        BufferedImage new_image = gfx_config.createCompatibleImage( image.getWidth(), image.getHeight(), image.getTransparency() );

        // get the graphics context of the new image to draw the old image on
        Graphics2D g2d = (Graphics2D) new_image.getGraphics();

        // actually draw the image and dispose of context no longer needed
        g2d.drawImage( image, 0, 0, null );
        g2d.dispose();

        // return the new optimized image
        return new_image;
    }

    public double measureFPS( int n ) {
        int n2 = n;
        Instant start = Instant.now();
        while ( --n2 != 0 ) {
            DraverLib.repaintImageA( this.imageBuffer.getGraphics(), this.map, Drawer.DRAW_SIZE, Drawer.DRAW_INC, this.paintColorMap, this.imageBuffer.getHeight( null ),
                    this.imageBuffer.getWidth( null ) );
        }
        Instant end = Instant.now();
        return 1.0 / ( Duration.between( start, end ).toNanos() / 1000000000.0 / n );
    }
}
