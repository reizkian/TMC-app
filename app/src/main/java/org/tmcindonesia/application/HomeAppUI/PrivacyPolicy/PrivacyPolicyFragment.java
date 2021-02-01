package org.tmcindonesia.application.HomeAppUI.PrivacyPolicy;

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

public class PrivacyPolicyFragment extends Fragment {

    private PrivacyPolicyViewModel privacyPolicyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        privacyPolicyViewModel =
                new ViewModelProvider(this).get(PrivacyPolicyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_policy, container, false);

        // WebView Privacy Policy
        WebView webViewPrivacyPolicy = root.findViewById(R.id.webview_privacy_policy);
        webViewPrivacyPolicy.setWebViewClient(new WebViewClient());
        // webViewPrivacyPolicy.loadUrl("https://www.privacypolicies.com/live/3a96b097-2db7-4d6e-be14-7f2395073578");
        webViewPrivacyPolicy.loadUrl("https://www.tmcindonesia.org/index.php/privacy");
        return root;
    }
}