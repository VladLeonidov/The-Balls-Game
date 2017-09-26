package com.leus.model.service.scores;

import java.awt.*;

public class Record implements Comparable<Record> {
    private String nameRecord;
    private long score;

    public Record(String nameRecord, long score) {
        this.nameRecord = nameRecord;
        this.score = score;
    }

    public String getNameRecord() {
        return nameRecord;
    }

    public long getScore() {
        return score;
    }

    public void paint(Graphics g, int positionX, int positionY) {
        g.drawString(this.toString(), positionX, positionY);
    }

    @Override
    public String toString() {
        return nameRecord + " = " + score;
    }

    @Override
    public int compareTo(Record that) {
        return Long.compare(that.score, this.score);
    }
}
