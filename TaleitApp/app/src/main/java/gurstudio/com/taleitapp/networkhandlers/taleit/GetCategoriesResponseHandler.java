package gurstudio.com.taleitapp.networkhandlers.taleit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.taleit.Category;
import lambdas.Selector;
import queries.stracture.NestedQuery;

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
            NestedQuery.create(JSONObject.class)
                    .select(new Selector<JSONObject, Category>() {
                        @Override
                        public Category select(JSONObject jsonObject) {
                            Category category = new Category();

                            try {
                                String name = jsonObject.getString("name");
                                String image = jsonObject.getString("image");
                                String hover = jsonObject.getString("hover");

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