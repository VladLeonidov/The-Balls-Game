package com.leus.business.service.score;

import com.leus.util.RecordsUtil;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class RecordTable {
    private static RecordTable instance;

    private List<Record> records;

    private RecordTable() {
        try {
            records = RecordsUtil.loadRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RecordTable getInstance() {
        if (instance == null) {
            instance = new RecordTable();
        }

        return instance;
    }

    public boolean setRecord(Record currentRecord) {
        if (isNewRecord(currentRecord.getScore())) {
            records.add(currentRecord);
            removeOldRecords();
            return true;
        }

        return false;
    }

    public void saveRecordsToFile() {
        try {
            RecordsUtil.saveRecords(records);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintRecords(Graphics g, int positionX, int positionY, int interval) {
        Collections.sort(records);
        for (Record record : records) {
            record.paint(g, positionX, positionY += interval);
        }
    }

    public void paintRecords(Graphics g, int positionX, int positionY, int interval, Comparator<Record> comp) {
        Collections.sort(records, comp);
        for (Record record : records) {
            record.paint(g, positionX, positionY += interval);
        }
    }

    public boolean isNewRecord(long currentScore) {
        for (Record writtenRecord : records) {
            if (writtenRecord.getScore() < currentScore) {
                return true;
            }
        }

        return false;
    }

    private void removeOldRecords() {
        if (records.size() > 10) {
            Collections.sort(records);
            records = records.subList(0, 10);
        }
    }
}
