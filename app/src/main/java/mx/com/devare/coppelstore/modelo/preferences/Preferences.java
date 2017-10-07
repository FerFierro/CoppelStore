package mx.com.devare.coppelstore.modelo.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String STRING_PREFERENCES="coppelstore.preferencias";
    public static final String PREFERENCE_ESTADO_TYC="estado.terminosycondiciones";
    public static final String PREFERENCE_ESTADO_NO_CERRAR_SESION="estado.button.sesion";
    public static final String PREFERENCE_USUARIO_LOGIN="usuario.login";
    public static final String PREFERENCE_BUTTON_LOGIN_FACEBOOK="estado.login.facebook";
    public static final String PREFERENCE_BUTTON_LOGIN_COPPEL_STORE="estado.login.coppelstore";
    public static final String PREFERENCE_MODO_LOGIN="modo.tipologin";
    public static final String PREFERENCE_ESTADO_CARGA_DATOS="estado.carga.datos";

    public static void savePreferenceBoolean(Context contexto, boolean b, String key){
        SharedPreferences preferences=contexto.getSharedPreferences(STRING_PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key,b).apply();
    }

    public static  void savePreferenceString(Context contexto, String b, String key){
        SharedPreferences preferences=contexto.getSharedPreferences(STRING_PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().putString(key,b).apply();
    }

    public static boolean obtenerPreferenceBoolean(Context contexto, String key){
        SharedPreferences preferences=contexto.getSharedPreferences(STRING_PREFERENCES, Context.MODE_PRIVATE);
        return  preferences.getBoolean(key,false);// si es que nunca se ha guardado nada en esta key pues retornara false
    }

    public static String obtenerPreferenceString(Context contexto, String key){
        SharedPreferences preferences=contexto.getSharedPreferences(STRING_PREFERENCES, Context.MODE_PRIVATE);
        return  preferences.getString(key,"");// si es que nunca se ha guardado nada en esta key pues retornara una cadena vacia
    }
}
