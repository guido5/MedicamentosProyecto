package semd.com.escom.guido.medicamentosproyecto;

//hola mundo
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InitFragment extends Fragment {
    ListView listView;
    private List<MedicamentoClass> lista = new ArrayList<MedicamentoClass>();
    private ListViewAdapter listViewAdapter;
    InitFragmentInterface context;


    public InitFragment() {    }

    @Override
    public void onAttach(Context context) {
        this.context = (InitFragmentInterface) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_init, container, false);
        listView = vista.findViewById(R.id.listViewInit);
        if(lista.isEmpty()){
            //Hacemos busqueda en la bd
            lista.addAll(context.queryMedicamentos());
        }else{
            lista.clear();
            lista.addAll(context.queryMedicamentos());
        }

        listViewAdapter = new ListViewAdapter(getContext(), R.layout.list_item, lista);
        listView.setAdapter(listViewAdapter);
        //Agrega eventos al tocar el elementos del listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Se selecciono uno", Toast.LENGTH_SHORT).show();
            }
        });

        return vista;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getContext(), "Se murio InitFragment", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


}
