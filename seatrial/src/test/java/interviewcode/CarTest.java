package interviewcode;


import com.scienceminer.interviewcode.quickstart.Car;
import com.scienceminer.interviewcode.quickstart.CarColour;
import com.scienceminer.interviewcode.quickstart.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CarTest {

    Car c1;
    @BeforeEach
    void setUp() {
        c1 = new Car(CarColour.BLACK,"BF59 GEN" , Manufacturer.TESLA ); // foolish why did I create with
        // Car c1 - so dumb so dumb so dumb so dumb so dumb
    }

    @Test
    void testCarMakeGetter() {
        Manufacturer m1 = Manufacturer.TESLA;

        assertEquals(m1,c1.getMake());
    }

    @Test
    void testBadCarMakeGetter() {
        Manufacturer m1 = Manufacturer.FORD;

        // not equal as actually different
        assertNotEquals(m1,c1.getMake());
    }


    @Test
    void testBadCarMakeGetterString() {
        Manufacturer m1 = Manufacturer.FORD;

        // not equal as comparing string and a car - dumb
        assertNotEquals(m1.getName(),c1.getMake());
    }


}
