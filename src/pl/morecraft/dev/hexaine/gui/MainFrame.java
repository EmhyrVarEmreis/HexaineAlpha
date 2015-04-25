package pl.morecraft.dev.hexaine.gui;

import pl.morecraft.dev.hexaine.egnine.HexaMappable;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings( "FieldCanBeLocal" )
public class MainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -9055604820803518818L;
    private JScrollPane jSP;
    private StatusBar bar;

    public MainFrame( HexaMappable map ) {
        this.setTitle( "Hexaine Visualisator" );
        this.setSize( new Dimension( 1366, 768 ) );
        this.setLayout( new BorderLayout() );
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        this.setLocation( ( Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width ) / 2,
                ( Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height ) / 2 );

        this.bar = new StatusBar();

        this.jSP = new JScrollPane( new Drawer( map, this.bar ) );

        this.add( this.jSP, BorderLayout.CENTER );
        this.add( this.bar, BorderLayout.PAGE_END );

    }
}
