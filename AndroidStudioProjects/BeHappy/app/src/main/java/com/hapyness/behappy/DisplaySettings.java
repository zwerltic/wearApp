package com.hapyness.behappy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class DisplaySettings extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "com.example.ojeda.timer.MESSAGE";
    public final static String to1 = "com.example.ojeda.timer.MESSAGE";
    public final static String interval1 = "com.example.ojeda.timer.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_settings);
    }

    public void startAlarm(View view) {
        Intent intent = new Intent(this, UpdateService.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String from = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, from);

        EditText editText1 = (EditText) findViewById(R.id.editText2);
        String to = editText1.getText().toString();
        intent.putExtra(to1, to);

        EditText editText2 = (EditText) findViewById(R.id.editText5);
        String interval = editText2.getText().toString();
        intent.putExtra(interval1, interval);

        startService(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
