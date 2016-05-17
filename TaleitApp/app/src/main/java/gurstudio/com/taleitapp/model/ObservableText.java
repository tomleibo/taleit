package gurstudio.com.taleitapp.model;

public class ObservableText extends Observable<CharSequence>{
    public ObservableText(CharSequence value){
        super(value);
    }

    public ObservableText(){
        super("");
    }
}

