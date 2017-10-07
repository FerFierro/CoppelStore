package mx.com.devare.coppelstore.controlador.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.com.devare.coppelstore.R;
import mx.com.devare.coppelstore.modelo.DBSourceData;
import mx.com.devare.coppelstore.modelo.Producto;
import mx.com.devare.coppelstore.modelo.Utilidades.Constantes;
import mx.com.devare.coppelstore.modelo.volley.SolicitudesJSON;

public class MainActivity extends AppCompatActivity {
    ListView lista_productos;
    ArrayAdapter mArrayAdapter;

    DBSourceData mDbSourceData;
    SolicitudesJSON mSolicitudesJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbSourceData=new DBSourceData(this);
        inicializarComponentesUI();
       obtenerServicioWeb();
        obtenerProductos();
    }

    private void obtenerServicioWeb() {
        mSolicitudesJSON=new SolicitudesJSON(this) {
            @Override
            public void obtenerDatos(JSONObject mJsonObject) {
                try {
                    JSONArray mJsonArray=mJsonObject.getJSONArray("productos");
                    List<Producto> mProductoList =new ArrayList<>();
                    for (int i=0;i<mJsonArray.length();i++){
                        JSONObject objeto2 = mJsonArray.getJSONObject(i);
                        String id=objeto2.getString("idProducto");
                        String nombre=objeto2.getString("nombre");
                        String url_imagen=objeto2.getString("url_imagen");
                        String  precio=objeto2.getString("precio");
                        String stock=objeto2.getString("stock");
                        String proveedor=objeto2.getString("proveedor");
                        mProductoList.add(new Producto(id,url_imagen,nombre,precio,proveedor,stock));

                    }
                    generarInsert(mProductoList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void obtenerError(VolleyError mError) {

            }
        };
        mSolicitudesJSON.solicitudGETJson(Constantes.URL_SW_OBTENER_PRODDUCTOS);

    }

    private void inicializarComponentesUI() {
        lista_productos=(ListView) findViewById(R.id.lista_productos);
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
