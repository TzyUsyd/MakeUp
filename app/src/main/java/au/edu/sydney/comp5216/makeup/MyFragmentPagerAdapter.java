package au.edu.sydney.comp5216.makeup;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    private final List<Fragment> fListFragment;


    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> frgListFragment) {
        super(fm);
        fListFragment = frgListFragment;

    }

    @Override
    public Fragment getItem(int position) {
        return fListFragment.get(position);
    }

    @Override
    public int getCount() {
        return fListFragment.size();
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
