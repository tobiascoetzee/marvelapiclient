package com.tobiascode.marvelclient.util;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.NotImplementedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tobiascode.marvelclient.service.model.Backup;

public class BackupRestoreTest {
    private final String backupFilePath = "./testwrite.bck";

    @Before
    public void cleanUpBefore() {
        File backupFile = new File(backupFilePath);

        if (backupFile.exists()) {
            backupFile.delete();
        }
    }

    @After
    public void cleanUpAfter() {
        File backupFile = new File(backupFilePath);

        if (backupFile.exists()) {
            backupFile.delete();
        }
    }

    @Test
    public void writeBackup_should_write_a_backup_file() {
        List<String> backupItems = asList("one", "two", "three", "four", "five");
        File backupFile = new File(backupFilePath);

        BackupRestore backupRestore = new BackupRestore(true, backupFilePath);
        backupRestore.writeBackup(4, backupItems);

        assertTrue(backupFile.exists());
    }

    @Test
    public void writeBackup_should_fail_with_no_exception() {
        List<NotSerializableClass> backupItems = asList(new NotSerializableClass("one"), new NotSerializableClass("two"));
        File backupFile = new File(backupFilePath);

        BackupRestore backupRestore = new BackupRestore(true, backupFilePath);
        backupRestore.writeBackup(4, backupItems);

        assertTrue(backupFile.exists());
    }

    @Test
    public void readBackup_should_return_backup_data() {
        List<String> backupItems = asList("one", "two", "three", "four", "five");

        BackupRestore backupRestore = new BackupRestore(true, backupFilePath);
        backupRestore.writeBackup(4, backupItems);
        Optional<Backup> backup = backupRestore.readBackup();

        assertTrue(backup.isPresent());
    }

    @Test
    public void readBackup_should_return_not_present_when_no_backupfile_exists() {
        BackupRestore backupRestore = new BackupRestore(true, backupFilePath);
        Optional<Backup> backup = backupRestore.readBackup();

        assertFalse(backup.isPresent());
    }

    @Test
    public void readBackup_should_not_throw_exception_on_error() {
        BackupRestore backupRestore = new BackupRestore(true, getClass().getClassLoader().getResource("backuprestoredata/brokenfile.bck").getPath());
        Optional<Backup> backup = backupRestore.readBackup();

        assertFalse(backup.isPresent());
    }

    @Test
    public void readBackup_should_return_not_present_with_empty_file() {
        BackupRestore backupRestore = new BackupRestore(true, getClass().getClassLoader().getResource("backuprestoredata/emptyfile.bck").getPath());
        Optional<Backup> backup = backupRestore.readBackup();

        assertFalse(backup.isPresent());
    }

    @Test
    public void readBackup_should_return_the_sames_backup_data_that_was_written() {
        List<String> backupItems = asList("one", "two", "three", "four", "five");
        int lastOffset = 4;

        BackupRestore backupRestore = new BackupRestore(true, backupFilePath);
        backupRestore.writeBackup(lastOffset, backupItems);
        Optional<Backup> backup = backupRestore.readBackup();

        Backup result = backup.get();

        assertEquals(lastOffset, result.getLastOffset());
        assertEquals(backupItems, result.getItems());
    }

    private class NotSerializableClass implements Serializable{
        private String someValue;

        public NotSerializableClass(String someValue) {
            this.someValue = someValue;
        }

        public String getSomeValue() {
            throw new NotImplementedException();
        }

        public void setSomeValue(String someValue) {
            this.someValue = someValue;
        }
    }
}