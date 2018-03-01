package google.com.fgeneration.hashcode_2018;

import google.com.fgeneration.hashcode_2018.model.Intersection;
import google.com.fgeneration.hashcode_2018.model.Ride;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsFileTest {

    static private final String filePathExampleA = "a_example.in";
    static private UtilsFile frA = new UtilsFile(filePathExampleA);
    static private int[] actualHeaderA = new int[]{3, 4, 2, 3, 2, 10};
    static private int[] headerA = frA.getHeader();
    static private List<Ride> actualRidesA = new ArrayList<>();
    static private List<Ride> ridesA = frA.getRides();
    static private int actualRidesSizeA = 3;
    static private int ridesSizeA = ridesA.size();


    static private final String filePathExampleB = "b_example.in";
    static private UtilsFile frB = new UtilsFile(filePathExampleB);
    static private int[] actualHeaderB = new int[]{3, 4, 2, 12, 2, 10};
    static private int[] headerB = frB.getHeader();
    static private List<Ride> actualRidesB = new ArrayList<>();
    static private List<Ride> ridesB = frB.getRides();
    static private int actualRidesSizeB = 12;
    static private int ridesSizeB = ridesB.size();


    @BeforeClass
    public static void bringUp() {

        // a_example.in
        // 0 0 1 3 2 9
        actualRidesA.add(new Ride(0, new Intersection(0, 0), new Intersection(1, 3), 2, 9));
        // 1 2 1 0 0 9
        actualRidesA.add(new Ride(1, new Intersection(1, 2), new Intersection(1, 0), 0, 9));
        // 2 0 2 2 0 9
        actualRidesA.add(new Ride(2, new Intersection(2, 0), new Intersection(2, 2), 0, 9));

        // b_example.in
        // 1 1 2 4 3 9
        actualRidesB.add(new Ride(0, new Intersection(1, 1), new Intersection(2, 4), 3, 9));
        //  2 3 2 1 1 9
        actualRidesB.add(new Ride(1, new Intersection(2, 3), new Intersection(2, 1), 1, 9));
        // 3 1 3 3 1 9
        actualRidesB.add(new Ride(2, new Intersection(3, 1), new Intersection(3, 3), 1, 9));
        // 0 0 1 3 2 9
        actualRidesB.add(new Ride(3, new Intersection(0, 0), new Intersection(1, 3), 2, 9));
        // 1 2 1 0 0 9
        actualRidesB.add(new Ride(4, new Intersection(1, 2), new Intersection(1, 0), 0, 9));
        // 2 0 2 2 0 9
        actualRidesB.add(new Ride(5, new Intersection(2, 0), new Intersection(2, 2), 0, 9));
        // 1 1 2 4 3 9
        actualRidesB.add(new Ride(6, new Intersection(1, 1), new Intersection(2, 4), 3, 9));
        //  2 3 2 1 1 9
        actualRidesB.add(new Ride(7, new Intersection(2, 3), new Intersection(2, 1), 1, 9));
        // 3 1 3 3 1 9
        actualRidesB.add(new Ride(8, new Intersection(3, 1), new Intersection(3, 3), 1, 9));
        // 0 0 1 3 2 9
        actualRidesB.add(new Ride(9, new Intersection(0, 0), new Intersection(1, 3), 2, 9));
        // 1 2 1 0 0 9
        actualRidesB.add(new Ride(10, new Intersection(1, 2), new Intersection(1, 0), 0, 9));
        // 2 0 2 2 0 9
        actualRidesB.add(new Ride(11, new Intersection(2, 0), new Intersection(2, 2), 0, 9));

    }

    @Test
    public void testHeaderA() {
        testHeader(headerA, actualHeaderA);
    }

    //@Test
    public void testRidesA() {
        testRides(ridesA, actualRidesA);
    }

    @Test
    public void testRidesSizeA() {
        Assert.assertEquals(actualRidesSizeA, ridesSizeA);
    }

    @Test
    public void testHeaderB() {
        testHeader(headerB, actualHeaderB);
    }

    @Test
    public void testRidesB() {
        testRides(ridesB, actualRidesB);
    }

    @Test
    public void testRidesSizeB() {
        Assert.assertEquals(ridesSizeB, actualRidesSizeB);
    }


    private void testHeader(int[] actualHeader, int[] header) {
        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
        }
    }

    private void testRides(List<Ride> rides, List<Ride> actualRides) {

        for (int i = 0; i < rides.size(); i++) {
            Assert.assertEquals(rides.get(i).getStart(), actualRides.get(i).getStart());
            Assert.assertEquals(rides.get(i).getEnd(), actualRides.get(i).getEnd());
            Assert.assertEquals(rides.get(i).getMinStartTime(), actualRides.get(i).getMinStartTime());
            Assert.assertEquals(rides.get(i).getMaxEndTime(), actualRides.get(i).getMaxEndTime());
        }
    }

}
