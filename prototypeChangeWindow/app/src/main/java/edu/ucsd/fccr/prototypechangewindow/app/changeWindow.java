package edu.ucsd.fccr.prototypechangewindow.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by CTogashi on 4/27/14.
 */
public class changeWindow extends Activity {

    TextView variableNameTextView;
    TextView variableMaxTextView;
    TextView variableMinTextView;
    EditText variableValueEditText;
    Button okayButton;
    Button cancelButton;

    private int index;
    private String name;
    private double value;
    private double newValue;
    private double min;
    private double max;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_window);

        Intent fromMain = getIntent();
        index = fromMain.getIntExtra("index", 0);
        name = fromMain.getStringExtra("name");
        value = fromMain.getDoubleExtra("value", 0);
        min = fromMain.getDoubleExtra("min", 0);
        max = fromMain.getDoubleExtra("max", 5);
        newValue = value;

        variableNameTextView = (TextView) findViewById(R.id.variableNameTextView);
        variableMaxTextView = (TextView) findViewById(R.id.variableMaxTextView);
        variableMinTextView = (TextView) findViewById(R.id.variableMinTextView);
        variableValueEditText = (EditText) findViewById(R.id.variableValueEditText);
        okayButton = (Button) findViewById(R.id.okayButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        variableNameTextView.setText(name);
        variableMaxTextView.setText("Max Value : " + Double.toString(max));
        variableMinTextView.setText("Min Value : " + Double.toString(min));
        variableValueEditText.setText(Double.toString(newValue));

        variableValueEditText.addTextChangedListener(variableValueEditTextListener);
        okayButton.setOnClickListener(okayButtonOnClickListener);
        cancelButton.setOnClickListener(cancelButtonOnClickListener);

    }

    private TextWatcher variableValueEditTextListener = new TextWatcher(){

        @Override
        public void afterTextChanged(Editable arg0) {

            try {

                if (Double.parseDouble(arg0.toString()) > max) {

                    Toast maxError = Toast.makeText(getApplicationContext(), "Too High", Toast.LENGTH_SHORT);
                    maxError.show();

                } else if (Double.parseDouble(arg0.toString()) < min) {

                    Toast minError = Toast.makeText(getApplicationContext(), "Too Low", Toast.LENGTH_SHORT);
                    minError.show();

                } else {

                    newValue = Double.parseDouble(arg0.toString());

                }
            } catch (NumberFormatException e) {

                Toast notANumber = Toast.makeText(getApplicationContext(), "Not a Number", Toast.LENGTH_SHORT);
                notANumber.show();

            }

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {

            try {

                if (Double.parseDouble(arg0.toString()) > max) {

                    Toast maxError = Toast.makeText(getApplicationContext(), "Too High", Toast.LENGTH_SHORT);
                    maxError.show();

                } else if (Double.parseDouble(arg0.toString()) < min) {

                    Toast minError = Toast.makeText(getApplicationContext(), "Too Low", Toast.LENGTH_SHORT);
                    minError.show();

                } else {

                    newValue = Double.parseDouble(arg0.toString());

                }
            } catch (NumberFormatException e) {

                Toast notANumber = Toast.makeText(getApplicationContext(), "Not a Number", Toast.LENGTH_SHORT);
                notANumber.show();

            }

        }

    };

    private OnClickListener okayButtonOnClickListener = new OnClickListener(){

        @Override
        public void onClick(View arg0) {

            Intent returnIntent = new Intent();
            returnIntent.putExtra("index", index);
            returnIntent.putExtra("value", newValue);
            setResult(RESULT_OK, returnIntent);
            finish();
        }

    };

    private OnClickListener cancelButtonOnClickListener = new OnClickListener(){

        @Override
        public void onClick(View v) {
            finish();

        }

    };

}
