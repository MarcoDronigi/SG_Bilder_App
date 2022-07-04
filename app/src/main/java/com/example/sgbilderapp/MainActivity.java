package com.example.sgbilderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtHeadline;
    private TextView txtComment;
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
    private Spinner spinnerDance;

    List<String> spinnerArray =  new ArrayList<String>();
    int spinnerInterfaceInput = 1;

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

    private int width;
    private int height;

    private int posSelected = 1;

    private int bildNumb;

    private boolean animationOn = false;
    private double animationSpeed = 1;

    final double CONVERSION_VALUE_COORD = 0.1269;
    //0.1207
    final long BASE_SPEED = 1000;

    private Choreography choreo;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String pathChoreo = bundle.getString("pathChoreo");

        choreo = Choreography.readFromFile(this, pathChoreo);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        //Views bekommen Variablen zugeordnet
        raster = findViewById(R.id.pctRaster);

        txtHeadline = findViewById(R.id.txtHeadline);
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

        marker = new ImageView[] {marker_1, marker_2, marker_3, marker_4, marker_5, marker_6, marker_7, marker_8};
        //Arrays für die Marker Icons
        drawableBlue = new int[] {R.drawable.marker_blue_1, R.drawable.marker_blue_2, R.drawable.marker_blue_3, R.drawable.marker_blue_4, R.drawable.marker_blue_5, R.drawable.marker_blue_6, R.drawable.marker_blue_7, R.drawable.marker_blue_8};
        drawableGold = new int[] {R.drawable.marker_gold_1, R.drawable.marker_gold_2, R.drawable.marker_gold_3, R.drawable.marker_gold_4, R.drawable.marker_gold_5, R.drawable.marker_gold_6, R.drawable.marker_gold_7, R.drawable.marker_gold_8};

        btnPlay = findViewById(R.id.btnPlay);
        btnEdit = findViewById(R.id.btnEdit);
        btnMenu = findViewById(R.id.btnCloud);

        spinnerPos = findViewById(R.id.spinnerPos);
        spinnerAnimationSpeed = findViewById(R.id.spinnerAnimationSpeed);
        spinnerLoopType = findViewById(R.id.spinnerLoopType);
        spinnerDance = findViewById(R.id.spinnerDance);

        //Animationsgeschwindigkeit wird Default 1x gesetzt
        spinnerAnimationSpeed.setSelection(3);

        spinnerArray = choreo.getDanceArray();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDance.setAdapter(adapter);

        //Erstes Bild wird Initialisiert
        restartChoreo(0);

        //Durch Spinnner kann User die hervorgehobene Position & die entsprechend davon angezeigten Coord auswählen
        spinnerPos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Davor ausgewählter Marker wird wieder mit Default Token anzgezeigt
                marker[posSelected - 1].setImageResource(drawableBlue[posSelected - 1]);

                posSelected = Integer.parseInt(spinnerPos.getSelectedItem().toString().substring(4));

                //Angezeigte Coord werden Updated
                updateTxt();

                //Neu ausgewählter Marker wird mit anderem Token angezeigt
                marker[posSelected - 1].setImageResource(drawableGold[posSelected - 1]);
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

                if (spinnerInterfaceInput == 1){
                    bildNumb = choreo.getDanceStart(spinnerDance.getSelectedItem().toString());
                    //Toast.makeText(MainActivity.this, ((Integer) bildNumb).toString(), Toast.LENGTH_SHORT).show();
                    updateTxt();
                    updateMarker();
                    updateLoopType(loopType);
                }

                spinnerInterfaceInput = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // Button zum Start und Stop der Animationen (& Restart Choero
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (animationOn){ // Animation wird gestoppt
                    animationOn = false;
                    btnPlay.setImageResource(R.drawable.ic_play_arrow);

                } else if(bildNumb == choreo.getMaxBild()){ //Choero wird Resetet (Fall letztes Bild)
                    restartChoreo(0);
                    btnPlay.setImageResource(R.drawable.ic_play_arrow);
                } else { //Animation wird gestartet
                    animationOn = true;
                    animateChoreo();
                    btnPlay.setImageResource(R.drawable.ic_stop);
                }

            }

        });

        // Derzeit Bringt Buttton User in die Cloud
        // Gedacht: Menü
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://onedrive.live.com/?authkey=%21AIuE2fVU8xePeB8&id=3E68918F98BDA615%2122770&cid=3E68918F98BDA615"));
                startActivity(browserIntent);
            }
        });

        //TODO Funktion zur Berabeitung von Bildern
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("bildNumb", bildNumb);
                intent.putExtra("pathChoreo", pathChoreo);
                startActivity(intent);
            }
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

    // Kommentare sowie Angezeigte Koord werden auf Wert laut "bildNumb" gestellt
    public void updateTxt(){
        spinnerInterfaceInput = 0;
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
            animation.setDuration((long) (BASE_SPEED * animationSpeed));
        } else{
            int pxTranslationY = (int) (width * choreo.getCoordY(bildNumb, pos) * CONVERSION_VALUE_COORD * (-1) / 2);
            animation = ObjectAnimator.ofFloat(marker, "translationY", pxTranslationY);
            animation.setDuration((long) (BASE_SPEED * animationSpeed));
        }

        return animation;
    }

    //Jeweils ein Bild wird Animiert
    //TODO Different kind of Animation
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
        bildNumb = restartAT;
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

    /*private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(isItAMansWorld);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}