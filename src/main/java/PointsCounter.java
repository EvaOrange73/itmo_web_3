import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class PointsCounter implements Serializable {
    private int allPoints;
    private int successfulPoints;
    private int mistakesInRow;

    @Inject
    Point point;

    public PointsCounter() {
        this.allPoints = 0;
        this.successfulPoints = 0;
        this.mistakesInRow = 0;
    }

    public String countPoint() {
        this.allPoints++;
        if (point.isResult()) {
            this.successfulPoints++;
            this.mistakesInRow = 0;
        } else
            this.mistakesInRow++;

        if (this.mistakesInRow >= 3)
            return "Вы совершили 3 ошибки подряд";
        return "";
    }

    public int getAllPoints() {
        return allPoints;
    }

    public int getSuccessfulPoints() {
        return successfulPoints;
    }
}
