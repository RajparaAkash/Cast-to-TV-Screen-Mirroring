package com.casttotv.screenmirroring.chromecast.smart.tv.Util;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;

import com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio.AudioAlbumModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio.AudioModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.FileImage.ImageAlbums;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Utils {

    public static void LinkOpen(Context context, String link) {
        if (link == null || link.isEmpty()) {
            Toast.makeText(context, "Invalid link", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
            customTabsIntent.launchUrl(context, Uri.parse(link));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to open link", Toast.LENGTH_SHORT).show();
        }
    }

    public static void fullScreenDarkStatusBar(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    public static void fullScreenLightStatusBar(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static ArrayList<MediaModel> getAllPhotos(Activity activity) {
        ArrayList<MediaModel> photoList = new ArrayList<>();

        // Define the projection (columns to fetch)
        String[] projection = {
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID
        };

        // Sort by DATE_ADDED to ensure the last added images are shown first
        String sortOrder = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        // Query the MediaStore for external images
        try (Cursor cursor = activity.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
        )) {
            if (cursor != null) {
                int titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
                int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                // Iterate through the cursor
                while (cursor.moveToNext()) {
                    String title = cursor.getString(titleColumn);
                    String data = cursor.getString(dataColumn);

                    // Add the MediaModel object to the list
                    photoList.add(new MediaModel(title, data, data));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return photoList;
    }

    public static ArrayList<ImageAlbums> getPhotoAlbums(Activity activity) {
        Map<String, ImageAlbums> albumMap = new HashMap<>();

        // Define the projection (columns to fetch)
        String[] projection = {
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID
        };

        // Sort by DATE_ADDED to ensure the last added images are shown first
        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";

        try (Cursor cursor = activity.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
        )) {
            if (cursor != null && cursor.getCount() > 0) {
                int bucketColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);

                // Iterate through the cursor
                while (cursor.moveToNext()) {
                    String albumName = cursor.getString(bucketColumn);
                    String photoUri = cursor.getString(dataColumn);
                    int photoId = cursor.getInt(idColumn);

                    MediaModel mediaModel = new MediaModel();
                    mediaModel.setAlbumName(albumName);
                    mediaModel.setPhotoUri(photoUri);
                    mediaModel.setId(photoId);

                    if (albumMap.containsKey(albumName)) {
                        Objects.requireNonNull(albumMap.get(albumName)).getAlbumPhotos().add(mediaModel);
                    } else {
                        ImageAlbums newAlbum = new ImageAlbums();
                        newAlbum.setId(photoId);
                        newAlbum.setName(albumName);
                        newAlbum.setCoverUri(photoUri);
                        newAlbum.getAlbumPhotos().add(mediaModel);
                        albumMap.put(albumName, newAlbum);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert the map to a list and sort by the most recent image
        ArrayList<ImageAlbums> albumsList = new ArrayList<>(albumMap.values());
        albumsList.sort((a, b) -> b.getAlbumPhotos().get(0).getId() - a.getAlbumPhotos().get(0).getId());

        return albumsList;
    }

    public static ArrayList<AudioModel> getAllAudioFromDevice(Context context) {
        ArrayList<AudioModel> audioList = new ArrayList<>();

        // Define the projection (columns to fetch)
        String[] projection = {
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION
        };

        // Sort by DATE_ADDED to ensure the last added audio files are shown first
        String sortOrder = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        try (Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
        )) {
            if (cursor != null && cursor.getCount() > 0) {
                int pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                int titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
                int albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
                int artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
                int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);

                while (cursor.moveToNext()) {
                    AudioModel audioModel = new AudioModel();
                    audioModel.setSongPath(cursor.getString(pathColumn));
                    audioModel.setSongTitle(cursor.getString(titleColumn));
                    audioModel.setSongAlbum(cursor.getString(albumColumn));
                    audioModel.setSongArtist(cursor.getString(artistColumn));
                    audioModel.setDuration(cursor.getLong(durationColumn));
                    audioList.add(audioModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return audioList;
    }

    public static ArrayList<AudioAlbumModel> getAllArtist(Context context) {
        ArrayList<AudioAlbumModel> artistAlbumList = new ArrayList<>();
        HashMap<String, ArrayList<AudioModel>> artistMap = new HashMap<>();

        // Query audio files from MediaStore
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION
        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                AudioModel audioModel = new AudioModel(path, title, artist, duration);

                // Group songs by artist
                if (!artistMap.containsKey(artist)) {
                    artistMap.put(artist, new ArrayList<>());
                }
                Objects.requireNonNull(artistMap.get(artist)).add(audioModel);
            }
            cursor.close();
        }

        // Convert HashMap to ArrayList<AudioAlbumModel>
        for (Map.Entry<String, ArrayList<AudioModel>> entry : artistMap.entrySet()) {
            String artistName = entry.getKey();
            ArrayList<AudioModel> songs = entry.getValue();
            AudioAlbumModel albumModel = new AudioAlbumModel(artistName, songs);
            artistAlbumList.add(albumModel);
        }

        return artistAlbumList;
    }

    public static ArrayList<AudioAlbumModel> getAllAudioFolders(Context context) {
        ArrayList<AudioAlbumModel> audioAlbums = new ArrayList<>();
        HashMap<String, AudioAlbumModel> folderMap = new HashMap<>();

        // Query audio files, sorted by DATE_ADDED in descending order
        String[] projection = {
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION
        };

        // Sort by DATE_ADDED to ensure the last added audio files are shown first
        String sortOrder = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        try (Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
        )) {
            if (cursor != null && cursor.getCount() > 0) {
                int pathIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                int titleIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
                int albumIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
                int durationIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);

                while (cursor.moveToNext()) {
                    String path = cursor.getString(pathIndex);
                    String title = cursor.getString(titleIndex);
                    String album = cursor.getString(albumIndex);
                    long duration = cursor.getLong(durationIndex);

                    File file = new File(path);
                    String folderPath = file.getParent();
                    String folderName = Objects.requireNonNull(file.getParentFile()).getName();

                    AudioModel audioModel = new AudioModel();
                    audioModel.setSongPath(path);
                    audioModel.setSongTitle(title);
                    audioModel.setSongAlbum(album);
                    audioModel.setDuration(duration);

                    if (folderMap.containsKey(folderPath)) {
                        Objects.requireNonNull(folderMap.get(folderPath)).getArrSong().add(audioModel);
                    } else {
                        AudioAlbumModel audioAlbumModel = new AudioAlbumModel();
                        audioAlbumModel.setNameAlbum(folderName);
                        audioAlbumModel.setFolderPath(folderPath);
                        audioAlbumModel.setArrSong(new ArrayList<>());
                        audioAlbumModel.getArrSong().add(audioModel);
                        folderMap.put(folderPath, audioAlbumModel);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        audioAlbums.addAll(folderMap.values());
        return audioAlbums;
    }

    public static String formatDuration(long durationInMillis) {
        long totalSeconds = durationInMillis / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        if (hours > 0) {
            // Format as "HH:MM:SS" if duration is more than 60 minutes
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            // Format as "MM:SS" otherwise
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
}
