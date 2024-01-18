import java.io.Serializable;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

@ManagedBean
@Named
@SessionScoped
@FacesValidator("pointValidator")
public class PointValidator implements Validator, Serializable {
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj)
            throws ValidatorException {
        float min = Float.parseFloat((String) uic.getAttributes().get("min"));
        float max = Float.parseFloat((String) uic.getAttributes().get("max"));
        System.out.println(min);

        float value = (float) obj;

        if (value < min || value > max) {
            ((UIInput) uic).setValid(false);

            FacesMessage msg = new FacesMessage(
                    "Введите число от " + min + " до " + max);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }
}