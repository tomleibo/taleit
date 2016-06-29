package taleit.usecases;

import taleit.lang.SafeObject;
import taleit.model.Model;
import taleit.lang.Action;

public abstract class ActionUseCase extends ModelUseCase implements Action<Model> {
    protected ActionUseCase(SafeObject<Model> context) {
        super(context);
    }

    @Override
    final protected void safe(SafeObject<Model> model){
        model.write(this);
    }
}
