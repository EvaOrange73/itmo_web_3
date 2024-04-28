import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class PercentageCounter implements Serializable, PercentageCounterMBean {
    private String percentage;

    @Inject
    PointsCounter pointsCounter;

    @Inject
    MBeanManager mBeanManager;

    @PostConstruct
    public void init() {
        mBeanManager.addBean("percentageCounter", this);
    }

    @PreDestroy
    public void destroy() {
        mBeanManager.removeBean("percentageCounter");
    }

    @Override
    public void countPercentage() {
        this.percentage = (pointsCounter.getSuccessfulPoints() * 100 / pointsCounter.getAllPoints()) + "%";
    }

    @Override
    public String getPercentage() {
        return percentage;
    }
}
