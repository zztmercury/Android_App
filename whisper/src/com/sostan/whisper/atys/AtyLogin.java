package com.sostan.whisper.atys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.sostan.whisper.Config;
import com.sostan.whisper.R;
import com.sostan.whisper.net.Login;
import com.sostan.whisper.tools.MD5Tool;

/**
 * Created by 35517_000 on 2015/2/27.
 */
public class AtyLogin extends Activity {
    private EditText etPhone = null;
    private EditText etPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);

        etPhone = (EditText) findViewById(R.id.etPhone);
        etPassword = (EditText) findViewById(R.id.etPassword);

        findViewById(R.id.btnRegister).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AtyLogin.this, AtyRegister.class));
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPhone.getText())) {
                    Toast.makeText(AtyLogin.this, getString(R.string.enter_phone_num), Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText())) {
                    Toast.makeText(AtyLogin.this, getString(R.string.enter_password), Toast.LENGTH_LONG).show();
                    return;
                }

                final ProgressDialog pd = ProgressDialog.show(AtyLogin.this, getString(R.string.connecting), getString(R.string.connecting_to_server));

                new Login(MD5Tool.md5(etPhone.getText().toString()), MD5Tool.md5(etPassword.getText().toString()),
                        new Login.SuccessCallback() {
                            @Override
                            public void onSuccess(String token) {
                                pd.dismiss();
                                Intent i = new Intent(AtyLogin.this, AtyTimeLine.class);
                                i.putExtra(Config.KEY_TOKEN,token);
                                startActivity(i);
                            }
                        },
                        new Login.FailCallback() {
                            @Override
                            public void onFail() {
                                pd.dismiss();
                                Toast.makeText(AtyLogin.this, getString(R.string.wrong_phone_num_or_password), Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });
    }
}
