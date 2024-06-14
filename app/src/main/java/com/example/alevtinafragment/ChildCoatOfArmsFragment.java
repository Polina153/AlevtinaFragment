package com.example.alevtinafragment;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ChildCoatOfArmsFragment extends Fragment {
    static final String ARG_INDEX_CHILD = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        view.findViewById(R.id.coat_of_arms_child_button_back).setOnClickListener(view1 -> {
            //getParentFragmentManager().popBackStack();
            requireActivity().getSupportFragmentManager().popBackStack();
        });
        if (arguments != null) {
            City city = arguments.getParcelable(ARG_INDEX_CHILD);
            TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
            if(city != null){
                ((ImageView) view.findViewById(R.id.coat_of_arms_image_view)).setImageResource(images.getResourceId(city.getImageIndex(), 0));
                images.recycle();
            } else{
                ((ImageView) view.findViewById(R.id.coat_of_arms_image_view)).setImageResource(images.getResourceId(0, 0));
                images.recycle();
            }
        }
    }

    public static ChildCoatOfArmsFragment newInstance(City city) {
        ChildCoatOfArmsFragment fragment = new ChildCoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX_CHILD, city);
        fragment.setArguments(args);
        return fragment;
    }

   }