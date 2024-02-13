import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@Named
@SessionScoped
public class PointsList implements Serializable {
    @Inject
    DBManager dbManager;

    public List<Point> getPoints() {
        return dbManager.getPoints();
    }
}

