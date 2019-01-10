package com.example.android.bestnews;

import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details = (TextView) findViewById(R.id.tv_details);

        if(getIntent().hasExtra("data")){
            String data = getIntent().getStringExtra("data");
            details.setText(data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_share){
            String mimeType = "text/plain";
            String title = "Share this news item!";
            String dataToShare = "Check out this news item: \n\n" + details.getText().toString() + "\n\n Shared via BestNews App.";
            ShareCompat.IntentBuilder.from(this).setChooserTitle(title).setType(mimeType).setText(dataToShare).startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}
