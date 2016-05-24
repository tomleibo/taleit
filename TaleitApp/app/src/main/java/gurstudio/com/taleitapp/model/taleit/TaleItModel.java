package gurstudio.com.taleitapp.model.taleit;

import java.util.ArrayList;

import gurstudio.com.taleitapp.model.core.ApplicationModel;
import gurstudio.com.taleitapp.model.core.ObservableCollection;

public class TaleItModel extends ApplicationModel {
    private Category currentViewedCategory;
    private Story currentViewedStory;
    ObservableCollection<Story> stories = new ObservableCollection<>(ArrayList.class);
    ObservableCollection<Category> categories = new ObservableCollection<>(ArrayList.class);

    public ObservableCollection<Story> getStories(){ return stories; }
    public ObservableCollection<Category> getCategories(){ return categories; }

    public Category getCurrentViewedCategory() { return currentViewedCategory; }

    public void setCurrentViewedCategory(Category currentViewedCategory) { this.currentViewedCategory = currentViewedCategory; }

    public Story getCurrentViewedStory() { return currentViewedStory; }

    public void setCurrentViewedStory(Story currentViewedStory) { this.currentViewedStory = currentViewedStory; }
}
