package com.sostan.whisper.net;

import com.sostan.whisper.Config;

/**
 * Created by 35517_000 on 2015/2/28.
 */
public class Login {
    public Login(String phone_md5, String password, NetConnection.SuccessCallback successCallback, NetConnection.FailCallback failCallback) {
        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {

            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {

            }
        },)
    }

    public static interface SuccessCallback {
        void onSuccess(String token);
    }

    public static interface FailCallback {
        void onFail();
    }
}
