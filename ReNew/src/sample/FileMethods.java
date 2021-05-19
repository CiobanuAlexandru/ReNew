package sample;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileMethods {

    private static int amount = 0;
    private static String realpath;
    private static File myObj;

    public FileMethods (String path, String filename) {

        while (true) {

            try {
                realpath = path+filename+amount+".txt";
                 myObj = new File(realpath);

                if (myObj.createNewFile()) {
                    break;
                } else {
                    amount++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FileMethods (String path, String filename, int currentamount) {

        realpath = path+filename+currentamount+".txt";
        myObj = new File(realpath);

    }

    public String readspecificline (int line) throws IOException {


        return Files.readAllLines(Paths.get(myObj.getPath())).get(line);
    }

    public void deleteFile () {

        if (myObj.delete()) {
            System.out.println("");
        } else {
            System.out.println("");
        }
    }

    public static int countFile (String path) {

        File directory = new File(path);

        return Objects.requireNonNull(directory.list()).length;
    }

    public static void openHTMLFile (String path) throws IOException {

        File htmlFile = new File(path);
        Desktop.getDesktop().browse(htmlFile.toURI());

    }

    public void removeLine(String lineContent) throws IOException {

        List<String> out = Files.lines(myObj.toPath())
                .filter(line -> !line.contains(lineContent))
                .collect(Collectors.toList());
        Files.write(myObj.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

    }

    public void writeFile (String text) {

        try {
            FileWriter myWriter = new FileWriter(realpath);
            myWriter.write(text);
            myWriter.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void addnewLine (String line) throws IOException {

        Writer output = new BufferedWriter(new FileWriter(realpath,true));
        output.append(line);
        output.close();

    }

    public String readFile() {
        StringBuilder data = new StringBuilder();

        try {
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
                data.append("\n");

            }

            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data.toString();
    }

    public static String readFile(String path, String filename, int number) {
        StringBuilder data = new StringBuilder();
        File file = new File(path+filename+number+".txt");

        try {
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
                data.append("\n");

            }

            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data.toString();
    }

    public void setHiddenAttrib() {

        Path path = Paths.get(myObj.getPath());

        try {
            Files.setAttribute(path, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
