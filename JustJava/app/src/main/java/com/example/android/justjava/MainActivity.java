/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private boolean hasWhippedCream = false;
    private boolean hasChocolate = false;
    private static final int COFFEE_PRICE = 5;
    private static final int CHOCOLATE_PRICE = 2;
    private static final int WHIPPED_CREAM_PRICE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        displayQuantity(++quantity);
    }

    public void decrement(View view) {
        displayQuantity(quantity > 0 ? --quantity : 0);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        createOrderSummary(calculatePrice());
    }

    public void toggleWhippedCream(View view) {
        hasWhippedCream = ((CheckBox)view).isChecked();
    }

    public void toogleChocolate(View view) {
        hasChocolate = ((CheckBox)view).isChecked();
    }

    private int calculatePrice() {
        return (COFFEE_PRICE +
               (hasWhippedCream ? WHIPPED_CREAM_PRICE : 0) +
               (hasChocolate ? CHOCOLATE_PRICE : 0)) * quantity;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.format("%d", number));
    }

    /**
     * This method displays the given price on the screen.
     */
    private void createOrderSummary(int number) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        EditText editTextName = findViewById(R.id.name_edit_text);
        String name = editTextName.getText().toString();
        orderSummaryTextView.setText(
            String.format("%s %s\n%s\n%s %s\n%s %s\n%s",
            getString(R.string.name_label),
            name,
            String.format("%s %s\n%s %s",
                           getString(R.string.whipped_cream_label), hasWhippedCream ? getString(R.string.yes) : getString(R.string.no),
                           getString(R.string.chocolate_label), hasChocolate ? getString(R.string.yes) : getString(R.string.no)),
            getString(R.string.quantity_label),
            String.valueOf(quantity),
            getString(R.string.total_label),
            NumberFormat.getCurrencyInstance().format(number),
            getString(R.string.thank_you)));
        sendEmail();
    }

    private void sendEmail() {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"oromar.melo@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_CC, new String[] {""});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_title));
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummaryTextView.getText());
        startActivity(emailIntent);
    }
}
