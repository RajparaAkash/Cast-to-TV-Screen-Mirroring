package com.casttotv.screenmirroring.chromecast.smart.tv.Database.dao;

import androidx.annotation.Keep;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.casttotv.screenmirroring.chromecast.smart.tv.Model.WebHistory;

import java.util.List;

@Keep
@Dao
public interface ConvertSpecDao {

    @Delete
    void delete(WebHistory webHistory);

    @Query("DELETE FROM web_history")
    public void deleteAll();

    @Query("DELETE FROM web_history WHERE url = :url")
    void delete(String url);

    @Query("SELECT * FROM web_history WHERE url = :url")
    WebHistory findByUrl(String url);

    @Query("SELECT * FROM web_history ORDER BY timeStamp DESC")
    LiveData<List<WebHistory>> getAllHistory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WebHistory webHistory);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(WebHistory webHistory);

}
