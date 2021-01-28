package mm.etopup.com.adapters;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import mm.etopup.com.R;
import mm.etopup.com.fragment.user.HistoryFragment;
import mm.etopup.com.fragment.admin.NewUserFragment;
import mm.etopup.com.fragment.user.TopupFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.topup, R.string.history,R.string.account};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return TopupFragment.newInstance();
        }else if(position ==1)
        {
            return NewUserFragment.newInstance();
        }
        else {
            return HistoryFragment.newInstance( );
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}