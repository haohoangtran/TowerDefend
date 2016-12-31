package controller.enemies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Created by Khuong Duy on 12/31/2016.
 */
public class SpawnEnemy {
    public int file =0;
    public static final SpawnEnemy instance = new SpawnEnemy();
    public final File folder;
    public Vector<File> allFile = new Vector<>();

    public SpawnEnemy() {
        folder = new File("res/Level/");
        listFilesForFolder(folder);
        System.out.println(allFile.size());
    }

    public List<String> getListString(File file) {
        System.out.println(file.getName());
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                allFile.add(fileEntry);
            }
        }
    }




}
