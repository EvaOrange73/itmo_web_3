import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ButtonProcessor {
    @Inject
    AreaChecker areaChecker;
    @Inject
    DBManager dbManager;

    String errorMessage;

    @Inject
    PointsCounter pointsCounter;

    @Inject
    PercentageCounter percentageCounter;

    public void processSubmitButton() {
        areaChecker.checkArea();
        dbManager.insertPoint();
        this.errorMessage = pointsCounter.countPoint();
        percentageCounter.countPercentage();
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
