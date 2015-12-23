package usecases;

import lang.SafeObject;
import model.Model;
import lang.Action;

public abstract class ActionUseCase extends ModelUseCase implements Action<Model> {
    protected ActionUseCase(SafeObject<Model> context) {
        super(context);
    }

    @Override
    protected void safe(SafeObject<Model> model){
        model.write(this);
    }
}
