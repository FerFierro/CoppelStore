package mx.com.devare.coppelstore.controlador.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mx.com.devare.coppelstore.R;
import mx.com.devare.coppelstore.modelo.DBSourceData;
import mx.com.devare.coppelstore.modelo.Producto;

public class MainActivity extends AppCompatActivity {
    ListView lista_productos;
    ArrayAdapter mArrayAdapter;

    DBSourceData mDbSourceData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbSourceData=new DBSourceData(this);
        inicializarComponentesUI();
        insertarProductos();
        obtenerProductos();
    }

    private void inicializarComponentesUI() {
        lista_productos=(ListView) findViewById(R.id.lista_productos);
    }

    private void insertarProductos() {
        List<Producto> mProductoList=new ArrayList<>();

        mProductoList.add(new Producto("1","urlImagen","Lap-top","8900","450","HP"));
        mProductoList.add(new Producto("2","urlImagen","Lap-top","8900","450","HP"));
        mProductoList.add(new Producto("3","urlImagen","Lap-top","8900","450","HP"));
        mProductoList.add(new Producto("4","urlImagen","Lap-top","8900","450","HP"));
        mProductoList.add(new Producto("5","urlImagen","Lap-top","8900","450","HP"));
        generarInsert(mProductoList);
    }

    private void generarInsert(List<Producto> mProductoList) {

        for (Producto mProducto:mProductoList) {
            mDbSourceData.insertarProductos(mProducto);
        }
    }

    private void obtenerProductos() {
       ArrayList<String> mProductoArrayList=new ArrayList<>();
        for (Producto mProducto: mDbSourceData.obtenerProductos()) {

            String s=
                    mProducto.getId()+"\n"
                    +mProducto.getNombre()+"\n"
                    +mProducto.getPrecio()+"\n"
                    +mProducto.getStock()+"\n"
                    +mProducto.getProveedor()+"\n\n";
            mProductoArrayList.add(s);

        }

        mArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mProductoArrayList);
        lista_productos.setAdapter(mArrayAdapter);
    }

}
