package com.example.quanlichitieu.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdapter extends FragmentStateAdapter{


    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MainFragment();
            case 1:
                return new TransactionFragment();
            case 2:
                return new AddFragment();
            case 3:
                return new BudgetFragment();
            case 4:
                return new ProfileFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
