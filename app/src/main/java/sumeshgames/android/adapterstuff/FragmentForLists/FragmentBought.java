package sumeshgames.android.adapterstuff.FragmentForLists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import sumeshgames.android.adapterstuff.DBHandler;
import sumeshgames.android.adapterstuff.R;

/**
 * Created by Sumesh on 26-10-2016.
 */

public class FragmentBought extends Fragment {
    public ListView lview;
    List<String> items;
    ArrayAdapter<String> arrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated)
    {
        View v=inflater.inflate(R.layout.list_view_frame,container,false);
        lview=(ListView)v.findViewById(R.id.listview);
        LoadBought();
        return v;
    }
  public void LoadBought()
  {
      DBHandler db=new DBHandler(getContext());

      items=db.GetBought();
      arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,items);

      lview.setAdapter(arrayAdapter);

  }

    public void UpdateBought(List<String> data)
    {

        arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,data);
        lview.setAdapter(arrayAdapter);
    }
}
