package sumeshgames.android.adapterstuff;

/**
 * Created by Sumesh on 25-10-2016.
 */

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import sumeshgames.android.adapterstuff.FragmentForLists.FragmentMasterList;
import sumeshgames.android.adapterstuff.FragmentForLists.FragmentToBuy;

import static android.R.attr.fragment;
import static android.R.attr.windowFrame;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapter extends BaseAdapter implements View.OnClickListener,View.OnLongClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private List data;
    FragmentMasterList fragment;
    private static LayoutInflater inflater=null;

    Item_obj tempValues=null;
    int i=0;

    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(Activity a, List d, FragmentMasterList frag) {

        /********** Take passed values **********/
        activity = a;
        data=d;
       fragment=frag;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public CheckedTextView tv;




    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.tabitem, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.tv = (CheckedTextView) vi.findViewById(R.id.checkedTextView);




            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.tv.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( Item_obj ) data.get( position );

            /************  Set Model values in Holder elements ***********/

           holder.tv.setText( tempValues.getName() );
           // holder.tv.setText( "FUCK!!!");

            holder.tv.setChecked( tempValues.isDone() );
            Log.v("checked"," "+tempValues.isDone());
            //holder.tv.setChecked(TRUE);
            /******** Set Item Click Listner for LayoutInflater for each row *******/

            vi.setOnClickListener(new OnItemClickListener( position ));

            vi.setOnLongClickListener(new OnItemLongClickListener(position) );
        }
        return vi;
    }

     @Override
     public boolean onLongClick(View v) {
         Log.v("CustomAdapter", "=====Row LONG clicked=====");return false;}

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View view) {

             // MainActivity aaa=(MainActivity)activity;
          // FragmentMasterList sct = (FragmentMasterList) view.getContext();

            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
            Log.v("qqq", "=====row clicked=====");
            CheckedTextView cv=(CheckedTextView)view.findViewById(R.id.checkedTextView);
            DBHandler db=new DBHandler(view.getContext());

            if(cv.isChecked())
            {
                cv.setChecked(FALSE);
                db.uncheck(cv.getText().toString());
                Toast.makeText(view.getContext(),cv.getText().toString()+"is removed from LIST TO BUY", Toast.LENGTH_SHORT).show();
            }
            else
            {
                cv.setChecked(TRUE);
                db.check(cv.getText().toString());
                Toast.makeText(view.getContext(),cv.getText().toString()+"is added to LIST TO BUY", Toast.LENGTH_SHORT).show();
            }
            //sct.onItemClick(mPosition);
          fragment.LoadData();
           // FragmentMasterList.Update(TRUE);
            //FragmentMasterList.mBoo=TRUE;
            //Log.v("val","in custom is "+FragmentMasterList.val);
        }
    }
//////////////////longclickk/////
private class OnItemLongClickListener  implements View.OnLongClickListener {
    private int mPosition;

    OnItemLongClickListener(int position){
        mPosition = position;
    }

    @Override
    public boolean onLongClick(View view) {

        MainActivity aaa=(MainActivity)activity;

       // FragmentMasterList sct = new FragmentMasterList();

        /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
        Log.v("qqq", "=====row clicked=====");
        CheckedTextView cv=(CheckedTextView)view.findViewById(R.id.checkedTextView);
        DBHandler db=new DBHandler(view.getContext());
         db.delete(cv.getText().toString());
        Toast.makeText(view.getContext(),cv.getText().toString()+" LONGGGGG CLICKK!!", Toast.LENGTH_SHORT).show();


        //sct.onItemLongClick(mPosition);
       fragment.LoadData();
        return true;
    }
}

}