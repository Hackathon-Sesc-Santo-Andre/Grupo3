package com.example.webview_example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.androidquery.AQuery;

public class AgendaActivity extends FragmentActivity {
	MyPageAdapter pageAdapter;
	AQuery a;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_activity);
        
        //a = new AQuery(this);
        //a.id(R.id.agendaLayoutView).animate(R.anim.up);  
        
        List<Fragment> fragments = getFragments();
        
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        

        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
        pager.setCurrentItem(1);
        pager.setOffscreenPageLimit(2);
    }
    
	protected void onPause() {
		super.onPause();
		finish();
		overridePendingTransition (R.anim.up, R.anim.down);
		
	}

    
    private List<Fragment> getFragments(){
    	List<Fragment> fList = new ArrayList<Fragment>();
    	Date today = new Date();

    	fList.add(MyFragment.newInstance(-1));
    	fList.add(MyFragment.newInstance(0));
    	fList.add(MyFragment.newInstance(1));
    	
    	return fList;
    }

   
    private class MyPageAdapter extends FragmentPagerAdapter {
    	private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }
     
        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
