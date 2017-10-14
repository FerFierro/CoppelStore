package mx.com.devare.coppelstore.controlador.activitys;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.com.devare.coppelstore.R;
import mx.com.devare.coppelstore.controlador.adaptadores.AdaptadorProductos;
import mx.com.devare.coppelstore.modelo.DBSourceData;
import mx.com.devare.coppelstore.modelo.Producto;
import mx.com.devare.coppelstore.modelo.Utilidades.Constantes;
import mx.com.devare.coppelstore.modelo.customs.CustomViews;
import mx.com.devare.coppelstore.modelo.volley.SolicitudesJSON;

public class MainActivity extends AppCompatActivity implements AdaptadorProductos.OnSetOnclickListener {

    DBSourceData mDbSourceData;
    AdaptadorProductos mAdaptadorProductos;
    RecyclerView recycler_lista_productos;
    List<Producto> mProductoList;
    LinearLayoutManager linearLayoutManager;
    private ProgressDialog progressDialog;
    SolicitudesJSON mSolicitudesJSON;
    CustomViews toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbSourceData = new DBSourceData(this);
        toast = new CustomViews(this);

        inicializarComponentesUI();
        new GetDataAsyncTask().execute();

    }

    private void inicializarComponentesUI() {
        recycler_lista_productos = (RecyclerView) findViewById(R.id.recycler_lista_productos);
    }

    public  class GetDataAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Obteniendo datos del servivio...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            obtenerProductosSW();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                mostrarProductos();
            }
        }
    }

    private void obtenerProductosSW() {
        mSolicitudesJSON = new SolicitudesJSON(this) {
            @Override
            public void obtenerDatos(JSONObject mJsonObject) {
                try {
                    JSONArray mJsonArray = mJsonObject.getJSONArray("productos");
                    List<Producto> mProductoList = new ArrayList<>();
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        JSONObject objeto2 = mJsonArray.getJSONObject(i);
                        String id = objeto2.getString("idProducto");
                        String nombre = objeto2.getString("nombre");
                        String url_imagen = objeto2.getString("url_imagen");
                        String precio = objeto2.getString("precio");
                        String stock = objeto2.getString("stock");
                        String proveedor = objeto2.getString("proveedor");
                        mProductoList.add(new Producto(id, url_imagen, nombre, precio, proveedor, stock));
                    }
                    generarInsert(mProductoList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void obtenerError(VolleyError mError) {
                toast.toastPersonalizado(mError.toString(), R.drawable.ic_logo, Gravity.TOP | Gravity.START, 0, 0);
            }
        };
        mSolicitudesJSON.solicitudGETJson(Constantes.URL_SW_OBTENER_PRODDUCTOS);
    }

    private void generarInsert(List<Producto> mProductoList) {

        for (Producto mProducto : mProductoList) {
            mDbSourceData.insertarProductos(mProducto);
        }
    }

    private void mostrarProductos() {

        mProductoList = new ArrayList<>();
        mProductoList = mDbSourceData.obtenerProductos();

        recycler_lista_productos.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        linearLayoutManager = new LinearLayoutManager(this);
        recycler_lista_productos.setLayoutManager(linearLayoutManager);

        // Crear un nuevo adaptador
        mAdaptadorProductos = new AdaptadorProductos(this, mProductoList,this);
        recycler_lista_productos.setAdapter(mAdaptadorProductos);
        recycler_lista_productos.setItemAnimator(new DefaultItemAnimator());
        mAdaptadorProductos.notifyDataSetChanged();
    }

    @Override
    public void onClick(AdaptadorProductos.ViewHolderProducto holderProducto, Producto mProducto) {
        Toast.makeText(this, mProducto.getNombre(), Toast.LENGTH_SHORT).show();
    }
}
