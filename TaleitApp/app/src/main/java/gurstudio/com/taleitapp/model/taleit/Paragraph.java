package gurstudio.com.taleitapp.model.taleit;

import java.util.ArrayList;

import gurstudio.com.taleitapp.model.core.ObservableCollection;
import gurstudio.com.taleitapp.model.core.ObservableText;

public class Paragraph extends TaleItCreationEntity{
    public ObservableText title = new ObservableText("");
    public ObservableText text = new ObservableText("");

    public ObservableCollection<Paragraph> children = new ObservableCollection<>(ArrayList.class);
}
