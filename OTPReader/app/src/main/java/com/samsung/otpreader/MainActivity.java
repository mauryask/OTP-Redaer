/**
 @ Developer : Shubham Maurya
 @ Date : 09/07/2022
 @ App Operations Team, Samsung R&D Institute, Delhi, India
*/

package com.samsung.otpreader;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.skyfishjy.library.RippleBackground;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView otpTextView;
    private RippleBackground rippleBackground1, rippleBackground2, rippleBackground3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rippleBackground1 = findViewById(R.id.content1);
        rippleBackground2 = findViewById(R.id.content2);
        rippleBackground3 = findViewById(R.id.content3);
        rippleBackground1.startRippleAnimation();
        rippleBackground2.startRippleAnimation();
        rippleBackground3.startRippleAnimation();

        otpTextView = findViewById(R.id.otpId);

        SmsReceiver.bindListener(userOtp -> {
            otpTextView.setText(userOtp);
            // Save userOtp to the server
            saveOtp(userOtp);
        });
    }

   private void saveOtp(final String userOtp)
    {
        final String url = "http://107.108.189.188/otp-reader/save-otp.php";
        //error
        StringRequest sr = new StringRequest(1, url,
                response -> {
                },
                error -> {
                }){
            @Override
            public Map<String, String> getParams() {
                Map<String, String> map =  new HashMap<>();
                map.put("otpKey", userOtp);
                return map;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
        rq.add(sr);
    }
}