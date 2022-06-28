package com.example.sgbilderapp;

import java.util.ArrayList;

public class Choreography {

    final private String name = "Is it a Man's World";
    ArrayList<String> tänze;
    private ArrayList<Bild> bilder;

    final private int MAX_BILD = 26;

    //Punkte von Is it a Man's World?
    private double[] bild0 = {0, 0, 1.5, 0, 0, 1.5, 1.5, 3, 0, 3, 0, 4.5, 1.5, 1.5, 1.5, 4.5};
    private double[] bild1 = {-3, -1.5, 0, -1.5, -1.5, 0, 3, 1.5, 0, 1.5, 1.5, 3, 1.5, 0, 4.5, 3};
    private double[] bild2 = {-1.5, -1.5, 1.5, -1.5, 0, 0, 4.5, 1.5, 1.5, 1.5, 3, 3, 3, 0, 6, 3};
    private double[] bild3 = {-3, -1.5, 0, -1.5, -1.5, 0, 3, 1.5, 0, 1.5, 1.5, 3, 1.5, 0, 4.5, 3};
    private double[] bild4 = {-6, -1.5, -3, -4.5, -4.5, -3, -1.5, -3, -4.5, 0, -1.5, 0, -3, -1.5, 0, -1.5};
    private double[] bild5 = {-6, -3, -3, -6, -4.5, -4.5, -1.5, -4.5, -4.5, -1.5, -1.5, -1.5, -3, -3, 0, -3};
    private double[] bild6 = {0, -3, 3, -6, 1.5, -4.5, 4.5, -4.5, 1.5, -1.5, 4.5, -1.5, 3, -3, 6, -3};
    private double[] bild7 = {-1.5, 0, 1.5, -3, 0, -1.5, 3, -1.5, 0, 1.5, 3, 1.5, 1.5, 0, 4.5, 0};
    private double[] bild8 = {-4.5, 3, 0, 1.5, -3, 1.5, 3, 1.5, -1.5, 3, 1.5, 3, 0, 4.5, 4.5, 3};
    private double[] bild9 = {-2, -3, 0, -6, -2, -6, 2, -6, -1, -4.5, 1, -4.5, 0, -3, 2, -3};
    private double[] bild10 = {-2, 1.5, 0, -1.5, -2, -1.5, 2, -1.5, -1, 0, 1, 0, 0, 1.5, 2, 1.5};
    private double[] bild11 = {-6, -3, -3, -6, -6, -6, 6, 3, -4.5, -4.5, 4.5, 4.5, 3, 6, 6, 6};
    private double[] bild12 = {0, -4.5, 1.5, -6, 0, -6, 0, 4.5, 1.5, -4.5, -1.5, 4.5, -1.5, 6, 0, 6};
    private double[] bild13 = {3, -4.5, 4.5, -6, 3, -6, -3, 4.5, 4.5, -4.5, -4.5, 4.5, -4.5, 6, -3, 6};
    private double[] bild14 = {2.25, -3.75, 3, -4.5, 1.5, -4.5, -2.25, 3.75, 3, -3, -3, 3, -3, 4.5, -1.5, 4.5};
    private double[] bild15 = {1.5, 0, 1.5, -1.5, 0, -1.5, -1.5, 0, 1.5, 1.5, -1.5, -1.5, -1.5, 1.5, 0, 1.5};
    private double[] bild16 = {-1.5, -4.5, -1.5, -6, -3, -6, -4.5, -4.5, -1.5, -3, -4.5, -6, -4.5, -3, -3, -3};
    private double[] bild17 = {0, -3, 0, -4.5, -1.5, -4.5, -3, -3, 0, -1.5, -3, -4.5, -3, -1.5, -1.5, -1.5};
    private double[] bild18 = {3, -1.5, 4.5, -3, 3, -4.5, 0, -4.5, 1.5, 0, 1.5, -6, -1.5, -3, 0, -1.5};
    private double[] bild19 = {4.5, -1.5, 6, -3, 4.5, -4.5, 1.5, -4.5, 3, 0, 3, -6, 0, -3, 1.5, -1.5};
    private double[] bild20 = {1.5, 3, 3, 1.5, 3, 0, -1.5, 0, 0, 3, 1.5, 1.5, -1.5, 1.5, 0, 1.5};
    private double[] bild21 = {0.5, 6, 4.5, 2, 6.5, 0, -6.5, 1, -0.5, 7, 2.5, 4, -4.5, 3, -2.5, 5};
    private double[] bild22 = {-1.5, 4, 2.5, 0, 4.5, -2, -4.5, -1, -2.5, 5, 0.5, 2, -2.5, 1, -0.5, 3};
    private double[] bild23 = {-3.5, 2, 0.5, -2, 2.5, -4, -2.5, -3, -4.5, 3, -1.5, 0, -0.5, -1, -2.5, 1};
    private double[] bild24 = {-5.5, 0, -1.5, -4, 0.5, -6, -0.5, -5, -6.5, 1, -3.5, -2, -2.5, -3, -4.5, -1};
    private double[] bild25 = {-1, 3, 0.5, -1.5, 2, -3, 0.5, -3, -2.5, 3, -1, 0, 0.5, 0, -1, 1.5};
    private double[] bild26 = {0, 6, 1.5, 3, 3, 1.5, 1.5, 1.5, -1.5, 6, 0, 3, 1.5, 4.5, 0, 4.5};
    //double[] bild27 = {};
    //double[] bild28 = {};
    //double[] bild29 = {};
    //double[] bild30 = {};
    //double[] bild31 = {};

    public Choreography() {
        //Bilder von Is it a Man's World
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
        bilder.add(new Bild("1.3 TG - 1. Tango", "Ende Fallaway\nRechter Fuß Herr", false, bild20));
        bilder.add(new Bild("1.3 TG - 1. Tango", "Diagonalen Ende Fünferschritt\nRechter Fuß Herr", false, bild21));
        bilder.add(new Bild("1.3 TG - 1. Tango", "Ende Fallaway", false, bild22));
        bilder.add(new Bild("1.3 TG - 1. Tango", "Ende Back Corté", false, bild23));
        bilder.add(new Bild("1.3 TG - 1. Tango", "Große Diagonale Ende sykopierte Linksdrehung\nRechter Fuß Herr", false, bild24));
        bilder.add(new Bild("1.3 TG - 1. Tango", "Ende synkopiertes Fallaway", false, bild25));
        bilder.add(new Bild("2.1 SF - 1. Slowfox", "Cijones Herr", false, bild26));
        //bilder.add(new Bild("", "", false, bild27));
        //bilder.add(new Bild("", "", false, bild28));
        //bilder.add(new Bild("", "", false, bild29));
        //bilder.add(new Bild("", "", false, bild30));
        //bilder.add(new Bild("", "", false, bild31));

        String tmp = "";
        for (Bild bild:bilder) {
            if (bild.getDance() != tmp){
                tänze = new ArrayList<String>();
                tänze.add(bild.getDance());
                tmp = bild.getDance();
            }
        }

        System.out.println(tänze);
    }

    public double getCoordX(int bildNumb, int pos){
        return bilder.get(bildNumb).getPositionX(pos);
    }

    public double getCoordY(int bildNumb, int pos){
        return bilder.get(bildNumb).getPositionY(pos);
    }

    public String getDance(int bildNumb) {
        return bilder.get(bildNumb).getDance();
    }

    public String getComment(int bildNumb) {
        return bilder.get(bildNumb).getComment();
    }

    public int getMAX_BILD(){
        return MAX_BILD;
    }
}
