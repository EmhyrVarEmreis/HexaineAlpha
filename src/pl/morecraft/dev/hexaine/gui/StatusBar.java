package pl.morecraft.dev.hexaine.gui;

import pl.morecraft.dev.hexaine.egnine.Hexagon;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class StatusBar extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 4435121648006240493L;
    private double FPS;
    private int posX;
    private int posY;
    private Hexagon current;

    public StatusBar() {
        super();

        this.setSize( new Dimension( 200, 20 ) );
        this.setPreferredSize( this.getSize() );

        this.posX = 0;
        this.posY = 0;
        this.current = null;
        this.FPS = 0;

    }

    public void setFPS( double fPS ) {
        this.FPS = fPS;
    }

    public void setPosX( int posX ) {
        this.posX = posX;
    }

    public void setPosY( int posY ) {
        this.posY = posY;
    }

    public void setCurrent( Hexagon current ) {
        this.current = current;
    }

    @Override
    public void paintComponent( Graphics graph ) {
        super.paintComponent( graph );
        Graphics2D g = (Graphics2D) graph;

        g.drawString( "Cursor (X,Y): (" + this.posX + "," + this.posY + ") ", 5, 13 );
        g.drawString( "Hex: (Q,R): (" + ( ( this.current == null ) ? "NONE" : this.current.getQ() + "," + this.current.getR() ) + ") ", 150, 13 );
        DecimalFormat df = new DecimalFormat( "#.##" );
        g.drawString( "FPS: " + df.format( this.FPS ), 300, 13 );

    }

}
