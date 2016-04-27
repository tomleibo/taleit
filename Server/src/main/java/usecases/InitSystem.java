package usecases;

import lang.SafeObject;
import model.Model;

/**
 * Created by gur on 4/27/2016.
 */
public class InitSystem extends ActionUseCase{
    public InitSystem(SafeObject<Model> context) {
        super(context);
    }

    public void perform(Model model) {
        model.init();
    }
}
