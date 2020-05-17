package com.simplertutorials.android.wheathograophy.ui;


import android.content.Context;

import androidx.fragment.app.Fragment;

public interface MainActivityMVP {
    interface View{
        Context getContext();
        void changeFragment(int containerId, Fragment fragment);
    }
    interface Presenter{
        void attachFragment(Fragment fragment);
    }
}
