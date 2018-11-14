package semd.com.escom.guido.medicamentosproyecto;

import java.util.List;

public interface InitFragmentInterface {
    public void showUpdateFragment(AddFragment currentItem);
    public List<MedicamentoClass> queryMedicamentos();
}
