package com.herlangga.order;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView quantityTextView1;
    private TextView quantityTextView2;
    private TextView quantityTextView3;
    private TextView price1,price2,price3;
    Button orderButton;

    private int quantity1 = 0;
    private int quantity2 = 0;
    private int quantity3 = 0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        price3 = findViewById(R.id.price3);

        quantityTextView1 = findViewById(R.id.quantity_textView1);
        quantityTextView2 = findViewById(R.id.quantity_textView2);
        quantityTextView3 = findViewById(R.id.quantity_textView3);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        Button addButton1 = findViewById(R.id.add_button1);
        Button addButton2 = findViewById(R.id.add_button2);
        Button addButton3 = findViewById(R.id.add_button3);

        Button subtractButton1 = findViewById(R.id.subs_button1);
        Button subtractButton2 = findViewById(R.id.subs_button2);
        Button subtractButton3 = findViewById(R.id.subs_button3);

        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseQuantity(quantityTextView1);
            }
        });

        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseQuantity(quantityTextView2);
            }
        });

        addButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseQuantity(quantityTextView3);
            }
        });

        subtractButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseQuantity(quantityTextView1);
            }
        });

        subtractButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseQuantity(quantityTextView2);
            }
        });

        subtractButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseQuantity(quantityTextView3);
            }
        });

        // Implement ORDER button functionality here
        Button orderButton = findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity1 = Integer.parseInt(((TextView) findViewById(R.id.quantity_textView1)).getText().toString());
                int quantity2 = Integer.parseInt(((TextView) findViewById(R.id.quantity_textView2)).getText().toString());
                int quantity3 = Integer.parseInt(((TextView) findViewById(R.id.quantity_textView3)).getText().toString());

                int price1 = Integer.parseInt(((TextView) findViewById(R.id.price1)).getText().toString());
                int price2 = Integer.parseInt(((TextView) findViewById(R.id.price2)).getText().toString());
                int price3 = Integer.parseInt(((TextView) findViewById(R.id.price3)).getText().toString());

                int total1 = quantity1 * price1;
                int total2 = quantity2 * price2;
                int total3 = quantity3 * price3;

                Intent intent = new Intent(MainActivity.this, OrderActivity.class);

                intent.putExtra("quantity1", quantity1);
                intent.putExtra("quantity2", quantity2);
                intent.putExtra("quantity3", quantity3);

                intent.putExtra("total1", total1);
                intent.putExtra("total2", total2);
                intent.putExtra("total3", total3);

                saveData();
                startActivity(intent);
            }
        });
        loadData();
    }

    public void increaseQuantity(TextView quantityTextView) {
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        if (currentQuantity < 10) {
            currentQuantity++;
            quantityTextView.setText(String.valueOf(currentQuantity));
        } else {
            showAlertDialog("Jumlah pesanan maksimal 10.");
        }
    }

    public void decreaseQuantity(TextView quantityTextView) {
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        if (currentQuantity > 0) {
            currentQuantity--;
            quantityTextView.setText(String.valueOf(currentQuantity));
        } else {
            showAlertDialog("Jumlah pesanan tidak boleh kurang dari 0.");
        }
    }
    public void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("quantity1", Integer.parseInt(quantityTextView1.getText().toString()));
        editor.putInt("quantity2", Integer.parseInt(quantityTextView2.getText().toString()));
        editor.putInt("quantity3", Integer.parseInt(quantityTextView3.getText().toString()));
        editor.apply();
    }


    public void loadData() {
        quantityTextView1.setText(String.valueOf(sharedPreferences.getInt("quantity1", 0)));
        quantityTextView2.setText(String.valueOf(sharedPreferences.getInt("quantity2", 0)));
        quantityTextView3.setText(String.valueOf(sharedPreferences.getInt("quantity3", 0)));
    }
    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing
                    }
                });
        builder.create().show();
    }

    public void launchDetailFried(View view) {
        Intent intent = new Intent(this, DishActivity.class);
        startActivity(intent);
    }
}