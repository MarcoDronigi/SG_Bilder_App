package com.example.sgbilderapp;

import static java.lang.System.out;


import android.util.Log;

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

    public Choreography(String name) {
        this.name = name;
    }

    public void addBild(int index, double[] bildCoord, String dance, String comment){
        assert bildCoord.length == 16;
        bilder.add(index + 1, new Bild(dance, comment, false, bildCoord));
    }

    public void addBlankBild(int index){
        double[] nullBild = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        addBild(index, nullBild, "Tanz", "Kommentar");
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
        return bilder.size() - 1;
    }

    public int getDanceStart(String loopDance){
        int i = 0;
        for (Bild bild:bilder) {
            if (bild.getDance().equals(loopDance)){
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
            if (bild.getDance().equals(loopDance)){
                tmp = true;
                i++;
                continue;
            } else if(tmp == true && !bild.getDance().equals(loopDance)){
                return i - 1;
            } else {
                i++;
            }
        }

        return 0;
    }

    public List<String> getDanceArray(){
        List<String> listDances = new ArrayList<String>();

        listDances.clear();
        for (int j = 0; j <= (bilder.size() - 1); j++){
            String tmp = bilder.get(j).getDance();
            if (j == 0){
                out.println("Fall j == 0");
                listDances.add(tmp);
            } else if (!tmp.equals(bilder.get(j - 1).getDance())){
                out.println(tmp + " != " + bilder.get(j - 1).getDance());
                listDances.add(tmp);
            }
        }
        return listDances;
    }

    public void save(String pathChoreo) throws IOException {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(pathChoreo));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            Log.e("ERROR", e.toString());
        } catch (IOException e) {
            Log.e("ERROR", e.toString());
        }
    }

    // Creates an object by reading it from a file
    public static Choreography readFromFile(String pathChoreo) {
        Choreography choreography = null;
        try {

            FileInputStream fileInputStream = new FileInputStream(new File(pathChoreo));
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
}
