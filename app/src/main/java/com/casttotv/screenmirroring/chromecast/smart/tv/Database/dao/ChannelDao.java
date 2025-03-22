package com.casttotv.screenmirroring.chromecast.smart.tv.Database.dao;

import androidx.annotation.Keep;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.casttotv.screenmirroring.chromecast.smart.tv.Model.ModelChannel;

import java.util.List;

@Keep
@Dao
public interface ChannelDao {

    @Delete
    void delete(ModelChannel modelChannel);

    @Query("DELETE FROM channel")
    public void deleteAll();

    @Query("DELETE FROM channel WHERE url = :url")
    void delete(String url);

    @Query("SELECT * FROM channel WHERE id = :id")
    ModelChannel findById(int id);

    @Query("SELECT * FROM channel WHERE id = :id")
    int isChannelExist(int id);

    @Query("SELECT * FROM channel ORDER BY id ASC")
    List<ModelChannel> getAllChannel();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ModelChannel modelChannel);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ModelChannel modelChannel);

}
