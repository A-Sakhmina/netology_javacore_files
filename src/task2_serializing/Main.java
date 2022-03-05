package task2_serializing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        //три экземпляра класса GameProgress, которые в дальнейшем будут сериализированы
        //т.е.  будут сохранены состояния объектов в последовательность байт.
        GameProgress gamer1 = new GameProgress(11, 2, 43, 23.8);
        GameProgress gamer2 = new GameProgress(22, 5, 43, 23.5);
        GameProgress gamer3 = new GameProgress(33, 10, 43, 20.5);

        //задаёс название и путь для файлов сохранений
        String filePath1 = "C:\\Users\\Sakhmina\\Games\\savegames\\save1.dat";
        String filePath2 = "C:\\Users\\Sakhmina\\Games\\savegames\\save2.dat";
        String filePath3 = "C:\\Users\\Sakhmina\\Games\\savegames\\save3.dat";

        //создание списка с полным путём файлов сохранений, которые в дальнейшем будут созданы
        List<String> filesPath = new ArrayList<>();
        filesPath.add(filePath1);
        filesPath.add(filePath2);
        filesPath.add(filePath3);


        //создание файлов сохранений в папке savegames
        saveGame(filePath1, gamer1);
        saveGame(filePath2, gamer2);
        saveGame(filePath3, gamer3);

        //архивирование файлов сохранений в папку zip_output.zip
        String filesDirPath = "C:\\Users\\Sakhmina\\Games\\savegames\\zip_output.zip";
        zipFiles(filesDirPath, filesPath);

        //удаление файлов сохранений, лежаший вне архива
        deleteSerializedFiles(filesPath);

    }

    public static void saveGame(String filePath, GameProgress gameProgress) {
        // откроем выходной поток для записи в файл
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {//преобразования объектов в поток байтов
            // запишем экземпляр класса в файл
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String filesDirPath, List<String> filesPath) {
        for (String filePath : filesPath) {

            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(filesDirPath));//в конструктор передается поток вывода
                 FileInputStream fis = new FileInputStream(filePath)) { //получает ссылку на поток ввода

                ZipEntry entry = new ZipEntry("packed_files.txt");  //в конструктор передается имя архивируемого файла
                zout.putNextEntry(entry);   //добавить каждый объект ZipEntry в архив
                // считываем содержимое файла в массив byte
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                // добавляем содержимое к архиву
                zout.write(buffer);
                // закрываем текущую запись для новой записи
                zout.closeEntry();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void deleteSerializedFiles(List<String> filesPath) {
        for (String file : filesPath) {
            File fileToBeDeleted = new File(file);
            fileToBeDeleted.delete();
        }
    }
}
