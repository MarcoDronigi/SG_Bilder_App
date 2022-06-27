package com.example.sgbilderapp;

import java.util.ArrayList;

public class Choreography {

    final private String name = "Is it a Man's World";
    ArrayList<String> tänze;
    private ArrayList<Bild> bilder;

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

    public Choreography() {
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

        String tmp = "";
        for (Bild bild:bilder) {
            if (bild.getDance() != tmp){
                tänze.add(bild.getDance());
                tmp = bild.getDance();
            }
        }

        System.out.println(tänze);
    }



}
