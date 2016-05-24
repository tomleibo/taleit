package gurstudio.com.taleitapp.model.taleit;

import gurstudio.com.taleitapp.model.core.Observable;
import gurstudio.com.taleitapp.model.core.ObservableText;

public class Story extends TaleItCreationEntity{
    public ObservableText category = new ObservableText("");
    public ObservableText title = new ObservableText("");
    public Observable<Paragraph> root = new Observable<>(new Paragraph());
    public ObservableText image = new ObservableText("");
}