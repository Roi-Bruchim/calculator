import org.junit.Assert;
import org.junit.Test;

public class LibraryTest {

    private final double DELTA = 0.0001; // Delta for double comparison

    @Test
    public void testAdd() {
        Library library = new Library();
        Assert.assertEquals(5.0, library.Add(2.0, 3.0), DELTA);
    }

    @Test
    public void testSubtract() {
        Library library = new Library();
        Assert.assertEquals(3.0, library.subtract(5.0, 2.0), DELTA);
    }

    @Test
    public void testMultiply() {
        Library library = new Library();
        Assert.assertEquals(10.0, library.multiply(2.0, 5.0), DELTA);
    }

    @Test
    public void testDivision() {
        Library library = new Library();
        Assert.assertEquals(2.0, library.division(10.0, 5.0), DELTA);
    }

    @Test
    public void testFact() {
        Library library = new Library();
        Assert.assertEquals(120.0, library.fact(5.0), DELTA);
    }

    @Test
    public void testPower() {
        Library library = new Library();
        Assert.assertEquals(8.0, library.power(2.0, 3.0), DELTA);
    }

    @Test
    public void testChoose() {
        Library library = new Library();
        Assert.assertEquals(10.0, library.choose(5.0, 2.0), DELTA);
    }

    @Test
    public void testSquareRoot() {
        Library library = new Library();
        Assert.assertEquals(4.0, library.squareRoot(16.0), DELTA);
    }

    @Test
    public void testAbsoluteValue() {
        Library library = new Library();
        Assert.assertEquals(5.0, library.absoluteValue(-5.0), DELTA);
    }

    @Test
    public void testLogBase10() {
        Library library = new Library();
        Assert.assertEquals(2.0, library.logBase10(100.0), DELTA);
    }

    @Test
    public void testSin() {
        Library library = new Library();
        Assert.assertEquals(0.5, library.sin(Math.PI / 6.0), DELTA);
    }

    @Test
    public void testCos() {
        Library library = new Library();
        Assert.assertEquals(0.5, library.cos(Math.PI / 3.0), DELTA);
    }

    @Test
    public void testTan() {
        Library library = new Library();
        Assert.assertEquals(1.0, library.tan(Math.PI / 4.0), DELTA);
    }

    @Test
    public void testArcsin() {
        Library library = new Library();
        Assert.assertEquals(Math.PI / 6.0, library.arcsin(0.5), DELTA);
    }

    @Test
    public void testArccos() {
        Library library = new Library();
        Assert.assertEquals(Math.PI / 3.0, library.arccos(0.5), DELTA);
    }

    @Test
    public void testArctan() {
        Library library = new Library();
        Assert.assertEquals(Math.PI / 4.0, library.arctan(1.0), DELTA);
    }

    @Test
    public void testPercentage() {
        Library library = new Library();
        Assert.assertEquals(20.0, library.percentage(100.0, 20.0), DELTA);
    }

    @Test
    public void testPI() {
        Library library = new Library();
        Assert.assertEquals(Math.PI, library.PI(), DELTA);
    }

    @Test
    public void testE() {
        Library library = new Library();
        Assert.assertEquals(Math.E, library.e(), DELTA);
    }

    @Test
    public void testComplexOperation() {
        Library library = new Library();

        // Calculate the square root of 25
        double squareRootResult = library.squareRoot(25);

        // Calculate the power of 2^3 = 8
        double powerResult = library.power(2, 3);

        // Calculate the factorial of 5 = 120
        double factorialResult = library.fact(5);

        // Calculate the sine of PI/6
        double sineResult = library.sin(Math.PI / 6);

        // Calculate the logarithm base 10 of 100 = 2
        double logResult = library.logBase10(100);

        // Combine results: (sqrt(25) + 2^3) / (5! - sin(PI/6) + log10(100))
        double finalResult = (squareRootResult + powerResult) / (factorialResult - sineResult + logResult);

        // Expected final result: (5 + 8) / (120 - 0.5 + 2) = 13 / 121.5 = ~0.1069
        Assert.assertEquals(0.1069, finalResult, DELTA);
    }
}
