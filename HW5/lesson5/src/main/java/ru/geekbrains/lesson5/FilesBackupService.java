package ru.geekbrains.lesson5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FilesBackupService {

    public static void backupFilesInDir(String dirName) throws IOException {
        Path backupFolder = Files.createDirectory(Paths.get("./backup"));
        File folderUnderBackup = new File(dirName);
        if (!folderUnderBackup.isDirectory()) {
            System.out.println("Указанное имя является именем файла.");
        } else {
            for (File file : folderUnderBackup.listFiles()) {
                if (file.isFile()) {
                    Path fileToBackupAbsPath = Paths.get(file.getAbsolutePath());
                    Files.copy(fileToBackupAbsPath,Paths.get(backupFolder.toString()+"/"+file.getName()),REPLACE_EXISTING);
                }
            }
        }
    }
}
