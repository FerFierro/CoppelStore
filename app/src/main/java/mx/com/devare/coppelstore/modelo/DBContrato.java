package mx.com.devare.coppelstore.modelo;

import android.provider.BaseColumns;

/**
 * Created by Angel on 30/09/2017.
 */

public class DBContrato {

    interface Tablas{
        String PRODUCTO_TABLE="producto";
    }

    interface Producto_Columnas{
        String ID_COLUMN="id";
        String NOMBRE_COLUMN="nombre";
        String URL_IMAGEN="urlImagen";
        String PRECIO_COLUMN="precio";
        String STOCK_COLUMNS="stock";
        String PROVEEDOR_COLUMNS="proveedor";
    }

    public static class Producto_Data implements Producto_Columnas,BaseColumns{

    }
}
