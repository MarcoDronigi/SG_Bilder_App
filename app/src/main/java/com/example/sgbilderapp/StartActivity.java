package com.example.sgbilderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Punkte von Is it a Man's World?
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
        double[] bild20 = {1.5, 3, 3, 1.5, 3, 0, -1.5, 0, 0, 3, 1.5, 1.5, -1.5, 1.5, 0, 1.5};
        double[] bild21 = {0.5, 6, 4.5, 2, 6.5, 0, -6.5, 1, -0.5, 7, 2.5, 4, -4.5, 3, -2.5, 5};
        double[] bild22 = {-1.5, 4, 2.5, 0, 4.5, -2, -4.5, -1, -2.5, 5, 0.5, 2, -2.5, 1, -0.5, 3};
        double[] bild23 = {-3.5, 2, 0.5, -2, 2.5, -4, -2.5, -3, -4.5, 3, -1.5, 0, -0.5, -1, -2.5, 1};
        double[] bild24 = {-5.5, 0, -1.5, -4, 0.5, -6, -0.5, -5, -6.5, 1, -3.5, -2, -2.5, -3, -4.5, -1};
        double[] bild25 = {-1, 3, 0.5, -1.5, 2, -3, 0.5, -3, -2.5, 3, -1, 0, 0.5, 0, -1, 1.5};
        double[] bild26 = {0, 6, 1.5, 3, 3, 1.5, 1.5, 1.5, -1.5, 6, 0, 3, 1.5, 4.5, 0, 4.5};
        double[] bild27 = {-1.5, 3, 0, 1.5, 1.5, -1.5, 0, -1.5, -3, 3, -3, 0, 1.5, 1.5, -1.5, 0};
        double[] bild28 = {4.5, 3, -6, 1.5, -4.5, -1.5, -6, -1.5, 3, 3, 3, 0, -4.5, 1.5, 4.5, 0};
        double[] bild29 = {4.5, 6, -4.5, -4.5, -3, -3, -6, -6, 3, 6, 3, 4.5, -1.5, -1.5, 4.5, 4.5};
        double[] bild30 = {-3, 6, 3, -4.5, 4.5, -3, 1.5, -6, -6, 6, -6, 3, 6, -1.5, -3, 3};
        double[] bild31 = {3, 0, -3, 1, -1.5, 2.5, -4.5, -0.5, 0, 0, 0, -3, 0, 4, 3, -3};
        double[] bild32 = {4.5, 3, 0, 1.5, 0, 4.5, -1.5, 3, 1.5, 3, 1.5, 0, 3, 4.5, 3, 1.5};
        double[] bild33 = {4.5, 4.5, 0, 3, 0, 6, -1.5, 4.5, 1.5, 4.5, 1.5, 1.5, 3, 6, 3, 3};
        double[] bild34 = {3, 0, -1.5, -1.5, -1.5, 0, -3, 0, 0, 0, 0, -3, 1.5, 0, 1.5, -1.5};
        double[] bild35 = {4.5, -1.5, -1.5, -4.5, -3, -3, -4.5, -1.5, 0, -3, 0, -6, 3, -3, 1.5, -4.5};
        double[] bild36 = {-3, 6, -4.5, 3, -4.5, 4.5, -4.5, 6, -3, 3, -4.5, 1.5, -3, 4.5, -3, 1.5};
        double[] bild37 = {0, 6, -1.5, 3, -1.5, 4.5, -1.5, 6, 0, 3, -1.5, 1.5, 0, 4.5, 0, 1.5};
        double[] bild38 = {0, 3, -1.5, 1.5, 0, 1.5, -1.5, 3, 1.5, 0, -1.5, 0, 1.5, 1.5, 0, 0};
        //private double[] bild39 = {};
        //private double[] bild40 = {};
        //private double[] bild41 = {};

        Choreography isItAMansWorld = new Choreography("Is it a Man's World?");

        isItAMansWorld.addBild(bild0, "Einmarsch", "Anfangsbild");
        isItAMansWorld.addBild(bild1, "Einmarsch", "Standing Spin");
        isItAMansWorld.addBild(bild2, "Einmarsch", "Ende Drehung\nLinker Fuß Herr");
        isItAMansWorld.addBild(bild3, "1.1 WW - 1. Wiener Walzer", "Gong\nCojones Herr");
        isItAMansWorld.addBild(bild4, "1.1 WW - 1. Wiener Walzer", "Ende RD\nLinker Fuß Herr");
        isItAMansWorld.addBild(bild5, "1.1 WW - 1. Wiener Walzer", "Achse\nLinker Fuß Herr");
        isItAMansWorld.addBild(bild6, "1.1 WW - 1. Wiener Walzer", "Schlag 3 RD\nLinker Fuß Herr");
        isItAMansWorld.addBild(bild7, "1.1 WW - 1. Wiener Walzer", "");
        isItAMansWorld.addBild(bild8, "1.1 WW - 1. Wiener Walzer", "Change of Direction\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild9, "1.2 Qs - 1. Quickstep", "Fleckerl");
        isItAMansWorld.addBild(bild10, "1.2 Qs - 1. Quickstep", "Außenseitlicher Wechsel\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild11, "1.2 Qs - 1. Quickstep", "2. Step Hop");
        isItAMansWorld.addBild(bild12, "1.2 Qs - 1. Quickstep", "Slow nach 4. Step Hop\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild13, "1.2 Qs - 1. Quickstep", "");
        isItAMansWorld.addBild(bild14, "1.2 Qs - 1. Quickstep", "Dreieck\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild15, "1.2 Qs - 1. Quickstep", "Kick Dame");
        isItAMansWorld.addBild(bild16, "1.3 TG - 1. Tango", "");
        isItAMansWorld.addBild(bild17, "1.3 TG - 1. Tango", "Verschieben mit Schlägen 1 bis 6");
        isItAMansWorld.addBild(bild18, "1.3 TG - 1. Tango", "Ende Fallaway\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild19, "1.3 TG - 1. Tango", "Linker Fuß Herr außenseitlich");
        isItAMansWorld.addBild(bild20, "1.3 TG - 1. Tango", "Ende Fallaway\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild21, "1.3 TG - 1. Tango", "Diagonalen Ende Fünferschritt\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild22, "1.3 TG - 1. Tango", "Ende Fallaway");
        isItAMansWorld.addBild(bild23, "1.3 TG - 1. Tango", "Ende Back Corté");
        isItAMansWorld.addBild(bild24, "1.3 TG - 1. Tango", "Große Diagonale Ende sykopierte Linksdrehung\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild25, "1.3 TG - 1. Tango", "Ende synkopiertes Fallaway");
        isItAMansWorld.addBild(bild26, "2.1 SF - 1. Slowfox", "Cojones Herr");
        isItAMansWorld.addBild(bild27, "2.1 SF - 1. Slowfox", "Ende Federschritt\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild28, "2.1 SF - 1. Slowfox", "");
        isItAMansWorld.addBild(bild29, "2.1 SF - 1. Slowfox", "Ende Tumble Turn\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild30, "2.1 SF - 1. Slowfox", "Ende Federschritt\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild31, "2.1 SF - 1. Slowfox", "Ende Bounced Fallaway\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild32, "2.1 SF - 1. Slowfox", "Ende Curved Feather\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild33, "2.2 TG - 2. Tango", "Rechter Fuß Herr");
        isItAMansWorld.addBild(bild34, "2.2 TG - 2. Tango", "Ende Back Corté");
        isItAMansWorld.addBild(bild35, "2.2 TG - 2. Tango", "Seit-Schluss vor Contra Check\nRechter Fuß Herr");
        isItAMansWorld.addBild(bild36, "2.2 TG - 2. Tango", "Ende Achse\nRechter Fuß\nDurchschachteln mit 4 Schlägen je 1.5 m Seite");
        isItAMansWorld.addBild(bild37, "2.3 LW - Langsamer Walzer", "Rechter Fuß Herr");
        isItAMansWorld.addBild(bild38, "2.3 LW - Langsamer Walzer", "Ende Chassé\nRechter Fuß Herr");
        //isItAMansWorld.addBild(bild, "", "");s

        try {
            isItAMansWorld.save(getBaseContext());
        } catch (IOException e) {
            e.printStackTrace();
        }



        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}