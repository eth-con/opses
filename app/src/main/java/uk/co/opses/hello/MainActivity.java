package uk.co.opses.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences myPrefs;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
        Button runButton = findViewById(R.id.run);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent == null) {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                    startActivityForResult(intent, 1);
                    Toast.makeText(getApplicationContext(), "Please select a directory for the Output file", Toast.LENGTH_LONG).show();
                } else {
                    run(v);
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myPrefs = getPreferences(MODE_PRIVATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        if (uri != null) {
            setUri(uri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void run(View view) {
        List<Integer> fibonacciSequence = Fibonacci.getFibonacciSequence((Integer.parseInt(((EditText) findViewById(R.id.plain_text_input)).getText().toString())));

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : fibonacciSequence) {
            stringBuilder.append(integer).append("\n");
        }

        save(stringBuilder.toString());

    }

    public void save(String result) {
        //TODO - Uri contains weird characters primary%3 which seem to be messing up file location
        // Apart from that, believe the solution works
        Uri uri = getUri();
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {

            File dir = new File(uri.getPath());

            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(dir, "Fibonappcci.txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(result.getBytes());
                fileOutputStream.close();
                Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "SD not found", Toast.LENGTH_LONG).show();
        }
    }

    private void setUri(Uri uri) {
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("fileName", uri.toString()).apply();
    }

    private Uri getUri() {
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        return Uri.parse(mPrefs.getString("fileName", ""));
    }
}