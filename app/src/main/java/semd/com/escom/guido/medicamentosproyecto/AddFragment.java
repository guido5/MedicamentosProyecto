package semd.com.escom.guido.medicamentosproyecto;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/*
* Este fragmento tiene los atributos para agregar el medicamento
* tambien tiene la interfaz grafica
* */
public class AddFragment extends Fragment {
    Button envaseFoto;
    Button medicamentoFoto;
    ImageView envase;
    ImageView medicamento;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_add, container, false);
        envase = vista.findViewById(R.id.imageViewEnvase);
        medicamento = vista.findViewById(R.id.imageViewMedicamento);
        //onClick del boton de envase
        envaseFoto = vista.findViewById(R.id.buttonEnvase);
        envaseFoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                envase.setImageResource(R.drawable.ic_farmacia);
            }
        });

        //onClick del boton de medicamento
        medicamentoFoto = vista.findViewById(R.id.buttonMedicamento);
        medicamentoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicamento.setImageResource(R.drawable.ic_farmacia);
            }
        });
        return vista;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getContext(), "Se murio el fragmento", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
