package mx.com.devare.coppelstore.modelo.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public abstract class SolicitudesJSON {
    Context mContext;
    public abstract void obtenerDatos(JSONObject mJsonObject);
    public abstract void obtenerError(VolleyError mError);

    public SolicitudesJSON(Context mContext) {
        this.mContext = mContext;
    }

    public  void solicitudGETJson(String murl){
        JsonObjectRequest solicitud=new JsonObjectRequest(
                Request.Method.GET,
                murl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        obtenerDatos(response);
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                obtenerError(error);
            }
        }
        );
        MySingleton.getInstance(mContext).addToRequestQueue(solicitud);
    }


}
