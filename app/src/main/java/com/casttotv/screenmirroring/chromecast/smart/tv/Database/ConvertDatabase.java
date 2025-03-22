package com.casttotv.screenmirroring.chromecast.smart.tv.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.casttotv.screenmirroring.chromecast.smart.tv.Database.dao.ChannelDao;
import com.casttotv.screenmirroring.chromecast.smart.tv.Database.dao.ConvertSpecDao;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.ModelChannel;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.WebHistory;

@Database(entities = {WebHistory.class, ModelChannel.class}, version = 1, exportSchema = false)
public abstract class ConvertDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "convert_database";
    private static final Object LOCK = new Object();
    private static volatile ConvertDatabase sInstance;

    public static ConvertDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = (ConvertDatabase) Room.databaseBuilder(context, ConvertDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return sInstance;
    }

    public abstract ConvertSpecDao convertSpecDao();
    public abstract ChannelDao channelDao();

}
