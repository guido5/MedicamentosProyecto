package semd.com.escom.guido.medicamentosproyecto;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    TextView textViewname;
    TextView textViewpadecimiento;
    ImageView imageViewmedicamento;
    Context context;
    int layout;
    List<MedicamentoClass> names;

    public ListViewAdapter(Context context, int layout, List<MedicamentoClass> names){
        this.context=context;
        this.layout=layout;
        this.names=names;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_item, null);

        MedicamentoClass currentItem = names.get(position);

        textViewname = v.findViewById(R.id.textViewNombre);
        textViewname.setText(currentItem.nombre);
        textViewpadecimiento = v.findViewById(R.id.textViewPadecimiento);
        textViewpadecimiento.setText(currentItem.para_que);
        imageViewmedicamento = v.findViewById(R.id.imageView2);
        String file = names.get(position).medicamento_foto;

        BitmapFactory.Options options;
        options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeFile(file, options);
        imageViewmedicamento.setImageBitmap(bitmap);

        //Devolvemos la vista inflada y modificada con nuestros datos
        return v;
    }
}
