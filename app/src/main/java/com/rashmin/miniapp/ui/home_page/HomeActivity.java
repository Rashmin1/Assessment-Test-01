package com.rashmin.miniapp.ui.home_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rashmin.miniapp.R;
import com.rashmin.miniapp.ui.product_list_action_bar.Action_Bar_ProductActivity;

public class HomeActivity extends AppCompatActivity {

    private Button guest_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        guest_btn = findViewById(R.id.guest_button);
        guest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, Action_Bar_ProductActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
