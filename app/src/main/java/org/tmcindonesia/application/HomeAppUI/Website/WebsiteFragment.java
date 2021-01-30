package org.tmcindonesia.application.HomeAppUI.Website;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.tmcindonesia.R;

public class WebsiteFragment extends Fragment {

    private WebsiteViewModel websiteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        websiteViewModel =
                new ViewModelProvider(this).get(WebsiteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_website, container, false);
        return root;
    }
}