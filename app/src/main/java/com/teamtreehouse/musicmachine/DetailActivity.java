package com.teamtreehouse.musicmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.teamtreehouse.musicmachine.models.Song;

public class DetailActivity extends AppCompatActivity {

    private Song mSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView titleLabel = (TextView)findViewById(R.id.songTitleLabel);
        final CheckBox favoriteCheckbox = (CheckBox)findViewById(R.id.checkBox);

        Intent intent = getIntent();
//        if (intent.getStringExtra(MainActivity.EXTRA_TITLE) != null) {
//            String songTitle = intent.getStringExtra(MainActivity.EXTRA_TITLE);
//            titleLabel.setText(songTitle);
//        }
        if (intent.getParcelableExtra(MainActivity.EXTRA_SONG) != null) {
            Song song = intent.getParcelableExtra(MainActivity.EXTRA_SONG);
            titleLabel.setText(song.getTitle());
            favoriteCheckbox.setChecked(song.isFavorite());
        }

        final int listPosition = intent.getIntExtra(MainActivity.EXTRA_LIST_POSITION, 0);

        favoriteCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.EXTRA_FAVORITE, isChecked);
                resultIntent.putExtra(MainActivity.EXTRA_LIST_POSITION, listPosition);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
