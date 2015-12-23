package ioc;

import lang.SafeObject;
import model.Model;

/**
 * Created by gur on 12/23/2015.
 */
 public enum Server {
    Instance;

    SafeObject<Model> safeModel;

    Server(){
        safeModel = new SafeObject<Model>(new Model());
    }

    public SafeObject<Model> getSafeModel(){
        return safeModel;
    }
}
