package com.madroid.moxtraapp.ui.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import com.madroid.moxtraapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragmentFour extends Fragment {


    public IntroFragmentFour() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Context context) {
        IntroFragmentFour f = new IntroFragmentFour();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.intro_fragment_4, null);
        return root;
    }

}
