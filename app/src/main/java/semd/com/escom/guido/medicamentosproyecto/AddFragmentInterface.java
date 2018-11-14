package semd.com.escom.guido.medicamentosproyecto;

import android.content.ContentValues;

public interface AddFragmentInterface {
    public void insertRowInMedicamentoTable(ContentValues valores);
    public void modifyRowInMedicamentoTable(ContentValues valores, MedicamentoClass currentItem);
}
