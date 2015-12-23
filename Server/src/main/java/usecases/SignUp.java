package usecases;

import lang.SafeObject;
import model.Model;

/**
 * Created by gur on 12/23/2015.
 */
public class SignUp extends ActionUseCase {
    protected SignUp(SafeObject<Model> context) {
        super(context);
    }

    public void perform(Model value) {

    }
}
