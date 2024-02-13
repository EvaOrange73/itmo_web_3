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
    public void processSubmitButton(){
        areaChecker.checkArea();
        dbManager.insertPoint();
    }
}
