package gurstudio.com.taleitapp.model.taleit;

import java.util.ArrayList;

import gurstudio.com.taleitapp.model.core.ApplicationModel;
import gurstudio.com.taleitapp.model.core.Observable;
import gurstudio.com.taleitapp.model.core.ObservableCollection;

public class TaleItModel extends ApplicationModel {
    Observable<Category> currentViewedCategory = new Observable<>(null);
    Observable<Story> currentViewedStory = new Observable<>(null);
    Observable<Paragraph> currentViewedParagraph = new Observable<>(null);
    ObservableCollection<Story> stories = new ObservableCollection<>(ArrayList.class);
    ObservableCollection<Category> categories = new ObservableCollection<>(ArrayList.class);

    public ObservableCollection<Story> getStories(){ return stories; }
    public ObservableCollection<Category> getCategories(){ return categories; }

    public Observable<Category> getCurrentViewedCategory() { return currentViewedCategory; }
    public Observable<Story> getCurrentViewedStory() { return currentViewedStory; }
    public Observable<Paragraph> getCurrentViewedParagraph() { return currentViewedParagraph; }

    public void setCurrentViewedCategory(Category currentViewedCategory) { this.currentViewedCategory.set(currentViewedCategory); }
    public void setCurrentViewedStory(Story currentViewedStory) { this.currentViewedStory.set(currentViewedStory); }
    public void setCurrentViewedParagraph(Paragraph currentViewedParagraph) { this.currentViewedParagraph.set(currentViewedParagraph); }
}
