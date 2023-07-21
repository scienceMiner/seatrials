package interviewcode;

import com.scienceminer.interviewcode.Powerset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowersetTests {

    Powerset ps;

    @BeforeEach
    void setUp() {
        ps = new Powerset(8);



    }

    @Test
    void firstTest() throws CloneNotSupportedException {
        Powerset ps2 = ps.calculate();

        String s1 = ps2.getPowerset().toString();

        System.out.println(s1);
        Integer p1 = ps2.getPowerset().size();

        assertEquals(255,p1);
    }

    @Test
    void secondTest() throws CloneNotSupportedException {
        Powerset ps2 = new Powerset(5);

        ps2 = ps2.calculate();

        String s1 = ps2.getPowerset().toString();

        System.out.println(s1);
        Integer p1 = ps2.getPowerset().size();

        assertEquals(31,p1);
    }




}
