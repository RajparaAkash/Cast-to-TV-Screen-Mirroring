package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.database.DeviceDatabase;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model.Device;

import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static int removeDevice(Context context, String str) {
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        int delete = writableDatabase.delete(DeviceDatabase.DEVICES_TABLE_NAME, "serial_number = ?", new String[]{str});
        writableDatabase.close();
        deviceDatabase.close();
        return delete;
    }

    private static boolean deviceExists(Context context, String str) {
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        Cursor query = writableDatabase.query(DeviceDatabase.DEVICES_TABLE_NAME, null, "serial_number = ?", new String[]{str}, null, null, null);
        boolean moveToNext = query.moveToNext();
        query.close();
        writableDatabase.close();
        deviceDatabase.close();
        return moveToNext;
    }

    public static long insertDevice(Context context, Device device) {
        if (deviceExists(context, device.getSerialNumber())) {
            return -1;
        }
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DeviceDatabase.HOST, device.getHost());
        contentValues.put(DeviceDatabase.UDN, device.getUdn());
        contentValues.put(DeviceDatabase.SERIAL_NUMBER, device.getSerialNumber());
        contentValues.put(DeviceDatabase.DEVICE_ID, device.getDeviceId());
        contentValues.put(DeviceDatabase.VENDOR_NAME, device.getVendorName());
        contentValues.put(DeviceDatabase.MODEL_NUMBER, device.getModelNumber());
        contentValues.put(DeviceDatabase.MODEL_NAME, device.getModelName());
        contentValues.put(DeviceDatabase.WIFI_MAC, device.getWifiMac());
        contentValues.put(DeviceDatabase.ETHERNET_MAC, device.getEthernetMac());
        contentValues.put(DeviceDatabase.NETWORK_TYPE, device.getNetworkType());
        contentValues.put(DeviceDatabase.USER_DEVICE_NAME, device.getUserDeviceName());
        contentValues.put(DeviceDatabase.SOFTWARE_VERSION, device.getSoftwareVersion());
        contentValues.put(DeviceDatabase.SOFTWARE_BUILD, device.getSoftwareBuild());
        contentValues.put(DeviceDatabase.SECURE_DEVICE, device.getSecureDevice());
        contentValues.put(DeviceDatabase.LANGUAGE, device.getLanguage());
        contentValues.put(DeviceDatabase.COUNTY, device.getCountry());
        contentValues.put(DeviceDatabase.LOCALE, device.getLocale());
        contentValues.put(DeviceDatabase.TIME_ZONE, device.getTimeZone());
        contentValues.put(DeviceDatabase.TIME_ZONE_OFFSET, device.getTimeZoneOffset());
        contentValues.put(DeviceDatabase.POWER_MODE, device.getPowerMode());
        contentValues.put(DeviceDatabase.SUPPORTS_SUSPEND, device.getSupportsSuspend());
        contentValues.put(DeviceDatabase.SUPPORTS_FIND_REMOTE, device.getSupportsFindRemote());
        contentValues.put(DeviceDatabase.SUPPORTS_AUDIO_GUIDE, device.getSupportsAudioGuide());
        contentValues.put(DeviceDatabase.DEVELOPER_ENABLED, device.getDeveloperEnabled());
        contentValues.put(DeviceDatabase.KEYED_DEVELOPER_ID, device.getKeyedDeveloperId());
        contentValues.put(DeviceDatabase.SEARCH_ENABLED, device.getSearchEnabled());
        contentValues.put(DeviceDatabase.VOICE_SEARCH_ENABLED, device.getVoiceSearchEnabled());
        contentValues.put(DeviceDatabase.NOTIFICATIONS_ENABLED, device.getNotificationsEnabled());
        contentValues.put(DeviceDatabase.NOTIFICATIONS_FIRST_USE, device.getNotificationsFirstUse());
        contentValues.put(DeviceDatabase.SUPPORTS_PRIVATE_LISTENING, device.getSupportsPrivateListening());
        contentValues.put(DeviceDatabase.HEADPHONES_CONNECTED, device.getHeadphonesConnected());
        contentValues.put(DeviceDatabase.IS_TV, device.getIsTv());
        contentValues.put(DeviceDatabase.IS_STICK, device.getIsStick());
        contentValues.put(DeviceDatabase.CUSTOM_USER_DEVICE_NAME, device.getCustomUserDeviceName());
        long insert = writableDatabase.insert(DeviceDatabase.DEVICES_TABLE_NAME, null, contentValues);
        writableDatabase.close();
        deviceDatabase.close();
        return insert;
    }

    public static long updateDevice(Context context, Device device) {
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DeviceDatabase.HOST, device.getHost());
        contentValues.put(DeviceDatabase.IS_TV, device.getIsTv());
        contentValues.put(DeviceDatabase.IS_STICK, device.getIsStick());
        if (device.getCustomUserDeviceName() != null) {
            contentValues.put(DeviceDatabase.CUSTOM_USER_DEVICE_NAME, device.getCustomUserDeviceName());
        }
        long update = (long) writableDatabase.update(DeviceDatabase.DEVICES_TABLE_NAME, contentValues, "serial_number = ?", new String[]{device.getSerialNumber()});
        writableDatabase.close();
        deviceDatabase.close();
        return update;
    }

    public static Device getDevice(Context context, String str) {
        Device device = null;
        if (str == null) {
            return null;
        }
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        Cursor query = writableDatabase.query(DeviceDatabase.DEVICES_TABLE_NAME, null, "serial_number = ?", new String[]{str}, null, null, null);
        if (query.moveToNext()) {
            device = parseDevice(query);
        }
        query.close();
        writableDatabase.close();
        deviceDatabase.close();
        return device;
    }

    public static List<Device> getAllDevices(Context context) {
        ArrayList arrayList = new ArrayList();
        DeviceDatabase deviceDatabase = new DeviceDatabase(context);
        SQLiteDatabase writableDatabase = deviceDatabase.getWritableDatabase();
        Cursor query = writableDatabase.query(DeviceDatabase.DEVICES_TABLE_NAME, null, null, null, null, null, null);
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
        device.setHost(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.HOST)));
        device.setUdn(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.UDN)));
        device.setSerialNumber(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SERIAL_NUMBER)));
        device.setDeviceId(cursor.getString(cursor.getColumnIndexOrThrow("device_id")));
        device.setVendorName(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.VENDOR_NAME)));
        device.setModelNumber(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.MODEL_NUMBER)));
        device.setModelName(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.MODEL_NAME)));
        device.setWifiMac(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.WIFI_MAC)));
        device.setEthernetMac(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.ETHERNET_MAC)));
        device.setNetworkType(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.NETWORK_TYPE)));
        device.setUserDeviceName(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.USER_DEVICE_NAME)));
        device.setSoftwareVersion(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SOFTWARE_VERSION)));
        device.setSoftwareBuild(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SOFTWARE_BUILD)));
        device.setSecureDevice(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SECURE_DEVICE)));
        device.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.LANGUAGE)));
        device.setCountry(cursor.getString(cursor.getColumnIndexOrThrow("country")));
        device.setLocale(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.LOCALE)));
        device.setTimeZone(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.TIME_ZONE)));
        device.setTimeZoneOffset(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.TIME_ZONE_OFFSET)));
        device.setPowerMode(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.POWER_MODE)));
        device.setSupportsSuspend(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SUPPORTS_SUSPEND)));
        device.setSupportsFindRemote(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SUPPORTS_FIND_REMOTE)));
        device.setSupportsAudioGuide(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SUPPORTS_AUDIO_GUIDE)));
        device.setDeveloperEnabled(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.DEVELOPER_ENABLED)));
        device.setKeyedDeveloperId(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.KEYED_DEVELOPER_ID)));
        device.setSearchEnabled(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SEARCH_ENABLED)));
        device.setVoiceSearchEnabled(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.VOICE_SEARCH_ENABLED)));
        device.setNotificationsEnabled(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.NOTIFICATIONS_ENABLED)));
        device.setNotificationsFirstUse(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.NOTIFICATIONS_FIRST_USE)));
        device.setSupportsPrivateListening(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.SUPPORTS_PRIVATE_LISTENING)));
        device.setHeadphonesConnected(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.HEADPHONES_CONNECTED)));
        device.setIsTv(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.IS_TV)));
        device.setIsStick(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.IS_STICK)));
        device.setCustomUserDeviceName(cursor.getString(cursor.getColumnIndexOrThrow(DeviceDatabase.CUSTOM_USER_DEVICE_NAME)));
        return device;
    }

}
