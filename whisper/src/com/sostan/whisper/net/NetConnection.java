package com.sostan.whisper.net;

import android.os.AsyncTask;
import com.sostan.whisper.Config;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 35517_000 on 2015/2/27.
 */
public class NetConnection {
    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallback, final FailCallback failCallback, final String... kvs) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                StringBuffer paramStr = new StringBuffer();
                for (int i = 0; i < kvs.length; i += 2) {
                    paramStr.append(kvs[i]).append("=").append(kvs[i + 1]).append("&");
                }
                try {
                    URLConnection uc;

                    switch (method) {
                        case POST:
                            uc = new URL(url).openConnection();
                            uc.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramStr.toString());
                            break;
                        default:
                            uc = new URL(url + "?" + paramStr.toString()).openConnection();
                            break;
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while ((line=br.readLine())!=null) {
                        result.append(line);
                    }
                    return result.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    if (successCallback != null) {
                        successCallback.onSuccess(result);
                    }
                }
                else {
                    if (failCallback != null) {
                        failCallback.onFail();
                    }
                }
            }
        };
    }

    public static interface SuccessCallback {
        void onSuccess(String result);
    }

    public static interface FailCallback {
        void onFail();
    }
}
