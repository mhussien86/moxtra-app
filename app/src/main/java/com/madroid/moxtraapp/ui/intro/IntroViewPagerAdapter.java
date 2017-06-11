package com.madroid.moxtraapp.ui.intro;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by omarmakeen on 6/10/17.
 */

public class IntroViewPagerAdapter extends FragmentPagerAdapter {

    private Context _context;
    public static int totalPage=4;
    public IntroViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        _context=context;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch(position){
            case 0:
                f=IntroFragmentOne.newInstance(_context);
                break;
            case 1:
                f=IntroFragmentTwo.newInstance(_context);
                break;
            case 2:
                f=IntroFragmentThree.newInstance(_context);
                break;
            case 3:
                f=IntroFragmentFour.newInstance(_context);
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return totalPage;
    }

//    @Override
//    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//        super.setPrimaryItem(container, position, object);
//        if (position != mCurrentPosition) {
//            Fragment fragment = (Fragment) object;
//            CustomPager pager = (CustomPager) container;
//            if (fragment != null && fragment.getView() != null) {
//                mCurrentPosition = position;
//                pager.measureCurrentView(fragment.getView());
//            }
//        }
//    }


}
