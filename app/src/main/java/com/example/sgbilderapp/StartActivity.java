package com.example.sgbilderapp;

import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

        boolean mboolean = false;

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) {
            // do the thing for the first time
            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.apply();

            Intent intent = new Intent(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:" + BuildConfig.APPLICATION_ID));

            startActivity(intent);
            //startActivityForResult(intent, APP_STORAGE_ACCESS_REQUEST_CODE);

            //Path to Choerografien
            String folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + File.separator + FOLDERNAME;

            subFolder = new File(folder);

            if (!subFolder.exists()) {
                subFolder.mkdirs();
            }

            AssetManager assetManager = getAssets();

            InputStream is = null;
            try {
                is = assetManager.open("MansWorld");
            } catch (IOException e) {
                e.printStackTrace();
            }

            Choreography choreography = null;
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(is);
                choreography = (Choreography) objectInputStream.readObject();
                objectInputStream.close();
                is.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(subFolder + File.separator + "MansWorld"));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(choreography);
                objectOutputStream.close();
                fileOutputStream.close();

            } catch (IOException e) {
                Log.e("ERROR", e.toString());
            }

        } else {
            String folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + File.separator + FOLDERNAME;

            subFolder = new File(folder);

            if (!subFolder.exists()) {
                subFolder.mkdirs();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        reloadChoreo();

    }

    //btn "Laden" -> send user to main Activity
    public void loadChoreo(View view) throws IOException {

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

    public void settings(View view){


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