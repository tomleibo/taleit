package gurstudio.com.taleitapp.model.taleit;

import java.util.ArrayList;

import gurstudio.com.taleitapp.model.core.ApplicationModel;
import gurstudio.com.taleitapp.model.core.ObservableCollection;

public class TaleItModel extends ApplicationModel {
    ObservableCollection<Story> stories = new ObservableCollection<>(ArrayList.class);

    ObservableCollection<Category> categories = new ObservableCollection<>(ArrayList.class);

    public ObservableCollection<Story> getStories(){ return stories; }

    public ObservableCollection<Category> getCategories(){ return categories; }
}
