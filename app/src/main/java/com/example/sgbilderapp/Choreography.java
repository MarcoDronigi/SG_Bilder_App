package com.example.sgbilderapp;

import static java.lang.System.out;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Choreography implements Serializable{

    private static final long serialVersionUID = 8736847634070552888L;

    private String name;
    private ArrayList<Bild> bilder = new ArrayList<Bild>();;
    List<String> listDances = new ArrayList<String>();
    //List<String> listComments = new ArrayList<String>();

    private int maxBild = -1;

    public Choreography(String name) {
        this.name = name;
        //Bilder von Is it a Man's World
        bilder = new ArrayList<Bild>();
        /*bilder.add(new Bild("Einmarsch", "Anfangsbild",false, bild0));
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
        bilder.add(new Bild("2.1 SF - 1. Slowfox", "Cojones Herr", false, bild26));
        bilder.add(new Bild("2.1 SF - 1. Slowfox", "Ende Federschritt\nRechter Fuß Herr", false, bild27));
        bilder.add(new Bild("2.1 SF - 1. Slowfox", "", false, bild28));
        bilder.add(new Bild("2.1 SF - 1. Slowfox", "Ende Tumble Turn\nRechter Fuß Herr", false, bild29));
        bilder.add(new Bild("2.1 SF - 1. Slowfox", "Ende Federschritt\nRechter Fuß Herr", false, bild30));
        bilder.add(new Bild("2.1 SF - 1. Slowfox", "Ende Bounced Fallaway\nRechter Fuß Herr", false, bild31));
        bilder.add(new Bild("2.1 SF - 1. Slowfox", "Ende Curved Feather\nRechter Fuß Herr", false, bild32));
        bilder.add(new Bild("2.2 TG - 2. Tango", "Rechter Fuß Herr", false, bild33));
        bilder.add(new Bild("2.2 TG - 2. Tango", "Ende Back Corté", false, bild34));
        bilder.add(new Bild("2.2 TG - 2. Tango", "Seit-Schluss vor Contra Check\nRechter Fuß Herr", false, bild35));
        bilder.add(new Bild("2.2 TG - 2. Tango", "Ende Achse\nRechter Fuß\nDurchschachteln mit 4 Schlägen je 1.5 m Seite", false, bild36));
        bilder.add(new Bild("2.3 LW - Langsamer Walzer", "Rechter Fuß Herr", false, bild37));
        bilder.add(new Bild("2.3 LW - Langsamer Walzer", "Ende Chassé\nRechter Fuß Herr", false, bild38));*/
        //bilder.add(new Bild("", "", false, bild39));
        //bilder.add(new Bild("", "", false, bild40));
        //bilder.add(new Bild("", "", false, bild41));

    }

    public void addBild(double[] bildCoord, String dance, String comment){
        assert bildCoord.length == 16;
        bilder.add(new Bild(dance, comment, false, bildCoord));
        if (maxBild == -1){
            listDances.add(dance);
        } else if (dance != bilder.get(maxBild).getDance()){
            listDances.add(dance);
        }
        maxBild++;
    }

    public void addBlankBild(){
        double[] nullBild = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        bilder.add(new Bild("Tanz", "Kommentar", false ,nullBild));
    }

    public void deleteBild(int bildIndex){
        bilder.remove(bildIndex);
    }

    public String getName(){
        return name;
    }

    public double getCoordX(int bildNumb, int pos){
        return bilder.get(bildNumb).getPositionX(pos);
    }

    public void setCoordX(int bildNumb, int pos, double xValue){
        bilder.get(bildNumb).setPositionX(pos, xValue);
    }

    public double getCoordY(int bildNumb, int pos){
        return bilder.get(bildNumb).getPositionY(pos);
    }

    public void setCoordY(int bildNumb, int pos, double yValue){
        bilder.get(bildNumb).setPositionY(pos, yValue);
    }

    public String getDance(int bildNumb) {
        return bilder.get(bildNumb).getDance();
    }

    public void setDance(int bildNumb, String dance){
        bilder.get(bildNumb).setDance(dance);
    }

    public String getComment(int bildNumb) {
        return bilder.get(bildNumb).getComment();
    }

    public void setComment(int bildNumb, String comment){
        bilder.get(bildNumb).setComment(comment);
    }

    public int getMaxBild(){
        return maxBild;
    }

    public int getDanceStart(String loopDance){
        int i = 0;
        for (Bild bild:bilder) {
            if (bild.getDance() == loopDance){
                return i;
            }
            else {
                i++;
            }
        }

        return 0;
    }

    public int getDanceEnd(String loopDance){
        int i = 0;
        boolean tmp = false;
        for (Bild bild:bilder) {
            if (bild.getDance() == loopDance){
                tmp = true;
                i++;
                continue;
            } else if(tmp == true && bild.getDance() != loopDance){
                return i - 1;
            } else {
                i++;
            }
        }

        return 0;
    }

    public List<String> getDanceArray(){
        return listDances;
    }

    public void save(Context context) throws IOException {

        try {

            String FILENAME = "test2.ser";
            String FOLDERNAME = "Choerografien";
            //String string = "hello world!";

            String folder = context.getFilesDir().getAbsolutePath() + File.separator + FOLDERNAME;
            //Toast.makeText(context, folder, Toast.LENGTH_LONG).show();
            out.println(folder);

            File subFolder = new File(folder);

            if (!subFolder.exists()) {
                subFolder.mkdirs();
            }

            File file = new File(subFolder, FILENAME);
            out.println(file.getAbsolutePath() + " MArk");

            FileOutputStream fileOutputStream = new FileOutputStream(new File(subFolder, FILENAME));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            Log.e("ERROR", e.toString());
        } catch (IOException e) {
            Log.e("ERROR", e.toString());
        }


        try {
            FileOutputStream fileOutputStream = context.openFileOutput("test.ser", Context.MODE_PRIVATE);
            out.println(fileOutputStream.toString());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Creates an object by reading it from a file
    public static Choreography readFromFile(Context context) {
        Choreography choreography = null;
        try {
            FileInputStream fileInputStream = context.openFileInput("test.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            choreography = (Choreography) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return choreography;
    }

    /*try {

        String FILENAME = "hello_file";
        String FOLDERNAME = "sub";
        byte[] bytes = new byte[1024];

        Context context = getApplicationContext();
        String folder = context.getFilesDir().getAbsolutePath() + File.separator + FOLDERNAME;

        File subFolder = new File(folder);

        FileInputStream outputStream = new FileInputStream(new File(subFolder, FILENAME));

        outputStream.read(bytes);
        outputStream.close();

        String string = new String(bytes);
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();

    } catch (FileNotFoundException e) {
        Log.e("ERROR", e.toString());
    } catch (IOException e) {
        Log.e(TAG, e.toString());
    }*/
}
