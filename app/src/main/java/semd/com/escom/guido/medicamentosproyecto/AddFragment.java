package semd.com.escom.guido.medicamentosproyecto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
* Este fragmento tiene los atributos para agregar el medicamento
* tambien tiene la interfaz grafica
* */
public class AddFragment extends Fragment {


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_add, container, false);
        return vista;
    }

}
