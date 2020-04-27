package com.example.handthishomathon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.handthishomathon.R;

public class CheckOutActivity extends AppCompatActivity {

    private TextView placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        Toolbar toolbar = findViewById(R.id.check_out_toolbar);
        toolbar.setTitle("Checkout");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        TextView orderDetails = findViewById(R.id.order_details);
        orderDetails.setText("Toasted twister"+"\n 2 Pieces crispy chicken pieces \n and barbecue sauce");

        placeOrder = findViewById(R.id.place_order_tv);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo send order subtotal, delivery fee and Total Amount
                CardDetailsBottomSheet pay = new CardDetailsBottomSheet();
                pay.show(getSupportFragmentManager(), "payBottomSheet");
            }
        });
    }
}
