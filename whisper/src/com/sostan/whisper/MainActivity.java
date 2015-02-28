package com.sostan.whisper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sostan.whisper.atys.AtyLogin;
import com.sostan.whisper.atys.AtyTimeLine;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = Config.getCachedToken(this);
        if(token!=null) {
            Intent i = new Intent(this, AtyTimeLine.class);
            i.putExtra(Config.KEY_TOKEN, token);
            startActivity(i);
        }
        else {
            startActivity(new Intent(this, AtyLogin.class));
        }
        finish();
    }
}
