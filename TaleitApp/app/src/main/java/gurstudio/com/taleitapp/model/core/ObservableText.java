package gurstudio.com.taleitapp.model.core;

public class ObservableText extends Observable<CharSequence>{
    public ObservableText(CharSequence value){
        super(value);
    }

    public ObservableText(){
        super("");
    }
}

