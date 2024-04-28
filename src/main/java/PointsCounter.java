import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class PointsCounter implements Serializable, PointsCounterMBean {
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

    @Inject
    MBeanManager mBeanManager;

    @PostConstruct
    public void init() {
        mBeanManager.addBean("pointsCounter", this);
    }

    @PreDestroy
    public void destroy() {
        mBeanManager.removeBean("pointsCounter");
    }


    @Override
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

    @Override
    public int getAllPoints() {
        return allPoints;
    }

    @Override
    public int getSuccessfulPoints() {
        return successfulPoints;
    }

    @Override
    public int getMistakesInRow() {
        return mistakesInRow;
    }
}
