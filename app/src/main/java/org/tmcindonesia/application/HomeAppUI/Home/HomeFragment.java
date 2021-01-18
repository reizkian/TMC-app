package org.tmcindonesia.application.HomeAppUI.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.card.MaterialCardView;

import org.tmcindonesia.R;
import org.tmcindonesia.tmc_explorer1.HomeExplorer1;
import org.tmcindonesia.tmc_explorer2.HomeExplorer2;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // card EXPLORER-1
        MaterialCardView cardExplorer1;
        cardExplorer1 = root.findViewById(R.id.card_explorer1);
        cardExplorer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeExplorer1.class));
            }
        });

        // card EXPLORER-2
        MaterialCardView cardExplorer2;
        cardExplorer2 = root.findViewById(R.id.card_explorer2);
        cardExplorer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeExplorer2.class));
            }
        });

        // card SEE AND DO
        MaterialCardView cardSeeAndDo;
        cardSeeAndDo = root.findViewById(R.id.card_seeanddo);
        cardSeeAndDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });



        return root;
    }
}