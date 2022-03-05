package task1_installing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        StringBuilder output = new StringBuilder();
        //В папке Games создайте несколько директорий: src, res, savegames, temp.
        //создаём экземпляр класса File
        File src = new File("C:\\Users\\Sakhmina\\Games\\src");
        //с помощью метода объекта File mkdir создаём директорию
        //если директория успешно создана(ранее не существовала), выводим сообщение
        if (src.mkdir()) output.append(String.format("Директория %s успешно создана\n", "src"));
        File res = new File("C:\\Users\\Sakhmina\\Games\\res");
        if (res.mkdir()) output.append(String.format("Директория %s успешно создана\n", "res"));
        File savegames = new File("C:\\Users\\Sakhmina\\Games\\savegames");
        if (savegames.mkdir()) output.append(String.format("Директория %s успешно создана\n", "savegames"));
        File temp = new File("C:\\Users\\Sakhmina\\Games\\temp");
        if (temp.mkdir()) output.append(String.format("Директория %s успешно создана\n", "temp"));

        //В каталоге src создайте две директории: main, test.
        File main = new File("C:\\Users\\Sakhmina\\Games\\src\\main");
        if (main.mkdir()) output.append(String.format("Директория %s успешно создана в каталоге src\n", "main"));
        File test = new File("C:\\Users\\Sakhmina\\Games\\src\\test");
        if (test.mkdir()) output.append(String.format("Директория %s успешно создана в каталоге src\n", "test"));

        //В подкаталоге main создайте два файла: Main.java, Utils.java.
        File mainFile = new File("C:\\Users\\Sakhmina\\Games\\src\\main", "Main.java");
        File utilsFile = new File("C:\\Users\\Sakhmina\\Games\\src\\main", "Utils.java");
        try {
            if (mainFile.createNewFile())
                output.append(String.format("Файл %s был создан в подкаталоге main\n", "Main.java"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            if (utilsFile.createNewFile())
                output.append(String.format("Файл %s был создан в подкаталоге main\n", "Utils.java"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //В каталог res создайте три директории: drawables, vectors, icons.
        File drawables = new File("C:\\Users\\Sakhmina\\Games\\res\\drawables");
        if (drawables.mkdir())
            output.append(String.format("Директория %s успешно создана в каталоге res\n", "drawables"));
        File vectors = new File("C:\\Users\\Sakhmina\\Games\\res\\vectors");
        if (vectors.mkdir()) output.append(String.format("Директория %s успешно создана в каталоге res\n", "vectors"));
        File icons = new File("C:\\Users\\Sakhmina\\Games\\res\\icons");
        if (icons.mkdir()) output.append(String.format("Директория %s успешно создана в каталоге res\n", "icons"));


        //В директории temp создайте файл temp.txt.
        File tempTxt = new File("C:\\Users\\Sakhmina\\Games\\temp\\temp.txt");
        try {
            if (tempTxt.createNewFile())
                output.append(String.format("Файл %s был создан в подкаталоге main\n", "Main.java"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //посимвольная запись строки output в файл temp.txt
        try (FileWriter writer = new FileWriter("C:\\Users\\Sakhmina\\Games\\temp\\temp.txt", false)) {
            // запись всей строки
            writer.write(output.toString());
            // запись по символам
            writer.append('\n');
            writer.append('!');
            // дозаписываем и очищаем буфер
            // writer.flush();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
