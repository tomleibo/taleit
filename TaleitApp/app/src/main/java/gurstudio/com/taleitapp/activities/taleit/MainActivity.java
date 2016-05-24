package gurstudio.com.taleitapp.activities.taleit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.adapters.taleit.CategoriesAdapter;
import gurstudio.com.taleitapp.model.core.ObservableCollection;
import gurstudio.com.taleitapp.model.core.Observer;
import gurstudio.com.taleitapp.model.taleit.Category;

public class MainActivity extends TaleItActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView categoriesRecycler;
    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        getResumeBinder().bind(getBaseApplication().getApplicationModel().getCategories(), new Observer<ObservableCollection.ObservableItemAction<Category>>() {
            @Override
            public void onUpdate(ObservableCollection.ObservableItemAction<Category> value) {
                categoriesRecycler.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void findViews() {
        categoriesRecycler = (RecyclerView)findViewById(R.id.categories_recycler);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void initViews() {
        initCategoriesRecyclerView();
        initFAB();
    }

    private void initFAB() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!categoriesRecycler.isComputingLayout()) {
                    categoriesRecycler.getAdapter().notifyDataSetChanged();
                }
            }
        });
    }

    private void initCategoriesRecyclerView() {
        // use a linear layout manager
        categoriesRecycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        // specify an adapter (see also next example)
        CategoriesAdapter adapter = new CategoriesAdapter(getBaseApplication().getApplicationModel().getCategories());
        categoriesRecycler.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }
}