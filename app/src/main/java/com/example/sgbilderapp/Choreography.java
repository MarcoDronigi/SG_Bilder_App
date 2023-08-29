package com.example.sgbilderapp;


import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
                //out.println("Fall j == 0");
                listDances.add(tmp);
            } else if (!tmp.equals(bilder.get(j - 1).getDance())){
                //out.println(tmp + " != " + bilder.get(j - 1).getDance());
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

    private String coordToString(Bild bild){
        String output = "(" + bild.getPositionX(1) + "," + bild.getPositionY(1) + ")";
        for (int i = 2; i <= 8; i++) {
            output = output + ", (" + bild.getPositionX(i) + "," + bild.getPositionY(i) + ")";
        }

        return output;
    }

    public void choreoToLatex(String pathChoreo){

        try {
            File myObj = new File(pathChoreo + "Latex.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(pathChoreo + "Latex.txt");
            myWriter.write("\\documentclass[a4paper]{article}\n" +
                    "\\usepackage[a4paper, left=2cm, right=1cm, top=2cm, bottom=2cm]{geometry}\n" +
                    "\\usepackage[dvipsnames]{xcolor}\n" +
                    "\\usepackage{tikz}\n" +
                    "\\usepackage{fancyhdr}\n" +
                    "\\usepackage{fontspec}\n" +
                    "\\setsansfont{Calibri}\n" +
                    "\\renewcommand{\\familydefault}{\\sfdefault}\n" +
                    "\n" +
                    "\\pagestyle{fancy}\n" +
                    "\\fancyhf{}\n" +
                    "\\renewcommand{\\headrulewidth}{0pt}\n" +
                    "\\setlength{\\headheight}{18pt}\n" +
                    "\\fancyhead[C]{\\Large " + name + "}\n" +
                    "\\fancyfoot[R]{\\Large\\thepage.}\n" +
                    "\n" +
                    "\\usetikzlibrary{shapes.geometric, shapes.symbols, shapes.multipart, arrows.meta, positioning, calc}\n" +
                    "\\tikzstyle{paar-shape} = [regular polygon, regular polygon sides=4, rotate=45, fill=red, minimum size=0.6cm]\n" +
                    "\n" +
                    "\\tikzset{flaeche/.pic={\n" +
                    "\t\\draw[step=1.5cm, shift={(-7.5,-7.5)}, color=SpringGreen] (-0.5,-0.5) grid (15.7,15.7);\n" +
                    "\t\\draw[thick, step=1cm, shift={(-8,-8)}, color=gray] (-0.2,-0.2) grid (16,16);\n" +
                    "\t\\draw[ultra thick] (-8.2,0) -- (8.2,0);\n" +
                    "\t\\draw[ultra thick] (0,-8.2) -- (0,8.2);\n" +
                    "\t\n" +
                    "\t\\foreach \\x in {-8,-6,-4,-2,2,4,6,8} { \\node [anchor=north] at (\\x,-8.2) {\\small\\x}; }\n" +
                    "\t\\foreach \\y in {-8,-6,...,8} { \\node [anchor=east] at (-8.2,\\y) {\\small\\y}; }\n" +
                    "\t\\foreach \\x in {-7.5,-6,...,7.5} { \\node [anchor=south] at (\\x,8.1) {\\small\\x}; }\n" +
                    "\t\\foreach \\y in {-7.5,-6,...,7.5} { \\node [anchor=west] at (8.1,\\y) {\\small\\y}; }\n" +
                    "\t\n" +
                    "\t\\node [anchor=north] at (0,-8.2) {\\textbf{Vorne}};\n" +
                    "\t\n" +
                    "\t\\foreach \\paar [count=\\num] in \\pos\n" +
                    "\t{\n" +
                    "\t\t\\node [at=\\paar, paar-shape, draw] {};\n" +
                    "\t\t\\node [at=\\paar, text=white] {\\small\\textbf{\\num}};\n" +
                    "\t}\n" +
                    "\t\n" +
                    "\t\\node[text width=4.25cm, anchor=north west] at (9.5,8.5) \n" +
                    "\t\t{\\Large\\textbf{\\tanz}\\skip\\definitionen};\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\\def\\prevpos{\\pos}\n" +
                    "}}\n" +
                    "\n" +
                    "\\begin{document}\n" +
                    "\\def\\skip{\\\\\\vspace{\\baselineskip}}\n" +
                    "\n");
            for (Bild bild:bilder) {
                myWriter.write("\n" +
                        "\n" +
                        "\\def\\tanz{" + bild.getDance() + "}\n" +
                        "\\def\\pos{" + coordToString(bild) + "}\n" +
                        "\\def\\definitionen{" + bild.getComment() + "}\n" +
                        "\\tikz\\pic[scale=0.7]{flaeche};\n" +
                        "\\vspace{1cm}");
            }
            myWriter.write("\n" +
                    "\n" +
                    "\\end{document}");


            Bild prevBild = null;
            double d1 = 0;
            double d2 = 0;
            double d3 = 0;
            double d4 = 0;
            double d5 = 0;
            double d6 = 0;
            double d7 = 0;
            double d8 = 0;

            for (Bild bild:bilder) {
                if(prevBild != null){
                    d1 += Math.sqrt(Math.pow(Math.abs(prevBild.getPositionX(1) - bild.getPositionX(1)), 2) + Math.pow(Math.abs(prevBild.getPositionX(1) - bild.getPositionX(1)), 2));
                    d2 += Math.sqrt(Math.pow(Math.abs(prevBild.getPositionX(2) - bild.getPositionX(2)), 2) + Math.pow(Math.abs(prevBild.getPositionX(2) - bild.getPositionX(2)), 2));
                    d3 += Math.sqrt(Math.pow(Math.abs(prevBild.getPositionX(3) - bild.getPositionX(3)), 2) + Math.pow(Math.abs(prevBild.getPositionX(3) - bild.getPositionX(3)), 2));
                    d4 += Math.sqrt(Math.pow(Math.abs(prevBild.getPositionX(4) - bild.getPositionX(4)), 2) + Math.pow(Math.abs(prevBild.getPositionX(4) - bild.getPositionX(4)), 2));
                    d5 += Math.sqrt(Math.pow(Math.abs(prevBild.getPositionX(5) - bild.getPositionX(5)), 2) + Math.pow(Math.abs(prevBild.getPositionX(5) - bild.getPositionX(5)), 2));
                    d6 += Math.sqrt(Math.pow(Math.abs(prevBild.getPositionX(6) - bild.getPositionX(6)), 2) + Math.pow(Math.abs(prevBild.getPositionX(6) - bild.getPositionX(6)), 2));
                    d7 += Math.sqrt(Math.pow(Math.abs(prevBild.getPositionX(7) - bild.getPositionX(7)), 2) + Math.pow(Math.abs(prevBild.getPositionX(7) - bild.getPositionX(7)), 2));
                    d8 += Math.sqrt(Math.pow(Math.abs(prevBild.getPositionX(8) - bild.getPositionX(8)), 2) + Math.pow(Math.abs(prevBild.getPositionX(8) - bild.getPositionX(8)), 2));
                }
                prevBild = bild;
            }
            myWriter.write("d1 = " + d1 + "\nd2" + d2 + "\nd3" + d3 + "\nd4" + d4 + "\nd5" + d5 + "\nd6" + d6 + "\nd7" + d7 + "\nd8" + d8);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



    }
}
