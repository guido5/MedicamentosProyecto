package semd.com.escom.guido.medicamentosproyecto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InitFragment extends Fragment {


    public InitFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_init, container, false);

        return vista;
        // Inflate the layout for this fragment
    }

}
