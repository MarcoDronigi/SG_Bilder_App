package com.example.sgbilderapp;


import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final double CONVERSION_VALUE_COORD = 0.1269;
    final long BASE_ANIMATION_SPEED = 1000;

    private TextView txtComment;
    private TextView txtPrevX, txtX, txtFutX, txtPrevY, txtY, txtFutY;

    private ImageButton btnPlay;

    private ImageView raster;
    private ImageView marker_1, marker_2, marker_3, marker_4, marker_5, marker_6, marker_7, marker_8;
    private ImageView[] markerArray;

    private int[] drawableBlue;
    private int[] drawableRed;
    private int[] drawableGold;

    private Spinner spinnerPos;
    private Spinner spinnerAnimationSpeed;
    private Spinner spinnerDance;
    private Spinner spinnerLoopType;

    List<String> spinnerArray =  new ArrayList<>();
    boolean spinnerInterfaceInput = true;

    private int loopType = 1;
    // 0 = Anfang bis Ende
    // 1 = Anfang bis Ende Loop
    // 2 = Ein Bild
    // 3 = Loop ein Bild
    // 4 = Ein Tanz
    // 5 = Loop ein Tanz

    private boolean animateLoop = false;
    private int animateEnd;
    private int animateStart;

    private int width, height;

    private int posSelected = 1;

    private int bildNumb;

    private boolean animationOn = false;
    private double animationSpeed = 1;

    private Choreography choreo;

    private String pathChoreo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        pathChoreo = bundle.getString("pathChoreo");

        choreo = Choreography.readFromFile(pathChoreo);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        float density = displayMetrics.density;
        int widthDp = width / ((int) density);

        //Views bekommen Variablen zugeordnet

        raster = findViewById(R.id.pctRaster);
        raster.getLayoutParams().height = (int) (width * 1.3);

        TextView txtHeadline = findViewById(R.id.txtHeadline);
        txtHeadline.setText(choreo.getName());

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

        markerArray = new ImageView[] {marker_1, marker_2, marker_3, marker_4, marker_5, marker_6, marker_7, marker_8};


        //Arrays für die Marker Icons
        drawableBlue = new int[] {R.drawable.marker_blue_1, R.drawable.marker_blue_2, R.drawable.marker_blue_3, R.drawable.marker_blue_4, R.drawable.marker_blue_5, R.drawable.marker_blue_6, R.drawable.marker_blue_7, R.drawable.marker_blue_8};
        //drawableRed = new int[] {R.drawable.marker_rot_1, R.drawable.marker_rot_2, R.drawable.marker_rot_3, R.drawable.marker_rot_4, R.drawable.marker_rot_5, R.drawable.marker_rot_6, R.drawable.marker_rot_7, R.drawable.marker_rot_8};
        drawableGold = new int[] {R.drawable.marker_sg_1, R.drawable.marker_sg_2, R.drawable.marker_sg_3, R.drawable.marker_sg_4, R.drawable.marker_sg_5, R.drawable.marker_sg_6, R.drawable.marker_sg_7, R.drawable.marker_sg_8};

        btnPlay = findViewById(R.id.btnPlay);

        spinnerPos = findViewById(R.id.spinnerPos);
        spinnerAnimationSpeed = findViewById(R.id.spinnerAnimationSpeed);
        spinnerLoopType = findViewById(R.id.spinnerLoopType);
        spinnerDance = findViewById(R.id.spinnerDance);

        //Animationsgeschwindigkeit wird Default 1x gesetzt
        spinnerAnimationSpeed.setSelection(3);



        System.out.println("create");
        System.out.println(getIntent().getExtras().getInt("bildNumbEdit"));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        System.out.println("restart");
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("start");

        spinnerArray = choreo.getDanceArray();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDance.setAdapter(adapter);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();

        //Erstes Bild wird Initialisiert
        //TODO
        System.out.println("Restart: " + getIntent().getExtras().getInt("bildNumbEdit"));
        restartChoreo(getIntent().getExtras().getInt("bildNumbEdit"));
        //restartChoreo(0);

        System.out.println("resume");

        //Durch Spinnner kann User die hervorgehobene Position & die entsprechend davon angezeigten Coord auswählen
        spinnerPos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Davor ausgewählter Marker wird wieder mit Default Token anzgezeigt
                markerArray[posSelected - 1].setImageResource(drawableBlue[posSelected - 1]);

                posSelected = Integer.parseInt(spinnerPos.getSelectedItem().toString().substring(4));

                //Angezeigte Coord werden Updated
                updateTxt();

                //Neu ausgewählter Marker wird mit anderem Token angezeigt
                markerArray[posSelected - 1].setImageResource(drawableGold[posSelected - 1]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Durch Spinner kann User Animationsgeschwindigkeit auswählen
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


        // Durch Spinner kann User den Loop Type auswählen (Aufschlüsselung in Deklaration)
        spinnerLoopType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loopType = i;
                updateLoopType(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        // Auswahl des Tanzes
        spinnerDance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (spinnerInterfaceInput){
                    bildNumb = choreo.getDanceStart(spinnerDance.getSelectedItem().toString());
                    updateTxt();
                    updateMarker();
                    updateLoopType(loopType);
                }

                spinnerInterfaceInput = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        //Swip nach Rechts und Links springt zum nächsten und letzten Bild
        raster.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {}
            //prev Bild
            public void onSwipeRight() {
                if (bildNumb == 0){ //Fall "Startbild"
                    restartChoreo(choreo.getMaxBild());
                    btnPlay.setImageResource(R.drawable.ic_restart);
                } else {
                    if (bildNumb == choreo.getMaxBild()){
                        btnPlay.setImageResource(R.drawable.ic_play_arrow);
                    }

                    bildNumb--;
                    //Toast.makeText(MainActivity.this, ((Integer) bildNumb).toString(), Toast.LENGTH_SHORT).show();
                    updateTxt();
                    updateMarker();
                    updateLoopType(loopType);
                }
            }
            //next Bild
            public void onSwipeLeft() {
                if (bildNumb == choreo.getMaxBild()){ //Fall letztes Bild
                    restartChoreo(0);
                    btnPlay.setImageResource(R.drawable.ic_play_arrow);
                } else {
                    bildNumb++;
                    //Toast.makeText(MainActivity.this, ((Integer) bildNumb).toString(), Toast.LENGTH_SHORT).show();
                    updateTxt();
                    updateMarker();
                    updateLoopType(loopType);
                    if (bildNumb == choreo.getMaxBild()){
                        btnPlay.setImageResource(R.drawable.ic_restart);
                    }
                }

            }
            public void onSwipeBottom() {}

        });
    }


    // Button to start, stop and restart animation
    public void playAnimation(View view){
        if (animationOn){ // animation stop
            animationOn = false;
            btnPlay.setImageResource(R.drawable.ic_play_arrow);

        } else if(bildNumb == choreo.getMaxBild()){ // animation restart (case last Bild"
            restartChoreo(0);
            btnPlay.setImageResource(R.drawable.ic_play_arrow);
        } else { // animation start
            animationOn = true;
            animateChoreo();
            btnPlay.setImageResource(R.drawable.ic_stop);
        }
    }

    //TODO printToPdf
    public void printToPdf(View view) {
        Toast.makeText(MainActivity.this, "Noch nicht funktionabel", Toast.LENGTH_SHORT).show();
        choreo.choreoToLatex(pathChoreo);
//        for (int i = 0; i <= choreo.getMaxBild(); i++) {
//            System.out.println("\\def\\tanz{" + choreo.getDance(i) + "}");
//            System.out.println("\\def\\pos{(" + choreo.getCoordX(i, 1) + "," + choreo.getCoordY(i, 1) +
//                    "), (" + choreo.getCoordX(i, 2) + "," + choreo.getCoordY(i, 2) +
//                    "), (" + choreo.getCoordX(i, 3) + "," + choreo.getCoordY(i, 3) +
//                    "), (" + choreo.getCoordX(i, 4) + "," + choreo.getCoordY(i, 4) +
//                    "), (" + choreo.getCoordX(i, 5) + "," + choreo.getCoordY(i, 5) +
//                    "), (" + choreo.getCoordX(i, 6) + "," + choreo.getCoordY(i, 6) +
//                    "), (" + choreo.getCoordX(i, 7) + "," + choreo.getCoordY(i, 7) +
//                    "), (" + choreo.getCoordX(i, 8) + "," + choreo.getCoordY(i, 8) +
//                    ")}");
//            System.out.println("\\def\\definitionen{" + choreo.getComment(i) + "}");
//            System.out.println("\\tikz\\pic[scale=0.7]{flaeche};");
//            System.out.println("\\vspace{1cm}");
//            System.out.println();
//
//        }

        /*
        \def\tanz{Einmarsch}
        \def\pos{(0,0), (1.5,0), (0,1.5), (1.5,3), (0,3), (0,4.5), (1.5,1.5), (1.5,4.5)}
        \def\definitionen{Anfangsbild}
        \tikz\pic[scale=0.7]{flaeche};
        \vspace{1cm}

         */


    }

    // button to edit Bild (edit activity)
    public void editBild(View view){
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        //Bild id and path is shared with Edit Activity
        intent.putExtra("bildNumb", bildNumb);
        intent.putExtra("pathChoreo", pathChoreo);
        startActivity(intent);
    }

    // move marker to position of bildNumb
    public void updateMarker(){
        for (int i = 0; i < 8; i ++){
            int pxTranslationX = (int) (width * choreo.getCoordX(bildNumb, i + 1) * CONVERSION_VALUE_COORD / 2);
            int pxTranslationY = (int) (width * choreo.getCoordY(bildNumb, i + 1) * CONVERSION_VALUE_COORD * (-1) / 2);

            markerArray[i].setTranslationX(pxTranslationX);
            markerArray[i].setTranslationY(pxTranslationY);
        }
    }

    // Kommentare sowie Angezeigte Koord werden auf Wert laut "bildNumb" gestellt
    public void updateTxt(){
        if (spinnerDance.getSelectedItemPosition() != spinnerArray.indexOf(choreo.getDance(bildNumb))){
            spinnerInterfaceInput = false;
        }

        spinnerDance.setSelection(spinnerArray.indexOf(choreo.getDance(bildNumb)));

        txtComment.setText(choreo.getComment(bildNumb));

        if (bildNumb == 0){ //Fall erstes Bild
            txtPrevX.setText("0.0");
            txtPrevY.setText("0.0");
        } else{
            txtPrevX.setText(String.valueOf(choreo.getCoordX(bildNumb - 1, posSelected)));
            txtPrevY.setText(String.valueOf(choreo.getCoordY(bildNumb - 1, posSelected)));
        }

        txtX.setText(String.valueOf(choreo.getCoordX(bildNumb, posSelected)));
        txtY.setText(String.valueOf(choreo.getCoordY(bildNumb, posSelected)));

        if (bildNumb == choreo.getMaxBild()){ //Fall letztes Bild
            txtFutX.setText("0.0");
            txtFutY.setText("0.0");
        } else{
            txtFutX.setText(String.valueOf(choreo.getCoordX(bildNumb + 1, posSelected)));
            txtFutY.setText(String.valueOf(choreo.getCoordY(bildNumb + 1, posSelected)));
        }
    }

    //Animation wird generiert, welche einen Marker in einer Koord zu "bildNumb" bewegt
    public ObjectAnimator animateMarkerTo(ImageView marker, int pos, boolean isX){
        ObjectAnimator animation;

        if (isX){
            int pxTranslationX = (int) (width * choreo.getCoordX(bildNumb, pos) * CONVERSION_VALUE_COORD / 2);
            animation = ObjectAnimator.ofFloat(marker, "translationX", pxTranslationX);
            animation.setDuration((long) (BASE_ANIMATION_SPEED * animationSpeed));
        } else{
            int pxTranslationY = (int) (width * choreo.getCoordY(bildNumb, pos) * CONVERSION_VALUE_COORD * (-1) / 2);
            animation = ObjectAnimator.ofFloat(marker, "translationY", pxTranslationY);
            animation.setDuration((long) (BASE_ANIMATION_SPEED * animationSpeed));
        }

        return animation;
    }

    //Jeweils ein Bild wird Animiert
    public void animateChoreo (){
        if (animationOn){
            if (bildNumb < animateEnd){
                bildNumb++;

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

                //Trigger bei AnimationsEnde
                animatorSetChoreo.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        animateChoreo();
                    }
                });
            }
            else {
                if (animateLoop){
                    restartChoreo(animateStart);
                    animateChoreo();
                }
                else{
                    updateTxt();
                    animationOn = false;
                    if (bildNumb == choreo.getMaxBild()){
                        btnPlay.setImageResource(R.drawable.ic_restart);
                    }
                    else{
                        btnPlay.setImageResource(R.drawable.ic_play_arrow);
                        updateLoopType(loopType);
                    }

                }
            }
        }
        else {
            updateTxt();
            updateLoopType(loopType);
        }
    }

    //Punkte und Anzwigen werden auf ein bestimmten Punkt gesetzt
    public void restartChoreo (int restartAT){
        System.out.println("Restart triggerd");
        bildNumb = restartAT;
        System.out.println(bildNumb);
        updateMarker();
        updateTxt();
        updateLoopType(loopType);
    }

    public void updateLoopType(int i){
        switch (i){
            case 0:
                animateLoop = false;
                animateStart = 0;
                animateEnd = choreo.getMaxBild();
                break;
            case 1:
                animateLoop = true;
                animateStart = 0;
                animateEnd = choreo.getMaxBild();
                break;
            case 2:
                animateLoop = false;
                animateStart = bildNumb;
                animateEnd = bildNumb + 1;
                break;
            case 3:
                animateLoop = true;
                animateStart = bildNumb;
                animateEnd = bildNumb + 1;
                break;
            case 4:
                animateLoop = false;
                animateStart = choreo.getDanceStart(choreo.getDance(bildNumb));
                animateEnd = choreo.getDanceEnd(choreo.getDance(bildNumb)) + 1;
                break;
            case 5:
                animateLoop = true;
                animateStart = choreo.getDanceStart(choreo.getDance(bildNumb));
                animateEnd = choreo.getDanceEnd(choreo.getDance(bildNumb)) + 1;
                break;
        }
    }


}