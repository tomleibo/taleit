package gurstudio.com.taleitapp.networkhandlers.taleit;

import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;

public abstract class TaleItResponseHandlerBase extends NetworkResponseHandlerBase<TaleItApplication> {
    public TaleItResponseHandlerBase (TaleItApplication application) {
        super(application);
    }
}
