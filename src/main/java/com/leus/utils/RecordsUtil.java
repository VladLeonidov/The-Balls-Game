package com.leus.utils;

import java.io.*;
import java.util.Arrays;

public class RecordsUtil {
    private static final String[] RECORDS = new String[10];
    private static final long[] RECORDS_IN_NUMBERS = new long[RECORDS.length];
    private static final String[] NAMES_OF_RECORDS = new String[RECORDS.length];
    private static final InputStream RECORDS_FILE = ResourceLoader.loadFile("Records.rl");

    static {
        loadRecords();
        parseRecords();
    }

    public static String[] getRecords() {
        return Arrays.copyOf(RECORDS, RECORDS.length);
    }

    /*public static void saveRecords() throws IOException {
        MakeRecords();
        try(BufferedWriter out = new BufferedWriter(new FileWriter(RECORDS_FILE))) {
            for (String currentLineScore : RECORDS) {
                out.write(currentLineScore);
                out.newLine();
            }
            out.flush();
        }
    }*/

    public static boolean setRecord(String nameRecord, long bestScore) {
        int indexRecord = isNewRecord(bestScore);
        if (indexRecord != -1) {
            NAMES_OF_RECORDS[indexRecord] = nameRecord;
            RECORDS_IN_NUMBERS[indexRecord] = bestScore;
            return true;
        }

        return false;
    }

    public static int isNewRecord(long bestScore) {
        for (int i = 0; i < RECORDS.length; i++) {
            if (bestScore > RECORDS_IN_NUMBERS[i]) {
                return i;
            }
        }

        return -1;
    }

    private static void loadRecords() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(RECORDS_FILE))) {
            int i = 0;
            while (in.ready()) {
                RECORDS[i++] = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseRecords() {
        for (int i = 0; i < RECORDS.length; i++) {
            RECORDS_IN_NUMBERS[i] = Long.valueOf(RECORDS[i].split("=")[1].trim());
            NAMES_OF_RECORDS[i] = RECORDS[i].split("=")[0].trim();
        }
    }

    private static void MakeRecords() {
        for (int i = 0; i < RECORDS.length; i++) {
            RECORDS[i] = NAMES_OF_RECORDS[i] + " = " + RECORDS_IN_NUMBERS[i];
        }
    }
}
