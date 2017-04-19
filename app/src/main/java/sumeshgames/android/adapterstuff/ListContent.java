package sumeshgames.android.adapterstuff;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import sumeshgames.android.adapterstuff.FragmentForLists.FragmentBought;
import sumeshgames.android.adapterstuff.FragmentForLists.FragmentMasterList;
import sumeshgames.android.adapterstuff.FragmentForLists.FragmentToBuy;

import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by Sumesh on 26-10-2016.
 */

public class ListContent extends Fragment implements ViewPager.OnPageChangeListener,TabHost.OnTabChangeListener {
    ViewPager viewPager;
    TabHost tabHost;
    View v;
    public ListContent()
    {
        //just like that....constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.list_content, container, false);

        initViewPager();

        initTabHost();
        return v;
    }

    private void initTabHost()
    {
        tabHost=(TabHost)v.findViewById(R.id.tabHost);
        tabHost.setup();

        String[] tabNames={"MasterList","List to BUY","Recently Bought"};

        for(int i=0;i<tabNames.length;i++)
        {
            TabHost.TabSpec tabSpec;
            tabSpec =tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(v.getContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedItem=tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);

        HorizontalScrollView hScrollView=(HorizontalScrollView)v.findViewById(R.id.h_scroll_view);
        View tabView=tabHost.getCurrentTabView();
        int scrollPos=tabView.getLeft()-(hScrollView.getWidth()
                -tabView.getWidth())/2;

        hScrollView.smoothScrollTo(scrollPos,0);
    }

    @Override
    public void onPageScrollStateChanged(int i) {


    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

   @Override
    public void onPageSelected(int selectedItem) {
     tabHost.setCurrentTab(selectedItem);
    }

    public class FakeContent implements TabHost.TabContentFactory
    {
        Context context;
        public  FakeContent(Context mcontext)
        {
            context=mcontext;
        }

        @Override
        public View createTabContent(String tag)
        {
            View fakeView=new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;

        }
    }


    private void initViewPager()
    {
        viewPager=(ViewPager)v.findViewById(R.id.view_pager);


        List<Fragment> listFragments=new ArrayList<Fragment>();
        listFragments.add(new FragmentMasterList());
        listFragments.add(new FragmentToBuy());
        listFragments.add(new FragmentBought());


        MyFragmentPagerAdapter myFragmentPagerAdapter=new MyFragmentPagerAdapter(

                getChildFragmentManager(),listFragments);

        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }


}
