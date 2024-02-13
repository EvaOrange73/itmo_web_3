import com.google.gson.Gson;
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
    public String getPointsJson() {
        return new Gson().toJson(dbManager.getPoints());
    }
}

