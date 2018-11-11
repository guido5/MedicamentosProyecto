package semd.com.escom.guido.medicamentosproyecto;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/*
* Este fragmento tiene los atributos para agregar el medicamento
* tambien tiene la interfaz grafica
* */
public class AddFragment extends Fragment {

    //Objetos
    public static final int REQUEST_TAKE_PHOTO_1 = 1;
    public static final int REQUEST_TAKE_PHOTO_2 = 2;
    String mCurrentPhotoPath;
    String envaseFilePath;
    String medicamentoFilePath;
    EditText nombre;
    EditText padecimiento;
    EditText doctor;
    Spinner number_time;
    Spinner time;
    Spinner checkpoint;
    EditText time_init;
    ImageView envase;
    ImageView medicamento;
    Button envaseFoto;
    Button medicamentoFoto;
    Button save;
    AddFragmentInterface context;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = (AddFragmentInterface) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_add, container, false);

        nombre = vista.findViewById(R.id.editTextName);
        padecimiento = vista.findViewById(R.id.editTextPadecimiento);
        doctor = vista.findViewById(R.id.editTextDoctor);
        number_time = vista.findViewById(R.id.spinner_number_time);
        time = vista.findViewById(R.id.spinner_time);
        time_init = vista.findViewById(R.id.editTextTimeInit);
        checkpoint = vista.findViewById(R.id.spinner_checkpoint);
        envase = vista.findViewById(R.id.imageViewEnvase);
        medicamento = vista.findViewById(R.id.imageViewMedicamento);

        //onClick del boton de envase
        envaseFoto = vista.findViewById(R.id.buttonEnvase);
        envaseFoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                takePictureIntent("envase", 1);
            }
        });

        //onClick del boton de medicamento
        medicamentoFoto = vista.findViewById(R.id.buttonMedicamento);
        medicamentoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent("medicamento", 2);
            }
        });

        //onClick del boton save
        save = vista.findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int total_dias = 0;
                String periodo = time.getSelectedItem().toString();
                String numero = number_time.getSelectedItem().toString();
                String check = checkpoint.getSelectedItem().toString();
                if(periodo.equals("Dia/s")){
                    total_dias = (Integer.parseInt(numero));
                }else if(periodo.equals("Semana/s")){
                    total_dias = (Integer.parseInt(numero))*7;
                }else if(periodo.equals("Mes/es")){
                    total_dias = (Integer.parseInt(numero))*30;
                }else if(periodo.equals("Ano/s")){
                    total_dias = (Integer.parseInt(numero))*365;
                }else{
                    Toast.makeText((Context) context, "Error, no se encontro la seleccion posible", Toast.LENGTH_SHORT).show();
                }
                ContentValues values = new ContentValues();
                values.put(DatabaseSchema.Medicamentos.COLUMN_NAME_NOMBRE, nombre.getText().toString());
                values.put(DatabaseSchema.Medicamentos.COLUMN_NAME_PARA_QUE, padecimiento.getText().toString());
                values.put(DatabaseSchema.Medicamentos.COLUMN_NAME_NOMBRE_DOCTOR, doctor.getText().toString());
                values.put(DatabaseSchema.Medicamentos.COLUMN_NAME_CUANTOS_DIAS, total_dias);
                values.put(DatabaseSchema.Medicamentos.COLUMN_NAME_INIT_FECHA, time_init.getText().toString());
                values.put(DatabaseSchema.Medicamentos.COLUMN_NAME_CHECKPOINT, check);
                values.put(DatabaseSchema.Medicamentos.COLUMN_NAME_ENVASE_FOTO, envaseFilePath);
                values.put(DatabaseSchema.Medicamentos.COLUMN_NAME_MEDICAMENTO_FOTO, medicamentoFilePath);

                context.insertRowInMedicamentoTable(values);

            }
        });
        return vista;
    }

    private File createImageFile(String type) throws IOException {
        // Create an image file name
        String imageFileName = nombre.getText().toString() + "-" + type;
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void takePictureIntent(String type, int action) {
        Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (camara.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(type);
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile((Context) context,
                        "semd.com.escom.guido.medicamentosproyecto",
                        photoFile);
                camara.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                if(action == 1){
                    startActivityForResult(camara, REQUEST_TAKE_PHOTO_1);
                }else if(action == 2){
                    startActivityForResult(camara, REQUEST_TAKE_PHOTO_2);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_TAKE_PHOTO_1:
                if(resultCode == RESULT_OK){
                    envaseFilePath = mCurrentPhotoPath;
                    Bitmap imageBitmap = BitmapFactory.decodeFile(envaseFilePath);
                    envase.setImageBitmap(imageBitmap);
                }
                break;
            case REQUEST_TAKE_PHOTO_2:
                if(resultCode == RESULT_OK){
                    medicamentoFilePath = mCurrentPhotoPath;
                    Bitmap imageBitmap = BitmapFactory.decodeFile(medicamentoFilePath);
                    medicamento.setImageBitmap(imageBitmap);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText((Context) context, "Se murio el fragmento", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
