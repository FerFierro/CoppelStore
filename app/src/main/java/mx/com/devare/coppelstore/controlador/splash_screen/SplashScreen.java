package mx.com.devare.coppelstore.controlador.splash_screen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import mx.com.devare.coppelstore.R;
import mx.com.devare.coppelstore.controlador.activitys.LoginActivity;
import mx.com.devare.coppelstore.controlador.activitys.TerminosYCondiciones;

public class SplashScreen extends AppCompatActivity {

    //Views
    ImageView iv_splash_screen;
    TextView tv_nombre_splash_screen,tv_slogan_splash_screen;
    boolean TERMINOS_Y_CONDICIONES=false,ESTADO_LOGIN;
    Intent mIntent;

    //Tiempo de espera
    public static final int DELAY_SPLASH_SCREEN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        inicializarComponentesUI();
        startAnimation();
    }

    private void inicializarComponentesUI() {
        iv_splash_screen=(ImageView) findViewById(R.id.iv_splash_screen);
        tv_nombre_splash_screen=(TextView) findViewById(R.id.tv_nombre_splash_screen);
        tv_slogan_splash_screen=(TextView) findViewById(R.id.tv_slogan_splash_screen);
    }

    private void startAnimation() {
        Animation mAnimation= AnimationUtils.loadAnimation(this,R.anim.traslate_top);
        mAnimation.reset();
        iv_splash_screen.startAnimation(mAnimation);

        mAnimation=AnimationUtils.loadAnimation(this,R.anim.traslate_right);
        mAnimation.reset();
        tv_nombre_splash_screen.clearAnimation();
        tv_nombre_splash_screen.startAnimation(mAnimation);

        mAnimation=AnimationUtils.loadAnimation(this,R.anim.traslate_left);
        mAnimation.reset();
        tv_slogan_splash_screen.clearAnimation();
        tv_slogan_splash_screen.startAnimation(mAnimation);

        inicializarTiempoDeEspera();
    }

    private void inicializarTiempoDeEspera() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TERMINOS_Y_CONDICIONES){
                    if(ESTADO_LOGIN){
                        iniciarActividadSiguiente();
                    }
                    inicializarLogin();
                }else{
                    inicializarTerminosYCondiciones();
                }
                finalizarActividad();
            }
        }, DELAY_SPLASH_SCREEN);
    }

    private void iniciarActividadSiguiente() {

    }

    private void finalizarActividad() {
        SplashScreen.this.finish();
    }

    private void inicializarTerminosYCondiciones() {
        mIntent=new Intent(this,TerminosYCondiciones.class);
        startActivity(mIntent);
        iniciarTransicionActivity();
    }

    private void inicializarLogin() {
        mIntent=new Intent(this,LoginActivity.class);
        startActivity(mIntent);
        iniciarTransicionActivity();
    }

    private void  iniciarTransicionActivity(){
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_back_out);
    }
}
