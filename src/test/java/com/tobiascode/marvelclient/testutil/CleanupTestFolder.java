package com.tobiascode.marvelclient.testutil;

import java.io.File;

public class CleanupTestFolder {
    public static void CleanFiles(String folderName){
        File testFolder = new File("./" + folderName + "/");

        for(File file: testFolder.listFiles()){
            if(!file.getName().equals("keepfolder.txt")){
                file.delete();
            }
        }
    }
}
