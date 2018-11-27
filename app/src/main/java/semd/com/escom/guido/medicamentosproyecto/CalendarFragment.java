package semd.com.escom.guido.medicamentosproyecto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment{
    java.util.Calendar calendar;
    CalendarView calendarView;
    Context context;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendar = java.util.Calendar.getInstance();


        calendar.set(java.util.Calendar.MONTH, java.util.Calendar.NOVEMBER);
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 11);
        calendar.set(java.util.Calendar.YEAR, 2018);


        calendar.add(java.util.Calendar.DAY_OF_MONTH, 1);
        calendar.add(java.util.Calendar.YEAR, 1);

        calendarView = vista.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                String msg = "Selecciona dia: " + i2 + " Mes : " + (i1 + 1) + " Año " + i;
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();


            }
        });
        return vista;
    }
}