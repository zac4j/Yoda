package com.zac4j.yoda.ui.home.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.AppExecutors;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.EmotionEntry;
import com.zac4j.yoda.ui.base.BaseFragment;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Messenger Page
 * Created by zac on 4/28/17.
 */

public class MessengerFragment extends BaseFragment {

    public static MessengerFragment newInstance() {
        Bundle args = new Bundle();
        MessengerFragment fragment = new MessengerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchUserMessages();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void fetchUserMessages() {
        AppExecutors.getInstance().networkIO().execute(() -> {
            String token = AccessTokenKeeper.readAccessToken(getActivity()).getToken();
            String url = "https://c.api.weibo.com/2/direct_messages.json?access_token=" + token;
            Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = Objects.requireNonNull(response.body()).string();
                        System.out.println("json >>>> " + json);
                    }
                }
            });
        });
    }

}
