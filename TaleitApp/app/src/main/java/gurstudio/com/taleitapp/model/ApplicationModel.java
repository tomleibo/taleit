package gurstudio.com.taleitapp.model;

import java.util.ArrayList;

/**
 * Created by gur on 4/23/2016.
 */
public enum ApplicationModel {
    Instance;

    ObservableCollection<Story> stories = new ObservableCollection<>(ArrayList.class);

    public ObservableCollection<Story> getStories(){ return stories; }
}

