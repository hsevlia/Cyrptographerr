package com.example.cyrptographer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdaptor extends FragmentStateAdapter {



    public FragmentAdaptor(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0 :
            {
                return new TextFragment();
            }
            case 1:
            {
                return new Imagecrypt();
            }


        }
        return new TextFragment();



    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
