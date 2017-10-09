package cs246.sara.prove05;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "cs246.sara.prove05.MESSAGE";
    EditText editBook;
    EditText editChapter;
    EditText editVerse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editBook = (EditText) findViewById(R.id.editBook);
        editChapter = (EditText) findViewById(R.id.editChapter);
        editVerse = (EditText) findViewById(R.id.editVerse);
    }

    /** Called when the user taps the Send button */
    public void showScripture(View view) {
        Intent intent = new Intent(this, DisplayScriptureActivity.class);
        String book = editBook.getText().toString();
        String chapter = editChapter.getText().toString();
        String verse = editVerse.getText().toString();
        String scripture = String.format("%s %s:%s", book, chapter, verse);
        intent.putExtra(EXTRA_MESSAGE, scripture);
        Log.d("MainActivity", "Creating intent with scripture " + scripture);
        editBook.setText("");
        editChapter.setText("");
        editVerse.setText("");
        startActivity(intent);
    }

    public void loadScripture(View view) {
        SharedPreferences prefs = getSharedPreferences("cs246.sara.prove05", MODE_PRIVATE);
        String scripture = prefs.getString(EXTRA_MESSAGE, "");
        Pattern p = Pattern.compile("(.*) (\\d*):(\\d*)");
        Matcher m = p.matcher(scripture);
        if(m.find()) {
            editBook.setText(m.group(1) == null ? "" : m.group(1));
            editChapter.setText(m.group(2) == null ? "" : m.group(2));
            editVerse.setText(m.group(3) == null ? "" : m.group(3));
        }
        else {
            editBook.setText("");
            editChapter.setText("");
            editVerse.setText("");
        }
    }
}
