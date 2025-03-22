package com.casttotv.screenmirroring.chromecast.smart.tv.FileVideo;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentVideoAlbumsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoAlbumsFragment extends Fragment {

    private VideoAlbumAdapter folderAdapter;
    public ArrayList<VideoAlbums> folders;

    private FragmentVideoAlbumsBinding binding;

    public VideoAlbumsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = FragmentVideoAlbumsBinding.inflate(layoutInflater, viewGroup, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.videoAlbumsRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        folders = new ArrayList<>();
        folderAdapter = new VideoAlbumAdapter(getActivity(), folders);
        binding.videoAlbumsRv.setAdapter(folderAdapter);
        binding.progressBar.setVisibility(View.VISIBLE);

        new FetchVideoFoldersTask().execute();
    }

    private class FetchVideoFoldersTask extends AsyncTask<Void, Void, List<VideoAlbums>> {

        @Override
        protected List<VideoAlbums> doInBackground(Void... voids) {
            List<VideoAlbums> videoAlbums = new ArrayList<>();
            try {
                // Query all video files and their folder info in one go
                Cursor query = getContext().getContentResolver().query(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        new String[]{"_id", "bucket_display_name", "_data"},
                        null, null, "bucket_display_name ASC"
                );

                if (query != null && query.moveToFirst()) {
                    Map<String, Integer> folderCountMap = new HashMap<>();
                    Map<String, String> folderPathMap = new HashMap<>();

                    // Group videos by folder
                    do {
                        String folderName = query.getString(query.getColumnIndexOrThrow("bucket_display_name"));
                        String filePath = query.getString(query.getColumnIndexOrThrow("_data"));

                        // Count videos per folder
                        folderCountMap.put(folderName, folderCountMap.getOrDefault(folderName, 0) + 1);

                        // Store the folder path
                        if (!folderPathMap.containsKey(folderName)) {
                            folderPathMap.put(folderName, filePath);
                        }
                    } while (query.moveToNext());

                    // Create VideoFolder objects
                    for (Map.Entry<String, Integer> entry : folderCountMap.entrySet()) {
                        String folderName = entry.getKey();
                        String folderPath = folderPathMap.get(folderName);
                        videoAlbums.add(new VideoAlbums(folderName, folderPath, entry.getValue()));
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e) {
                Log.e("VideoFolderQuery", "Error querying video folders", e);
            }
            return videoAlbums;
        }

        @Override
        protected void onPostExecute(List<VideoAlbums> videoAlbums) {
            super.onPostExecute(videoAlbums);
            if (videoAlbums.isEmpty()) {
                binding.noItemFoundLay.setVisibility(View.VISIBLE);
            } else {
                folderAdapter.setFolders(videoAlbums);
                folderAdapter.notifyDataSetChanged();
            }
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}