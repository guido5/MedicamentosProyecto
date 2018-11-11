package semd.com.escom.guido.medicamentosproyecto;

import android.provider.BaseColumns;

public class DatabaseSchema {

    private DatabaseSchema(){}

    public class Medicamentos implements BaseColumns{
        public static final String TABLE_NAME = "medicamentos";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_PARA_QUE = "para_que";
        public static final String COLUMN_NAME_NOMBRE_DOCTOR = "nombre_doctor";
        public static final String COLUMN_NAME_CUANTOS_DIAS = "cuantos_dias";
        public static final String COLUMN_NAME_INIT_FECHA = "init_fecha";
        public static final String COLUMN_NAME_CHECKPOINT = "checkpoint";
        public static final String COLUMN_NAME_ENVASE_FOTO = "envase_foto";
        public static final String COLUMN_NAME_MEDICAMENTO_FOTO = "medicamento_foto";
    }
}
