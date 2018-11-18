package semd.com.escom.guido.medicamentosproyecto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AddFragmentInterface, InitFragmentInterface{
    String[] projection = {
            BaseColumns._ID,
            DatabaseSchema.Medicamentos.COLUMN_NAME_NOMBRE,
            DatabaseSchema.Medicamentos.COLUMN_NAME_PARA_QUE,
            DatabaseSchema.Medicamentos.COLUMN_NAME_NOMBRE_DOCTOR,
            DatabaseSchema.Medicamentos.COLUMN_NAME_INIT_FECHA,
            DatabaseSchema.Medicamentos.COLUMN_NAME_CHECKPOINT,
            DatabaseSchema.Medicamentos.COLUMN_NAME_CUANTOS_DIAS,
            DatabaseSchema.Medicamentos.COLUMN_NAME_ENVASE_FOTO,
            DatabaseSchema.Medicamentos.COLUMN_NAME_MEDICAMENTO_FOTO
    };

    String sortOrder = BaseColumns._ID + " DESC";

    //Objetos e intancias
    AddFragment addFragment = new AddFragment();
    InitFragment initFragment = new InitFragment();
    MapFragment mapFragment = new MapFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Implementacion de la BD.
        dbHelper = new DBHelper(this);      //Crea la base de datos al llamar al constructor y crea las tablas indicadas.
        db = dbHelper.getWritableDatabase();        //Crea una instancia de la base de datos que creamos para poder editarla.

        //Agregacion del fragment ListView
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContent, initFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            InitFragment initFragment = new InitFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContent, initFragment);
            fragmentTransaction.commit();
        }
    }


    //Menu de tres puntitos en el accion bar Sin Usar

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_newmedicamento) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContent, addFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_farmacia) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContent, mapFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_calendario) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContent, calendarFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_tools) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void insertRowInMedicamentoTable(ContentValues valores) {
        long newRowId = db.insert(DatabaseSchema.Medicamentos.TABLE_NAME, null, valores);

        //Recarga del fragment ListView
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContent, initFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void modifyRowInMedicamentoTable(ContentValues valores, MedicamentoClass currentItem) {
        // Which row to update, based on the title
        String selection = BaseColumns._ID + " LIKE ?";
        String selection_args[] = {String.valueOf(currentItem.id)};
        long updateRowId = db.update(DatabaseSchema.Medicamentos.TABLE_NAME, valores, selection, selection_args);

        //Recarga del fragment ListView
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContent, initFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        db.close();     //Cierre de la bd cuando se cierra la actividad.
        super.onDestroy();
    }

    @Override
    public void showUpdateFragment(AddFragment currentItem) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContent, currentItem);
        fragmentTransaction.commit();
    }

    @Override
    public List<MedicamentoClass> queryMedicamentos() {
        List<MedicamentoClass> lista = new ArrayList<MedicamentoClass>();
        Cursor cursor = db.query(DatabaseSchema.Medicamentos.TABLE_NAME,
                        projection,
                null,
                null,
                null,
                null,
                    sortOrder
                );

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String nombre = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Medicamentos.COLUMN_NAME_NOMBRE));
            String para_que = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Medicamentos.COLUMN_NAME_PARA_QUE));
            String nombre_docto = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Medicamentos.COLUMN_NAME_NOMBRE_DOCTOR));
            String init_fecha = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Medicamentos.COLUMN_NAME_INIT_FECHA));
            String checkpoint = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Medicamentos.COLUMN_NAME_CHECKPOINT));
            String cuantos_dias = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Medicamentos.COLUMN_NAME_CUANTOS_DIAS));
            String medicamentoFoto = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Medicamentos.COLUMN_NAME_MEDICAMENTO_FOTO));
            String envaseFoto = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Medicamentos.COLUMN_NAME_ENVASE_FOTO));
            MedicamentoClass currentMedicamento = new MedicamentoClass(itemId, nombre,para_que,nombre_docto,cuantos_dias,init_fecha,checkpoint,envaseFoto, medicamentoFoto);
            lista.add(currentMedicamento);
        }

        return lista;
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
