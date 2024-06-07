package com.example.alevtinafragment;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CoatOfArmsFragment extends Fragment {
    static final String ARG_INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        Button buttonBack = view.findViewById(R.id.coat_of_arms_button_back);
        buttonBack.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        view.findViewById(R.id.coat_of_arms_button_remove).setOnClickListener(view1 -> {
            final FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            final List<Fragment> fragments = fragmentManager.getFragments();
            for (Fragment fragment : fragments) {
                if (fragment instanceof CoatOfArmsFragment && fragment.isVisible())
                    fragmentManager.beginTransaction().remove(fragment).commit();
            }
        });


        // Аргументы могут быть null (как в случае с методом Activity getIntent())
        // поэтому обязательно проверяем на null
        if (arguments != null) {
            City city = (City) arguments.getParcelable(ARG_INDEX);
            //int index = arguments.getInt(ARG_INDEX);
            // найдем в root view нужный ImageView
            ImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms_image_view);
            // Получим из ресурсов массив указателей на изображения гербов
            // Обратите внимание на тип - TypedArray, и способ получения - obtainTypedArray
            TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);

            if (city != null) {
                // Возьмем нужное изображение и отобразим в ImageView
                imageCoatOfArms.setImageResource(images.getResourceId(city.getImageIndex(), 0));
            } else {
                imageCoatOfArms.setImageResource(images.getResourceId(0, 0));
            }
            // TypedArray рекомендуется закрыть после использования
            images.recycle();

            // найдем в root view нужный TextView
            TextView textView = view.findViewById(R.id.coat_of_arms_text_view);
            if (city != null) {
                textView.setText(city.getCityName());
            } else {
                textView.setText("Moscow");
            }
        }
    }

    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы
    public static CoatOfArmsFragment newInstance(City city) {
        // Создание фрагмента
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        // Передача параметра через бандл
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, city);
        fragment.setArguments(args);
        return fragment;
    }
}

