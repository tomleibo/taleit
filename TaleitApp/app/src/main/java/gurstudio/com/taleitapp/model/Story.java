package gurstudio.com.taleitapp.model;

public class Story extends TaleItCreationEntity{
    public ObservableText category = new ObservableText("");
    public ObservableText title = new ObservableText("");
    public Observable<Paragraph> root = new Observable<>(new Paragraph());
}
