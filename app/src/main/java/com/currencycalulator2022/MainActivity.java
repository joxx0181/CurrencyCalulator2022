package com.currencycalulator2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Joxx0181
 * @version 2022.0429
 */
public class MainActivity extends AppCompatActivity {

    // Initializing
    EditText enterAmount;
    TextView fromCurrency;
    Spinner toCurrency;
    TextView textView;
    Button calcButton;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Object
        final DatabaseHelper saveCurrency = new DatabaseHelper(this);

        // Getting view that is identified by the android:id
        enterAmount = findViewById(R.id.amountToConvert);
        fromCurrency = findViewById(R.id.convertCurrencyFrom);
        toCurrency = findViewById(R.id.convertCurrencyTo);
        textView = findViewById(R.id.convertedValue);
        calcButton = findViewById(R.id.calcButton);
        saveButton = findViewById(R.id.saveToSQLliteButton);


        // Perform click event using lambda on calcbutton
        calcButton.setOnClickListener(view -> {

            // Getting amount entered and selected currency
            String amountVal = enterAmount.getText().toString();
            String toCurr = toCurrency.getSelectedItem().toString();

            double rate = 0;

            switch (toCurr) {
                case "DKK":
                    rate = 7.44;
                    break;
                case "USD":
                    rate = 1.05;
                    break;
                case "GBP":
                    rate = 0.84;
                    break;
                case "SEK":
                    rate = 10.36;
                    break;
                case "NOK":
                    rate = 9.89;
                    break;
                case "CAD":
                    rate = 1.36;
                    break;
                case "CHF":
                    rate = 1.03;
                    break;
                case "CUP":
                    rate = 27.16;
                    break;
                case "HKD":
                    rate = 8.28;
                    break;
                default:
                    textView.setText("ERROR");
                    break;
            }
                // Calculate value
                int val = Integer.parseInt(amountVal);
                double calcValue = val * rate;

                // Using format "%.2f" for display only 2 decimals
                textView.setText(String.format("%.2f", calcValue));
        });

        // Perform click event using lambda on savebutton
        saveButton.setOnClickListener(view -> {

            if (!textView.getText().toString().isEmpty()) {
                if (saveCurrency.insert(enterAmount.getText().toString(), textView.getText().toString())) {

                    // Using Toast to view a little message for the user
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "NOT Inserted", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
            }
        });
    }
}