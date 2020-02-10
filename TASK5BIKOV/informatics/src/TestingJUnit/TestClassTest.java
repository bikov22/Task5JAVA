import org.junit.Test;

import static org.junit.Assert.*;

public class TestClassTest {

    @Test
    public void getSquare() {
        TestClass testMethod = new TestClass();
        int square = testMethod.getSquare();
        assertEquals(square, 50);
    }
}