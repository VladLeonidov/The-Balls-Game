package com.leus.utils;

import com.leus.game.service.scores.Record;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class-util is designed for load/save records file to/from game directory in users.
 * @see GameDirUtil
 * @see com.leus.game.service.scores.RecordTable
 */
public final class RecordsUtil {
    private static File recordsFile = new File(GameDirUtil.getGameDirPath(), "/Records.rl");

    private RecordsUtil() {
    }

    /**
     * This method saves a records list to file in user.
     * @param recordsList List with records
     * @throws IOException If has problems with a saving records file.
     * @see com.leus.game.service.scores.RecordTable
     */
    public static void saveRecords(List<Record> recordsList) throws IOException {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(recordsFile))) {
            for (Record currentRecord : recordsList) {
                out.write(currentRecord.toString());
                out.newLine();
            }

            out.flush();
        }
    }

    /**
     * This method loads records from the file in user to the records list,
     * if a game directory in the user is missing, the method creates it,
     * if the records file in user is missing, the method creates it
     * and copies a file from resources to the records file in user.
     * @return List of records
     * @throws IOException If has problems with reading a records file.
     */
    public static List<Record> loadRecords() throws IOException {
        GameDirUtil.createGameDir();
        if (createGameRecordsFile()) {
            copyRecordsFileToUserFromResources();
            return loadRecordsHelper();
        } else {
            return loadRecordsHelper();
        }
    }

    private static List<Record> loadRecordsHelper() throws IOException {
        List<Record> result = new ArrayList<>(10);
        try(BufferedReader in = new BufferedReader(new FileReader(recordsFile))) {
            while (in.ready()) {
                String record = in.readLine();
                if (!record.equals("")) {
                    result.add(parseRecord(record));
                }
            }

            if (result.size() < 10) {
                copyRecordsFileToUserFromResources();
                return loadRecordsHelper();
            }
        }

        return result;
    }

    private static void copyRecordsFileToUserFromResources() throws IOException {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(ResourceLoader.loadFile(PathsToResources.RECORDS_FILE.getPath())));
            BufferedWriter out = new BufferedWriter(new FileWriter(recordsFile))) {
            while (in.ready()) {
                out.write(in.readLine());
                out.newLine();
            }
            out.flush();
        }
    }

    private static Record parseRecord(String record) {
        String[] tmp = record.split("=");
        return new Record(tmp[0].trim(), Long.valueOf(tmp[1].trim()));
    }

    private static boolean createGameRecordsFile() throws IOException {
        if (!isRecordsFileInUser()) {
            return recordsFile.createNewFile();
        }

        return false;
    }

    private static boolean isRecordsFileInUser() {
        return recordsFile.exists();
    }
}
