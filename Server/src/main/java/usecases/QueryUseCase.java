package usecases;

import lang.SafeObject;
import model.Model;
import lang.Function;

/**
 * Created by gur on 11/6/2015.
 */
public abstract class QueryUseCase<S> extends ModelUseCase implements Function<Model> {
    protected QueryUseCase(SafeObject<Model> context) {
        super(context);
    }

    @Override
    final protected void safe(SafeObject<Model> model){
        model.read(this);
    }
}

