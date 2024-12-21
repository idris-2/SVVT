package TestingPurposes;

import org.junit.jupiter.api.*;

public class CalculatorTest {
    @BeforeAll
    public static void setUpBeforeClass() {
        System.out.println("Before all tests");
    }
    @BeforeEach
    public void setUp() {
        System.out.println("Before each test");
    }
    @Test
    public void testAddition() {
        System.out.println("Test addition");
    }
    @AfterEach
    public void tearDown() {
        System.out.println("After each test");
    }
    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("After all tests");
    }
}
