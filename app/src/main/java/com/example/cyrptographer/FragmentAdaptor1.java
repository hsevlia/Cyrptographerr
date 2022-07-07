package com.example.cyrptographer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdaptor1 extends FragmentStateAdapter {
    public FragmentAdaptor1(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0 :
            {
                return new textFragmentdecrypt();
            }
            case 1:
            {
                return new BlankFragment2();
            }


        }
        return new textFragmentdecrypt();


    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
