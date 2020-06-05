package com.pumpit.app.data.remote.response;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenericCallback<T> implements Callback<T> {

    private MutableLiveData<BasicResponse<T>> result;

    public GenericCallback(final MutableLiveData<BasicResponse<T>> result) {
        this.result = result;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        BasicResponse<T> result = new BasicResponse<>();
        if (response.isSuccessful()) {
            result.setSuccessful(Boolean.TRUE);
            result.setResponse(response.body());
        } else {
            result.setSuccessful(Boolean.FALSE);
            result.setMessage(response.message());
        }

        this.result.setValue(result);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        System.out.println("FAIL: " + t.getMessage());
        t.printStackTrace();
        BasicResponse<T> result = new BasicResponse<>();
        result.setSuccessful(Boolean.FALSE);
        result.setMessage(t.getMessage());
        this.result.setValue(result);
    }
}
