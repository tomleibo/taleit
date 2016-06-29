package taleit.usecases;

import taleit.exceptions.UsecaseException;
import taleit.lang.SafeObject;
import taleit.model.Model;

/**
 * Created by gur on 12/23/2015.
 */
public abstract class ModelUseCase implements UseCase{
    final SafeObject<Model> model;

    protected ModelUseCase(SafeObject<Model> context){
        this.model = context;
    }

    protected void pre() throws UsecaseException {};
    protected void post()throws UsecaseException {};
    protected abstract void safe(SafeObject<Model> model) throws UsecaseException;

    public void perform() {
        pre();
        safe(model);
        post();
    }
}