package com.tobiascode.marvelclient.util;

import java.io.*;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.service.model.Backup;

public class BackupRestore {
    private final Logger logger = LoggerFactory.getLogger(BackupRestore.class);
    private final boolean keepBackup;
    private final String backupPath;

    public BackupRestore(boolean keepBackup, String backupPath) {
        this.keepBackup = keepBackup;
        this.backupPath = backupPath;
    }

    public void writeBackup(int lastOffset, List<? extends Serializable> items) {
        if (!keepBackup) {
            return;
        }

        Backup backup = new Backup();
        backup.setLastOffset(lastOffset);
        backup.setItems(items);

        try (FileOutputStream fileOutputStream = new FileOutputStream(backupPath)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(backup);
                logger.info("Writing backup file to: " + backupPath + " - offset at [" + lastOffset + "]");
            }
        } catch (Exception e) {
            logger.error("Error writing backup file", e);
        }
    }

    public Optional<Backup> readBackup() {
        Backup backup = new Backup();

        if (!new File(backupPath).exists()) {
            logger.info("No backup file found at: " + backupPath);

            return Optional.empty();
        }

        try (FileInputStream fileInputStream = new FileInputStream(backupPath)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                backup = (Backup) objectInputStream.readObject();
                logger.info("Reading backup data from: " + backupPath);
            }
        } catch (Exception e) {
            logger.error("Error reading backup file", e);

            return Optional.empty();
        }

        return Optional.ofNullable(backup);
    }
}
