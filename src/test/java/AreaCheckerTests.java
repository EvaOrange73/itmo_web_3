import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AreaCheckerTests {

    private boolean checkArea(float x, float y, float r) {
        var checker = new AreaChecker();
        checker.point = new Point();
        checker.point.setX(x);
        checker.point.setY(y);
        checker.point.setR(r);
        checker.checkArea();
        return checker.point.isResult();
    }

    @Test
    void firstQuarter() {
        assertTrue(checkArea(0, 0, 1));
        assertTrue(checkArea(1, 1, 2));
        assertTrue(!checkArea(1, 1, 0.5f));
    }

    @Test
    void secondQuarter() {
        assertTrue(checkArea(-1, 0, 2));
        assertTrue(!checkArea(-1, 1, 1));
    }

    @Test
    void thirdQuarter() {
        assertTrue(checkArea(-1, -1, 4));
        assertTrue(!checkArea(-1, -1, 2));
    }

    @Test
    void fourthQuarter() {
        assertTrue(!checkArea(1, -1, 0.1f));
        assertTrue(!checkArea(1, -1, 4));
    }
}