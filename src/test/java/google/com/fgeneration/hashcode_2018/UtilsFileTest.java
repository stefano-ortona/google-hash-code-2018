package google.com.fgeneration.hashcode_2018;

import google.com.fgeneration.hashcode_2018.model.Intersection;
import google.com.fgeneration.hashcode_2018.model.Ride;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsFileTest {

    static private final String filePathExample = "a_example.in";
    static private UtilsFile fr = new UtilsFile(filePathExample);
    static private int[] actualHeader = new int[]{3, 4, 2, 3, 2, 10};
    static private int[] header = fr.getHeader();
    static private List<Ride> actualRides = new ArrayList<>();
    static private List<Ride> rides = fr.getRides();

    @BeforeClass
    public static void bringUp() {

        // 0 0 1 3 2 9
        actualRides.add(new Ride(0, new Intersection(0, 0), new Intersection(1, 3), 3, 9));
        // 1 2 1 0 0 9
        actualRides.add(new Ride(1, new Intersection(1, 2), new Intersection(1, 0), 0, 9));
        // 2 0 2 2 0 9
        actualRides.add(new Ride(2, new Intersection(2, 0), new Intersection(2, 2), 0, 9));
    }

    @Test
    public void testHeader() {
        testHeader(header, actualHeader);
    }

    private void testHeader(int[] actualHeader, int[] header) {
        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
        }
    }

    @Test
    public void testRides() {
        testRides(rides, actualRides);
    }

    private void testRides(List<Ride> rides, List<Ride> actualRides) {

        for (int i = 0; i < actualRides.size(); i++) {
            Assert.assertEquals(rides.get(i).getStart(), actualRides.get(i).getStart());
        }
    }

}
