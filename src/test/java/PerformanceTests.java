import com.commsen.stopwatch.Report;
import com.commsen.stopwatch.Stopwatch;
import de.mcs.jmeasurement.MeasureFactory;
import de.mcs.jmeasurement.MeasurePoint;
import de.mcs.jmeasurement.Monitor;
import org.junit.jupiter.api.Test;

class PerformanceTests {

    private float randomFloat() {
        return (float) (-5 + Math.random() * 10);
    }

    private boolean checkArea() {
        var checker = new AreaChecker();
        checker.point = new Point();
        checker.point.setX(this.randomFloat());
        checker.point.setY(this.randomFloat());
        checker.point.setR(this.randomFloat());
        checker.checkArea();
        return checker.point.isResult();
    }

    @Test
    void stopwatch() {
        Stopwatch.setActive(true);
        for (int i = 0; i < 10000; i++) {
            long id = Stopwatch.start("test", "checkArea");
            this.checkArea();
            Stopwatch.stop(id);
        }
        Report report = Stopwatch.getSingleReport("test", "checkArea");
        System.out.print(report + "\n");
    }

    @Test
    void jmeasurement() {
        Monitor monitor1 = null;
        MeasurePoint point;
        MeasureFactory.setApplicationName("testFactory");
        for (int i = 0; i < 10; i++) {
            point = MeasureFactory.getMeasurePoint("point" + i);
            monitor1 = MeasureFactory.start(point.getName());
            this.checkArea();
            monitor1.stop();
        }

        System.out.println(MeasureFactory.asString());
    }
}