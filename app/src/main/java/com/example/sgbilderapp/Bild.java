package com.example.sgbilderapp;

public class Bild {
    private String dance;
    private String comment;
    private boolean isSeparated;
    private double[] positions;
    private double[] positionsGirls;

    public Bild(String dance, String comment, boolean isSeparated) {
        this.dance = dance;
        this.comment = comment;
        this.isSeparated = isSeparated;
        if (!isSeparated) {
            positions = new double[16];
            positionsGirls = null;
        } else {
            positions = new double[16];
            positionsGirls = new double[16];
        }
    }

    public Bild(String dance, String comment, boolean isSeparated, double[] positions) {
        this.dance = dance;
        this.comment = comment;
        this.isSeparated = isSeparated;
        this.positions = positions;

        if (!isSeparated) {
            positionsGirls = null;
        } else {
            positionsGirls = new double[16];
        }
    }

    public Bild(String dance, String comment, boolean isSeparated, double[] positions, double[] positionsGirls) {
        this.dance = dance;
        this.comment = comment;
        this.isSeparated = isSeparated;
        this.positions = positions;
        this.positionsGirls = positionsGirls;
    }

    public String getDance() {
        return dance;
    }

    public void setDance(String dance) {
        this.dance = dance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSeparated() {
        return isSeparated;
    }

    public void setSeparated(boolean separated) {
        if (!isSeparated && separated){
            this.positionsGirls = new double[16];
        } else if (isSeparated && !separated) {
            this.positionsGirls = null;
        }
        isSeparated = separated;
    }

    public double getPositionX(int pos) {
        return positions[2 * (pos - 1)];
    }

    public double getPositionY(int pos) {
        return positions[(2 * (pos - 1)) + 1];
    }

    public void setPositionX(int pos, double valueX) {
        this.positions[2 * (pos - 1)] = valueX;
    }

    public void setPositionY(int pos, double valueY) {
        this.positions[(2 * (pos - 1)) + 1] = valueY;
    }

    public double getPositionGirlsX(int pos) {
        return positionsGirls[2 * (pos - 1)];
    }

    public double getPositionGirlsY(int pos) {
        return positionsGirls[(2 * (pos - 1)) + 1];
    }

    public void setPositionGirlsX(int pos, double valueX) {
        this.positionsGirls[2 * (pos - 1)] = valueX;
    }

    public void setPositionGirlsY(int pos, double valueY) {
        this.positionsGirls[(2 * (pos - 1)) + 1] = valueY;
    }

}
