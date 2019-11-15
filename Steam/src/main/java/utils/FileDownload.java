package utils;

import java.io.File;


public class FileDownload {

    private final String fileName = "SteamSetup";

    public boolean isFileDownloaded(String downloadPath) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            System.out.println(dirContents[i].getName());
            if (dirContents[i].getName().startsWith(fileName)) {
                dirContents[i].delete();
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;
    }
}
