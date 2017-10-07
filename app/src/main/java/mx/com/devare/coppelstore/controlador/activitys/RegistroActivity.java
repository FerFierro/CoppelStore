package mx.com.devare.coppelstore.controlador.activitys;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;

import mx.com.devare.coppelstore.R;
import mx.com.devare.coppelstore.controlador.dialog_fragments.DatePickerFragment;
import mx.com.devare.coppelstore.modelo.customs.CustomViews;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener,DatePickerFragment.OnDateChangeListenerInterface{

    TextInputLayout til_registro_usuario,
            til_registro_password,
            til_confirmar_password,
            til_registro_nombre,
            til_registro_ap_paterno,
            til_registro_ap_materno,
            til_fecha_nacimiento,
            til_registro_telefono,
            til_registro_correo;

    AutoCompleteTextView autocomplete_registro_nombre,
            autocomplete_registro_ap_paterno,
            autocomplete_registro_ap_materno;


    String USUARIO="";
    String PASSWORD="";
    String CONFIRMAR_PASSWORD;
    String NOMBRE;
    String AP_PATERNO;
    String AP_MATERNO;
    String FECHA_NACIMIENTO;
    String CORREO;
    String TELEFONO;
    String GENERO;

    RadioButton rb_hombre,rb_mujer;
    Button btn_registrar_usuario,btn_agregar_fecha;

    CustomViews toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inicializarComponentesUI();
        inicializarSetOnClickListener();
        inicializarAdatadoresAutoComplete();
        toast=new CustomViews(this);
    }


    private void inicializarComponentesUI() {

        //Obtener instancia del TextInputLayouts
        til_registro_usuario = (TextInputLayout) findViewById(R.id.til_registro_usuario);
        til_registro_password = (TextInputLayout) findViewById(R.id.til_registro_password);
        til_confirmar_password = (TextInputLayout) findViewById(R.id.til_confirmar_password);
        til_registro_nombre = (TextInputLayout) findViewById(R.id.til_registro_nombre);
        til_registro_ap_paterno = (TextInputLayout) findViewById(R.id.til_registro_ap_paterno);
        til_registro_ap_materno = (TextInputLayout) findViewById(R.id.til_registro_ap_materno);
        til_fecha_nacimiento= (TextInputLayout) findViewById(R.id.til_fecha_nacimiento);
        til_registro_correo = (TextInputLayout) findViewById(R.id.til_registro_correo);
        til_registro_telefono = (TextInputLayout) findViewById(R.id.til_registro_telefono);

        //AutoCompleteTextViews
        autocomplete_registro_nombre=(AutoCompleteTextView) findViewById(R.id.autocomplete_registro_nombre);
        autocomplete_registro_ap_paterno=(AutoCompleteTextView) findViewById(R.id.autocomplete_registro_ap_paterno);
        autocomplete_registro_ap_materno=(AutoCompleteTextView) findViewById(R.id.autocomplete_registro_ap_materno);

        //Obtener instancia del RadioButtons
        rb_hombre = (RadioButton) findViewById(R.id.rb_hombre);
        rb_mujer = (RadioButton) findViewById(R.id.rb_mujer);

        //Obtener instancia del Buttons
        btn_registrar_usuario = (Button) findViewById(R.id.btn_registrar_usuario);
        btn_agregar_fecha= (Button) findViewById(R.id.btn_agregar_fecha);
    }

    private void obtenerTextoDeCampos() {

        USUARIO=til_registro_usuario.getEditText().getText().toString().trim();
        PASSWORD= til_registro_password.getEditText().getText().toString().trim();
        CONFIRMAR_PASSWORD= til_confirmar_password.getEditText().getText().toString().trim();
        NOMBRE= til_registro_nombre.getEditText().getText().toString().trim();
        AP_PATERNO= til_registro_ap_paterno.getEditText().getText().toString().trim();
        AP_MATERNO= til_registro_ap_materno.getEditText().getText().toString().trim();
        FECHA_NACIMIENTO=til_fecha_nacimiento.getEditText().getText().toString().trim();
        CORREO= til_registro_correo.getEditText().getText().toString().trim();
        TELEFONO= til_registro_telefono.getEditText().getText().toString().trim();

        if (rb_hombre.isChecked()) GENERO=getString(R.string.genero_hombre);
        else if (rb_mujer.isChecked()) GENERO=getString(R.string.genero_mujer);
    }

    private void inicializarSetOnClickListener() {
        btn_registrar_usuario.setOnClickListener(this);
        btn_agregar_fecha.setOnClickListener(this);
        rb_hombre.setOnClickListener(this);
        rb_mujer.setOnClickListener(this);
    }

    private void inicializarAdatadoresAutoComplete() {
        String[] nombres = getResources().getStringArray(R.array.nombre_array);
        String[] apellidos=getResources().getStringArray(R.array.apellidos_array);
        ArrayAdapter<String> adapterNombres = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        ArrayAdapter<String> adapterApellidos = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,apellidos);

        autocomplete_registro_nombre.setAdapter(adapterNombres);
        autocomplete_registro_ap_paterno.setAdapter(adapterApellidos);
        autocomplete_registro_ap_materno.setAdapter(adapterApellidos);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_agregar_fecha:
                agregarFecha();
                break;

            case R.id.rb_hombre:
                rb_mujer.setChecked(false);
                break;

            case R.id.rb_mujer:
                rb_hombre.setChecked(false);
                break;

            case R.id.btn_registrar_usuario:
                registrarUsuario();
                break;
        }

    }


    private void registrarUsuario(){
        obtenerTextoDeCampos();

        boolean estadoUsuario=validarUsuario(USUARIO);
        boolean estadoPassword=validarPassword(PASSWORD,CONFIRMAR_PASSWORD);
        boolean estadonombre=validarNombre(NOMBRE);
        boolean estadoAp_Paterno=validarAp_Paternp(AP_PATERNO);
        boolean estadoAp_materno=validarAp_Materno(AP_MATERNO);
        boolean estadoFecha_nacimiento=validarFechaDeNacimiento(FECHA_NACIMIENTO);
        boolean estadoCorreo=validarCorreo(CORREO);
        boolean estadoTelefono=validarTelefono(TELEFONO);
        boolean estadoGenero=validarGenero();

        if (estadoUsuario && estadoPassword && estadonombre && estadoAp_Paterno && estadoAp_materno && estadoFecha_nacimiento && estadoCorreo && estadoTelefono && estadoGenero){
           mandarDatosALogin();
        }
    }

    private void mandarDatosALogin() {
        obtenerTextoDeCampos();
        Intent intent=new Intent();
        intent.putExtra("USU_REGISTRO_KEY",USUARIO);
        intent.putExtra("PAS_REGISTRO_KEY",PASSWORD);
        setResult(1,intent);
        finish();//finishing activity
    }

    private void agregarFecha() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    //<editor-fold desc="METODO DE LA INTERFAZ DatePickerFragment.OnDateChangeListenerInterface  QUE DEVUELVE LA FECHA SELECCIONADA">
    @Override
    public void onDateChangeListener(String fecha) {
        til_fecha_nacimiento.getEditText().setText(fecha);
    }
    //</editor-fold>

    private boolean validarUsuario(String usuario){

        if (usuario.isEmpty()){
            til_registro_usuario.setError(getString(R.string.validate_campo_vacio));
            til_registro_usuario.requestFocus();
            return false;
        }
        til_registro_usuario.setError(null);
        return true;
    }

    private boolean validarPassword(String password, String confirmarPassword){

        if (password.isEmpty() && confirmarPassword.length()==0){
            til_registro_password.requestFocus();
            til_registro_password.setError(getString(R.string.validate_campo_vacio));
            til_confirmar_password.requestFocus();
            til_confirmar_password.setError(getString(R.string.validate_campo_vacio));
            return false;
        }

        if (!password.equals(confirmarPassword)){
            til_confirmar_password.requestFocus();
            til_registro_password.setError("Las contrasenas deben coincidir");
            til_confirmar_password.setError("Las contrasenas deben coincidir");
            return false;
        }
        til_registro_password.setError(null);
        til_confirmar_password.setError(null);
        return true;
    }

    private boolean validarNombre(String nombre){
        if (nombre.length()==0){
            til_registro_nombre.requestFocus();
            til_registro_nombre.setError(getString(R.string.validate_campo_vacio));
            return false;
        }
        til_registro_nombre.setError(null);

        return true;
    }

    private boolean validarAp_Paternp(String ap_paterno){
        if (ap_paterno.isEmpty()){
            til_registro_ap_paterno.requestFocus();
            til_registro_ap_paterno.setError(getString(R.string.validate_campo_vacio));
            return false;
        }
        til_registro_ap_paterno.setError(null);
        return true;
    }

    private boolean validarAp_Materno(String ap_materno){
        if (ap_materno.isEmpty()){
            til_registro_ap_materno.requestFocus();
            til_registro_ap_materno.setError(getString(R.string.validate_campo_vacio));
            return false;
        }
        til_registro_ap_materno.setError(null);
        return true;
    }

    private boolean validarFechaDeNacimiento(String fechaNacimiento){
        String hintfecha=getString(R.string.hint_registro_fecha_de_nacimiento);

        if (fechaNacimiento.isEmpty()){
            til_fecha_nacimiento.requestFocus();
            til_fecha_nacimiento.setError(getString(R.string.validate_campo_vacio));
            return false;
        }
        til_fecha_nacimiento.setError(null);
        return true;
    }

    private boolean validarCorreo(String correo){
        if (correo.isEmpty()){
            til_registro_correo.requestFocus();
            til_registro_correo.setError(getString(R.string.validate_campo_vacio));
            return false;
        }
        til_registro_correo.setError(null);
        return true;
    }

    private boolean validarTelefono(String telefono){
        if (telefono.isEmpty()){
            til_registro_telefono.setError(getString(R.string.validate_campo_vacio));
            til_registro_telefono.requestFocus();
            return false;
        }
        til_registro_telefono.setError(null);

        return true;
    }

    private boolean validarGenero(){
        if (!(rb_hombre.isChecked() ||rb_mujer.isChecked())){
            toast.toastPersonalizado(getString(R.string.msj_error_sexo), R.drawable.ic_logo, Gravity.TOP | Gravity.START, 0, 0);
            return false;
        }
        return true;
    }
}
