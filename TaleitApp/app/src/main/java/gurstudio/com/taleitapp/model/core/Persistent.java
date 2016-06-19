package gurstudio.com.taleitapp.model.core;

/**
 * Created by gur on 6/19/2016.
 */
public class Persistent<T> extends Observable<T> {
    private final String key;
    private final SharedPreferencesManager spm;

    public Persistent(ApplicationModel modelContext, String key, Class<T> type){
        this.key = key;
        this.spm = modelContext.getSharedPreferencesManager();

        T value;
        try {
            value = spm.read(key, type);
        }
        catch (Exception ex){
            return;
        }
        set(value);
    }

    public void set(T value){
        try {
            if (spm != null) {
                spm.write(key, value);
            }
        }
        catch (Exception ex){
            return;
        }

        super.set(value);
    }
}
