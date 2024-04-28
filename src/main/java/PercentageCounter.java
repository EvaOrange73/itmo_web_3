import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class PercentageCounter implements Serializable {
    private String percentage;

    @Inject
    PointsCounter pointsCounter;

    public void countPercentage() {
        this.percentage = (pointsCounter.getSuccessfulPoints() * 100 / pointsCounter.getAllPoints()) + "%";
    }
}
