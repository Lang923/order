package com.herlangga.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.herlangga.order.R;

public class OrderActivity extends AppCompatActivity {

    private EditText editTextCatatan1;
    private EditText editTextCatatan2;
    private EditText editTextCatatan3;
    private TextView result1,result2,result3;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);

        editTextCatatan1 = findViewById(R.id.editTextCatatan1);
        editTextCatatan2 = findViewById(R.id.editTextCatatan2);
        editTextCatatan3 = findViewById(R.id.editTextCatatan3);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);

        Intent intent = getIntent();
        int quantity1 = intent.getIntExtra("quantity1", 0);
        int quantity2 = intent.getIntExtra("quantity2", 0);
        int quantity3 = intent.getIntExtra("quantity3", 0);

        int total1 = intent.getIntExtra("total1", 0);
        int total2 = intent.getIntExtra("total2", 0);
        int total3 = intent.getIntExtra("total3", 0);

        result1.setText("Total Harga : " + total1);
        result2.setText("Total Harga : " + total2);
        result3.setText("Total Harga : " + total3);

        // Implement SAVE button functionality here
        Button finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {
        String catatan1 = editTextCatatan1.getText().toString();
        String catatan2 = editTextCatatan2.getText().toString();
        String catatan3 = editTextCatatan3.getText().toString();

        if (isCatatanValid(catatan1) && isCatatanValid(catatan2) && isCatatanValid(catatan3)) {
            // Implement saving catatan functionality here
            // For example, you can save the catatan to SharedPreferences or send it to a server
            // ...

            // Show success message or navigate to another screen
            showAlertDialog("Catatan berhasil disimpan!");
        } else {
            showAlertDialog("Catatan harus memiliki panjang antara 4 dan 32 karakter.");
        }
    }

    private boolean isCatatanValid(String catatan) {
        return catatan.length() >= 4 && catatan.length() <= 32;
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing or handle accordingly
                    }
                });
        builder.create().show();
    }
}
