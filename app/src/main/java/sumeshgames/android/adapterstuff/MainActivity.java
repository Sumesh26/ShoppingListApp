package sumeshgames.android.adapterstuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {
 ListView lview;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.main_content);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new ListContent())
                .commit();
        lview=(ListView)findViewById(R.id.lv);

        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        //setupToolbar();

        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.drawable.ic_launcher, "Clear All");
        drawerItem[1] = new DataModel(R.drawable.ic_launcher, "Export List");
        drawerItem[2] = new DataModel(R.drawable.ic_launcher, "Share List");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(false);
     //   getSupportActionBar().setHomeButtonEnabled(true);



        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        /*
         ArrayList<Item_obj> items=new ArrayList<Item_obj>();


         DBHandler db=new DBHandler(getApplicationContext());
       // db.addItem(new Item_obj("apple"));
       // db.addItem(new Item_obj("orange"));
        //db.addItem(new Item_obj("BANANA",TRUE));
        //db.addItem(new Item_obj("item2"));
        //items=db.GetAllItems();
       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,items);
        //lview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //lview.setAdapter(adapter);
        LoadData();
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DBHandler db=new DBHandler(getApplicationContext());

                CheckedTextView cv=(CheckedTextView)view.findViewById(R.id.checkedTextView);
                Toast.makeText(getApplicationContext(),"khkhkhkh", Toast.LENGTH_SHORT).show();
                Log.v("onclick","Click!!!");
                if(cv.isChecked())
                {
                    cv.setChecked(FALSE);
                    db.uncheck(cv.getText().toString());
                    Toast.makeText(MainActivity.this,cv.getText().toString()+"is checked", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cv.setChecked(TRUE);
                    db.check(cv.getText().toString());
                    Toast.makeText(MainActivity.this,cv.getText().toString()+"is unchecked", Toast.LENGTH_SHORT).show();
                }
                LoadData();
            }
        });*/

   /* public void check()
    {

    }
    public void LoadData()
    {
        DBHandler db=new DBHandler(getApplicationContext());
        List<Item_obj> contacts=db.GetAllItems();

        CustomAdapter dataAdapter=new CustomAdapter(this,contacts);
        lview.setAdapter(dataAdapter);
    }
}
*/}
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }*/



    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                Toast.makeText(getApplicationContext(),"click on clear all",Toast.LENGTH_SHORT).show();
                DBHandler db=new DBHandler(getApplicationContext());
                db.clearall();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new ListContent())
                        .commit();

                break;
            case 1:

                break;
            case 2:

                break;

            default:
                break;
        }

       /* if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            //mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }*/
    }
   }