package mx.com.devare.coppelstore.controlador.activitys;

import android.content.Intent;
import android.preference.Preference;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import mx.com.devare.coppelstore.R;
import mx.com.devare.coppelstore.modelo.customs.CustomViews;
import mx.com.devare.coppelstore.modelo.preferences.Preferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_iniciar_sesion_facebook, btn_iniciar_sesion_interna, btn_registro;
    TextInputLayout til_login_usuario, til_login_password;
    EditText et_login_usuario, et_login_password;
    RadioButton rbtn_login_guardar_sesion;
    TextView tv_link_restaurar_contrasena;

    public static final int REQUEST_CODE = 1;


    CustomViews toast;
    boolean estadoTYC;

    private boolean isActivateRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verificarEstadoTYC();
        setContentView(R.layout.activity_login);
        toast = new CustomViews(this);
        iniciarComponentesUI();
        inicializarSetOnClickListener();
        isActivateRadioButton = rbtn_login_guardar_sesion.isChecked();//se va ha guardar desactivado
    }

    private void verificarEstadoTYC() {
        estadoTYC = Preferences.obtenerPreferenceBoolean(this, Preferences.PREFERENCE_ESTADO_NO_CERRAR_SESION);
        if (estadoTYC) {
            actividadSiguiente();
        }
    }


    private void iniciarComponentesUI() {
        //Buttons
        btn_iniciar_sesion_facebook = (Button) findViewById(R.id.btn_iniciar_sesion_facebook);
        btn_iniciar_sesion_interna = (Button) findViewById(R.id.btn_iniciar_sesion_interna);
        btn_registro = (Button) findViewById(R.id.btn_registro);

        //TextInputLayouts
        til_login_usuario = (TextInputLayout) findViewById(R.id.til_login_usuario);
        til_login_password = (TextInputLayout) findViewById(R.id.til_login_password);

        //EditTexts
        et_login_usuario = (EditText) findViewById(R.id.et_login_usuario);
        et_login_password = (EditText) findViewById(R.id.et_login_password);

        //RadioButtons
        rbtn_login_guardar_sesion = (RadioButton) findViewById(R.id.rbtn_login_guardar_sesion);

        // TextViews
        tv_link_restaurar_contrasena = (TextView) findViewById(R.id.tv_link_restaurar_contrasena);
    }

    private void inicializarSetOnClickListener() {
        btn_iniciar_sesion_facebook.setOnClickListener(this);
        btn_iniciar_sesion_interna.setOnClickListener(this);
        btn_registro.setOnClickListener(this);
        rbtn_login_guardar_sesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_iniciar_sesion_facebook:
                iniciarSesionFacebook();
                break;

            case R.id.btn_iniciar_sesion_interna:
                iniciarSecionInterna();
                break;

            case R.id.btn_registro:
                iniciarRegistro();
                break;

            case R.id.rbtn_login_guardar_sesion:
                //Cuando se da click es cuando se activa
                if (isActivateRadioButton) {
                    rbtn_login_guardar_sesion.setChecked(false);
                }
                isActivateRadioButton = rbtn_login_guardar_sesion.isChecked();
                break;
        }
    }

    private void iniciarRegistro() {
        Intent mIntent = new Intent(this, RegistroActivity.class);
        startActivityForResult(mIntent, REQUEST_CODE);
    }

    private void iniciarSecionInterna() {
        String usuario = et_login_usuario.getText().toString().trim();
        String password = et_login_password.getText().toString().trim();

        if (usuario.equals("admin") && password.equals("1234")) {
            Preferences.savePreferenceBoolean(this, rbtn_login_guardar_sesion.isChecked(), Preferences.PREFERENCE_ESTADO_NO_CERRAR_SESION);
            toast.toastPersonalizado("Sesion exitosa", R.drawable.ic_logo, Gravity.TOP | Gravity.START, 0, 0);
            actividadSiguiente();
        } else {
            toast.toastPersonalizado("usuario o password incorrectos", R.drawable.ic_logo, Gravity.TOP | Gravity.START, 0, 0);
        }
    }

    private void iniciarSesionFacebook() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (data != null) {
                    String usuarioLogin, passwordLogin;
                    usuarioLogin = data.getStringExtra("USU_REGISTRO_KEY");
                    passwordLogin = data.getStringExtra("PAS_REGISTRO_KEY");
                    et_login_usuario.setText(usuarioLogin);
                    et_login_password.setText(passwordLogin);
                }

                break;
        }
    }

    private void actividadSiguiente() {
        Intent mIntent = new Intent(this, MainActivity.class);
        startActivity(mIntent);
        finish();
    }
}
