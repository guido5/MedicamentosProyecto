package guido.foreignDevelopment.health.ProPharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import guido.foreignDevelopment.health.ProPharmacy.Modules.Home.Model.HomePresentable;
import guido.foreignDevelopment.health.ProPharmacy.Modules.Home.Model.HomeViewable;
import guido.foreignDevelopment.health.ProPharmacy.Modules.Home.Presenter.HomePresenter;

public class MainActivity extends AppCompatActivity implements HomeViewable {
    private FrameLayout frame;
    private HomePresentable presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = (FrameLayout) findViewById(R.id.fragment_frame);
        init();
    }

    private void init() {
        presenter = new HomePresenter(this);
        presenter.getMedicines();
    }
}
