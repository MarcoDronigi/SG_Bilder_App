package com.example.sgbilderapp;

import static java.lang.System.out;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private File subFolder;

    private Spinner spinnerChoreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        String FOLDERNAME = "Choerografien";

        String folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + File.separator + FOLDERNAME;

        subFolder = new File(folder);

        if (!subFolder.exists()) {
            subFolder.mkdirs();
        }

        File[] files = subFolder.listFiles();
        //Toast.makeText(this, ((Integer) files.length).toString(), Toast.LENGTH_SHORT).show();


        List<String> spinnerArray =  new ArrayList<>();
        for (int i = 0; i < files.length; i++){
            spinnerArray.add(files[i].getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoreo = findViewById(R.id.spinnerChoreo);
        spinnerChoreo.setAdapter(adapter);

        Button btnStart = findViewById(R.id.btnLaden);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (files.length == 0) {
                    Toast.makeText(StartActivity.this, "Es konnte keine Choreografie geladen werden!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("pathChoreo", subFolder + File.separator + spinnerChoreo.getSelectedItem());
                    startActivity(intent);
                }
            }
        });
    }

    public void btnCreateChoreo(View view) throws IOException {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setTitle("Bennene die Choreo:")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (input == null){
                            Toast.makeText(StartActivity.this, "null", Toast.LENGTH_SHORT).show();
                        } else {
                            String name = (input.getText()).toString();

                            //String name = s.replaceAll("\\W+", "");

                            Choreography newChoreo = new Choreography(name);
                            newChoreo.addBlankBild();
                            try {
                                newChoreo.save(subFolder + File.separator + name.replaceAll("\\W+", ""));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            Intent intent = new Intent(StartActivity.this, MainActivity.class);
                            intent.putExtra("pathChoreo", subFolder + File.separator + name.replaceAll("\\W+", ""));
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}