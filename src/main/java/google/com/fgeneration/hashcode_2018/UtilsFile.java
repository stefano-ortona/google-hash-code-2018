package google.com.fgeneration.hashcode_2018;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import google.com.fgeneration.hashcode_2018.model.*;

public class UtilsFile {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] header;
    private List<Ride> rides;

    // 2. generate setters and getters for header and data
    public void setHeader(int[] header) {
        this.header = header;
    }

    public int[] getHeader() {
        return header;
    }

    public int getRowAmount() {
        return this.header[0];
    }

    public int getColumnAmount() {
        return this.header[1];
    }

    public int getDriversAmount() {
        return this.header[2];
    }

    public int getRidesAmount() {
        return this.header[3];
    }

    public int getBonusAmount() {
        return this.header[4];
    }

    public int getStepsAmount() {
        return this.header[5];
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    //3. define logic of createHeader() and createRides()

    public void createHeader() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        // always finish with this.setHeader()
        this.setHeader(converted);
    }

    public void createRides() {
        String[] file = this.getFile();
        int indexOfLastRide = this.getRidesAmount();
        String[] dataRaw = cloneArrayOfString(file, 1, indexOfLastRide + 1);

        List<Ride> rides = convertArrayOfStringToListOfRide(dataRaw);

        // always finish with this.setData()
        this.setRides(rides);
    }

    public List<Ride> convertArrayOfStringToListOfRide(String[] dataRaw) {


        List<Ride> result = new ArrayList<>();

        for (int i = 0; i < dataRaw.length; i++) {
            result.add(convertStringToRide(dataRaw[i], i));
        }
        return result;
    }

    public Ride convertStringToRide(String string, int id) {

        String[] split = splitString(string, " ");

        // create intersection start
        Intersection start = new Intersection(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

        // create intersection end
        Intersection end = new Intersection(Integer.parseInt(split[2]), Integer.parseInt(split[3]));

        return new Ride(id, start, end, Integer.parseInt(split[4]), Integer.parseInt(split[5]));
    }

    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/fgeneration/hashcode_2018/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsFile(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            //LOGGER.info("Header creation: start");
            createHeader();
            //LOGGER.info("Header creation: done");

            //LOGGER.info("Ride creation: start");
            createRides();
            //LOGGER.info("Ride creation: done");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Utils
    public void readFile(String filepath) throws IOException {

        String line;
        List<String> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(filepath));

        while ((line = in.readLine()) != null) {
            list.add(line);  //Add line to file
        }

        //Cast List to Array of Array
        String[] stringArr = list.toArray(new String[0]);

        this.setFile(stringArr);

    }

    public String getFirstLineOfFile() {
        return this.file[0];
    }

    public String[] splitString(String string, String separator) {
        return string.split(separator);
    }

    public String[] cloneArrayOfString(String[] source, Integer start, Integer to) {
        return Arrays.copyOfRange(source, start, to);
    }

    public char[] convertStringToArrayOfChar(String string) {
        return string.toCharArray();
    }

    public char[][] cconvertArrayOfStringToArrayOfCharArrays(String[] dataRaw) {

        char[][] result = new char[dataRaw.length][dataRaw[0].length()];

        for (int i = 0; i < dataRaw.length; i++) {
            result[i] = convertStringToArrayOfChar(dataRaw[i]);
        }
        return result;
    }

    public String convertArrayOfChartToString(char[] a) {
        return new String(a);
    }

    public int[] convertArrayOfStringToArrayOfInt(String[] strings) {
        return Arrays.asList(strings).stream().mapToInt(Integer::parseInt).toArray();

    }

}
