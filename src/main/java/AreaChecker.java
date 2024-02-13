import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class AreaChecker {
    private enum Quarters {
        FIRST, SECOND, THIRD, FOURTH;

        static Quarters quarter(float x, float y) {
            if (x >= 0 && y >= 0) return Quarters.FIRST;
            if (x < 0 && y >= 0) return Quarters.SECOND;
            if (x < 0 && y < 0) return Quarters.THIRD;
            return Quarters.FOURTH;
        }
    }

    @Inject
    Point point;

    public void checkArea() {
        float x = point.getX();
        float y = point.getY();
        float r = point.getR();

        boolean result = false;
        switch (Quarters.quarter(x, y)) {
            case FIRST:
                result = x < r && y < r;
                break;
            case SECOND:
                result = x * x + y * y < r * r;
                break;
            case THIRD:
                result = y > -2 * x - r;
                break;
            case FOURTH:
                break;
        }
        point.setResult(result);
    }
}
