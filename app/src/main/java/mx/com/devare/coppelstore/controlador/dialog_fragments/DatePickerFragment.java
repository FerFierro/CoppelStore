package mx.com.devare.coppelstore.controlador.dialog_fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Obtener fecha actual
        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, ano, mes, dia);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String dia=String.valueOf(day);
        String mes=String.valueOf(month);
        String ano=String.valueOf(year);
        String fecha=String.format("%s/%s/%s",dia,mes,ano);
        ((OnDateChangeListenerInterface) getActivity()).onDateChangeListener(fecha );
    }

    public interface OnDateChangeListenerInterface{
        void onDateChangeListener(String fecha);
    }
}