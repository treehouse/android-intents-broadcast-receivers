package com.teamtreehouse.musicmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teamtreehouse.musicmachine.models.Song;

public class DetailActivity extends AppCompatActivity {

    private Song mSong;
    private RelativeLayout mRootLayout;

    public static final String SHARE_SONG = "com.teamtreehouse.intent.action.SHARE_SONG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView titleLabel = (TextView)findViewById(R.id.songTitleLabel);
        final CheckBox favoriteCheckbox = (CheckBox)findViewById(R.id.checkBox);
        mRootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        Intent intent = getIntent();

        if (Intent.ACTION_SEND.equals(intent.getAction())) {
            handleSendIntent(intent);
        }
        else {
//        if (intent.getStringExtra(MainActivity.EXTRA_TITLE) != null) {
//            String songTitle = intent.getStringExtra(MainActivity.EXTRA_TITLE);
//            titleLabel.setText(songTitle);
//        }
            if (intent.getParcelableExtra(MainActivity.EXTRA_SONG) != null) {
                mSong = intent.getParcelableExtra(MainActivity.EXTRA_SONG);
                titleLabel.setText(mSong.getTitle());
                favoriteCheckbox.setChecked(mSong.isFavorite());
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
    }

    private void handleSendIntent(Intent intent) {
        if (intent.getStringExtra(Intent.EXTRA_TEXT) != null) {
            Snackbar.make(mRootLayout, intent.getStringExtra(Intent.EXTRA_TEXT),
                    Snackbar.LENGTH_LONG).show();
        }
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

        if (itemId == R.id.action_share) {
            if (mSong != null) {
                Intent customIntent = new Intent(SHARE_SONG);
                customIntent.putExtra(MainActivity.EXTRA_SONG, mSong);
                Intent chooser = Intent.createChooser(customIntent, "Share song");
                startActivity(chooser);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}











