package com.example.onthicuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;

public class BussinessActivity extends AppCompatActivity {

    private EditText tenCongViec;
    private String mucDo = "Quan trọng";


    private Button btnSave;
    private Button btnLinkDanhSachCongViec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussiness);

        DatabaseHandler db = new DatabaseHandler(this);

        tenCongViec = findViewById(R.id.txtTenCongViec);

        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputTenCongViec = tenCongViec.getText().toString().trim();
//                String inputMucDo = mucDo.getText().toString().trim();
                String inputMucDo = mucDo;


                db.addCongViec(new CongViec(inputTenCongViec, inputMucDo));

                Toast.makeText(BussinessActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(BussinessActivity.this, StatisticalActivity.class));
            }
        });


        btnLinkDanhSachCongViec = findViewById(R.id.btnLinkDanhSachCongViec);
        btnLinkDanhSachCongViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BussinessActivity.this, StatisticalActivity.class));
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_quantrong:
                if (checked)
                    mucDo = "Quan trọng";
                    break;
            case R.id.radio_khongquantrong:
                if (checked)
                    mucDo = "Không quan trọng";
                    break;
        }
    }
}