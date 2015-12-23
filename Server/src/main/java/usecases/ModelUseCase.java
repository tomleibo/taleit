package usecases;

import lang.SafeObject;
import model.Model;

/**
 * Created by gur on 12/23/2015.
 */
public abstract class ModelUseCase implements UseCase{
    final SafeObject<Model> model;

    protected ModelUseCase(SafeObject<Model> context){
        this.model = context;
    }

    protected void pre(){};
    protected void post(){};
    protected abstract void safe(SafeObject<Model> model);

    public void perform() {
        pre();

        safe(model);

        post();
    }
}
