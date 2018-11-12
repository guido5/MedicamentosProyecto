package semd.com.escom.guido.medicamentosproyecto;

//hola mundo
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class InitFragment extends Fragment {
    ListView listView;
    private List<MedicamentoClass> lista = new ArrayList<MedicamentoClass>();
    ListViewAdapter listViewAdapter;

    public InitFragment() {    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_init, container, false);
        listView = vista.findViewById(R.id.listViewInit);
        lista.add(new MedicamentoClass("paracetamol", "nose", "asdf", "asdfg" +
                "asdfg", "sadfg", "sadfg" ," asdfg", "asdfgh"));
        if(lista.isEmpty()){

        }

        listViewAdapter = new ListViewAdapter(getContext(), R.layout.list_item, lista);
        listView.setAdapter(listViewAdapter);

        return vista;
    }

}
