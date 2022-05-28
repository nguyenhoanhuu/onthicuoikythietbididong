package com.example.onthicuoiky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class StatisticalActivity extends AppCompatActivity {

    private ListView lvDanhSachCongViec;
    private List<CongViec> danhSachCongViec;
    private CongViecAdapter congViecAdapter;
    private Button btnSaveToCloud;
    private Button btnSynchronize;
    private FirebaseFirestore dbFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical);

        DatabaseHandler db = new DatabaseHandler(this);

        danhSachCongViec = db.getAllDanhsachCongViec();

        lvDanhSachCongViec = findViewById(R.id.lvDanhSachCongViec);

        btnSaveToCloud = findViewById(R.id.btnSaveToCloud);
        btnSynchronize = findViewById(R.id.btnSynchronize);

        dbFirebase = FirebaseFirestore.getInstance();

        btnSaveToCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CongViec congViec: danhSachCongViec ) {
                    dbFirebase.collection("danhSachCongViec")
                            .add(congViec)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(StatisticalActivity.this, "không dồng bộ được", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });


        btnSynchronize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbFirebase.collection("danhSachCongViec")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        CongViec congViec = new CongViec();
                                        congViec.set_id(Integer.parseInt(Objects.requireNonNull(document.getData().get("_id")).toString()));
                                        congViec.setTenCongViec(Objects.requireNonNull(document.getData().get("tenCongViec")).toString());
                                        congViec.setMucDo(Objects.requireNonNull(document.getData().get("mucDo")).toString());
                                        congViec.setNgay( new Date());

                                        db.addCongViec(congViec);

//
                                        danhSachCongViec.add(congViec);
                                    }

                                    congViecAdapter = new CongViecAdapter(StatisticalActivity.this, R.layout.congviec_view, danhSachCongViec);
                                    lvDanhSachCongViec.setAdapter(congViecAdapter);
                                }
                            }
                        });
            }
        });






//        danhSachCongViec = new ArrayList<>();

//        danhSachCongViec.add(new CongViec(1, "Lau nha", "hoan thanh", new Date()));
//        danhSachCongViec.add(new CongViec(2, "quet nha", "hoan thanh", new Date()));


        congViecAdapter = new CongViecAdapter(StatisticalActivity.this, R.layout.congviec_view, danhSachCongViec);
        lvDanhSachCongViec.setAdapter(congViecAdapter);




    }
}