package com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.connectsdk.device.DefaultConnectableDeviceStore;

import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static long updateDevice(Context context, Device device) {
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("host", device.getHost());
        contentValues.put("is_tv", device.getIsTv());
        contentValues.put("is_stick", device.getIsStick());
        if (device.getCustomUserDeviceName() != null) {
            contentValues.put("custom_user_device_name", device.getCustomUserDeviceName());
        }
        long update = writableDatabase.update(DefaultConnectableDeviceStore.KEY_DEVICES, contentValues, "serial_number = ?", new String[]{device.getSerialNumber()});
        writableDatabase.close();
        deviceDatabase.close();
        return update;
    }

    public static Device getDevice(Context context, String str) {
        if (str == null) {
            return null;
        }
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        Cursor query = writableDatabase.query(DefaultConnectableDeviceStore.KEY_DEVICES, null, "serial_number = ?", new String[]{str}, null, null, null);
        Device parseDevice = query.moveToNext() ? parseDevice(query) : null;
        query.close();
        writableDatabase.close();
        deviceDatabase.close();
        return parseDevice;
    }

    public static List<Device> getAllDevices(Context context) {
        ArrayList arrayList = new ArrayList<>();
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        Cursor query = writableDatabase.query(DefaultConnectableDeviceStore.KEY_DEVICES, null, null, null, null, null, null);
        while (query.moveToNext()) {
            arrayList.add(parseDevice(query));
        }
        query.close();
        writableDatabase.close();
        deviceDatabase.close();
        return arrayList;
    }

    private static Device parseDevice(Cursor cursor) {
        Device device = new Device();
        device.setHost(cursor.getString(cursor.getColumnIndexOrThrow("host")));
        device.setUdn(cursor.getString(cursor.getColumnIndexOrThrow("udn")));
        device.setSerialNumber(cursor.getString(cursor.getColumnIndexOrThrow("serial_number")));
        device.setDeviceId(cursor.getString(cursor.getColumnIndexOrThrow("device_id")));
        device.setVendorName(cursor.getString(cursor.getColumnIndexOrThrow("vendor_name")));
        device.setModelNumber(cursor.getString(cursor.getColumnIndexOrThrow("model_number")));
        device.setModelName(cursor.getString(cursor.getColumnIndexOrThrow("model_name")));
        device.setWifiMac(cursor.getString(cursor.getColumnIndexOrThrow("wifi_mac")));
        device.setEthernetMac(cursor.getString(cursor.getColumnIndexOrThrow("ethernet_mac")));
        device.setNetworkType(cursor.getString(cursor.getColumnIndexOrThrow("network_type")));
        device.setUserDeviceName(cursor.getString(cursor.getColumnIndexOrThrow("user_device_name")));
        device.setSoftwareVersion(cursor.getString(cursor.getColumnIndexOrThrow("software_version")));
        device.setSoftwareBuild(cursor.getString(cursor.getColumnIndexOrThrow("software_build")));
        device.setSecureDevice(cursor.getString(cursor.getColumnIndexOrThrow("secure_device")));
        device.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow("language")));
        device.setCountry(cursor.getString(cursor.getColumnIndexOrThrow("country")));
        device.setLocale(cursor.getString(cursor.getColumnIndexOrThrow("locale")));
        device.setTimeZone(cursor.getString(cursor.getColumnIndexOrThrow("time_zone")));
        device.setTimeZoneOffset(cursor.getString(cursor.getColumnIndexOrThrow("time_zone_offset")));
        device.setPowerMode(cursor.getString(cursor.getColumnIndexOrThrow("power_mode")));
        device.setSupportsSuspend(cursor.getString(cursor.getColumnIndexOrThrow("supports_suspend")));
        device.setSupportsFindRemote(cursor.getString(cursor.getColumnIndexOrThrow("supports_find_remote")));
        device.setSupportsAudioGuide(cursor.getString(cursor.getColumnIndexOrThrow("supports_audio_guide")));
        device.setDeveloperEnabled(cursor.getString(cursor.getColumnIndexOrThrow("developer_enabled")));
        device.setKeyedDeveloperId(cursor.getString(cursor.getColumnIndexOrThrow("keyed_developer_id")));
        device.setSearchEnabled(cursor.getString(cursor.getColumnIndexOrThrow("search_enabled")));
        device.setVoiceSearchEnabled(cursor.getString(cursor.getColumnIndexOrThrow("voice_search_enabled")));
        device.setNotificationsEnabled(cursor.getString(cursor.getColumnIndexOrThrow("notifications_enabled")));
        device.setNotificationsFirstUse(cursor.getString(cursor.getColumnIndexOrThrow("notifications_first_use")));
        device.setSupportsPrivateListening(cursor.getString(cursor.getColumnIndexOrThrow("supports_private_listening")));
        device.setHeadphonesConnected(cursor.getString(cursor.getColumnIndexOrThrow("headphones_connected")));
        device.setIsTv(cursor.getString(cursor.getColumnIndexOrThrow("is_tv")));
        device.setIsStick(cursor.getString(cursor.getColumnIndexOrThrow("is_stick")));
        device.setCustomUserDeviceName(cursor.getString(cursor.getColumnIndexOrThrow("custom_user_device_name")));
        return device;
    }
}
