package com.casttotv.screenmirroring.chromecast.smart.tv.FileVideo;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentVideoAllBinding;

import java.util.ArrayList;
import java.util.List;

public class VideoAllFragment extends Fragment {

    private ArrayList<MediaModel> videoList;
    private VideoListAdapter videoListAdapter;

    private FragmentVideoAllBinding binding;

    public VideoAllFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVideoAllBinding.inflate(inflater, container, false);
        setupRecyclerView();
        loadVideos();
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        binding.videoListRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        videoList = new ArrayList<>();
        videoListAdapter = new VideoListAdapter(requireActivity(), videoList);
        binding.videoListRv.setAdapter(videoListAdapter);
    }

    private void loadVideos() {
        binding.progressBar.setVisibility(View.VISIBLE);
        new FetchVideosTask().execute();
    }

    private class FetchVideosTask extends AsyncTask<Void, Void, List<MediaModel>> {

        @Override
        protected List<MediaModel> doInBackground(Void... voids) {
            ArrayList<MediaModel> fetchedVideos = new ArrayList<>();
            String[] projection = {
                    MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DATA,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.SIZE,
                    MediaStore.Video.Media.DURATION,
                    MediaStore.Video.Media.DATE_ADDED
            };
            Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

            try (Cursor cursor = requireContext().getContentResolver().query(videoUri, projection, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
                    int pathIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                    int nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                    int durationIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);

                    do {
                        String videoId = String.valueOf(cursor.getLong(idIndex));
                        String videoPath = cursor.getString(pathIndex);
                        String videoName = cursor.getString(nameIndex);
                        long videoDuration = cursor.getLong(durationIndex);

                        fetchedVideos.add(new MediaModel(
                                videoName,
                                videoPath,
                                videoPath,
                                /*Uri.withAppendedPath(videoUri, videoId).toString(),*/
                                videoDuration
                        ));
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return fetchedVideos;
        }

        @Override
        protected void onPostExecute(List<MediaModel> fetchedVideos) {
            binding.progressBar.setVisibility(View.GONE);

            if (fetchedVideos.isEmpty()) {
                binding.noItemFoundLay.setVisibility(View.VISIBLE);
            } else {
                videoList.clear();
                videoList.addAll(fetchedVideos);
                videoListAdapter.notifyDataSetChanged();
                binding.noItemFoundLay.setVisibility(View.GONE);
            }
        }
    }
}