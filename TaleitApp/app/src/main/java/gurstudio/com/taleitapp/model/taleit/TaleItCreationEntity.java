package gurstudio.com.taleitapp.model.taleit;

import gurstudio.com.taleitapp.model.core.ObservableText;

public abstract class TaleItCreationEntity extends TaleItEntity {
    public ObservableText author = new ObservableText();
    public ObservableText name = new ObservableText("");
}
