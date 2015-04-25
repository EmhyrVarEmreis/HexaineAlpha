package pl.morecraft.dev.hexaine.egnine;

public class HexaMath {

    public static double[] cube_round( double[] h ) {
        int rx = (int) Math.round( h[0] );
        int ry = (int) Math.round( h[1] );
        int rz = (int) Math.round( h[2] );

        double x_diff = Math.abs( rx - h[0] );
        double y_diff = Math.abs( ry - h[1] );
        double z_diff = Math.abs( rz - h[2] );

        if ( x_diff > y_diff && x_diff > z_diff )
            rx = -ry - rz;
        else if ( y_diff > z_diff )
            ry = -rx - rz;
        else
            rz = -rx - ry;

        return new double[]{
                rx, ry, rz
        };
    }

    public static double[] hex_round( double[] h ) {
        return cube_to_hex( cube_round( hex_to_cube( h ) ) );
    }

    public static double[] hex_to_cube( double[] h ) {
        return new double[]{
                h[0], -h[0] - h[1], h[1]
        };
    }

    public static double[] cube_to_hex( double[] h ) {
        return new double[]{
                h[0], h[2]
        };
    }
}
