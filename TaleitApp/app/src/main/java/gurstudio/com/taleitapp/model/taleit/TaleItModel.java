package gurstudio.com.taleitapp.model.taleit;

import com.facebook.AccessToken;
import com.facebook.Profile;

import java.util.ArrayList;
import java.util.Stack;

import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.core.ApplicationModel;
import gurstudio.com.taleitapp.model.core.Observable;
import gurstudio.com.taleitapp.model.core.ObservableCollection;
import gurstudio.com.taleitapp.model.core.Persistent;

public class TaleItModel extends ApplicationModel {
    public TaleItModel(TaleItApplication application){
        super(application);
    }

    Observable<Category> currentViewedCategory = new Observable<>(null);
    Observable<Story> currentViewedStory = new Observable<>(null);
    Observable<Paragraph> currentViewedParagraph = new Observable<>(null);
    ObservableCollection<Story> stories = new ObservableCollection<>(ArrayList.class);
    ObservableCollection<Category> categories = new ObservableCollection<>(ArrayList.class);
    Stack<Paragraph> navigationPath = new Stack<>();
    Persistent<String> cookie = new Persistent<>(this, "application_user_cookie", String.class);
    Persistent<Profile> facebookProfile = new Persistent<>(this, "facebook_profile", Profile.class);
    Persistent<AccessToken> accessToken = new Persistent<>(this, "facebook_access_token", AccessToken.class);

    public ObservableCollection<Story> getStories(){ return stories; }
    public ObservableCollection<Category> getCategories(){ return categories; }
    public Stack<Paragraph> getNavigationPath() { return navigationPath; }

    public Observable<Category> getCurrentViewedCategory() { return currentViewedCategory; }
    public Observable<Story> getCurrentViewedStory() { return currentViewedStory; }
    public Observable<Paragraph> getCurrentViewedParagraph() { return currentViewedParagraph; }

    public void setCurrentViewedCategory(Category currentViewedCategory) { this.currentViewedCategory.set(currentViewedCategory); }
    public void setCurrentViewedStory(Story currentViewedStory) { this.currentViewedStory.set(currentViewedStory); }
    public void setCurrentViewedParagraph(Paragraph currentViewedParagraph) { this.currentViewedParagraph.set(currentViewedParagraph); }

    public void setFacebookProfile(Profile facebookProfile) {
        this.facebookProfile.set(facebookProfile); }
    public Observable<Profile> getFacebookProfile() { return facebookProfile; }

    public AccessToken getAccessToken() {
        return accessToken.get();
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken.set(accessToken);
    }

    public Observable<String> getUserCookie(){
        return cookie;
    }

    public void setUserCookie(String cookie){
        this.cookie.set(cookie);
    }
}