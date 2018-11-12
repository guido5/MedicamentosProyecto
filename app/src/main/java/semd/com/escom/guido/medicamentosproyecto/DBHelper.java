package semd.com.escom.guido.medicamentosproyecto;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "DBMedicamentos";
    public static final int DBversion = 1;
    private String create_table_medicamento = "CREATE TABLE " + DatabaseSchema.Medicamentos.TABLE_NAME + " (" +
            DatabaseSchema.Medicamentos._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            DatabaseSchema.Medicamentos.COLUMN_NAME_NOMBRE + " TEXT," +
            DatabaseSchema.Medicamentos.COLUMN_NAME_PARA_QUE + " TEXT," +
            DatabaseSchema.Medicamentos.COLUMN_NAME_NOMBRE_DOCTOR + " TEXT," +
            DatabaseSchema.Medicamentos.COLUMN_NAME_CUANTOS_DIAS + " TEXT," +
            DatabaseSchema.Medicamentos.COLUMN_NAME_INIT_FECHA + " TEXT," +
            DatabaseSchema.Medicamentos.COLUMN_NAME_CHECKPOINT + " TEXT," +
            DatabaseSchema.Medicamentos.COLUMN_NAME_ENVASE_FOTO+ " TEXT," +
            DatabaseSchema.Medicamentos.COLUMN_NAME_MEDICAMENTO_FOTO + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseSchema.Medicamentos.TABLE_NAME;

    public DBHelper(Context context){
        super(context, DBName, null, DBversion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_medicamento);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
