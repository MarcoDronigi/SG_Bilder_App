package com.example.sgbilderapp;


import androidx.appcompat.app.AlertDialog;
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

    final double CONVERSION_VALUE_COORD = 0.1269;

    private ImageView[] marker;
    private EditText[] editTexts;
    private TextView[] txtPosArray;
    private EditText edtTxtDance, edtTxtComment;

    private int height;
    private int width;

    private int bildNumb;

    private Choreography choreo;

    private boolean coordView = true;

    private Intent intentEditToMain;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        ImageView raster = findViewById(R.id.pctRaster);
        raster.setLongClickable(true);


        TextView txtHeadline = findViewById(R.id.txtHeadline);

        ImageView marker_1 = findViewById(R.id.marker_blue_1);
        ImageView marker_2 = findViewById(R.id.marker_blue_2);
        ImageView marker_3 = findViewById(R.id.marker_blue_3);
        ImageView marker_4 = findViewById(R.id.marker_blue_4);
        ImageView marker_5 = findViewById(R.id.marker_blue_5);
        ImageView marker_6 = findViewById(R.id.marker_blue_6);
        ImageView marker_7 = findViewById(R.id.marker_blue_7);
        ImageView marker_8 = findViewById(R.id.marker_blue_8);

        marker = new ImageView[] {marker_1, marker_2, marker_3, marker_4, marker_5, marker_6, marker_7, marker_8};

        EditText edtTxt1X = findViewById(R.id.edtTxt1X);
        EditText edtTxt1Y = findViewById(R.id.edtTxt1Y);
        EditText edtTxt2X = findViewById(R.id.edtTxt2X);
        EditText edtTxt2Y = findViewById(R.id.edtTxt2Y);
        EditText edtTxt3X = findViewById(R.id.edtTxt3X);
        EditText edtTxt3Y = findViewById(R.id.edtTxt3Y);
        EditText edtTxt4X = findViewById(R.id.edtTxt4X);
        EditText edtTxt4Y = findViewById(R.id.edtTxt4Y);
        EditText edtTxt5X = findViewById(R.id.edtTxt5X);
        EditText edtTxt5Y = findViewById(R.id.edtTxt5Y);
        EditText edtTxt6X = findViewById(R.id.edtTxt6X);
        EditText edtTxt6Y = findViewById(R.id.edtTxt6Y);
        EditText edtTxt7X = findViewById(R.id.edtTxt7X);
        EditText edtTxt7Y = findViewById(R.id.edtTxt7Y);
        EditText edtTxt8X = findViewById(R.id.edtTxt8X);
        EditText edtTxt8Y = findViewById(R.id.edtTxt8Y);

        editTexts = new EditText[]{edtTxt1X, edtTxt1Y, edtTxt2X, edtTxt2Y, edtTxt3X, edtTxt3Y, edtTxt4X, edtTxt4Y, edtTxt5X, edtTxt5Y, edtTxt6X, edtTxt6Y, edtTxt7X, edtTxt7Y, edtTxt8X, edtTxt8Y};

        TextView txtPos1 = findViewById(R.id.txtPos1);
        TextView txtPos2 = findViewById(R.id.txtPos2);
        TextView txtPos3 = findViewById(R.id.txtPos3);
        TextView txtPos4 = findViewById(R.id.txtPos4);
        TextView txtPos5 = findViewById(R.id.txtPos5);
        TextView txtPos6 = findViewById(R.id.txtPos6);
        TextView txtPos7 = findViewById(R.id.txtPos7);
        TextView txtPos8 = findViewById(R.id.txtPos8);

        txtPosArray = new TextView[]{txtPos1, txtPos2, txtPos3, txtPos4, txtPos5, txtPos6, txtPos7, txtPos8};

        edtTxtDance = findViewById(R.id.edtTextDance);
        edtTxtComment = findViewById(R.id.edtTxtComment);

        choreo = Choreography.readFromFile(getIntent().getExtras().getString("pathChoreo"));
        txtHeadline.setText(choreo.getName());

        restartChoreo(getIntent().getExtras().getInt("bildNumb"));

        intentEditToMain = new Intent(EditActivity.this, MainActivity.class);
        intentEditToMain. putExtra("pathChoreo", getIntent().getExtras().getString("pathChoreo"));
        intentEditToMain.removeExtra("bildNumbEdit");
        intentEditToMain.putExtra("bildNumbEdit", bildNumb);
        System.out.println("Put Extra: " + bildNumb);

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

                intentEditToMain.removeExtra("bildNumbEdit");
                intentEditToMain.putExtra("bildNumbEdit", bildNumb);
                System.out.println("Put Extra: " + bildNumb);
            }
            //next Bild
            public void onSwipeLeft() {
                if (bildNumb == choreo.getMaxBild()) { //Fall letztes Bild
                    bildNumb = 0;
                } else {
                    bildNumb++;
                }

                updateTxt();
                updateMarker();
                intentEditToMain.removeExtra("bildNumbEdit");
                intentEditToMain.putExtra("bildNumbEdit", bildNumb);
                System.out.println("Put Extra: " + bildNumb);

            }
            public void onSwipeBottom() {}

            @Override
            public void onLongClick() {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setTitle("Bild " + bildNumb);
                builder.setPositiveButton("Hinzufügen", (dialogInterface, i) -> {
                    choreo.addBlankBild(bildNumb);
                    bildNumb++;
                    updateTxt();
                    updateMarker();

                    intentEditToMain.removeExtra("bildNumbEdit");
                    intentEditToMain.putExtra("bildNumbEdit", bildNumb);
                    System.out.println("Put Extra: " + bildNumb);

                    Toast.makeText(EditActivity.this, "Bild wurde erfolgreich erstellt!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Löschen", (dialogInterface, i) -> {
                    choreo.deleteBild(bildNumb);
                    Toast.makeText(EditActivity.this, "Bild wurde erfolgreich gelöscht!", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Schließen", (dialogInterface, i) -> {

                });
                builder.create().show();
            }
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


    public void changeEdtText(View view){
        ImageButton btnChangeEdtMode = findViewById(R.id.btnChangeEdtMode);

        if (coordView) {
            coordView = false;

            btnChangeEdtMode.setImageResource(R.drawable.ic_round_edit_location_alt_24);

            for (EditText editText : editTexts){
                editText.setVisibility(View.GONE);
            }

            for (TextView txtView : txtPosArray){
                txtView.setVisibility(View.GONE);
            }

            edtTxtDance.setVisibility(View.VISIBLE);
            edtTxtComment.setVisibility(View.VISIBLE);

        } else {
            coordView = true;

            btnChangeEdtMode.setImageResource(R.drawable.ic_comment);

            edtTxtDance.setVisibility(View.GONE);
            edtTxtComment.setVisibility(View.GONE);

            for (EditText editText : editTexts){
                editText.setVisibility(View.VISIBLE);
            }

            for (TextView txtView : txtPosArray){
                txtView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void save(View view){
        try {
            choreo.save(getIntent().getExtras().getString("pathChoreo"));
        } catch (IOException e) {
            Toast.makeText(EditActivity.this, "Fehler", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        startActivity(intentEditToMain);
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
        for (int i = 0; i < 8; i++) {
            moveMarker(marker[i], i + 1);
        }
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
                    edtTxt.setHint(edtTxt.getText().toString());
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    //TODO
    public void updateTxt(){

        boolean isX = true;
        int pos = 1;

        for (int i = 0; i < 16; i++) {
            editTexts[i].setText("");

            if (isX){
                editTexts[i].setHint(((Double) choreo.getCoordX(bildNumb, pos)).toString());
                isX = false;
            }
            else{
                editTexts[i].setHint(((Double) choreo.getCoordY(bildNumb, pos)).toString());
                pos++;
                isX = true;
            }
        }

        //TODO
        /*edtTxtDance.setText("");
        edtTxtComment.setText("");*/


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