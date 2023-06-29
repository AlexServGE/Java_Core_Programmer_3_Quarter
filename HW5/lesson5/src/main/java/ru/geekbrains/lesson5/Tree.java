package ru.geekbrains.lesson5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tree {

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     *
     * @param file
     * @param indent
     * @param isLast
     */
    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent); // рисуем отступ
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null) {
            return;
        } else {
            List<File> pureFiles = new ArrayList<>();
            for (File insidefile : files) {
                if (insidefile.isFile()) pureFiles.add(insidefile);
            }
            for (File insidefile : pureFiles) {
                if (!insidefile.equals(files[pureFiles.size() - 1])) {
                    System.out.println(indent + "├─" + insidefile.getName());
                } else {
                    System.out.println(indent + "└─" + insidefile.getName());
                }
            }
            int subDirTotal = 0;
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory())
                    subDirTotal++;
            }

            int subDirCounter = 0;
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    print(files[i], indent, subDirCounter == subDirTotal - 1);
                    subDirCounter++;
                }
            }
        }


    }

}
