package semd.com.escom.guido.medicamentosproyecto;

public class MedicamentoClass {

    public String nombre;
    public String para_que;
    public String nombre_doctor;
    public String cuantos_dias;
    public String init_fecha;
    public String checkpoint;
    public String envase_foto;
    public String medicamento_foto;

    public MedicamentoClass(String nombre, String para_que, String nombre_doctor,
                            String cuantos_dias, String init_fecha, String checkpoint,
                            String envase_foto, String medicamento_foto){

        this.nombre = nombre;
        this.para_que = para_que;
        this.nombre_doctor = nombre_doctor;
        this.cuantos_dias = cuantos_dias;
        this.init_fecha = init_fecha;
        this.checkpoint = checkpoint;
        this.envase_foto = envase_foto;
        this.medicamento_foto = medicamento_foto;
    }


}
