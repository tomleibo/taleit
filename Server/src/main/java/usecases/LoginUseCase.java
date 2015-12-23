package usecases;

import model.Model;

/**
 * Created by gur on 12/23/2015.
 */
public class LoginUseCase extends ActionUseCase {
    public void perform(Model state) {
        if (state.getUsers().contains("Sharon")){
            // do something
        }
        else {
            // do something else
        }
    }

    @Override
    protected void safe(Object model) {
        perform((Model) model);
    }
}