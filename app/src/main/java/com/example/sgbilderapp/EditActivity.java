package com.example.sgbilderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class EditActivity extends AppCompatActivity {

    private ImageButton btnSave;
    private ImageButton btnMenu;

    private ImageView marker_1, marker_2, marker_3, marker_4, marker_5, marker_6, marker_7, marker_8;
    private ImageView[] marker;

    ImageView raster;

    private TextView txtHeadline;

    private EditText edtTxt1X, edtTxt1Y;
    private EditText edtTxt2X, edtTxt2Y;
    private EditText edtTxt3X, edtTxt3Y;
    private EditText edtTxt4X, edtTxt4Y;
    private EditText edtTxt5X, edtTxt5Y;
    private EditText edtTxt6X, edtTxt6Y;
    private EditText edtTxt7X, edtTxt7Y;
    private EditText edtTxt8X, edtTxt8Y;

    private int height;
    private int width;

    int bildNumb;

    final double CONVERSION_VALUE_COORD = 0.1269;

    Choreography choreo;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toast.makeText(this, "Noch nicht Funktional!", Toast.LENGTH_SHORT).show();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        btnSave = findViewById(R.id.btnEdit);
        btnMenu = findViewById(R.id.btnCloud);

        raster = findViewById(R.id.pctRaster);

        txtHeadline = findViewById(R.id.txtHeadline);
        txtHeadline.setText(choreo.getName());

        marker_1 = findViewById(R.id.marker_blue_1);
        marker_2 = findViewById(R.id.marker_blue_2);
        marker_3 = findViewById(R.id.marker_blue_3);
        marker_4 = findViewById(R.id.marker_blue_4);
        marker_5 = findViewById(R.id.marker_blue_5);
        marker_6 = findViewById(R.id.marker_blue_6);
        marker_7 = findViewById(R.id.marker_blue_7);
        marker_8 = findViewById(R.id.marker_blue_8);

        marker = new ImageView[] {marker_1, marker_2, marker_3, marker_4, marker_5, marker_6, marker_7, marker_8};

        edtTxt1X = findViewById(R.id.edtTxt1X);
        edtTxt1Y = findViewById(R.id.edtTxt1Y);
        edtTxt2X = findViewById(R.id.edtTxt2X);
        edtTxt2Y = findViewById(R.id.edtTxt2Y);
        edtTxt3X = findViewById(R.id.edtTxt3X);
        edtTxt3Y = findViewById(R.id.edtTxt3Y);
        edtTxt4X = findViewById(R.id.edtTxt4X);
        edtTxt4Y = findViewById(R.id.edtTxt4Y);
        edtTxt5X = findViewById(R.id.edtTxt5X);
        edtTxt5Y = findViewById(R.id.edtTxt5Y);
        edtTxt6X = findViewById(R.id.edtTxt6X);
        edtTxt6Y = findViewById(R.id.edtTxt6Y);
        edtTxt7X = findViewById(R.id.edtTxt7X);
        edtTxt7Y = findViewById(R.id.edtTxt7Y);
        edtTxt8X = findViewById(R.id.edtTxt8X);
        edtTxt8Y = findViewById(R.id.edtTxt8Y);

        choreo = Choreography.readFromFile(this, getIntent().getExtras().getString("pathChoreo"));


        restartChoreo(getIntent().getExtras().getInt("bildNumb"));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    choreo.save(getApplicationContext(), getIntent().getExtras().getString("pathChoreo"));
                } catch (IOException e) {
                    Toast.makeText(EditActivity.this, "Fehler", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                intent.putExtra("pathChoreo", getIntent().getExtras().getString("pathChoreo"));
                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://onedrive.live.com/?authkey=%21AIuE2fVU8xePeB8&id=3E68918F98BDA615%2122770&cid=3E68918F98BDA615"));
                startActivity(browserIntent);
            }
        });

        //Swip nach Rechts und Links springt zum nächsten und letzten Bild
        raster.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {}
            //prev Bild
            public void onSwipeRight() {
                if (bildNumb == 0){ //Fall "Startbild"
                    restartChoreo(choreo.getMaxBild());
                } else {
                    bildNumb--;
                    updateTxt();
                    updateMarker();
                }
            }
            //next Bild
            public void onSwipeLeft() {
                if (bildNumb == choreo.getMaxBild()) { //Fall letztes Bild
                    choreo.addBlankBild();
                }

                bildNumb++;
                updateTxt();
                updateMarker();

            }
            public void onSwipeBottom() {}
        });

        edtTxtListener(edtTxt1X, 1, true);
        edtTxtListener(edtTxt1Y, 1, false);
        edtTxtListener(edtTxt2X, 2, true);
        edtTxtListener(edtTxt2Y, 2, false);
        edtTxtListener(edtTxt3X, 3, true);
        edtTxtListener(edtTxt3Y, 3, false);
        edtTxtListener(edtTxt4X, 4, true);
        edtTxtListener(edtTxt4Y, 4, false);
        edtTxtListener(edtTxt5X, 5, true);
        edtTxtListener(edtTxt5Y, 5, false);
        edtTxtListener(edtTxt6X, 6, true);
        edtTxtListener(edtTxt6Y, 6, false);
        edtTxtListener(edtTxt7X, 7, true);
        edtTxtListener(edtTxt7Y, 7, false);
        edtTxtListener(edtTxt8X, 8, true);
        edtTxtListener(edtTxt8Y, 8, false);

    }

    //Funktion Bewegt einen Marker zu "bildNumb"
    public void moveMarker(ImageView marker, int pos){
        int pxTranslationX = (int) (width * choreo.getCoordX(bildNumb, pos) * CONVERSION_VALUE_COORD / 2);
        int pxTranslationY = (int) (width * choreo.getCoordY(bildNumb, pos) * CONVERSION_VALUE_COORD * (-1) / 2);

        marker.setTranslationX(pxTranslationX);
        marker.setTranslationY(pxTranslationY);
    }

    //Funktion Bewegt alle Marker zu "bildNumb"
    public void updateMarker(){
        moveMarker(marker_1, 1);
        moveMarker(marker_2, 2);
        moveMarker(marker_3, 3);
        moveMarker(marker_4, 4);
        moveMarker(marker_5, 5);
        moveMarker(marker_6, 6);
        moveMarker(marker_7, 7);
        moveMarker(marker_8, 8);
    }

    //Punkte und Anzwigen werden auf ein bestimmten Punkt gesetzt
    public void restartChoreo (int restartAT){
        bildNumb = restartAT;
        updateMarker();
        updateTxt();
    }

    public void edtTxtListener (EditText edtTxt, int pos, boolean isX){
        edtTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (isX){
                        choreo.setCoordX(bildNumb, pos, Double.parseDouble(edtTxt.getText().toString()));
                    } else {
                        choreo.setCoordY(bildNumb, pos, Double.parseDouble(edtTxt.getText().toString()));
                    }
                    updateMarker();
                } catch (Exception e){
                    e.printStackTrace();
                    /*edtTxt1X.setText(((Double) choreo.getCoordX(bildNumb, 1)).toString());
                    Toast.makeText(EditActivity.this, "Ungültige Eingabe", Toast.LENGTH_SHORT).show();*/
                }

            }
        });
    }

    public void updateTxt(){
        edtTxt1X.setText(((Double) choreo.getCoordX(bildNumb, 1)).toString());
        edtTxt1Y.setText(((Double) choreo.getCoordY(bildNumb, 1)).toString());
        edtTxt2X.setText(((Double) choreo.getCoordX(bildNumb, 2)).toString());
        edtTxt2Y.setText(((Double) choreo.getCoordY(bildNumb, 2)).toString());
        edtTxt3X.setText(((Double) choreo.getCoordX(bildNumb, 3)).toString());
        edtTxt3Y.setText(((Double) choreo.getCoordY(bildNumb, 3)).toString());
        edtTxt4X.setText(((Double) choreo.getCoordX(bildNumb, 4)).toString());
        edtTxt4Y.setText(((Double) choreo.getCoordY(bildNumb, 4)).toString());
        edtTxt5X.setText(((Double) choreo.getCoordX(bildNumb, 5)).toString());
        edtTxt5Y.setText(((Double) choreo.getCoordY(bildNumb, 5)).toString());
        edtTxt6X.setText(((Double) choreo.getCoordX(bildNumb, 6)).toString());
        edtTxt6Y.setText(((Double) choreo.getCoordY(bildNumb, 6)).toString());
        edtTxt7X.setText(((Double) choreo.getCoordX(bildNumb, 7)).toString());
        edtTxt7Y.setText(((Double) choreo.getCoordY(bildNumb, 7)).toString());
        edtTxt8X.setText(((Double) choreo.getCoordX(bildNumb, 8)).toString());
        edtTxt8Y.setText(((Double) choreo.getCoordY(bildNumb, 8)).toString());
    }

}