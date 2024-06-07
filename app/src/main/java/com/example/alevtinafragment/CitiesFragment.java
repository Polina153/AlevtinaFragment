package com.example.alevtinafragment;

import static com.example.alevtinafragment.CoatOfArmsFragment.ARG_INDEX;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CitiesFragment extends Fragment {

    private static final String CURRENT_CITY = "CurrentCity";
    // Текущая позиция (выбранный город)
    //private int currentPosition = 0;
    private City city = null;

    public CitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_cities, container, false);
        View rootView = inflater.inflate(R.layout.fragment_cities, container, false);
        //TextView textView = rootView.findViewById(R.id.some_id);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Восстановление текущей позиции
        if (savedInstanceState != null) {
            //currentPosition = savedInstanceState.getInt(CURRENT_CITY, 0);
            city = savedInstanceState.getParcelable(CURRENT_CITY);
        }
        // инициализация списка
        initList(view);
        // отображения открытого ранее герба в ландшафтной ориентации
        if (isLandscape()) {
            showLandCoatOfArms(city);
        }

    }

    public static CitiesFragment newInstance() {
        CitiesFragment fragment = new CitiesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    // создаём список городов на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        for (int i = 0; i < cities.length; i++) {
            String currentCity = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(currentCity);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                city = new City(position, currentCity);
                //currentPosition = position;
                showCoatOfArms(city);
            });
        }
    }

    private void showCoatOfArms(City city) {
        if (isLandscape()) {
            showLandCoatOfArms(city);
        } else {
            showPortCoatOfArms(city);
        }

    }

    // Показываем герб в портретной ориентации
    private void showPortCoatOfArms(City city) {
        Activity activity = requireActivity();
        final Intent intent = new Intent(activity, CoatOfArmsActivity.class);
        intent.putExtra(ARG_INDEX, city);
        activity.startActivity(intent);
    }

    // Показываем герб в ландшафтной ориентации
    private void showLandCoatOfArms(City city) {
        // Создаём новый фрагмент с текущей позицией для вывода герба
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(city);
        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.add(R.id.coat_of_arms_container, detail);  // замена фрагмента
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_CITY, city);
        super.onSaveInstanceState(outState);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

}
