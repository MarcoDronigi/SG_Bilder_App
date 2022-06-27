package com.example.sgbilderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private TextView txtDance, txtComment;
    private TextView txtPrevX, txtX, txtFutX, txtPrevY, txtY, txtFutY;

    private ImageButton btnPlay;
    private ImageButton btnMenu;
    private ImageButton btnEdit;

    private ArrayList<Bild> bilder;

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

    private int posSelected = 1;

    private int bildNumb = 0;
    private int bildNumbMove;

    private boolean animationOn = false;
    private double animationSpeed = 1;

    final long baseSpeed = 1000;
    final private int maxBild = 19;

    private Choreography isItAMansWorld = new Choreography();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        double[] bild0 = {0, 0, 1.5, 0, 0, 1.5, 1.5, 3, 0, 3, 0, 4.5, 1.5, 1.5, 1.5, 4.5};
        double[] bild1 = {-3, -1.5, 0, -1.5, -1.5, 0, 3, 1.5, 0, 1.5, 1.5, 3, 1.5, 0, 4.5, 3};
        double[] bild2 = {-1.5, -1.5, 1.5, -1.5, 0, 0, 4.5, 1.5, 1.5, 1.5, 3, 3, 3, 0, 6, 3};
        double[] bild3 = {-3, -1.5, 0, -1.5, -1.5, 0, 3, 1.5, 0, 1.5, 1.5, 3, 1.5, 0, 4.5, 3};
        double[] bild4 = {-6, -1.5, -3, -4.5, -4.5, -3, -1.5, -3, -4.5, 0, -1.5, 0, -3, -1.5, 0, -1.5};
        double[] bild5 = {-6, -3, -3, -6, -4.5, -4.5, -1.5, -4.5, -4.5, -1.5, -1.5, -1.5, -3, -3, 0, -3};
        double[] bild6 = {0, -3, 3, -6, 1.5, -4.5, 4.5, -4.5, 1.5, -1.5, 4.5, -1.5, 3, -3, 6, -3};
        double[] bild7 = {-1.5, 0, 1.5, -3, 0, -1.5, 3, -1.5, 0, 1.5, 3, 1.5, 1.5, 0, 4.5, 0};
        double[] bild8 = {-4.5, 3, 0, 1.5, -3, 1.5, 3, 1.5, -1.5, 3, 1.5, 3, 0, 4.5, 4.5, 3};
        double[] bild9 = {-2, -3, 0, -6, -2, -6, 2, -6, -1, -4.5, 1, -4.5, 0, -3, 2, -3};
        double[] bild10 = {-2, 1.5, 0, -1.5, -2, -1.5, 2, -1.5, -1, 0, 1, 0, 0, 1.5, 2, 1.5};
        double[] bild11 = {-6, -3, -3, -6, -6, -6, 6, 3, -4.5, -4.5, 4.5, 4.5, 3, 6, 6, 6};
        double[] bild12 = {0, -4.5, 1.5, -6, 0, -6, 0, 4.5, 1.5, -4.5, -1.5, 4.5, -1.5, 6, 0, 6};
        double[] bild13 = {3, -4.5, 4.5, -6, 3, -6, -3, 4.5, 4.5, -4.5, -4.5, 4.5, -4.5, 6, -3, 6};
        double[] bild14 = {2.25, -3.75, 3, -4.5, 1.5, -4.5, -2.25, 3.75, 3, -3, -3, 3, -3, 4.5, -1.5, 4.5};
        double[] bild15 = {1.5, 0, 1.5, -1.5, 0, -1.5, -1.5, 0, 1.5, 1.5, -1.5, -1.5, -1.5, 1.5, 0, 1.5};
        double[] bild16 = {-1.5, -4.5, -1.5, -6, -3, -6, -4.5, -4.5, -1.5, -3, -4.5, -6, -4.5, -3, -3, -3};
        double[] bild17 = {0, -3, 0, -4.5, -1.5, -4.5, -3, -3, 0, -1.5, -3, -4.5, -3, -1.5, -1.5, -1.5};
        double[] bild18 = {3, -1.5, 4.5, -3, 3, -4.5, 0, -4.5, 1.5, 0, 1.5, -6, -1.5, -3, 0, -1.5};
        double[] bild19 = {4.5, -1.5, 6, -3, 4.5, -4.5, 1.5, -4.5, 3, 0, 3, -6, 0, -3, 1.5, -1.5};
        //double[] bild20 = {};
        //double[] bild21 = {};

        bilder = new ArrayList<Bild>();
        bilder.add(new Bild("Einmarsch", "Anfangsbild",false, bild0));
        bilder.add(new Bild("Einmarsch", "Standing Spin",false, bild1));
        bilder.add(new Bild("Einmarsch", "Ende Drehung\nLinker Fuß Herr",false, bild2));
        bilder.add(new Bild("1.1 WW - 1. Wiener Walzer", "Gong\nCojones Herr", false, bild3));
        bilder.add(new Bild("1.1 WW - 1. Wiener Walzer", "Ende RD\nLinker Fuß Herr", false, bild4));
        bilder.add(new Bild("1.1 WW - 1. Wiener Walzer", "Achse\nLinker Fuß Herr", false, bild5));
        bilder.add(new Bild("1.1 WW - 1. Wiener Walzer", "Schlag 3 RD\nLinker Fuß Herr", false, bild6));
        bilder.add(new Bild("1.1 WW - 1. Wiener Walzer", "", false, bild7));
        bilder.add(new Bild("1.1 WW - 1. Wiener Walzer", "Change of Direction\nRechter Fuß Herr", false, bild8));
        bilder.add(new Bild("1.2 Qs - 1. Quickstep", "Fleckerl", false, bild9));
        bilder.add(new Bild("1.2 Qs - 1. Quickstep", "Außenseitlicher Wechsel\nRechter Fuß Herr", false, bild10));
        bilder.add(new Bild("1.2 Qs - 1. Quickstep", "2. Step Hop", false, bild11));
        bilder.add(new Bild("1.2 Qs - 1. Quickstep", "Slow nach 4. Step Hop\nRechter Fuß Herr", false, bild12));
        bilder.add(new Bild("1.2 Qs - 1. Quickstep", "", false, bild13));
        bilder.add(new Bild("1.2 Qs - 1. Quickstep", "Dreieck\nRechter Fuß Herr", false, bild14));
        bilder.add(new Bild("1.2 Qs - 1. Quickstep", "Kick Dame", false, bild15));
        bilder.add(new Bild("1.3 TG - 1. Tango", "", false, bild16));
        bilder.add(new Bild("1.3 TG - 1. Tango", "Verschieben mit Schlägen 1 bis 6", false, bild17));
        bilder.add(new Bild("1.3 TG - 1. Tango", "Ende Fallaway\nRechter Fuß Herr", false, bild18));
        bilder.add(new Bild("1.3 TG - 1. Tango", "Linker Fuß Herr außenseitlich", false, bild19));
        //bilder.add(new Bild("", "", false, bild20));
        //bilder.add(new Bild("", "", false, bild21));


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
        drawableBlue = new int[] {R.drawable.marker_blue_1, R.drawable.marker_blue_2, R.drawable.marker_blue_3, R.drawable.marker_blue_4, R.drawable.marker_blue_5, R.drawable.marker_blue_6, R.drawable.marker_blue_7, R.drawable.marker_blue_8};
        drawableGold = new int[] {R.drawable.marker_gold_1, R.drawable.marker_gold_2, R.drawable.marker_gold_3, R.drawable.marker_gold_4, R.drawable.marker_gold_5, R.drawable.marker_gold_6, R.drawable.marker_gold_7, R.drawable.marker_gold_8};

        btnPlay = findViewById(R.id.btnPlay);
        btnEdit = findViewById(R.id.btnEdit);
        btnMenu = findViewById(R.id.btnMenu);

        spinnerPos = findViewById(R.id.spinnerPos);
        spinnerAnimationSpeed = findViewById(R.id.spinnerAnimationSpeed);
        spinnerLoopType = findViewById(R.id.spinnerLoopType);


        spinnerAnimationSpeed.setSelection(3);

        updateTxt();

        updateMarker();


        spinnerPos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                marker[posSelected - 1].setImageResource(drawableBlue[posSelected - 1]);

                posSelected = Integer.parseInt(spinnerPos.getSelectedItem().toString().substring(4));

                updateTxt();

                marker[posSelected - 1].setImageResource(drawableGold[posSelected - 1]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
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

        spinnerLoopType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loopType = i;
                //Toast.makeText(MainActivity.this, ((Integer) i).toString(), Toast.LENGTH_SHORT).show();
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

                } else if(bildNumb == maxBild){
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
                //Toast.makeText(MainActivity.this, "Funktion noch nicht implementiert!", Toast.LENGTH_SHORT).show();
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
                    if (bildNumb == maxBild){
                        btnPlay.setImageResource(R.drawable.ic_play_arrow);
                    }

                    bildNumb--;
                    updateTxt();
                    updateMarker();
                    bildNumbMove = bildNumb;
                }
            }
            //next Bild
            public void onSwipeLeft() {
                if (bildNumb == maxBild){
                    //Toast.makeText(MainActivity.this, "letztes Bild", Toast.LENGTH_SHORT).show();
                } else {
                    bildNumb++;
                    updateTxt();
                    updateMarker();
                    bildNumbMove = bildNumb;
                    if (bildNumb == maxBild){
                        btnPlay.setImageResource(R.drawable.ic_restart);
                    }
                }

            }
            public void onSwipeBottom() {

            }
        });
    }

    public void moveMarker(ImageView marker, int pos, int bild){
        marker.setTranslationX(0);
        marker.setTranslationY(0);

        Resources r = marker.getResources();
        int pxX = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                (float) ((bilder.get(bildNumb).getPositionX(pos)+8)*18.125+25),
                r.getDisplayMetrics()
        );
        int pxY = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                (float) (((bilder.get(bildNumb).getPositionY(pos)-8)*(-1))*18.125+38),
                r.getDisplayMetrics()
        );

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) marker.getLayoutParams();
        params.topMargin = pxY;
        params.leftMargin = pxX;
        marker.setLayoutParams(params);

    }

    public void updateMarker(){
        moveMarker(marker_1, 1, bildNumb);
        moveMarker(marker_2, 2, bildNumb);
        moveMarker(marker_3, 3, bildNumb);
        moveMarker(marker_4, 4, bildNumb);
        moveMarker(marker_5, 5, bildNumb);
        moveMarker(marker_6, 6, bildNumb);
        moveMarker(marker_7, 7, bildNumb);
        moveMarker(marker_8, 8, bildNumb);
    }

    public void updateTxt(){

        txtDance.setText(bilder.get(bildNumb).getDance());
        txtComment.setText(bilder.get(bildNumb).getComment());

        if (bildNumb == 0){
            txtPrevX.setText("0.0");
            txtPrevY.setText("0.0");
        } else{
            txtPrevX.setText(String.valueOf(bilder.get(bildNumb - 1).getPositionX(posSelected)));
            txtPrevY.setText(String.valueOf(bilder.get(bildNumb - 1).getPositionY(posSelected)));
        }

        txtX.setText(String.valueOf(bilder.get(bildNumb).getPositionX(posSelected)));
        txtY.setText(String.valueOf(bilder.get(bildNumb).getPositionY(posSelected)));

        if (bildNumb == maxBild){
            txtFutX.setText("0.0");
            txtFutY.setText("0.0");
        } else{
            txtFutX.setText(String.valueOf(bilder.get(bildNumb + 1).getPositionX(posSelected)));
            txtFutY.setText(String.valueOf(bilder.get(bildNumb + 1).getPositionY(posSelected)));
        }
    }

    public ObjectAnimator animateMarkerTo(ImageView marker, int pos, int bild, boolean isX){
        ObjectAnimator animation;

        Resources r = marker.getResources();

        if (isX){
            int pxXAlt = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    (float) ((bilder.get(bildNumbMove).getPositionX(pos)+8)*18.125+25),
                    r.getDisplayMetrics()
            );

            int pxXNeu = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    (float) ((bilder.get(bild).getPositionX(pos)+8)*18.125+25),
                    r.getDisplayMetrics()
            );
            animation = ObjectAnimator.ofFloat(marker, "translationX", pxXNeu-pxXAlt);
            animation.setDuration((long) (baseSpeed * animationSpeed));
        } else{

            int pxYAlt = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    (float) (((bilder.get(bildNumbMove).getPositionY(pos)-8)*(-1))*18.125+38),
                    r.getDisplayMetrics()
            );

            int pxYNeu = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    (float) (((bilder.get(bild).getPositionY(pos)-8)*(-1))*18.125+38),
                    r.getDisplayMetrics()
            );

            animation = ObjectAnimator.ofFloat(marker, "translationY", pxYNeu-pxYAlt);
            animation.setDuration((long) (baseSpeed * animationSpeed));
        }

        return animation;
    }

    //TODO Different kind of Animation

    public void animateChoreo (){
        if (bildNumb < maxBild){
            if (animationOn){
                bildNumb++;

                txtDance.setText(bilder.get(bildNumb).getDance());
                txtComment.setText(bilder.get(bildNumb).getComment());

                updateTxt();

                AnimatorSet animatorSetChoreo = new AnimatorSet();

                animatorSetChoreo.playTogether(
                        animateMarkerTo(marker_1, 1, bildNumb, true), animateMarkerTo(marker_1, 1, bildNumb, false),
                        animateMarkerTo(marker_2, 2, bildNumb, true), animateMarkerTo(marker_2, 2, bildNumb, false),
                        animateMarkerTo(marker_3, 3, bildNumb, true), animateMarkerTo(marker_3, 3, bildNumb, false),
                        animateMarkerTo(marker_4, 4, bildNumb, true), animateMarkerTo(marker_4, 4, bildNumb, false),
                        animateMarkerTo(marker_5, 5, bildNumb, true), animateMarkerTo(marker_5, 5, bildNumb, false),
                        animateMarkerTo(marker_6, 6, bildNumb, true), animateMarkerTo(marker_6, 6, bildNumb, false),
                        animateMarkerTo(marker_7, 7, bildNumb, true), animateMarkerTo(marker_7, 7, bildNumb, false),
                        animateMarkerTo(marker_8, 8, bildNumb, true), animateMarkerTo(marker_8, 8, bildNumb, false));

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
                            bildNumb++;
                            //updateTxt();
                            animateChoreo();
                            bildNumb--;
                            //updateMarker();
                        }

                    }
                });

            } else {
                //Toast.makeText(this, "Animation has stopped with " + ((Integer) bildNumb).toString(), Toast.LENGTH_SHORT).show();
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
        bildNumbMove = restartAT;
        updateMarker();
        updateTxt();
    }
}