package com.codragon.restuarant;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

			// Top Rated fragment activity
		System.out.println("tab pager "+ index);
			return new Veg(index);
		/*case 1:
			// Games fragment activity
			return new chinese();
			
		case 2:
			// Games fragment activity
			return new beverages();
			
		case 3:
			// Games fragment activity
			return new sides();
			
		case 4:
			// Games fragment activity
			return new extras();
			
		case 5:
			// Games fragment activity
			return new desserts();*/
}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		System.out.println("tab pager count ");
		return FoodList.nom;
	}

	
}
