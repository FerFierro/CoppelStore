package mx.com.devare.coppelstore.modelo;

/**
 * Created by Angel on 30/09/2017.
 */

public class DBScript {

    public static final String NOMBRE_BASE_DATOS="coppelstore.db";
    public static final int VERSION_BASE_DATOS=1;


    public static final String SQL_SCRIPT_PRODUCTO=
            "CREATE TABLE "+DBContrato.Tablas.PRODUCTO_TABLE
                +"( "
                     + DBContrato.Producto_Data._ID                + " PRIMARY KEY,"
                     + DBContrato.Producto_Data.ID_COLUMN          + " VARCHAR(10) NOT NULL,"
                     + DBContrato.Producto_Data.URL_IMAGEN         + " VARCHAR(200) NOT NULL,"
                     + DBContrato.Producto_Data.NOMBRE_COLUMN      + " VARCHAR(50) NOT NULL,"
                     + DBContrato.Producto_Data.PRECIO_COLUMN      + " VARCHAR(10) NOT NULL,"
                     + DBContrato.Producto_Data.STOCK_COLUMNS      + " VARCHAR(10)NOT NULL,"
                     + DBContrato.Producto_Data.PROVEEDOR_COLUMNS  + " VARCHAR(10) NOT NULL"
            +" )";

}
