package com.pumpit.app.data.remote.interceptor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pumpit.app.data.remote.util.NetworkUnavailableException;

import java.io.IOException;
import java.util.Optional;

import okhttp3.Interceptor;
import okhttp3.Response;

public class InternetConnectionInterceptor implements Interceptor {

    private Context context;

    public InternetConnectionInterceptor(final Context context) {
        this.context = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isNetworkAvailable()) {
            throw new NetworkUnavailableException("Check the Internet connection!");
        }

        return chain.proceed(chain.request());
    }

    private boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return Optional.ofNullable(connectivityManager.getActiveNetworkInfo())
                .map(NetworkInfo::isConnected)
                .orElse(Boolean.FALSE);
    }
}
