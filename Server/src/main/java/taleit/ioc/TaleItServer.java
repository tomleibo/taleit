package taleit.ioc;

import taleit.lang.SafeObject;
import taleit.model.Model;

/**
 * Created by gur on 12/23/2015.
 */
 public enum TaleItServer {
    Instance;

    SafeObject<Model> safeModel;

    TaleItServer(){
        safeModel = new SafeObject<Model>(new Model());
    }

    public SafeObject<Model> getSafeModel(){
        return safeModel;
    }
}
