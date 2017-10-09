package cs246.sara.prove05;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayScriptureActivity extends AppCompatActivity {
    String scripture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scripture);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        scripture = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.d("ScriptureActivity", "Received intent with " + scripture);
        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.displayScripture);
        textView.setText(scripture);
    }

    public void saveScripture(View view) {
        SharedPreferences.Editor editor = getSharedPreferences("cs246.sara.prove05", MODE_PRIVATE).edit();
        editor.putString(MainActivity.EXTRA_MESSAGE, scripture);
        editor.apply();

        Toast.makeText(this, "Scripture saved successfully!", Toast.LENGTH_SHORT).show();
    }
}