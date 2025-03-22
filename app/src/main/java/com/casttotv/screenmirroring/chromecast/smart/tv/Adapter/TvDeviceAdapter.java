package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.service.FireTVService;
import com.connectsdk.service.RokuService;
import com.connectsdk.service.WebOSTVService;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.TVObject;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TvDeviceAdapter extends RecyclerView.Adapter<TvDeviceAdapter.ViewHolder> {

    private final ArrayList<TVObject> connectableDevices = new ArrayList<>();
    private final DeviceItemClick deviceItemClick;

    public interface DeviceItemClick {
        void onItemClick(ConnectableDevice connectableDevice);
    }

    public TvDeviceAdapter(DeviceItemClick deviceItemClick) {
        this.deviceItemClick = deviceItemClick;
    }

    public void setData(List<TVObject> list) {
        connectableDevices.clear();
        if (list != null) {
            connectableDevices.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_tv_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TVObject tvObject = connectableDevices.get(position);
        if (tvObject == null) return;

        // Set TV name
        holder.tvNameTxt.setText(tvObject.getTvModelName());

        // Set connected service names
        ArrayList<ConnectableDevice> arrType = tvObject.getArrType();
        if (arrType != null && !arrType.isEmpty()) {
            String serviceNames = arrType.stream()
                    .map(ConnectableDevice::getConnectedServiceNames)
                    .filter(Objects::nonNull)
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("");
            holder.tvTypeTxt.setText(serviceNames);
        } else {
            holder.tvTypeTxt.setText("");
        }

        // Handle item click
        holder.itemView.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (deviceItemClick != null && arrType != null && !arrType.isEmpty()) {
                    ConnectableDevice selectedDevice = null;
                    for (ConnectableDevice device : arrType) {
                        String serviceName = device.getConnectedServiceNames();
                        if (serviceName != null &&
                                (serviceName.equalsIgnoreCase(WebOSTVService.ID)
                                        || serviceName.equalsIgnoreCase(FireTVService.ID)
                                        || serviceName.equalsIgnoreCase(RokuService.ID))) {
                            selectedDevice = device;
                            break;
                        }
                    }

                    // If a prioritized device is not found, fallback to the first one
                    deviceItemClick.onItemClick(selectedDevice != null ? selectedDevice : arrType.get(0));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (connectableDevices == null) return 0;
        return connectableDevices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNameTxt;
        private final TextView tvTypeTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameTxt = itemView.findViewById(R.id.tvNameTxt);
            tvTypeTxt = itemView.findViewById(R.id.tvTypeTxt);
        }
    }
}