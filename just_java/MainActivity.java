package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //String confirmationMessage = "Your order of " + counter + " coffees for a total price of $" + price + ".00 has been placed." ;
        String confirmationMessage = "ORDER SUMMARY\n\nName: Snuffles\nAdd whipped cream? " + boolcheck + "\nQuantity: " + counter + "\nTotal: $" + price;
        displayMessage(confirmationMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    int counter = 0;
    int price = 1;
    private void display(int number) {
       // int counter = 0;
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + counter);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void plusbutton(View view) {
        counter+=1;
        display(counter);
        price=counter * 5;
        displayPrice(price);
        morethan1(counter);
    }
    public void minusbutton(View view) {
        counter-=1;
        display(counter);
        price= counter *5;
        displayPrice(price);
        morethan1(counter);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView confirmationTextView = (TextView) findViewById(R.id.confirmation);
        confirmationTextView.setText(message);
    }

    boolean boolcheck;
    public void checkboxclicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if (checked){
            boolcheck = true;
            price+=1;
            displayPrice(price);
        }else{
            price-=1;
            boolcheck = false;
        }
    }
    public void morethan1(int counter){
        CheckBox checkbox = (CheckBox) findViewById(R.id.checkboxid);
        if(counter>=1){
            checkbox.setVisibility(View.VISIBLE);
        }else{
            checkbox.setVisibility(View.INVISIBLE);
        }
    }

}