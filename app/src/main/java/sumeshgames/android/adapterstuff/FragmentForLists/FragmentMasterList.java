package sumeshgames.android.adapterstuff.FragmentForLists;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sumeshgames.android.adapterstuff.CustomAdapter;
import sumeshgames.android.adapterstuff.DBHandler;
import sumeshgames.android.adapterstuff.Item_obj;
import sumeshgames.android.adapterstuff.MainActivity;
import sumeshgames.android.adapterstuff.R;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static sumeshgames.android.adapterstuff.R.id.listview;
import static sumeshgames.android.adapterstuff.R.id.lv;
import static sumeshgames.android.adapterstuff.R.layout.list_view_frame;
import static sumeshgames.android.adapterstuff.R.layout.tabitem;

/**
 * Created by Sumesh on 26-10-2016.
 */

public class FragmentMasterList extends Fragment {
     ListView lview;        FloatingActionButton fb;
      FragmentToBuy frag;
public static boolean val=FALSE;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated)
    {
        View v=inflater.inflate(R.layout.activity_main,container,false);
        ArrayList<Item_obj> items=new ArrayList<Item_obj>();
        lview=(ListView)v.findViewById(lv);

         fb=(FloatingActionButton)v.findViewById(R.id.floatingActionButton) ;
        DBHandler db=new DBHandler(getContext());
       // db.addItem(new Item_obj("apple"));
       // db.addItem(new Item_obj("orange"));
        //db.addItem(new Item_obj("BANANA",TRUE));
        //db.addItem(new Item_obj("item2"));
        //items=db.GetAllItems();
        // ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,items);
        //lview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //lview.setAdapter(adapter);

       Load();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {




                Toast.makeText(getContext(),"khkhkhkh", Toast.LENGTH_SHORT).show();
                Log.v("onclick","Click!!!");


            }


        });
        lview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }


        });




        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val) {
                    Log.v("val"," true val");
                    val=FALSE;
                    LoadData();
                }
               // Toast.makeText(getContext(),"FB PRESSED", Toast.LENGTH_SHORT).show();
                // get prompts.xml view
                Context context=getContext();
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    DBHandler db=new DBHandler(getContext());
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text

                                        String itm=userInput.getText().toString();
                                      // itm=itm.replaceAll("\'","''");
                                        db.addItem(new Item_obj(itm));
                                        LoadData();

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        if(val) {
            Log.v("val"," true val");
            val=FALSE;
            LoadData();
        }

        return v;
    }

    public  void LoadData()
    {
        DBHandler db=new DBHandler(getContext());
        List<Item_obj> contacts=db.GetAllItems();
        Toast.makeText(getContext(),"update", Toast.LENGTH_SHORT).show();
        Log.v("update","yes");
        CustomAdapter dataAdapter=new CustomAdapter(getActivity(),contacts,FragmentMasterList.this);
        lview.setAdapter(dataAdapter);
        db.close();
        String tag="android:switcher:" + R.id.view_pager+ ":" + 1;
       FragmentManager manager=getFragmentManager();
        FragmentToBuy f=(FragmentToBuy)manager.findFragmentByTag(tag);
          if(f==null)
          {
              Log.v("frag","fail");

          }else
              Log.v("frag","yeah");
       // List<String> items=db.GetToBuy();
        List<String> items=db.GetToBuy();
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,items);
        f.setToBuy(items);

    }

    public  void Load()
    {
        DBHandler db=new DBHandler(getContext());
        List<Item_obj> contacts=db.GetAllItems();
        Toast.makeText(getContext(),"update", Toast.LENGTH_SHORT).show();
        Log.v("update","yes");
        CustomAdapter dataAdapter=new CustomAdapter(getActivity(),contacts,FragmentMasterList.this);
        lview.setAdapter(dataAdapter);


    }





}
