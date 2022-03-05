package task3_unZip_unSerializing;

import task2_serializing.GameProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        //путь в папку, куда будут разархивированы файлы сохранений
        String dirZipOut = "C:\\Users\\Sakhmina\\Games\\savegames";
        //путь к ранее заархивированных файлам сохранений
        String zipPath = "C:\\Users\\Sakhmina\\Games\\savegames\\zip_output.zip";

        //разархивация папки zipPath в папку dirZipOut
        openZip(zipPath, dirZipOut);

        //путь к файлу сохранения(состояние объекта GameProcess)
        String filePath = "C:\\Users\\Sakhmina\\Games\\savegames\\packed_save1.txt";
        //Вывод в консоль состояния объекта(десериализация файла сохранения по пути filePath)
        System.out.println(openProgress(filePath).toString());
    }

    private static void openZip(String zipPath, String dirZipOut) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла из архива zipPath
                // распаковка архива в папку dirZipOut
                FileOutputStream fout = new FileOutputStream(dirZipOut);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static GameProgress openProgress(String filePath) {
        GameProgress gameProgress = null;// откроем входной поток для чтения файла
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return (gameProgress);
    }
}


