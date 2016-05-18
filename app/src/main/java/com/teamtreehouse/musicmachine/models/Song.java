package com.teamtreehouse.musicmachine.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benjakuben on 5/13/16.
 */
public class Song implements Parcelable {

    private long mId;
    private String mTitle;
    private int mDuration;
    private String mArtist;
    private String mLabel;
    private int mYearReleased;
    private long mAlbumId;
    private boolean mIsFavorite;

    public Song(long id, String title, int duration, String artist, String label,
                int yearReleased, long albumId, boolean isFavorite) {
        setId(id);
        setTitle(title);
        setDuration(duration);
        setArtist(artist);
        setLabel(label);
        setYearReleased(yearReleased);
        setAlbumId(albumId);
        setIsFavorite(isFavorite);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getYearReleased() {
        return mYearReleased;
    }

    public void setYearReleased(int yearReleased) {
        mYearReleased = yearReleased;
    }

    public long getAlbumId() {
        return mAlbumId;
    }

    public void setAlbumId(long albumId) {
        mAlbumId = albumId;
    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        mIsFavorite = isFavorite;
    }

    public String toString() {
        return "ID: " + mId + "\n" +
                "Title: " + mTitle + "\n" +
                "Duration: " + mDuration + "\n" +
                "Artist: " + mArtist + "\n" +
                "Label: " + mLabel + "\n" +
                "Year Released: " + mYearReleased + "\n" +
                "Album ID: " + mAlbumId + "\n" +
                "Favorite?: " + mIsFavorite;
    }

    @Override
    public int describeContents() {
        return 0; // ignore
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mTitle);
        dest.writeInt(mDuration);
        dest.writeString(mArtist);
        dest.writeString(mLabel);
        dest.writeInt(mYearReleased);
        dest.writeLong(mAlbumId);
        dest.writeInt(mIsFavorite ? 1 : 0);
    }

    private Song(Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mDuration = in.readInt();
        mArtist = in.readString();
        mLabel = in.readString();
        mYearReleased = in.readInt();
        mAlbumId = in.readLong();
        mIsFavorite = in.readInt() != 0;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}
