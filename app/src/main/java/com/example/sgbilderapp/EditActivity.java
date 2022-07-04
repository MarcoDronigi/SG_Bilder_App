package com.example.sgbilderapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

    private EditText edtTxtDance, edtTxtComment;

    private int height;
    private int width;

    int bildNumb;

    final double CONVERSION_VALUE_COORD = 0.1269;

    private Choreography choreo;

    private boolean coordView = true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        btnSave = findViewById(R.id.btnEdit);
        btnMenu = findViewById(R.id.btnCloud);

        raster = findViewById(R.id.pctRaster);

        txtHeadline = findViewById(R.id.txtHeadline);

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

        TextView txtPos1 = findViewById(R.id.txtPos1);
        TextView txtPos2 = findViewById(R.id.txtPos2);
        TextView txtPos3 = findViewById(R.id.txtPos3);
        TextView txtPos4 = findViewById(R.id.txtPos4);
        TextView txtPos5 = findViewById(R.id.txtPos5);
        TextView txtPos6 = findViewById(R.id.txtPos6);
        TextView txtPos7 = findViewById(R.id.txtPos7);
        TextView txtPos8 = findViewById(R.id.txtPos8);

        edtTxtDance = findViewById(R.id.edtTextDance);
        edtTxtComment = findViewById(R.id.edtTxtComment);

        choreo = Choreography.readFromFile(getIntent().getExtras().getString("pathChoreo"));
        txtHeadline.setText(choreo.getName());

        restartChoreo(getIntent().getExtras().getInt("bildNumb"));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    choreo.save(getIntent().getExtras().getString("pathChoreo"));
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
                if (coordView) {
                    coordView = false;

                    btnMenu.setImageResource(R.drawable.ic_round_edit_location_alt_24);

                    edtTxt1X.setVisibility(View.GONE);
                    edtTxt1Y.setVisibility(View.GONE);
                    edtTxt2X.setVisibility(View.GONE);
                    edtTxt2Y.setVisibility(View.GONE);
                    edtTxt3X.setVisibility(View.GONE);
                    edtTxt3Y.setVisibility(View.GONE);
                    edtTxt4X.setVisibility(View.GONE);
                    edtTxt4Y.setVisibility(View.GONE);
                    edtTxt5X.setVisibility(View.GONE);
                    edtTxt5Y.setVisibility(View.GONE);
                    edtTxt6X.setVisibility(View.GONE);
                    edtTxt6Y.setVisibility(View.GONE);
                    edtTxt7X.setVisibility(View.GONE);
                    edtTxt7Y.setVisibility(View.GONE);
                    edtTxt8X.setVisibility(View.GONE);
                    edtTxt8Y.setVisibility(View.GONE);

                    txtPos1.setVisibility(View.GONE);
                    txtPos2.setVisibility(View.GONE);
                    txtPos3.setVisibility(View.GONE);
                    txtPos4.setVisibility(View.GONE);
                    txtPos5.setVisibility(View.GONE);
                    txtPos6.setVisibility(View.GONE);
                    txtPos7.setVisibility(View.GONE);
                    txtPos8.setVisibility(View.GONE);

                    edtTxtDance.setVisibility(View.VISIBLE);
                    edtTxtComment.setVisibility(View.VISIBLE);

                } else {
                    coordView = true;

                    btnMenu.setImageResource(R.drawable.ic_comment);

                    edtTxtDance.setVisibility(View.GONE);
                    edtTxtComment.setVisibility(View.GONE);


                    edtTxt1X.setVisibility(View.VISIBLE);
                    edtTxt1Y.setVisibility(View.VISIBLE);
                    edtTxt2X.setVisibility(View.VISIBLE);
                    edtTxt2Y.setVisibility(View.VISIBLE);
                    edtTxt3X.setVisibility(View.VISIBLE);
                    edtTxt3Y.setVisibility(View.VISIBLE);
                    edtTxt4X.setVisibility(View.VISIBLE);
                    edtTxt4Y.setVisibility(View.VISIBLE);
                    edtTxt5X.setVisibility(View.VISIBLE);
                    edtTxt5Y.setVisibility(View.VISIBLE);
                    edtTxt6X.setVisibility(View.VISIBLE);
                    edtTxt6Y.setVisibility(View.VISIBLE);
                    edtTxt7X.setVisibility(View.VISIBLE);
                    edtTxt7Y.setVisibility(View.VISIBLE);
                    edtTxt8X.setVisibility(View.VISIBLE);
                    edtTxt8Y.setVisibility(View.VISIBLE);

                    txtPos1.setVisibility(View.VISIBLE);
                    txtPos2.setVisibility(View.VISIBLE);
                    txtPos3.setVisibility(View.VISIBLE);
                    txtPos4.setVisibility(View.VISIBLE);
                    txtPos5.setVisibility(View.VISIBLE);
                    txtPos6.setVisibility(View.VISIBLE);
                    txtPos7.setVisibility(View.VISIBLE);
                    txtPos8.setVisibility(View.VISIBLE);
                }
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

        edtTxtDance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                choreo.setDance(bildNumb, editable.toString());
            }
        });

        edtTxtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                choreo.setComment(bildNumb, editable.toString());
            }
        });

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
                }

            }
        });
    }

    public void updateTxt(){
        edtTxt1X.setText("");
        edtTxt1Y.setText("");
        edtTxt2X.setText("");
        edtTxt2Y.setText("");
        edtTxt3X.setText("");
        edtTxt3Y.setText("");
        edtTxt4X.setText("");
        edtTxt4Y.setText("");
        edtTxt5X.setText("");
        edtTxt5Y.setText("");
        edtTxt6X.setText("");
        edtTxt6Y.setText("");
        edtTxt7X.setText("");
        edtTxt7Y.setText("");
        edtTxt8X.setText("");
        edtTxt8Y.setText("");

        /*edtTxtDance.setText("");
        edtTxtComment.setText("");*/


        edtTxt1X.setHint(((Double) choreo.getCoordX(bildNumb, 1)).toString());
        edtTxt1Y.setHint(((Double) choreo.getCoordY(bildNumb, 1)).toString());
        edtTxt2X.setHint(((Double) choreo.getCoordX(bildNumb, 2)).toString());
        edtTxt2Y.setHint(((Double) choreo.getCoordY(bildNumb, 2)).toString());
        edtTxt3X.setHint(((Double) choreo.getCoordX(bildNumb, 3)).toString());
        edtTxt3Y.setHint(((Double) choreo.getCoordY(bildNumb, 3)).toString());
        edtTxt4X.setHint(((Double) choreo.getCoordX(bildNumb, 4)).toString());
        edtTxt4Y.setHint(((Double) choreo.getCoordY(bildNumb, 4)).toString());
        edtTxt5X.setHint(((Double) choreo.getCoordX(bildNumb, 5)).toString());
        edtTxt5Y.setHint(((Double) choreo.getCoordY(bildNumb, 5)).toString());
        edtTxt6X.setHint(((Double) choreo.getCoordX(bildNumb, 6)).toString());
        edtTxt6Y.setHint(((Double) choreo.getCoordY(bildNumb, 6)).toString());
        edtTxt7X.setHint(((Double) choreo.getCoordX(bildNumb, 7)).toString());
        edtTxt7Y.setHint(((Double) choreo.getCoordY(bildNumb, 7)).toString());
        edtTxt8X.setHint(((Double) choreo.getCoordX(bildNumb, 8)).toString());
        edtTxt8Y.setHint(((Double) choreo.getCoordY(bildNumb, 8)).toString());

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String[] strings = choreo.getDanceArray().toArray(new String[0]);
            edtTxtDance.setAutofillHints(strings);
        } else {
            edtTxtDance.setHint(choreo.getDance(bildNumb));
        }*/
        edtTxtDance.setText(choreo.getDance(bildNumb));
        edtTxtComment.setText(choreo.getComment(bildNumb));
    }

}