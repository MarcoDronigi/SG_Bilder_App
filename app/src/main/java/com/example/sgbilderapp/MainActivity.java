package com.example.sgbilderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtDance, txtComment;
    private TextView txtPrevX, txtX, txtFutX, txtPrevY, txtY, txtFutY;

    private ImageButton btnPlay;
    private ImageButton btnMenu;
    private ImageButton btnEdit;

    private ImageView raster;

    private ImageView marker_1, marker_2, marker_3, marker_4, marker_5, marker_6, marker_7, marker_8;
    private ImageView[] marker;

    private int[] drawableBlue;
    private int[] drawableGold;

    private Spinner spinnerPos;
    private Spinner spinnerAnimationSpeed;
    private Spinner spinnerLoopType;

    private int loopType = 1;
    // 0 = Anfang bis Ende
    // 1 = Anfang bis Ende Loop
    // 2 = Ein Bild
    // 3 = Loop ein Bild
    // 4 = Ein Tanz
    // 5 = Loop ein Tanz

    private int width;
    private int height;

    private int posSelected = 1;

    private int bildNumb;

    private boolean animationOn = false;
    private double animationSpeed = 1;

    final double CONVERSION_VALUE_COORD = 0.1269;
    //0.1207
    final long BASE_SPEED = 1000;
    //final private int MAX_BILD = 26;

    private Choreography isItAMansWorld = new Choreography();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        //Views bekommen Variablen zugeordnet
        raster = findViewById(R.id.pctRaster);

        txtDance = findViewById(R.id.txtDance);
        txtComment = findViewById(R.id.txtComment);

        txtPrevX = findViewById(R.id.txtPrevX);
        txtX = findViewById(R.id.txtX);
        txtFutX = findViewById(R.id.txtFutX);
        txtPrevY = findViewById(R.id.txtPrevY);
        txtY = findViewById(R.id.txtY);
        txtFutY = findViewById(R.id.txtFutY);

        marker_1 = findViewById(R.id.marker_blue_1);
        marker_2 = findViewById(R.id.marker_blue_2);
        marker_3 = findViewById(R.id.marker_blue_3);
        marker_4 = findViewById(R.id.marker_blue_4);
        marker_5 = findViewById(R.id.marker_blue_5);
        marker_6 = findViewById(R.id.marker_blue_6);
        marker_7 = findViewById(R.id.marker_blue_7);
        marker_8 = findViewById(R.id.marker_blue_8);

        marker = new ImageView[] {marker_1, marker_2, marker_3, marker_4, marker_5, marker_6, marker_7, marker_8};
        //Arrays für die Marker Icons
        drawableBlue = new int[] {R.drawable.marker_blue_1, R.drawable.marker_blue_2, R.drawable.marker_blue_3, R.drawable.marker_blue_4, R.drawable.marker_blue_5, R.drawable.marker_blue_6, R.drawable.marker_blue_7, R.drawable.marker_blue_8};
        drawableGold = new int[] {R.drawable.marker_gold_1, R.drawable.marker_gold_2, R.drawable.marker_gold_3, R.drawable.marker_gold_4, R.drawable.marker_gold_5, R.drawable.marker_gold_6, R.drawable.marker_gold_7, R.drawable.marker_gold_8};

        btnPlay = findViewById(R.id.btnPlay);
        btnEdit = findViewById(R.id.btnEdit);
        btnMenu = findViewById(R.id.btnMenu);

        spinnerPos = findViewById(R.id.spinnerPos);
        spinnerAnimationSpeed = findViewById(R.id.spinnerAnimationSpeed);
        spinnerLoopType = findViewById(R.id.spinnerLoopType);

        //Animationsgeschwindigkeit wird Default 1x gesetzt
        spinnerAnimationSpeed.setSelection(3);

        //Erstes Bild wird Initialisiert
        restartChoreo(0);


        spinnerPos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                marker[posSelected - 1].setImageResource(drawableBlue[posSelected - 1]);

                posSelected = Integer.parseInt(spinnerPos.getSelectedItem().toString().substring(4));

                updateTxt();

                marker[posSelected - 1].setImageResource(drawableGold[posSelected - 1]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spinnerAnimationSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinnerAnimationSpeed.getSelectedItem().toString()){
                    case "0.25x":
                        animationSpeed = 4;
                        break;
                    case "0.5x":
                        animationSpeed = 2;
                        break;
                    case "0.75x":
                        animationSpeed = 1.5;
                        break;
                    case "1x":
                        animationSpeed = 1;
                        break;
                    case "1.5x":
                        animationSpeed = 0.75;
                        break;
                    case "2x":
                        animationSpeed = 0.5;
                        break;
                    case "4x":
                        animationSpeed = 0.25;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //TODO Implement one dance Loops
        spinnerLoopType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 4 || i == 5){
                    spinnerLoopType.setSelection(loopType);
                    Toast.makeText(MainActivity.this, "Die Funktion wurde noch nicht implementiert", Toast.LENGTH_SHORT).show();
                }
                else{
                    loopType = i;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (animationOn){
                    animationOn = false;
                    btnPlay.setImageResource(R.drawable.ic_play_arrow);

                } else if(bildNumb == isItAMansWorld.getMAX_BILD()){
                    restartChoreo(0);
                    btnPlay.setImageResource(R.drawable.ic_play_arrow);
                } else {
                    animationOn = true;
                    animateChoreo();
                    btnPlay.setImageResource(R.drawable.ic_stop);
                }

            }

        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://onedrive.live.com/?authkey=%21AIuE2fVU8xePeB8&id=3E68918F98BDA615%2122770&cid=3E68918F98BDA615"));
                startActivity(browserIntent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Funktion noch nicht implementiert!", Toast.LENGTH_SHORT).show();
            }
        });


        raster.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {

            }
            //prev Bild
            public void onSwipeRight() {
                if (bildNumb == 0){
                    Toast.makeText(MainActivity.this, "erstes Bild", Toast.LENGTH_SHORT).show();
                } else {
                    if (bildNumb == isItAMansWorld.getMAX_BILD()){
                        btnPlay.setImageResource(R.drawable.ic_play_arrow);
                    }

                    bildNumb--;
                    updateTxt();
                    updateMarker();
                }
            }
            //next Bild
            public void onSwipeLeft() {
                if (bildNumb == isItAMansWorld.getMAX_BILD()){
                    //Toast.makeText(MainActivity.this, "letztes Bild", Toast.LENGTH_SHORT).show();
                } else {
                    bildNumb++;
                    updateTxt();
                    updateMarker();
                    if (bildNumb == isItAMansWorld.getMAX_BILD()){
                        btnPlay.setImageResource(R.drawable.ic_restart);
                    }
                }

            }
            public void onSwipeBottom() {

            }
        });
    }

    public void moveMarker(ImageView marker, int pos){
        int pxTranslationX = (int) (width * isItAMansWorld.getCoordX(bildNumb, pos) * CONVERSION_VALUE_COORD / 2);
        int pxTranslationY = (int) (width * isItAMansWorld.getCoordY(bildNumb, pos) * CONVERSION_VALUE_COORD * (-1) / 2);


        marker.setTranslationX(pxTranslationX);
        marker.setTranslationY(pxTranslationY);
    }

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

    public void updateTxt(){

        txtDance.setText(isItAMansWorld.getDance(bildNumb));
        txtComment.setText(isItAMansWorld.getComment(bildNumb));

        if (bildNumb == 0){
            txtPrevX.setText("0.0");
            txtPrevY.setText("0.0");
        } else{
            txtPrevX.setText(String.valueOf(isItAMansWorld.getCoordX(bildNumb - 1, posSelected)));
            txtPrevY.setText(String.valueOf(isItAMansWorld.getCoordY(bildNumb - 1, posSelected)));
        }

        txtX.setText(String.valueOf(isItAMansWorld.getCoordX(bildNumb, posSelected)));
        txtY.setText(String.valueOf(isItAMansWorld.getCoordY(bildNumb, posSelected)));

        if (bildNumb == isItAMansWorld.getMAX_BILD()){
            txtFutX.setText("0.0");
            txtFutY.setText("0.0");
        } else{
            txtFutX.setText(String.valueOf(isItAMansWorld.getCoordX(bildNumb + 1, posSelected)));
            txtFutY.setText(String.valueOf(isItAMansWorld.getCoordY(bildNumb + 1, posSelected)));
        }
    }

    public ObjectAnimator animateMarkerTo(ImageView marker, int pos, boolean isX){
        ObjectAnimator animation;

        if (isX){
            int pxTranslationX = (int) (width * isItAMansWorld.getCoordX(bildNumb, pos) * CONVERSION_VALUE_COORD / 2);
            animation = ObjectAnimator.ofFloat(marker, "translationX", pxTranslationX);
            animation.setDuration((long) (BASE_SPEED * animationSpeed));
        } else{
            int pxTranslationY = (int) (width * isItAMansWorld.getCoordY(bildNumb, pos) * CONVERSION_VALUE_COORD * (-1) / 2);
            animation = ObjectAnimator.ofFloat(marker, "translationY", pxTranslationY);
            animation.setDuration((long) (BASE_SPEED * animationSpeed));
        }

        return animation;
    }

    //TODO Different kind of Animation

    public void animateChoreo (){
        if (bildNumb < isItAMansWorld.getMAX_BILD()){
            if (animationOn){
                bildNumb++;

                txtDance.setText(isItAMansWorld.getDance(bildNumb));
                txtComment.setText(isItAMansWorld.getComment(bildNumb));

                updateTxt();

                AnimatorSet animatorSetChoreo = new AnimatorSet();

                animatorSetChoreo.playTogether(
                        animateMarkerTo(marker_1, 1, true), animateMarkerTo(marker_1, 1, false),
                        animateMarkerTo(marker_2, 2, true), animateMarkerTo(marker_2, 2, false),
                        animateMarkerTo(marker_3, 3, true), animateMarkerTo(marker_3, 3, false),
                        animateMarkerTo(marker_4, 4, true), animateMarkerTo(marker_4, 4, false),
                        animateMarkerTo(marker_5, 5, true), animateMarkerTo(marker_5, 5, false),
                        animateMarkerTo(marker_6, 6, true), animateMarkerTo(marker_6, 6, false),
                        animateMarkerTo(marker_7, 7, true), animateMarkerTo(marker_7, 7, false),
                        animateMarkerTo(marker_8, 8, true), animateMarkerTo(marker_8, 8, false));

                animatorSetChoreo.start();


                animatorSetChoreo.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);


                        if (loopType == 0 || loopType == 1){
                            animateChoreo();
                        }
                        if (loopType == 2){
                            animationOn = false;
                            btnPlay.setImageResource(R.drawable.ic_play_arrow);
                        }
                        if (loopType == 3){
                            bildNumb--;
                            updateMarker();
                            animateChoreo();
                        }

                    }
                });

            } else {
                updateTxt();
            }

        } else{
            if (loopType == 0){
                animationOn = false;
                btnPlay.setImageResource(R.drawable.ic_restart);
                //Toast.makeText(MainActivity.this, "Letztes Bild erreicht", Toast.LENGTH_SHORT).show();
            }
            if (loopType == 1){
                restartChoreo(0);
                animateChoreo();
            }
        }
    }

    public void restartChoreo (int restartAT){
        bildNumb = restartAT;
        updateMarker();
        updateTxt();
    }
}