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
import org.tmcindonesia.content.tmc_Explorer1.HomeExplorer1;
import org.tmcindonesia.content.tmc_Explorer2.HomeExplorer2;
import org.tmcindonesia.content.tmc_SeeAndDo.HomeSeeAndDo1;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // card SEE AND DO-1
        MaterialCardView cardSeeAndDo;
        cardSeeAndDo = root.findViewById(R.id.card_seeanddo1);
        cardSeeAndDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getBaseContext(), "Materi \"Se and Do\" sedang dalam tahap pengembangan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), HomeSeeAndDo1.class));
            }
        });

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

        return root;
    }
}