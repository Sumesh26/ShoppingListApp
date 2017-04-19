package sumeshgames.android.adapterstuff.FragmentForLists;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import sumeshgames.android.adapterstuff.DBHandler;
import sumeshgames.android.adapterstuff.R;

import static sumeshgames.android.adapterstuff.R.id.lv;

/**
 * Created by Sumesh on 26-10-2016.
 */

public class FragmentToBuy extends Fragment {
    public    ListView lview;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated)
    {
        View v=inflater.inflate(R.layout.list_view_frame,container,false);

        lview=(ListView)v.findViewById(R.id.listview);
        lview.setItemsCanFocus(false);
       LoadTo();
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos=i;
                Log.v("tobuy","clcik");
                DBHandler db=new DBHandler(view.getContext());

                String itemval=(String)lview.getItemAtPosition(pos);
                Toast.makeText(getContext(),itemval+" :bought",Toast.LENGTH_SHORT).show();
                db.bought(itemval);
                view.animate().translationX(500).withEndAction(new Runnable(){
                    public void run(){
                        UpdateToBuy();
                        // do something
                    }
                });


            }
        });

        return v;
    }
    List<String> items;ArrayAdapter<String> arrayAdapter;
    public   void LoadToBuy(Context context)
    {
        DBHandler db=new DBHandler(context);
        //List<String> items=db.GetToBuy();
        items=db.GetToBuy();
        //Toast.makeText(getContext(),"update", Toast.LENGTH_SHORT).show();
        //Log.v("update","yes");
        //CustomAdapter dataAdapter=new CustomAdapter(getActivity(),Items,FragmentMasterList.this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,items);
        lview.setAdapter(arrayAdapter);


    }
    public   void LoadTo()
    {
        DBHandler db=new DBHandler(getContext());
        //List<String> items=db.GetToBuy();
        items=db.GetToBuy();
        //Toast.makeText(getContext(),"update", Toast.LENGTH_SHORT).show();
        //Log.v("update","yes");
        //CustomAdapter dataAdapter=new CustomAdapter(getActivity(),Items,FragmentMasterList.this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,items);
        lview.setAdapter(arrayAdapter);
    }
    public void UpdateToBuy()
    {
        DBHandler db=new DBHandler((getContext()));
        List<String> data=db.GetBought();
        items=db.GetToBuy();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,items);
        lview.setAdapter(arrayAdapter);


        String tag="android:switcher:" + R.id.view_pager+ ":" + 2;

        FragmentManager manager=getFragmentManager();
        FragmentBought f=(FragmentBought)manager.findFragmentByTag(tag);
        f.UpdateBought(data);

    }

    public void setToBuy(List<String> arg)
    {
        Log.v("frag","set to buy");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,arg);
        lview.setAdapter(arrayAdapter);


        if(lview==null)
        Log.v("buy","is null");
        else
            Log.v("buy","is not null");
    }

}
