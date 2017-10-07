package mx.com.devare.coppelstore.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBSourceData {
    DBHelper mDbHelper;
    SQLiteDatabase mSqLiteDatabase;

    public DBSourceData(Context mContext) {
        mDbHelper=new DBHelper(mContext);
        mSqLiteDatabase=mDbHelper.getWritableDatabase();
    }

    public void insertarProductos(Producto mProducto){
        ContentValues mContentValues;
        mContentValues=new ContentValues();
        mContentValues.put(DBContrato.Producto_Data.ID_COLUMN,mProducto.getId());
        mContentValues.put(DBContrato.Producto_Data.URL_IMAGEN,mProducto.getUriImagen());
        mContentValues.put(DBContrato.Producto_Data.NOMBRE_COLUMN,mProducto.getNombre());
        mContentValues.put(DBContrato.Producto_Data.PRECIO_COLUMN,mProducto.getPrecio());
        mContentValues.put(DBContrato.Producto_Data.STOCK_COLUMNS,mProducto.getStock());
        mContentValues.put(DBContrato.Producto_Data.PROVEEDOR_COLUMNS,mProducto.getProveedor());
        mSqLiteDatabase.insert(DBContrato.Tablas.PRODUCTO_TABLE,null,mContentValues);

    }

    public List<Producto> obtenerProductos(){
        List<Producto> mProductoList=new ArrayList<>();

        Cursor mCursor= mSqLiteDatabase.rawQuery("SELECT * FROM " + DBContrato.Tablas.PRODUCTO_TABLE, null);

        int idIndex=mCursor.getColumnIndex(DBContrato.Producto_Data.ID_COLUMN);
        int urlImagenIndex=mCursor.getColumnIndex(DBContrato.Producto_Data.URL_IMAGEN);
        int nombreIndex=mCursor.getColumnIndex(DBContrato.Producto_Data.NOMBRE_COLUMN);
        int precioIndex=mCursor.getColumnIndex(DBContrato.Producto_Data.PRECIO_COLUMN);
        int stockIndex=mCursor.getColumnIndex(DBContrato.Producto_Data.STOCK_COLUMNS);
        int proveedorIndex=mCursor.getColumnIndex(DBContrato.Producto_Data.PROVEEDOR_COLUMNS);

        for (mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
            Producto mProducto=new Producto();
            mProducto.setId(mCursor.getString(idIndex));
            mProducto.setUriImagen(mCursor.getString(urlImagenIndex));
            mProducto.setNombre(mCursor.getString(nombreIndex));
            mProducto.setPrecio(mCursor.getString(precioIndex));
            mProducto.setStock(mCursor.getString(stockIndex));
            mProducto.setProveedor(mCursor.getString(proveedorIndex));
            mProductoList.add(mProducto);
        }

        return mProductoList;
    }
}
