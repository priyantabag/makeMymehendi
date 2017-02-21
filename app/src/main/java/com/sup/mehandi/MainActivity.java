package com.sup.mehandi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sup.mehandi.base.BaseActivity;
import com.sup.mehandi.features.dashboard.DashboardActivity;
import com.sup.mehandi.features.registration.RegistrationActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_is_login)
    CheckBox cbIsLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setFontStyle();


    }

    @OnClick({R.id.btn_login, R.id.signup, R.id.password})
    public void onClick(View view) {
        Log.i(TAG, "onClick");
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                break;
            case R.id.signup:
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                break;
            case R.id.password:
                break;
        }
    }
}
