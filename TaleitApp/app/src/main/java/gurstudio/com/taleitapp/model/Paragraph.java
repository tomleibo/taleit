package gurstudio.com.taleitapp.model;

import java.util.ArrayList;

public class Paragraph extends TaleItCreationEntity{
    public ObservableText title = new ObservableText("");
    public ObservableText text = new ObservableText("");

    public ObservableCollection<Paragraph> children = new ObservableCollection<>(ArrayList.class);
}
