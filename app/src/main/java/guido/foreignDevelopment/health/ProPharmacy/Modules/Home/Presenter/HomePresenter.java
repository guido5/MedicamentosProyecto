package guido.foreignDevelopment.health.ProPharmacy.Modules.Home.Presenter;

import guido.foreignDevelopment.health.ProPharmacy.Modules.Home.Model.HomePresentable;
import guido.foreignDevelopment.health.ProPharmacy.Modules.Home.Model.HomeViewable;

public class HomePresenter implements HomePresentable {
    HomeViewable view;
    public HomePresenter(HomeViewable view) {
        this.view = view;
    }

    @Override
    public void getMedicines() {
        //Pregutnar por la base de datos



    }
}