package gurstudio.com.taleitapp.networkhandlers.taleit;

import com.gurkashi.fj.lambdas.Selector;
import com.gurkashi.fj.queries.stracture.Queriable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import gurstudio.com.taleitapp.BuildConfig;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.taleit.Category;

public class GetCategoriesResponseHandler extends TaleItResponseHandlerBase {
    public GetCategoriesResponseHandler(TaleItApplication application) {
        super(application);
    }

    @Override
    public void onResponse(JSONObject response) {
        Collection<JSONObject> data;
        try {
            data = toList(response.getJSONObject("data").getJSONArray("categories"));
        } catch (JSONException e) {
            return;
        }

        try {
            application.getApplicationModel().getCategories().clear();
            Queriable.create(JSONObject.class)
                    .select(new Selector<JSONObject, Category>() {
                        @Override
                        public Category select(JSONObject jsonObject) {
                            Category category = new Category();

                            try {
                                String name = jsonObject.getString("name");
                                String image = "http://" + BuildConfig.SERVER_ADDRESS + ":8080" + jsonObject.getString("image").replace("localhost", BuildConfig.SERVER_ADDRESS);
                                String hover = "http://" + BuildConfig.SERVER_ADDRESS + ":8080" + jsonObject.getString("hover").replace("localhost", BuildConfig.SERVER_ADDRESS);

                                category.name.set(name);
                                category.image.set(image);
                                category.hover.set(hover);

                            } catch (JSONException ex) {
                            }
                            return category;
                        }
                    })
                    .select(new Selector<Category, Object>() {
                        @Override
                        public Object select(Category category) {
                            application.getApplicationModel().getCategories().add(category);
                            return null;
                        }
                    })
                    .execute(data);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}