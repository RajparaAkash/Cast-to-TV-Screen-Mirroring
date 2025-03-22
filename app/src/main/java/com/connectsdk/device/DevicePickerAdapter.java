package com.connectsdk.device;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.connectsdk.discovery.DiscoveryManager;

import java.util.HashMap;


public class DevicePickerAdapter extends ArrayAdapter<ConnectableDevice> {
    Context context;
    HashMap<String, ConnectableDevice> currentDevices;
    int resource;
    int subTextViewResourceId;
    int textViewResourceId;

    public DevicePickerAdapter(Context context) {
        this(context, 17367044);
    }

    DevicePickerAdapter(Context context, int resource) {
        this(context, resource, 16908308, 16908309);
    }

    DevicePickerAdapter(Context context, int resource, int textViewResourceId, int subTextViewResourceId) {
        super(context, resource, textViewResourceId);
        this.currentDevices = new HashMap<>();
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.subTextViewResourceId = subTextViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String modelName;
        if (convertView == null) {
            convertView = View.inflate(getContext(), this.resource, null);
        }
        ConnectableDevice item = getItem(position);
        if (item.getFriendlyName() != null) {
            modelName = item.getFriendlyName();
        } else {
            modelName = item.getModelName();
        }
        convertView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        TextView textView = (TextView) convertView.findViewById(this.textViewResourceId);
        textView.setText(modelName);
        textView.setTextColor(-1);
        boolean z = true;
        boolean z2 = (this.context.getApplicationInfo().flags & 2) != 0;
        boolean z3 = DiscoveryManager.getInstance().getCapabilityFilters().size() == 0;
        String connectedServiceNames = item.getConnectedServiceNames();
        if (!(connectedServiceNames != null && connectedServiceNames.length() > 0) || (!z2 && !z3)) {
            z = false;
        }
        TextView textView2 = (TextView) convertView.findViewById(this.subTextViewResourceId);
        if (z) {
            textView2.setText(connectedServiceNames);
            textView2.setTextColor(-1);
        } else {
            textView2.setText((CharSequence) null);
        }
        return convertView;
    }
}
