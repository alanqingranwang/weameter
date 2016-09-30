package com.kingrandesigns.weameter;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


/**
 *
 * @author Alan
 *
 */
public class ShowError extends Activity {
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_error);
        error = (TextView) findViewById(R.id.error);
    }
}
