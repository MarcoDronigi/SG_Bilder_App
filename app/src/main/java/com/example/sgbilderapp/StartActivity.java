package com.example.sgbilderapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private File subFolder;
    private Spinner spinnerChoreo;

    private File[] files;

    final private String FOLDERNAME = "Choerografien";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Permissions Choreo Files
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        //Path to Choerografien
        String folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + File.separator + FOLDERNAME;

        subFolder = new File(folder);

        if (!subFolder.exists()) {
            subFolder.mkdirs();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        reloadChoreo();
    }

    //btn "Laden" -> send user to main Activity
    public void loadChoreo(View view){

        if (files.length == 0) { //Case no files found in dir
            Toast.makeText(StartActivity.this, "Es konnte keine Choreografie geladen werden!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            intent.putExtra("pathChoreo", subFolder + File.separator + spinnerChoreo.getSelectedItem()); //path of choreo is sent to mainActivity
            startActivity(intent);
        }
    }

    //btn "Neue Choreo" -> opens Dialgo where user can create new Choreo
    public void createChoreo(View view) throws IOException {

        //Create Dialoge
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setTitle("Bennene die Choreo:")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = (input.getText()).toString();

                        if (name.equals("")){ //case no input
                            Toast.makeText(StartActivity.this, "Der Name kann nicht leer sein!", Toast.LENGTH_SHORT).show();
                        } else { //New choreo is initiated with a first Bild
                            Choreography newChoreo = new Choreography(name);
                            newChoreo.addBlankBild(-1);
                            try {
                                newChoreo.save(subFolder + File.separator + name.replaceAll("\\W+", ""));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Start main Activity with new CHoreo
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

    //btn Reload Icon
    public void pushReloadChoreo(View view){
        ImageView btnReload = findViewById(R.id.pctReload);

        btnReload.setRotation(0);
        ViewPropertyAnimator rotateButton = btnReload.animate().rotation(-360).setDuration(1000);
        rotateButton.start();


        reloadChoreo();
    }

    //Grap Choreos from folder and fill spinner
    public void reloadChoreo() {
        files = subFolder.listFiles();

        List<String> spinnerArray =  new ArrayList<>();
        for (File file : files) {
            spinnerArray.add(file.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoreo = findViewById(R.id.spinnerChoreo);
        spinnerChoreo.setAdapter(adapter);
    }
}