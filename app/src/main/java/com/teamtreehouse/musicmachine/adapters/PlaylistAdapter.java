package com.teamtreehouse.musicmachine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamtreehouse.musicmachine.R;
import com.teamtreehouse.musicmachine.models.Song;

/**
 * Created by benjakuben on 5/12/16.
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.SongViewHolder> {

    private Song[] mSongs;
    private Context mContext;
    private SongViewHolder mViewHolder;

    public PlaylistAdapter(Context context, Song[] songs) {
        mContext = context;
        mSongs = songs;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_song, parent, false);
        mViewHolder = new SongViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.bindSong(mSongs[position]);
    }

    @Override
    public int getItemCount() {
        return mSongs.length;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleLabel;
        public ImageView mFavIcon;

        public SongViewHolder(View itemView) {
            super(itemView);
            mTitleLabel = (TextView) itemView.findViewById(R.id.songTitleLabel);
            mFavIcon = (ImageView) itemView.findViewById(R.id.favIcon);
            itemView.setOnClickListener(this);
        }

        public void bindSong(Song song) {
            mTitleLabel.setText(song.getTitle());
            if (song.isFavorite()) {
                mFavIcon.setVisibility(View.VISIBLE);
            }
            else {
                mFavIcon.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View v) {

        }
    }
}
