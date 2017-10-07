package mx.com.devare.coppelstore.controlador.activitys;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.preference.Preference;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import mx.com.devare.coppelstore.R;
import mx.com.devare.coppelstore.modelo.preferences.Preferences;

@SuppressWarnings("unchecked")
public class TerminosYCondiciones extends AppCompatActivity implements View.OnClickListener {

    //Views
    Button btn_terminos_rechazar,btn_terminos_aceptar;
    NestedScrollView nested_scroll;
    boolean estadoTYC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verificarEstadoTYC();
        setContentView(R.layout.activity_terminos_ycondiciones);
        inicializarComponentesUI();
        inicializarSetOnClickListener();
        habilitarComponentes(View.INVISIBLE);
        estadoScroll();

    }

    private void verificarEstadoTYC() {
        estadoTYC= Preferences.obtenerPreferenceBoolean(this,Preferences.PREFERENCE_ESTADO_TYC);
        if (estadoTYC){
            iniciarActividadSiguiente();
        }
    }


    private void inicializarComponentesUI() {
        btn_terminos_aceptar=(Button) findViewById(R.id.btn_terminos_aceptar);
        btn_terminos_rechazar=(Button) findViewById(R.id.btn_terminos_rechazar);
        nested_scroll=(NestedScrollView) findViewById(R.id.nested_scroll);
    }

    private void inicializarSetOnClickListener() {
         btn_terminos_aceptar.setOnClickListener(this);
        btn_terminos_rechazar.setOnClickListener(this);
    }

    private void habilitarComponentes(int visible) {
        btn_terminos_aceptar.setVisibility(visible);
        btn_terminos_rechazar.setVisibility(visible);
    }

    private void estadoScroll() {
        nested_scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                //Pregunta si llego hasta el fondo del terminos y condiciones
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    habilitarComponentes(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_terminos_aceptar:
                guardarEstadoAceptacionTYC();
                iniciarActividadSiguiente();
                break;

            case R.id.btn_terminos_rechazar:
                finalizarActividad();
                break;
        }

    }

    private void guardarEstadoAceptacionTYC() {
        Preferences.savePreferenceBoolean(this,true,Preferences.PREFERENCE_ESTADO_TYC);
    }

    @SuppressWarnings("unchecked")
    private void iniciarActividadSiguiente() {
        Intent mIntent= new Intent(this,LoginActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Transition mTransition=new Explode();
            mTransition.setDuration(1000);
            mTransition.setInterpolator(new DecelerateInterpolator());
            getWindow().setEnterTransition(mTransition);
            startActivity(mIntent,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
        }else{
            startActivity(mIntent);
            overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_back_out);
        }

        finalizarActividad();
    }

    private void finalizarActividad() {
        TerminosYCondiciones.this.finish();
    }
}
