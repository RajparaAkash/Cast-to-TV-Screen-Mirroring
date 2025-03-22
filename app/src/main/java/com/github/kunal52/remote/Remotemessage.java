package com.github.kunal52.remote;

import androidx.exifinterface.media.ExifInterface;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

public final class Remotemessage {
    public static final int KEYCODE_0_VALUE = 7;
    public static final int KEYCODE_11_VALUE = 227;
    public static final int KEYCODE_12_VALUE = 228;
    public static final int KEYCODE_1_VALUE = 8;
    public static final int KEYCODE_2_VALUE = 9;
    public static final int KEYCODE_3D_MODE_VALUE = 206;
    public static final int KEYCODE_3_VALUE = 10;
    public static final int KEYCODE_4_VALUE = 11;
    public static final int KEYCODE_5_VALUE = 12;
    public static final int KEYCODE_6_VALUE = 13;
    public static final int KEYCODE_7_VALUE = 14;
    public static final int KEYCODE_8_VALUE = 15;
    public static final int KEYCODE_9_VALUE = 16;
    public static final int KEYCODE_ALL_APPS_VALUE = 284;
    public static final int KEYCODE_ALT_LEFT_VALUE = 57;
    public static final int KEYCODE_ALT_RIGHT_VALUE = 58;
    public static final int KEYCODE_APOSTROPHE_VALUE = 75;
    public static final int KEYCODE_APP_SWITCH_VALUE = 187;
    public static final int KEYCODE_ASSIST_VALUE = 219;
    public static final int KEYCODE_AT_VALUE = 77;
    public static final int KEYCODE_AVR_INPUT_VALUE = 182;
    public static final int KEYCODE_AVR_POWER_VALUE = 181;
    public static final int KEYCODE_A_VALUE = 29;
    public static final int KEYCODE_BACKSLASH_VALUE = 73;
    public static final int KEYCODE_BACK_VALUE = 4;
    public static final int KEYCODE_BOOKMARK_VALUE = 174;
    public static final int KEYCODE_BREAK_VALUE = 121;
    public static final int KEYCODE_BRIGHTNESS_DOWN_VALUE = 220;
    public static final int KEYCODE_BRIGHTNESS_UP_VALUE = 221;
    public static final int KEYCODE_BUTTON_10_VALUE = 197;
    public static final int KEYCODE_BUTTON_11_VALUE = 198;
    public static final int KEYCODE_BUTTON_12_VALUE = 199;
    public static final int KEYCODE_BUTTON_13_VALUE = 200;
    public static final int KEYCODE_BUTTON_14_VALUE = 201;
    public static final int KEYCODE_BUTTON_15_VALUE = 202;
    public static final int KEYCODE_BUTTON_16_VALUE = 203;
    public static final int KEYCODE_BUTTON_1_VALUE = 188;
    public static final int KEYCODE_BUTTON_2_VALUE = 189;
    public static final int KEYCODE_BUTTON_3_VALUE = 190;
    public static final int KEYCODE_BUTTON_4_VALUE = 191;
    public static final int KEYCODE_BUTTON_5_VALUE = 192;
    public static final int KEYCODE_BUTTON_6_VALUE = 193;
    public static final int KEYCODE_BUTTON_7_VALUE = 194;
    public static final int KEYCODE_BUTTON_8_VALUE = 195;
    public static final int KEYCODE_BUTTON_9_VALUE = 196;
    public static final int KEYCODE_BUTTON_A_VALUE = 96;
    public static final int KEYCODE_BUTTON_B_VALUE = 97;
    public static final int KEYCODE_BUTTON_C_VALUE = 98;
    public static final int KEYCODE_BUTTON_L1_VALUE = 102;
    public static final int KEYCODE_BUTTON_L2_VALUE = 104;
    public static final int KEYCODE_BUTTON_MODE_VALUE = 110;
    public static final int KEYCODE_BUTTON_R1_VALUE = 103;
    public static final int KEYCODE_BUTTON_R2_VALUE = 105;
    public static final int KEYCODE_BUTTON_SELECT_VALUE = 109;
    public static final int KEYCODE_BUTTON_START_VALUE = 108;
    public static final int KEYCODE_BUTTON_THUMBL_VALUE = 106;
    public static final int KEYCODE_BUTTON_THUMBR_VALUE = 107;
    public static final int KEYCODE_BUTTON_X_VALUE = 99;
    public static final int KEYCODE_BUTTON_Y_VALUE = 100;
    public static final int KEYCODE_BUTTON_Z_VALUE = 101;
    public static final int KEYCODE_B_VALUE = 30;
    public static final int KEYCODE_CALCULATOR_VALUE = 210;
    public static final int KEYCODE_CALENDAR_VALUE = 208;
    public static final int KEYCODE_CALL_VALUE = 5;
    public static final int KEYCODE_CAMERA_VALUE = 27;
    public static final int KEYCODE_CAPS_LOCK_VALUE = 115;
    public static final int KEYCODE_CAPTIONS_VALUE = 175;
    public static final int KEYCODE_CHANNEL_DOWN_VALUE = 167;
    public static final int KEYCODE_CHANNEL_UP_VALUE = 166;
    public static final int KEYCODE_CLEAR_VALUE = 28;
    public static final int KEYCODE_COMMA_VALUE = 55;
    public static final int KEYCODE_CONTACTS_VALUE = 207;
    public static final int KEYCODE_COPY_VALUE = 278;
    public static final int KEYCODE_CTRL_LEFT_VALUE = 113;
    public static final int KEYCODE_CTRL_RIGHT_VALUE = 114;
    public static final int KEYCODE_CUT_VALUE = 277;
    public static final int KEYCODE_C_VALUE = 31;
    public static final int KEYCODE_DEL_VALUE = 67;
    public static final int KEYCODE_DEMO_APP_1_VALUE = 301;
    public static final int KEYCODE_DEMO_APP_2_VALUE = 302;
    public static final int KEYCODE_DEMO_APP_3_VALUE = 303;
    public static final int KEYCODE_DEMO_APP_4_VALUE = 304;
    public static final int KEYCODE_DPAD_CENTER_VALUE = 23;
    public static final int KEYCODE_DPAD_DOWN_LEFT_VALUE = 269;
    public static final int KEYCODE_DPAD_DOWN_RIGHT_VALUE = 271;
    public static final int KEYCODE_DPAD_DOWN_VALUE = 20;
    public static final int KEYCODE_DPAD_LEFT_VALUE = 21;
    public static final int KEYCODE_DPAD_RIGHT_VALUE = 22;
    public static final int KEYCODE_DPAD_UP_LEFT_VALUE = 268;
    public static final int KEYCODE_DPAD_UP_RIGHT_VALUE = 270;
    public static final int KEYCODE_DPAD_UP_VALUE = 19;
    public static final int KEYCODE_DVR_VALUE = 173;
    public static final int KEYCODE_D_VALUE = 32;
    public static final int KEYCODE_EISU_VALUE = 212;
    public static final int KEYCODE_ENDCALL_VALUE = 6;
    public static final int KEYCODE_ENTER_VALUE = 66;
    public static final int KEYCODE_ENVELOPE_VALUE = 65;
    public static final int KEYCODE_EQUALS_VALUE = 70;
    public static final int KEYCODE_ESCAPE_VALUE = 111;
    public static final int KEYCODE_EXPLORER_VALUE = 64;
    public static final int KEYCODE_E_VALUE = 33;
    public static final int KEYCODE_F10_VALUE = 140;
    public static final int KEYCODE_F11_VALUE = 141;
    public static final int KEYCODE_F12_VALUE = 142;
    public static final int KEYCODE_F1_VALUE = 131;
    public static final int KEYCODE_F2_VALUE = 132;
    public static final int KEYCODE_F3_VALUE = 133;
    public static final int KEYCODE_F4_VALUE = 134;
    public static final int KEYCODE_F5_VALUE = 135;
    public static final int KEYCODE_F6_VALUE = 136;
    public static final int KEYCODE_F7_VALUE = 137;
    public static final int KEYCODE_F8_VALUE = 138;
    public static final int KEYCODE_F9_VALUE = 139;
    public static final int KEYCODE_FEATURED_APP_1_VALUE = 297;
    public static final int KEYCODE_FEATURED_APP_2_VALUE = 298;
    public static final int KEYCODE_FEATURED_APP_3_VALUE = 299;
    public static final int KEYCODE_FEATURED_APP_4_VALUE = 300;
    public static final int KEYCODE_FOCUS_VALUE = 80;
    public static final int KEYCODE_FORWARD_DEL_VALUE = 112;
    public static final int KEYCODE_FORWARD_VALUE = 125;
    public static final int KEYCODE_FUNCTION_VALUE = 119;
    public static final int KEYCODE_F_VALUE = 34;
    public static final int KEYCODE_GRAVE_VALUE = 68;
    public static final int KEYCODE_GUIDE_VALUE = 172;
    public static final int KEYCODE_G_VALUE = 35;
    public static final int KEYCODE_HEADSETHOOK_VALUE = 79;
    public static final int KEYCODE_HELP_VALUE = 259;
    public static final int KEYCODE_HENKAN_VALUE = 214;
    public static final int KEYCODE_HOME_VALUE = 3;
    public static final int KEYCODE_H_VALUE = 36;
    public static final int KEYCODE_INFO_VALUE = 165;
    public static final int KEYCODE_INSERT_VALUE = 124;
    public static final int KEYCODE_I_VALUE = 37;
    public static final int KEYCODE_J_VALUE = 38;
    public static final int KEYCODE_KANA_VALUE = 218;
    public static final int KEYCODE_KATAKANA_HIRAGANA_VALUE = 215;
    public static final int KEYCODE_K_VALUE = 39;
    public static final int KEYCODE_LANGUAGE_SWITCH_VALUE = 204;
    public static final int KEYCODE_LAST_CHANNEL_VALUE = 229;
    public static final int KEYCODE_LEFT_BRACKET_VALUE = 71;
    public static final int KEYCODE_L_VALUE = 40;
    public static final int KEYCODE_MANNER_MODE_VALUE = 205;
    public static final int KEYCODE_MEDIA_AUDIO_TRACK_VALUE = 222;
    public static final int KEYCODE_MEDIA_CLOSE_VALUE = 128;
    public static final int KEYCODE_MEDIA_EJECT_VALUE = 129;
    public static final int KEYCODE_MEDIA_FAST_FORWARD_VALUE = 90;
    public static final int KEYCODE_MEDIA_NEXT_VALUE = 87;
    public static final int KEYCODE_MEDIA_PAUSE_VALUE = 127;
    public static final int KEYCODE_MEDIA_PLAY_PAUSE_VALUE = 85;
    public static final int KEYCODE_MEDIA_PLAY_VALUE = 126;
    public static final int KEYCODE_MEDIA_PREVIOUS_VALUE = 88;
    public static final int KEYCODE_MEDIA_RECORD_VALUE = 130;
    public static final int KEYCODE_MEDIA_REWIND_VALUE = 89;
    public static final int KEYCODE_MEDIA_SKIP_BACKWARD_VALUE = 273;
    public static final int KEYCODE_MEDIA_SKIP_FORWARD_VALUE = 272;
    public static final int KEYCODE_MEDIA_STEP_BACKWARD_VALUE = 275;
    public static final int KEYCODE_MEDIA_STEP_FORWARD_VALUE = 274;
    public static final int KEYCODE_MEDIA_STOP_VALUE = 86;
    public static final int KEYCODE_MEDIA_TOP_MENU_VALUE = 226;
    public static final int KEYCODE_MENU_VALUE = 82;
    public static final int KEYCODE_META_LEFT_VALUE = 117;
    public static final int KEYCODE_META_RIGHT_VALUE = 118;
    public static final int KEYCODE_MINUS_VALUE = 69;
    public static final int KEYCODE_MOVE_END_VALUE = 123;
    public static final int KEYCODE_MOVE_HOME_VALUE = 122;
    public static final int KEYCODE_MUHENKAN_VALUE = 213;
    public static final int KEYCODE_MUSIC_VALUE = 209;
    public static final int KEYCODE_MUTE_VALUE = 91;
    public static final int KEYCODE_M_VALUE = 41;
    public static final int KEYCODE_NAVIGATE_IN_VALUE = 262;
    public static final int KEYCODE_NAVIGATE_NEXT_VALUE = 261;
    public static final int KEYCODE_NAVIGATE_OUT_VALUE = 263;
    public static final int KEYCODE_NAVIGATE_PREVIOUS_VALUE = 260;
    public static final int KEYCODE_NOTIFICATION_VALUE = 83;
    public static final int KEYCODE_NUMPAD_0_VALUE = 144;
    public static final int KEYCODE_NUMPAD_1_VALUE = 145;
    public static final int KEYCODE_NUMPAD_2_VALUE = 146;
    public static final int KEYCODE_NUMPAD_3_VALUE = 147;
    public static final int KEYCODE_NUMPAD_4_VALUE = 148;
    public static final int KEYCODE_NUMPAD_5_VALUE = 149;
    public static final int KEYCODE_NUMPAD_6_VALUE = 150;
    public static final int KEYCODE_NUMPAD_7_VALUE = 151;
    public static final int KEYCODE_NUMPAD_8_VALUE = 152;
    public static final int KEYCODE_NUMPAD_9_VALUE = 153;
    public static final int KEYCODE_NUMPAD_ADD_VALUE = 157;
    public static final int KEYCODE_NUMPAD_COMMA_VALUE = 159;
    public static final int KEYCODE_NUMPAD_DIVIDE_VALUE = 154;
    public static final int KEYCODE_NUMPAD_DOT_VALUE = 158;
    public static final int KEYCODE_NUMPAD_ENTER_VALUE = 160;
    public static final int KEYCODE_NUMPAD_EQUALS_VALUE = 161;
    public static final int KEYCODE_NUMPAD_LEFT_PAREN_VALUE = 162;
    public static final int KEYCODE_NUMPAD_MULTIPLY_VALUE = 155;
    public static final int KEYCODE_NUMPAD_RIGHT_PAREN_VALUE = 163;
    public static final int KEYCODE_NUMPAD_SUBTRACT_VALUE = 156;
    public static final int KEYCODE_NUM_LOCK_VALUE = 143;
    public static final int KEYCODE_NUM_VALUE = 78;
    public static final int KEYCODE_N_VALUE = 42;
    public static final int KEYCODE_O_VALUE = 43;
    public static final int KEYCODE_PAGE_DOWN_VALUE = 93;
    public static final int KEYCODE_PAGE_UP_VALUE = 92;
    public static final int KEYCODE_PAIRING_VALUE = 225;
    public static final int KEYCODE_PASTE_VALUE = 279;
    public static final int KEYCODE_PERIOD_VALUE = 56;
    public static final int KEYCODE_PICTSYMBOLS_VALUE = 94;
    public static final int KEYCODE_PLUS_VALUE = 81;
    public static final int KEYCODE_POUND_VALUE = 18;
    public static final int KEYCODE_POWER_VALUE = 26;
    public static final int KEYCODE_PROFILE_SWITCH_VALUE = 288;
    public static final int KEYCODE_PROG_BLUE_VALUE = 186;
    public static final int KEYCODE_PROG_GREEN_VALUE = 184;
    public static final int KEYCODE_PROG_RED_VALUE = 183;
    public static final int KEYCODE_PROG_YELLOW_VALUE = 185;
    public static final int KEYCODE_P_VALUE = 44;
    public static final int KEYCODE_Q_VALUE = 45;
    public static final int KEYCODE_REFRESH_VALUE = 285;
    public static final int KEYCODE_RIGHT_BRACKET_VALUE = 72;
    public static final int KEYCODE_RO_VALUE = 217;
    public static final int KEYCODE_R_VALUE = 46;
    public static final int KEYCODE_SCROLL_LOCK_VALUE = 116;
    public static final int KEYCODE_SEARCH_VALUE = 84;
    public static final int KEYCODE_SEMICOLON_VALUE = 74;
    public static final int KEYCODE_SETTINGS_VALUE = 176;
    public static final int KEYCODE_SHIFT_LEFT_VALUE = 59;
    public static final int KEYCODE_SHIFT_RIGHT_VALUE = 60;
    public static final int KEYCODE_SLASH_VALUE = 76;
    public static final int KEYCODE_SLEEP_VALUE = 223;
    public static final int KEYCODE_SOFT_LEFT_VALUE = 1;
    public static final int KEYCODE_SOFT_RIGHT_VALUE = 2;
    public static final int KEYCODE_SOFT_SLEEP_VALUE = 276;
    public static final int KEYCODE_SPACE_VALUE = 62;
    public static final int KEYCODE_STAR_VALUE = 17;
    public static final int KEYCODE_STB_INPUT_VALUE = 180;
    public static final int KEYCODE_STB_POWER_VALUE = 179;
    public static final int KEYCODE_STEM_1_VALUE = 265;
    public static final int KEYCODE_STEM_2_VALUE = 266;
    public static final int KEYCODE_STEM_3_VALUE = 267;
    public static final int KEYCODE_STEM_PRIMARY_VALUE = 264;
    public static final int KEYCODE_SWITCH_CHARSET_VALUE = 95;
    public static final int KEYCODE_SYM_VALUE = 63;
    public static final int KEYCODE_SYSRQ_VALUE = 120;
    public static final int KEYCODE_SYSTEM_NAVIGATION_DOWN_VALUE = 281;
    public static final int KEYCODE_SYSTEM_NAVIGATION_LEFT_VALUE = 282;
    public static final int KEYCODE_SYSTEM_NAVIGATION_RIGHT_VALUE = 283;
    public static final int KEYCODE_SYSTEM_NAVIGATION_UP_VALUE = 280;
    public static final int KEYCODE_S_VALUE = 47;
    public static final int KEYCODE_TAB_VALUE = 61;
    public static final int KEYCODE_THUMBS_DOWN_VALUE = 287;
    public static final int KEYCODE_THUMBS_UP_VALUE = 286;
    public static final int KEYCODE_TV_ANTENNA_CABLE_VALUE = 242;
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN_VALUE = 254;
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP_VALUE = 253;
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION_VALUE = 252;
    public static final int KEYCODE_TV_CONTENTS_MENU_VALUE = 256;
    public static final int KEYCODE_TV_DATA_SERVICE_VALUE = 230;
    public static final int KEYCODE_TV_INPUT_COMPONENT_1_VALUE = 249;
    public static final int KEYCODE_TV_INPUT_COMPONENT_2_VALUE = 250;
    public static final int KEYCODE_TV_INPUT_COMPOSITE_1_VALUE = 247;
    public static final int KEYCODE_TV_INPUT_COMPOSITE_2_VALUE = 248;
    public static final int KEYCODE_TV_INPUT_HDMI_1_VALUE = 243;
    public static final int KEYCODE_TV_INPUT_HDMI_2_VALUE = 244;
    public static final int KEYCODE_TV_INPUT_HDMI_3_VALUE = 245;
    public static final int KEYCODE_TV_INPUT_HDMI_4_VALUE = 246;
    public static final int KEYCODE_TV_INPUT_VALUE = 178;
    public static final int KEYCODE_TV_INPUT_VGA_1_VALUE = 251;
    public static final int KEYCODE_TV_MEDIA_CONTEXT_MENU_VALUE = 257;
    public static final int KEYCODE_TV_NETWORK_VALUE = 241;
    public static final int KEYCODE_TV_NUMBER_ENTRY_VALUE = 234;
    public static final int KEYCODE_TV_POWER_VALUE = 177;
    public static final int KEYCODE_TV_RADIO_SERVICE_VALUE = 232;
    public static final int KEYCODE_TV_SATELLITE_BS_VALUE = 238;
    public static final int KEYCODE_TV_SATELLITE_CS_VALUE = 239;
    public static final int KEYCODE_TV_SATELLITE_SERVICE_VALUE = 240;
    public static final int KEYCODE_TV_SATELLITE_VALUE = 237;
    public static final int KEYCODE_TV_TELETEXT_VALUE = 233;
    public static final int KEYCODE_TV_TERRESTRIAL_ANALOG_VALUE = 235;
    public static final int KEYCODE_TV_TERRESTRIAL_DIGITAL_VALUE = 236;
    public static final int KEYCODE_TV_TIMER_PROGRAMMING_VALUE = 258;
    public static final int KEYCODE_TV_VALUE = 170;
    public static final int KEYCODE_TV_ZOOM_MODE_VALUE = 255;
    public static final int KEYCODE_T_VALUE = 48;
    public static final int KEYCODE_UNKNOWN_VALUE = 0;
    public static final int KEYCODE_U_VALUE = 49;
    public static final int KEYCODE_VIDEO_APP_1_VALUE = 289;
    public static final int KEYCODE_VIDEO_APP_2_VALUE = 290;
    public static final int KEYCODE_VIDEO_APP_3_VALUE = 291;
    public static final int KEYCODE_VIDEO_APP_4_VALUE = 292;
    public static final int KEYCODE_VIDEO_APP_5_VALUE = 293;
    public static final int KEYCODE_VIDEO_APP_6_VALUE = 294;
    public static final int KEYCODE_VIDEO_APP_7_VALUE = 295;
    public static final int KEYCODE_VIDEO_APP_8_VALUE = 296;
    public static final int KEYCODE_VOICE_ASSIST_VALUE = 231;
    public static final int KEYCODE_VOLUME_DOWN_VALUE = 25;
    public static final int KEYCODE_VOLUME_MUTE_VALUE = 164;
    public static final int KEYCODE_VOLUME_UP_VALUE = 24;
    public static final int KEYCODE_V_VALUE = 50;
    public static final int KEYCODE_WAKEUP_VALUE = 224;
    public static final int KEYCODE_WINDOW_VALUE = 171;
    public static final int KEYCODE_W_VALUE = 51;
    public static final int KEYCODE_X_VALUE = 52;
    public static final int KEYCODE_YEN_VALUE = 216;
    public static final int KEYCODE_Y_VALUE = 53;
    public static final int KEYCODE_ZENKAKU_HANKAKU_VALUE = 211;
    public static final int KEYCODE_ZOOM_IN_VALUE = 168;
    public static final int KEYCODE_ZOOM_OUT_VALUE = 169;
    public static final int KEYCODE_Z_VALUE = 54;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteAppInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteAppInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteConfigure_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteConfigure_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteDeviceInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteDeviceInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteEditInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteEditInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteError_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteError_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteImeBatchEdit_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteImeBatchEdit_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteImeKeyInject_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteImeKeyInject_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteImeShowRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteImeShowRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteKeyInject_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteKeyInject_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemotePingRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemotePingRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemotePingResponse_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemotePingResponse_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteSetActive_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteSetActive_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteSetVolumeLevel_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteSetVolumeLevel_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteStart_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteStart_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteTextFieldStatus_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteTextFieldStatus_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteVoiceBegin_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteVoiceBegin_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteVoiceEnd_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteVoiceEnd_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_com_kunal52_remote_RemoteVoicePayload_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_kunal52_remote_RemoteVoicePayload_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0013remotemessage.proto\u0012\u0012com.kunal52.remote\".\n\u001aRemoteAppLinkLaunchRequest\u0012\u0010\n\bapp_link\u0018\u0001 \u0001(\t\"!\n\u001fRemoteResetPreferredAudioDevice\"\u001f\n\u001dRemoteSetPreferredAudioDevice\"\u0019\n\u0017RemoteAdjustVolumeLevel\"´\u0001\n\u0014RemoteSetVolumeLevel\u0012\u0010\n\bunknown1\u0018\u0001 \u0001(\r\u0012\u0010\n\bunknown2\u0018\u0002 \u0001(\r\u0012\u0014\n\fplayer_model\u0018\u0003 \u0001(\t\u0012\u0010\n\bunknown4\u0018\u0004 \u0001(\r\u0012\u0010\n\bunknown5\u0018\u0005 \u0001(\r\u0012\u0012\n\nvolume_max\u0018\u0006 \u0001(\r\u0012\u0014\n\fvolume_level\u0018\u0007 \u0001(\r\u0012\u0014\n\fvolume_muted\u0018\b \u0001(\b\"\u001e\n\u000bRemoteStart\u0012\u000f\n\u0007started\u0018\u0001 \u0001(\b\"\u0010\n\u000eRemoteVoiceEnd\"\u0014\n\u0012RemoteVoicePayload\"\u0012\n\u0010RemoteVoiceBegin\"v\n\u0015RemoteTextFieldStatus\u0012\u0015\n\rcounter_field\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005value\u0018\u0002 \u0001(\t\u0012\r\n\u0005start\u0018\u0003 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0004 \u0001(\u0005\u0012\f\n\u0004int5\u0018\u0005 \u0001(\u0005\u0012\r\n\u0005label\u0018\u0006 \u0001(\t\"c\n\u0014RemoteImeShowRequest\u0012K\n\u0018remote_text_field_status\u0018\u0002 \u0001(\u000b2).com.kunal52.remote.RemoteTextFieldStatus\" \n\u000eRemoteEditInfo\u0012\u000e\n\u0006insert\u0018\u0002 \u0001(\u0005\"w\n\u0012RemoteImeBatchEdit\u0012\u0013\n\u000bime_counter\u0018\u0001 \u0001(\u0005\u0012\u0015\n\rfield_counter\u0018\u0002 \u0001(\u0005\u00125\n\tedit_info\u0018\u0003 \u0001(\u000b2\".com.kunal52.remote.RemoteEditInfo\"\u0001\n\rRemoteAppInfo\u0012\u000f\n\u0007counter\u0018\u0001 \u0001(\u0005\u0012\f\n\u0004int2\u0018\u0002 \u0001(\u0005\u0012\f\n\u0004int3\u0018\u0003 \u0001(\u0005\u0012\f\n\u0004int4\u0018\u0004 \u0001(\t\u0012\f\n\u0004int7\u0018\u0007 \u0001(\u0005\u0012\f\n\u0004int8\u0018\b \u0001(\u0005\u0012\r\n\u0005label\u0018\n \u0001(\t\u0012\u0013\n\u000bapp_package\u0018\f \u0001(\t\u0012\r\n\u0005int13\u0018\r \u0001(\u0005\"\u0001\n\u0012RemoteImeKeyInject\u00123\n\bapp_info\u0018\u0001 \u0001(\u000b2!.com.kunal52.remote.RemoteAppInfo\u0012D\n\u0011text_field_status\u0018\u0002 \u0001(\u000b2).com.kunal52.remote.RemoteTextFieldStatus\"~\n\u000fRemoteKeyInject\u00123\n\bkey_code\u0018\u0001 \u0001(\u000e2!.com.kunal52.remote.RemoteKeyCode\u00126\n\tdirection\u0018\u0002 \u0001(\u000e2#.com.kunal52.remote.RemoteDirection\"\"\n\u0012RemotePingResponse\u0012\f\n\u0004val1\u0018\u0001 \u0001(\u0005\"/\n\u0011RemotePingRequest\u0012\f\n\u0004val1\u0018\u0001 \u0001(\u0005\u0012\f\n\u0004val2\u0018\u0002 \u0001(\u0005\"!\n\u000fRemoteSetActive\u0012\u000e\n\u0006active\u0018\u0001 \u0001(\u0005\"\u0001\n\u0010RemoteDeviceInfo\u0012\r\n\u0005model\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006vendor\u0018\u0002 \u0001(\t\u0012\u0010\n\bunknown1\u0018\u0003 \u0001(\u0005\u0012\u0010\n\bunknown2\u0018\u0004 \u0001(\t\u0012\u0014\n\fpackage_name\u0018\u0005 \u0001(\t\u0012\u0013\n\u000bapp_version\u0018\u0006 \u0001(\t\"[\n\u000fRemoteConfigure\u0012\r\n\u0005code1\u0018\u0001 \u0001(\u0005\u00129\n\u000bdevice_info\u0018\u0002 \u0001(\u000b2$.com.kunal52.remote.RemoteDeviceInfo\"P\n\u000bRemoteError\u0012\r\n\u0005value\u0018\u0001 \u0001(\b\u00122\n\u0007message\u0018\u0002 \u0001(\u000b2!.com.kunal52.remote.RemoteMessage\"\n\n\rRemoteMessage\u0012=\n\u0010remote_configure\u0018\u0001 \u0001(\u000b2#.com.kunal52.remote.RemoteConfigure\u0012>\n\u0011remote_set_active\u0018\u0002 \u0001(\u000b2#.com.kunal52.remote.RemoteSetActive\u00125\n\fremote_error\u0018\u0003 \u0001(\u000b2\u001f.com.kunal52.remote.RemoteError\u0012B\n\u0013remote_ping_request\u0018\b \u0001(\u000b2%.com.kunal52.remote.RemotePingRequest\u0012D\n\u0014remote_ping_response\u0018\t \u0001(\u000b2&.com.kunal52.remote.RemotePingResponse\u0012>\n\u0011remote_key_inject\u0018\n \u0001(\u000b2#.com.kunal52.remote.RemoteKeyInject\u0012E\n\u0015remote_ime_key_inject\u0018\u0014 \u0001(\u000b2&.com.kunal52.remote.RemoteImeKeyInject\u0012E\n\u0015remote_ime_batch_edit\u0018\u0015 \u0001(\u000b2&.com.kunal52.remote.RemoteImeBatchEdit\u0012I\n\u0017remote_ime_show_request\u0018\u0016 \u0001(\u000b2(.com.kunal52.remote.RemoteImeShowRequest\u0012@\n\u0012remote_voice_begin\u0018\u001e \u0001(\u000b2$.com.kunal52.remote.RemoteVoiceBegin\u0012D\n\u0014remote_voice_payload\u0018\u001f \u0001(\u000b2&.com.kunal52.remote.RemoteVoicePayload\u0012<\n\u0010remote_voice_end\u0018  \u0001(\u000b2\".com.kunal52.remote.RemoteVoiceEnd\u00125\n\fremote_start\u0018( \u0001(\u000b2\u001f.com.kunal52.remote.RemoteStart\u0012I\n\u0017remote_set_volume_level\u00182 \u0001(\u000b2(.com.kunal52.remote.RemoteSetVolumeLevel\u0012O\n\u001aremote_adjust_volume_level\u00183 \u0001(\u000b2+.com.kunal52.remote.RemoteAdjustVolumeLevel\u0012\\\n!remote_set_preferred_audio_device\u0018< \u0001(\u000b21.com.kunal52.remote.RemoteSetPreferredAudioDevice\u0012`\n#remote_reset_preferred_audio_device\u0018= \u0001(\u000b23.com.kunal52.remote.RemoteResetPreferredAudioDevice\u0012V\n\u001eremote_app_link_launch_request\u0018Z \u0001(\u000b2..com.kunal52.remote.RemoteAppLinkLaunchRequest*ç7\n\rRemoteKeyCode\u0012\u0013\n\u000fKEYCODE_UNKNOWN\u0010\u0000\u0012\u0015\n\u0011KEYCODE_SOFT_LEFT\u0010\u0001\u0012\u0016\n\u0012KEYCODE_SOFT_RIGHT\u0010\u0002\u0012\u0010\n\fKEYCODE_HOME\u0010\u0003\u0012\u0010\n\fKEYCODE_BACK\u0010\u0004\u0012\u0010\n\fKEYCODE_CALL\u0010\u0005\u0012\u0013\n\u000fKEYCODE_ENDCALL\u0010\u0006\u0012\r\n\tKEYCODE_0\u0010\u0007\u0012\r\n\tKEYCODE_1\u0010\b\u0012\r\n\tKEYCODE_2\u0010\t\u0012\r\n\tKEYCODE_3\u0010\n\u0012\r\n\tKEYCODE_4\u0010\u000b\u0012\r\n\tKEYCODE_5\u0010\f\u0012\r\n\tKEYCODE_6\u0010\r\u0012\r\n\tKEYCODE_7\u0010\u000e\u0012\r\n\tKEYCODE_8\u0010\u000f\u0012\r\n\tKEYCODE_9\u0010\u0010\u0012\u0010\n\fKEYCODE_STAR\u0010\u0011\u0012\u0011\n\rKEYCODE_POUND\u0010\u0012\u0012\u0013\n\u000fKEYCODE_DPAD_UP\u0010\u0013\u0012\u0015\n\u0011KEYCODE_DPAD_DOWN\u0010\u0014\u0012\u0015\n\u0011KEYCODE_DPAD_LEFT\u0010\u0015\u0012\u0016\n\u0012KEYCODE_DPAD_RIGHT\u0010\u0016\u0012\u0017\n\u0013KEYCODE_DPAD_CENTER\u0010\u0017\u0012\u0015\n\u0011KEYCODE_VOLUME_UP\u0010\u0018\u0012\u0017\n\u0013KEYCODE_VOLUME_DOWN\u0010\u0019\u0012\u0011\n\rKEYCODE_POWER\u0010\u001a\u0012\u0012\n\u000eKEYCODE_CAMERA\u0010\u001b\u0012\u0011\n\rKEYCODE_CLEAR\u0010\u001c\u0012\r\n\tKEYCODE_A\u0010\u001d\u0012\r\n\tKEYCODE_B\u0010\u001e\u0012\r\n\tKEYCODE_C\u0010\u001f\u0012\r\n\tKEYCODE_D\u0010 \u0012\r\n\tKEYCODE_E\u0010!\u0012\r\n\tKEYCODE_F\u0010\"\u0012\r\n\tKEYCODE_G\u0010#\u0012\r\n\tKEYCODE_H\u0010$\u0012\r\n\tKEYCODE_I\u0010%\u0012\r\n\tKEYCODE_J\u0010&\u0012\r\n\tKEYCODE_K\u0010'\u0012\r\n\tKEYCODE_L\u0010(\u0012\r\n\tKEYCODE_M\u0010)\u0012\r\n\tKEYCODE_N\u0010*\u0012\r\n\tKEYCODE_O\u0010+\u0012\r\n\tKEYCODE_P\u0010,\u0012\r\n\tKEYCODE_Q\u0010-\u0012\r\n\tKEYCODE_R\u0010.\u0012\r\n\tKEYCODE_S\u0010/\u0012\r\n\tKEYCODE_T\u00100\u0012\r\n\tKEYCODE_U\u00101\u0012\r\n\tKEYCODE_V\u00102\u0012\r\n\tKEYCODE_W\u00103\u0012\r\n\tKEYCODE_X\u00104\u0012\r\n\tKEYCODE_Y\u00105\u0012\r\n\tKEYCODE_Z\u00106\u0012\u0011\n\rKEYCODE_COMMA\u00107\u0012\u0012\n\u000eKEYCODE_PERIOD\u00108\u0012\u0014\n\u0010KEYCODE_ALT_LEFT\u00109\u0012\u0015\n\u0011KEYCODE_ALT_RIGHT\u0010:\u0012\u0016\n\u0012KEYCODE_SHIFT_LEFT\u0010;\u0012\u0017\n\u0013KEYCODE_SHIFT_RIGHT\u0010<\u0012\u000f\n\u000bKEYCODE_TAB\u0010=\u0012\u0011\n\rKEYCODE_SPACE\u0010>\u0012\u000f\n\u000bKEYCODE_SYM\u0010?\u0012\u0014\n\u0010KEYCODE_EXPLORER\u0010@\u0012\u0014\n\u0010KEYCODE_ENVELOPE\u0010A\u0012\u0011\n\rKEYCODE_ENTER\u0010B\u0012\u000f\n\u000bKEYCODE_DEL\u0010C\u0012\u0011\n\rKEYCODE_GRAVE\u0010D\u0012\u0011\n\rKEYCODE_MINUS\u0010E\u0012\u0012\n\u000eKEYCODE_EQUALS\u0010F\u0012\u0018\n\u0014KEYCODE_LEFT_BRACKET\u0010G\u0012\u0019\n\u0015KEYCODE_RIGHT_BRACKET\u0010H\u0012\u0015\n\u0011KEYCODE_BACKSLASH\u0010I\u0012\u0015\n\u0011KEYCODE_SEMICOLON\u0010J\u0012\u0016\n\u0012KEYCODE_APOSTROPHE\u0010K\u0012\u0011\n\rKEYCODE_SLASH\u0010L\u0012\u000e\n\nKEYCODE_AT\u0010M\u0012\u000f\n\u000bKEYCODE_NUM\u0010N\u0012\u0017\n\u0013KEYCODE_HEADSETHOOK\u0010O\u0012\u0011\n\rKEYCODE_FOCUS\u0010P\u0012\u0010\n\fKEYCODE_PLUS\u0010Q\u0012\u0010\n\fKEYCODE_MENU\u0010R\u0012\u0018\n\u0014KEYCODE_NOTIFICATION\u0010S\u0012\u0012\n\u000eKEYCODE_SEARCH\u0010T\u0012\u001c\n\u0018KEYCODE_MEDIA_PLAY_PAUSE\u0010U\u0012\u0016\n\u0012KEYCODE_MEDIA_STOP\u0010V\u0012\u0016\n\u0012KEYCODE_MEDIA_NEXT\u0010W\u0012\u001a\n\u0016KEYCODE_MEDIA_PREVIOUS\u0010X\u0012\u0018\n\u0014KEYCODE_MEDIA_REWIND\u0010Y\u0012\u001e\n\u001aKEYCODE_MEDIA_FAST_FORWARD\u0010Z\u0012\u0010\n\fKEYCODE_MUTE\u0010[\u0012\u0013\n\u000fKEYCODE_PAGE_UP\u0010\\\u0012\u0015\n\u0011KEYCODE_PAGE_DOWN\u0010]\u0012\u0017\n\u0013KEYCODE_PICTSYMBOLS\u0010^\u0012\u001a\n\u0016KEYCODE_SWITCH_CHARSET\u0010_\u0012\u0014\n\u0010KEYCODE_BUTTON_A\u0010`\u0012\u0014\n\u0010KEYCODE_BUTTON_B\u0010a\u0012\u0014\n\u0010KEYCODE_BUTTON_C\u0010b\u0012\u0014\n\u0010KEYCODE_BUTTON_X\u0010c\u0012\u0014\n\u0010KEYCODE_BUTTON_Y\u0010d\u0012\u0014\n\u0010KEYCODE_BUTTON_Z\u0010e\u0012\u0015\n\u0011KEYCODE_BUTTON_L1\u0010f\u0012\u0015\n\u0011KEYCODE_BUTTON_R1\u0010g\u0012\u0015\n\u0011KEYCODE_BUTTON_L2\u0010h\u0012\u0015\n\u0011KEYCODE_BUTTON_R2\u0010i\u0012\u0019\n\u0015KEYCODE_BUTTON_THUMBL\u0010j\u0012\u0019\n\u0015KEYCODE_BUTTON_THUMBR\u0010k\u0012\u0018\n\u0014KEYCODE_BUTTON_START\u0010l\u0012\u0019\n\u0015KEYCODE_BUTTON_SELECT\u0010m\u0012\u0017\n\u0013KEYCODE_BUTTON_MODE\u0010n\u0012\u0012\n\u000eKEYCODE_ESCAPE\u0010o\u0012\u0017\n\u0013KEYCODE_FORWARD_DEL\u0010p\u0012\u0015\n\u0011KEYCODE_CTRL_LEFT\u0010q\u0012\u0016\n\u0012KEYCODE_CTRL_RIGHT\u0010r\u0012\u0015\n\u0011KEYCODE_CAPS_LOCK\u0010s\u0012\u0017\n\u0013KEYCODE_SCROLL_LOCK\u0010t\u0012\u0015\n\u0011KEYCODE_META_LEFT\u0010u\u0012\u0016\n\u0012KEYCODE_META_RIGHT\u0010v\u0012\u0014\n\u0010KEYCODE_FUNCTION\u0010w\u0012\u0011\n\rKEYCODE_SYSRQ\u0010x\u0012\u0011\n\rKEYCODE_BREAK\u0010y\u0012\u0015\n\u0011KEYCODE_MOVE_HOME\u0010z\u0012\u0014\n\u0010KEYCODE_MOVE_END\u0010{\u0012\u0012\n\u000eKEYCODE_INSERT\u0010|\u0012\u0013\n\u000fKEYCODE_FORWARD\u0010}\u0012\u0016\n\u0012KEYCODE_MEDIA_PLAY\u0010~\u0012\u0017\n\u0013KEYCODE_MEDIA_PAUSE\u0010\u0012\u0018\n\u0013KEYCODE_MEDIA_CLOSE\u0010\u0001\u0012\u0018\n\u0013KEYCODE_MEDIA_EJECT\u0010\u0001\u0012\u0019\n\u0014KEYCODE_MEDIA_RECORD\u0010\u0001\u0012\u000f\n\nKEYCODE_F1\u0010\u0001\u0012\u000f\n\nKEYCODE_F2\u0010\u0001\u0012\u000f\n\nKEYCODE_F3\u0010\u0001\u0012\u000f\n\nKEYCODE_F4\u0010\u0001\u0012\u000f\n\nKEYCODE_F5\u0010\u0001\u0012\u000f\n\nKEYCODE_F6\u0010\u0001\u0012\u000f\n\nKEYCODE_F7\u0010\u0001\u0012\u000f\n\nKEYCODE_F8\u0010\u0001\u0012\u000f\n\nKEYCODE_F9\u0010\u0001\u0012\u0010\n\u000bKEYCODE_F10\u0010\u0001\u0012\u0010\n\u000bKEYCODE_F11\u0010\u0001\u0012\u0010\n\u000bKEYCODE_F12\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUM_LOCK\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_0\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_1\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_2\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_3\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_4\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_5\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_6\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_7\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_8\u0010\u0001\u0012\u0015\n\u0010KEYCODE_NUMPAD_9\u0010\u0001\u0012\u001a\n\u0015KEYCODE_NUMPAD_DIVIDE\u0010\u0001\u0012\u001c\n\u0017KEYCODE_NUMPAD_MULTIPLY\u0010\u0001\u0012\u001c\n\u0017KEYCODE_NUMPAD_SUBTRACT\u0010\u0001\u0012\u0017\n\u0012KEYCODE_NUMPAD_ADD\u0010\u0001\u0012\u0017\n\u0012KEYCODE_NUMPAD_DOT\u0010\u0001\u0012\u0019\n\u0014KEYCODE_NUMPAD_COMMA\u0010\u0001\u0012\u0019\n\u0014KEYCODE_NUMPAD_ENTER\u0010 \u0001\u0012\u001a\n\u0015KEYCODE_NUMPAD_EQUALS\u0010¡\u0001\u0012\u001e\n\u0019KEYCODE_NUMPAD_LEFT_PAREN\u0010¢\u0001\u0012\u001f\n\u001aKEYCODE_NUMPAD_RIGHT_PAREN\u0010£\u0001\u0012\u0018\n\u0013KEYCODE_VOLUME_MUTE\u0010¤\u0001\u0012\u0011\n\fKEYCODE_INFO\u0010¥\u0001\u0012\u0017\n\u0012KEYCODE_CHANNEL_UP\u0010¦\u0001\u0012\u0019\n\u0014KEYCODE_CHANNEL_DOWN\u0010§\u0001\u0012\u0014\n\u000fKEYCODE_ZOOM_IN\u0010¨\u0001\u0012\u0015\n\u0010KEYCODE_ZOOM_OUT\u0010©\u0001\u0012\u000f\n\nKEYCODE_TV\u0010ª\u0001\u0012\u0013\n\u000eKEYCODE_WINDOW\u0010«\u0001\u0012\u0012\n\rKEYCODE_GUIDE\u0010¬\u0001\u0012\u0010\n\u000bKEYCODE_DVR\u0010­\u0001\u0012\u0015\n\u0010KEYCODE_BOOKMARK\u0010®\u0001\u0012\u0015\n\u0010KEYCODE_CAPTIONS\u0010¯\u0001\u0012\u0015\n\u0010KEYCODE_SETTINGS\u0010°\u0001\u0012\u0015\n\u0010KEYCODE_TV_POWER\u0010±\u0001\u0012\u0015\n\u0010KEYCODE_TV_INPUT\u0010²\u0001\u0012\u0016\n\u0011KEYCODE_STB_POWER\u0010³\u0001\u0012\u0016\n\u0011KEYCODE_STB_INPUT\u0010´\u0001\u0012\u0016\n\u0011KEYCODE_AVR_POWER\u0010µ\u0001\u0012\u0016\n\u0011KEYCODE_AVR_INPUT\u0010¶\u0001\u0012\u0015\n\u0010KEYCODE_PROG_RED\u0010·\u0001\u0012\u0017\n\u0012KEYCODE_PROG_GREEN\u0010¸\u0001\u0012\u0018\n\u0013KEYCODE_PROG_YELLOW\u0010¹\u0001\u0012\u0016\n\u0011KEYCODE_PROG_BLUE\u0010º\u0001\u0012\u0017\n\u0012KEYCODE_APP_SWITCH\u0010»\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_1\u0010¼\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_2\u0010½\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_3\u0010¾\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_4\u0010¿\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_5\u0010À\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_6\u0010Á\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_7\u0010Â\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_8\u0010Ã\u0001\u0012\u0015\n\u0010KEYCODE_BUTTON_9\u0010Ä\u0001\u0012\u0016\n\u0011KEYCODE_BUTTON_10\u0010Å\u0001\u0012\u0016\n\u0011KEYCODE_BUTTON_11\u0010Æ\u0001\u0012\u0016\n\u0011KEYCODE_BUTTON_12\u0010Ç\u0001\u0012\u0016\n\u0011KEYCODE_BUTTON_13\u0010È\u0001\u0012\u0016\n\u0011KEYCODE_BUTTON_14\u0010É\u0001\u0012\u0016\n\u0011KEYCODE_BUTTON_15\u0010Ê\u0001\u0012\u0016\n\u0011KEYCODE_BUTTON_16\u0010Ë\u0001\u0012\u001c\n\u0017KEYCODE_LANGUAGE_SWITCH\u0010Ì\u0001\u0012\u0018\n\u0013KEYCODE_MANNER_MODE\u0010Í\u0001\u0012\u0014\n\u000fKEYCODE_3D_MODE\u0010Î\u0001\u0012\u0015\n\u0010KEYCODE_CONTACTS\u0010Ï\u0001\u0012\u0015\n\u0010KEYCODE_CALENDAR\u0010Ð\u0001\u0012\u0012\n\rKEYCODE_MUSIC\u0010Ñ\u0001\u0012\u0017\n\u0012KEYCODE_CALCULATOR\u0010Ò\u0001\u0012\u001c\n\u0017KEYCODE_ZENKAKU_HANKAKU\u0010Ó\u0001\u0012\u0011\n\fKEYCODE_EISU\u0010Ô\u0001\u0012\u0015\n\u0010KEYCODE_MUHENKAN\u0010Õ\u0001\u0012\u0013\n\u000eKEYCODE_HENKAN\u0010Ö\u0001\u0012\u001e\n\u0019KEYCODE_KATAKANA_HIRAGANA\u0010×\u0001\u0012\u0010\n\u000bKEYCODE_YEN\u0010Ø\u0001\u0012\u000f\n\nKEYCODE_RO\u0010Ù\u0001\u0012\u0011\n\fKEYCODE_KANA\u0010Ú\u0001\u0012\u0013\n\u000eKEYCODE_ASSIST\u0010Û\u0001\u0012\u001c\n\u0017KEYCODE_BRIGHTNESS_DOWN\u0010Ü\u0001\u0012\u001a\n\u0015KEYCODE_BRIGHTNESS_UP\u0010Ý\u0001\u0012\u001e\n\u0019KEYCODE_MEDIA_AUDIO_TRACK\u0010Þ\u0001\u0012\u0012\n\rKEYCODE_SLEEP\u0010ß\u0001\u0012\u0013\n\u000eKEYCODE_WAKEUP\u0010à\u0001\u0012\u0014\n\u000fKEYCODE_PAIRING\u0010á\u0001\u0012\u001b\n\u0016KEYCODE_MEDIA_TOP_MENU\u0010â\u0001\u0012\u000f\n\nKEYCODE_11\u0010ã\u0001\u0012\u000f\n\nKEYCODE_12\u0010ä\u0001\u0012\u0019\n\u0014KEYCODE_LAST_CHANNEL\u0010å\u0001\u0012\u001c\n\u0017KEYCODE_TV_DATA_SERVICE\u0010æ\u0001\u0012\u0019\n\u0014KEYCODE_VOICE_ASSIST\u0010ç\u0001\u0012\u001d\n\u0018KEYCODE_TV_RADIO_SERVICE\u0010è\u0001\u0012\u0018\n\u0013KEYCODE_TV_TELETEXT\u0010é\u0001\u0012\u001c\n\u0017KEYCODE_TV_NUMBER_ENTRY\u0010ê\u0001\u0012\"\n\u001dKEYCODE_TV_TERRESTRIAL_ANALOG\u0010ë\u0001\u0012#\n\u001eKEYCODE_TV_TERRESTRIAL_DIGITAL\u0010ì\u0001\u0012\u0019\n\u0014KEYCODE_TV_SATELLITE\u0010í\u0001\u0012\u001c\n\u0017KEYCODE_TV_SATELLITE_BS\u0010î\u0001\u0012\u001c\n\u0017KEYCODE_TV_SATELLITE_CS\u0010ï\u0001\u0012!\n\u001cKEYCODE_TV_SATELLITE_SERVICE\u0010ð\u0001\u0012\u0017\n\u0012KEYCODE_TV_NETWORK\u0010ñ\u0001\u0012\u001d\n\u0018KEYCODE_TV_ANTENNA_CABLE\u0010ò\u0001\u0012\u001c\n\u0017KEYCODE_TV_INPUT_HDMI_1\u0010ó\u0001\u0012\u001c\n\u0017KEYCODE_TV_INPUT_HDMI_2\u0010ô\u0001\u0012\u001c\n\u0017KEYCODE_TV_INPUT_HDMI_3\u0010õ\u0001\u0012\u001c\n\u0017KEYCODE_TV_INPUT_HDMI_4\u0010ö\u0001\u0012!\n\u001cKEYCODE_TV_INPUT_COMPOSITE_1\u0010÷\u0001\u0012!\n\u001cKEYCODE_TV_INPUT_COMPOSITE_2\u0010ø\u0001\u0012!\n\u001cKEYCODE_TV_INPUT_COMPONENT_1\u0010ù\u0001\u0012!\n\u001cKEYCODE_TV_INPUT_COMPONENT_2\u0010ú\u0001\u0012\u001b\n\u0016KEYCODE_TV_INPUT_VGA_1\u0010û\u0001\u0012!\n\u001cKEYCODE_TV_AUDIO_DESCRIPTION\u0010ü\u0001\u0012(\n#KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP\u0010ý\u0001\u0012*\n%KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN\u0010þ\u0001\u0012\u0019\n\u0014KEYCODE_TV_ZOOM_MODE\u0010ÿ\u0001\u0012\u001d\n\u0018KEYCODE_TV_CONTENTS_MENU\u0010\u0002\u0012\"\n\u001dKEYCODE_TV_MEDIA_CONTEXT_MENU\u0010\u0002\u0012!\n\u001cKEYCODE_TV_TIMER_PROGRAMMING\u0010\u0002\u0012\u0011\n\fKEYCODE_HELP\u0010\u0002\u0012\u001e\n\u0019KEYCODE_NAVIGATE_PREVIOUS\u0010\u0002\u0012\u001a\n\u0015KEYCODE_NAVIGATE_NEXT\u0010\u0002\u0012\u0018\n\u0013KEYCODE_NAVIGATE_IN\u0010\u0002\u0012\u0019\n\u0014KEYCODE_NAVIGATE_OUT\u0010\u0002\u0012\u0019\n\u0014KEYCODE_STEM_PRIMARY\u0010\u0002\u0012\u0013\n\u000eKEYCODE_STEM_1\u0010\u0002\u0012\u0013\n\u000eKEYCODE_STEM_2\u0010\u0002\u0012\u0013\n\u000eKEYCODE_STEM_3\u0010\u0002\u0012\u0019\n\u0014KEYCODE_DPAD_UP_LEFT\u0010\u0002\u0012\u001b\n\u0016KEYCODE_DPAD_DOWN_LEFT\u0010\u0002\u0012\u001a\n\u0015KEYCODE_DPAD_UP_RIGHT\u0010\u0002\u0012\u001c\n\u0017KEYCODE_DPAD_DOWN_RIGHT\u0010\u0002\u0012\u001f\n\u001aKEYCODE_MEDIA_SKIP_FORWARD\u0010\u0002\u0012 \n\u001bKEYCODE_MEDIA_SKIP_BACKWARD\u0010\u0002\u0012\u001f\n\u001aKEYCODE_MEDIA_STEP_FORWARD\u0010\u0002\u0012 \n\u001bKEYCODE_MEDIA_STEP_BACKWARD\u0010\u0002\u0012\u0017\n\u0012KEYCODE_SOFT_SLEEP\u0010\u0002\u0012\u0010\n\u000bKEYCODE_CUT\u0010\u0002\u0012\u0011\n\fKEYCODE_COPY\u0010\u0002\u0012\u0012\n\rKEYCODE_PASTE\u0010\u0002\u0012!\n\u001cKEYCODE_SYSTEM_NAVIGATION_UP\u0010\u0002\u0012#\n\u001eKEYCODE_SYSTEM_NAVIGATION_DOWN\u0010\u0002\u0012#\n\u001eKEYCODE_SYSTEM_NAVIGATION_LEFT\u0010\u0002\u0012$\n\u001fKEYCODE_SYSTEM_NAVIGATION_RIGHT\u0010\u0002\u0012\u0015\n\u0010KEYCODE_ALL_APPS\u0010\u0002\u0012\u0014\n\u000fKEYCODE_REFRESH\u0010\u0002\u0012\u0016\n\u0011KEYCODE_THUMBS_UP\u0010\u0002\u0012\u0018\n\u0013KEYCODE_THUMBS_DOWN\u0010\u0002\u0012\u001b\n\u0016KEYCODE_PROFILE_SWITCH\u0010 \u0002\u0012\u0018\n\u0013KEYCODE_VIDEO_APP_1\u0010¡\u0002\u0012\u0018\n\u0013KEYCODE_VIDEO_APP_2\u0010¢\u0002\u0012\u0018\n\u0013KEYCODE_VIDEO_APP_3\u0010£\u0002\u0012\u0018\n\u0013KEYCODE_VIDEO_APP_4\u0010¤\u0002\u0012\u0018\n\u0013KEYCODE_VIDEO_APP_5\u0010¥\u0002\u0012\u0018\n\u0013KEYCODE_VIDEO_APP_6\u0010¦\u0002\u0012\u0018\n\u0013KEYCODE_VIDEO_APP_7\u0010§\u0002\u0012\u0018\n\u0013KEYCODE_VIDEO_APP_8\u0010¨\u0002\u0012\u001b\n\u0016KEYCODE_FEATURED_APP_1\u0010©\u0002\u0012\u001b\n\u0016KEYCODE_FEATURED_APP_2\u0010ª\u0002\u0012\u001b\n\u0016KEYCODE_FEATURED_APP_3\u0010«\u0002\u0012\u001b\n\u0016KEYCODE_FEATURED_APP_4\u0010¬\u0002\u0012\u0017\n\u0012KEYCODE_DEMO_APP_1\u0010­\u0002\u0012\u0017\n\u0012KEYCODE_DEMO_APP_2\u0010®\u0002\u0012\u0017\n\u0012KEYCODE_DEMO_APP_3\u0010¯\u0002\u0012\u0017\n\u0012KEYCODE_DEMO_APP_4\u0010°\u0002*Q\n\u000fRemoteDirection\u0012\u0015\n\u0011UNKNOWN_DIRECTION\u0010\u0000\u0012\u000e\n\nSTART_LONG\u0010\u0001\u0012\f\n\bEND_LONG\u0010\u0002\u0012\t\n\u0005SHORT\u0010\u0003b\u0006proto3"}, new Descriptors.FileDescriptor[0]);

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_descriptor = descriptor2;
        internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"AppLink"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_descriptor = descriptor3;
        internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[0]);
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_descriptor = descriptor4;
        internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[0]);
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_descriptor = descriptor5;
        internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[0]);
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_com_kunal52_remote_RemoteSetVolumeLevel_descriptor = descriptor6;
        internal_static_com_kunal52_remote_RemoteSetVolumeLevel_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Unknown1", "Unknown2", "PlayerModel", "Unknown4", "Unknown5", "VolumeMax", "VolumeLevel", "VolumeMuted"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(5);
        internal_static_com_kunal52_remote_RemoteStart_descriptor = descriptor7;
        internal_static_com_kunal52_remote_RemoteStart_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Started"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(6);
        internal_static_com_kunal52_remote_RemoteVoiceEnd_descriptor = descriptor8;
        internal_static_com_kunal52_remote_RemoteVoiceEnd_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[0]);
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(7);
        internal_static_com_kunal52_remote_RemoteVoicePayload_descriptor = descriptor9;
        internal_static_com_kunal52_remote_RemoteVoicePayload_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[0]);
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(8);
        internal_static_com_kunal52_remote_RemoteVoiceBegin_descriptor = descriptor10;
        internal_static_com_kunal52_remote_RemoteVoiceBegin_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[0]);
        Descriptors.Descriptor descriptor11 = getDescriptor().getMessageTypes().get(9);
        internal_static_com_kunal52_remote_RemoteTextFieldStatus_descriptor = descriptor11;
        internal_static_com_kunal52_remote_RemoteTextFieldStatus_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"CounterField", "Value", "Start", "End", "Int5", "Label"});
        Descriptors.Descriptor descriptor12 = getDescriptor().getMessageTypes().get(10);
        internal_static_com_kunal52_remote_RemoteImeShowRequest_descriptor = descriptor12;
        internal_static_com_kunal52_remote_RemoteImeShowRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"RemoteTextFieldStatus"});
        Descriptors.Descriptor descriptor13 = getDescriptor().getMessageTypes().get(11);
        internal_static_com_kunal52_remote_RemoteEditInfo_descriptor = descriptor13;
        internal_static_com_kunal52_remote_RemoteEditInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"Insert"});
        Descriptors.Descriptor descriptor14 = getDescriptor().getMessageTypes().get(12);
        internal_static_com_kunal52_remote_RemoteImeBatchEdit_descriptor = descriptor14;
        internal_static_com_kunal52_remote_RemoteImeBatchEdit_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"ImeCounter", "FieldCounter", "EditInfo"});
        Descriptors.Descriptor descriptor15 = getDescriptor().getMessageTypes().get(13);
        internal_static_com_kunal52_remote_RemoteAppInfo_descriptor = descriptor15;
        internal_static_com_kunal52_remote_RemoteAppInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"Counter", "Int2", "Int3", "Int4", "Int7", "Int8", "Label", "AppPackage", "Int13"});
        Descriptors.Descriptor descriptor16 = getDescriptor().getMessageTypes().get(14);
        internal_static_com_kunal52_remote_RemoteImeKeyInject_descriptor = descriptor16;
        internal_static_com_kunal52_remote_RemoteImeKeyInject_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"AppInfo", "TextFieldStatus"});
        Descriptors.Descriptor descriptor17 = getDescriptor().getMessageTypes().get(15);
        internal_static_com_kunal52_remote_RemoteKeyInject_descriptor = descriptor17;
        internal_static_com_kunal52_remote_RemoteKeyInject_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"KeyCode", "Direction"});
        Descriptors.Descriptor descriptor18 = getDescriptor().getMessageTypes().get(16);
        internal_static_com_kunal52_remote_RemotePingResponse_descriptor = descriptor18;
        internal_static_com_kunal52_remote_RemotePingResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"Val1"});
        Descriptors.Descriptor descriptor19 = getDescriptor().getMessageTypes().get(17);
        internal_static_com_kunal52_remote_RemotePingRequest_descriptor = descriptor19;
        internal_static_com_kunal52_remote_RemotePingRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor19, new String[]{"Val1", "Val2"});
        Descriptors.Descriptor descriptor20 = getDescriptor().getMessageTypes().get(18);
        internal_static_com_kunal52_remote_RemoteSetActive_descriptor = descriptor20;
        internal_static_com_kunal52_remote_RemoteSetActive_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor20, new String[]{"Active"});
        Descriptors.Descriptor descriptor21 = getDescriptor().getMessageTypes().get(19);
        internal_static_com_kunal52_remote_RemoteDeviceInfo_descriptor = descriptor21;
        internal_static_com_kunal52_remote_RemoteDeviceInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor21, new String[]{ExifInterface.TAG_MODEL, "Vendor", "Unknown1", "Unknown2", "PackageName", "AppVersion"});
        Descriptors.Descriptor descriptor22 = getDescriptor().getMessageTypes().get(20);
        internal_static_com_kunal52_remote_RemoteConfigure_descriptor = descriptor22;
        internal_static_com_kunal52_remote_RemoteConfigure_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor22, new String[]{"Code1", "DeviceInfo"});
        Descriptors.Descriptor descriptor23 = getDescriptor().getMessageTypes().get(21);
        internal_static_com_kunal52_remote_RemoteError_descriptor = descriptor23;
        internal_static_com_kunal52_remote_RemoteError_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor23, new String[]{"Value", "Message"});
        Descriptors.Descriptor descriptor24 = getDescriptor().getMessageTypes().get(22);
        internal_static_com_kunal52_remote_RemoteMessage_descriptor = descriptor24;
        internal_static_com_kunal52_remote_RemoteMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor24, new String[]{"RemoteConfigure", "RemoteSetActive", "RemoteError", "RemotePingRequest", "RemotePingResponse", "RemoteKeyInject", "RemoteImeKeyInject", "RemoteImeBatchEdit", "RemoteImeShowRequest", "RemoteVoiceBegin", "RemoteVoicePayload", "RemoteVoiceEnd", "RemoteStart", "RemoteSetVolumeLevel", "RemoteAdjustVolumeLevel", "RemoteSetPreferredAudioDevice", "RemoteResetPreferredAudioDevice", "RemoteAppLinkLaunchRequest"});
    }

    private Remotemessage() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public enum RemoteKeyCode implements ProtocolMessageEnum {
        KEYCODE_UNKNOWN(0),
        KEYCODE_SOFT_LEFT(1),
        KEYCODE_SOFT_RIGHT(2),
        KEYCODE_HOME(3),
        KEYCODE_BACK(4),
        KEYCODE_CALL(5),
        KEYCODE_ENDCALL(6),
        KEYCODE_0(7),
        KEYCODE_1(8),
        KEYCODE_2(9),
        KEYCODE_3(10),
        KEYCODE_4(11),
        KEYCODE_5(12),
        KEYCODE_6(13),
        KEYCODE_7(14),
        KEYCODE_8(15),
        KEYCODE_9(16),
        KEYCODE_STAR(17),
        KEYCODE_POUND(18),
        KEYCODE_DPAD_UP(19),
        KEYCODE_DPAD_DOWN(20),
        KEYCODE_DPAD_LEFT(21),
        KEYCODE_DPAD_RIGHT(22),
        KEYCODE_DPAD_CENTER(23),
        KEYCODE_VOLUME_UP(24),
        KEYCODE_VOLUME_DOWN(25),
        KEYCODE_POWER(26),
        KEYCODE_CAMERA(27),
        KEYCODE_CLEAR(28),
        KEYCODE_A(29),
        KEYCODE_B(30),
        KEYCODE_C(31),
        KEYCODE_D(32),
        KEYCODE_E(33),
        KEYCODE_F(34),
        KEYCODE_G(35),
        KEYCODE_H(36),
        KEYCODE_I(37),
        KEYCODE_J(38),
        KEYCODE_K(39),
        KEYCODE_L(40),
        KEYCODE_M(41),
        KEYCODE_N(42),
        KEYCODE_O(43),
        KEYCODE_P(44),
        KEYCODE_Q(45),
        KEYCODE_R(46),
        KEYCODE_S(47),
        KEYCODE_T(48),
        KEYCODE_U(49),
        KEYCODE_V(50),
        KEYCODE_W(51),
        KEYCODE_X(52),
        KEYCODE_Y(53),
        KEYCODE_Z(54),
        KEYCODE_COMMA(55),
        KEYCODE_PERIOD(56),
        KEYCODE_ALT_LEFT(57),
        KEYCODE_ALT_RIGHT(58),
        KEYCODE_SHIFT_LEFT(59),
        KEYCODE_SHIFT_RIGHT(60),
        KEYCODE_TAB(61),
        KEYCODE_SPACE(62),
        KEYCODE_SYM(63),
        KEYCODE_EXPLORER(64),
        KEYCODE_ENVELOPE(65),
        KEYCODE_ENTER(66),
        KEYCODE_DEL(67),
        KEYCODE_GRAVE(68),
        KEYCODE_MINUS(69),
        KEYCODE_EQUALS(70),
        KEYCODE_LEFT_BRACKET(71),
        KEYCODE_RIGHT_BRACKET(72),
        KEYCODE_BACKSLASH(73),
        KEYCODE_SEMICOLON(74),
        KEYCODE_APOSTROPHE(75),
        KEYCODE_SLASH(76),
        KEYCODE_AT(77),
        KEYCODE_NUM(78),
        KEYCODE_HEADSETHOOK(79),
        KEYCODE_FOCUS(80),
        KEYCODE_PLUS(81),
        KEYCODE_MENU(82),
        KEYCODE_NOTIFICATION(83),
        KEYCODE_SEARCH(84),
        KEYCODE_MEDIA_PLAY_PAUSE(85),
        KEYCODE_MEDIA_STOP(86),
        KEYCODE_MEDIA_NEXT(87),
        KEYCODE_MEDIA_PREVIOUS(88),
        KEYCODE_MEDIA_REWIND(89),
        KEYCODE_MEDIA_FAST_FORWARD(90),
        KEYCODE_MUTE(91),
        KEYCODE_PAGE_UP(92),
        KEYCODE_PAGE_DOWN(93),
        KEYCODE_PICTSYMBOLS(94),
        KEYCODE_SWITCH_CHARSET(95),
        KEYCODE_BUTTON_A(96),
        KEYCODE_BUTTON_B(97),
        KEYCODE_BUTTON_C(98),
        KEYCODE_BUTTON_X(99),
        KEYCODE_BUTTON_Y(100),
        KEYCODE_BUTTON_Z(101),
        KEYCODE_BUTTON_L1(102),
        KEYCODE_BUTTON_R1(103),
        KEYCODE_BUTTON_L2(104),
        KEYCODE_BUTTON_R2(105),
        KEYCODE_BUTTON_THUMBL(106),
        KEYCODE_BUTTON_THUMBR(107),
        KEYCODE_BUTTON_START(108),
        KEYCODE_BUTTON_SELECT(109),
        KEYCODE_BUTTON_MODE(110),
        KEYCODE_ESCAPE(111),
        KEYCODE_FORWARD_DEL(112),
        KEYCODE_CTRL_LEFT(113),
        KEYCODE_CTRL_RIGHT(114),
        KEYCODE_CAPS_LOCK(115),
        KEYCODE_SCROLL_LOCK(116),
        KEYCODE_META_LEFT(117),
        KEYCODE_META_RIGHT(118),
        KEYCODE_FUNCTION(119),
        KEYCODE_SYSRQ(120),
        KEYCODE_BREAK(121),
        KEYCODE_MOVE_HOME(122),
        KEYCODE_MOVE_END(123),
        KEYCODE_INSERT(124),
        KEYCODE_FORWARD(125),
        KEYCODE_MEDIA_PLAY(126),
        KEYCODE_MEDIA_PAUSE(127),
        KEYCODE_MEDIA_CLOSE(128),
        KEYCODE_MEDIA_EJECT(KEYCODE_MEDIA_EJECT_VALUE),
        KEYCODE_MEDIA_RECORD(KEYCODE_MEDIA_RECORD_VALUE),
        KEYCODE_F1(KEYCODE_F1_VALUE),
        KEYCODE_F2(KEYCODE_F2_VALUE),
        KEYCODE_F3(KEYCODE_F3_VALUE),
        KEYCODE_F4(KEYCODE_F4_VALUE),
        KEYCODE_F5(KEYCODE_F5_VALUE),
        KEYCODE_F6(KEYCODE_F6_VALUE),
        KEYCODE_F7(KEYCODE_F7_VALUE),
        KEYCODE_F8(KEYCODE_F8_VALUE),
        KEYCODE_F9(KEYCODE_F9_VALUE),
        KEYCODE_F10(KEYCODE_F10_VALUE),
        KEYCODE_F11(KEYCODE_F11_VALUE),
        KEYCODE_F12(KEYCODE_F12_VALUE),
        KEYCODE_NUM_LOCK(KEYCODE_NUM_LOCK_VALUE),
        KEYCODE_NUMPAD_0(KEYCODE_NUMPAD_0_VALUE),
        KEYCODE_NUMPAD_1(KEYCODE_NUMPAD_1_VALUE),
        KEYCODE_NUMPAD_2(KEYCODE_NUMPAD_2_VALUE),
        KEYCODE_NUMPAD_3(KEYCODE_NUMPAD_3_VALUE),
        KEYCODE_NUMPAD_4(KEYCODE_NUMPAD_4_VALUE),
        KEYCODE_NUMPAD_5(KEYCODE_NUMPAD_5_VALUE),
        KEYCODE_NUMPAD_6(KEYCODE_NUMPAD_6_VALUE),
        KEYCODE_NUMPAD_7(KEYCODE_NUMPAD_7_VALUE),
        KEYCODE_NUMPAD_8(KEYCODE_NUMPAD_8_VALUE),
        KEYCODE_NUMPAD_9(KEYCODE_NUMPAD_9_VALUE),
        KEYCODE_NUMPAD_DIVIDE(KEYCODE_NUMPAD_DIVIDE_VALUE),
        KEYCODE_NUMPAD_MULTIPLY(KEYCODE_NUMPAD_MULTIPLY_VALUE),
        KEYCODE_NUMPAD_SUBTRACT(KEYCODE_NUMPAD_SUBTRACT_VALUE),
        KEYCODE_NUMPAD_ADD(KEYCODE_NUMPAD_ADD_VALUE),
        KEYCODE_NUMPAD_DOT(KEYCODE_NUMPAD_DOT_VALUE),
        KEYCODE_NUMPAD_COMMA(KEYCODE_NUMPAD_COMMA_VALUE),
        KEYCODE_NUMPAD_ENTER(KEYCODE_NUMPAD_ENTER_VALUE),
        KEYCODE_NUMPAD_EQUALS(KEYCODE_NUMPAD_EQUALS_VALUE),
        KEYCODE_NUMPAD_LEFT_PAREN(KEYCODE_NUMPAD_LEFT_PAREN_VALUE),
        KEYCODE_NUMPAD_RIGHT_PAREN(KEYCODE_NUMPAD_RIGHT_PAREN_VALUE),
        KEYCODE_VOLUME_MUTE(KEYCODE_VOLUME_MUTE_VALUE),
        KEYCODE_INFO(KEYCODE_INFO_VALUE),
        KEYCODE_CHANNEL_UP(KEYCODE_CHANNEL_UP_VALUE),
        KEYCODE_CHANNEL_DOWN(KEYCODE_CHANNEL_DOWN_VALUE),
        KEYCODE_ZOOM_IN(KEYCODE_ZOOM_IN_VALUE),
        KEYCODE_ZOOM_OUT(KEYCODE_ZOOM_OUT_VALUE),
        KEYCODE_TV(KEYCODE_TV_VALUE),
        KEYCODE_WINDOW(KEYCODE_WINDOW_VALUE),
        KEYCODE_GUIDE(KEYCODE_GUIDE_VALUE),
        KEYCODE_DVR(KEYCODE_DVR_VALUE),
        KEYCODE_BOOKMARK(KEYCODE_BOOKMARK_VALUE),
        KEYCODE_CAPTIONS(KEYCODE_CAPTIONS_VALUE),
        KEYCODE_SETTINGS(KEYCODE_SETTINGS_VALUE),
        KEYCODE_TV_POWER(KEYCODE_TV_POWER_VALUE),
        KEYCODE_TV_INPUT(KEYCODE_TV_INPUT_VALUE),
        KEYCODE_STB_POWER(KEYCODE_STB_POWER_VALUE),
        KEYCODE_STB_INPUT(KEYCODE_STB_INPUT_VALUE),
        KEYCODE_AVR_POWER(KEYCODE_AVR_POWER_VALUE),
        KEYCODE_AVR_INPUT(KEYCODE_AVR_INPUT_VALUE),
        KEYCODE_PROG_RED(KEYCODE_PROG_RED_VALUE),
        KEYCODE_PROG_GREEN(KEYCODE_PROG_GREEN_VALUE),
        KEYCODE_PROG_YELLOW(KEYCODE_PROG_YELLOW_VALUE),
        KEYCODE_PROG_BLUE(KEYCODE_PROG_BLUE_VALUE),
        KEYCODE_APP_SWITCH(KEYCODE_APP_SWITCH_VALUE),
        KEYCODE_BUTTON_1(188),
        KEYCODE_BUTTON_2(KEYCODE_BUTTON_2_VALUE),
        KEYCODE_BUTTON_3(190),
        KEYCODE_BUTTON_4(KEYCODE_BUTTON_4_VALUE),
        KEYCODE_BUTTON_5(192),
        KEYCODE_BUTTON_6(KEYCODE_BUTTON_6_VALUE),
        KEYCODE_BUTTON_7(KEYCODE_BUTTON_7_VALUE),
        KEYCODE_BUTTON_8(KEYCODE_BUTTON_8_VALUE),
        KEYCODE_BUTTON_9(KEYCODE_BUTTON_9_VALUE),
        KEYCODE_BUTTON_10(KEYCODE_BUTTON_10_VALUE),
        KEYCODE_BUTTON_11(KEYCODE_BUTTON_11_VALUE),
        KEYCODE_BUTTON_12(KEYCODE_BUTTON_12_VALUE),
        KEYCODE_BUTTON_13(200),
        KEYCODE_BUTTON_14(201),
        KEYCODE_BUTTON_15(202),
        KEYCODE_BUTTON_16(203),
        KEYCODE_LANGUAGE_SWITCH(KEYCODE_LANGUAGE_SWITCH_VALUE),
        KEYCODE_MANNER_MODE(KEYCODE_MANNER_MODE_VALUE),
        KEYCODE_3D_MODE(KEYCODE_3D_MODE_VALUE),
        KEYCODE_CONTACTS(KEYCODE_CONTACTS_VALUE),
        KEYCODE_CALENDAR(KEYCODE_CALENDAR_VALUE),
        KEYCODE_MUSIC(KEYCODE_MUSIC_VALUE),
        KEYCODE_CALCULATOR(KEYCODE_CALCULATOR_VALUE),
        KEYCODE_ZENKAKU_HANKAKU(211),
        KEYCODE_EISU(KEYCODE_EISU_VALUE),
        KEYCODE_MUHENKAN(KEYCODE_MUHENKAN_VALUE),
        KEYCODE_HENKAN(KEYCODE_HENKAN_VALUE),
        KEYCODE_KATAKANA_HIRAGANA(KEYCODE_KATAKANA_HIRAGANA_VALUE),
        KEYCODE_YEN(KEYCODE_YEN_VALUE),
        KEYCODE_RO(KEYCODE_RO_VALUE),
        KEYCODE_KANA(KEYCODE_KANA_VALUE),
        KEYCODE_ASSIST(KEYCODE_ASSIST_VALUE),
        KEYCODE_BRIGHTNESS_DOWN(KEYCODE_BRIGHTNESS_DOWN_VALUE),
        KEYCODE_BRIGHTNESS_UP(KEYCODE_BRIGHTNESS_UP_VALUE),
        KEYCODE_MEDIA_AUDIO_TRACK(KEYCODE_MEDIA_AUDIO_TRACK_VALUE),
        KEYCODE_SLEEP(KEYCODE_SLEEP_VALUE),
        KEYCODE_WAKEUP(224),
        KEYCODE_PAIRING(225),
        KEYCODE_MEDIA_TOP_MENU(KEYCODE_MEDIA_TOP_MENU_VALUE),
        KEYCODE_11(KEYCODE_11_VALUE),
        KEYCODE_12(KEYCODE_12_VALUE),
        KEYCODE_LAST_CHANNEL(KEYCODE_LAST_CHANNEL_VALUE),
        KEYCODE_TV_DATA_SERVICE(KEYCODE_TV_DATA_SERVICE_VALUE),
        KEYCODE_VOICE_ASSIST(KEYCODE_VOICE_ASSIST_VALUE),
        KEYCODE_TV_RADIO_SERVICE(KEYCODE_TV_RADIO_SERVICE_VALUE),
        KEYCODE_TV_TELETEXT(KEYCODE_TV_TELETEXT_VALUE),
        KEYCODE_TV_NUMBER_ENTRY(KEYCODE_TV_NUMBER_ENTRY_VALUE),
        KEYCODE_TV_TERRESTRIAL_ANALOG(KEYCODE_TV_TERRESTRIAL_ANALOG_VALUE),
        KEYCODE_TV_TERRESTRIAL_DIGITAL(KEYCODE_TV_TERRESTRIAL_DIGITAL_VALUE),
        KEYCODE_TV_SATELLITE(KEYCODE_TV_SATELLITE_VALUE),
        KEYCODE_TV_SATELLITE_BS(KEYCODE_TV_SATELLITE_BS_VALUE),
        KEYCODE_TV_SATELLITE_CS(KEYCODE_TV_SATELLITE_CS_VALUE),
        KEYCODE_TV_SATELLITE_SERVICE(KEYCODE_TV_SATELLITE_SERVICE_VALUE),
        KEYCODE_TV_NETWORK(KEYCODE_TV_NETWORK_VALUE),
        KEYCODE_TV_ANTENNA_CABLE(KEYCODE_TV_ANTENNA_CABLE_VALUE),
        KEYCODE_TV_INPUT_HDMI_1(KEYCODE_TV_INPUT_HDMI_1_VALUE),
        KEYCODE_TV_INPUT_HDMI_2(KEYCODE_TV_INPUT_HDMI_2_VALUE),
        KEYCODE_TV_INPUT_HDMI_3(KEYCODE_TV_INPUT_HDMI_3_VALUE),
        KEYCODE_TV_INPUT_HDMI_4(KEYCODE_TV_INPUT_HDMI_4_VALUE),
        KEYCODE_TV_INPUT_COMPOSITE_1(KEYCODE_TV_INPUT_COMPOSITE_1_VALUE),
        KEYCODE_TV_INPUT_COMPOSITE_2(KEYCODE_TV_INPUT_COMPOSITE_2_VALUE),
        KEYCODE_TV_INPUT_COMPONENT_1(KEYCODE_TV_INPUT_COMPONENT_1_VALUE),
        KEYCODE_TV_INPUT_COMPONENT_2(250),
        KEYCODE_TV_INPUT_VGA_1(KEYCODE_TV_INPUT_VGA_1_VALUE),
        KEYCODE_TV_AUDIO_DESCRIPTION(KEYCODE_TV_AUDIO_DESCRIPTION_VALUE),
        KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP(KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP_VALUE),
        KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN(KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN_VALUE),
        KEYCODE_TV_ZOOM_MODE(255),
        KEYCODE_TV_CONTENTS_MENU(256),
        KEYCODE_TV_MEDIA_CONTEXT_MENU(257),
        KEYCODE_TV_TIMER_PROGRAMMING(258),
        KEYCODE_HELP(259),
        KEYCODE_NAVIGATE_PREVIOUS(260),
        KEYCODE_NAVIGATE_NEXT(261),
        KEYCODE_NAVIGATE_IN(262),
        KEYCODE_NAVIGATE_OUT(263),
        KEYCODE_STEM_PRIMARY(264),
        KEYCODE_STEM_1(KEYCODE_STEM_1_VALUE),
        KEYCODE_STEM_2(KEYCODE_STEM_2_VALUE),
        KEYCODE_STEM_3(KEYCODE_STEM_3_VALUE),
        KEYCODE_DPAD_UP_LEFT(KEYCODE_DPAD_UP_LEFT_VALUE),
        KEYCODE_DPAD_DOWN_LEFT(KEYCODE_DPAD_DOWN_LEFT_VALUE),
        KEYCODE_DPAD_UP_RIGHT(KEYCODE_DPAD_UP_RIGHT_VALUE),
        KEYCODE_DPAD_DOWN_RIGHT(KEYCODE_DPAD_DOWN_RIGHT_VALUE),
        KEYCODE_MEDIA_SKIP_FORWARD(KEYCODE_MEDIA_SKIP_FORWARD_VALUE),
        KEYCODE_MEDIA_SKIP_BACKWARD(KEYCODE_MEDIA_SKIP_BACKWARD_VALUE),
        KEYCODE_MEDIA_STEP_FORWARD(KEYCODE_MEDIA_STEP_FORWARD_VALUE),
        KEYCODE_MEDIA_STEP_BACKWARD(KEYCODE_MEDIA_STEP_BACKWARD_VALUE),
        KEYCODE_SOFT_SLEEP(KEYCODE_SOFT_SLEEP_VALUE),
        KEYCODE_CUT(KEYCODE_CUT_VALUE),
        KEYCODE_COPY(KEYCODE_COPY_VALUE),
        KEYCODE_PASTE(KEYCODE_PASTE_VALUE),
        KEYCODE_SYSTEM_NAVIGATION_UP(KEYCODE_SYSTEM_NAVIGATION_UP_VALUE),
        KEYCODE_SYSTEM_NAVIGATION_DOWN(KEYCODE_SYSTEM_NAVIGATION_DOWN_VALUE),
        KEYCODE_SYSTEM_NAVIGATION_LEFT(KEYCODE_SYSTEM_NAVIGATION_LEFT_VALUE),
        KEYCODE_SYSTEM_NAVIGATION_RIGHT(KEYCODE_SYSTEM_NAVIGATION_RIGHT_VALUE),
        KEYCODE_ALL_APPS(KEYCODE_ALL_APPS_VALUE),
        KEYCODE_REFRESH(KEYCODE_REFRESH_VALUE),
        KEYCODE_THUMBS_UP(KEYCODE_THUMBS_UP_VALUE),
        KEYCODE_THUMBS_DOWN(KEYCODE_THUMBS_DOWN_VALUE),
        KEYCODE_PROFILE_SWITCH(KEYCODE_PROFILE_SWITCH_VALUE),
        KEYCODE_VIDEO_APP_1(KEYCODE_VIDEO_APP_1_VALUE),
        KEYCODE_VIDEO_APP_2(KEYCODE_VIDEO_APP_2_VALUE),
        KEYCODE_VIDEO_APP_3(KEYCODE_VIDEO_APP_3_VALUE),
        KEYCODE_VIDEO_APP_4(KEYCODE_VIDEO_APP_4_VALUE),
        KEYCODE_VIDEO_APP_5(KEYCODE_VIDEO_APP_5_VALUE),
        KEYCODE_VIDEO_APP_6(KEYCODE_VIDEO_APP_6_VALUE),
        KEYCODE_VIDEO_APP_7(KEYCODE_VIDEO_APP_7_VALUE),
        KEYCODE_VIDEO_APP_8(KEYCODE_VIDEO_APP_8_VALUE),
        KEYCODE_FEATURED_APP_1(KEYCODE_FEATURED_APP_1_VALUE),
        KEYCODE_FEATURED_APP_2(KEYCODE_FEATURED_APP_2_VALUE),
        KEYCODE_FEATURED_APP_3(KEYCODE_FEATURED_APP_3_VALUE),
        KEYCODE_FEATURED_APP_4(300),
        KEYCODE_DEMO_APP_1(301),
        KEYCODE_DEMO_APP_2(302),
        KEYCODE_DEMO_APP_3(303),
        KEYCODE_DEMO_APP_4(304),
        UNRECOGNIZED(-1);


        private static final RemoteKeyCode[] VALUES = values();
        private static final Internal.EnumLiteMap<RemoteKeyCode> internalValueMap = new Internal.EnumLiteMap<RemoteKeyCode>() {

            @Override
            public RemoteKeyCode findValueByNumber(int i) {
                return RemoteKeyCode.forNumber(i);
            }
        };
        private final int value;

        private RemoteKeyCode(int i) {
            this.value = i;
        }

        @Deprecated
        public static RemoteKeyCode valueOf(int i) {
            return forNumber(i);
        }

        public static RemoteKeyCode forNumber(int i) {
            switch (i) {
                case 0:
                    return KEYCODE_UNKNOWN;
                case 1:
                    return KEYCODE_SOFT_LEFT;
                case 2:
                    return KEYCODE_SOFT_RIGHT;
                case 3:
                    return KEYCODE_HOME;
                case 4:
                    return KEYCODE_BACK;
                case 5:
                    return KEYCODE_CALL;
                case 6:
                    return KEYCODE_ENDCALL;
                case 7:
                    return KEYCODE_0;
                case 8:
                    return KEYCODE_1;
                case 9:
                    return KEYCODE_2;
                case 10:
                    return KEYCODE_3;
                case 11:
                    return KEYCODE_4;
                case 12:
                    return KEYCODE_5;
                case 13:
                    return KEYCODE_6;
                case 14:
                    return KEYCODE_7;
                case 15:
                    return KEYCODE_8;
                case 16:
                    return KEYCODE_9;
                case 17:
                    return KEYCODE_STAR;
                case 18:
                    return KEYCODE_POUND;
                case 19:
                    return KEYCODE_DPAD_UP;
                case 20:
                    return KEYCODE_DPAD_DOWN;
                case 21:
                    return KEYCODE_DPAD_LEFT;
                case 22:
                    return KEYCODE_DPAD_RIGHT;
                case 23:
                    return KEYCODE_DPAD_CENTER;
                case 24:
                    return KEYCODE_VOLUME_UP;
                case 25:
                    return KEYCODE_VOLUME_DOWN;
                case 26:
                    return KEYCODE_POWER;
                case 27:
                    return KEYCODE_CAMERA;
                case 28:
                    return KEYCODE_CLEAR;
                case 29:
                    return KEYCODE_A;
                case 30:
                    return KEYCODE_B;
                case 31:
                    return KEYCODE_C;
                case 32:
                    return KEYCODE_D;
                case 33:
                    return KEYCODE_E;
                case 34:
                    return KEYCODE_F;
                case 35:
                    return KEYCODE_G;
                case 36:
                    return KEYCODE_H;
                case 37:
                    return KEYCODE_I;
                case 38:
                    return KEYCODE_J;
                case 39:
                    return KEYCODE_K;
                case 40:
                    return KEYCODE_L;
                case 41:
                    return KEYCODE_M;
                case 42:
                    return KEYCODE_N;
                case 43:
                    return KEYCODE_O;
                case 44:
                    return KEYCODE_P;
                case 45:
                    return KEYCODE_Q;
                case 46:
                    return KEYCODE_R;
                case 47:
                    return KEYCODE_S;
                case 48:
                    return KEYCODE_T;
                case 49:
                    return KEYCODE_U;
                case 50:
                    return KEYCODE_V;
                case 51:
                    return KEYCODE_W;
                case 52:
                    return KEYCODE_X;
                case 53:
                    return KEYCODE_Y;
                case 54:
                    return KEYCODE_Z;
                case 55:
                    return KEYCODE_COMMA;
                case 56:
                    return KEYCODE_PERIOD;
                case 57:
                    return KEYCODE_ALT_LEFT;
                case 58:
                    return KEYCODE_ALT_RIGHT;
                case 59:
                    return KEYCODE_SHIFT_LEFT;
                case 60:
                    return KEYCODE_SHIFT_RIGHT;
                case 61:
                    return KEYCODE_TAB;
                case 62:
                    return KEYCODE_SPACE;
                case 63:
                    return KEYCODE_SYM;
                case 64:
                    return KEYCODE_EXPLORER;
                case 65:
                    return KEYCODE_ENVELOPE;
                case 66:
                    return KEYCODE_ENTER;
                case 67:
                    return KEYCODE_DEL;
                case 68:
                    return KEYCODE_GRAVE;
                case 69:
                    return KEYCODE_MINUS;
                case 70:
                    return KEYCODE_EQUALS;
                case 71:
                    return KEYCODE_LEFT_BRACKET;
                case 72:
                    return KEYCODE_RIGHT_BRACKET;
                case 73:
                    return KEYCODE_BACKSLASH;
                case 74:
                    return KEYCODE_SEMICOLON;
                case 75:
                    return KEYCODE_APOSTROPHE;
                case 76:
                    return KEYCODE_SLASH;
                case 77:
                    return KEYCODE_AT;
                case 78:
                    return KEYCODE_NUM;
                case 79:
                    return KEYCODE_HEADSETHOOK;
                case 80:
                    return KEYCODE_FOCUS;
                case 81:
                    return KEYCODE_PLUS;
                case 82:
                    return KEYCODE_MENU;
                case 83:
                    return KEYCODE_NOTIFICATION;
                case 84:
                    return KEYCODE_SEARCH;
                case 85:
                    return KEYCODE_MEDIA_PLAY_PAUSE;
                case 86:
                    return KEYCODE_MEDIA_STOP;
                case 87:
                    return KEYCODE_MEDIA_NEXT;
                case 88:
                    return KEYCODE_MEDIA_PREVIOUS;
                case 89:
                    return KEYCODE_MEDIA_REWIND;
                case 90:
                    return KEYCODE_MEDIA_FAST_FORWARD;
                case 91:
                    return KEYCODE_MUTE;
                case 92:
                    return KEYCODE_PAGE_UP;
                case 93:
                    return KEYCODE_PAGE_DOWN;
                case 94:
                    return KEYCODE_PICTSYMBOLS;
                case 95:
                    return KEYCODE_SWITCH_CHARSET;
                case 96:
                    return KEYCODE_BUTTON_A;
                case 97:
                    return KEYCODE_BUTTON_B;
                case 98:
                    return KEYCODE_BUTTON_C;
                case 99:
                    return KEYCODE_BUTTON_X;
                case 100:
                    return KEYCODE_BUTTON_Y;
                case 101:
                    return KEYCODE_BUTTON_Z;
                case 102:
                    return KEYCODE_BUTTON_L1;
                case 103:
                    return KEYCODE_BUTTON_R1;
                case 104:
                    return KEYCODE_BUTTON_L2;
                case 105:
                    return KEYCODE_BUTTON_R2;
                case 106:
                    return KEYCODE_BUTTON_THUMBL;
                case 107:
                    return KEYCODE_BUTTON_THUMBR;
                case 108:
                    return KEYCODE_BUTTON_START;
                case 109:
                    return KEYCODE_BUTTON_SELECT;
                case 110:
                    return KEYCODE_BUTTON_MODE;
                case 111:
                    return KEYCODE_ESCAPE;
                case 112:
                    return KEYCODE_FORWARD_DEL;
                case 113:
                    return KEYCODE_CTRL_LEFT;
                case 114:
                    return KEYCODE_CTRL_RIGHT;
                case 115:
                    return KEYCODE_CAPS_LOCK;
                case 116:
                    return KEYCODE_SCROLL_LOCK;
                case 117:
                    return KEYCODE_META_LEFT;
                case 118:
                    return KEYCODE_META_RIGHT;
                case 119:
                    return KEYCODE_FUNCTION;
                case 120:
                    return KEYCODE_SYSRQ;
                case 121:
                    return KEYCODE_BREAK;
                case 122:
                    return KEYCODE_MOVE_HOME;
                case 123:
                    return KEYCODE_MOVE_END;
                case 124:
                    return KEYCODE_INSERT;
                case 125:
                    return KEYCODE_FORWARD;
                case 126:
                    return KEYCODE_MEDIA_PLAY;
                case 127:
                    return KEYCODE_MEDIA_PAUSE;
                case 128:
                    return KEYCODE_MEDIA_CLOSE;
                case KEYCODE_MEDIA_EJECT_VALUE:
                    return KEYCODE_MEDIA_EJECT;
                case KEYCODE_MEDIA_RECORD_VALUE:
                    return KEYCODE_MEDIA_RECORD;
                case KEYCODE_F1_VALUE:
                    return KEYCODE_F1;
                case KEYCODE_F2_VALUE:
                    return KEYCODE_F2;
                case KEYCODE_F3_VALUE:
                    return KEYCODE_F3;
                case KEYCODE_F4_VALUE:
                    return KEYCODE_F4;
                case KEYCODE_F5_VALUE:
                    return KEYCODE_F5;
                case KEYCODE_F6_VALUE:
                    return KEYCODE_F6;
                case KEYCODE_F7_VALUE:
                    return KEYCODE_F7;
                case KEYCODE_F8_VALUE:
                    return KEYCODE_F8;
                case KEYCODE_F9_VALUE:
                    return KEYCODE_F9;
                case KEYCODE_F10_VALUE:
                    return KEYCODE_F10;
                case KEYCODE_F11_VALUE:
                    return KEYCODE_F11;
                case KEYCODE_F12_VALUE:
                    return KEYCODE_F12;
                case KEYCODE_NUM_LOCK_VALUE:
                    return KEYCODE_NUM_LOCK;
                case KEYCODE_NUMPAD_0_VALUE:
                    return KEYCODE_NUMPAD_0;
                case KEYCODE_NUMPAD_1_VALUE:
                    return KEYCODE_NUMPAD_1;
                case KEYCODE_NUMPAD_2_VALUE:
                    return KEYCODE_NUMPAD_2;
                case KEYCODE_NUMPAD_3_VALUE:
                    return KEYCODE_NUMPAD_3;
                case KEYCODE_NUMPAD_4_VALUE:
                    return KEYCODE_NUMPAD_4;
                case KEYCODE_NUMPAD_5_VALUE:
                    return KEYCODE_NUMPAD_5;
                case KEYCODE_NUMPAD_6_VALUE:
                    return KEYCODE_NUMPAD_6;
                case KEYCODE_NUMPAD_7_VALUE:
                    return KEYCODE_NUMPAD_7;
                case KEYCODE_NUMPAD_8_VALUE:
                    return KEYCODE_NUMPAD_8;
                case KEYCODE_NUMPAD_9_VALUE:
                    return KEYCODE_NUMPAD_9;
                case KEYCODE_NUMPAD_DIVIDE_VALUE:
                    return KEYCODE_NUMPAD_DIVIDE;
                case KEYCODE_NUMPAD_MULTIPLY_VALUE:
                    return KEYCODE_NUMPAD_MULTIPLY;
                case KEYCODE_NUMPAD_SUBTRACT_VALUE:
                    return KEYCODE_NUMPAD_SUBTRACT;
                case KEYCODE_NUMPAD_ADD_VALUE:
                    return KEYCODE_NUMPAD_ADD;
                case KEYCODE_NUMPAD_DOT_VALUE:
                    return KEYCODE_NUMPAD_DOT;
                case KEYCODE_NUMPAD_COMMA_VALUE:
                    return KEYCODE_NUMPAD_COMMA;
                case KEYCODE_NUMPAD_ENTER_VALUE:
                    return KEYCODE_NUMPAD_ENTER;
                case KEYCODE_NUMPAD_EQUALS_VALUE:
                    return KEYCODE_NUMPAD_EQUALS;
                case KEYCODE_NUMPAD_LEFT_PAREN_VALUE:
                    return KEYCODE_NUMPAD_LEFT_PAREN;
                case KEYCODE_NUMPAD_RIGHT_PAREN_VALUE:
                    return KEYCODE_NUMPAD_RIGHT_PAREN;
                case KEYCODE_VOLUME_MUTE_VALUE:
                    return KEYCODE_VOLUME_MUTE;
                case KEYCODE_INFO_VALUE:
                    return KEYCODE_INFO;
                case KEYCODE_CHANNEL_UP_VALUE:
                    return KEYCODE_CHANNEL_UP;
                case KEYCODE_CHANNEL_DOWN_VALUE:
                    return KEYCODE_CHANNEL_DOWN;
                case KEYCODE_ZOOM_IN_VALUE:
                    return KEYCODE_ZOOM_IN;
                case KEYCODE_ZOOM_OUT_VALUE:
                    return KEYCODE_ZOOM_OUT;
                case KEYCODE_TV_VALUE:
                    return KEYCODE_TV;
                case KEYCODE_WINDOW_VALUE:
                    return KEYCODE_WINDOW;
                case KEYCODE_GUIDE_VALUE:
                    return KEYCODE_GUIDE;
                case KEYCODE_DVR_VALUE:
                    return KEYCODE_DVR;
                case KEYCODE_BOOKMARK_VALUE:
                    return KEYCODE_BOOKMARK;
                case KEYCODE_CAPTIONS_VALUE:
                    return KEYCODE_CAPTIONS;
                case KEYCODE_SETTINGS_VALUE:
                    return KEYCODE_SETTINGS;
                case KEYCODE_TV_POWER_VALUE:
                    return KEYCODE_TV_POWER;
                case KEYCODE_TV_INPUT_VALUE:
                    return KEYCODE_TV_INPUT;
                case KEYCODE_STB_POWER_VALUE:
                    return KEYCODE_STB_POWER;
                case KEYCODE_STB_INPUT_VALUE:
                    return KEYCODE_STB_INPUT;
                case KEYCODE_AVR_POWER_VALUE:
                    return KEYCODE_AVR_POWER;
                case KEYCODE_AVR_INPUT_VALUE:
                    return KEYCODE_AVR_INPUT;
                case KEYCODE_PROG_RED_VALUE:
                    return KEYCODE_PROG_RED;
                case KEYCODE_PROG_GREEN_VALUE:
                    return KEYCODE_PROG_GREEN;
                case KEYCODE_PROG_YELLOW_VALUE:
                    return KEYCODE_PROG_YELLOW;
                case KEYCODE_PROG_BLUE_VALUE:
                    return KEYCODE_PROG_BLUE;
                case KEYCODE_APP_SWITCH_VALUE:
                    return KEYCODE_APP_SWITCH;
                case 188:
                    return KEYCODE_BUTTON_1;
                case KEYCODE_BUTTON_2_VALUE:
                    return KEYCODE_BUTTON_2;
                case 190:
                    return KEYCODE_BUTTON_3;
                case KEYCODE_BUTTON_4_VALUE:
                    return KEYCODE_BUTTON_4;
                case 192:
                    return KEYCODE_BUTTON_5;
                case KEYCODE_BUTTON_6_VALUE:
                    return KEYCODE_BUTTON_6;
                case KEYCODE_BUTTON_7_VALUE:
                    return KEYCODE_BUTTON_7;
                case KEYCODE_BUTTON_8_VALUE:
                    return KEYCODE_BUTTON_8;
                case KEYCODE_BUTTON_9_VALUE:
                    return KEYCODE_BUTTON_9;
                case KEYCODE_BUTTON_10_VALUE:
                    return KEYCODE_BUTTON_10;
                case KEYCODE_BUTTON_11_VALUE:
                    return KEYCODE_BUTTON_11;
                case KEYCODE_BUTTON_12_VALUE:
                    return KEYCODE_BUTTON_12;
                case 200:
                    return KEYCODE_BUTTON_13;
                case 201:
                    return KEYCODE_BUTTON_14;
                case 202:
                    return KEYCODE_BUTTON_15;
                case 203:
                    return KEYCODE_BUTTON_16;
                case KEYCODE_LANGUAGE_SWITCH_VALUE:
                    return KEYCODE_LANGUAGE_SWITCH;
                case KEYCODE_MANNER_MODE_VALUE:
                    return KEYCODE_MANNER_MODE;
                case KEYCODE_3D_MODE_VALUE:
                    return KEYCODE_3D_MODE;
                case KEYCODE_CONTACTS_VALUE:
                    return KEYCODE_CONTACTS;
                case KEYCODE_CALENDAR_VALUE:
                    return KEYCODE_CALENDAR;
                case KEYCODE_MUSIC_VALUE:
                    return KEYCODE_MUSIC;
                case KEYCODE_CALCULATOR_VALUE:
                    return KEYCODE_CALCULATOR;
                case 211:
                    return KEYCODE_ZENKAKU_HANKAKU;
                case KEYCODE_EISU_VALUE:
                    return KEYCODE_EISU;
                case KEYCODE_MUHENKAN_VALUE:
                    return KEYCODE_MUHENKAN;
                case KEYCODE_HENKAN_VALUE:
                    return KEYCODE_HENKAN;
                case KEYCODE_KATAKANA_HIRAGANA_VALUE:
                    return KEYCODE_KATAKANA_HIRAGANA;
                case KEYCODE_YEN_VALUE:
                    return KEYCODE_YEN;
                case KEYCODE_RO_VALUE:
                    return KEYCODE_RO;
                case KEYCODE_KANA_VALUE:
                    return KEYCODE_KANA;
                case KEYCODE_ASSIST_VALUE:
                    return KEYCODE_ASSIST;
                case KEYCODE_BRIGHTNESS_DOWN_VALUE:
                    return KEYCODE_BRIGHTNESS_DOWN;
                case KEYCODE_BRIGHTNESS_UP_VALUE:
                    return KEYCODE_BRIGHTNESS_UP;
                case KEYCODE_MEDIA_AUDIO_TRACK_VALUE:
                    return KEYCODE_MEDIA_AUDIO_TRACK;
                case KEYCODE_SLEEP_VALUE:
                    return KEYCODE_SLEEP;
                case 224:
                    return KEYCODE_WAKEUP;
                case 225:
                    return KEYCODE_PAIRING;
                case KEYCODE_MEDIA_TOP_MENU_VALUE:
                    return KEYCODE_MEDIA_TOP_MENU;
                case KEYCODE_11_VALUE:
                    return KEYCODE_11;
                case KEYCODE_12_VALUE:
                    return KEYCODE_12;
                case KEYCODE_LAST_CHANNEL_VALUE:
                    return KEYCODE_LAST_CHANNEL;
                case KEYCODE_TV_DATA_SERVICE_VALUE:
                    return KEYCODE_TV_DATA_SERVICE;
                case KEYCODE_VOICE_ASSIST_VALUE:
                    return KEYCODE_VOICE_ASSIST;
                case KEYCODE_TV_RADIO_SERVICE_VALUE:
                    return KEYCODE_TV_RADIO_SERVICE;
                case KEYCODE_TV_TELETEXT_VALUE:
                    return KEYCODE_TV_TELETEXT;
                case KEYCODE_TV_NUMBER_ENTRY_VALUE:
                    return KEYCODE_TV_NUMBER_ENTRY;
                case KEYCODE_TV_TERRESTRIAL_ANALOG_VALUE:
                    return KEYCODE_TV_TERRESTRIAL_ANALOG;
                case KEYCODE_TV_TERRESTRIAL_DIGITAL_VALUE:
                    return KEYCODE_TV_TERRESTRIAL_DIGITAL;
                case KEYCODE_TV_SATELLITE_VALUE:
                    return KEYCODE_TV_SATELLITE;
                case KEYCODE_TV_SATELLITE_BS_VALUE:
                    return KEYCODE_TV_SATELLITE_BS;
                case KEYCODE_TV_SATELLITE_CS_VALUE:
                    return KEYCODE_TV_SATELLITE_CS;
                case KEYCODE_TV_SATELLITE_SERVICE_VALUE:
                    return KEYCODE_TV_SATELLITE_SERVICE;
                case KEYCODE_TV_NETWORK_VALUE:
                    return KEYCODE_TV_NETWORK;
                case KEYCODE_TV_ANTENNA_CABLE_VALUE:
                    return KEYCODE_TV_ANTENNA_CABLE;
                case KEYCODE_TV_INPUT_HDMI_1_VALUE:
                    return KEYCODE_TV_INPUT_HDMI_1;
                case KEYCODE_TV_INPUT_HDMI_2_VALUE:
                    return KEYCODE_TV_INPUT_HDMI_2;
                case KEYCODE_TV_INPUT_HDMI_3_VALUE:
                    return KEYCODE_TV_INPUT_HDMI_3;
                case KEYCODE_TV_INPUT_HDMI_4_VALUE:
                    return KEYCODE_TV_INPUT_HDMI_4;
                case KEYCODE_TV_INPUT_COMPOSITE_1_VALUE:
                    return KEYCODE_TV_INPUT_COMPOSITE_1;
                case KEYCODE_TV_INPUT_COMPOSITE_2_VALUE:
                    return KEYCODE_TV_INPUT_COMPOSITE_2;
                case KEYCODE_TV_INPUT_COMPONENT_1_VALUE:
                    return KEYCODE_TV_INPUT_COMPONENT_1;
                case 250:
                    return KEYCODE_TV_INPUT_COMPONENT_2;
                case KEYCODE_TV_INPUT_VGA_1_VALUE:
                    return KEYCODE_TV_INPUT_VGA_1;
                case KEYCODE_TV_AUDIO_DESCRIPTION_VALUE:
                    return KEYCODE_TV_AUDIO_DESCRIPTION;
                case KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP_VALUE:
                    return KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP;
                case KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN_VALUE:
                    return KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN;
                case 255:
                    return KEYCODE_TV_ZOOM_MODE;
                case 256:
                    return KEYCODE_TV_CONTENTS_MENU;
                case 257:
                    return KEYCODE_TV_MEDIA_CONTEXT_MENU;
                case 258:
                    return KEYCODE_TV_TIMER_PROGRAMMING;
                case 259:
                    return KEYCODE_HELP;
                case 260:
                    return KEYCODE_NAVIGATE_PREVIOUS;
                case 261:
                    return KEYCODE_NAVIGATE_NEXT;
                case 262:
                    return KEYCODE_NAVIGATE_IN;
                case 263:
                    return KEYCODE_NAVIGATE_OUT;
                case 264:
                    return KEYCODE_STEM_PRIMARY;
                case KEYCODE_STEM_1_VALUE:
                    return KEYCODE_STEM_1;
                case KEYCODE_STEM_2_VALUE:
                    return KEYCODE_STEM_2;
                case KEYCODE_STEM_3_VALUE:
                    return KEYCODE_STEM_3;
                case KEYCODE_DPAD_UP_LEFT_VALUE:
                    return KEYCODE_DPAD_UP_LEFT;
                case KEYCODE_DPAD_DOWN_LEFT_VALUE:
                    return KEYCODE_DPAD_DOWN_LEFT;
                case KEYCODE_DPAD_UP_RIGHT_VALUE:
                    return KEYCODE_DPAD_UP_RIGHT;
                case KEYCODE_DPAD_DOWN_RIGHT_VALUE:
                    return KEYCODE_DPAD_DOWN_RIGHT;
                case KEYCODE_MEDIA_SKIP_FORWARD_VALUE:
                    return KEYCODE_MEDIA_SKIP_FORWARD;
                case KEYCODE_MEDIA_SKIP_BACKWARD_VALUE:
                    return KEYCODE_MEDIA_SKIP_BACKWARD;
                case KEYCODE_MEDIA_STEP_FORWARD_VALUE:
                    return KEYCODE_MEDIA_STEP_FORWARD;
                case KEYCODE_MEDIA_STEP_BACKWARD_VALUE:
                    return KEYCODE_MEDIA_STEP_BACKWARD;
                case KEYCODE_SOFT_SLEEP_VALUE:
                    return KEYCODE_SOFT_SLEEP;
                case KEYCODE_CUT_VALUE:
                    return KEYCODE_CUT;
                case KEYCODE_COPY_VALUE:
                    return KEYCODE_COPY;
                case KEYCODE_PASTE_VALUE:
                    return KEYCODE_PASTE;
                case KEYCODE_SYSTEM_NAVIGATION_UP_VALUE:
                    return KEYCODE_SYSTEM_NAVIGATION_UP;
                case KEYCODE_SYSTEM_NAVIGATION_DOWN_VALUE:
                    return KEYCODE_SYSTEM_NAVIGATION_DOWN;
                case KEYCODE_SYSTEM_NAVIGATION_LEFT_VALUE:
                    return KEYCODE_SYSTEM_NAVIGATION_LEFT;
                case KEYCODE_SYSTEM_NAVIGATION_RIGHT_VALUE:
                    return KEYCODE_SYSTEM_NAVIGATION_RIGHT;
                case KEYCODE_ALL_APPS_VALUE:
                    return KEYCODE_ALL_APPS;
                case KEYCODE_REFRESH_VALUE:
                    return KEYCODE_REFRESH;
                case KEYCODE_THUMBS_UP_VALUE:
                    return KEYCODE_THUMBS_UP;
                case KEYCODE_THUMBS_DOWN_VALUE:
                    return KEYCODE_THUMBS_DOWN;
                case KEYCODE_PROFILE_SWITCH_VALUE:
                    return KEYCODE_PROFILE_SWITCH;
                case KEYCODE_VIDEO_APP_1_VALUE:
                    return KEYCODE_VIDEO_APP_1;
                case KEYCODE_VIDEO_APP_2_VALUE:
                    return KEYCODE_VIDEO_APP_2;
                case KEYCODE_VIDEO_APP_3_VALUE:
                    return KEYCODE_VIDEO_APP_3;
                case KEYCODE_VIDEO_APP_4_VALUE:
                    return KEYCODE_VIDEO_APP_4;
                case KEYCODE_VIDEO_APP_5_VALUE:
                    return KEYCODE_VIDEO_APP_5;
                case KEYCODE_VIDEO_APP_6_VALUE:
                    return KEYCODE_VIDEO_APP_6;
                case KEYCODE_VIDEO_APP_7_VALUE:
                    return KEYCODE_VIDEO_APP_7;
                case KEYCODE_VIDEO_APP_8_VALUE:
                    return KEYCODE_VIDEO_APP_8;
                case KEYCODE_FEATURED_APP_1_VALUE:
                    return KEYCODE_FEATURED_APP_1;
                case KEYCODE_FEATURED_APP_2_VALUE:
                    return KEYCODE_FEATURED_APP_2;
                case KEYCODE_FEATURED_APP_3_VALUE:
                    return KEYCODE_FEATURED_APP_3;
                case 300:
                    return KEYCODE_FEATURED_APP_4;
                case 301:
                    return KEYCODE_DEMO_APP_1;
                case 302:
                    return KEYCODE_DEMO_APP_2;
                case 303:
                    return KEYCODE_DEMO_APP_3;
                case 304:
                    return KEYCODE_DEMO_APP_4;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<RemoteKeyCode> internalGetValueMap() {
            return internalValueMap;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Remotemessage.getDescriptor().getEnumTypes().get(0);
        }

        public static RemoteKeyCode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public enum RemoteDirection implements ProtocolMessageEnum {
        UNKNOWN_DIRECTION(0),
        START_LONG(1),
        END_LONG(2),
        SHORT(3),
        UNRECOGNIZED(-1);

        public static final int END_LONG_VALUE = 2;
        public static final int SHORT_VALUE = 3;
        public static final int START_LONG_VALUE = 1;
        public static final int UNKNOWN_DIRECTION_VALUE = 0;
        private static final RemoteDirection[] VALUES = values();
        private static final Internal.EnumLiteMap<RemoteDirection> internalValueMap = new Internal.EnumLiteMap<RemoteDirection>() {

            @Override
            public RemoteDirection findValueByNumber(int i) {
                return RemoteDirection.forNumber(i);
            }
        };
        private final int value;

        private RemoteDirection(int i) {
            this.value = i;
        }

        @Deprecated
        public static RemoteDirection valueOf(int i) {
            return forNumber(i);
        }

        public static RemoteDirection forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_DIRECTION;
            }
            if (i == 1) {
                return START_LONG;
            }
            if (i == 2) {
                return END_LONG;
            }
            if (i != 3) {
                return null;
            }
            return SHORT;
        }

        public static Internal.EnumLiteMap<RemoteDirection> internalGetValueMap() {
            return internalValueMap;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Remotemessage.getDescriptor().getEnumTypes().get(1);
        }

        public static RemoteDirection valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public interface RemoteAdjustVolumeLevelOrBuilder extends MessageOrBuilder {
    }

    public interface RemoteAppInfoOrBuilder extends MessageOrBuilder {
        String getAppPackage();

        ByteString getAppPackageBytes();

        int getCounter();

        int getInt13();

        int getInt2();

        int getInt3();

        String getInt4();

        ByteString getInt4Bytes();

        int getInt7();

        int getInt8();

        String getLabel();

        ByteString getLabelBytes();
    }

    public interface RemoteAppLinkLaunchRequestOrBuilder extends MessageOrBuilder {
        String getAppLink();

        ByteString getAppLinkBytes();
    }

    public interface RemoteConfigureOrBuilder extends MessageOrBuilder {
        int getCode1();

        RemoteDeviceInfo getDeviceInfo();

        RemoteDeviceInfoOrBuilder getDeviceInfoOrBuilder();

        boolean hasDeviceInfo();
    }

    public interface RemoteDeviceInfoOrBuilder extends MessageOrBuilder {
        String getAppVersion();

        ByteString getAppVersionBytes();

        String getModel();

        ByteString getModelBytes();

        String getPackageName();

        ByteString getPackageNameBytes();

        int getUnknown1();

        String getUnknown2();

        ByteString getUnknown2Bytes();

        String getVendor();

        ByteString getVendorBytes();
    }

    public interface RemoteEditInfoOrBuilder extends MessageOrBuilder {
        int getInsert();
    }

    public interface RemoteErrorOrBuilder extends MessageOrBuilder {
        RemoteMessage getMessage();

        RemoteMessageOrBuilder getMessageOrBuilder();

        boolean getValue();

        boolean hasMessage();
    }

    public interface RemoteImeBatchEditOrBuilder extends MessageOrBuilder {
        RemoteEditInfo getEditInfo();

        RemoteEditInfoOrBuilder getEditInfoOrBuilder();

        int getFieldCounter();

        int getImeCounter();

        boolean hasEditInfo();
    }

    public interface RemoteImeKeyInjectOrBuilder extends MessageOrBuilder {
        RemoteAppInfo getAppInfo();

        RemoteAppInfoOrBuilder getAppInfoOrBuilder();

        RemoteTextFieldStatus getTextFieldStatus();

        RemoteTextFieldStatusOrBuilder getTextFieldStatusOrBuilder();

        boolean hasAppInfo();

        boolean hasTextFieldStatus();
    }

    public interface RemoteImeShowRequestOrBuilder extends MessageOrBuilder {
        RemoteTextFieldStatus getRemoteTextFieldStatus();

        RemoteTextFieldStatusOrBuilder getRemoteTextFieldStatusOrBuilder();

        boolean hasRemoteTextFieldStatus();
    }

    public interface RemoteKeyInjectOrBuilder extends MessageOrBuilder {
        RemoteDirection getDirection();

        int getDirectionValue();

        RemoteKeyCode getKeyCode();

        int getKeyCodeValue();
    }

    public interface RemoteMessageOrBuilder extends MessageOrBuilder {
        RemoteAdjustVolumeLevel getRemoteAdjustVolumeLevel();

        RemoteAdjustVolumeLevelOrBuilder getRemoteAdjustVolumeLevelOrBuilder();

        RemoteAppLinkLaunchRequest getRemoteAppLinkLaunchRequest();

        RemoteAppLinkLaunchRequestOrBuilder getRemoteAppLinkLaunchRequestOrBuilder();

        RemoteConfigure getRemoteConfigure();

        RemoteConfigureOrBuilder getRemoteConfigureOrBuilder();

        RemoteError getRemoteError();

        RemoteErrorOrBuilder getRemoteErrorOrBuilder();

        RemoteImeBatchEdit getRemoteImeBatchEdit();

        RemoteImeBatchEditOrBuilder getRemoteImeBatchEditOrBuilder();

        RemoteImeKeyInject getRemoteImeKeyInject();

        RemoteImeKeyInjectOrBuilder getRemoteImeKeyInjectOrBuilder();

        RemoteImeShowRequest getRemoteImeShowRequest();

        RemoteImeShowRequestOrBuilder getRemoteImeShowRequestOrBuilder();

        RemoteKeyInject getRemoteKeyInject();

        RemoteKeyInjectOrBuilder getRemoteKeyInjectOrBuilder();

        RemotePingRequest getRemotePingRequest();

        RemotePingRequestOrBuilder getRemotePingRequestOrBuilder();

        RemotePingResponse getRemotePingResponse();

        RemotePingResponseOrBuilder getRemotePingResponseOrBuilder();

        RemoteResetPreferredAudioDevice getRemoteResetPreferredAudioDevice();

        RemoteResetPreferredAudioDeviceOrBuilder getRemoteResetPreferredAudioDeviceOrBuilder();

        RemoteSetActive getRemoteSetActive();

        RemoteSetActiveOrBuilder getRemoteSetActiveOrBuilder();

        RemoteSetPreferredAudioDevice getRemoteSetPreferredAudioDevice();

        RemoteSetPreferredAudioDeviceOrBuilder getRemoteSetPreferredAudioDeviceOrBuilder();

        RemoteSetVolumeLevel getRemoteSetVolumeLevel();

        RemoteSetVolumeLevelOrBuilder getRemoteSetVolumeLevelOrBuilder();

        RemoteStart getRemoteStart();

        RemoteStartOrBuilder getRemoteStartOrBuilder();

        RemoteVoiceBegin getRemoteVoiceBegin();

        RemoteVoiceBeginOrBuilder getRemoteVoiceBeginOrBuilder();

        RemoteVoiceEnd getRemoteVoiceEnd();

        RemoteVoiceEndOrBuilder getRemoteVoiceEndOrBuilder();

        RemoteVoicePayload getRemoteVoicePayload();

        RemoteVoicePayloadOrBuilder getRemoteVoicePayloadOrBuilder();

        boolean hasRemoteAdjustVolumeLevel();

        boolean hasRemoteAppLinkLaunchRequest();

        boolean hasRemoteConfigure();

        boolean hasRemoteError();

        boolean hasRemoteImeBatchEdit();

        boolean hasRemoteImeKeyInject();

        boolean hasRemoteImeShowRequest();

        boolean hasRemoteKeyInject();

        boolean hasRemotePingRequest();

        boolean hasRemotePingResponse();

        boolean hasRemoteResetPreferredAudioDevice();

        boolean hasRemoteSetActive();

        boolean hasRemoteSetPreferredAudioDevice();

        boolean hasRemoteSetVolumeLevel();

        boolean hasRemoteStart();

        boolean hasRemoteVoiceBegin();

        boolean hasRemoteVoiceEnd();

        boolean hasRemoteVoicePayload();
    }

    public interface RemotePingRequestOrBuilder extends MessageOrBuilder {
        int getVal1();

        int getVal2();
    }

    public interface RemotePingResponseOrBuilder extends MessageOrBuilder {
        int getVal1();
    }

    public interface RemoteResetPreferredAudioDeviceOrBuilder extends MessageOrBuilder {
    }

    public interface RemoteSetActiveOrBuilder extends MessageOrBuilder {
        int getActive();
    }

    public interface RemoteSetPreferredAudioDeviceOrBuilder extends MessageOrBuilder {
    }

    public interface RemoteSetVolumeLevelOrBuilder extends MessageOrBuilder {
        String getPlayerModel();

        ByteString getPlayerModelBytes();

        int getUnknown1();

        int getUnknown2();

        int getUnknown4();

        int getUnknown5();

        int getVolumeLevel();

        int getVolumeMax();

        boolean getVolumeMuted();
    }

    public interface RemoteStartOrBuilder extends MessageOrBuilder {
        boolean getStarted();
    }

    public interface RemoteTextFieldStatusOrBuilder extends MessageOrBuilder {
        int getCounterField();

        int getEnd();

        int getInt5();

        String getLabel();

        ByteString getLabelBytes();

        int getStart();

        String getValue();

        ByteString getValueBytes();
    }

    public interface RemoteVoiceBeginOrBuilder extends MessageOrBuilder {
    }

    public interface RemoteVoiceEndOrBuilder extends MessageOrBuilder {
    }

    public interface RemoteVoicePayloadOrBuilder extends MessageOrBuilder {
    }

    public static final class RemoteAppLinkLaunchRequest extends GeneratedMessageV3 implements RemoteAppLinkLaunchRequestOrBuilder {
        public static final int APP_LINK_FIELD_NUMBER = 1;
        private static final RemoteAppLinkLaunchRequest DEFAULT_INSTANCE = new RemoteAppLinkLaunchRequest();
        private static final Parser<RemoteAppLinkLaunchRequest> PARSER = new AbstractParser<RemoteAppLinkLaunchRequest>() {

            @Override
            public RemoteAppLinkLaunchRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteAppLinkLaunchRequest(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object appLink_;
        private byte memoizedIsInitialized;

        private RemoteAppLinkLaunchRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteAppLinkLaunchRequest() {
            this.memoizedIsInitialized = -1;
            this.appLink_ = "";
        }


        //        private RemoteAppLinkLaunchRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 10) {
//                            this.appLink_ = codedInputStream.readStringRequireUtf8();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteAppLinkLaunchRequest(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            String s = input.readStringRequireUtf8();

                            appLink_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_descriptor;
        }

        public static RemoteAppLinkLaunchRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(InputStream inputStream) throws IOException {
            return (RemoteAppLinkLaunchRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAppLinkLaunchRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteAppLinkLaunchRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteAppLinkLaunchRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteAppLinkLaunchRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAppLinkLaunchRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteAppLinkLaunchRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteAppLinkLaunchRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAppLinkLaunchRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteAppLinkLaunchRequest);
        }

        public static RemoteAppLinkLaunchRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteAppLinkLaunchRequest> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteAppLinkLaunchRequest();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteAppLinkLaunchRequest.class, Builder.class);
        }

        @Override
        public String getAppLink() {
            Object obj = this.appLink_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.appLink_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getAppLinkBytes() {
            Object obj = this.appLink_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.appLink_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getAppLinkBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.appLink_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getAppLinkBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.appLink_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteAppLinkLaunchRequest)) {
                return super.equals(obj);
            }
            RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest = (RemoteAppLinkLaunchRequest) obj;
            if (getAppLink().equals(remoteAppLinkLaunchRequest.getAppLink()) && this.unknownFields.equals(remoteAppLinkLaunchRequest.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAppLink().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteAppLinkLaunchRequest> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteAppLinkLaunchRequest getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteAppLinkLaunchRequestOrBuilder {
            private Object appLink_;

            private Builder() {
                this.appLink_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.appLink_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteAppLinkLaunchRequest.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteAppLinkLaunchRequest.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.appLink_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAppLinkLaunchRequest_descriptor;
            }

            @Override
            public RemoteAppLinkLaunchRequest getDefaultInstanceForType() {
                return RemoteAppLinkLaunchRequest.getDefaultInstance();
            }

            @Override
            public RemoteAppLinkLaunchRequest build() {
                RemoteAppLinkLaunchRequest buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteAppLinkLaunchRequest buildPartial() {
                RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest = new RemoteAppLinkLaunchRequest(this);
                remoteAppLinkLaunchRequest.appLink_ = this.appLink_;
                onBuilt();
                return remoteAppLinkLaunchRequest;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteAppLinkLaunchRequest) {
                    return mergeFrom((RemoteAppLinkLaunchRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest) {
                if (remoteAppLinkLaunchRequest == RemoteAppLinkLaunchRequest.getDefaultInstance()) {
                    return this;
                }
                if (!remoteAppLinkLaunchRequest.getAppLink().isEmpty()) {
                    this.appLink_ = remoteAppLinkLaunchRequest.appLink_;
                    onChanged();
                }
                mergeUnknownFields(remoteAppLinkLaunchRequest.unknownFields);
                onChanged();
                return this;
            }

            /* CODE  */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest = null;
                try {
                    remoteAppLinkLaunchRequest = (RemoteAppLinkLaunchRequest) RemoteAppLinkLaunchRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteAppLinkLaunchRequest = (RemoteAppLinkLaunchRequest) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteAppLinkLaunchRequest != null) {
                        mergeFrom(remoteAppLinkLaunchRequest);
                    }
                }
                return this;
            }

            @Override
            public String getAppLink() {
                Object obj = this.appLink_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.appLink_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setAppLink(String str) {
                Objects.requireNonNull(str);
                this.appLink_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getAppLinkBytes() {
                Object obj = this.appLink_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.appLink_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setAppLinkBytes(ByteString byteString) {
                RemoteAppLinkLaunchRequest.checkByteStringIsUtf8(byteString);
                this.appLink_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAppLink() {
                this.appLink_ = RemoteAppLinkLaunchRequest.getDefaultInstance().getAppLink();
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteResetPreferredAudioDevice extends GeneratedMessageV3 implements RemoteResetPreferredAudioDeviceOrBuilder {
        private static final RemoteResetPreferredAudioDevice DEFAULT_INSTANCE = new RemoteResetPreferredAudioDevice();
        private static final Parser<RemoteResetPreferredAudioDevice> PARSER = new AbstractParser<RemoteResetPreferredAudioDevice>() {

            @Override
            public RemoteResetPreferredAudioDevice parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteResetPreferredAudioDevice(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private RemoteResetPreferredAudioDevice(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteResetPreferredAudioDevice() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteResetPreferredAudioDevice(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag == 0 || !parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        z = true;
//                    }
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteResetPreferredAudioDevice(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_descriptor;
        }

        public static RemoteResetPreferredAudioDevice parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(InputStream inputStream) throws IOException {
            return (RemoteResetPreferredAudioDevice) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteResetPreferredAudioDevice) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteResetPreferredAudioDevice parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteResetPreferredAudioDevice) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteResetPreferredAudioDevice parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteResetPreferredAudioDevice) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteResetPreferredAudioDevice) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteResetPreferredAudioDevice parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteResetPreferredAudioDevice) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteResetPreferredAudioDevice);
        }

        public static RemoteResetPreferredAudioDevice getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteResetPreferredAudioDevice> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteResetPreferredAudioDevice();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteResetPreferredAudioDevice.class, Builder.class);
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteResetPreferredAudioDevice)) {
                return super.equals(obj);
            }
            if (!this.unknownFields.equals(((RemoteResetPreferredAudioDevice) obj).unknownFields)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteResetPreferredAudioDevice> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteResetPreferredAudioDevice getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteResetPreferredAudioDeviceOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteResetPreferredAudioDevice.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteResetPreferredAudioDevice.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteResetPreferredAudioDevice_descriptor;
            }

            @Override
            public RemoteResetPreferredAudioDevice getDefaultInstanceForType() {
                return RemoteResetPreferredAudioDevice.getDefaultInstance();
            }

            @Override
            public RemoteResetPreferredAudioDevice build() {
                RemoteResetPreferredAudioDevice buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteResetPreferredAudioDevice buildPartial() {
                RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice = new RemoteResetPreferredAudioDevice(this);
                onBuilt();
                return remoteResetPreferredAudioDevice;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteResetPreferredAudioDevice) {
                    return mergeFrom((RemoteResetPreferredAudioDevice) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice) {
                if (remoteResetPreferredAudioDevice == RemoteResetPreferredAudioDevice.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(remoteResetPreferredAudioDevice.unknownFields);
                onChanged();
                return this;
            }

            /* CODE  */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice = null;
                try {
                    remoteResetPreferredAudioDevice = (RemoteResetPreferredAudioDevice) RemoteResetPreferredAudioDevice.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteResetPreferredAudioDevice = (RemoteResetPreferredAudioDevice) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteResetPreferredAudioDevice != null) {
                        mergeFrom(remoteResetPreferredAudioDevice);
                    }
                }
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteSetPreferredAudioDevice extends GeneratedMessageV3 implements RemoteSetPreferredAudioDeviceOrBuilder {
        private static final RemoteSetPreferredAudioDevice DEFAULT_INSTANCE = new RemoteSetPreferredAudioDevice();
        private static final Parser<RemoteSetPreferredAudioDevice> PARSER = new AbstractParser<RemoteSetPreferredAudioDevice>() {

            @Override
            public RemoteSetPreferredAudioDevice parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteSetPreferredAudioDevice(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private RemoteSetPreferredAudioDevice(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteSetPreferredAudioDevice() {
            this.memoizedIsInitialized = -1;
        }

        private RemoteSetPreferredAudioDevice(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_descriptor;
        }

        public static RemoteSetPreferredAudioDevice parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(InputStream inputStream) throws IOException {
            return (RemoteSetPreferredAudioDevice) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetPreferredAudioDevice) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteSetPreferredAudioDevice parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteSetPreferredAudioDevice) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteSetPreferredAudioDevice parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetPreferredAudioDevice) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteSetPreferredAudioDevice) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteSetPreferredAudioDevice parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetPreferredAudioDevice) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteSetPreferredAudioDevice);
        }

        public static RemoteSetPreferredAudioDevice getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteSetPreferredAudioDevice> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteSetPreferredAudioDevice();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteSetPreferredAudioDevice.class, Builder.class);
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteSetPreferredAudioDevice)) {
                return super.equals(obj);
            }
            if (!this.unknownFields.equals(((RemoteSetPreferredAudioDevice) obj).unknownFields)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteSetPreferredAudioDevice> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteSetPreferredAudioDevice getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteSetPreferredAudioDeviceOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteSetPreferredAudioDevice.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteSetPreferredAudioDevice.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetPreferredAudioDevice_descriptor;
            }

            @Override
            public RemoteSetPreferredAudioDevice getDefaultInstanceForType() {
                return RemoteSetPreferredAudioDevice.getDefaultInstance();
            }

            @Override
            public RemoteSetPreferredAudioDevice build() {
                RemoteSetPreferredAudioDevice buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteSetPreferredAudioDevice buildPartial() {
                RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice = new RemoteSetPreferredAudioDevice(this);
                onBuilt();
                return remoteSetPreferredAudioDevice;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteSetPreferredAudioDevice) {
                    return mergeFrom((RemoteSetPreferredAudioDevice) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice) {
                if (remoteSetPreferredAudioDevice == RemoteSetPreferredAudioDevice.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(remoteSetPreferredAudioDevice.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice = null;
                try {
                    remoteSetPreferredAudioDevice = (RemoteSetPreferredAudioDevice) RemoteSetPreferredAudioDevice.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteSetPreferredAudioDevice = (RemoteSetPreferredAudioDevice) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteSetPreferredAudioDevice != null) {
                        mergeFrom(remoteSetPreferredAudioDevice);
                    }
                }
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteAdjustVolumeLevel extends GeneratedMessageV3 implements RemoteAdjustVolumeLevelOrBuilder {
        private static final RemoteAdjustVolumeLevel DEFAULT_INSTANCE = new RemoteAdjustVolumeLevel();
        private static final Parser<RemoteAdjustVolumeLevel> PARSER = new AbstractParser<RemoteAdjustVolumeLevel>() {

            @Override
            public RemoteAdjustVolumeLevel parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteAdjustVolumeLevel(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private RemoteAdjustVolumeLevel(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteAdjustVolumeLevel() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteAdjustVolumeLevel(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag == 0 || !parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        z = true;
//                    }
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteAdjustVolumeLevel(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_descriptor;
        }

        public static RemoteAdjustVolumeLevel parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteAdjustVolumeLevel parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteAdjustVolumeLevel parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteAdjustVolumeLevel parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteAdjustVolumeLevel parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteAdjustVolumeLevel parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteAdjustVolumeLevel parseFrom(InputStream inputStream) throws IOException {
            return (RemoteAdjustVolumeLevel) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteAdjustVolumeLevel parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAdjustVolumeLevel) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteAdjustVolumeLevel parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteAdjustVolumeLevel) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteAdjustVolumeLevel parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAdjustVolumeLevel) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteAdjustVolumeLevel parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteAdjustVolumeLevel) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteAdjustVolumeLevel parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAdjustVolumeLevel) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteAdjustVolumeLevel remoteAdjustVolumeLevel) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteAdjustVolumeLevel);
        }

        public static RemoteAdjustVolumeLevel getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteAdjustVolumeLevel> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteAdjustVolumeLevel();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteAdjustVolumeLevel.class, Builder.class);
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteAdjustVolumeLevel)) {
                return super.equals(obj);
            }
            if (!this.unknownFields.equals(((RemoteAdjustVolumeLevel) obj).unknownFields)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteAdjustVolumeLevel> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteAdjustVolumeLevel getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteAdjustVolumeLevelOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteAdjustVolumeLevel.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteAdjustVolumeLevel.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAdjustVolumeLevel_descriptor;
            }

            @Override
            public RemoteAdjustVolumeLevel getDefaultInstanceForType() {
                return RemoteAdjustVolumeLevel.getDefaultInstance();
            }

            @Override
            public RemoteAdjustVolumeLevel build() {
                RemoteAdjustVolumeLevel buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteAdjustVolumeLevel buildPartial() {
                RemoteAdjustVolumeLevel remoteAdjustVolumeLevel = new RemoteAdjustVolumeLevel(this);
                onBuilt();
                return remoteAdjustVolumeLevel;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteAdjustVolumeLevel) {
                    return mergeFrom((RemoteAdjustVolumeLevel) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteAdjustVolumeLevel remoteAdjustVolumeLevel) {
                if (remoteAdjustVolumeLevel == RemoteAdjustVolumeLevel.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(remoteAdjustVolumeLevel.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteAdjustVolumeLevel remoteAdjustVolumeLevel = null;
                try {
                    remoteAdjustVolumeLevel = (RemoteAdjustVolumeLevel) RemoteAdjustVolumeLevel.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteAdjustVolumeLevel = (RemoteAdjustVolumeLevel) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteAdjustVolumeLevel != null) {
                        mergeFrom(remoteAdjustVolumeLevel);
                    }
                }
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteSetVolumeLevel extends GeneratedMessageV3 implements RemoteSetVolumeLevelOrBuilder {
        public static final int PLAYER_MODEL_FIELD_NUMBER = 3;
        public static final int UNKNOWN1_FIELD_NUMBER = 1;
        public static final int UNKNOWN2_FIELD_NUMBER = 2;
        public static final int UNKNOWN4_FIELD_NUMBER = 4;
        public static final int UNKNOWN5_FIELD_NUMBER = 5;
        public static final int VOLUME_LEVEL_FIELD_NUMBER = 7;
        public static final int VOLUME_MAX_FIELD_NUMBER = 6;
        public static final int VOLUME_MUTED_FIELD_NUMBER = 8;
        private static final RemoteSetVolumeLevel DEFAULT_INSTANCE = new RemoteSetVolumeLevel();
        private static final Parser<RemoteSetVolumeLevel> PARSER = new AbstractParser<RemoteSetVolumeLevel>() {

            @Override
            public RemoteSetVolumeLevel parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteSetVolumeLevel(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object playerModel_;
        private int unknown1_;
        private int unknown2_;
        private int unknown4_;
        private int unknown5_;
        private int volumeLevel_;
        private int volumeMax_;
        private boolean volumeMuted_;

        private RemoteSetVolumeLevel(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteSetVolumeLevel() {
            this.memoizedIsInitialized = -1;
            this.playerModel_ = "";
        }


        //        private RemoteSetVolumeLevel(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.unknown1_ = codedInputStream.readUInt32();
//                        } else if (readTag == 16) {
//                            this.unknown2_ = codedInputStream.readUInt32();
//                        } else if (readTag == 26) {
//                            this.playerModel_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 32) {
//                            this.unknown4_ = codedInputStream.readUInt32();
//                        } else if (readTag == 40) {
//                            this.unknown5_ = codedInputStream.readUInt32();
//                        } else if (readTag == 48) {
//                            this.volumeMax_ = codedInputStream.readUInt32();
//                        } else if (readTag == 56) {
//                            this.volumeLevel_ = codedInputStream.readUInt32();
//                        } else if (readTag == 64) {
//                            this.volumeMuted_ = codedInputStream.readBool();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteSetVolumeLevel(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            unknown1_ = input.readUInt32();
                            break;
                        }
                        case 16: {

                            unknown2_ = input.readUInt32();
                            break;
                        }
                        case 26: {
                            String s = input.readStringRequireUtf8();

                            playerModel_ = s;
                            break;
                        }
                        case 32: {

                            unknown4_ = input.readUInt32();
                            break;
                        }
                        case 40: {

                            unknown5_ = input.readUInt32();
                            break;
                        }
                        case 48: {

                            volumeMax_ = input.readUInt32();
                            break;
                        }
                        case 56: {

                            volumeLevel_ = input.readUInt32();
                            break;
                        }
                        case 64: {

                            volumeMuted_ = input.readBool();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteSetVolumeLevel_descriptor;
        }

        public static RemoteSetVolumeLevel parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteSetVolumeLevel parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteSetVolumeLevel parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteSetVolumeLevel parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteSetVolumeLevel parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteSetVolumeLevel parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteSetVolumeLevel parseFrom(InputStream inputStream) throws IOException {
            return (RemoteSetVolumeLevel) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteSetVolumeLevel parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetVolumeLevel) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteSetVolumeLevel parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteSetVolumeLevel) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteSetVolumeLevel parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetVolumeLevel) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteSetVolumeLevel parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteSetVolumeLevel) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteSetVolumeLevel parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetVolumeLevel) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteSetVolumeLevel remoteSetVolumeLevel) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteSetVolumeLevel);
        }

        public static RemoteSetVolumeLevel getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteSetVolumeLevel> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteSetVolumeLevel();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteSetVolumeLevel_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteSetVolumeLevel.class, Builder.class);
        }

        @Override
        public int getUnknown1() {
            return this.unknown1_;
        }

        @Override
        public int getUnknown2() {
            return this.unknown2_;
        }

        @Override
        public String getPlayerModel() {
            Object obj = this.playerModel_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.playerModel_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getPlayerModelBytes() {
            Object obj = this.playerModel_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.playerModel_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public int getUnknown4() {
            return this.unknown4_;
        }

        @Override
        public int getUnknown5() {
            return this.unknown5_;
        }

        @Override
        public int getVolumeMax() {
            return this.volumeMax_;
        }

        @Override
        public int getVolumeLevel() {
            return this.volumeLevel_;
        }

        @Override
        public boolean getVolumeMuted() {
            return this.volumeMuted_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.unknown1_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.unknown2_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            if (!getPlayerModelBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.playerModel_);
            }
            int i3 = this.unknown4_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(4, i3);
            }
            int i4 = this.unknown5_;
            if (i4 != 0) {
                codedOutputStream.writeUInt32(5, i4);
            }
            int i5 = this.volumeMax_;
            if (i5 != 0) {
                codedOutputStream.writeUInt32(6, i5);
            }
            int i6 = this.volumeLevel_;
            if (i6 != 0) {
                codedOutputStream.writeUInt32(7, i6);
            }
            boolean z = this.volumeMuted_;
            if (z) {
                codedOutputStream.writeBool(8, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.unknown1_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.unknown2_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            if (!getPlayerModelBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(3, this.playerModel_);
            }
            int i5 = this.unknown4_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(4, i5);
            }
            int i6 = this.unknown5_;
            if (i6 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(5, i6);
            }
            int i7 = this.volumeMax_;
            if (i7 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(6, i7);
            }
            int i8 = this.volumeLevel_;
            if (i8 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(7, i8);
            }
            boolean z = this.volumeMuted_;
            if (z) {
                i2 += CodedOutputStream.computeBoolSize(8, z);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteSetVolumeLevel)) {
                return super.equals(obj);
            }
            RemoteSetVolumeLevel remoteSetVolumeLevel = (RemoteSetVolumeLevel) obj;
            if (getUnknown1() == remoteSetVolumeLevel.getUnknown1() && getUnknown2() == remoteSetVolumeLevel.getUnknown2() && getPlayerModel().equals(remoteSetVolumeLevel.getPlayerModel()) && getUnknown4() == remoteSetVolumeLevel.getUnknown4() && getUnknown5() == remoteSetVolumeLevel.getUnknown5() && getVolumeMax() == remoteSetVolumeLevel.getVolumeMax() && getVolumeLevel() == remoteSetVolumeLevel.getVolumeLevel() && getVolumeMuted() == remoteSetVolumeLevel.getVolumeMuted() && this.unknownFields.equals(remoteSetVolumeLevel.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getUnknown1()) * 37) + 2) * 53) + getUnknown2()) * 37) + 3) * 53) + getPlayerModel().hashCode()) * 37) + 4) * 53) + getUnknown4()) * 37) + 5) * 53) + getUnknown5()) * 37) + 6) * 53) + getVolumeMax()) * 37) + 7) * 53) + getVolumeLevel()) * 37) + 8) * 53) + Internal.hashBoolean(getVolumeMuted())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteSetVolumeLevel> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteSetVolumeLevel getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteSetVolumeLevelOrBuilder {
            private Object playerModel_;
            private int unknown1_;
            private int unknown2_;
            private int unknown4_;
            private int unknown5_;
            private int volumeLevel_;
            private int volumeMax_;
            private boolean volumeMuted_;

            private Builder() {
                this.playerModel_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.playerModel_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetVolumeLevel_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetVolumeLevel_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteSetVolumeLevel.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteSetVolumeLevel.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.unknown1_ = 0;
                this.unknown2_ = 0;
                this.playerModel_ = "";
                this.unknown4_ = 0;
                this.unknown5_ = 0;
                this.volumeMax_ = 0;
                this.volumeLevel_ = 0;
                this.volumeMuted_ = false;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetVolumeLevel_descriptor;
            }

            @Override
            public RemoteSetVolumeLevel getDefaultInstanceForType() {
                return RemoteSetVolumeLevel.getDefaultInstance();
            }

            @Override
            public RemoteSetVolumeLevel build() {
                RemoteSetVolumeLevel buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteSetVolumeLevel buildPartial() {
                RemoteSetVolumeLevel remoteSetVolumeLevel = new RemoteSetVolumeLevel(this);
                remoteSetVolumeLevel.unknown1_ = this.unknown1_;
                remoteSetVolumeLevel.unknown2_ = this.unknown2_;
                remoteSetVolumeLevel.playerModel_ = this.playerModel_;
                remoteSetVolumeLevel.unknown4_ = this.unknown4_;
                remoteSetVolumeLevel.unknown5_ = this.unknown5_;
                remoteSetVolumeLevel.volumeMax_ = this.volumeMax_;
                remoteSetVolumeLevel.volumeLevel_ = this.volumeLevel_;
                remoteSetVolumeLevel.volumeMuted_ = this.volumeMuted_;
                onBuilt();
                return remoteSetVolumeLevel;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteSetVolumeLevel) {
                    return mergeFrom((RemoteSetVolumeLevel) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteSetVolumeLevel remoteSetVolumeLevel) {
                if (remoteSetVolumeLevel == RemoteSetVolumeLevel.getDefaultInstance()) {
                    return this;
                }
                if (remoteSetVolumeLevel.getUnknown1() != 0) {
                    setUnknown1(remoteSetVolumeLevel.getUnknown1());
                }
                if (remoteSetVolumeLevel.getUnknown2() != 0) {
                    setUnknown2(remoteSetVolumeLevel.getUnknown2());
                }
                if (!remoteSetVolumeLevel.getPlayerModel().isEmpty()) {
                    this.playerModel_ = remoteSetVolumeLevel.playerModel_;
                    onChanged();
                }
                if (remoteSetVolumeLevel.getUnknown4() != 0) {
                    setUnknown4(remoteSetVolumeLevel.getUnknown4());
                }
                if (remoteSetVolumeLevel.getUnknown5() != 0) {
                    setUnknown5(remoteSetVolumeLevel.getUnknown5());
                }
                if (remoteSetVolumeLevel.getVolumeMax() != 0) {
                    setVolumeMax(remoteSetVolumeLevel.getVolumeMax());
                }
                if (remoteSetVolumeLevel.getVolumeLevel() != 0) {
                    setVolumeLevel(remoteSetVolumeLevel.getVolumeLevel());
                }
                if (remoteSetVolumeLevel.getVolumeMuted()) {
                    setVolumeMuted(remoteSetVolumeLevel.getVolumeMuted());
                }
                mergeUnknownFields(remoteSetVolumeLevel.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteSetVolumeLevel remoteSetVolumeLevel = null;
                try {
                    remoteSetVolumeLevel = (RemoteSetVolumeLevel) RemoteSetVolumeLevel.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteSetVolumeLevel = (RemoteSetVolumeLevel) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteSetVolumeLevel != null) {
                        mergeFrom(remoteSetVolumeLevel);
                    }
                }
                return this;
            }

            @Override
            public int getUnknown1() {
                return this.unknown1_;
            }

            public Builder setUnknown1(int i) {
                this.unknown1_ = i;
                onChanged();
                return this;
            }

            public Builder clearUnknown1() {
                this.unknown1_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getUnknown2() {
                return this.unknown2_;
            }

            public Builder setUnknown2(int i) {
                this.unknown2_ = i;
                onChanged();
                return this;
            }

            public Builder clearUnknown2() {
                this.unknown2_ = 0;
                onChanged();
                return this;
            }

            @Override
            public String getPlayerModel() {
                Object obj = this.playerModel_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.playerModel_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setPlayerModel(String str) {
                Objects.requireNonNull(str);
                this.playerModel_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getPlayerModelBytes() {
                Object obj = this.playerModel_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.playerModel_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setPlayerModelBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteSetVolumeLevel.checkByteStringIsUtf8(byteString);
                this.playerModel_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearPlayerModel() {
                this.playerModel_ = RemoteSetVolumeLevel.getDefaultInstance().getPlayerModel();
                onChanged();
                return this;
            }

            @Override
            public int getUnknown4() {
                return this.unknown4_;
            }

            public Builder setUnknown4(int i) {
                this.unknown4_ = i;
                onChanged();
                return this;
            }

            public Builder clearUnknown4() {
                this.unknown4_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getUnknown5() {
                return this.unknown5_;
            }

            public Builder setUnknown5(int i) {
                this.unknown5_ = i;
                onChanged();
                return this;
            }

            public Builder clearUnknown5() {
                this.unknown5_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getVolumeMax() {
                return this.volumeMax_;
            }

            public Builder setVolumeMax(int i) {
                this.volumeMax_ = i;
                onChanged();
                return this;
            }

            public Builder clearVolumeMax() {
                this.volumeMax_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getVolumeLevel() {
                return this.volumeLevel_;
            }

            public Builder setVolumeLevel(int i) {
                this.volumeLevel_ = i;
                onChanged();
                return this;
            }

            public Builder clearVolumeLevel() {
                this.volumeLevel_ = 0;
                onChanged();
                return this;
            }

            @Override
            public boolean getVolumeMuted() {
                return this.volumeMuted_;
            }

            public Builder setVolumeMuted(boolean z) {
                this.volumeMuted_ = z;
                onChanged();
                return this;
            }

            public Builder clearVolumeMuted() {
                this.volumeMuted_ = false;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteStart extends GeneratedMessageV3 implements RemoteStartOrBuilder {
        public static final int STARTED_FIELD_NUMBER = 1;
        private static final RemoteStart DEFAULT_INSTANCE = new RemoteStart();
        private static final Parser<RemoteStart> PARSER = new AbstractParser<RemoteStart>() {

            @Override
            public RemoteStart parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteStart(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private boolean started_;

        private RemoteStart(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteStart() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteStart(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.started_ = codedInputStream.readBool();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteStart(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            started_ = input.readBool();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteStart_descriptor;
        }

        public static RemoteStart parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteStart parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteStart parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteStart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteStart parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteStart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteStart parseFrom(InputStream inputStream) throws IOException {
            return (RemoteStart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteStart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteStart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteStart parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteStart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteStart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteStart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteStart parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteStart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteStart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteStart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteStart remoteStart) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteStart);
        }

        public static RemoteStart getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteStart> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteStart();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteStart_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteStart.class, Builder.class);
        }

        @Override
        public boolean getStarted() {
            return this.started_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.started_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.started_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteStart)) {
                return super.equals(obj);
            }
            RemoteStart remoteStart = (RemoteStart) obj;
            if (getStarted() == remoteStart.getStarted() && this.unknownFields.equals(remoteStart.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getStarted())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteStart> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteStart getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteStartOrBuilder {
            private boolean started_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteStart_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteStart_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteStart.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteStart.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.started_ = false;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteStart_descriptor;
            }

            @Override
            public RemoteStart getDefaultInstanceForType() {
                return RemoteStart.getDefaultInstance();
            }

            @Override
            public RemoteStart build() {
                RemoteStart buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteStart buildPartial() {
                RemoteStart remoteStart = new RemoteStart(this);
                remoteStart.started_ = this.started_;
                onBuilt();
                return remoteStart;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteStart) {
                    return mergeFrom((RemoteStart) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteStart remoteStart) {
                if (remoteStart == RemoteStart.getDefaultInstance()) {
                    return this;
                }
                if (remoteStart.getStarted()) {
                    setStarted(remoteStart.getStarted());
                }
                mergeUnknownFields(remoteStart.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteStart remoteStart = null;
                try {
                    remoteStart = (RemoteStart) RemoteStart.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteStart = (RemoteStart) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteStart != null) {
                        mergeFrom(remoteStart);
                    }
                }
                return this;
            }

            @Override
            public boolean getStarted() {
                return this.started_;
            }

            public Builder setStarted(boolean z) {
                this.started_ = z;
                onChanged();
                return this;
            }

            public Builder clearStarted() {
                this.started_ = false;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteVoiceEnd extends GeneratedMessageV3 implements RemoteVoiceEndOrBuilder {
        private static final RemoteVoiceEnd DEFAULT_INSTANCE = new RemoteVoiceEnd();
        private static final Parser<RemoteVoiceEnd> PARSER = new AbstractParser<RemoteVoiceEnd>() {

            @Override
            public RemoteVoiceEnd parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteVoiceEnd(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private RemoteVoiceEnd(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteVoiceEnd() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteVoiceEnd(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag == 0 || !parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        z = true;
//                    }
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteVoiceEnd(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceEnd_descriptor;
        }

        public static RemoteVoiceEnd parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteVoiceEnd parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteVoiceEnd parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteVoiceEnd parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteVoiceEnd parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteVoiceEnd parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteVoiceEnd parseFrom(InputStream inputStream) throws IOException {
            return (RemoteVoiceEnd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteVoiceEnd parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoiceEnd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteVoiceEnd parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteVoiceEnd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteVoiceEnd parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoiceEnd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteVoiceEnd parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteVoiceEnd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteVoiceEnd parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoiceEnd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteVoiceEnd remoteVoiceEnd) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteVoiceEnd);
        }

        public static RemoteVoiceEnd getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteVoiceEnd> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteVoiceEnd();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceEnd_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteVoiceEnd.class, Builder.class);
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteVoiceEnd)) {
                return super.equals(obj);
            }
            if (!this.unknownFields.equals(((RemoteVoiceEnd) obj).unknownFields)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteVoiceEnd> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteVoiceEnd getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteVoiceEndOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceEnd_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceEnd_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteVoiceEnd.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteVoiceEnd.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceEnd_descriptor;
            }

            @Override
            public RemoteVoiceEnd getDefaultInstanceForType() {
                return RemoteVoiceEnd.getDefaultInstance();
            }

            @Override
            public RemoteVoiceEnd build() {
                RemoteVoiceEnd buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteVoiceEnd buildPartial() {
                RemoteVoiceEnd remoteVoiceEnd = new RemoteVoiceEnd(this);
                onBuilt();
                return remoteVoiceEnd;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteVoiceEnd) {
                    return mergeFrom((RemoteVoiceEnd) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteVoiceEnd remoteVoiceEnd) {
                if (remoteVoiceEnd == RemoteVoiceEnd.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(remoteVoiceEnd.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteVoiceEnd remoteVoiceEnd = null;
                try {
                    remoteVoiceEnd = (RemoteVoiceEnd) RemoteVoiceEnd.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteVoiceEnd = (RemoteVoiceEnd) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteVoiceEnd != null) {
                        mergeFrom(remoteVoiceEnd);
                    }
                }
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteVoicePayload extends GeneratedMessageV3 implements RemoteVoicePayloadOrBuilder {
        private static final RemoteVoicePayload DEFAULT_INSTANCE = new RemoteVoicePayload();
        private static final Parser<RemoteVoicePayload> PARSER = new AbstractParser<RemoteVoicePayload>() {

            @Override
            public RemoteVoicePayload parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteVoicePayload(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private RemoteVoicePayload(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteVoicePayload() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteVoicePayload(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag == 0 || !parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        z = true;
//                    }
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteVoicePayload(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteVoicePayload_descriptor;
        }

        public static RemoteVoicePayload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteVoicePayload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteVoicePayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteVoicePayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteVoicePayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteVoicePayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteVoicePayload parseFrom(InputStream inputStream) throws IOException {
            return (RemoteVoicePayload) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteVoicePayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoicePayload) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteVoicePayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteVoicePayload) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteVoicePayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoicePayload) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteVoicePayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteVoicePayload) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteVoicePayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoicePayload) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteVoicePayload remoteVoicePayload) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteVoicePayload);
        }

        public static RemoteVoicePayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteVoicePayload> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteVoicePayload();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteVoicePayload_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteVoicePayload.class, Builder.class);
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteVoicePayload)) {
                return super.equals(obj);
            }
            if (!this.unknownFields.equals(((RemoteVoicePayload) obj).unknownFields)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteVoicePayload> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteVoicePayload getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteVoicePayloadOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoicePayload_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoicePayload_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteVoicePayload.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteVoicePayload.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoicePayload_descriptor;
            }

            @Override
            public RemoteVoicePayload getDefaultInstanceForType() {
                return RemoteVoicePayload.getDefaultInstance();
            }

            @Override
            public RemoteVoicePayload build() {
                RemoteVoicePayload buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteVoicePayload buildPartial() {
                RemoteVoicePayload remoteVoicePayload = new RemoteVoicePayload(this);
                onBuilt();
                return remoteVoicePayload;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteVoicePayload) {
                    return mergeFrom((RemoteVoicePayload) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteVoicePayload remoteVoicePayload) {
                if (remoteVoicePayload == RemoteVoicePayload.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(remoteVoicePayload.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteVoicePayload remoteVoicePayload = null;
                try {
                    remoteVoicePayload = (RemoteVoicePayload) RemoteVoicePayload.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteVoicePayload = (RemoteVoicePayload) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteVoicePayload != null) {
                        mergeFrom(remoteVoicePayload);
                    }
                }
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteVoiceBegin extends GeneratedMessageV3 implements RemoteVoiceBeginOrBuilder {
        private static final RemoteVoiceBegin DEFAULT_INSTANCE = new RemoteVoiceBegin();
        private static final Parser<RemoteVoiceBegin> PARSER = new AbstractParser<RemoteVoiceBegin>() {

            @Override
            public RemoteVoiceBegin parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteVoiceBegin(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private RemoteVoiceBegin(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteVoiceBegin() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteVoiceBegin(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag == 0 || !parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        z = true;
//                    }
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteVoiceBegin(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceBegin_descriptor;
        }

        public static RemoteVoiceBegin parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteVoiceBegin parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteVoiceBegin parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteVoiceBegin parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteVoiceBegin parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteVoiceBegin parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteVoiceBegin parseFrom(InputStream inputStream) throws IOException {
            return (RemoteVoiceBegin) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteVoiceBegin parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoiceBegin) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteVoiceBegin parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteVoiceBegin) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteVoiceBegin parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoiceBegin) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteVoiceBegin parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteVoiceBegin) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteVoiceBegin parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteVoiceBegin) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteVoiceBegin remoteVoiceBegin) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteVoiceBegin);
        }

        public static RemoteVoiceBegin getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteVoiceBegin> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteVoiceBegin();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceBegin_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteVoiceBegin.class, Builder.class);
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteVoiceBegin)) {
                return super.equals(obj);
            }
            if (!this.unknownFields.equals(((RemoteVoiceBegin) obj).unknownFields)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteVoiceBegin> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteVoiceBegin getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteVoiceBeginOrBuilder {
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceBegin_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceBegin_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteVoiceBegin.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteVoiceBegin.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteVoiceBegin_descriptor;
            }

            @Override
            public RemoteVoiceBegin getDefaultInstanceForType() {
                return RemoteVoiceBegin.getDefaultInstance();
            }

            @Override
            public RemoteVoiceBegin build() {
                RemoteVoiceBegin buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteVoiceBegin buildPartial() {
                RemoteVoiceBegin remoteVoiceBegin = new RemoteVoiceBegin(this);
                onBuilt();
                return remoteVoiceBegin;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteVoiceBegin) {
                    return mergeFrom((RemoteVoiceBegin) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteVoiceBegin remoteVoiceBegin) {
                if (remoteVoiceBegin == RemoteVoiceBegin.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(remoteVoiceBegin.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteVoiceBegin remoteVoiceBegin = null;
                try {
                    remoteVoiceBegin = (RemoteVoiceBegin) RemoteVoiceBegin.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteVoiceBegin = (RemoteVoiceBegin) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteVoiceBegin != null) {
                        mergeFrom(remoteVoiceBegin);
                    }
                }
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteTextFieldStatus extends GeneratedMessageV3 implements RemoteTextFieldStatusOrBuilder {
        public static final int COUNTER_FIELD_FIELD_NUMBER = 1;
        public static final int END_FIELD_NUMBER = 4;
        public static final int INT5_FIELD_NUMBER = 5;
        public static final int LABEL_FIELD_NUMBER = 6;
        public static final int START_FIELD_NUMBER = 3;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final RemoteTextFieldStatus DEFAULT_INSTANCE = new RemoteTextFieldStatus();
        private static final Parser<RemoteTextFieldStatus> PARSER = new AbstractParser<RemoteTextFieldStatus>() {

            @Override
            public RemoteTextFieldStatus parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteTextFieldStatus(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int counterField_;
        private int end_;
        private int int5_;
        private volatile Object label_;
        private byte memoizedIsInitialized;
        private int start_;
        private volatile Object value_;

        private RemoteTextFieldStatus(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteTextFieldStatus() {
            this.memoizedIsInitialized = -1;
            this.value_ = "";
            this.label_ = "";
        }


        //        private RemoteTextFieldStatus(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.counterField_ = codedInputStream.readInt32();
//                        } else if (readTag == 18) {
//                            this.value_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 24) {
//                            this.start_ = codedInputStream.readInt32();
//                        } else if (readTag == 32) {
//                            this.end_ = codedInputStream.readInt32();
//                        } else if (readTag == 40) {
//                            this.int5_ = codedInputStream.readInt32();
//                        } else if (readTag == 50) {
//                            this.label_ = codedInputStream.readStringRequireUtf8();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteTextFieldStatus(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            counterField_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            String s = input.readStringRequireUtf8();

                            value_ = s;
                            break;
                        }
                        case 24: {

                            start_ = input.readInt32();
                            break;
                        }
                        case 32: {

                            end_ = input.readInt32();
                            break;
                        }
                        case 40: {

                            int5_ = input.readInt32();
                            break;
                        }
                        case 50: {
                            String s = input.readStringRequireUtf8();

                            label_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteTextFieldStatus_descriptor;
        }

        public static RemoteTextFieldStatus parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteTextFieldStatus parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteTextFieldStatus parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteTextFieldStatus parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteTextFieldStatus parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteTextFieldStatus parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteTextFieldStatus parseFrom(InputStream inputStream) throws IOException {
            return (RemoteTextFieldStatus) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteTextFieldStatus parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteTextFieldStatus) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteTextFieldStatus parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteTextFieldStatus) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteTextFieldStatus parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteTextFieldStatus) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteTextFieldStatus parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteTextFieldStatus) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteTextFieldStatus parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteTextFieldStatus) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteTextFieldStatus remoteTextFieldStatus) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteTextFieldStatus);
        }

        public static RemoteTextFieldStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteTextFieldStatus> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteTextFieldStatus();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteTextFieldStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteTextFieldStatus.class, Builder.class);
        }

        @Override
        public int getCounterField() {
            return this.counterField_;
        }

        @Override
        public String getValue() {
            Object obj = this.value_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.value_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getValueBytes() {
            Object obj = this.value_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.value_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public int getStart() {
            return this.start_;
        }

        @Override
        public int getEnd() {
            return this.end_;
        }

        @Override
        public int getInt5() {
            return this.int5_;
        }

        @Override
        public String getLabel() {
            Object obj = this.label_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.label_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getLabelBytes() {
            Object obj = this.label_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.label_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.counterField_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            if (!getValueBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.value_);
            }
            int i2 = this.start_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(3, i2);
            }
            int i3 = this.end_;
            if (i3 != 0) {
                codedOutputStream.writeInt32(4, i3);
            }
            int i4 = this.int5_;
            if (i4 != 0) {
                codedOutputStream.writeInt32(5, i4);
            }
            if (!getLabelBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.label_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.counterField_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            if (!getValueBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.value_);
            }
            int i4 = this.start_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeInt32Size(3, i4);
            }
            int i5 = this.end_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeInt32Size(4, i5);
            }
            int i6 = this.int5_;
            if (i6 != 0) {
                i2 += CodedOutputStream.computeInt32Size(5, i6);
            }
            if (!getLabelBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(6, this.label_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteTextFieldStatus)) {
                return super.equals(obj);
            }
            RemoteTextFieldStatus remoteTextFieldStatus = (RemoteTextFieldStatus) obj;
            if (getCounterField() == remoteTextFieldStatus.getCounterField() && getValue().equals(remoteTextFieldStatus.getValue()) && getStart() == remoteTextFieldStatus.getStart() && getEnd() == remoteTextFieldStatus.getEnd() && getInt5() == remoteTextFieldStatus.getInt5() && getLabel().equals(remoteTextFieldStatus.getLabel()) && this.unknownFields.equals(remoteTextFieldStatus.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCounterField()) * 37) + 2) * 53) + getValue().hashCode()) * 37) + 3) * 53) + getStart()) * 37) + 4) * 53) + getEnd()) * 37) + 5) * 53) + getInt5()) * 37) + 6) * 53) + getLabel().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteTextFieldStatus> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteTextFieldStatus getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteTextFieldStatusOrBuilder {
            private int counterField_;
            private int end_;
            private int int5_;
            private Object label_;
            private int start_;
            private Object value_;

            private Builder() {
                this.value_ = "";
                this.label_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.value_ = "";
                this.label_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteTextFieldStatus_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteTextFieldStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteTextFieldStatus.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteTextFieldStatus.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.counterField_ = 0;
                this.value_ = "";
                this.start_ = 0;
                this.end_ = 0;
                this.int5_ = 0;
                this.label_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteTextFieldStatus_descriptor;
            }

            @Override
            public RemoteTextFieldStatus getDefaultInstanceForType() {
                return RemoteTextFieldStatus.getDefaultInstance();
            }

            @Override
            public RemoteTextFieldStatus build() {
                RemoteTextFieldStatus buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteTextFieldStatus buildPartial() {
                RemoteTextFieldStatus remoteTextFieldStatus = new RemoteTextFieldStatus(this);
                remoteTextFieldStatus.counterField_ = this.counterField_;
                remoteTextFieldStatus.value_ = this.value_;
                remoteTextFieldStatus.start_ = this.start_;
                remoteTextFieldStatus.end_ = this.end_;
                remoteTextFieldStatus.int5_ = this.int5_;
                remoteTextFieldStatus.label_ = this.label_;
                onBuilt();
                return remoteTextFieldStatus;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteTextFieldStatus) {
                    return mergeFrom((RemoteTextFieldStatus) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteTextFieldStatus remoteTextFieldStatus) {
                if (remoteTextFieldStatus == RemoteTextFieldStatus.getDefaultInstance()) {
                    return this;
                }
                if (remoteTextFieldStatus.getCounterField() != 0) {
                    setCounterField(remoteTextFieldStatus.getCounterField());
                }
                if (!remoteTextFieldStatus.getValue().isEmpty()) {
                    this.value_ = remoteTextFieldStatus.value_;
                    onChanged();
                }
                if (remoteTextFieldStatus.getStart() != 0) {
                    setStart(remoteTextFieldStatus.getStart());
                }
                if (remoteTextFieldStatus.getEnd() != 0) {
                    setEnd(remoteTextFieldStatus.getEnd());
                }
                if (remoteTextFieldStatus.getInt5() != 0) {
                    setInt5(remoteTextFieldStatus.getInt5());
                }
                if (!remoteTextFieldStatus.getLabel().isEmpty()) {
                    this.label_ = remoteTextFieldStatus.label_;
                    onChanged();
                }
                mergeUnknownFields(remoteTextFieldStatus.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteTextFieldStatus remoteTextFieldStatus = null;
                try {
                    remoteTextFieldStatus = (RemoteTextFieldStatus) RemoteTextFieldStatus.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteTextFieldStatus = (RemoteTextFieldStatus) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteTextFieldStatus != null) {
                        mergeFrom(remoteTextFieldStatus);
                    }
                }
                return this;
            }

            @Override
            public int getCounterField() {
                return this.counterField_;
            }

            public Builder setCounterField(int i) {
                this.counterField_ = i;
                onChanged();
                return this;
            }

            public Builder clearCounterField() {
                this.counterField_ = 0;
                onChanged();
                return this;
            }

            @Override
            public String getValue() {
                Object obj = this.value_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.value_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setValue(String str) {
                Objects.requireNonNull(str);
                this.value_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getValueBytes() {
                Object obj = this.value_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.value_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setValueBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteTextFieldStatus.checkByteStringIsUtf8(byteString);
                this.value_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.value_ = RemoteTextFieldStatus.getDefaultInstance().getValue();
                onChanged();
                return this;
            }

            @Override
            public int getStart() {
                return this.start_;
            }

            public Builder setStart(int i) {
                this.start_ = i;
                onChanged();
                return this;
            }

            public Builder clearStart() {
                this.start_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getEnd() {
                return this.end_;
            }

            public Builder setEnd(int i) {
                this.end_ = i;
                onChanged();
                return this;
            }

            public Builder clearEnd() {
                this.end_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getInt5() {
                return this.int5_;
            }

            public Builder setInt5(int i) {
                this.int5_ = i;
                onChanged();
                return this;
            }

            public Builder clearInt5() {
                this.int5_ = 0;
                onChanged();
                return this;
            }

            @Override
            public String getLabel() {
                Object obj = this.label_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.label_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setLabel(String str) {
                Objects.requireNonNull(str);
                this.label_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getLabelBytes() {
                Object obj = this.label_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.label_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setLabelBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteTextFieldStatus.checkByteStringIsUtf8(byteString);
                this.label_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearLabel() {
                this.label_ = RemoteTextFieldStatus.getDefaultInstance().getLabel();
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteImeShowRequest extends GeneratedMessageV3 implements RemoteImeShowRequestOrBuilder {
        public static final int REMOTE_TEXT_FIELD_STATUS_FIELD_NUMBER = 2;
        private static final RemoteImeShowRequest DEFAULT_INSTANCE = new RemoteImeShowRequest();
        private static final Parser<RemoteImeShowRequest> PARSER = new AbstractParser<RemoteImeShowRequest>() {

            @Override
            public RemoteImeShowRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteImeShowRequest(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private RemoteTextFieldStatus remoteTextFieldStatus_;

        private RemoteImeShowRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteImeShowRequest() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteImeShowRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 18) {
//                            RemoteTextFieldStatus.Builder builder = null;
//                            RemoteTextFieldStatus remoteTextFieldStatus = this.remoteTextFieldStatus_;
//                            builder = remoteTextFieldStatus != null ? remoteTextFieldStatus.toBuilder() : builder;
//                            RemoteTextFieldStatus remoteTextFieldStatus2 = (RemoteTextFieldStatus) codedInputStream.readMessage(RemoteTextFieldStatus.parser(), extensionRegistryLite);
//                            this.remoteTextFieldStatus_ = remoteTextFieldStatus2;
//                            if (builder != null) {
//                                builder.mergeFrom(remoteTextFieldStatus2);
//                                this.remoteTextFieldStatus_ = builder.buildPartial();
//                            }
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteImeShowRequest(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 18: {
                            RemoteTextFieldStatus.Builder subBuilder = null;
                            if (remoteTextFieldStatus_ != null) {
                                subBuilder = remoteTextFieldStatus_.toBuilder();
                            }
                            remoteTextFieldStatus_ = input.readMessage(RemoteTextFieldStatus.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteTextFieldStatus_);
                                remoteTextFieldStatus_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteImeShowRequest_descriptor;
        }

        public static RemoteImeShowRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteImeShowRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteImeShowRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteImeShowRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteImeShowRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteImeShowRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteImeShowRequest parseFrom(InputStream inputStream) throws IOException {
            return (RemoteImeShowRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteImeShowRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeShowRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteImeShowRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteImeShowRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteImeShowRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeShowRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteImeShowRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteImeShowRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteImeShowRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeShowRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteImeShowRequest remoteImeShowRequest) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteImeShowRequest);
        }

        public static RemoteImeShowRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteImeShowRequest> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteImeShowRequest();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteImeShowRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteImeShowRequest.class, Builder.class);
        }

        @Override
        public boolean hasRemoteTextFieldStatus() {
            return this.remoteTextFieldStatus_ != null;
        }

        @Override
        public RemoteTextFieldStatus getRemoteTextFieldStatus() {
            RemoteTextFieldStatus remoteTextFieldStatus = this.remoteTextFieldStatus_;
            return remoteTextFieldStatus == null ? RemoteTextFieldStatus.getDefaultInstance() : remoteTextFieldStatus;
        }

        @Override
        public RemoteTextFieldStatusOrBuilder getRemoteTextFieldStatusOrBuilder() {
            return getRemoteTextFieldStatus();
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.remoteTextFieldStatus_ != null) {
                codedOutputStream.writeMessage(2, getRemoteTextFieldStatus());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.remoteTextFieldStatus_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(2, getRemoteTextFieldStatus());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteImeShowRequest)) {
                return super.equals(obj);
            }
            RemoteImeShowRequest remoteImeShowRequest = (RemoteImeShowRequest) obj;
            if (hasRemoteTextFieldStatus() != remoteImeShowRequest.hasRemoteTextFieldStatus()) {
                return false;
            }
            if ((!hasRemoteTextFieldStatus() || getRemoteTextFieldStatus().equals(remoteImeShowRequest.getRemoteTextFieldStatus())) && this.unknownFields.equals(remoteImeShowRequest.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasRemoteTextFieldStatus()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getRemoteTextFieldStatus().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteImeShowRequest> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteImeShowRequest getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteImeShowRequestOrBuilder {
            private SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> remoteTextFieldStatusBuilder_;
            private RemoteTextFieldStatus remoteTextFieldStatus_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeShowRequest_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeShowRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteImeShowRequest.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteImeShowRequest.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.remoteTextFieldStatusBuilder_ == null) {
                    this.remoteTextFieldStatus_ = null;
                } else {
                    this.remoteTextFieldStatus_ = null;
                    this.remoteTextFieldStatusBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeShowRequest_descriptor;
            }

            @Override
            public RemoteImeShowRequest getDefaultInstanceForType() {
                return RemoteImeShowRequest.getDefaultInstance();
            }

            @Override
            public RemoteImeShowRequest build() {
                RemoteImeShowRequest buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteImeShowRequest buildPartial() {
                RemoteImeShowRequest remoteImeShowRequest = new RemoteImeShowRequest(this);
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.remoteTextFieldStatusBuilder_;
                if (singleFieldBuilderV3 == null) {
                    remoteImeShowRequest.remoteTextFieldStatus_ = this.remoteTextFieldStatus_;
                } else {
                    remoteImeShowRequest.remoteTextFieldStatus_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return remoteImeShowRequest;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteImeShowRequest) {
                    return mergeFrom((RemoteImeShowRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteImeShowRequest remoteImeShowRequest) {
                if (remoteImeShowRequest == RemoteImeShowRequest.getDefaultInstance()) {
                    return this;
                }
                if (remoteImeShowRequest.hasRemoteTextFieldStatus()) {
                    mergeRemoteTextFieldStatus(remoteImeShowRequest.getRemoteTextFieldStatus());
                }
                mergeUnknownFields(remoteImeShowRequest.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteImeShowRequest remoteImeShowRequest = null;
                try {
                    remoteImeShowRequest = (RemoteImeShowRequest) RemoteImeShowRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteImeShowRequest = (RemoteImeShowRequest) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteImeShowRequest != null) {
                        mergeFrom(remoteImeShowRequest);
                    }
                }
                return this;
            }

            @Override
            public boolean hasRemoteTextFieldStatus() {
                return (this.remoteTextFieldStatusBuilder_ == null && this.remoteTextFieldStatus_ == null) ? false : true;
            }

            @Override
            public RemoteTextFieldStatus getRemoteTextFieldStatus() {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.remoteTextFieldStatusBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteTextFieldStatus remoteTextFieldStatus = this.remoteTextFieldStatus_;
                return remoteTextFieldStatus == null ? RemoteTextFieldStatus.getDefaultInstance() : remoteTextFieldStatus;
            }

            public Builder setRemoteTextFieldStatus(RemoteTextFieldStatus remoteTextFieldStatus) {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.remoteTextFieldStatusBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteTextFieldStatus);
                    this.remoteTextFieldStatus_ = remoteTextFieldStatus;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteTextFieldStatus);
                }
                return this;
            }

            public Builder setRemoteTextFieldStatus(RemoteTextFieldStatus.Builder builder) {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.remoteTextFieldStatusBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteTextFieldStatus_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteTextFieldStatus(RemoteTextFieldStatus remoteTextFieldStatus) {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.remoteTextFieldStatusBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteTextFieldStatus remoteTextFieldStatus2 = this.remoteTextFieldStatus_;
                    if (remoteTextFieldStatus2 != null) {
                        this.remoteTextFieldStatus_ = RemoteTextFieldStatus.newBuilder(remoteTextFieldStatus2).mergeFrom(remoteTextFieldStatus).buildPartial();
                    } else {
                        this.remoteTextFieldStatus_ = remoteTextFieldStatus;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteTextFieldStatus);
                }
                return this;
            }

            public Builder clearRemoteTextFieldStatus() {
                if (this.remoteTextFieldStatusBuilder_ == null) {
                    this.remoteTextFieldStatus_ = null;
                    onChanged();
                } else {
                    this.remoteTextFieldStatus_ = null;
                    this.remoteTextFieldStatusBuilder_ = null;
                }
                return this;
            }

            public RemoteTextFieldStatus.Builder getRemoteTextFieldStatusBuilder() {
                onChanged();
                return getRemoteTextFieldStatusFieldBuilder().getBuilder();
            }

            @Override
            public RemoteTextFieldStatusOrBuilder getRemoteTextFieldStatusOrBuilder() {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.remoteTextFieldStatusBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteTextFieldStatus remoteTextFieldStatus = this.remoteTextFieldStatus_;
                return remoteTextFieldStatus == null ? RemoteTextFieldStatus.getDefaultInstance() : remoteTextFieldStatus;
            }

            private SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> getRemoteTextFieldStatusFieldBuilder() {
                if (this.remoteTextFieldStatusBuilder_ == null) {
                    this.remoteTextFieldStatusBuilder_ = new SingleFieldBuilderV3<>(getRemoteTextFieldStatus(), getParentForChildren(), isClean());
                    this.remoteTextFieldStatus_ = null;
                }
                return this.remoteTextFieldStatusBuilder_;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteEditInfo extends GeneratedMessageV3 implements RemoteEditInfoOrBuilder {
        public static final int INSERT_FIELD_NUMBER = 2;
        private static final RemoteEditInfo DEFAULT_INSTANCE = new RemoteEditInfo();
        private static final Parser<RemoteEditInfo> PARSER = new AbstractParser<RemoteEditInfo>() {

            @Override
            public RemoteEditInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteEditInfo(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int insert_;
        private byte memoizedIsInitialized;

        private RemoteEditInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteEditInfo() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteEditInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 16) {
//                            this.insert_ = codedInputStream.readInt32();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteEditInfo(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 16: {

                            insert_ = input.readInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteEditInfo_descriptor;
        }

        public static RemoteEditInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteEditInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteEditInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteEditInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteEditInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteEditInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteEditInfo parseFrom(InputStream inputStream) throws IOException {
            return (RemoteEditInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteEditInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteEditInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteEditInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteEditInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteEditInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteEditInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteEditInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteEditInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteEditInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteEditInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteEditInfo remoteEditInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteEditInfo);
        }

        public static RemoteEditInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteEditInfo> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteEditInfo();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteEditInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteEditInfo.class, Builder.class);
        }

        @Override
        public int getInsert() {
            return this.insert_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.insert_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.insert_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(2, i3);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteEditInfo)) {
                return super.equals(obj);
            }
            RemoteEditInfo remoteEditInfo = (RemoteEditInfo) obj;
            if (getInsert() == remoteEditInfo.getInsert() && this.unknownFields.equals(remoteEditInfo.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 2) * 53) + getInsert()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteEditInfo> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteEditInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteEditInfoOrBuilder {
            private int insert_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteEditInfo_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteEditInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteEditInfo.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteEditInfo.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.insert_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteEditInfo_descriptor;
            }

            @Override
            public RemoteEditInfo getDefaultInstanceForType() {
                return RemoteEditInfo.getDefaultInstance();
            }

            @Override
            public RemoteEditInfo build() {
                RemoteEditInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteEditInfo buildPartial() {
                RemoteEditInfo remoteEditInfo = new RemoteEditInfo(this);
                remoteEditInfo.insert_ = this.insert_;
                onBuilt();
                return remoteEditInfo;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteEditInfo) {
                    return mergeFrom((RemoteEditInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteEditInfo remoteEditInfo) {
                if (remoteEditInfo == RemoteEditInfo.getDefaultInstance()) {
                    return this;
                }
                if (remoteEditInfo.getInsert() != 0) {
                    setInsert(remoteEditInfo.getInsert());
                }
                mergeUnknownFields(remoteEditInfo.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteEditInfo remoteEditInfo = null;
                try {
                    remoteEditInfo = (RemoteEditInfo) RemoteEditInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteEditInfo = (RemoteEditInfo) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteEditInfo != null) {
                        mergeFrom(remoteEditInfo);
                    }
                }
                return this;
            }

            @Override
            public int getInsert() {
                return this.insert_;
            }

            public Builder setInsert(int i) {
                this.insert_ = i;
                onChanged();
                return this;
            }

            public Builder clearInsert() {
                this.insert_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteImeBatchEdit extends GeneratedMessageV3 implements RemoteImeBatchEditOrBuilder {
        public static final int EDIT_INFO_FIELD_NUMBER = 3;
        public static final int FIELD_COUNTER_FIELD_NUMBER = 2;
        public static final int IME_COUNTER_FIELD_NUMBER = 1;
        private static final RemoteImeBatchEdit DEFAULT_INSTANCE = new RemoteImeBatchEdit();
        private static final Parser<RemoteImeBatchEdit> PARSER = new AbstractParser<RemoteImeBatchEdit>() {

            @Override
            public RemoteImeBatchEdit parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteImeBatchEdit(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private RemoteEditInfo editInfo_;
        private int fieldCounter_;
        private int imeCounter_;
        private byte memoizedIsInitialized;

        private RemoteImeBatchEdit(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteImeBatchEdit() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteImeBatchEdit(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.imeCounter_ = codedInputStream.readInt32();
//                        } else if (readTag == 16) {
//                            this.fieldCounter_ = codedInputStream.readInt32();
//                        } else if (readTag == 26) {
//                            RemoteEditInfo.Builder builder = null;
//                            RemoteEditInfo remoteEditInfo = this.editInfo_;
//                            builder = remoteEditInfo != null ? remoteEditInfo.toBuilder() : builder;
//                            RemoteEditInfo remoteEditInfo2 = (RemoteEditInfo) codedInputStream.readMessage(RemoteEditInfo.parser(), extensionRegistryLite);
//                            this.editInfo_ = remoteEditInfo2;
//                            if (builder != null) {
//                                builder.mergeFrom(remoteEditInfo2);
//                                this.editInfo_ = builder.buildPartial();
//                            }
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteImeBatchEdit(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            imeCounter_ = input.readInt32();
                            break;
                        }
                        case 16: {

                            fieldCounter_ = input.readInt32();
                            break;
                        }
                        case 26: {
                            RemoteEditInfo.Builder subBuilder = null;
                            if (editInfo_ != null) {
                                subBuilder = editInfo_.toBuilder();
                            }
                            editInfo_ = input.readMessage(RemoteEditInfo.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(editInfo_);
                                editInfo_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteImeBatchEdit_descriptor;
        }

        public static RemoteImeBatchEdit parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteImeBatchEdit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteImeBatchEdit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteImeBatchEdit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteImeBatchEdit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteImeBatchEdit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteImeBatchEdit parseFrom(InputStream inputStream) throws IOException {
            return (RemoteImeBatchEdit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteImeBatchEdit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeBatchEdit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteImeBatchEdit parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteImeBatchEdit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteImeBatchEdit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeBatchEdit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteImeBatchEdit parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteImeBatchEdit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteImeBatchEdit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeBatchEdit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteImeBatchEdit remoteImeBatchEdit) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteImeBatchEdit);
        }

        public static RemoteImeBatchEdit getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteImeBatchEdit> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteImeBatchEdit();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteImeBatchEdit_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteImeBatchEdit.class, Builder.class);
        }

        @Override
        public int getImeCounter() {
            return this.imeCounter_;
        }

        @Override
        public int getFieldCounter() {
            return this.fieldCounter_;
        }

        @Override
        public boolean hasEditInfo() {
            return this.editInfo_ != null;
        }

        @Override
        public RemoteEditInfo getEditInfo() {
            RemoteEditInfo remoteEditInfo = this.editInfo_;
            return remoteEditInfo == null ? RemoteEditInfo.getDefaultInstance() : remoteEditInfo;
        }

        @Override
        public RemoteEditInfoOrBuilder getEditInfoOrBuilder() {
            return getEditInfo();
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.imeCounter_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            int i2 = this.fieldCounter_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(2, i2);
            }
            if (this.editInfo_ != null) {
                codedOutputStream.writeMessage(3, getEditInfo());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.imeCounter_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            int i4 = this.fieldCounter_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeInt32Size(2, i4);
            }
            if (this.editInfo_ != null) {
                i2 += CodedOutputStream.computeMessageSize(3, getEditInfo());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteImeBatchEdit)) {
                return super.equals(obj);
            }
            RemoteImeBatchEdit remoteImeBatchEdit = (RemoteImeBatchEdit) obj;
            if (getImeCounter() != remoteImeBatchEdit.getImeCounter() || getFieldCounter() != remoteImeBatchEdit.getFieldCounter() || hasEditInfo() != remoteImeBatchEdit.hasEditInfo()) {
                return false;
            }
            if ((!hasEditInfo() || getEditInfo().equals(remoteImeBatchEdit.getEditInfo())) && this.unknownFields.equals(remoteImeBatchEdit.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getImeCounter()) * 37) + 2) * 53) + getFieldCounter();
            if (hasEditInfo()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getEditInfo().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteImeBatchEdit> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteImeBatchEdit getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteImeBatchEditOrBuilder {
            private SingleFieldBuilderV3<RemoteEditInfo, RemoteEditInfo.Builder, RemoteEditInfoOrBuilder> editInfoBuilder_;
            private RemoteEditInfo editInfo_;
            private int fieldCounter_;
            private int imeCounter_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeBatchEdit_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeBatchEdit_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteImeBatchEdit.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteImeBatchEdit.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.imeCounter_ = 0;
                this.fieldCounter_ = 0;
                if (this.editInfoBuilder_ == null) {
                    this.editInfo_ = null;
                } else {
                    this.editInfo_ = null;
                    this.editInfoBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeBatchEdit_descriptor;
            }

            @Override
            public RemoteImeBatchEdit getDefaultInstanceForType() {
                return RemoteImeBatchEdit.getDefaultInstance();
            }

            @Override
            public RemoteImeBatchEdit build() {
                RemoteImeBatchEdit buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteImeBatchEdit buildPartial() {
                RemoteImeBatchEdit remoteImeBatchEdit = new RemoteImeBatchEdit(this);
                remoteImeBatchEdit.imeCounter_ = this.imeCounter_;
                remoteImeBatchEdit.fieldCounter_ = this.fieldCounter_;
                SingleFieldBuilderV3<RemoteEditInfo, RemoteEditInfo.Builder, RemoteEditInfoOrBuilder> singleFieldBuilderV3 = this.editInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    remoteImeBatchEdit.editInfo_ = this.editInfo_;
                } else {
                    remoteImeBatchEdit.editInfo_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return remoteImeBatchEdit;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteImeBatchEdit) {
                    return mergeFrom((RemoteImeBatchEdit) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteImeBatchEdit remoteImeBatchEdit) {
                if (remoteImeBatchEdit == RemoteImeBatchEdit.getDefaultInstance()) {
                    return this;
                }
                if (remoteImeBatchEdit.getImeCounter() != 0) {
                    setImeCounter(remoteImeBatchEdit.getImeCounter());
                }
                if (remoteImeBatchEdit.getFieldCounter() != 0) {
                    setFieldCounter(remoteImeBatchEdit.getFieldCounter());
                }
                if (remoteImeBatchEdit.hasEditInfo()) {
                    mergeEditInfo(remoteImeBatchEdit.getEditInfo());
                }
                mergeUnknownFields(remoteImeBatchEdit.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteImeBatchEdit remoteImeBatchEdit = null;
                try {
                    remoteImeBatchEdit = (RemoteImeBatchEdit) RemoteImeBatchEdit.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteImeBatchEdit = (RemoteImeBatchEdit) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteImeBatchEdit != null) {
                        mergeFrom(remoteImeBatchEdit);
                    }
                }
                return this;
            }

            @Override
            public int getImeCounter() {
                return this.imeCounter_;
            }

            public Builder setImeCounter(int i) {
                this.imeCounter_ = i;
                onChanged();
                return this;
            }

            public Builder clearImeCounter() {
                this.imeCounter_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getFieldCounter() {
                return this.fieldCounter_;
            }

            public Builder setFieldCounter(int i) {
                this.fieldCounter_ = i;
                onChanged();
                return this;
            }

            public Builder clearFieldCounter() {
                this.fieldCounter_ = 0;
                onChanged();
                return this;
            }

            @Override
            public boolean hasEditInfo() {
                return (this.editInfoBuilder_ == null && this.editInfo_ == null) ? false : true;
            }

            @Override
            public RemoteEditInfo getEditInfo() {
                SingleFieldBuilderV3<RemoteEditInfo, RemoteEditInfo.Builder, RemoteEditInfoOrBuilder> singleFieldBuilderV3 = this.editInfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteEditInfo remoteEditInfo = this.editInfo_;
                return remoteEditInfo == null ? RemoteEditInfo.getDefaultInstance() : remoteEditInfo;
            }

            public Builder setEditInfo(RemoteEditInfo remoteEditInfo) {
                SingleFieldBuilderV3<RemoteEditInfo, RemoteEditInfo.Builder, RemoteEditInfoOrBuilder> singleFieldBuilderV3 = this.editInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteEditInfo);
                    this.editInfo_ = remoteEditInfo;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteEditInfo);
                }
                return this;
            }

            public Builder setEditInfo(RemoteEditInfo.Builder builder) {
                SingleFieldBuilderV3<RemoteEditInfo, RemoteEditInfo.Builder, RemoteEditInfoOrBuilder> singleFieldBuilderV3 = this.editInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.editInfo_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeEditInfo(RemoteEditInfo remoteEditInfo) {
                SingleFieldBuilderV3<RemoteEditInfo, RemoteEditInfo.Builder, RemoteEditInfoOrBuilder> singleFieldBuilderV3 = this.editInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteEditInfo remoteEditInfo2 = this.editInfo_;
                    if (remoteEditInfo2 != null) {
                        this.editInfo_ = RemoteEditInfo.newBuilder(remoteEditInfo2).mergeFrom(remoteEditInfo).buildPartial();
                    } else {
                        this.editInfo_ = remoteEditInfo;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteEditInfo);
                }
                return this;
            }

            public Builder clearEditInfo() {
                if (this.editInfoBuilder_ == null) {
                    this.editInfo_ = null;
                    onChanged();
                } else {
                    this.editInfo_ = null;
                    this.editInfoBuilder_ = null;
                }
                return this;
            }

            public RemoteEditInfo.Builder getEditInfoBuilder() {
                onChanged();
                return getEditInfoFieldBuilder().getBuilder();
            }

            @Override
            public RemoteEditInfoOrBuilder getEditInfoOrBuilder() {
                SingleFieldBuilderV3<RemoteEditInfo, RemoteEditInfo.Builder, RemoteEditInfoOrBuilder> singleFieldBuilderV3 = this.editInfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteEditInfo remoteEditInfo = this.editInfo_;
                return remoteEditInfo == null ? RemoteEditInfo.getDefaultInstance() : remoteEditInfo;
            }

            private SingleFieldBuilderV3<RemoteEditInfo, RemoteEditInfo.Builder, RemoteEditInfoOrBuilder> getEditInfoFieldBuilder() {
                if (this.editInfoBuilder_ == null) {
                    this.editInfoBuilder_ = new SingleFieldBuilderV3<>(getEditInfo(), getParentForChildren(), isClean());
                    this.editInfo_ = null;
                }
                return this.editInfoBuilder_;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteAppInfo extends GeneratedMessageV3 implements RemoteAppInfoOrBuilder {
        public static final int APP_PACKAGE_FIELD_NUMBER = 12;
        public static final int COUNTER_FIELD_NUMBER = 1;
        public static final int INT13_FIELD_NUMBER = 13;
        public static final int INT2_FIELD_NUMBER = 2;
        public static final int INT3_FIELD_NUMBER = 3;
        public static final int INT4_FIELD_NUMBER = 4;
        public static final int INT7_FIELD_NUMBER = 7;
        public static final int INT8_FIELD_NUMBER = 8;
        public static final int LABEL_FIELD_NUMBER = 10;
        private static final RemoteAppInfo DEFAULT_INSTANCE = new RemoteAppInfo();
        private static final Parser<RemoteAppInfo> PARSER = new AbstractParser<RemoteAppInfo>() {

            @Override
            public RemoteAppInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteAppInfo(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object appPackage_;
        private int counter_;
        private int int13_;
        private int int2_;
        private int int3_;
        private volatile Object int4_;
        private int int7_;
        private int int8_;
        private volatile Object label_;
        private byte memoizedIsInitialized;

        private RemoteAppInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteAppInfo() {
            this.memoizedIsInitialized = -1;
            this.int4_ = "";
            this.label_ = "";
            this.appPackage_ = "";
        }


        //        private RemoteAppInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.counter_ = codedInputStream.readInt32();
//                        } else if (readTag == 16) {
//                            this.int2_ = codedInputStream.readInt32();
//                        } else if (readTag == 24) {
//                            this.int3_ = codedInputStream.readInt32();
//                        } else if (readTag == 34) {
//                            this.int4_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 56) {
//                            this.int7_ = codedInputStream.readInt32();
//                        } else if (readTag == 64) {
//                            this.int8_ = codedInputStream.readInt32();
//                        } else if (readTag == 82) {
//                            this.label_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 98) {
//                            this.appPackage_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 104) {
//                            this.int13_ = codedInputStream.readInt32();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteAppInfo(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            counter_ = input.readInt32();
                            break;
                        }
                        case 16: {

                            int2_ = input.readInt32();
                            break;
                        }
                        case 24: {

                            int3_ = input.readInt32();
                            break;
                        }
                        case 34: {
                            String s = input.readStringRequireUtf8();

                            int4_ = s;
                            break;
                        }
                        case 56: {

                            int7_ = input.readInt32();
                            break;
                        }
                        case 64: {

                            int8_ = input.readInt32();
                            break;
                        }
                        case 82: {
                            String s = input.readStringRequireUtf8();

                            label_ = s;
                            break;
                        }
                        case 98: {
                            String s = input.readStringRequireUtf8();

                            appPackage_ = s;
                            break;
                        }
                        case 104: {

                            int13_ = input.readInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteAppInfo_descriptor;
        }

        public static RemoteAppInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteAppInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteAppInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteAppInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteAppInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteAppInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteAppInfo parseFrom(InputStream inputStream) throws IOException {
            return (RemoteAppInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteAppInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAppInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteAppInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteAppInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteAppInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAppInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteAppInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteAppInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteAppInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteAppInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteAppInfo remoteAppInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteAppInfo);
        }

        public static RemoteAppInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteAppInfo> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteAppInfo();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteAppInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteAppInfo.class, Builder.class);
        }

        @Override
        public int getCounter() {
            return this.counter_;
        }

        @Override
        public int getInt2() {
            return this.int2_;
        }

        @Override
        public int getInt3() {
            return this.int3_;
        }

        @Override
        public String getInt4() {
            Object obj = this.int4_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.int4_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getInt4Bytes() {
            Object obj = this.int4_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.int4_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public int getInt7() {
            return this.int7_;
        }

        @Override
        public int getInt8() {
            return this.int8_;
        }

        @Override
        public String getLabel() {
            Object obj = this.label_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.label_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getLabelBytes() {
            Object obj = this.label_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.label_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public String getAppPackage() {
            Object obj = this.appPackage_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.appPackage_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getAppPackageBytes() {
            Object obj = this.appPackage_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.appPackage_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public int getInt13() {
            return this.int13_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.counter_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            int i2 = this.int2_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(2, i2);
            }
            int i3 = this.int3_;
            if (i3 != 0) {
                codedOutputStream.writeInt32(3, i3);
            }
            if (!getInt4Bytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.int4_);
            }
            int i4 = this.int7_;
            if (i4 != 0) {
                codedOutputStream.writeInt32(7, i4);
            }
            int i5 = this.int8_;
            if (i5 != 0) {
                codedOutputStream.writeInt32(8, i5);
            }
            if (!getLabelBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 10, this.label_);
            }
            if (!getAppPackageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 12, this.appPackage_);
            }
            int i6 = this.int13_;
            if (i6 != 0) {
                codedOutputStream.writeInt32(13, i6);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.counter_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            int i4 = this.int2_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeInt32Size(2, i4);
            }
            int i5 = this.int3_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeInt32Size(3, i5);
            }
            if (!getInt4Bytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(4, this.int4_);
            }
            int i6 = this.int7_;
            if (i6 != 0) {
                i2 += CodedOutputStream.computeInt32Size(7, i6);
            }
            int i7 = this.int8_;
            if (i7 != 0) {
                i2 += CodedOutputStream.computeInt32Size(8, i7);
            }
            if (!getLabelBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(10, this.label_);
            }
            if (!getAppPackageBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(12, this.appPackage_);
            }
            int i8 = this.int13_;
            if (i8 != 0) {
                i2 += CodedOutputStream.computeInt32Size(13, i8);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteAppInfo)) {
                return super.equals(obj);
            }
            RemoteAppInfo remoteAppInfo = (RemoteAppInfo) obj;
            if (getCounter() == remoteAppInfo.getCounter() && getInt2() == remoteAppInfo.getInt2() && getInt3() == remoteAppInfo.getInt3() && getInt4().equals(remoteAppInfo.getInt4()) && getInt7() == remoteAppInfo.getInt7() && getInt8() == remoteAppInfo.getInt8() && getLabel().equals(remoteAppInfo.getLabel()) && getAppPackage().equals(remoteAppInfo.getAppPackage()) && getInt13() == remoteAppInfo.getInt13() && this.unknownFields.equals(remoteAppInfo.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCounter()) * 37) + 2) * 53) + getInt2()) * 37) + 3) * 53) + getInt3()) * 37) + 4) * 53) + getInt4().hashCode()) * 37) + 7) * 53) + getInt7()) * 37) + 8) * 53) + getInt8()) * 37) + 10) * 53) + getLabel().hashCode()) * 37) + 12) * 53) + getAppPackage().hashCode()) * 37) + 13) * 53) + getInt13()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteAppInfo> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteAppInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteAppInfoOrBuilder {
            private Object appPackage_;
            private int counter_;
            private int int13_;
            private int int2_;
            private int int3_;
            private Object int4_;
            private int int7_;
            private int int8_;
            private Object label_;

            private Builder() {
                this.int4_ = "";
                this.label_ = "";
                this.appPackage_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.int4_ = "";
                this.label_ = "";
                this.appPackage_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAppInfo_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAppInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteAppInfo.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteAppInfo.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.counter_ = 0;
                this.int2_ = 0;
                this.int3_ = 0;
                this.int4_ = "";
                this.int7_ = 0;
                this.int8_ = 0;
                this.label_ = "";
                this.appPackage_ = "";
                this.int13_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteAppInfo_descriptor;
            }

            @Override
            public RemoteAppInfo getDefaultInstanceForType() {
                return RemoteAppInfo.getDefaultInstance();
            }

            @Override
            public RemoteAppInfo build() {
                RemoteAppInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteAppInfo buildPartial() {
                RemoteAppInfo remoteAppInfo = new RemoteAppInfo(this);
                remoteAppInfo.counter_ = this.counter_;
                remoteAppInfo.int2_ = this.int2_;
                remoteAppInfo.int3_ = this.int3_;
                remoteAppInfo.int4_ = this.int4_;
                remoteAppInfo.int7_ = this.int7_;
                remoteAppInfo.int8_ = this.int8_;
                remoteAppInfo.label_ = this.label_;
                remoteAppInfo.appPackage_ = this.appPackage_;
                remoteAppInfo.int13_ = this.int13_;
                onBuilt();
                return remoteAppInfo;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteAppInfo) {
                    return mergeFrom((RemoteAppInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteAppInfo remoteAppInfo) {
                if (remoteAppInfo == RemoteAppInfo.getDefaultInstance()) {
                    return this;
                }
                if (remoteAppInfo.getCounter() != 0) {
                    setCounter(remoteAppInfo.getCounter());
                }
                if (remoteAppInfo.getInt2() != 0) {
                    setInt2(remoteAppInfo.getInt2());
                }
                if (remoteAppInfo.getInt3() != 0) {
                    setInt3(remoteAppInfo.getInt3());
                }
                if (!remoteAppInfo.getInt4().isEmpty()) {
                    this.int4_ = remoteAppInfo.int4_;
                    onChanged();
                }
                if (remoteAppInfo.getInt7() != 0) {
                    setInt7(remoteAppInfo.getInt7());
                }
                if (remoteAppInfo.getInt8() != 0) {
                    setInt8(remoteAppInfo.getInt8());
                }
                if (!remoteAppInfo.getLabel().isEmpty()) {
                    this.label_ = remoteAppInfo.label_;
                    onChanged();
                }
                if (!remoteAppInfo.getAppPackage().isEmpty()) {
                    this.appPackage_ = remoteAppInfo.appPackage_;
                    onChanged();
                }
                if (remoteAppInfo.getInt13() != 0) {
                    setInt13(remoteAppInfo.getInt13());
                }
                mergeUnknownFields(remoteAppInfo.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteAppInfo remoteAppInfo = null;
                try {
                    remoteAppInfo = (RemoteAppInfo) RemoteAppInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteAppInfo = (RemoteAppInfo) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteAppInfo != null) {
                        mergeFrom(remoteAppInfo);
                    }
                }
                return this;
            }

            @Override
            public int getCounter() {
                return this.counter_;
            }

            public Builder setCounter(int i) {
                this.counter_ = i;
                onChanged();
                return this;
            }

            public Builder clearCounter() {
                this.counter_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getInt2() {
                return this.int2_;
            }

            public Builder setInt2(int i) {
                this.int2_ = i;
                onChanged();
                return this;
            }

            public Builder clearInt2() {
                this.int2_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getInt3() {
                return this.int3_;
            }

            public Builder setInt3(int i) {
                this.int3_ = i;
                onChanged();
                return this;
            }

            public Builder clearInt3() {
                this.int3_ = 0;
                onChanged();
                return this;
            }

            @Override
            public String getInt4() {
                Object obj = this.int4_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.int4_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setInt4(String str) {
                Objects.requireNonNull(str);
                this.int4_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getInt4Bytes() {
                Object obj = this.int4_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.int4_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setInt4Bytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteAppInfo.checkByteStringIsUtf8(byteString);
                this.int4_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearInt4() {
                this.int4_ = RemoteAppInfo.getDefaultInstance().getInt4();
                onChanged();
                return this;
            }

            @Override
            public int getInt7() {
                return this.int7_;
            }

            public Builder setInt7(int i) {
                this.int7_ = i;
                onChanged();
                return this;
            }

            public Builder clearInt7() {
                this.int7_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getInt8() {
                return this.int8_;
            }

            public Builder setInt8(int i) {
                this.int8_ = i;
                onChanged();
                return this;
            }

            public Builder clearInt8() {
                this.int8_ = 0;
                onChanged();
                return this;
            }

            @Override
            public String getLabel() {
                Object obj = this.label_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.label_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setLabel(String str) {
                Objects.requireNonNull(str);
                this.label_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getLabelBytes() {
                Object obj = this.label_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.label_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setLabelBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteAppInfo.checkByteStringIsUtf8(byteString);
                this.label_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearLabel() {
                this.label_ = RemoteAppInfo.getDefaultInstance().getLabel();
                onChanged();
                return this;
            }

            @Override
            public String getAppPackage() {
                Object obj = this.appPackage_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.appPackage_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setAppPackage(String str) {
                Objects.requireNonNull(str);
                this.appPackage_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getAppPackageBytes() {
                Object obj = this.appPackage_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.appPackage_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setAppPackageBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteAppInfo.checkByteStringIsUtf8(byteString);
                this.appPackage_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAppPackage() {
                this.appPackage_ = RemoteAppInfo.getDefaultInstance().getAppPackage();
                onChanged();
                return this;
            }

            @Override
            public int getInt13() {
                return this.int13_;
            }

            public Builder setInt13(int i) {
                this.int13_ = i;
                onChanged();
                return this;
            }

            public Builder clearInt13() {
                this.int13_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteImeKeyInject extends GeneratedMessageV3 implements RemoteImeKeyInjectOrBuilder {
        public static final int APP_INFO_FIELD_NUMBER = 1;
        public static final int TEXT_FIELD_STATUS_FIELD_NUMBER = 2;
        private static final RemoteImeKeyInject DEFAULT_INSTANCE = new RemoteImeKeyInject();
        private static final Parser<RemoteImeKeyInject> PARSER = new AbstractParser<RemoteImeKeyInject>() {

            @Override
            public RemoteImeKeyInject parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteImeKeyInject(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private RemoteAppInfo appInfo_;
        private byte memoizedIsInitialized;
        private RemoteTextFieldStatus textFieldStatus_;

        private RemoteImeKeyInject(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteImeKeyInject() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteImeKeyInject(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        RemoteAppInfo.Builder builder = null;
//                        RemoteTextFieldStatus.Builder builder2 = null;
//                        if (readTag == 10) {
//                            RemoteAppInfo remoteAppInfo = this.appInfo_;
//                            builder = remoteAppInfo != null ? remoteAppInfo.toBuilder() : builder;
//                            RemoteAppInfo remoteAppInfo2 = (RemoteAppInfo) codedInputStream.readMessage(RemoteAppInfo.parser(), extensionRegistryLite);
//                            this.appInfo_ = remoteAppInfo2;
//                            if (builder != null) {
//                                builder.mergeFrom(remoteAppInfo2);
//                                this.appInfo_ = builder.buildPartial();
//                            }
//                        } else if (readTag == 18) {
//                            RemoteTextFieldStatus remoteTextFieldStatus = this.textFieldStatus_;
//                            builder2 = remoteTextFieldStatus != null ? remoteTextFieldStatus.toBuilder() : builder2;
//                            RemoteTextFieldStatus remoteTextFieldStatus2 = (RemoteTextFieldStatus) codedInputStream.readMessage(RemoteTextFieldStatus.parser(), extensionRegistryLite);
//                            this.textFieldStatus_ = remoteTextFieldStatus2;
//                            if (builder2 != null) {
//                                builder2.mergeFrom(remoteTextFieldStatus2);
//                                this.textFieldStatus_ = builder2.buildPartial();
//                            }
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteImeKeyInject(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            RemoteAppInfo.Builder subBuilder = null;
                            if (appInfo_ != null) {
                                subBuilder = appInfo_.toBuilder();
                            }
                            appInfo_ = input.readMessage(RemoteAppInfo.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(appInfo_);
                                appInfo_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 18: {
                            RemoteTextFieldStatus.Builder subBuilder = null;
                            if (textFieldStatus_ != null) {
                                subBuilder = textFieldStatus_.toBuilder();
                            }
                            textFieldStatus_ = input.readMessage(RemoteTextFieldStatus.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(textFieldStatus_);
                                textFieldStatus_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteImeKeyInject_descriptor;
        }

        public static RemoteImeKeyInject parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteImeKeyInject parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteImeKeyInject parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteImeKeyInject parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteImeKeyInject parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteImeKeyInject parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteImeKeyInject parseFrom(InputStream inputStream) throws IOException {
            return (RemoteImeKeyInject) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteImeKeyInject parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeKeyInject) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteImeKeyInject parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteImeKeyInject) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteImeKeyInject parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeKeyInject) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteImeKeyInject parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteImeKeyInject) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteImeKeyInject parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteImeKeyInject) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteImeKeyInject remoteImeKeyInject) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteImeKeyInject);
        }

        public static RemoteImeKeyInject getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteImeKeyInject> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteImeKeyInject();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteImeKeyInject_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteImeKeyInject.class, Builder.class);
        }

        @Override
        public boolean hasAppInfo() {
            return this.appInfo_ != null;
        }

        @Override
        public RemoteAppInfo getAppInfo() {
            RemoteAppInfo remoteAppInfo = this.appInfo_;
            return remoteAppInfo == null ? RemoteAppInfo.getDefaultInstance() : remoteAppInfo;
        }

        @Override
        public RemoteAppInfoOrBuilder getAppInfoOrBuilder() {
            return getAppInfo();
        }

        @Override
        public boolean hasTextFieldStatus() {
            return this.textFieldStatus_ != null;
        }

        @Override
        public RemoteTextFieldStatus getTextFieldStatus() {
            RemoteTextFieldStatus remoteTextFieldStatus = this.textFieldStatus_;
            return remoteTextFieldStatus == null ? RemoteTextFieldStatus.getDefaultInstance() : remoteTextFieldStatus;
        }

        @Override
        public RemoteTextFieldStatusOrBuilder getTextFieldStatusOrBuilder() {
            return getTextFieldStatus();
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.appInfo_ != null) {
                codedOutputStream.writeMessage(1, getAppInfo());
            }
            if (this.textFieldStatus_ != null) {
                codedOutputStream.writeMessage(2, getTextFieldStatus());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.appInfo_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getAppInfo());
            }
            if (this.textFieldStatus_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getTextFieldStatus());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteImeKeyInject)) {
                return super.equals(obj);
            }
            RemoteImeKeyInject remoteImeKeyInject = (RemoteImeKeyInject) obj;
            if (hasAppInfo() != remoteImeKeyInject.hasAppInfo()) {
                return false;
            }
            if ((hasAppInfo() && !getAppInfo().equals(remoteImeKeyInject.getAppInfo())) || hasTextFieldStatus() != remoteImeKeyInject.hasTextFieldStatus()) {
                return false;
            }
            if ((!hasTextFieldStatus() || getTextFieldStatus().equals(remoteImeKeyInject.getTextFieldStatus())) && this.unknownFields.equals(remoteImeKeyInject.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasAppInfo()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getAppInfo().hashCode();
            }
            if (hasTextFieldStatus()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getTextFieldStatus().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteImeKeyInject> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteImeKeyInject getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteImeKeyInjectOrBuilder {
            private SingleFieldBuilderV3<RemoteAppInfo, RemoteAppInfo.Builder, RemoteAppInfoOrBuilder> appInfoBuilder_;
            private RemoteAppInfo appInfo_;
            private SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> textFieldStatusBuilder_;
            private RemoteTextFieldStatus textFieldStatus_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeKeyInject_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeKeyInject_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteImeKeyInject.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteImeKeyInject.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.appInfoBuilder_ == null) {
                    this.appInfo_ = null;
                } else {
                    this.appInfo_ = null;
                    this.appInfoBuilder_ = null;
                }
                if (this.textFieldStatusBuilder_ == null) {
                    this.textFieldStatus_ = null;
                } else {
                    this.textFieldStatus_ = null;
                    this.textFieldStatusBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteImeKeyInject_descriptor;
            }

            @Override
            public RemoteImeKeyInject getDefaultInstanceForType() {
                return RemoteImeKeyInject.getDefaultInstance();
            }

            @Override
            public RemoteImeKeyInject build() {
                RemoteImeKeyInject buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteImeKeyInject buildPartial() {
                RemoteImeKeyInject remoteImeKeyInject = new RemoteImeKeyInject(this);
                SingleFieldBuilderV3<RemoteAppInfo, RemoteAppInfo.Builder, RemoteAppInfoOrBuilder> singleFieldBuilderV3 = this.appInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    remoteImeKeyInject.appInfo_ = this.appInfo_;
                } else {
                    remoteImeKeyInject.appInfo_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV32 = this.textFieldStatusBuilder_;
                if (singleFieldBuilderV32 == null) {
                    remoteImeKeyInject.textFieldStatus_ = this.textFieldStatus_;
                } else {
                    remoteImeKeyInject.textFieldStatus_ = singleFieldBuilderV32.build();
                }
                onBuilt();
                return remoteImeKeyInject;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteImeKeyInject) {
                    return mergeFrom((RemoteImeKeyInject) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteImeKeyInject remoteImeKeyInject) {
                if (remoteImeKeyInject == RemoteImeKeyInject.getDefaultInstance()) {
                    return this;
                }
                if (remoteImeKeyInject.hasAppInfo()) {
                    mergeAppInfo(remoteImeKeyInject.getAppInfo());
                }
                if (remoteImeKeyInject.hasTextFieldStatus()) {
                    mergeTextFieldStatus(remoteImeKeyInject.getTextFieldStatus());
                }
                mergeUnknownFields(remoteImeKeyInject.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteImeKeyInject remoteImeKeyInject = null;
                try {
                    remoteImeKeyInject = (RemoteImeKeyInject) RemoteImeKeyInject.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteImeKeyInject = (RemoteImeKeyInject) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteImeKeyInject != null) {
                        mergeFrom(remoteImeKeyInject);
                    }
                }
                return this;
            }

            @Override
            public boolean hasAppInfo() {
                return (this.appInfoBuilder_ == null && this.appInfo_ == null) ? false : true;
            }

            @Override
            public RemoteAppInfo getAppInfo() {
                SingleFieldBuilderV3<RemoteAppInfo, RemoteAppInfo.Builder, RemoteAppInfoOrBuilder> singleFieldBuilderV3 = this.appInfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteAppInfo remoteAppInfo = this.appInfo_;
                return remoteAppInfo == null ? RemoteAppInfo.getDefaultInstance() : remoteAppInfo;
            }

            public Builder setAppInfo(RemoteAppInfo remoteAppInfo) {
                SingleFieldBuilderV3<RemoteAppInfo, RemoteAppInfo.Builder, RemoteAppInfoOrBuilder> singleFieldBuilderV3 = this.appInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteAppInfo);
                    this.appInfo_ = remoteAppInfo;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteAppInfo);
                }
                return this;
            }

            public Builder setAppInfo(RemoteAppInfo.Builder builder) {
                SingleFieldBuilderV3<RemoteAppInfo, RemoteAppInfo.Builder, RemoteAppInfoOrBuilder> singleFieldBuilderV3 = this.appInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.appInfo_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeAppInfo(RemoteAppInfo remoteAppInfo) {
                SingleFieldBuilderV3<RemoteAppInfo, RemoteAppInfo.Builder, RemoteAppInfoOrBuilder> singleFieldBuilderV3 = this.appInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteAppInfo remoteAppInfo2 = this.appInfo_;
                    if (remoteAppInfo2 != null) {
                        this.appInfo_ = RemoteAppInfo.newBuilder(remoteAppInfo2).mergeFrom(remoteAppInfo).buildPartial();
                    } else {
                        this.appInfo_ = remoteAppInfo;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteAppInfo);
                }
                return this;
            }

            public Builder clearAppInfo() {
                if (this.appInfoBuilder_ == null) {
                    this.appInfo_ = null;
                    onChanged();
                } else {
                    this.appInfo_ = null;
                    this.appInfoBuilder_ = null;
                }
                return this;
            }

            public RemoteAppInfo.Builder getAppInfoBuilder() {
                onChanged();
                return getAppInfoFieldBuilder().getBuilder();
            }

            @Override
            public RemoteAppInfoOrBuilder getAppInfoOrBuilder() {
                SingleFieldBuilderV3<RemoteAppInfo, RemoteAppInfo.Builder, RemoteAppInfoOrBuilder> singleFieldBuilderV3 = this.appInfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteAppInfo remoteAppInfo = this.appInfo_;
                return remoteAppInfo == null ? RemoteAppInfo.getDefaultInstance() : remoteAppInfo;
            }

            private SingleFieldBuilderV3<RemoteAppInfo, RemoteAppInfo.Builder, RemoteAppInfoOrBuilder> getAppInfoFieldBuilder() {
                if (this.appInfoBuilder_ == null) {
                    this.appInfoBuilder_ = new SingleFieldBuilderV3<>(getAppInfo(), getParentForChildren(), isClean());
                    this.appInfo_ = null;
                }
                return this.appInfoBuilder_;
            }

            @Override
            public boolean hasTextFieldStatus() {
                return (this.textFieldStatusBuilder_ == null && this.textFieldStatus_ == null) ? false : true;
            }

            @Override
            public RemoteTextFieldStatus getTextFieldStatus() {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.textFieldStatusBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteTextFieldStatus remoteTextFieldStatus = this.textFieldStatus_;
                return remoteTextFieldStatus == null ? RemoteTextFieldStatus.getDefaultInstance() : remoteTextFieldStatus;
            }

            public Builder setTextFieldStatus(RemoteTextFieldStatus remoteTextFieldStatus) {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.textFieldStatusBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteTextFieldStatus);
                    this.textFieldStatus_ = remoteTextFieldStatus;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteTextFieldStatus);
                }
                return this;
            }

            public Builder setTextFieldStatus(RemoteTextFieldStatus.Builder builder) {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.textFieldStatusBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.textFieldStatus_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTextFieldStatus(RemoteTextFieldStatus remoteTextFieldStatus) {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.textFieldStatusBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteTextFieldStatus remoteTextFieldStatus2 = this.textFieldStatus_;
                    if (remoteTextFieldStatus2 != null) {
                        this.textFieldStatus_ = RemoteTextFieldStatus.newBuilder(remoteTextFieldStatus2).mergeFrom(remoteTextFieldStatus).buildPartial();
                    } else {
                        this.textFieldStatus_ = remoteTextFieldStatus;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteTextFieldStatus);
                }
                return this;
            }

            public Builder clearTextFieldStatus() {
                if (this.textFieldStatusBuilder_ == null) {
                    this.textFieldStatus_ = null;
                    onChanged();
                } else {
                    this.textFieldStatus_ = null;
                    this.textFieldStatusBuilder_ = null;
                }
                return this;
            }

            public RemoteTextFieldStatus.Builder getTextFieldStatusBuilder() {
                onChanged();
                return getTextFieldStatusFieldBuilder().getBuilder();
            }

            @Override
            public RemoteTextFieldStatusOrBuilder getTextFieldStatusOrBuilder() {
                SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> singleFieldBuilderV3 = this.textFieldStatusBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteTextFieldStatus remoteTextFieldStatus = this.textFieldStatus_;
                return remoteTextFieldStatus == null ? RemoteTextFieldStatus.getDefaultInstance() : remoteTextFieldStatus;
            }

            private SingleFieldBuilderV3<RemoteTextFieldStatus, RemoteTextFieldStatus.Builder, RemoteTextFieldStatusOrBuilder> getTextFieldStatusFieldBuilder() {
                if (this.textFieldStatusBuilder_ == null) {
                    this.textFieldStatusBuilder_ = new SingleFieldBuilderV3<>(getTextFieldStatus(), getParentForChildren(), isClean());
                    this.textFieldStatus_ = null;
                }
                return this.textFieldStatusBuilder_;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteKeyInject extends GeneratedMessageV3 implements RemoteKeyInjectOrBuilder {
        public static final int DIRECTION_FIELD_NUMBER = 2;
        public static final int KEY_CODE_FIELD_NUMBER = 1;
        private static final RemoteKeyInject DEFAULT_INSTANCE = new RemoteKeyInject();
        private static final Parser<RemoteKeyInject> PARSER = new AbstractParser<RemoteKeyInject>() {

            @Override
            public RemoteKeyInject parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteKeyInject(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int direction_;
        private int keyCode_;
        private byte memoizedIsInitialized;

        private RemoteKeyInject(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteKeyInject() {
            this.memoizedIsInitialized = -1;
            this.keyCode_ = 0;
            this.direction_ = 0;
        }


        //        private RemoteKeyInject(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.keyCode_ = codedInputStream.readEnum();
//                        } else if (readTag == 16) {
//                            this.direction_ = codedInputStream.readEnum();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteKeyInject(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {
                            int rawValue = input.readEnum();

                            keyCode_ = rawValue;
                            break;
                        }
                        case 16: {
                            int rawValue = input.readEnum();

                            direction_ = rawValue;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteKeyInject_descriptor;
        }

        public static RemoteKeyInject parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteKeyInject parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteKeyInject parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteKeyInject parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteKeyInject parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteKeyInject parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteKeyInject parseFrom(InputStream inputStream) throws IOException {
            return (RemoteKeyInject) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteKeyInject parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteKeyInject) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteKeyInject parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteKeyInject) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteKeyInject parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteKeyInject) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteKeyInject parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteKeyInject) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteKeyInject parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteKeyInject) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteKeyInject remoteKeyInject) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteKeyInject);
        }

        public static RemoteKeyInject getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteKeyInject> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteKeyInject();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteKeyInject_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteKeyInject.class, Builder.class);
        }

        @Override
        public int getKeyCodeValue() {
            return this.keyCode_;
        }

        @Override
        public RemoteKeyCode getKeyCode() {
            RemoteKeyCode valueOf = RemoteKeyCode.valueOf(this.keyCode_);
            return valueOf == null ? RemoteKeyCode.UNRECOGNIZED : valueOf;
        }

        @Override
        public int getDirectionValue() {
            return this.direction_;
        }

        @Override
        public RemoteDirection getDirection() {
            RemoteDirection valueOf = RemoteDirection.valueOf(this.direction_);
            return valueOf == null ? RemoteDirection.UNRECOGNIZED : valueOf;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.keyCode_ != RemoteKeyCode.KEYCODE_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(1, this.keyCode_);
            }
            if (this.direction_ != RemoteDirection.UNKNOWN_DIRECTION.getNumber()) {
                codedOutputStream.writeEnum(2, this.direction_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.keyCode_ != RemoteKeyCode.KEYCODE_UNKNOWN.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.keyCode_);
            }
            if (this.direction_ != RemoteDirection.UNKNOWN_DIRECTION.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.direction_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteKeyInject)) {
                return super.equals(obj);
            }
            RemoteKeyInject remoteKeyInject = (RemoteKeyInject) obj;
            if (this.keyCode_ == remoteKeyInject.keyCode_ && this.direction_ == remoteKeyInject.direction_ && this.unknownFields.equals(remoteKeyInject.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.keyCode_) * 37) + 2) * 53) + this.direction_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteKeyInject> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteKeyInject getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteKeyInjectOrBuilder {
            private int direction_;
            private int keyCode_;

            private Builder() {
                this.keyCode_ = 0;
                this.direction_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.keyCode_ = 0;
                this.direction_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteKeyInject_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteKeyInject_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteKeyInject.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteKeyInject.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.keyCode_ = 0;
                this.direction_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteKeyInject_descriptor;
            }

            @Override
            public RemoteKeyInject getDefaultInstanceForType() {
                return RemoteKeyInject.getDefaultInstance();
            }

            @Override
            public RemoteKeyInject build() {
                RemoteKeyInject buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteKeyInject buildPartial() {
                RemoteKeyInject remoteKeyInject = new RemoteKeyInject(this);
                remoteKeyInject.keyCode_ = this.keyCode_;
                remoteKeyInject.direction_ = this.direction_;
                onBuilt();
                return remoteKeyInject;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteKeyInject) {
                    return mergeFrom((RemoteKeyInject) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteKeyInject remoteKeyInject) {
                if (remoteKeyInject == RemoteKeyInject.getDefaultInstance()) {
                    return this;
                }
                if (remoteKeyInject.keyCode_ != 0) {
                    setKeyCodeValue(remoteKeyInject.getKeyCodeValue());
                }
                if (remoteKeyInject.direction_ != 0) {
                    setDirectionValue(remoteKeyInject.getDirectionValue());
                }
                mergeUnknownFields(remoteKeyInject.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteKeyInject remoteKeyInject = null;
                try {
                    remoteKeyInject = (RemoteKeyInject) RemoteKeyInject.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteKeyInject = (RemoteKeyInject) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteKeyInject != null) {
                        mergeFrom(remoteKeyInject);
                    }
                }
                return this;
            }

            @Override
            public int getKeyCodeValue() {
                return this.keyCode_;
            }

            public Builder setKeyCodeValue(int i) {
                this.keyCode_ = i;
                onChanged();
                return this;
            }

            @Override
            public RemoteKeyCode getKeyCode() {
                RemoteKeyCode valueOf = RemoteKeyCode.valueOf(this.keyCode_);
                return valueOf == null ? RemoteKeyCode.UNRECOGNIZED : valueOf;
            }

            public Builder setKeyCode(RemoteKeyCode remoteKeyCode) {
                Objects.requireNonNull(remoteKeyCode);
                this.keyCode_ = remoteKeyCode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearKeyCode() {
                this.keyCode_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getDirectionValue() {
                return this.direction_;
            }

            public Builder setDirectionValue(int i) {
                this.direction_ = i;
                onChanged();
                return this;
            }

            @Override
            public RemoteDirection getDirection() {
                RemoteDirection valueOf = RemoteDirection.valueOf(this.direction_);
                return valueOf == null ? RemoteDirection.UNRECOGNIZED : valueOf;
            }

            public Builder setDirection(RemoteDirection remoteDirection) {
                Objects.requireNonNull(remoteDirection);
                this.direction_ = remoteDirection.getNumber();
                onChanged();
                return this;
            }

            public Builder clearDirection() {
                this.direction_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemotePingResponse extends GeneratedMessageV3 implements RemotePingResponseOrBuilder {
        public static final int VAL1_FIELD_NUMBER = 1;
        private static final RemotePingResponse DEFAULT_INSTANCE = new RemotePingResponse();
        private static final Parser<RemotePingResponse> PARSER = new AbstractParser<RemotePingResponse>() {

            @Override
            public RemotePingResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemotePingResponse(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int val1_;

        private RemotePingResponse(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemotePingResponse() {
            this.memoizedIsInitialized = -1;
        }


//        private RemotePingResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.val1_ = codedInputStream.readInt32();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemotePingResponse(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            val1_ = input.readInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemotePingResponse_descriptor;
        }

        public static RemotePingResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemotePingResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemotePingResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemotePingResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemotePingResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemotePingResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemotePingResponse parseFrom(InputStream inputStream) throws IOException {
            return (RemotePingResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemotePingResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemotePingResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemotePingResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemotePingResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemotePingResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemotePingResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemotePingResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemotePingResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemotePingResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemotePingResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemotePingResponse remotePingResponse) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remotePingResponse);
        }

        public static RemotePingResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemotePingResponse> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemotePingResponse();
        }

        @Override
        public UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemotePingResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(RemotePingResponse.class, Builder.class);
        }

        @Override
        public int getVal1() {
            return this.val1_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.val1_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.val1_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemotePingResponse)) {
                return super.equals(obj);
            }
            RemotePingResponse remotePingResponse = (RemotePingResponse) obj;
            if (getVal1() == remotePingResponse.getVal1() && this.unknownFields.equals(remotePingResponse.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getVal1()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemotePingResponse> getParserForType() {
            return PARSER;
        }

        @Override
        public RemotePingResponse getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemotePingResponseOrBuilder {
            private int val1_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemotePingResponse_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemotePingResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(RemotePingResponse.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemotePingResponse.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.val1_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemotePingResponse_descriptor;
            }

            @Override
            public RemotePingResponse getDefaultInstanceForType() {
                return RemotePingResponse.getDefaultInstance();
            }

            @Override
            public RemotePingResponse build() {
                RemotePingResponse buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemotePingResponse buildPartial() {
                RemotePingResponse remotePingResponse = new RemotePingResponse(this);
                remotePingResponse.val1_ = this.val1_;
                onBuilt();
                return remotePingResponse;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemotePingResponse) {
                    return mergeFrom((RemotePingResponse) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemotePingResponse remotePingResponse) {
                if (remotePingResponse == RemotePingResponse.getDefaultInstance()) {
                    return this;
                }
                if (remotePingResponse.getVal1() != 0) {
                    setVal1(remotePingResponse.getVal1());
                }
                mergeUnknownFields(remotePingResponse.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemotePingResponse remotePingResponse = null;
                try {
                    remotePingResponse = (RemotePingResponse) RemotePingResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remotePingResponse = (RemotePingResponse) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remotePingResponse != null) {
                        mergeFrom(remotePingResponse);
                    }
                }
                return this;
            }

            @Override
            public int getVal1() {
                return this.val1_;
            }

            public Builder setVal1(int i) {
                this.val1_ = i;
                onChanged();
                return this;
            }

            public Builder clearVal1() {
                this.val1_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemotePingRequest extends GeneratedMessageV3 implements RemotePingRequestOrBuilder {
        public static final int VAL1_FIELD_NUMBER = 1;
        public static final int VAL2_FIELD_NUMBER = 2;
        private static final RemotePingRequest DEFAULT_INSTANCE = new RemotePingRequest();
        private static final Parser<RemotePingRequest> PARSER = new AbstractParser<RemotePingRequest>() {

            @Override
            public RemotePingRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemotePingRequest(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int val1_;
        private int val2_;

        private RemotePingRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemotePingRequest() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemotePingRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.val1_ = codedInputStream.readInt32();
//                        } else if (readTag == 16) {
//                            this.val2_ = codedInputStream.readInt32();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemotePingRequest(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            val1_ = input.readInt32();
                            break;
                        }
                        case 16: {

                            val2_ = input.readInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemotePingRequest_descriptor;
        }

        public static RemotePingRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemotePingRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemotePingRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemotePingRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemotePingRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemotePingRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemotePingRequest parseFrom(InputStream inputStream) throws IOException {
            return (RemotePingRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemotePingRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemotePingRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemotePingRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemotePingRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemotePingRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemotePingRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemotePingRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemotePingRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemotePingRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemotePingRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemotePingRequest remotePingRequest) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remotePingRequest);
        }

        public static RemotePingRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemotePingRequest> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemotePingRequest();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemotePingRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(RemotePingRequest.class, Builder.class);
        }

        @Override
        public int getVal1() {
            return this.val1_;
        }

        @Override
        public int getVal2() {
            return this.val2_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.val1_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            int i2 = this.val2_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(2, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.val1_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            int i4 = this.val2_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeInt32Size(2, i4);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemotePingRequest)) {
                return super.equals(obj);
            }
            RemotePingRequest remotePingRequest = (RemotePingRequest) obj;
            if (getVal1() == remotePingRequest.getVal1() && getVal2() == remotePingRequest.getVal2() && this.unknownFields.equals(remotePingRequest.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getVal1()) * 37) + 2) * 53) + getVal2()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemotePingRequest> getParserForType() {
            return PARSER;
        }

        @Override
        public RemotePingRequest getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemotePingRequestOrBuilder {
            private int val1_;
            private int val2_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemotePingRequest_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemotePingRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(RemotePingRequest.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemotePingRequest.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.val1_ = 0;
                this.val2_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemotePingRequest_descriptor;
            }

            @Override
            public RemotePingRequest getDefaultInstanceForType() {
                return RemotePingRequest.getDefaultInstance();
            }

            @Override
            public RemotePingRequest build() {
                RemotePingRequest buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemotePingRequest buildPartial() {
                RemotePingRequest remotePingRequest = new RemotePingRequest(this);
                remotePingRequest.val1_ = this.val1_;
                remotePingRequest.val2_ = this.val2_;
                onBuilt();
                return remotePingRequest;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemotePingRequest) {
                    return mergeFrom((RemotePingRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemotePingRequest remotePingRequest) {
                if (remotePingRequest == RemotePingRequest.getDefaultInstance()) {
                    return this;
                }
                if (remotePingRequest.getVal1() != 0) {
                    setVal1(remotePingRequest.getVal1());
                }
                if (remotePingRequest.getVal2() != 0) {
                    setVal2(remotePingRequest.getVal2());
                }
                mergeUnknownFields(remotePingRequest.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemotePingRequest remotePingRequest = null;
                try {
                    remotePingRequest = (RemotePingRequest) RemotePingRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remotePingRequest = (RemotePingRequest) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remotePingRequest != null) {
                        mergeFrom(remotePingRequest);
                    }
                }
                return this;
            }

            @Override
            public int getVal1() {
                return this.val1_;
            }

            public Builder setVal1(int i) {
                this.val1_ = i;
                onChanged();
                return this;
            }

            public Builder clearVal1() {
                this.val1_ = 0;
                onChanged();
                return this;
            }

            @Override
            public int getVal2() {
                return this.val2_;
            }

            public Builder setVal2(int i) {
                this.val2_ = i;
                onChanged();
                return this;
            }

            public Builder clearVal2() {
                this.val2_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteSetActive extends GeneratedMessageV3 implements RemoteSetActiveOrBuilder {
        public static final int ACTIVE_FIELD_NUMBER = 1;
        private static final RemoteSetActive DEFAULT_INSTANCE = new RemoteSetActive();
        private static final Parser<RemoteSetActive> PARSER = new AbstractParser<RemoteSetActive>() {

            @Override
            public RemoteSetActive parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteSetActive(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int active_;
        private byte memoizedIsInitialized;

        private RemoteSetActive(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteSetActive() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteSetActive(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.active_ = codedInputStream.readInt32();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteSetActive(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            active_ = input.readInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteSetActive_descriptor;
        }

        public static RemoteSetActive parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteSetActive parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteSetActive parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteSetActive parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteSetActive parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteSetActive parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteSetActive parseFrom(InputStream inputStream) throws IOException {
            return (RemoteSetActive) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteSetActive parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetActive) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteSetActive parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteSetActive) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteSetActive parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetActive) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteSetActive parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteSetActive) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteSetActive parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteSetActive) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteSetActive remoteSetActive) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteSetActive);
        }

        public static RemoteSetActive getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteSetActive> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteSetActive();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteSetActive_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteSetActive.class, Builder.class);
        }

        @Override
        public int getActive() {
            return this.active_;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.active_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.active_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteSetActive)) {
                return super.equals(obj);
            }
            RemoteSetActive remoteSetActive = (RemoteSetActive) obj;
            if (getActive() == remoteSetActive.getActive() && this.unknownFields.equals(remoteSetActive.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getActive()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteSetActive> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteSetActive getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteSetActiveOrBuilder {
            private int active_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetActive_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetActive_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteSetActive.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteSetActive.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.active_ = 0;
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteSetActive_descriptor;
            }

            @Override
            public RemoteSetActive getDefaultInstanceForType() {
                return RemoteSetActive.getDefaultInstance();
            }

            @Override
            public RemoteSetActive build() {
                RemoteSetActive buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteSetActive buildPartial() {
                RemoteSetActive remoteSetActive = new RemoteSetActive(this);
                remoteSetActive.active_ = this.active_;
                onBuilt();
                return remoteSetActive;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteSetActive) {
                    return mergeFrom((RemoteSetActive) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteSetActive remoteSetActive) {
                if (remoteSetActive == RemoteSetActive.getDefaultInstance()) {
                    return this;
                }
                if (remoteSetActive.getActive() != 0) {
                    setActive(remoteSetActive.getActive());
                }
                mergeUnknownFields(remoteSetActive.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteSetActive remoteSetActive = null;
                try {
                    remoteSetActive = (RemoteSetActive) RemoteSetActive.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteSetActive = (RemoteSetActive) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteSetActive != null) {
                        mergeFrom(remoteSetActive);
                    }
                }
                return this;
            }

            @Override
            public int getActive() {
                return this.active_;
            }

            public Builder setActive(int i) {
                this.active_ = i;
                onChanged();
                return this;
            }

            public Builder clearActive() {
                this.active_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteDeviceInfo extends GeneratedMessageV3 implements RemoteDeviceInfoOrBuilder {
        public static final int APP_VERSION_FIELD_NUMBER = 6;
        public static final int MODEL_FIELD_NUMBER = 1;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 5;
        public static final int UNKNOWN1_FIELD_NUMBER = 3;
        public static final int UNKNOWN2_FIELD_NUMBER = 4;
        public static final int VENDOR_FIELD_NUMBER = 2;
        private static final RemoteDeviceInfo DEFAULT_INSTANCE = new RemoteDeviceInfo();
        private static final Parser<RemoteDeviceInfo> PARSER = new AbstractParser<RemoteDeviceInfo>() {

            @Override
            public RemoteDeviceInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteDeviceInfo(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object appVersion_;
        private byte memoizedIsInitialized;
        private volatile Object model_;
        private volatile Object packageName_;
        private int unknown1_;
        private volatile Object unknown2_;
        private volatile Object vendor_;

        private RemoteDeviceInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteDeviceInfo() {
            this.memoizedIsInitialized = -1;
            this.model_ = "";
            this.vendor_ = "";
            this.unknown2_ = "";
            this.packageName_ = "";
            this.appVersion_ = "";
        }


        //        private RemoteDeviceInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 10) {
//                            this.model_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 18) {
//                            this.vendor_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 24) {
//                            this.unknown1_ = codedInputStream.readInt32();
//                        } else if (readTag == 34) {
//                            this.unknown2_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 42) {
//                            this.packageName_ = codedInputStream.readStringRequireUtf8();
//                        } else if (readTag == 50) {
//                            this.appVersion_ = codedInputStream.readStringRequireUtf8();
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteDeviceInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            String s = input.readStringRequireUtf8();

                            model_ = s;
                            break;
                        }
                        case 18: {
                            String s = input.readStringRequireUtf8();

                            vendor_ = s;
                            break;
                        }
                        case 24: {

                            unknown1_ = input.readInt32();
                            break;
                        }
                        case 34: {
                            String s = input.readStringRequireUtf8();

                            unknown2_ = s;
                            break;
                        }
                        case 42: {
                            String s = input.readStringRequireUtf8();

                            packageName_ = s;
                            break;
                        }
                        case 50: {
                            String s = input.readStringRequireUtf8();

                            appVersion_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteDeviceInfo_descriptor;
        }

        public static RemoteDeviceInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteDeviceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteDeviceInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteDeviceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteDeviceInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteDeviceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteDeviceInfo parseFrom(InputStream inputStream) throws IOException {
            return (RemoteDeviceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteDeviceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteDeviceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteDeviceInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteDeviceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteDeviceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteDeviceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteDeviceInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteDeviceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteDeviceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteDeviceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteDeviceInfo remoteDeviceInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteDeviceInfo);
        }

        public static RemoteDeviceInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteDeviceInfo> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteDeviceInfo();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteDeviceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteDeviceInfo.class, Builder.class);
        }

        @Override
        public String getModel() {
            Object obj = this.model_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.model_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getModelBytes() {
            Object obj = this.model_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.model_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public String getVendor() {
            Object obj = this.vendor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.vendor_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getVendorBytes() {
            Object obj = this.vendor_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.vendor_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public int getUnknown1() {
            return this.unknown1_;
        }

        @Override
        public String getUnknown2() {
            Object obj = this.unknown2_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.unknown2_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getUnknown2Bytes() {
            Object obj = this.unknown2_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.unknown2_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public String getPackageName() {
            Object obj = this.packageName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.packageName_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getPackageNameBytes() {
            Object obj = this.packageName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.packageName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public String getAppVersion() {
            Object obj = this.appVersion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.appVersion_ = stringUtf8;
            return stringUtf8;
        }

        @Override
        public ByteString getAppVersionBytes() {
            Object obj = this.appVersion_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.appVersion_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getModelBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.model_);
            }
            if (!getVendorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.vendor_);
            }
            int i = this.unknown1_;
            if (i != 0) {
                codedOutputStream.writeInt32(3, i);
            }
            if (!getUnknown2Bytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.unknown2_);
            }
            if (!getPackageNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.packageName_);
            }
            if (!getAppVersionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.appVersion_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getModelBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.model_);
            }
            if (!getVendorBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.vendor_);
            }
            int i3 = this.unknown1_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeInt32Size(3, i3);
            }
            if (!getUnknown2Bytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(4, this.unknown2_);
            }
            if (!getPackageNameBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(5, this.packageName_);
            }
            if (!getAppVersionBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(6, this.appVersion_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteDeviceInfo)) {
                return super.equals(obj);
            }
            RemoteDeviceInfo remoteDeviceInfo = (RemoteDeviceInfo) obj;
            if (getModel().equals(remoteDeviceInfo.getModel()) && getVendor().equals(remoteDeviceInfo.getVendor()) && getUnknown1() == remoteDeviceInfo.getUnknown1() && getUnknown2().equals(remoteDeviceInfo.getUnknown2()) && getPackageName().equals(remoteDeviceInfo.getPackageName()) && getAppVersion().equals(remoteDeviceInfo.getAppVersion()) && this.unknownFields.equals(remoteDeviceInfo.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getModel().hashCode()) * 37) + 2) * 53) + getVendor().hashCode()) * 37) + 3) * 53) + getUnknown1()) * 37) + 4) * 53) + getUnknown2().hashCode()) * 37) + 5) * 53) + getPackageName().hashCode()) * 37) + 6) * 53) + getAppVersion().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteDeviceInfo> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteDeviceInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteDeviceInfoOrBuilder {
            private Object appVersion_;
            private Object model_;
            private Object packageName_;
            private int unknown1_;
            private Object unknown2_;
            private Object vendor_;

            private Builder() {
                this.model_ = "";
                this.vendor_ = "";
                this.unknown2_ = "";
                this.packageName_ = "";
                this.appVersion_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.model_ = "";
                this.vendor_ = "";
                this.unknown2_ = "";
                this.packageName_ = "";
                this.appVersion_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteDeviceInfo_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteDeviceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteDeviceInfo.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteDeviceInfo.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.model_ = "";
                this.vendor_ = "";
                this.unknown1_ = 0;
                this.unknown2_ = "";
                this.packageName_ = "";
                this.appVersion_ = "";
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteDeviceInfo_descriptor;
            }

            @Override
            public RemoteDeviceInfo getDefaultInstanceForType() {
                return RemoteDeviceInfo.getDefaultInstance();
            }

            @Override
            public RemoteDeviceInfo build() {
                RemoteDeviceInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteDeviceInfo buildPartial() {
                RemoteDeviceInfo remoteDeviceInfo = new RemoteDeviceInfo(this);
                remoteDeviceInfo.model_ = this.model_;
                remoteDeviceInfo.vendor_ = this.vendor_;
                remoteDeviceInfo.unknown1_ = this.unknown1_;
                remoteDeviceInfo.unknown2_ = this.unknown2_;
                remoteDeviceInfo.packageName_ = this.packageName_;
                remoteDeviceInfo.appVersion_ = this.appVersion_;
                onBuilt();
                return remoteDeviceInfo;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteDeviceInfo) {
                    return mergeFrom((RemoteDeviceInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteDeviceInfo remoteDeviceInfo) {
                if (remoteDeviceInfo == RemoteDeviceInfo.getDefaultInstance()) {
                    return this;
                }
                if (!remoteDeviceInfo.getModel().isEmpty()) {
                    this.model_ = remoteDeviceInfo.model_;
                    onChanged();
                }
                if (!remoteDeviceInfo.getVendor().isEmpty()) {
                    this.vendor_ = remoteDeviceInfo.vendor_;
                    onChanged();
                }
                if (remoteDeviceInfo.getUnknown1() != 0) {
                    setUnknown1(remoteDeviceInfo.getUnknown1());
                }
                if (!remoteDeviceInfo.getUnknown2().isEmpty()) {
                    this.unknown2_ = remoteDeviceInfo.unknown2_;
                    onChanged();
                }
                if (!remoteDeviceInfo.getPackageName().isEmpty()) {
                    this.packageName_ = remoteDeviceInfo.packageName_;
                    onChanged();
                }
                if (!remoteDeviceInfo.getAppVersion().isEmpty()) {
                    this.appVersion_ = remoteDeviceInfo.appVersion_;
                    onChanged();
                }
                mergeUnknownFields(remoteDeviceInfo.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteDeviceInfo remoteDeviceInfo = null;
                try {
                    remoteDeviceInfo = (RemoteDeviceInfo) RemoteDeviceInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteDeviceInfo = (RemoteDeviceInfo) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteDeviceInfo != null) {
                        mergeFrom(remoteDeviceInfo);
                    }
                }
                return this;
            }

            @Override
            public String getModel() {
                Object obj = this.model_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.model_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setModel(String str) {
                Objects.requireNonNull(str);
                this.model_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getModelBytes() {
                Object obj = this.model_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.model_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setModelBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteDeviceInfo.checkByteStringIsUtf8(byteString);
                this.model_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearModel() {
                this.model_ = RemoteDeviceInfo.getDefaultInstance().getModel();
                onChanged();
                return this;
            }

            @Override
            public String getVendor() {
                Object obj = this.vendor_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.vendor_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setVendor(String str) {
                Objects.requireNonNull(str);
                this.vendor_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getVendorBytes() {
                Object obj = this.vendor_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.vendor_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setVendorBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteDeviceInfo.checkByteStringIsUtf8(byteString);
                this.vendor_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearVendor() {
                this.vendor_ = RemoteDeviceInfo.getDefaultInstance().getVendor();
                onChanged();
                return this;
            }

            @Override
            public int getUnknown1() {
                return this.unknown1_;
            }

            public Builder setUnknown1(int i) {
                this.unknown1_ = i;
                onChanged();
                return this;
            }

            public Builder clearUnknown1() {
                this.unknown1_ = 0;
                onChanged();
                return this;
            }

            @Override
            public String getUnknown2() {
                Object obj = this.unknown2_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.unknown2_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setUnknown2(String str) {
                Objects.requireNonNull(str);
                this.unknown2_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getUnknown2Bytes() {
                Object obj = this.unknown2_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.unknown2_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setUnknown2Bytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteDeviceInfo.checkByteStringIsUtf8(byteString);
                this.unknown2_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearUnknown2() {
                this.unknown2_ = RemoteDeviceInfo.getDefaultInstance().getUnknown2();
                onChanged();
                return this;
            }

            @Override
            public String getPackageName() {
                Object obj = this.packageName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.packageName_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setPackageName(String str) {
                Objects.requireNonNull(str);
                this.packageName_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getPackageNameBytes() {
                Object obj = this.packageName_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.packageName_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setPackageNameBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteDeviceInfo.checkByteStringIsUtf8(byteString);
                this.packageName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearPackageName() {
                this.packageName_ = RemoteDeviceInfo.getDefaultInstance().getPackageName();
                onChanged();
                return this;
            }

            @Override
            public String getAppVersion() {
                Object obj = this.appVersion_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.appVersion_ = stringUtf8;
                return stringUtf8;
            }

            public Builder setAppVersion(String str) {
                Objects.requireNonNull(str);
                this.appVersion_ = str;
                onChanged();
                return this;
            }

            @Override
            public ByteString getAppVersionBytes() {
                Object obj = this.appVersion_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.appVersion_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setAppVersionBytes(ByteString byteString) {
                Objects.requireNonNull(byteString);
                RemoteDeviceInfo.checkByteStringIsUtf8(byteString);
                this.appVersion_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAppVersion() {
                this.appVersion_ = RemoteDeviceInfo.getDefaultInstance().getAppVersion();
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteConfigure extends GeneratedMessageV3 implements RemoteConfigureOrBuilder {
        public static final int CODE1_FIELD_NUMBER = 1;
        public static final int DEVICE_INFO_FIELD_NUMBER = 2;
        private static final RemoteConfigure DEFAULT_INSTANCE = new RemoteConfigure();
        private static final Parser<RemoteConfigure> PARSER = new AbstractParser<RemoteConfigure>() {

            @Override
            public RemoteConfigure parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteConfigure(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int code1_;
        private RemoteDeviceInfo deviceInfo_;
        private byte memoizedIsInitialized;

        private RemoteConfigure(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteConfigure() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteConfigure(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.code1_ = codedInputStream.readInt32();
//                        } else if (readTag == 18) {
//                            RemoteDeviceInfo.Builder builder = null;
//                            RemoteDeviceInfo remoteDeviceInfo = this.deviceInfo_;
//                            builder = remoteDeviceInfo != null ? remoteDeviceInfo.toBuilder() : builder;
//                            RemoteDeviceInfo remoteDeviceInfo2 = (RemoteDeviceInfo) codedInputStream.readMessage(RemoteDeviceInfo.parser(), extensionRegistryLite);
//                            this.deviceInfo_ = remoteDeviceInfo2;
//                            if (builder != null) {
//                                builder.mergeFrom(remoteDeviceInfo2);
//                                this.deviceInfo_ = builder.buildPartial();
//                            }
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteConfigure(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            code1_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            RemoteDeviceInfo.Builder subBuilder = null;
                            if (deviceInfo_ != null) {
                                subBuilder = deviceInfo_.toBuilder();
                            }
                            deviceInfo_ = input.readMessage(RemoteDeviceInfo.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(deviceInfo_);
                                deviceInfo_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteConfigure_descriptor;
        }

        public static RemoteConfigure parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteConfigure parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteConfigure parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteConfigure parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteConfigure parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteConfigure parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteConfigure parseFrom(InputStream inputStream) throws IOException {
            return (RemoteConfigure) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteConfigure parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteConfigure) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteConfigure parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteConfigure) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteConfigure parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteConfigure) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteConfigure parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteConfigure) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteConfigure parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteConfigure) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteConfigure remoteConfigure) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteConfigure);
        }

        public static RemoteConfigure getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteConfigure> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteConfigure();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteConfigure_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteConfigure.class, Builder.class);
        }

        @Override
        public int getCode1() {
            return this.code1_;
        }

        @Override
        public boolean hasDeviceInfo() {
            return this.deviceInfo_ != null;
        }

        @Override
        public RemoteDeviceInfo getDeviceInfo() {
            RemoteDeviceInfo remoteDeviceInfo = this.deviceInfo_;
            return remoteDeviceInfo == null ? RemoteDeviceInfo.getDefaultInstance() : remoteDeviceInfo;
        }

        @Override
        public RemoteDeviceInfoOrBuilder getDeviceInfoOrBuilder() {
            return getDeviceInfo();
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.code1_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            if (this.deviceInfo_ != null) {
                codedOutputStream.writeMessage(2, getDeviceInfo());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.code1_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            if (this.deviceInfo_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getDeviceInfo());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteConfigure)) {
                return super.equals(obj);
            }
            RemoteConfigure remoteConfigure = (RemoteConfigure) obj;
            if (getCode1() != remoteConfigure.getCode1() || hasDeviceInfo() != remoteConfigure.hasDeviceInfo()) {
                return false;
            }
            if ((!hasDeviceInfo() || getDeviceInfo().equals(remoteConfigure.getDeviceInfo())) && this.unknownFields.equals(remoteConfigure.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCode1();
            if (hasDeviceInfo()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getDeviceInfo().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteConfigure> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteConfigure getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteConfigureOrBuilder {
            private int code1_;
            private SingleFieldBuilderV3<RemoteDeviceInfo, RemoteDeviceInfo.Builder, RemoteDeviceInfoOrBuilder> deviceInfoBuilder_;
            private RemoteDeviceInfo deviceInfo_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteConfigure_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteConfigure_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteConfigure.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteConfigure.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.code1_ = 0;
                if (this.deviceInfoBuilder_ == null) {
                    this.deviceInfo_ = null;
                } else {
                    this.deviceInfo_ = null;
                    this.deviceInfoBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteConfigure_descriptor;
            }

            @Override
            public RemoteConfigure getDefaultInstanceForType() {
                return RemoteConfigure.getDefaultInstance();
            }

            @Override
            public RemoteConfigure build() {
                RemoteConfigure buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteConfigure buildPartial() {
                RemoteConfigure remoteConfigure = new RemoteConfigure(this);
                remoteConfigure.code1_ = this.code1_;
                SingleFieldBuilderV3<RemoteDeviceInfo, RemoteDeviceInfo.Builder, RemoteDeviceInfoOrBuilder> singleFieldBuilderV3 = this.deviceInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    remoteConfigure.deviceInfo_ = this.deviceInfo_;
                } else {
                    remoteConfigure.deviceInfo_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return remoteConfigure;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteConfigure) {
                    return mergeFrom((RemoteConfigure) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteConfigure remoteConfigure) {
                if (remoteConfigure == RemoteConfigure.getDefaultInstance()) {
                    return this;
                }
                if (remoteConfigure.getCode1() != 0) {
                    setCode1(remoteConfigure.getCode1());
                }
                if (remoteConfigure.hasDeviceInfo()) {
                    mergeDeviceInfo(remoteConfigure.getDeviceInfo());
                }
                mergeUnknownFields(remoteConfigure.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteConfigure remoteConfigure = null;
                try {
                    remoteConfigure = (RemoteConfigure) RemoteConfigure.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteConfigure = (RemoteConfigure) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteConfigure != null) {
                        mergeFrom(remoteConfigure);
                    }
                }
                return this;
            }

            @Override
            public int getCode1() {
                return this.code1_;
            }

            public Builder setCode1(int i) {
                this.code1_ = i;
                onChanged();
                return this;
            }

            public Builder clearCode1() {
                this.code1_ = 0;
                onChanged();
                return this;
            }

            @Override
            public boolean hasDeviceInfo() {
                return (this.deviceInfoBuilder_ == null && this.deviceInfo_ == null) ? false : true;
            }

            @Override
            public RemoteDeviceInfo getDeviceInfo() {
                SingleFieldBuilderV3<RemoteDeviceInfo, RemoteDeviceInfo.Builder, RemoteDeviceInfoOrBuilder> singleFieldBuilderV3 = this.deviceInfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteDeviceInfo remoteDeviceInfo = this.deviceInfo_;
                return remoteDeviceInfo == null ? RemoteDeviceInfo.getDefaultInstance() : remoteDeviceInfo;
            }

            public Builder setDeviceInfo(RemoteDeviceInfo remoteDeviceInfo) {
                SingleFieldBuilderV3<RemoteDeviceInfo, RemoteDeviceInfo.Builder, RemoteDeviceInfoOrBuilder> singleFieldBuilderV3 = this.deviceInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteDeviceInfo);
                    this.deviceInfo_ = remoteDeviceInfo;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteDeviceInfo);
                }
                return this;
            }

            public Builder setDeviceInfo(RemoteDeviceInfo.Builder builder) {
                SingleFieldBuilderV3<RemoteDeviceInfo, RemoteDeviceInfo.Builder, RemoteDeviceInfoOrBuilder> singleFieldBuilderV3 = this.deviceInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.deviceInfo_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeDeviceInfo(RemoteDeviceInfo remoteDeviceInfo) {
                SingleFieldBuilderV3<RemoteDeviceInfo, RemoteDeviceInfo.Builder, RemoteDeviceInfoOrBuilder> singleFieldBuilderV3 = this.deviceInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteDeviceInfo remoteDeviceInfo2 = this.deviceInfo_;
                    if (remoteDeviceInfo2 != null) {
                        this.deviceInfo_ = RemoteDeviceInfo.newBuilder(remoteDeviceInfo2).mergeFrom(remoteDeviceInfo).buildPartial();
                    } else {
                        this.deviceInfo_ = remoteDeviceInfo;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteDeviceInfo);
                }
                return this;
            }

            public Builder clearDeviceInfo() {
                if (this.deviceInfoBuilder_ == null) {
                    this.deviceInfo_ = null;
                    onChanged();
                } else {
                    this.deviceInfo_ = null;
                    this.deviceInfoBuilder_ = null;
                }
                return this;
            }

            public RemoteDeviceInfo.Builder getDeviceInfoBuilder() {
                onChanged();
                return getDeviceInfoFieldBuilder().getBuilder();
            }

            @Override
            public RemoteDeviceInfoOrBuilder getDeviceInfoOrBuilder() {
                SingleFieldBuilderV3<RemoteDeviceInfo, RemoteDeviceInfo.Builder, RemoteDeviceInfoOrBuilder> singleFieldBuilderV3 = this.deviceInfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteDeviceInfo remoteDeviceInfo = this.deviceInfo_;
                return remoteDeviceInfo == null ? RemoteDeviceInfo.getDefaultInstance() : remoteDeviceInfo;
            }

            private SingleFieldBuilderV3<RemoteDeviceInfo, RemoteDeviceInfo.Builder, RemoteDeviceInfoOrBuilder> getDeviceInfoFieldBuilder() {
                if (this.deviceInfoBuilder_ == null) {
                    this.deviceInfoBuilder_ = new SingleFieldBuilderV3<>(getDeviceInfo(), getParentForChildren(), isClean());
                    this.deviceInfo_ = null;
                }
                return this.deviceInfoBuilder_;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteError extends GeneratedMessageV3 implements RemoteErrorOrBuilder {
        public static final int MESSAGE_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final RemoteError DEFAULT_INSTANCE = new RemoteError();
        private static final Parser<RemoteError> PARSER = new AbstractParser<RemoteError>() {

            @Override
            public RemoteError parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteError(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private RemoteMessage message_;
        private boolean value_;

        private RemoteError(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteError() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteError(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    if (readTag != 0) {
//                        if (readTag == 8) {
//                            this.value_ = codedInputStream.readBool();
//                        } else if (readTag == 18) {
//                            RemoteMessage.Builder builder = null;
//                            RemoteMessage remoteMessage = this.message_;
//                            builder = remoteMessage != null ? remoteMessage.toBuilder() : builder;
//                            RemoteMessage remoteMessage2 = (RemoteMessage) codedInputStream.readMessage(RemoteMessage.parser(), extensionRegistryLite);
//                            this.message_ = remoteMessage2;
//                            if (builder != null) {
//                                builder.mergeFrom(remoteMessage2);
//                                this.message_ = builder.buildPartial();
//                            }
//                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                        }
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteError(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            value_ = input.readBool();
                            break;
                        }
                        case 18: {
                            RemoteMessage.Builder subBuilder = null;
                            if (message_ != null) {
                                subBuilder = message_.toBuilder();
                            }
                            message_ = input.readMessage(RemoteMessage.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(message_);
                                message_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteError_descriptor;
        }

        public static RemoteError parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteError parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteError parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteError parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteError parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteError parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteError parseFrom(InputStream inputStream) throws IOException {
            return (RemoteError) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteError parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteError) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteError parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteError) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteError parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteError) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteError parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteError) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteError parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteError) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteError remoteError) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteError);
        }

        public static RemoteError getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteError> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteError();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteError_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteError.class, Builder.class);
        }

        @Override
        public boolean getValue() {
            return this.value_;
        }

        @Override
        public boolean hasMessage() {
            return this.message_ != null;
        }

        @Override
        public RemoteMessage getMessage() {
            RemoteMessage remoteMessage = this.message_;
            return remoteMessage == null ? RemoteMessage.getDefaultInstance() : remoteMessage;
        }

        @Override
        public RemoteMessageOrBuilder getMessageOrBuilder() {
            return getMessage();
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.value_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (this.message_ != null) {
                codedOutputStream.writeMessage(2, getMessage());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.value_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            if (this.message_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getMessage());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteError)) {
                return super.equals(obj);
            }
            RemoteError remoteError = (RemoteError) obj;
            if (getValue() != remoteError.getValue() || hasMessage() != remoteError.hasMessage()) {
                return false;
            }
            if ((!hasMessage() || getMessage().equals(remoteError.getMessage())) && this.unknownFields.equals(remoteError.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getValue());
            if (hasMessage()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getMessage().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteError> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteError getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteErrorOrBuilder {
            private SingleFieldBuilderV3<RemoteMessage, RemoteMessage.Builder, RemoteMessageOrBuilder> messageBuilder_;
            private RemoteMessage message_;
            private boolean value_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteError_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteError_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteError.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteError.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                this.value_ = false;
                if (this.messageBuilder_ == null) {
                    this.message_ = null;
                } else {
                    this.message_ = null;
                    this.messageBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteError_descriptor;
            }

            @Override
            public RemoteError getDefaultInstanceForType() {
                return RemoteError.getDefaultInstance();
            }

            @Override
            public RemoteError build() {
                RemoteError buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteError buildPartial() {
                RemoteError remoteError = new RemoteError(this);
                remoteError.value_ = this.value_;
                SingleFieldBuilderV3<RemoteMessage, RemoteMessage.Builder, RemoteMessageOrBuilder> singleFieldBuilderV3 = this.messageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    remoteError.message_ = this.message_;
                } else {
                    remoteError.message_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return remoteError;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteError) {
                    return mergeFrom((RemoteError) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteError remoteError) {
                if (remoteError == RemoteError.getDefaultInstance()) {
                    return this;
                }
                if (remoteError.getValue()) {
                    setValue(remoteError.getValue());
                }
                if (remoteError.hasMessage()) {
                    mergeMessage(remoteError.getMessage());
                }
                mergeUnknownFields(remoteError.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteError remoteError = null;
                try {
                    remoteError = (RemoteError) RemoteError.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteError = (RemoteError) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteError != null) {
                        mergeFrom(remoteError);
                    }
                }
                return this;
            }

            @Override
            public boolean getValue() {
                return this.value_;
            }

            public Builder setValue(boolean z) {
                this.value_ = z;
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.value_ = false;
                onChanged();
                return this;
            }

            @Override
            public boolean hasMessage() {
                return (this.messageBuilder_ == null && this.message_ == null) ? false : true;
            }

            @Override
            public RemoteMessage getMessage() {
                SingleFieldBuilderV3<RemoteMessage, RemoteMessage.Builder, RemoteMessageOrBuilder> singleFieldBuilderV3 = this.messageBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteMessage remoteMessage = this.message_;
                return remoteMessage == null ? RemoteMessage.getDefaultInstance() : remoteMessage;
            }

            public Builder setMessage(RemoteMessage remoteMessage) {
                SingleFieldBuilderV3<RemoteMessage, RemoteMessage.Builder, RemoteMessageOrBuilder> singleFieldBuilderV3 = this.messageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteMessage);
                    this.message_ = remoteMessage;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteMessage);
                }
                return this;
            }

            public Builder setMessage(RemoteMessage.Builder builder) {
                SingleFieldBuilderV3<RemoteMessage, RemoteMessage.Builder, RemoteMessageOrBuilder> singleFieldBuilderV3 = this.messageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.message_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeMessage(RemoteMessage remoteMessage) {
                SingleFieldBuilderV3<RemoteMessage, RemoteMessage.Builder, RemoteMessageOrBuilder> singleFieldBuilderV3 = this.messageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteMessage remoteMessage2 = this.message_;
                    if (remoteMessage2 != null) {
                        this.message_ = RemoteMessage.newBuilder(remoteMessage2).mergeFrom(remoteMessage).buildPartial();
                    } else {
                        this.message_ = remoteMessage;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteMessage);
                }
                return this;
            }

            public Builder clearMessage() {
                if (this.messageBuilder_ == null) {
                    this.message_ = null;
                    onChanged();
                } else {
                    this.message_ = null;
                    this.messageBuilder_ = null;
                }
                return this;
            }

            public RemoteMessage.Builder getMessageBuilder() {
                onChanged();
                return getMessageFieldBuilder().getBuilder();
            }

            @Override
            public RemoteMessageOrBuilder getMessageOrBuilder() {
                SingleFieldBuilderV3<RemoteMessage, RemoteMessage.Builder, RemoteMessageOrBuilder> singleFieldBuilderV3 = this.messageBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteMessage remoteMessage = this.message_;
                return remoteMessage == null ? RemoteMessage.getDefaultInstance() : remoteMessage;
            }

            private SingleFieldBuilderV3<RemoteMessage, RemoteMessage.Builder, RemoteMessageOrBuilder> getMessageFieldBuilder() {
                if (this.messageBuilder_ == null) {
                    this.messageBuilder_ = new SingleFieldBuilderV3<>(getMessage(), getParentForChildren(), isClean());
                    this.message_ = null;
                }
                return this.messageBuilder_;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RemoteMessage extends GeneratedMessageV3 implements RemoteMessageOrBuilder {
        public static final int REMOTE_ADJUST_VOLUME_LEVEL_FIELD_NUMBER = 51;
        public static final int REMOTE_APP_LINK_LAUNCH_REQUEST_FIELD_NUMBER = 90;
        public static final int REMOTE_CONFIGURE_FIELD_NUMBER = 1;
        public static final int REMOTE_ERROR_FIELD_NUMBER = 3;
        public static final int REMOTE_IME_BATCH_EDIT_FIELD_NUMBER = 21;
        public static final int REMOTE_IME_KEY_INJECT_FIELD_NUMBER = 20;
        public static final int REMOTE_IME_SHOW_REQUEST_FIELD_NUMBER = 22;
        public static final int REMOTE_KEY_INJECT_FIELD_NUMBER = 10;
        public static final int REMOTE_PING_REQUEST_FIELD_NUMBER = 8;
        public static final int REMOTE_PING_RESPONSE_FIELD_NUMBER = 9;
        public static final int REMOTE_RESET_PREFERRED_AUDIO_DEVICE_FIELD_NUMBER = 61;
        public static final int REMOTE_SET_ACTIVE_FIELD_NUMBER = 2;
        public static final int REMOTE_SET_PREFERRED_AUDIO_DEVICE_FIELD_NUMBER = 60;
        public static final int REMOTE_SET_VOLUME_LEVEL_FIELD_NUMBER = 50;
        public static final int REMOTE_START_FIELD_NUMBER = 40;
        public static final int REMOTE_VOICE_BEGIN_FIELD_NUMBER = 30;
        public static final int REMOTE_VOICE_END_FIELD_NUMBER = 32;
        public static final int REMOTE_VOICE_PAYLOAD_FIELD_NUMBER = 31;
        private static final RemoteMessage DEFAULT_INSTANCE = new RemoteMessage();
        private static final Parser<RemoteMessage> PARSER = new AbstractParser<RemoteMessage>() {

            @Override
            public RemoteMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoteMessage(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private RemoteAdjustVolumeLevel remoteAdjustVolumeLevel_;
        private RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest_;
        private RemoteConfigure remoteConfigure_;
        private RemoteError remoteError_;
        private RemoteImeBatchEdit remoteImeBatchEdit_;
        private RemoteImeKeyInject remoteImeKeyInject_;
        private RemoteImeShowRequest remoteImeShowRequest_;
        private RemoteKeyInject remoteKeyInject_;
        private RemotePingRequest remotePingRequest_;
        private RemotePingResponse remotePingResponse_;
        private RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice_;
        private RemoteSetActive remoteSetActive_;
        private RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice_;
        private RemoteSetVolumeLevel remoteSetVolumeLevel_;
        private RemoteStart remoteStart_;
        private RemoteVoiceBegin remoteVoiceBegin_;
        private RemoteVoiceEnd remoteVoiceEnd_;
        private RemoteVoicePayload remoteVoicePayload_;

        private RemoteMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private RemoteMessage() {
            this.memoizedIsInitialized = -1;
        }


        //        private RemoteMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
//            this();
//            Objects.requireNonNull(extensionRegistryLite);
//            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
//            boolean z = false;
//            while (!z) {
//                try {
//                    int readTag = codedInputStream.readTag();
//                    RemoteConfigure.Builder builder = null;
//                    RemoteAppLinkLaunchRequest.Builder builder2 = null;
//                    RemoteResetPreferredAudioDevice.Builder builder3 = null;
//                    RemoteSetPreferredAudioDevice.Builder builder4 = null;
//                    RemoteAdjustVolumeLevel.Builder builder5 = null;
//                    RemoteSetVolumeLevel.Builder builder6 = null;
//                    RemoteStart.Builder builder7 = null;
//                    RemoteVoiceEnd.Builder builder8 = null;
//                    RemoteVoicePayload.Builder builder9 = null;
//                    RemoteVoiceBegin.Builder builder10 = null;
//                    RemoteImeShowRequest.Builder builder11 = null;
//                    RemoteImeBatchEdit.Builder builder12 = null;
//                    RemoteImeKeyInject.Builder builder13 = null;
//                    RemoteKeyInject.Builder builder14 = null;
//                    RemotePingResponse.Builder builder15 = null;
//                    RemotePingRequest.Builder builder16 = null;
//                    RemoteError.Builder builder17 = null;
//                    RemoteSetActive.Builder builder18 = null;
//                    switch (readTag) {
//                        case 0:
//                            break;
//                        case 10:
//                            RemoteConfigure remoteConfigure = this.remoteConfigure_;
//                            builder = remoteConfigure != null ? remoteConfigure.toBuilder() : builder;
//                            RemoteConfigure remoteConfigure2 = (RemoteConfigure) codedInputStream.readMessage(RemoteConfigure.parser(), extensionRegistryLite);
//                            this.remoteConfigure_ = remoteConfigure2;
//                            if (builder != null) {
//                                builder.mergeFrom(remoteConfigure2);
//                                this.remoteConfigure_ = builder.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 18:
//                            RemoteSetActive remoteSetActive = this.remoteSetActive_;
//                            builder18 = remoteSetActive != null ? remoteSetActive.toBuilder() : builder18;
//                            RemoteSetActive remoteSetActive2 = (RemoteSetActive) codedInputStream.readMessage(RemoteSetActive.parser(), extensionRegistryLite);
//                            this.remoteSetActive_ = remoteSetActive2;
//                            if (builder18 != null) {
//                                builder18.mergeFrom(remoteSetActive2);
//                                this.remoteSetActive_ = builder18.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 26:
//                            RemoteError remoteError = this.remoteError_;
//                            builder17 = remoteError != null ? remoteError.toBuilder() : builder17;
//                            RemoteError remoteError2 = (RemoteError) codedInputStream.readMessage(RemoteError.parser(), extensionRegistryLite);
//                            this.remoteError_ = remoteError2;
//                            if (builder17 != null) {
//                                builder17.mergeFrom(remoteError2);
//                                this.remoteError_ = builder17.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 66:
//                            RemotePingRequest remotePingRequest = this.remotePingRequest_;
//                            builder16 = remotePingRequest != null ? remotePingRequest.toBuilder() : builder16;
//                            RemotePingRequest remotePingRequest2 = (RemotePingRequest) codedInputStream.readMessage(RemotePingRequest.parser(), extensionRegistryLite);
//                            this.remotePingRequest_ = remotePingRequest2;
//                            if (builder16 != null) {
//                                builder16.mergeFrom(remotePingRequest2);
//                                this.remotePingRequest_ = builder16.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 74:
//                            RemotePingResponse remotePingResponse = this.remotePingResponse_;
//                            builder15 = remotePingResponse != null ? remotePingResponse.toBuilder() : builder15;
//                            RemotePingResponse remotePingResponse2 = (RemotePingResponse) codedInputStream.readMessage(RemotePingResponse.parser(), extensionRegistryLite);
//                            this.remotePingResponse_ = remotePingResponse2;
//                            if (builder15 != null) {
//                                builder15.mergeFrom(remotePingResponse2);
//                                this.remotePingResponse_ = builder15.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 82:
//                            RemoteKeyInject remoteKeyInject = this.remoteKeyInject_;
//                            builder14 = remoteKeyInject != null ? remoteKeyInject.toBuilder() : builder14;
//                            RemoteKeyInject remoteKeyInject2 = (RemoteKeyInject) codedInputStream.readMessage(RemoteKeyInject.parser(), extensionRegistryLite);
//                            this.remoteKeyInject_ = remoteKeyInject2;
//                            if (builder14 != null) {
//                                builder14.mergeFrom(remoteKeyInject2);
//                                this.remoteKeyInject_ = builder14.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case KEYCODE_NUMPAD_LEFT_PAREN_VALUE:
//                            RemoteImeKeyInject remoteImeKeyInject = this.remoteImeKeyInject_;
//                            builder13 = remoteImeKeyInject != null ? remoteImeKeyInject.toBuilder() : builder13;
//                            RemoteImeKeyInject remoteImeKeyInject2 = (RemoteImeKeyInject) codedInputStream.readMessage(RemoteImeKeyInject.parser(), extensionRegistryLite);
//                            this.remoteImeKeyInject_ = remoteImeKeyInject2;
//                            if (builder13 != null) {
//                                builder13.mergeFrom(remoteImeKeyInject2);
//                                this.remoteImeKeyInject_ = builder13.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case KEYCODE_TV_VALUE:
//                            RemoteImeBatchEdit remoteImeBatchEdit = this.remoteImeBatchEdit_;
//                            builder12 = remoteImeBatchEdit != null ? remoteImeBatchEdit.toBuilder() : builder12;
//                            RemoteImeBatchEdit remoteImeBatchEdit2 = (RemoteImeBatchEdit) codedInputStream.readMessage(RemoteImeBatchEdit.parser(), extensionRegistryLite);
//                            this.remoteImeBatchEdit_ = remoteImeBatchEdit2;
//                            if (builder12 != null) {
//                                builder12.mergeFrom(remoteImeBatchEdit2);
//                                this.remoteImeBatchEdit_ = builder12.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case KEYCODE_TV_INPUT_VALUE:
//                            RemoteImeShowRequest remoteImeShowRequest = this.remoteImeShowRequest_;
//                            builder11 = remoteImeShowRequest != null ? remoteImeShowRequest.toBuilder() : builder11;
//                            RemoteImeShowRequest remoteImeShowRequest2 = (RemoteImeShowRequest) codedInputStream.readMessage(RemoteImeShowRequest.parser(), extensionRegistryLite);
//                            this.remoteImeShowRequest_ = remoteImeShowRequest2;
//                            if (builder11 != null) {
//                                builder11.mergeFrom(remoteImeShowRequest2);
//                                this.remoteImeShowRequest_ = builder11.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case KEYCODE_TV_ANTENNA_CABLE_VALUE:
//                            RemoteVoiceBegin remoteVoiceBegin = this.remoteVoiceBegin_;
//                            builder10 = remoteVoiceBegin != null ? remoteVoiceBegin.toBuilder() : builder10;
//                            RemoteVoiceBegin remoteVoiceBegin2 = (RemoteVoiceBegin) codedInputStream.readMessage(RemoteVoiceBegin.parser(), extensionRegistryLite);
//                            this.remoteVoiceBegin_ = remoteVoiceBegin2;
//                            if (builder10 != null) {
//                                builder10.mergeFrom(remoteVoiceBegin2);
//                                this.remoteVoiceBegin_ = builder10.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 250:
//                            RemoteVoicePayload remoteVoicePayload = this.remoteVoicePayload_;
//                            builder9 = remoteVoicePayload != null ? remoteVoicePayload.toBuilder() : builder9;
//                            RemoteVoicePayload remoteVoicePayload2 = (RemoteVoicePayload) codedInputStream.readMessage(RemoteVoicePayload.parser(), extensionRegistryLite);
//                            this.remoteVoicePayload_ = remoteVoicePayload2;
//                            if (builder9 != null) {
//                                builder9.mergeFrom(remoteVoicePayload2);
//                                this.remoteVoicePayload_ = builder9.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 258:
//                            RemoteVoiceEnd remoteVoiceEnd = this.remoteVoiceEnd_;
//                            builder8 = remoteVoiceEnd != null ? remoteVoiceEnd.toBuilder() : builder8;
//                            RemoteVoiceEnd remoteVoiceEnd2 = (RemoteVoiceEnd) codedInputStream.readMessage(RemoteVoiceEnd.parser(), extensionRegistryLite);
//                            this.remoteVoiceEnd_ = remoteVoiceEnd2;
//                            if (builder8 != null) {
//                                builder8.mergeFrom(remoteVoiceEnd2);
//                                this.remoteVoiceEnd_ = builder8.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case MediaError.DetailedErrorCode.DASH_NO_INIT /*{ENCODED_INT: 322}*/:
//                            RemoteStart remoteStart = this.remoteStart_;
//                            builder7 = remoteStart != null ? remoteStart.toBuilder() : builder7;
//                            RemoteStart remoteStart2 = (RemoteStart) codedInputStream.readMessage(RemoteStart.parser(), extensionRegistryLite);
//                            this.remoteStart_ = remoteStart2;
//                            if (builder7 != null) {
//                                builder7.mergeFrom(remoteStart2);
//                                this.remoteStart_ = builder7.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 402:
//                            RemoteSetVolumeLevel remoteSetVolumeLevel = this.remoteSetVolumeLevel_;
//                            builder6 = remoteSetVolumeLevel != null ? remoteSetVolumeLevel.toBuilder() : builder6;
//                            RemoteSetVolumeLevel remoteSetVolumeLevel2 = (RemoteSetVolumeLevel) codedInputStream.readMessage(RemoteSetVolumeLevel.parser(), extensionRegistryLite);
//                            this.remoteSetVolumeLevel_ = remoteSetVolumeLevel2;
//                            if (builder6 != null) {
//                                builder6.mergeFrom(remoteSetVolumeLevel2);
//                                this.remoteSetVolumeLevel_ = builder6.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 410:
//                            RemoteAdjustVolumeLevel remoteAdjustVolumeLevel = this.remoteAdjustVolumeLevel_;
//                            builder5 = remoteAdjustVolumeLevel != null ? remoteAdjustVolumeLevel.toBuilder() : builder5;
//                            RemoteAdjustVolumeLevel remoteAdjustVolumeLevel2 = (RemoteAdjustVolumeLevel) codedInputStream.readMessage(RemoteAdjustVolumeLevel.parser(), extensionRegistryLite);
//                            this.remoteAdjustVolumeLevel_ = remoteAdjustVolumeLevel2;
//                            if (builder5 != null) {
//                                builder5.mergeFrom(remoteAdjustVolumeLevel2);
//                                this.remoteAdjustVolumeLevel_ = builder5.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 482:
//                            RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice = this.remoteSetPreferredAudioDevice_;
//                            builder4 = remoteSetPreferredAudioDevice != null ? remoteSetPreferredAudioDevice.toBuilder() : builder4;
//                            RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice2 = (RemoteSetPreferredAudioDevice) codedInputStream.readMessage(RemoteSetPreferredAudioDevice.parser(), extensionRegistryLite);
//                            this.remoteSetPreferredAudioDevice_ = remoteSetPreferredAudioDevice2;
//                            if (builder4 != null) {
//                                builder4.mergeFrom(remoteSetPreferredAudioDevice2);
//                                this.remoteSetPreferredAudioDevice_ = builder4.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 490:
//                            RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice = this.remoteResetPreferredAudioDevice_;
//                            builder3 = remoteResetPreferredAudioDevice != null ? remoteResetPreferredAudioDevice.toBuilder() : builder3;
//                            RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice2 = (RemoteResetPreferredAudioDevice) codedInputStream.readMessage(RemoteResetPreferredAudioDevice.parser(), extensionRegistryLite);
//                            this.remoteResetPreferredAudioDevice_ = remoteResetPreferredAudioDevice2;
//                            if (builder3 != null) {
//                                builder3.mergeFrom(remoteResetPreferredAudioDevice2);
//                                this.remoteResetPreferredAudioDevice_ = builder3.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        case 722:
//                            RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest = this.remoteAppLinkLaunchRequest_;
//                            builder2 = remoteAppLinkLaunchRequest != null ? remoteAppLinkLaunchRequest.toBuilder() : builder2;
//                            RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest2 = (RemoteAppLinkLaunchRequest) codedInputStream.readMessage(RemoteAppLinkLaunchRequest.parser(), extensionRegistryLite);
//                            this.remoteAppLinkLaunchRequest_ = remoteAppLinkLaunchRequest2;
//                            if (builder2 != null) {
//                                builder2.mergeFrom(remoteAppLinkLaunchRequest2);
//                                this.remoteAppLinkLaunchRequest_ = builder2.buildPartial();
//                            } else {
//                                continue;
//                            }
//                        default:
//                            if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
//                                continue;
//                            }
//                            break;
//                    }
//                    z = true;
//                } catch (InvalidProtocolBufferException e) {
//                    throw e.setUnfinishedMessage(this);
//                } catch (IOException e2) {
//                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
//                } catch (Throwable th) {
//                    this.unknownFields = newBuilder.build();
//                    makeExtensionsImmutable();
//                    throw th;
//                }
//            }
//            this.unknownFields = newBuilder.build();
//            makeExtensionsImmutable();
//        }
        private RemoteMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            RemoteConfigure.Builder subBuilder = null;
                            if (remoteConfigure_ != null) {
                                subBuilder = remoteConfigure_.toBuilder();
                            }
                            remoteConfigure_ = input.readMessage(RemoteConfigure.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteConfigure_);
                                remoteConfigure_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 18: {
                            RemoteSetActive.Builder subBuilder = null;
                            if (remoteSetActive_ != null) {
                                subBuilder = remoteSetActive_.toBuilder();
                            }
                            remoteSetActive_ = input.readMessage(RemoteSetActive.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteSetActive_);
                                remoteSetActive_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 26: {
                            RemoteError.Builder subBuilder = null;
                            if (remoteError_ != null) {
                                subBuilder = remoteError_.toBuilder();
                            }
                            remoteError_ = input.readMessage(RemoteError.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteError_);
                                remoteError_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 66: {
                            RemotePingRequest.Builder subBuilder = null;
                            if (remotePingRequest_ != null) {
                                subBuilder = remotePingRequest_.toBuilder();
                            }
                            remotePingRequest_ = input.readMessage(RemotePingRequest.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remotePingRequest_);
                                remotePingRequest_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 74: {
                            RemotePingResponse.Builder subBuilder = null;
                            if (remotePingResponse_ != null) {
                                subBuilder = remotePingResponse_.toBuilder();
                            }
                            remotePingResponse_ = input.readMessage(RemotePingResponse.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remotePingResponse_);
                                remotePingResponse_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 82: {
                            RemoteKeyInject.Builder subBuilder = null;
                            if (remoteKeyInject_ != null) {
                                subBuilder = remoteKeyInject_.toBuilder();
                            }
                            remoteKeyInject_ = input.readMessage(RemoteKeyInject.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteKeyInject_);
                                remoteKeyInject_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 162: {
                            RemoteImeKeyInject.Builder subBuilder = null;
                            if (remoteImeKeyInject_ != null) {
                                subBuilder = remoteImeKeyInject_.toBuilder();
                            }
                            remoteImeKeyInject_ = input.readMessage(RemoteImeKeyInject.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteImeKeyInject_);
                                remoteImeKeyInject_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 170: {
                            RemoteImeBatchEdit.Builder subBuilder = null;
                            if (remoteImeBatchEdit_ != null) {
                                subBuilder = remoteImeBatchEdit_.toBuilder();
                            }
                            remoteImeBatchEdit_ = input.readMessage(RemoteImeBatchEdit.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteImeBatchEdit_);
                                remoteImeBatchEdit_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 178: {
                            RemoteImeShowRequest.Builder subBuilder = null;
                            if (remoteImeShowRequest_ != null) {
                                subBuilder = remoteImeShowRequest_.toBuilder();
                            }
                            remoteImeShowRequest_ = input.readMessage(RemoteImeShowRequest.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteImeShowRequest_);
                                remoteImeShowRequest_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 242: {
                            RemoteVoiceBegin.Builder subBuilder = null;
                            if (remoteVoiceBegin_ != null) {
                                subBuilder = remoteVoiceBegin_.toBuilder();
                            }
                            remoteVoiceBegin_ = input.readMessage(RemoteVoiceBegin.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteVoiceBegin_);
                                remoteVoiceBegin_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 250: {
                            RemoteVoicePayload.Builder subBuilder = null;
                            if (remoteVoicePayload_ != null) {
                                subBuilder = remoteVoicePayload_.toBuilder();
                            }
                            remoteVoicePayload_ = input.readMessage(RemoteVoicePayload.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteVoicePayload_);
                                remoteVoicePayload_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 258: {
                            RemoteVoiceEnd.Builder subBuilder = null;
                            if (remoteVoiceEnd_ != null) {
                                subBuilder = remoteVoiceEnd_.toBuilder();
                            }
                            remoteVoiceEnd_ = input.readMessage(RemoteVoiceEnd.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteVoiceEnd_);
                                remoteVoiceEnd_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 322: {
                            RemoteStart.Builder subBuilder = null;
                            if (remoteStart_ != null) {
                                subBuilder = remoteStart_.toBuilder();
                            }
                            remoteStart_ = input.readMessage(RemoteStart.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteStart_);
                                remoteStart_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 402: {
                            RemoteSetVolumeLevel.Builder subBuilder = null;
                            if (remoteSetVolumeLevel_ != null) {
                                subBuilder = remoteSetVolumeLevel_.toBuilder();
                            }
                            remoteSetVolumeLevel_ = input.readMessage(RemoteSetVolumeLevel.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteSetVolumeLevel_);
                                remoteSetVolumeLevel_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 410: {
                            RemoteAdjustVolumeLevel.Builder subBuilder = null;
                            if (remoteAdjustVolumeLevel_ != null) {
                                subBuilder = remoteAdjustVolumeLevel_.toBuilder();
                            }
                            remoteAdjustVolumeLevel_ = input.readMessage(RemoteAdjustVolumeLevel.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteAdjustVolumeLevel_);
                                remoteAdjustVolumeLevel_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 482: {
                            RemoteSetPreferredAudioDevice.Builder subBuilder = null;
                            if (remoteSetPreferredAudioDevice_ != null) {
                                subBuilder = remoteSetPreferredAudioDevice_.toBuilder();
                            }
                            remoteSetPreferredAudioDevice_ = input.readMessage(RemoteSetPreferredAudioDevice.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteSetPreferredAudioDevice_);
                                remoteSetPreferredAudioDevice_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 490: {
                            RemoteResetPreferredAudioDevice.Builder subBuilder = null;
                            if (remoteResetPreferredAudioDevice_ != null) {
                                subBuilder = remoteResetPreferredAudioDevice_.toBuilder();
                            }
                            remoteResetPreferredAudioDevice_ = input.readMessage(RemoteResetPreferredAudioDevice.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteResetPreferredAudioDevice_);
                                remoteResetPreferredAudioDevice_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 722: {
                            RemoteAppLinkLaunchRequest.Builder subBuilder = null;
                            if (remoteAppLinkLaunchRequest_ != null) {
                                subBuilder = remoteAppLinkLaunchRequest_.toBuilder();
                            }
                            remoteAppLinkLaunchRequest_ = input.readMessage(RemoteAppLinkLaunchRequest.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(remoteAppLinkLaunchRequest_);
                                remoteAppLinkLaunchRequest_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }


        public static final Descriptors.Descriptor getDescriptor() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteMessage_descriptor;
        }

        public static RemoteMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RemoteMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RemoteMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RemoteMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RemoteMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RemoteMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RemoteMessage parseFrom(InputStream inputStream) throws IOException {
            return (RemoteMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RemoteMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoteMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RemoteMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RemoteMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoteMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RemoteMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoteMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RemoteMessage remoteMessage) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(remoteMessage);
        }

        public static RemoteMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RemoteMessage> parser() {
            return PARSER;
        }

        @Override
        public Object newInstance(UnusedPrivateParameter unusedPrivateParameter) {
            return new RemoteMessage();
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Remotemessage.internal_static_com_kunal52_remote_RemoteMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteMessage.class, Builder.class);
        }

        @Override
        public boolean hasRemoteConfigure() {
            return this.remoteConfigure_ != null;
        }

        @Override
        public RemoteConfigure getRemoteConfigure() {
            RemoteConfigure remoteConfigure = this.remoteConfigure_;
            return remoteConfigure == null ? RemoteConfigure.getDefaultInstance() : remoteConfigure;
        }

        @Override
        public RemoteConfigureOrBuilder getRemoteConfigureOrBuilder() {
            return getRemoteConfigure();
        }

        @Override
        public boolean hasRemoteSetActive() {
            return this.remoteSetActive_ != null;
        }

        @Override
        public RemoteSetActive getRemoteSetActive() {
            RemoteSetActive remoteSetActive = this.remoteSetActive_;
            return remoteSetActive == null ? RemoteSetActive.getDefaultInstance() : remoteSetActive;
        }

        @Override
        public RemoteSetActiveOrBuilder getRemoteSetActiveOrBuilder() {
            return getRemoteSetActive();
        }

        @Override
        public boolean hasRemoteError() {
            return this.remoteError_ != null;
        }

        @Override
        public RemoteError getRemoteError() {
            RemoteError remoteError = this.remoteError_;
            return remoteError == null ? RemoteError.getDefaultInstance() : remoteError;
        }

        @Override
        public RemoteErrorOrBuilder getRemoteErrorOrBuilder() {
            return getRemoteError();
        }

        @Override
        public boolean hasRemotePingRequest() {
            return this.remotePingRequest_ != null;
        }

        @Override
        public RemotePingRequest getRemotePingRequest() {
            RemotePingRequest remotePingRequest = this.remotePingRequest_;
            return remotePingRequest == null ? RemotePingRequest.getDefaultInstance() : remotePingRequest;
        }

        @Override
        public RemotePingRequestOrBuilder getRemotePingRequestOrBuilder() {
            return getRemotePingRequest();
        }

        @Override
        public boolean hasRemotePingResponse() {
            return this.remotePingResponse_ != null;
        }

        @Override
        public RemotePingResponse getRemotePingResponse() {
            RemotePingResponse remotePingResponse = this.remotePingResponse_;
            return remotePingResponse == null ? RemotePingResponse.getDefaultInstance() : remotePingResponse;
        }

        @Override
        public RemotePingResponseOrBuilder getRemotePingResponseOrBuilder() {
            return getRemotePingResponse();
        }

        @Override
        public boolean hasRemoteKeyInject() {
            return this.remoteKeyInject_ != null;
        }

        @Override
        public RemoteKeyInject getRemoteKeyInject() {
            RemoteKeyInject remoteKeyInject = this.remoteKeyInject_;
            return remoteKeyInject == null ? RemoteKeyInject.getDefaultInstance() : remoteKeyInject;
        }

        @Override
        public RemoteKeyInjectOrBuilder getRemoteKeyInjectOrBuilder() {
            return getRemoteKeyInject();
        }

        @Override
        public boolean hasRemoteImeKeyInject() {
            return this.remoteImeKeyInject_ != null;
        }

        @Override
        public RemoteImeKeyInject getRemoteImeKeyInject() {
            RemoteImeKeyInject remoteImeKeyInject = this.remoteImeKeyInject_;
            return remoteImeKeyInject == null ? RemoteImeKeyInject.getDefaultInstance() : remoteImeKeyInject;
        }

        @Override
        public RemoteImeKeyInjectOrBuilder getRemoteImeKeyInjectOrBuilder() {
            return getRemoteImeKeyInject();
        }

        @Override
        public boolean hasRemoteImeBatchEdit() {
            return this.remoteImeBatchEdit_ != null;
        }

        @Override
        public RemoteImeBatchEdit getRemoteImeBatchEdit() {
            RemoteImeBatchEdit remoteImeBatchEdit = this.remoteImeBatchEdit_;
            return remoteImeBatchEdit == null ? RemoteImeBatchEdit.getDefaultInstance() : remoteImeBatchEdit;
        }

        @Override
        public RemoteImeBatchEditOrBuilder getRemoteImeBatchEditOrBuilder() {
            return getRemoteImeBatchEdit();
        }

        @Override
        public boolean hasRemoteImeShowRequest() {
            return this.remoteImeShowRequest_ != null;
        }

        @Override
        public RemoteImeShowRequest getRemoteImeShowRequest() {
            RemoteImeShowRequest remoteImeShowRequest = this.remoteImeShowRequest_;
            return remoteImeShowRequest == null ? RemoteImeShowRequest.getDefaultInstance() : remoteImeShowRequest;
        }

        @Override
        public RemoteImeShowRequestOrBuilder getRemoteImeShowRequestOrBuilder() {
            return getRemoteImeShowRequest();
        }

        @Override
        public boolean hasRemoteVoiceBegin() {
            return this.remoteVoiceBegin_ != null;
        }

        @Override
        public RemoteVoiceBegin getRemoteVoiceBegin() {
            RemoteVoiceBegin remoteVoiceBegin = this.remoteVoiceBegin_;
            return remoteVoiceBegin == null ? RemoteVoiceBegin.getDefaultInstance() : remoteVoiceBegin;
        }

        @Override
        public RemoteVoiceBeginOrBuilder getRemoteVoiceBeginOrBuilder() {
            return getRemoteVoiceBegin();
        }

        @Override
        public boolean hasRemoteVoicePayload() {
            return this.remoteVoicePayload_ != null;
        }

        @Override
        public RemoteVoicePayload getRemoteVoicePayload() {
            RemoteVoicePayload remoteVoicePayload = this.remoteVoicePayload_;
            return remoteVoicePayload == null ? RemoteVoicePayload.getDefaultInstance() : remoteVoicePayload;
        }

        @Override
        public RemoteVoicePayloadOrBuilder getRemoteVoicePayloadOrBuilder() {
            return getRemoteVoicePayload();
        }

        @Override
        public boolean hasRemoteVoiceEnd() {
            return this.remoteVoiceEnd_ != null;
        }

        @Override
        public RemoteVoiceEnd getRemoteVoiceEnd() {
            RemoteVoiceEnd remoteVoiceEnd = this.remoteVoiceEnd_;
            return remoteVoiceEnd == null ? RemoteVoiceEnd.getDefaultInstance() : remoteVoiceEnd;
        }

        @Override
        public RemoteVoiceEndOrBuilder getRemoteVoiceEndOrBuilder() {
            return getRemoteVoiceEnd();
        }

        @Override
        public boolean hasRemoteStart() {
            return this.remoteStart_ != null;
        }

        @Override
        public RemoteStart getRemoteStart() {
            RemoteStart remoteStart = this.remoteStart_;
            return remoteStart == null ? RemoteStart.getDefaultInstance() : remoteStart;
        }

        @Override
        public RemoteStartOrBuilder getRemoteStartOrBuilder() {
            return getRemoteStart();
        }

        @Override
        public boolean hasRemoteSetVolumeLevel() {
            return this.remoteSetVolumeLevel_ != null;
        }

        @Override
        public RemoteSetVolumeLevel getRemoteSetVolumeLevel() {
            RemoteSetVolumeLevel remoteSetVolumeLevel = this.remoteSetVolumeLevel_;
            return remoteSetVolumeLevel == null ? RemoteSetVolumeLevel.getDefaultInstance() : remoteSetVolumeLevel;
        }

        @Override
        public RemoteSetVolumeLevelOrBuilder getRemoteSetVolumeLevelOrBuilder() {
            return getRemoteSetVolumeLevel();
        }

        @Override
        public boolean hasRemoteAdjustVolumeLevel() {
            return this.remoteAdjustVolumeLevel_ != null;
        }

        @Override
        public RemoteAdjustVolumeLevel getRemoteAdjustVolumeLevel() {
            RemoteAdjustVolumeLevel remoteAdjustVolumeLevel = this.remoteAdjustVolumeLevel_;
            return remoteAdjustVolumeLevel == null ? RemoteAdjustVolumeLevel.getDefaultInstance() : remoteAdjustVolumeLevel;
        }

        @Override
        public RemoteAdjustVolumeLevelOrBuilder getRemoteAdjustVolumeLevelOrBuilder() {
            return getRemoteAdjustVolumeLevel();
        }

        @Override
        public boolean hasRemoteSetPreferredAudioDevice() {
            return this.remoteSetPreferredAudioDevice_ != null;
        }

        @Override
        public RemoteSetPreferredAudioDevice getRemoteSetPreferredAudioDevice() {
            RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice = this.remoteSetPreferredAudioDevice_;
            return remoteSetPreferredAudioDevice == null ? RemoteSetPreferredAudioDevice.getDefaultInstance() : remoteSetPreferredAudioDevice;
        }

        @Override
        public RemoteSetPreferredAudioDeviceOrBuilder getRemoteSetPreferredAudioDeviceOrBuilder() {
            return getRemoteSetPreferredAudioDevice();
        }

        @Override
        public boolean hasRemoteResetPreferredAudioDevice() {
            return this.remoteResetPreferredAudioDevice_ != null;
        }

        @Override
        public RemoteResetPreferredAudioDevice getRemoteResetPreferredAudioDevice() {
            RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice = this.remoteResetPreferredAudioDevice_;
            return remoteResetPreferredAudioDevice == null ? RemoteResetPreferredAudioDevice.getDefaultInstance() : remoteResetPreferredAudioDevice;
        }

        @Override
        public RemoteResetPreferredAudioDeviceOrBuilder getRemoteResetPreferredAudioDeviceOrBuilder() {
            return getRemoteResetPreferredAudioDevice();
        }

        @Override
        public boolean hasRemoteAppLinkLaunchRequest() {
            return this.remoteAppLinkLaunchRequest_ != null;
        }

        @Override
        public RemoteAppLinkLaunchRequest getRemoteAppLinkLaunchRequest() {
            RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest = this.remoteAppLinkLaunchRequest_;
            return remoteAppLinkLaunchRequest == null ? RemoteAppLinkLaunchRequest.getDefaultInstance() : remoteAppLinkLaunchRequest;
        }

        @Override
        public RemoteAppLinkLaunchRequestOrBuilder getRemoteAppLinkLaunchRequestOrBuilder() {
            return getRemoteAppLinkLaunchRequest();
        }

        @Override
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.remoteConfigure_ != null) {
                codedOutputStream.writeMessage(1, getRemoteConfigure());
            }
            if (this.remoteSetActive_ != null) {
                codedOutputStream.writeMessage(2, getRemoteSetActive());
            }
            if (this.remoteError_ != null) {
                codedOutputStream.writeMessage(3, getRemoteError());
            }
            if (this.remotePingRequest_ != null) {
                codedOutputStream.writeMessage(8, getRemotePingRequest());
            }
            if (this.remotePingResponse_ != null) {
                codedOutputStream.writeMessage(9, getRemotePingResponse());
            }
            if (this.remoteKeyInject_ != null) {
                codedOutputStream.writeMessage(10, getRemoteKeyInject());
            }
            if (this.remoteImeKeyInject_ != null) {
                codedOutputStream.writeMessage(20, getRemoteImeKeyInject());
            }
            if (this.remoteImeBatchEdit_ != null) {
                codedOutputStream.writeMessage(21, getRemoteImeBatchEdit());
            }
            if (this.remoteImeShowRequest_ != null) {
                codedOutputStream.writeMessage(22, getRemoteImeShowRequest());
            }
            if (this.remoteVoiceBegin_ != null) {
                codedOutputStream.writeMessage(30, getRemoteVoiceBegin());
            }
            if (this.remoteVoicePayload_ != null) {
                codedOutputStream.writeMessage(31, getRemoteVoicePayload());
            }
            if (this.remoteVoiceEnd_ != null) {
                codedOutputStream.writeMessage(32, getRemoteVoiceEnd());
            }
            if (this.remoteStart_ != null) {
                codedOutputStream.writeMessage(40, getRemoteStart());
            }
            if (this.remoteSetVolumeLevel_ != null) {
                codedOutputStream.writeMessage(50, getRemoteSetVolumeLevel());
            }
            if (this.remoteAdjustVolumeLevel_ != null) {
                codedOutputStream.writeMessage(51, getRemoteAdjustVolumeLevel());
            }
            if (this.remoteSetPreferredAudioDevice_ != null) {
                codedOutputStream.writeMessage(60, getRemoteSetPreferredAudioDevice());
            }
            if (this.remoteResetPreferredAudioDevice_ != null) {
                codedOutputStream.writeMessage(61, getRemoteResetPreferredAudioDevice());
            }
            if (this.remoteAppLinkLaunchRequest_ != null) {
                codedOutputStream.writeMessage(90, getRemoteAppLinkLaunchRequest());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.remoteConfigure_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getRemoteConfigure());
            }
            if (this.remoteSetActive_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getRemoteSetActive());
            }
            if (this.remoteError_ != null) {
                i2 += CodedOutputStream.computeMessageSize(3, getRemoteError());
            }
            if (this.remotePingRequest_ != null) {
                i2 += CodedOutputStream.computeMessageSize(8, getRemotePingRequest());
            }
            if (this.remotePingResponse_ != null) {
                i2 += CodedOutputStream.computeMessageSize(9, getRemotePingResponse());
            }
            if (this.remoteKeyInject_ != null) {
                i2 += CodedOutputStream.computeMessageSize(10, getRemoteKeyInject());
            }
            if (this.remoteImeKeyInject_ != null) {
                i2 += CodedOutputStream.computeMessageSize(20, getRemoteImeKeyInject());
            }
            if (this.remoteImeBatchEdit_ != null) {
                i2 += CodedOutputStream.computeMessageSize(21, getRemoteImeBatchEdit());
            }
            if (this.remoteImeShowRequest_ != null) {
                i2 += CodedOutputStream.computeMessageSize(22, getRemoteImeShowRequest());
            }
            if (this.remoteVoiceBegin_ != null) {
                i2 += CodedOutputStream.computeMessageSize(30, getRemoteVoiceBegin());
            }
            if (this.remoteVoicePayload_ != null) {
                i2 += CodedOutputStream.computeMessageSize(31, getRemoteVoicePayload());
            }
            if (this.remoteVoiceEnd_ != null) {
                i2 += CodedOutputStream.computeMessageSize(32, getRemoteVoiceEnd());
            }
            if (this.remoteStart_ != null) {
                i2 += CodedOutputStream.computeMessageSize(40, getRemoteStart());
            }
            if (this.remoteSetVolumeLevel_ != null) {
                i2 += CodedOutputStream.computeMessageSize(50, getRemoteSetVolumeLevel());
            }
            if (this.remoteAdjustVolumeLevel_ != null) {
                i2 += CodedOutputStream.computeMessageSize(51, getRemoteAdjustVolumeLevel());
            }
            if (this.remoteSetPreferredAudioDevice_ != null) {
                i2 += CodedOutputStream.computeMessageSize(60, getRemoteSetPreferredAudioDevice());
            }
            if (this.remoteResetPreferredAudioDevice_ != null) {
                i2 += CodedOutputStream.computeMessageSize(61, getRemoteResetPreferredAudioDevice());
            }
            if (this.remoteAppLinkLaunchRequest_ != null) {
                i2 += CodedOutputStream.computeMessageSize(90, getRemoteAppLinkLaunchRequest());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RemoteMessage)) {
                return super.equals(obj);
            }
            RemoteMessage remoteMessage = (RemoteMessage) obj;
            if (hasRemoteConfigure() != remoteMessage.hasRemoteConfigure()) {
                return false;
            }
            if ((hasRemoteConfigure() && !getRemoteConfigure().equals(remoteMessage.getRemoteConfigure())) || hasRemoteSetActive() != remoteMessage.hasRemoteSetActive()) {
                return false;
            }
            if ((hasRemoteSetActive() && !getRemoteSetActive().equals(remoteMessage.getRemoteSetActive())) || hasRemoteError() != remoteMessage.hasRemoteError()) {
                return false;
            }
            if ((hasRemoteError() && !getRemoteError().equals(remoteMessage.getRemoteError())) || hasRemotePingRequest() != remoteMessage.hasRemotePingRequest()) {
                return false;
            }
            if ((hasRemotePingRequest() && !getRemotePingRequest().equals(remoteMessage.getRemotePingRequest())) || hasRemotePingResponse() != remoteMessage.hasRemotePingResponse()) {
                return false;
            }
            if ((hasRemotePingResponse() && !getRemotePingResponse().equals(remoteMessage.getRemotePingResponse())) || hasRemoteKeyInject() != remoteMessage.hasRemoteKeyInject()) {
                return false;
            }
            if ((hasRemoteKeyInject() && !getRemoteKeyInject().equals(remoteMessage.getRemoteKeyInject())) || hasRemoteImeKeyInject() != remoteMessage.hasRemoteImeKeyInject()) {
                return false;
            }
            if ((hasRemoteImeKeyInject() && !getRemoteImeKeyInject().equals(remoteMessage.getRemoteImeKeyInject())) || hasRemoteImeBatchEdit() != remoteMessage.hasRemoteImeBatchEdit()) {
                return false;
            }
            if ((hasRemoteImeBatchEdit() && !getRemoteImeBatchEdit().equals(remoteMessage.getRemoteImeBatchEdit())) || hasRemoteImeShowRequest() != remoteMessage.hasRemoteImeShowRequest()) {
                return false;
            }
            if ((hasRemoteImeShowRequest() && !getRemoteImeShowRequest().equals(remoteMessage.getRemoteImeShowRequest())) || hasRemoteVoiceBegin() != remoteMessage.hasRemoteVoiceBegin()) {
                return false;
            }
            if ((hasRemoteVoiceBegin() && !getRemoteVoiceBegin().equals(remoteMessage.getRemoteVoiceBegin())) || hasRemoteVoicePayload() != remoteMessage.hasRemoteVoicePayload()) {
                return false;
            }
            if ((hasRemoteVoicePayload() && !getRemoteVoicePayload().equals(remoteMessage.getRemoteVoicePayload())) || hasRemoteVoiceEnd() != remoteMessage.hasRemoteVoiceEnd()) {
                return false;
            }
            if ((hasRemoteVoiceEnd() && !getRemoteVoiceEnd().equals(remoteMessage.getRemoteVoiceEnd())) || hasRemoteStart() != remoteMessage.hasRemoteStart()) {
                return false;
            }
            if ((hasRemoteStart() && !getRemoteStart().equals(remoteMessage.getRemoteStart())) || hasRemoteSetVolumeLevel() != remoteMessage.hasRemoteSetVolumeLevel()) {
                return false;
            }
            if ((hasRemoteSetVolumeLevel() && !getRemoteSetVolumeLevel().equals(remoteMessage.getRemoteSetVolumeLevel())) || hasRemoteAdjustVolumeLevel() != remoteMessage.hasRemoteAdjustVolumeLevel()) {
                return false;
            }
            if ((hasRemoteAdjustVolumeLevel() && !getRemoteAdjustVolumeLevel().equals(remoteMessage.getRemoteAdjustVolumeLevel())) || hasRemoteSetPreferredAudioDevice() != remoteMessage.hasRemoteSetPreferredAudioDevice()) {
                return false;
            }
            if ((hasRemoteSetPreferredAudioDevice() && !getRemoteSetPreferredAudioDevice().equals(remoteMessage.getRemoteSetPreferredAudioDevice())) || hasRemoteResetPreferredAudioDevice() != remoteMessage.hasRemoteResetPreferredAudioDevice()) {
                return false;
            }
            if ((hasRemoteResetPreferredAudioDevice() && !getRemoteResetPreferredAudioDevice().equals(remoteMessage.getRemoteResetPreferredAudioDevice())) || hasRemoteAppLinkLaunchRequest() != remoteMessage.hasRemoteAppLinkLaunchRequest()) {
                return false;
            }
            if ((!hasRemoteAppLinkLaunchRequest() || getRemoteAppLinkLaunchRequest().equals(remoteMessage.getRemoteAppLinkLaunchRequest())) && this.unknownFields.equals(remoteMessage.unknownFields)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasRemoteConfigure()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getRemoteConfigure().hashCode();
            }
            if (hasRemoteSetActive()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getRemoteSetActive().hashCode();
            }
            if (hasRemoteError()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getRemoteError().hashCode();
            }
            if (hasRemotePingRequest()) {
                hashCode = (((hashCode * 37) + 8) * 53) + getRemotePingRequest().hashCode();
            }
            if (hasRemotePingResponse()) {
                hashCode = (((hashCode * 37) + 9) * 53) + getRemotePingResponse().hashCode();
            }
            if (hasRemoteKeyInject()) {
                hashCode = (((hashCode * 37) + 10) * 53) + getRemoteKeyInject().hashCode();
            }
            if (hasRemoteImeKeyInject()) {
                hashCode = (((hashCode * 37) + 20) * 53) + getRemoteImeKeyInject().hashCode();
            }
            if (hasRemoteImeBatchEdit()) {
                hashCode = (((hashCode * 37) + 21) * 53) + getRemoteImeBatchEdit().hashCode();
            }
            if (hasRemoteImeShowRequest()) {
                hashCode = (((hashCode * 37) + 22) * 53) + getRemoteImeShowRequest().hashCode();
            }
            if (hasRemoteVoiceBegin()) {
                hashCode = (((hashCode * 37) + 30) * 53) + getRemoteVoiceBegin().hashCode();
            }
            if (hasRemoteVoicePayload()) {
                hashCode = (((hashCode * 37) + 31) * 53) + getRemoteVoicePayload().hashCode();
            }
            if (hasRemoteVoiceEnd()) {
                hashCode = (((hashCode * 37) + 32) * 53) + getRemoteVoiceEnd().hashCode();
            }
            if (hasRemoteStart()) {
                hashCode = (((hashCode * 37) + 40) * 53) + getRemoteStart().hashCode();
            }
            if (hasRemoteSetVolumeLevel()) {
                hashCode = (((hashCode * 37) + 50) * 53) + getRemoteSetVolumeLevel().hashCode();
            }
            if (hasRemoteAdjustVolumeLevel()) {
                hashCode = (((hashCode * 37) + 51) * 53) + getRemoteAdjustVolumeLevel().hashCode();
            }
            if (hasRemoteSetPreferredAudioDevice()) {
                hashCode = (((hashCode * 37) + 60) * 53) + getRemoteSetPreferredAudioDevice().hashCode();
            }
            if (hasRemoteResetPreferredAudioDevice()) {
                hashCode = (((hashCode * 37) + 61) * 53) + getRemoteResetPreferredAudioDevice().hashCode();
            }
            if (hasRemoteAppLinkLaunchRequest()) {
                hashCode = (((hashCode * 37) + 90) * 53) + getRemoteAppLinkLaunchRequest().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override
        public Parser<RemoteMessage> getParserForType() {
            return PARSER;
        }

        @Override
        public RemoteMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RemoteMessageOrBuilder {
            private SingleFieldBuilderV3<RemoteAdjustVolumeLevel, RemoteAdjustVolumeLevel.Builder, RemoteAdjustVolumeLevelOrBuilder> remoteAdjustVolumeLevelBuilder_;
            private RemoteAdjustVolumeLevel remoteAdjustVolumeLevel_;
            private SingleFieldBuilderV3<RemoteAppLinkLaunchRequest, RemoteAppLinkLaunchRequest.Builder, RemoteAppLinkLaunchRequestOrBuilder> remoteAppLinkLaunchRequestBuilder_;
            private RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest_;
            private SingleFieldBuilderV3<RemoteConfigure, RemoteConfigure.Builder, RemoteConfigureOrBuilder> remoteConfigureBuilder_;
            private RemoteConfigure remoteConfigure_;
            private SingleFieldBuilderV3<RemoteError, RemoteError.Builder, RemoteErrorOrBuilder> remoteErrorBuilder_;
            private RemoteError remoteError_;
            private SingleFieldBuilderV3<RemoteImeBatchEdit, RemoteImeBatchEdit.Builder, RemoteImeBatchEditOrBuilder> remoteImeBatchEditBuilder_;
            private RemoteImeBatchEdit remoteImeBatchEdit_;
            private SingleFieldBuilderV3<RemoteImeKeyInject, RemoteImeKeyInject.Builder, RemoteImeKeyInjectOrBuilder> remoteImeKeyInjectBuilder_;
            private RemoteImeKeyInject remoteImeKeyInject_;
            private SingleFieldBuilderV3<RemoteImeShowRequest, RemoteImeShowRequest.Builder, RemoteImeShowRequestOrBuilder> remoteImeShowRequestBuilder_;
            private RemoteImeShowRequest remoteImeShowRequest_;
            private SingleFieldBuilderV3<RemoteKeyInject, RemoteKeyInject.Builder, RemoteKeyInjectOrBuilder> remoteKeyInjectBuilder_;
            private RemoteKeyInject remoteKeyInject_;
            private SingleFieldBuilderV3<RemotePingRequest, RemotePingRequest.Builder, RemotePingRequestOrBuilder> remotePingRequestBuilder_;
            private RemotePingRequest remotePingRequest_;
            private SingleFieldBuilderV3<RemotePingResponse, RemotePingResponse.Builder, RemotePingResponseOrBuilder> remotePingResponseBuilder_;
            private RemotePingResponse remotePingResponse_;
            private SingleFieldBuilderV3<RemoteResetPreferredAudioDevice, RemoteResetPreferredAudioDevice.Builder, RemoteResetPreferredAudioDeviceOrBuilder> remoteResetPreferredAudioDeviceBuilder_;
            private RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice_;
            private SingleFieldBuilderV3<RemoteSetActive, RemoteSetActive.Builder, RemoteSetActiveOrBuilder> remoteSetActiveBuilder_;
            private RemoteSetActive remoteSetActive_;
            private SingleFieldBuilderV3<RemoteSetPreferredAudioDevice, RemoteSetPreferredAudioDevice.Builder, RemoteSetPreferredAudioDeviceOrBuilder> remoteSetPreferredAudioDeviceBuilder_;
            private RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice_;
            private SingleFieldBuilderV3<RemoteSetVolumeLevel, RemoteSetVolumeLevel.Builder, RemoteSetVolumeLevelOrBuilder> remoteSetVolumeLevelBuilder_;
            private RemoteSetVolumeLevel remoteSetVolumeLevel_;
            private SingleFieldBuilderV3<RemoteStart, RemoteStart.Builder, RemoteStartOrBuilder> remoteStartBuilder_;
            private RemoteStart remoteStart_;
            private SingleFieldBuilderV3<RemoteVoiceBegin, RemoteVoiceBegin.Builder, RemoteVoiceBeginOrBuilder> remoteVoiceBeginBuilder_;
            private RemoteVoiceBegin remoteVoiceBegin_;
            private SingleFieldBuilderV3<RemoteVoiceEnd, RemoteVoiceEnd.Builder, RemoteVoiceEndOrBuilder> remoteVoiceEndBuilder_;
            private RemoteVoiceEnd remoteVoiceEnd_;
            private SingleFieldBuilderV3<RemoteVoicePayload, RemoteVoicePayload.Builder, RemoteVoicePayloadOrBuilder> remoteVoicePayloadBuilder_;
            private RemoteVoicePayload remoteVoicePayload_;

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteMessage_descriptor;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(RemoteMessage.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RemoteMessage.alwaysUseFieldBuilders;
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.remoteConfigureBuilder_ == null) {
                    this.remoteConfigure_ = null;
                } else {
                    this.remoteConfigure_ = null;
                    this.remoteConfigureBuilder_ = null;
                }
                if (this.remoteSetActiveBuilder_ == null) {
                    this.remoteSetActive_ = null;
                } else {
                    this.remoteSetActive_ = null;
                    this.remoteSetActiveBuilder_ = null;
                }
                if (this.remoteErrorBuilder_ == null) {
                    this.remoteError_ = null;
                } else {
                    this.remoteError_ = null;
                    this.remoteErrorBuilder_ = null;
                }
                if (this.remotePingRequestBuilder_ == null) {
                    this.remotePingRequest_ = null;
                } else {
                    this.remotePingRequest_ = null;
                    this.remotePingRequestBuilder_ = null;
                }
                if (this.remotePingResponseBuilder_ == null) {
                    this.remotePingResponse_ = null;
                } else {
                    this.remotePingResponse_ = null;
                    this.remotePingResponseBuilder_ = null;
                }
                if (this.remoteKeyInjectBuilder_ == null) {
                    this.remoteKeyInject_ = null;
                } else {
                    this.remoteKeyInject_ = null;
                    this.remoteKeyInjectBuilder_ = null;
                }
                if (this.remoteImeKeyInjectBuilder_ == null) {
                    this.remoteImeKeyInject_ = null;
                } else {
                    this.remoteImeKeyInject_ = null;
                    this.remoteImeKeyInjectBuilder_ = null;
                }
                if (this.remoteImeBatchEditBuilder_ == null) {
                    this.remoteImeBatchEdit_ = null;
                } else {
                    this.remoteImeBatchEdit_ = null;
                    this.remoteImeBatchEditBuilder_ = null;
                }
                if (this.remoteImeShowRequestBuilder_ == null) {
                    this.remoteImeShowRequest_ = null;
                } else {
                    this.remoteImeShowRequest_ = null;
                    this.remoteImeShowRequestBuilder_ = null;
                }
                if (this.remoteVoiceBeginBuilder_ == null) {
                    this.remoteVoiceBegin_ = null;
                } else {
                    this.remoteVoiceBegin_ = null;
                    this.remoteVoiceBeginBuilder_ = null;
                }
                if (this.remoteVoicePayloadBuilder_ == null) {
                    this.remoteVoicePayload_ = null;
                } else {
                    this.remoteVoicePayload_ = null;
                    this.remoteVoicePayloadBuilder_ = null;
                }
                if (this.remoteVoiceEndBuilder_ == null) {
                    this.remoteVoiceEnd_ = null;
                } else {
                    this.remoteVoiceEnd_ = null;
                    this.remoteVoiceEndBuilder_ = null;
                }
                if (this.remoteStartBuilder_ == null) {
                    this.remoteStart_ = null;
                } else {
                    this.remoteStart_ = null;
                    this.remoteStartBuilder_ = null;
                }
                if (this.remoteSetVolumeLevelBuilder_ == null) {
                    this.remoteSetVolumeLevel_ = null;
                } else {
                    this.remoteSetVolumeLevel_ = null;
                    this.remoteSetVolumeLevelBuilder_ = null;
                }
                if (this.remoteAdjustVolumeLevelBuilder_ == null) {
                    this.remoteAdjustVolumeLevel_ = null;
                } else {
                    this.remoteAdjustVolumeLevel_ = null;
                    this.remoteAdjustVolumeLevelBuilder_ = null;
                }
                if (this.remoteSetPreferredAudioDeviceBuilder_ == null) {
                    this.remoteSetPreferredAudioDevice_ = null;
                } else {
                    this.remoteSetPreferredAudioDevice_ = null;
                    this.remoteSetPreferredAudioDeviceBuilder_ = null;
                }
                if (this.remoteResetPreferredAudioDeviceBuilder_ == null) {
                    this.remoteResetPreferredAudioDevice_ = null;
                } else {
                    this.remoteResetPreferredAudioDevice_ = null;
                    this.remoteResetPreferredAudioDeviceBuilder_ = null;
                }
                if (this.remoteAppLinkLaunchRequestBuilder_ == null) {
                    this.remoteAppLinkLaunchRequest_ = null;
                } else {
                    this.remoteAppLinkLaunchRequest_ = null;
                    this.remoteAppLinkLaunchRequestBuilder_ = null;
                }
                return this;
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return Remotemessage.internal_static_com_kunal52_remote_RemoteMessage_descriptor;
            }

            @Override
            public RemoteMessage getDefaultInstanceForType() {
                return RemoteMessage.getDefaultInstance();
            }

            @Override
            public RemoteMessage build() {
                RemoteMessage buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override
            public RemoteMessage buildPartial() {
                RemoteMessage remoteMessage = new RemoteMessage(this);
                SingleFieldBuilderV3<RemoteConfigure, RemoteConfigure.Builder, RemoteConfigureOrBuilder> singleFieldBuilderV3 = this.remoteConfigureBuilder_;
                if (singleFieldBuilderV3 == null) {
                    remoteMessage.remoteConfigure_ = this.remoteConfigure_;
                } else {
                    remoteMessage.remoteConfigure_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<RemoteSetActive, RemoteSetActive.Builder, RemoteSetActiveOrBuilder> singleFieldBuilderV32 = this.remoteSetActiveBuilder_;
                if (singleFieldBuilderV32 == null) {
                    remoteMessage.remoteSetActive_ = this.remoteSetActive_;
                } else {
                    remoteMessage.remoteSetActive_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<RemoteError, RemoteError.Builder, RemoteErrorOrBuilder> singleFieldBuilderV33 = this.remoteErrorBuilder_;
                if (singleFieldBuilderV33 == null) {
                    remoteMessage.remoteError_ = this.remoteError_;
                } else {
                    remoteMessage.remoteError_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<RemotePingRequest, RemotePingRequest.Builder, RemotePingRequestOrBuilder> singleFieldBuilderV34 = this.remotePingRequestBuilder_;
                if (singleFieldBuilderV34 == null) {
                    remoteMessage.remotePingRequest_ = this.remotePingRequest_;
                } else {
                    remoteMessage.remotePingRequest_ = singleFieldBuilderV34.build();
                }
                SingleFieldBuilderV3<RemotePingResponse, RemotePingResponse.Builder, RemotePingResponseOrBuilder> singleFieldBuilderV35 = this.remotePingResponseBuilder_;
                if (singleFieldBuilderV35 == null) {
                    remoteMessage.remotePingResponse_ = this.remotePingResponse_;
                } else {
                    remoteMessage.remotePingResponse_ = singleFieldBuilderV35.build();
                }
                SingleFieldBuilderV3<RemoteKeyInject, RemoteKeyInject.Builder, RemoteKeyInjectOrBuilder> singleFieldBuilderV36 = this.remoteKeyInjectBuilder_;
                if (singleFieldBuilderV36 == null) {
                    remoteMessage.remoteKeyInject_ = this.remoteKeyInject_;
                } else {
                    remoteMessage.remoteKeyInject_ = singleFieldBuilderV36.build();
                }
                SingleFieldBuilderV3<RemoteImeKeyInject, RemoteImeKeyInject.Builder, RemoteImeKeyInjectOrBuilder> singleFieldBuilderV37 = this.remoteImeKeyInjectBuilder_;
                if (singleFieldBuilderV37 == null) {
                    remoteMessage.remoteImeKeyInject_ = this.remoteImeKeyInject_;
                } else {
                    remoteMessage.remoteImeKeyInject_ = singleFieldBuilderV37.build();
                }
                SingleFieldBuilderV3<RemoteImeBatchEdit, RemoteImeBatchEdit.Builder, RemoteImeBatchEditOrBuilder> singleFieldBuilderV38 = this.remoteImeBatchEditBuilder_;
                if (singleFieldBuilderV38 == null) {
                    remoteMessage.remoteImeBatchEdit_ = this.remoteImeBatchEdit_;
                } else {
                    remoteMessage.remoteImeBatchEdit_ = singleFieldBuilderV38.build();
                }
                SingleFieldBuilderV3<RemoteImeShowRequest, RemoteImeShowRequest.Builder, RemoteImeShowRequestOrBuilder> singleFieldBuilderV39 = this.remoteImeShowRequestBuilder_;
                if (singleFieldBuilderV39 == null) {
                    remoteMessage.remoteImeShowRequest_ = this.remoteImeShowRequest_;
                } else {
                    remoteMessage.remoteImeShowRequest_ = singleFieldBuilderV39.build();
                }
                SingleFieldBuilderV3<RemoteVoiceBegin, RemoteVoiceBegin.Builder, RemoteVoiceBeginOrBuilder> singleFieldBuilderV310 = this.remoteVoiceBeginBuilder_;
                if (singleFieldBuilderV310 == null) {
                    remoteMessage.remoteVoiceBegin_ = this.remoteVoiceBegin_;
                } else {
                    remoteMessage.remoteVoiceBegin_ = singleFieldBuilderV310.build();
                }
                SingleFieldBuilderV3<RemoteVoicePayload, RemoteVoicePayload.Builder, RemoteVoicePayloadOrBuilder> singleFieldBuilderV311 = this.remoteVoicePayloadBuilder_;
                if (singleFieldBuilderV311 == null) {
                    remoteMessage.remoteVoicePayload_ = this.remoteVoicePayload_;
                } else {
                    remoteMessage.remoteVoicePayload_ = singleFieldBuilderV311.build();
                }
                SingleFieldBuilderV3<RemoteVoiceEnd, RemoteVoiceEnd.Builder, RemoteVoiceEndOrBuilder> singleFieldBuilderV312 = this.remoteVoiceEndBuilder_;
                if (singleFieldBuilderV312 == null) {
                    remoteMessage.remoteVoiceEnd_ = this.remoteVoiceEnd_;
                } else {
                    remoteMessage.remoteVoiceEnd_ = singleFieldBuilderV312.build();
                }
                SingleFieldBuilderV3<RemoteStart, RemoteStart.Builder, RemoteStartOrBuilder> singleFieldBuilderV313 = this.remoteStartBuilder_;
                if (singleFieldBuilderV313 == null) {
                    remoteMessage.remoteStart_ = this.remoteStart_;
                } else {
                    remoteMessage.remoteStart_ = singleFieldBuilderV313.build();
                }
                SingleFieldBuilderV3<RemoteSetVolumeLevel, RemoteSetVolumeLevel.Builder, RemoteSetVolumeLevelOrBuilder> singleFieldBuilderV314 = this.remoteSetVolumeLevelBuilder_;
                if (singleFieldBuilderV314 == null) {
                    remoteMessage.remoteSetVolumeLevel_ = this.remoteSetVolumeLevel_;
                } else {
                    remoteMessage.remoteSetVolumeLevel_ = singleFieldBuilderV314.build();
                }
                SingleFieldBuilderV3<RemoteAdjustVolumeLevel, RemoteAdjustVolumeLevel.Builder, RemoteAdjustVolumeLevelOrBuilder> singleFieldBuilderV315 = this.remoteAdjustVolumeLevelBuilder_;
                if (singleFieldBuilderV315 == null) {
                    remoteMessage.remoteAdjustVolumeLevel_ = this.remoteAdjustVolumeLevel_;
                } else {
                    remoteMessage.remoteAdjustVolumeLevel_ = singleFieldBuilderV315.build();
                }
                SingleFieldBuilderV3<RemoteSetPreferredAudioDevice, RemoteSetPreferredAudioDevice.Builder, RemoteSetPreferredAudioDeviceOrBuilder> singleFieldBuilderV316 = this.remoteSetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV316 == null) {
                    remoteMessage.remoteSetPreferredAudioDevice_ = this.remoteSetPreferredAudioDevice_;
                } else {
                    remoteMessage.remoteSetPreferredAudioDevice_ = singleFieldBuilderV316.build();
                }
                SingleFieldBuilderV3<RemoteResetPreferredAudioDevice, RemoteResetPreferredAudioDevice.Builder, RemoteResetPreferredAudioDeviceOrBuilder> singleFieldBuilderV317 = this.remoteResetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV317 == null) {
                    remoteMessage.remoteResetPreferredAudioDevice_ = this.remoteResetPreferredAudioDevice_;
                } else {
                    remoteMessage.remoteResetPreferredAudioDevice_ = singleFieldBuilderV317.build();
                }
                SingleFieldBuilderV3<RemoteAppLinkLaunchRequest, RemoteAppLinkLaunchRequest.Builder, RemoteAppLinkLaunchRequestOrBuilder> singleFieldBuilderV318 = this.remoteAppLinkLaunchRequestBuilder_;
                if (singleFieldBuilderV318 == null) {
                    remoteMessage.remoteAppLinkLaunchRequest_ = this.remoteAppLinkLaunchRequest_;
                } else {
                    remoteMessage.remoteAppLinkLaunchRequest_ = singleFieldBuilderV318.build();
                }
                onBuilt();
                return remoteMessage;
            }

            @Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @Override
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override
            public Builder mergeFrom(Message message) {
                if (message instanceof RemoteMessage) {
                    return mergeFrom((RemoteMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RemoteMessage remoteMessage) {
                if (remoteMessage == RemoteMessage.getDefaultInstance()) {
                    return this;
                }
                if (remoteMessage.hasRemoteConfigure()) {
                    mergeRemoteConfigure(remoteMessage.getRemoteConfigure());
                }
                if (remoteMessage.hasRemoteSetActive()) {
                    mergeRemoteSetActive(remoteMessage.getRemoteSetActive());
                }
                if (remoteMessage.hasRemoteError()) {
                    mergeRemoteError(remoteMessage.getRemoteError());
                }
                if (remoteMessage.hasRemotePingRequest()) {
                    mergeRemotePingRequest(remoteMessage.getRemotePingRequest());
                }
                if (remoteMessage.hasRemotePingResponse()) {
                    mergeRemotePingResponse(remoteMessage.getRemotePingResponse());
                }
                if (remoteMessage.hasRemoteKeyInject()) {
                    mergeRemoteKeyInject(remoteMessage.getRemoteKeyInject());
                }
                if (remoteMessage.hasRemoteImeKeyInject()) {
                    mergeRemoteImeKeyInject(remoteMessage.getRemoteImeKeyInject());
                }
                if (remoteMessage.hasRemoteImeBatchEdit()) {
                    mergeRemoteImeBatchEdit(remoteMessage.getRemoteImeBatchEdit());
                }
                if (remoteMessage.hasRemoteImeShowRequest()) {
                    mergeRemoteImeShowRequest(remoteMessage.getRemoteImeShowRequest());
                }
                if (remoteMessage.hasRemoteVoiceBegin()) {
                    mergeRemoteVoiceBegin(remoteMessage.getRemoteVoiceBegin());
                }
                if (remoteMessage.hasRemoteVoicePayload()) {
                    mergeRemoteVoicePayload(remoteMessage.getRemoteVoicePayload());
                }
                if (remoteMessage.hasRemoteVoiceEnd()) {
                    mergeRemoteVoiceEnd(remoteMessage.getRemoteVoiceEnd());
                }
                if (remoteMessage.hasRemoteStart()) {
                    mergeRemoteStart(remoteMessage.getRemoteStart());
                }
                if (remoteMessage.hasRemoteSetVolumeLevel()) {
                    mergeRemoteSetVolumeLevel(remoteMessage.getRemoteSetVolumeLevel());
                }
                if (remoteMessage.hasRemoteAdjustVolumeLevel()) {
                    mergeRemoteAdjustVolumeLevel(remoteMessage.getRemoteAdjustVolumeLevel());
                }
                if (remoteMessage.hasRemoteSetPreferredAudioDevice()) {
                    mergeRemoteSetPreferredAudioDevice(remoteMessage.getRemoteSetPreferredAudioDevice());
                }
                if (remoteMessage.hasRemoteResetPreferredAudioDevice()) {
                    mergeRemoteResetPreferredAudioDevice(remoteMessage.getRemoteResetPreferredAudioDevice());
                }
                if (remoteMessage.hasRemoteAppLinkLaunchRequest()) {
                    mergeRemoteAppLinkLaunchRequest(remoteMessage.getRemoteAppLinkLaunchRequest());
                }
                mergeUnknownFields(remoteMessage.unknownFields);
                onChanged();
                return this;
            }

            /* CODE */
            @Override
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RemoteMessage remoteMessage = null;
                try {
                    remoteMessage = (RemoteMessage) RemoteMessage.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                } catch (InvalidProtocolBufferException e) {
                    remoteMessage = (RemoteMessage) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (remoteMessage != null) {
                        mergeFrom(remoteMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasRemoteConfigure() {
                return (this.remoteConfigureBuilder_ == null && this.remoteConfigure_ == null) ? false : true;
            }

            @Override
            public RemoteConfigure getRemoteConfigure() {
                SingleFieldBuilderV3<RemoteConfigure, RemoteConfigure.Builder, RemoteConfigureOrBuilder> singleFieldBuilderV3 = this.remoteConfigureBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteConfigure remoteConfigure = this.remoteConfigure_;
                return remoteConfigure == null ? RemoteConfigure.getDefaultInstance() : remoteConfigure;
            }

            public Builder setRemoteConfigure(RemoteConfigure remoteConfigure) {
                SingleFieldBuilderV3<RemoteConfigure, RemoteConfigure.Builder, RemoteConfigureOrBuilder> singleFieldBuilderV3 = this.remoteConfigureBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteConfigure);
                    this.remoteConfigure_ = remoteConfigure;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteConfigure);
                }
                return this;
            }

            public Builder setRemoteConfigure(RemoteConfigure.Builder builder) {
                SingleFieldBuilderV3<RemoteConfigure, RemoteConfigure.Builder, RemoteConfigureOrBuilder> singleFieldBuilderV3 = this.remoteConfigureBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteConfigure_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteConfigure(RemoteConfigure remoteConfigure) {
                SingleFieldBuilderV3<RemoteConfigure, RemoteConfigure.Builder, RemoteConfigureOrBuilder> singleFieldBuilderV3 = this.remoteConfigureBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteConfigure remoteConfigure2 = this.remoteConfigure_;
                    if (remoteConfigure2 != null) {
                        this.remoteConfigure_ = RemoteConfigure.newBuilder(remoteConfigure2).mergeFrom(remoteConfigure).buildPartial();
                    } else {
                        this.remoteConfigure_ = remoteConfigure;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteConfigure);
                }
                return this;
            }

            public Builder clearRemoteConfigure() {
                if (this.remoteConfigureBuilder_ == null) {
                    this.remoteConfigure_ = null;
                    onChanged();
                } else {
                    this.remoteConfigure_ = null;
                    this.remoteConfigureBuilder_ = null;
                }
                return this;
            }

            public RemoteConfigure.Builder getRemoteConfigureBuilder() {
                onChanged();
                return getRemoteConfigureFieldBuilder().getBuilder();
            }

            @Override
            public RemoteConfigureOrBuilder getRemoteConfigureOrBuilder() {
                SingleFieldBuilderV3<RemoteConfigure, RemoteConfigure.Builder, RemoteConfigureOrBuilder> singleFieldBuilderV3 = this.remoteConfigureBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteConfigure remoteConfigure = this.remoteConfigure_;
                return remoteConfigure == null ? RemoteConfigure.getDefaultInstance() : remoteConfigure;
            }

            private SingleFieldBuilderV3<RemoteConfigure, RemoteConfigure.Builder, RemoteConfigureOrBuilder> getRemoteConfigureFieldBuilder() {
                if (this.remoteConfigureBuilder_ == null) {
                    this.remoteConfigureBuilder_ = new SingleFieldBuilderV3<>(getRemoteConfigure(), getParentForChildren(), isClean());
                    this.remoteConfigure_ = null;
                }
                return this.remoteConfigureBuilder_;
            }

            @Override
            public boolean hasRemoteSetActive() {
                return (this.remoteSetActiveBuilder_ == null && this.remoteSetActive_ == null) ? false : true;
            }

            @Override
            public RemoteSetActive getRemoteSetActive() {
                SingleFieldBuilderV3<RemoteSetActive, RemoteSetActive.Builder, RemoteSetActiveOrBuilder> singleFieldBuilderV3 = this.remoteSetActiveBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteSetActive remoteSetActive = this.remoteSetActive_;
                return remoteSetActive == null ? RemoteSetActive.getDefaultInstance() : remoteSetActive;
            }

            public Builder setRemoteSetActive(RemoteSetActive remoteSetActive) {
                SingleFieldBuilderV3<RemoteSetActive, RemoteSetActive.Builder, RemoteSetActiveOrBuilder> singleFieldBuilderV3 = this.remoteSetActiveBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteSetActive);
                    this.remoteSetActive_ = remoteSetActive;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteSetActive);
                }
                return this;
            }

            public Builder setRemoteSetActive(RemoteSetActive.Builder builder) {
                SingleFieldBuilderV3<RemoteSetActive, RemoteSetActive.Builder, RemoteSetActiveOrBuilder> singleFieldBuilderV3 = this.remoteSetActiveBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteSetActive_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteSetActive(RemoteSetActive remoteSetActive) {
                SingleFieldBuilderV3<RemoteSetActive, RemoteSetActive.Builder, RemoteSetActiveOrBuilder> singleFieldBuilderV3 = this.remoteSetActiveBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteSetActive remoteSetActive2 = this.remoteSetActive_;
                    if (remoteSetActive2 != null) {
                        this.remoteSetActive_ = RemoteSetActive.newBuilder(remoteSetActive2).mergeFrom(remoteSetActive).buildPartial();
                    } else {
                        this.remoteSetActive_ = remoteSetActive;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteSetActive);
                }
                return this;
            }

            public Builder clearRemoteSetActive() {
                if (this.remoteSetActiveBuilder_ == null) {
                    this.remoteSetActive_ = null;
                    onChanged();
                } else {
                    this.remoteSetActive_ = null;
                    this.remoteSetActiveBuilder_ = null;
                }
                return this;
            }

            public RemoteSetActive.Builder getRemoteSetActiveBuilder() {
                onChanged();
                return getRemoteSetActiveFieldBuilder().getBuilder();
            }

            @Override
            public RemoteSetActiveOrBuilder getRemoteSetActiveOrBuilder() {
                SingleFieldBuilderV3<RemoteSetActive, RemoteSetActive.Builder, RemoteSetActiveOrBuilder> singleFieldBuilderV3 = this.remoteSetActiveBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteSetActive remoteSetActive = this.remoteSetActive_;
                return remoteSetActive == null ? RemoteSetActive.getDefaultInstance() : remoteSetActive;
            }

            private SingleFieldBuilderV3<RemoteSetActive, RemoteSetActive.Builder, RemoteSetActiveOrBuilder> getRemoteSetActiveFieldBuilder() {
                if (this.remoteSetActiveBuilder_ == null) {
                    this.remoteSetActiveBuilder_ = new SingleFieldBuilderV3<>(getRemoteSetActive(), getParentForChildren(), isClean());
                    this.remoteSetActive_ = null;
                }
                return this.remoteSetActiveBuilder_;
            }

            @Override
            public boolean hasRemoteError() {
                return (this.remoteErrorBuilder_ == null && this.remoteError_ == null) ? false : true;
            }

            @Override
            public RemoteError getRemoteError() {
                SingleFieldBuilderV3<RemoteError, RemoteError.Builder, RemoteErrorOrBuilder> singleFieldBuilderV3 = this.remoteErrorBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteError remoteError = this.remoteError_;
                return remoteError == null ? RemoteError.getDefaultInstance() : remoteError;
            }

            public Builder setRemoteError(RemoteError remoteError) {
                SingleFieldBuilderV3<RemoteError, RemoteError.Builder, RemoteErrorOrBuilder> singleFieldBuilderV3 = this.remoteErrorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteError);
                    this.remoteError_ = remoteError;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteError);
                }
                return this;
            }

            public Builder setRemoteError(RemoteError.Builder builder) {
                SingleFieldBuilderV3<RemoteError, RemoteError.Builder, RemoteErrorOrBuilder> singleFieldBuilderV3 = this.remoteErrorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteError_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteError(RemoteError remoteError) {
                SingleFieldBuilderV3<RemoteError, RemoteError.Builder, RemoteErrorOrBuilder> singleFieldBuilderV3 = this.remoteErrorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteError remoteError2 = this.remoteError_;
                    if (remoteError2 != null) {
                        this.remoteError_ = RemoteError.newBuilder(remoteError2).mergeFrom(remoteError).buildPartial();
                    } else {
                        this.remoteError_ = remoteError;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteError);
                }
                return this;
            }

            public Builder clearRemoteError() {
                if (this.remoteErrorBuilder_ == null) {
                    this.remoteError_ = null;
                    onChanged();
                } else {
                    this.remoteError_ = null;
                    this.remoteErrorBuilder_ = null;
                }
                return this;
            }

            public RemoteError.Builder getRemoteErrorBuilder() {
                onChanged();
                return getRemoteErrorFieldBuilder().getBuilder();
            }

            @Override
            public RemoteErrorOrBuilder getRemoteErrorOrBuilder() {
                SingleFieldBuilderV3<RemoteError, RemoteError.Builder, RemoteErrorOrBuilder> singleFieldBuilderV3 = this.remoteErrorBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteError remoteError = this.remoteError_;
                return remoteError == null ? RemoteError.getDefaultInstance() : remoteError;
            }

            private SingleFieldBuilderV3<RemoteError, RemoteError.Builder, RemoteErrorOrBuilder> getRemoteErrorFieldBuilder() {
                if (this.remoteErrorBuilder_ == null) {
                    this.remoteErrorBuilder_ = new SingleFieldBuilderV3<>(getRemoteError(), getParentForChildren(), isClean());
                    this.remoteError_ = null;
                }
                return this.remoteErrorBuilder_;
            }

            @Override
            public boolean hasRemotePingRequest() {
                return (this.remotePingRequestBuilder_ == null && this.remotePingRequest_ == null) ? false : true;
            }

            @Override
            public RemotePingRequest getRemotePingRequest() {
                SingleFieldBuilderV3<RemotePingRequest, RemotePingRequest.Builder, RemotePingRequestOrBuilder> singleFieldBuilderV3 = this.remotePingRequestBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemotePingRequest remotePingRequest = this.remotePingRequest_;
                return remotePingRequest == null ? RemotePingRequest.getDefaultInstance() : remotePingRequest;
            }

            public Builder setRemotePingRequest(RemotePingRequest remotePingRequest) {
                SingleFieldBuilderV3<RemotePingRequest, RemotePingRequest.Builder, RemotePingRequestOrBuilder> singleFieldBuilderV3 = this.remotePingRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remotePingRequest);
                    this.remotePingRequest_ = remotePingRequest;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remotePingRequest);
                }
                return this;
            }

            public Builder setRemotePingRequest(RemotePingRequest.Builder builder) {
                SingleFieldBuilderV3<RemotePingRequest, RemotePingRequest.Builder, RemotePingRequestOrBuilder> singleFieldBuilderV3 = this.remotePingRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remotePingRequest_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemotePingRequest(RemotePingRequest remotePingRequest) {
                SingleFieldBuilderV3<RemotePingRequest, RemotePingRequest.Builder, RemotePingRequestOrBuilder> singleFieldBuilderV3 = this.remotePingRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemotePingRequest remotePingRequest2 = this.remotePingRequest_;
                    if (remotePingRequest2 != null) {
                        this.remotePingRequest_ = RemotePingRequest.newBuilder(remotePingRequest2).mergeFrom(remotePingRequest).buildPartial();
                    } else {
                        this.remotePingRequest_ = remotePingRequest;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remotePingRequest);
                }
                return this;
            }

            public Builder clearRemotePingRequest() {
                if (this.remotePingRequestBuilder_ == null) {
                    this.remotePingRequest_ = null;
                    onChanged();
                } else {
                    this.remotePingRequest_ = null;
                    this.remotePingRequestBuilder_ = null;
                }
                return this;
            }

            public RemotePingRequest.Builder getRemotePingRequestBuilder() {
                onChanged();
                return getRemotePingRequestFieldBuilder().getBuilder();
            }

            @Override
            public RemotePingRequestOrBuilder getRemotePingRequestOrBuilder() {
                SingleFieldBuilderV3<RemotePingRequest, RemotePingRequest.Builder, RemotePingRequestOrBuilder> singleFieldBuilderV3 = this.remotePingRequestBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemotePingRequest remotePingRequest = this.remotePingRequest_;
                return remotePingRequest == null ? RemotePingRequest.getDefaultInstance() : remotePingRequest;
            }

            private SingleFieldBuilderV3<RemotePingRequest, RemotePingRequest.Builder, RemotePingRequestOrBuilder> getRemotePingRequestFieldBuilder() {
                if (this.remotePingRequestBuilder_ == null) {
                    this.remotePingRequestBuilder_ = new SingleFieldBuilderV3<>(getRemotePingRequest(), getParentForChildren(), isClean());
                    this.remotePingRequest_ = null;
                }
                return this.remotePingRequestBuilder_;
            }

            @Override
            public boolean hasRemotePingResponse() {
                return (this.remotePingResponseBuilder_ == null && this.remotePingResponse_ == null) ? false : true;
            }

            @Override
            public RemotePingResponse getRemotePingResponse() {
                SingleFieldBuilderV3<RemotePingResponse, RemotePingResponse.Builder, RemotePingResponseOrBuilder> singleFieldBuilderV3 = this.remotePingResponseBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemotePingResponse remotePingResponse = this.remotePingResponse_;
                return remotePingResponse == null ? RemotePingResponse.getDefaultInstance() : remotePingResponse;
            }

            public Builder setRemotePingResponse(RemotePingResponse remotePingResponse) {
                SingleFieldBuilderV3<RemotePingResponse, RemotePingResponse.Builder, RemotePingResponseOrBuilder> singleFieldBuilderV3 = this.remotePingResponseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remotePingResponse);
                    this.remotePingResponse_ = remotePingResponse;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remotePingResponse);
                }
                return this;
            }

            public Builder setRemotePingResponse(RemotePingResponse.Builder builder) {
                SingleFieldBuilderV3<RemotePingResponse, RemotePingResponse.Builder, RemotePingResponseOrBuilder> singleFieldBuilderV3 = this.remotePingResponseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remotePingResponse_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemotePingResponse(RemotePingResponse remotePingResponse) {
                SingleFieldBuilderV3<RemotePingResponse, RemotePingResponse.Builder, RemotePingResponseOrBuilder> singleFieldBuilderV3 = this.remotePingResponseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemotePingResponse remotePingResponse2 = this.remotePingResponse_;
                    if (remotePingResponse2 != null) {
                        this.remotePingResponse_ = RemotePingResponse.newBuilder(remotePingResponse2).mergeFrom(remotePingResponse).buildPartial();
                    } else {
                        this.remotePingResponse_ = remotePingResponse;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remotePingResponse);
                }
                return this;
            }

            public Builder clearRemotePingResponse() {
                if (this.remotePingResponseBuilder_ == null) {
                    this.remotePingResponse_ = null;
                    onChanged();
                } else {
                    this.remotePingResponse_ = null;
                    this.remotePingResponseBuilder_ = null;
                }
                return this;
            }

            public RemotePingResponse.Builder getRemotePingResponseBuilder() {
                onChanged();
                return getRemotePingResponseFieldBuilder().getBuilder();
            }

            @Override
            public RemotePingResponseOrBuilder getRemotePingResponseOrBuilder() {
                SingleFieldBuilderV3<RemotePingResponse, RemotePingResponse.Builder, RemotePingResponseOrBuilder> singleFieldBuilderV3 = this.remotePingResponseBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemotePingResponse remotePingResponse = this.remotePingResponse_;
                return remotePingResponse == null ? RemotePingResponse.getDefaultInstance() : remotePingResponse;
            }

            private SingleFieldBuilderV3<RemotePingResponse, RemotePingResponse.Builder, RemotePingResponseOrBuilder> getRemotePingResponseFieldBuilder() {
                if (this.remotePingResponseBuilder_ == null) {
                    this.remotePingResponseBuilder_ = new SingleFieldBuilderV3<>(getRemotePingResponse(), getParentForChildren(), isClean());
                    this.remotePingResponse_ = null;
                }
                return this.remotePingResponseBuilder_;
            }

            @Override
            public boolean hasRemoteKeyInject() {
                return (this.remoteKeyInjectBuilder_ == null && this.remoteKeyInject_ == null) ? false : true;
            }

            @Override
            public RemoteKeyInject getRemoteKeyInject() {
                SingleFieldBuilderV3<RemoteKeyInject, RemoteKeyInject.Builder, RemoteKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteKeyInjectBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteKeyInject remoteKeyInject = this.remoteKeyInject_;
                return remoteKeyInject == null ? RemoteKeyInject.getDefaultInstance() : remoteKeyInject;
            }

            public Builder setRemoteKeyInject(RemoteKeyInject remoteKeyInject) {
                SingleFieldBuilderV3<RemoteKeyInject, RemoteKeyInject.Builder, RemoteKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteKeyInjectBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteKeyInject);
                    this.remoteKeyInject_ = remoteKeyInject;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteKeyInject);
                }
                return this;
            }

            public Builder setRemoteKeyInject(RemoteKeyInject.Builder builder) {
                SingleFieldBuilderV3<RemoteKeyInject, RemoteKeyInject.Builder, RemoteKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteKeyInjectBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteKeyInject_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteKeyInject(RemoteKeyInject remoteKeyInject) {
                SingleFieldBuilderV3<RemoteKeyInject, RemoteKeyInject.Builder, RemoteKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteKeyInjectBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteKeyInject remoteKeyInject2 = this.remoteKeyInject_;
                    if (remoteKeyInject2 != null) {
                        this.remoteKeyInject_ = RemoteKeyInject.newBuilder(remoteKeyInject2).mergeFrom(remoteKeyInject).buildPartial();
                    } else {
                        this.remoteKeyInject_ = remoteKeyInject;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteKeyInject);
                }
                return this;
            }

            public Builder clearRemoteKeyInject() {
                if (this.remoteKeyInjectBuilder_ == null) {
                    this.remoteKeyInject_ = null;
                    onChanged();
                } else {
                    this.remoteKeyInject_ = null;
                    this.remoteKeyInjectBuilder_ = null;
                }
                return this;
            }

            public RemoteKeyInject.Builder getRemoteKeyInjectBuilder() {
                onChanged();
                return getRemoteKeyInjectFieldBuilder().getBuilder();
            }

            @Override
            public RemoteKeyInjectOrBuilder getRemoteKeyInjectOrBuilder() {
                SingleFieldBuilderV3<RemoteKeyInject, RemoteKeyInject.Builder, RemoteKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteKeyInjectBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteKeyInject remoteKeyInject = this.remoteKeyInject_;
                return remoteKeyInject == null ? RemoteKeyInject.getDefaultInstance() : remoteKeyInject;
            }

            private SingleFieldBuilderV3<RemoteKeyInject, RemoteKeyInject.Builder, RemoteKeyInjectOrBuilder> getRemoteKeyInjectFieldBuilder() {
                if (this.remoteKeyInjectBuilder_ == null) {
                    this.remoteKeyInjectBuilder_ = new SingleFieldBuilderV3<>(getRemoteKeyInject(), getParentForChildren(), isClean());
                    this.remoteKeyInject_ = null;
                }
                return this.remoteKeyInjectBuilder_;
            }

            @Override
            public boolean hasRemoteImeKeyInject() {
                return (this.remoteImeKeyInjectBuilder_ == null && this.remoteImeKeyInject_ == null) ? false : true;
            }

            @Override
            public RemoteImeKeyInject getRemoteImeKeyInject() {
                SingleFieldBuilderV3<RemoteImeKeyInject, RemoteImeKeyInject.Builder, RemoteImeKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteImeKeyInjectBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteImeKeyInject remoteImeKeyInject = this.remoteImeKeyInject_;
                return remoteImeKeyInject == null ? RemoteImeKeyInject.getDefaultInstance() : remoteImeKeyInject;
            }

            public Builder setRemoteImeKeyInject(RemoteImeKeyInject remoteImeKeyInject) {
                SingleFieldBuilderV3<RemoteImeKeyInject, RemoteImeKeyInject.Builder, RemoteImeKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteImeKeyInjectBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteImeKeyInject);
                    this.remoteImeKeyInject_ = remoteImeKeyInject;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteImeKeyInject);
                }
                return this;
            }

            public Builder setRemoteImeKeyInject(RemoteImeKeyInject.Builder builder) {
                SingleFieldBuilderV3<RemoteImeKeyInject, RemoteImeKeyInject.Builder, RemoteImeKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteImeKeyInjectBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteImeKeyInject_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteImeKeyInject(RemoteImeKeyInject remoteImeKeyInject) {
                SingleFieldBuilderV3<RemoteImeKeyInject, RemoteImeKeyInject.Builder, RemoteImeKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteImeKeyInjectBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteImeKeyInject remoteImeKeyInject2 = this.remoteImeKeyInject_;
                    if (remoteImeKeyInject2 != null) {
                        this.remoteImeKeyInject_ = RemoteImeKeyInject.newBuilder(remoteImeKeyInject2).mergeFrom(remoteImeKeyInject).buildPartial();
                    } else {
                        this.remoteImeKeyInject_ = remoteImeKeyInject;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteImeKeyInject);
                }
                return this;
            }

            public Builder clearRemoteImeKeyInject() {
                if (this.remoteImeKeyInjectBuilder_ == null) {
                    this.remoteImeKeyInject_ = null;
                    onChanged();
                } else {
                    this.remoteImeKeyInject_ = null;
                    this.remoteImeKeyInjectBuilder_ = null;
                }
                return this;
            }

            public RemoteImeKeyInject.Builder getRemoteImeKeyInjectBuilder() {
                onChanged();
                return getRemoteImeKeyInjectFieldBuilder().getBuilder();
            }

            @Override
            public RemoteImeKeyInjectOrBuilder getRemoteImeKeyInjectOrBuilder() {
                SingleFieldBuilderV3<RemoteImeKeyInject, RemoteImeKeyInject.Builder, RemoteImeKeyInjectOrBuilder> singleFieldBuilderV3 = this.remoteImeKeyInjectBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteImeKeyInject remoteImeKeyInject = this.remoteImeKeyInject_;
                return remoteImeKeyInject == null ? RemoteImeKeyInject.getDefaultInstance() : remoteImeKeyInject;
            }

            private SingleFieldBuilderV3<RemoteImeKeyInject, RemoteImeKeyInject.Builder, RemoteImeKeyInjectOrBuilder> getRemoteImeKeyInjectFieldBuilder() {
                if (this.remoteImeKeyInjectBuilder_ == null) {
                    this.remoteImeKeyInjectBuilder_ = new SingleFieldBuilderV3<>(getRemoteImeKeyInject(), getParentForChildren(), isClean());
                    this.remoteImeKeyInject_ = null;
                }
                return this.remoteImeKeyInjectBuilder_;
            }

            @Override
            public boolean hasRemoteImeBatchEdit() {
                return (this.remoteImeBatchEditBuilder_ == null && this.remoteImeBatchEdit_ == null) ? false : true;
            }

            @Override
            public RemoteImeBatchEdit getRemoteImeBatchEdit() {
                SingleFieldBuilderV3<RemoteImeBatchEdit, RemoteImeBatchEdit.Builder, RemoteImeBatchEditOrBuilder> singleFieldBuilderV3 = this.remoteImeBatchEditBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteImeBatchEdit remoteImeBatchEdit = this.remoteImeBatchEdit_;
                return remoteImeBatchEdit == null ? RemoteImeBatchEdit.getDefaultInstance() : remoteImeBatchEdit;
            }

            public Builder setRemoteImeBatchEdit(RemoteImeBatchEdit remoteImeBatchEdit) {
                SingleFieldBuilderV3<RemoteImeBatchEdit, RemoteImeBatchEdit.Builder, RemoteImeBatchEditOrBuilder> singleFieldBuilderV3 = this.remoteImeBatchEditBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteImeBatchEdit);
                    this.remoteImeBatchEdit_ = remoteImeBatchEdit;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteImeBatchEdit);
                }
                return this;
            }

            public Builder setRemoteImeBatchEdit(RemoteImeBatchEdit.Builder builder) {
                SingleFieldBuilderV3<RemoteImeBatchEdit, RemoteImeBatchEdit.Builder, RemoteImeBatchEditOrBuilder> singleFieldBuilderV3 = this.remoteImeBatchEditBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteImeBatchEdit_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteImeBatchEdit(RemoteImeBatchEdit remoteImeBatchEdit) {
                SingleFieldBuilderV3<RemoteImeBatchEdit, RemoteImeBatchEdit.Builder, RemoteImeBatchEditOrBuilder> singleFieldBuilderV3 = this.remoteImeBatchEditBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteImeBatchEdit remoteImeBatchEdit2 = this.remoteImeBatchEdit_;
                    if (remoteImeBatchEdit2 != null) {
                        this.remoteImeBatchEdit_ = RemoteImeBatchEdit.newBuilder(remoteImeBatchEdit2).mergeFrom(remoteImeBatchEdit).buildPartial();
                    } else {
                        this.remoteImeBatchEdit_ = remoteImeBatchEdit;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteImeBatchEdit);
                }
                return this;
            }

            public Builder clearRemoteImeBatchEdit() {
                if (this.remoteImeBatchEditBuilder_ == null) {
                    this.remoteImeBatchEdit_ = null;
                    onChanged();
                } else {
                    this.remoteImeBatchEdit_ = null;
                    this.remoteImeBatchEditBuilder_ = null;
                }
                return this;
            }

            public RemoteImeBatchEdit.Builder getRemoteImeBatchEditBuilder() {
                onChanged();
                return getRemoteImeBatchEditFieldBuilder().getBuilder();
            }

            @Override
            public RemoteImeBatchEditOrBuilder getRemoteImeBatchEditOrBuilder() {
                SingleFieldBuilderV3<RemoteImeBatchEdit, RemoteImeBatchEdit.Builder, RemoteImeBatchEditOrBuilder> singleFieldBuilderV3 = this.remoteImeBatchEditBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteImeBatchEdit remoteImeBatchEdit = this.remoteImeBatchEdit_;
                return remoteImeBatchEdit == null ? RemoteImeBatchEdit.getDefaultInstance() : remoteImeBatchEdit;
            }

            private SingleFieldBuilderV3<RemoteImeBatchEdit, RemoteImeBatchEdit.Builder, RemoteImeBatchEditOrBuilder> getRemoteImeBatchEditFieldBuilder() {
                if (this.remoteImeBatchEditBuilder_ == null) {
                    this.remoteImeBatchEditBuilder_ = new SingleFieldBuilderV3<>(getRemoteImeBatchEdit(), getParentForChildren(), isClean());
                    this.remoteImeBatchEdit_ = null;
                }
                return this.remoteImeBatchEditBuilder_;
            }

            @Override
            public boolean hasRemoteImeShowRequest() {
                return (this.remoteImeShowRequestBuilder_ == null && this.remoteImeShowRequest_ == null) ? false : true;
            }

            @Override
            public RemoteImeShowRequest getRemoteImeShowRequest() {
                SingleFieldBuilderV3<RemoteImeShowRequest, RemoteImeShowRequest.Builder, RemoteImeShowRequestOrBuilder> singleFieldBuilderV3 = this.remoteImeShowRequestBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteImeShowRequest remoteImeShowRequest = this.remoteImeShowRequest_;
                return remoteImeShowRequest == null ? RemoteImeShowRequest.getDefaultInstance() : remoteImeShowRequest;
            }

            public Builder setRemoteImeShowRequest(RemoteImeShowRequest remoteImeShowRequest) {
                SingleFieldBuilderV3<RemoteImeShowRequest, RemoteImeShowRequest.Builder, RemoteImeShowRequestOrBuilder> singleFieldBuilderV3 = this.remoteImeShowRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteImeShowRequest);
                    this.remoteImeShowRequest_ = remoteImeShowRequest;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteImeShowRequest);
                }
                return this;
            }

            public Builder setRemoteImeShowRequest(RemoteImeShowRequest.Builder builder) {
                SingleFieldBuilderV3<RemoteImeShowRequest, RemoteImeShowRequest.Builder, RemoteImeShowRequestOrBuilder> singleFieldBuilderV3 = this.remoteImeShowRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteImeShowRequest_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteImeShowRequest(RemoteImeShowRequest remoteImeShowRequest) {
                SingleFieldBuilderV3<RemoteImeShowRequest, RemoteImeShowRequest.Builder, RemoteImeShowRequestOrBuilder> singleFieldBuilderV3 = this.remoteImeShowRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteImeShowRequest remoteImeShowRequest2 = this.remoteImeShowRequest_;
                    if (remoteImeShowRequest2 != null) {
                        this.remoteImeShowRequest_ = RemoteImeShowRequest.newBuilder(remoteImeShowRequest2).mergeFrom(remoteImeShowRequest).buildPartial();
                    } else {
                        this.remoteImeShowRequest_ = remoteImeShowRequest;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteImeShowRequest);
                }
                return this;
            }

            public Builder clearRemoteImeShowRequest() {
                if (this.remoteImeShowRequestBuilder_ == null) {
                    this.remoteImeShowRequest_ = null;
                    onChanged();
                } else {
                    this.remoteImeShowRequest_ = null;
                    this.remoteImeShowRequestBuilder_ = null;
                }
                return this;
            }

            public RemoteImeShowRequest.Builder getRemoteImeShowRequestBuilder() {
                onChanged();
                return getRemoteImeShowRequestFieldBuilder().getBuilder();
            }

            @Override
            public RemoteImeShowRequestOrBuilder getRemoteImeShowRequestOrBuilder() {
                SingleFieldBuilderV3<RemoteImeShowRequest, RemoteImeShowRequest.Builder, RemoteImeShowRequestOrBuilder> singleFieldBuilderV3 = this.remoteImeShowRequestBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteImeShowRequest remoteImeShowRequest = this.remoteImeShowRequest_;
                return remoteImeShowRequest == null ? RemoteImeShowRequest.getDefaultInstance() : remoteImeShowRequest;
            }

            private SingleFieldBuilderV3<RemoteImeShowRequest, RemoteImeShowRequest.Builder, RemoteImeShowRequestOrBuilder> getRemoteImeShowRequestFieldBuilder() {
                if (this.remoteImeShowRequestBuilder_ == null) {
                    this.remoteImeShowRequestBuilder_ = new SingleFieldBuilderV3<>(getRemoteImeShowRequest(), getParentForChildren(), isClean());
                    this.remoteImeShowRequest_ = null;
                }
                return this.remoteImeShowRequestBuilder_;
            }

            @Override
            public boolean hasRemoteVoiceBegin() {
                return (this.remoteVoiceBeginBuilder_ == null && this.remoteVoiceBegin_ == null) ? false : true;
            }

            @Override
            public RemoteVoiceBegin getRemoteVoiceBegin() {
                SingleFieldBuilderV3<RemoteVoiceBegin, RemoteVoiceBegin.Builder, RemoteVoiceBeginOrBuilder> singleFieldBuilderV3 = this.remoteVoiceBeginBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteVoiceBegin remoteVoiceBegin = this.remoteVoiceBegin_;
                return remoteVoiceBegin == null ? RemoteVoiceBegin.getDefaultInstance() : remoteVoiceBegin;
            }

            public Builder setRemoteVoiceBegin(RemoteVoiceBegin remoteVoiceBegin) {
                SingleFieldBuilderV3<RemoteVoiceBegin, RemoteVoiceBegin.Builder, RemoteVoiceBeginOrBuilder> singleFieldBuilderV3 = this.remoteVoiceBeginBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteVoiceBegin);
                    this.remoteVoiceBegin_ = remoteVoiceBegin;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteVoiceBegin);
                }
                return this;
            }

            public Builder setRemoteVoiceBegin(RemoteVoiceBegin.Builder builder) {
                SingleFieldBuilderV3<RemoteVoiceBegin, RemoteVoiceBegin.Builder, RemoteVoiceBeginOrBuilder> singleFieldBuilderV3 = this.remoteVoiceBeginBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteVoiceBegin_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteVoiceBegin(RemoteVoiceBegin remoteVoiceBegin) {
                SingleFieldBuilderV3<RemoteVoiceBegin, RemoteVoiceBegin.Builder, RemoteVoiceBeginOrBuilder> singleFieldBuilderV3 = this.remoteVoiceBeginBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteVoiceBegin remoteVoiceBegin2 = this.remoteVoiceBegin_;
                    if (remoteVoiceBegin2 != null) {
                        this.remoteVoiceBegin_ = RemoteVoiceBegin.newBuilder(remoteVoiceBegin2).mergeFrom(remoteVoiceBegin).buildPartial();
                    } else {
                        this.remoteVoiceBegin_ = remoteVoiceBegin;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteVoiceBegin);
                }
                return this;
            }

            public Builder clearRemoteVoiceBegin() {
                if (this.remoteVoiceBeginBuilder_ == null) {
                    this.remoteVoiceBegin_ = null;
                    onChanged();
                } else {
                    this.remoteVoiceBegin_ = null;
                    this.remoteVoiceBeginBuilder_ = null;
                }
                return this;
            }

            public RemoteVoiceBegin.Builder getRemoteVoiceBeginBuilder() {
                onChanged();
                return getRemoteVoiceBeginFieldBuilder().getBuilder();
            }

            @Override
            public RemoteVoiceBeginOrBuilder getRemoteVoiceBeginOrBuilder() {
                SingleFieldBuilderV3<RemoteVoiceBegin, RemoteVoiceBegin.Builder, RemoteVoiceBeginOrBuilder> singleFieldBuilderV3 = this.remoteVoiceBeginBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteVoiceBegin remoteVoiceBegin = this.remoteVoiceBegin_;
                return remoteVoiceBegin == null ? RemoteVoiceBegin.getDefaultInstance() : remoteVoiceBegin;
            }

            private SingleFieldBuilderV3<RemoteVoiceBegin, RemoteVoiceBegin.Builder, RemoteVoiceBeginOrBuilder> getRemoteVoiceBeginFieldBuilder() {
                if (this.remoteVoiceBeginBuilder_ == null) {
                    this.remoteVoiceBeginBuilder_ = new SingleFieldBuilderV3<>(getRemoteVoiceBegin(), getParentForChildren(), isClean());
                    this.remoteVoiceBegin_ = null;
                }
                return this.remoteVoiceBeginBuilder_;
            }

            @Override
            public boolean hasRemoteVoicePayload() {
                return (this.remoteVoicePayloadBuilder_ == null && this.remoteVoicePayload_ == null) ? false : true;
            }

            @Override
            public RemoteVoicePayload getRemoteVoicePayload() {
                SingleFieldBuilderV3<RemoteVoicePayload, RemoteVoicePayload.Builder, RemoteVoicePayloadOrBuilder> singleFieldBuilderV3 = this.remoteVoicePayloadBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteVoicePayload remoteVoicePayload = this.remoteVoicePayload_;
                return remoteVoicePayload == null ? RemoteVoicePayload.getDefaultInstance() : remoteVoicePayload;
            }

            public Builder setRemoteVoicePayload(RemoteVoicePayload remoteVoicePayload) {
                SingleFieldBuilderV3<RemoteVoicePayload, RemoteVoicePayload.Builder, RemoteVoicePayloadOrBuilder> singleFieldBuilderV3 = this.remoteVoicePayloadBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteVoicePayload);
                    this.remoteVoicePayload_ = remoteVoicePayload;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteVoicePayload);
                }
                return this;
            }

            public Builder setRemoteVoicePayload(RemoteVoicePayload.Builder builder) {
                SingleFieldBuilderV3<RemoteVoicePayload, RemoteVoicePayload.Builder, RemoteVoicePayloadOrBuilder> singleFieldBuilderV3 = this.remoteVoicePayloadBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteVoicePayload_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteVoicePayload(RemoteVoicePayload remoteVoicePayload) {
                SingleFieldBuilderV3<RemoteVoicePayload, RemoteVoicePayload.Builder, RemoteVoicePayloadOrBuilder> singleFieldBuilderV3 = this.remoteVoicePayloadBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteVoicePayload remoteVoicePayload2 = this.remoteVoicePayload_;
                    if (remoteVoicePayload2 != null) {
                        this.remoteVoicePayload_ = RemoteVoicePayload.newBuilder(remoteVoicePayload2).mergeFrom(remoteVoicePayload).buildPartial();
                    } else {
                        this.remoteVoicePayload_ = remoteVoicePayload;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteVoicePayload);
                }
                return this;
            }

            public Builder clearRemoteVoicePayload() {
                if (this.remoteVoicePayloadBuilder_ == null) {
                    this.remoteVoicePayload_ = null;
                    onChanged();
                } else {
                    this.remoteVoicePayload_ = null;
                    this.remoteVoicePayloadBuilder_ = null;
                }
                return this;
            }

            public RemoteVoicePayload.Builder getRemoteVoicePayloadBuilder() {
                onChanged();
                return getRemoteVoicePayloadFieldBuilder().getBuilder();
            }

            @Override
            public RemoteVoicePayloadOrBuilder getRemoteVoicePayloadOrBuilder() {
                SingleFieldBuilderV3<RemoteVoicePayload, RemoteVoicePayload.Builder, RemoteVoicePayloadOrBuilder> singleFieldBuilderV3 = this.remoteVoicePayloadBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteVoicePayload remoteVoicePayload = this.remoteVoicePayload_;
                return remoteVoicePayload == null ? RemoteVoicePayload.getDefaultInstance() : remoteVoicePayload;
            }

            private SingleFieldBuilderV3<RemoteVoicePayload, RemoteVoicePayload.Builder, RemoteVoicePayloadOrBuilder> getRemoteVoicePayloadFieldBuilder() {
                if (this.remoteVoicePayloadBuilder_ == null) {
                    this.remoteVoicePayloadBuilder_ = new SingleFieldBuilderV3<>(getRemoteVoicePayload(), getParentForChildren(), isClean());
                    this.remoteVoicePayload_ = null;
                }
                return this.remoteVoicePayloadBuilder_;
            }

            @Override
            public boolean hasRemoteVoiceEnd() {
                return (this.remoteVoiceEndBuilder_ == null && this.remoteVoiceEnd_ == null) ? false : true;
            }

            @Override
            public RemoteVoiceEnd getRemoteVoiceEnd() {
                SingleFieldBuilderV3<RemoteVoiceEnd, RemoteVoiceEnd.Builder, RemoteVoiceEndOrBuilder> singleFieldBuilderV3 = this.remoteVoiceEndBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteVoiceEnd remoteVoiceEnd = this.remoteVoiceEnd_;
                return remoteVoiceEnd == null ? RemoteVoiceEnd.getDefaultInstance() : remoteVoiceEnd;
            }

            public Builder setRemoteVoiceEnd(RemoteVoiceEnd remoteVoiceEnd) {
                SingleFieldBuilderV3<RemoteVoiceEnd, RemoteVoiceEnd.Builder, RemoteVoiceEndOrBuilder> singleFieldBuilderV3 = this.remoteVoiceEndBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteVoiceEnd);
                    this.remoteVoiceEnd_ = remoteVoiceEnd;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteVoiceEnd);
                }
                return this;
            }

            public Builder setRemoteVoiceEnd(RemoteVoiceEnd.Builder builder) {
                SingleFieldBuilderV3<RemoteVoiceEnd, RemoteVoiceEnd.Builder, RemoteVoiceEndOrBuilder> singleFieldBuilderV3 = this.remoteVoiceEndBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteVoiceEnd_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteVoiceEnd(RemoteVoiceEnd remoteVoiceEnd) {
                SingleFieldBuilderV3<RemoteVoiceEnd, RemoteVoiceEnd.Builder, RemoteVoiceEndOrBuilder> singleFieldBuilderV3 = this.remoteVoiceEndBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteVoiceEnd remoteVoiceEnd2 = this.remoteVoiceEnd_;
                    if (remoteVoiceEnd2 != null) {
                        this.remoteVoiceEnd_ = RemoteVoiceEnd.newBuilder(remoteVoiceEnd2).mergeFrom(remoteVoiceEnd).buildPartial();
                    } else {
                        this.remoteVoiceEnd_ = remoteVoiceEnd;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteVoiceEnd);
                }
                return this;
            }

            public Builder clearRemoteVoiceEnd() {
                if (this.remoteVoiceEndBuilder_ == null) {
                    this.remoteVoiceEnd_ = null;
                    onChanged();
                } else {
                    this.remoteVoiceEnd_ = null;
                    this.remoteVoiceEndBuilder_ = null;
                }
                return this;
            }

            public RemoteVoiceEnd.Builder getRemoteVoiceEndBuilder() {
                onChanged();
                return getRemoteVoiceEndFieldBuilder().getBuilder();
            }

            @Override
            public RemoteVoiceEndOrBuilder getRemoteVoiceEndOrBuilder() {
                SingleFieldBuilderV3<RemoteVoiceEnd, RemoteVoiceEnd.Builder, RemoteVoiceEndOrBuilder> singleFieldBuilderV3 = this.remoteVoiceEndBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteVoiceEnd remoteVoiceEnd = this.remoteVoiceEnd_;
                return remoteVoiceEnd == null ? RemoteVoiceEnd.getDefaultInstance() : remoteVoiceEnd;
            }

            private SingleFieldBuilderV3<RemoteVoiceEnd, RemoteVoiceEnd.Builder, RemoteVoiceEndOrBuilder> getRemoteVoiceEndFieldBuilder() {
                if (this.remoteVoiceEndBuilder_ == null) {
                    this.remoteVoiceEndBuilder_ = new SingleFieldBuilderV3<>(getRemoteVoiceEnd(), getParentForChildren(), isClean());
                    this.remoteVoiceEnd_ = null;
                }
                return this.remoteVoiceEndBuilder_;
            }

            @Override
            public boolean hasRemoteStart() {
                return (this.remoteStartBuilder_ == null && this.remoteStart_ == null) ? false : true;
            }

            @Override
            public RemoteStart getRemoteStart() {
                SingleFieldBuilderV3<RemoteStart, RemoteStart.Builder, RemoteStartOrBuilder> singleFieldBuilderV3 = this.remoteStartBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteStart remoteStart = this.remoteStart_;
                return remoteStart == null ? RemoteStart.getDefaultInstance() : remoteStart;
            }

            public Builder setRemoteStart(RemoteStart remoteStart) {
                SingleFieldBuilderV3<RemoteStart, RemoteStart.Builder, RemoteStartOrBuilder> singleFieldBuilderV3 = this.remoteStartBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteStart);
                    this.remoteStart_ = remoteStart;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteStart);
                }
                return this;
            }

            public Builder setRemoteStart(RemoteStart.Builder builder) {
                SingleFieldBuilderV3<RemoteStart, RemoteStart.Builder, RemoteStartOrBuilder> singleFieldBuilderV3 = this.remoteStartBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteStart_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteStart(RemoteStart remoteStart) {
                SingleFieldBuilderV3<RemoteStart, RemoteStart.Builder, RemoteStartOrBuilder> singleFieldBuilderV3 = this.remoteStartBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteStart remoteStart2 = this.remoteStart_;
                    if (remoteStart2 != null) {
                        this.remoteStart_ = RemoteStart.newBuilder(remoteStart2).mergeFrom(remoteStart).buildPartial();
                    } else {
                        this.remoteStart_ = remoteStart;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteStart);
                }
                return this;
            }

            public Builder clearRemoteStart() {
                if (this.remoteStartBuilder_ == null) {
                    this.remoteStart_ = null;
                    onChanged();
                } else {
                    this.remoteStart_ = null;
                    this.remoteStartBuilder_ = null;
                }
                return this;
            }

            public RemoteStart.Builder getRemoteStartBuilder() {
                onChanged();
                return getRemoteStartFieldBuilder().getBuilder();
            }

            @Override
            public RemoteStartOrBuilder getRemoteStartOrBuilder() {
                SingleFieldBuilderV3<RemoteStart, RemoteStart.Builder, RemoteStartOrBuilder> singleFieldBuilderV3 = this.remoteStartBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteStart remoteStart = this.remoteStart_;
                return remoteStart == null ? RemoteStart.getDefaultInstance() : remoteStart;
            }

            private SingleFieldBuilderV3<RemoteStart, RemoteStart.Builder, RemoteStartOrBuilder> getRemoteStartFieldBuilder() {
                if (this.remoteStartBuilder_ == null) {
                    this.remoteStartBuilder_ = new SingleFieldBuilderV3<>(getRemoteStart(), getParentForChildren(), isClean());
                    this.remoteStart_ = null;
                }
                return this.remoteStartBuilder_;
            }

            @Override
            public boolean hasRemoteSetVolumeLevel() {
                return (this.remoteSetVolumeLevelBuilder_ == null && this.remoteSetVolumeLevel_ == null) ? false : true;
            }

            @Override
            public RemoteSetVolumeLevel getRemoteSetVolumeLevel() {
                SingleFieldBuilderV3<RemoteSetVolumeLevel, RemoteSetVolumeLevel.Builder, RemoteSetVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteSetVolumeLevelBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteSetVolumeLevel remoteSetVolumeLevel = this.remoteSetVolumeLevel_;
                return remoteSetVolumeLevel == null ? RemoteSetVolumeLevel.getDefaultInstance() : remoteSetVolumeLevel;
            }

            public Builder setRemoteSetVolumeLevel(RemoteSetVolumeLevel remoteSetVolumeLevel) {
                SingleFieldBuilderV3<RemoteSetVolumeLevel, RemoteSetVolumeLevel.Builder, RemoteSetVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteSetVolumeLevelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteSetVolumeLevel);
                    this.remoteSetVolumeLevel_ = remoteSetVolumeLevel;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteSetVolumeLevel);
                }
                return this;
            }

            public Builder setRemoteSetVolumeLevel(RemoteSetVolumeLevel.Builder builder) {
                SingleFieldBuilderV3<RemoteSetVolumeLevel, RemoteSetVolumeLevel.Builder, RemoteSetVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteSetVolumeLevelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteSetVolumeLevel_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteSetVolumeLevel(RemoteSetVolumeLevel remoteSetVolumeLevel) {
                SingleFieldBuilderV3<RemoteSetVolumeLevel, RemoteSetVolumeLevel.Builder, RemoteSetVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteSetVolumeLevelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteSetVolumeLevel remoteSetVolumeLevel2 = this.remoteSetVolumeLevel_;
                    if (remoteSetVolumeLevel2 != null) {
                        this.remoteSetVolumeLevel_ = RemoteSetVolumeLevel.newBuilder(remoteSetVolumeLevel2).mergeFrom(remoteSetVolumeLevel).buildPartial();
                    } else {
                        this.remoteSetVolumeLevel_ = remoteSetVolumeLevel;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteSetVolumeLevel);
                }
                return this;
            }

            public Builder clearRemoteSetVolumeLevel() {
                if (this.remoteSetVolumeLevelBuilder_ == null) {
                    this.remoteSetVolumeLevel_ = null;
                    onChanged();
                } else {
                    this.remoteSetVolumeLevel_ = null;
                    this.remoteSetVolumeLevelBuilder_ = null;
                }
                return this;
            }

            public RemoteSetVolumeLevel.Builder getRemoteSetVolumeLevelBuilder() {
                onChanged();
                return getRemoteSetVolumeLevelFieldBuilder().getBuilder();
            }

            @Override
            public RemoteSetVolumeLevelOrBuilder getRemoteSetVolumeLevelOrBuilder() {
                SingleFieldBuilderV3<RemoteSetVolumeLevel, RemoteSetVolumeLevel.Builder, RemoteSetVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteSetVolumeLevelBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteSetVolumeLevel remoteSetVolumeLevel = this.remoteSetVolumeLevel_;
                return remoteSetVolumeLevel == null ? RemoteSetVolumeLevel.getDefaultInstance() : remoteSetVolumeLevel;
            }

            private SingleFieldBuilderV3<RemoteSetVolumeLevel, RemoteSetVolumeLevel.Builder, RemoteSetVolumeLevelOrBuilder> getRemoteSetVolumeLevelFieldBuilder() {
                if (this.remoteSetVolumeLevelBuilder_ == null) {
                    this.remoteSetVolumeLevelBuilder_ = new SingleFieldBuilderV3<>(getRemoteSetVolumeLevel(), getParentForChildren(), isClean());
                    this.remoteSetVolumeLevel_ = null;
                }
                return this.remoteSetVolumeLevelBuilder_;
            }

            @Override
            public boolean hasRemoteAdjustVolumeLevel() {
                return (this.remoteAdjustVolumeLevelBuilder_ == null && this.remoteAdjustVolumeLevel_ == null) ? false : true;
            }

            @Override
            public RemoteAdjustVolumeLevel getRemoteAdjustVolumeLevel() {
                SingleFieldBuilderV3<RemoteAdjustVolumeLevel, RemoteAdjustVolumeLevel.Builder, RemoteAdjustVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteAdjustVolumeLevelBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteAdjustVolumeLevel remoteAdjustVolumeLevel = this.remoteAdjustVolumeLevel_;
                return remoteAdjustVolumeLevel == null ? RemoteAdjustVolumeLevel.getDefaultInstance() : remoteAdjustVolumeLevel;
            }

            public Builder setRemoteAdjustVolumeLevel(RemoteAdjustVolumeLevel remoteAdjustVolumeLevel) {
                SingleFieldBuilderV3<RemoteAdjustVolumeLevel, RemoteAdjustVolumeLevel.Builder, RemoteAdjustVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteAdjustVolumeLevelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteAdjustVolumeLevel);
                    this.remoteAdjustVolumeLevel_ = remoteAdjustVolumeLevel;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteAdjustVolumeLevel);
                }
                return this;
            }

            public Builder setRemoteAdjustVolumeLevel(RemoteAdjustVolumeLevel.Builder builder) {
                SingleFieldBuilderV3<RemoteAdjustVolumeLevel, RemoteAdjustVolumeLevel.Builder, RemoteAdjustVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteAdjustVolumeLevelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteAdjustVolumeLevel_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteAdjustVolumeLevel(RemoteAdjustVolumeLevel remoteAdjustVolumeLevel) {
                SingleFieldBuilderV3<RemoteAdjustVolumeLevel, RemoteAdjustVolumeLevel.Builder, RemoteAdjustVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteAdjustVolumeLevelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteAdjustVolumeLevel remoteAdjustVolumeLevel2 = this.remoteAdjustVolumeLevel_;
                    if (remoteAdjustVolumeLevel2 != null) {
                        this.remoteAdjustVolumeLevel_ = RemoteAdjustVolumeLevel.newBuilder(remoteAdjustVolumeLevel2).mergeFrom(remoteAdjustVolumeLevel).buildPartial();
                    } else {
                        this.remoteAdjustVolumeLevel_ = remoteAdjustVolumeLevel;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteAdjustVolumeLevel);
                }
                return this;
            }

            public Builder clearRemoteAdjustVolumeLevel() {
                if (this.remoteAdjustVolumeLevelBuilder_ == null) {
                    this.remoteAdjustVolumeLevel_ = null;
                    onChanged();
                } else {
                    this.remoteAdjustVolumeLevel_ = null;
                    this.remoteAdjustVolumeLevelBuilder_ = null;
                }
                return this;
            }

            public RemoteAdjustVolumeLevel.Builder getRemoteAdjustVolumeLevelBuilder() {
                onChanged();
                return getRemoteAdjustVolumeLevelFieldBuilder().getBuilder();
            }

            @Override
            public RemoteAdjustVolumeLevelOrBuilder getRemoteAdjustVolumeLevelOrBuilder() {
                SingleFieldBuilderV3<RemoteAdjustVolumeLevel, RemoteAdjustVolumeLevel.Builder, RemoteAdjustVolumeLevelOrBuilder> singleFieldBuilderV3 = this.remoteAdjustVolumeLevelBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteAdjustVolumeLevel remoteAdjustVolumeLevel = this.remoteAdjustVolumeLevel_;
                return remoteAdjustVolumeLevel == null ? RemoteAdjustVolumeLevel.getDefaultInstance() : remoteAdjustVolumeLevel;
            }

            private SingleFieldBuilderV3<RemoteAdjustVolumeLevel, RemoteAdjustVolumeLevel.Builder, RemoteAdjustVolumeLevelOrBuilder> getRemoteAdjustVolumeLevelFieldBuilder() {
                if (this.remoteAdjustVolumeLevelBuilder_ == null) {
                    this.remoteAdjustVolumeLevelBuilder_ = new SingleFieldBuilderV3<>(getRemoteAdjustVolumeLevel(), getParentForChildren(), isClean());
                    this.remoteAdjustVolumeLevel_ = null;
                }
                return this.remoteAdjustVolumeLevelBuilder_;
            }

            @Override
            public boolean hasRemoteSetPreferredAudioDevice() {
                return (this.remoteSetPreferredAudioDeviceBuilder_ == null && this.remoteSetPreferredAudioDevice_ == null) ? false : true;
            }

            @Override
            public RemoteSetPreferredAudioDevice getRemoteSetPreferredAudioDevice() {
                SingleFieldBuilderV3<RemoteSetPreferredAudioDevice, RemoteSetPreferredAudioDevice.Builder, RemoteSetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteSetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice = this.remoteSetPreferredAudioDevice_;
                return remoteSetPreferredAudioDevice == null ? RemoteSetPreferredAudioDevice.getDefaultInstance() : remoteSetPreferredAudioDevice;
            }

            public Builder setRemoteSetPreferredAudioDevice(RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice) {
                SingleFieldBuilderV3<RemoteSetPreferredAudioDevice, RemoteSetPreferredAudioDevice.Builder, RemoteSetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteSetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteSetPreferredAudioDevice);
                    this.remoteSetPreferredAudioDevice_ = remoteSetPreferredAudioDevice;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteSetPreferredAudioDevice);
                }
                return this;
            }

            public Builder setRemoteSetPreferredAudioDevice(RemoteSetPreferredAudioDevice.Builder builder) {
                SingleFieldBuilderV3<RemoteSetPreferredAudioDevice, RemoteSetPreferredAudioDevice.Builder, RemoteSetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteSetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteSetPreferredAudioDevice_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteSetPreferredAudioDevice(RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice) {
                SingleFieldBuilderV3<RemoteSetPreferredAudioDevice, RemoteSetPreferredAudioDevice.Builder, RemoteSetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteSetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice2 = this.remoteSetPreferredAudioDevice_;
                    if (remoteSetPreferredAudioDevice2 != null) {
                        this.remoteSetPreferredAudioDevice_ = RemoteSetPreferredAudioDevice.newBuilder(remoteSetPreferredAudioDevice2).mergeFrom(remoteSetPreferredAudioDevice).buildPartial();
                    } else {
                        this.remoteSetPreferredAudioDevice_ = remoteSetPreferredAudioDevice;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteSetPreferredAudioDevice);
                }
                return this;
            }

            public Builder clearRemoteSetPreferredAudioDevice() {
                if (this.remoteSetPreferredAudioDeviceBuilder_ == null) {
                    this.remoteSetPreferredAudioDevice_ = null;
                    onChanged();
                } else {
                    this.remoteSetPreferredAudioDevice_ = null;
                    this.remoteSetPreferredAudioDeviceBuilder_ = null;
                }
                return this;
            }

            public RemoteSetPreferredAudioDevice.Builder getRemoteSetPreferredAudioDeviceBuilder() {
                onChanged();
                return getRemoteSetPreferredAudioDeviceFieldBuilder().getBuilder();
            }

            @Override
            public RemoteSetPreferredAudioDeviceOrBuilder getRemoteSetPreferredAudioDeviceOrBuilder() {
                SingleFieldBuilderV3<RemoteSetPreferredAudioDevice, RemoteSetPreferredAudioDevice.Builder, RemoteSetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteSetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteSetPreferredAudioDevice remoteSetPreferredAudioDevice = this.remoteSetPreferredAudioDevice_;
                return remoteSetPreferredAudioDevice == null ? RemoteSetPreferredAudioDevice.getDefaultInstance() : remoteSetPreferredAudioDevice;
            }

            private SingleFieldBuilderV3<RemoteSetPreferredAudioDevice, RemoteSetPreferredAudioDevice.Builder, RemoteSetPreferredAudioDeviceOrBuilder> getRemoteSetPreferredAudioDeviceFieldBuilder() {
                if (this.remoteSetPreferredAudioDeviceBuilder_ == null) {
                    this.remoteSetPreferredAudioDeviceBuilder_ = new SingleFieldBuilderV3<>(getRemoteSetPreferredAudioDevice(), getParentForChildren(), isClean());
                    this.remoteSetPreferredAudioDevice_ = null;
                }
                return this.remoteSetPreferredAudioDeviceBuilder_;
            }

            @Override
            public boolean hasRemoteResetPreferredAudioDevice() {
                return (this.remoteResetPreferredAudioDeviceBuilder_ == null && this.remoteResetPreferredAudioDevice_ == null) ? false : true;
            }

            @Override
            public RemoteResetPreferredAudioDevice getRemoteResetPreferredAudioDevice() {
                SingleFieldBuilderV3<RemoteResetPreferredAudioDevice, RemoteResetPreferredAudioDevice.Builder, RemoteResetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteResetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice = this.remoteResetPreferredAudioDevice_;
                return remoteResetPreferredAudioDevice == null ? RemoteResetPreferredAudioDevice.getDefaultInstance() : remoteResetPreferredAudioDevice;
            }

            public Builder setRemoteResetPreferredAudioDevice(RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice) {
                SingleFieldBuilderV3<RemoteResetPreferredAudioDevice, RemoteResetPreferredAudioDevice.Builder, RemoteResetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteResetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteResetPreferredAudioDevice);
                    this.remoteResetPreferredAudioDevice_ = remoteResetPreferredAudioDevice;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteResetPreferredAudioDevice);
                }
                return this;
            }

            public Builder setRemoteResetPreferredAudioDevice(RemoteResetPreferredAudioDevice.Builder builder) {
                SingleFieldBuilderV3<RemoteResetPreferredAudioDevice, RemoteResetPreferredAudioDevice.Builder, RemoteResetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteResetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteResetPreferredAudioDevice_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteResetPreferredAudioDevice(RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice) {
                SingleFieldBuilderV3<RemoteResetPreferredAudioDevice, RemoteResetPreferredAudioDevice.Builder, RemoteResetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteResetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice2 = this.remoteResetPreferredAudioDevice_;
                    if (remoteResetPreferredAudioDevice2 != null) {
                        this.remoteResetPreferredAudioDevice_ = RemoteResetPreferredAudioDevice.newBuilder(remoteResetPreferredAudioDevice2).mergeFrom(remoteResetPreferredAudioDevice).buildPartial();
                    } else {
                        this.remoteResetPreferredAudioDevice_ = remoteResetPreferredAudioDevice;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteResetPreferredAudioDevice);
                }
                return this;
            }

            public Builder clearRemoteResetPreferredAudioDevice() {
                if (this.remoteResetPreferredAudioDeviceBuilder_ == null) {
                    this.remoteResetPreferredAudioDevice_ = null;
                    onChanged();
                } else {
                    this.remoteResetPreferredAudioDevice_ = null;
                    this.remoteResetPreferredAudioDeviceBuilder_ = null;
                }
                return this;
            }

            public RemoteResetPreferredAudioDevice.Builder getRemoteResetPreferredAudioDeviceBuilder() {
                onChanged();
                return getRemoteResetPreferredAudioDeviceFieldBuilder().getBuilder();
            }

            @Override
            public RemoteResetPreferredAudioDeviceOrBuilder getRemoteResetPreferredAudioDeviceOrBuilder() {
                SingleFieldBuilderV3<RemoteResetPreferredAudioDevice, RemoteResetPreferredAudioDevice.Builder, RemoteResetPreferredAudioDeviceOrBuilder> singleFieldBuilderV3 = this.remoteResetPreferredAudioDeviceBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteResetPreferredAudioDevice remoteResetPreferredAudioDevice = this.remoteResetPreferredAudioDevice_;
                return remoteResetPreferredAudioDevice == null ? RemoteResetPreferredAudioDevice.getDefaultInstance() : remoteResetPreferredAudioDevice;
            }

            private SingleFieldBuilderV3<RemoteResetPreferredAudioDevice, RemoteResetPreferredAudioDevice.Builder, RemoteResetPreferredAudioDeviceOrBuilder> getRemoteResetPreferredAudioDeviceFieldBuilder() {
                if (this.remoteResetPreferredAudioDeviceBuilder_ == null) {
                    this.remoteResetPreferredAudioDeviceBuilder_ = new SingleFieldBuilderV3<>(getRemoteResetPreferredAudioDevice(), getParentForChildren(), isClean());
                    this.remoteResetPreferredAudioDevice_ = null;
                }
                return this.remoteResetPreferredAudioDeviceBuilder_;
            }

            @Override
            public boolean hasRemoteAppLinkLaunchRequest() {
                return (this.remoteAppLinkLaunchRequestBuilder_ == null && this.remoteAppLinkLaunchRequest_ == null) ? false : true;
            }

            @Override
            public RemoteAppLinkLaunchRequest getRemoteAppLinkLaunchRequest() {
                SingleFieldBuilderV3<RemoteAppLinkLaunchRequest, RemoteAppLinkLaunchRequest.Builder, RemoteAppLinkLaunchRequestOrBuilder> singleFieldBuilderV3 = this.remoteAppLinkLaunchRequestBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest = this.remoteAppLinkLaunchRequest_;
                return remoteAppLinkLaunchRequest == null ? RemoteAppLinkLaunchRequest.getDefaultInstance() : remoteAppLinkLaunchRequest;
            }

            public Builder setRemoteAppLinkLaunchRequest(RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest) {
                SingleFieldBuilderV3<RemoteAppLinkLaunchRequest, RemoteAppLinkLaunchRequest.Builder, RemoteAppLinkLaunchRequestOrBuilder> singleFieldBuilderV3 = this.remoteAppLinkLaunchRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Objects.requireNonNull(remoteAppLinkLaunchRequest);
                    this.remoteAppLinkLaunchRequest_ = remoteAppLinkLaunchRequest;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(remoteAppLinkLaunchRequest);
                }
                return this;
            }

            public Builder setRemoteAppLinkLaunchRequest(RemoteAppLinkLaunchRequest.Builder builder) {
                SingleFieldBuilderV3<RemoteAppLinkLaunchRequest, RemoteAppLinkLaunchRequest.Builder, RemoteAppLinkLaunchRequestOrBuilder> singleFieldBuilderV3 = this.remoteAppLinkLaunchRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.remoteAppLinkLaunchRequest_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRemoteAppLinkLaunchRequest(RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest) {
                SingleFieldBuilderV3<RemoteAppLinkLaunchRequest, RemoteAppLinkLaunchRequest.Builder, RemoteAppLinkLaunchRequestOrBuilder> singleFieldBuilderV3 = this.remoteAppLinkLaunchRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest2 = this.remoteAppLinkLaunchRequest_;
                    if (remoteAppLinkLaunchRequest2 != null) {
                        this.remoteAppLinkLaunchRequest_ = RemoteAppLinkLaunchRequest.newBuilder(remoteAppLinkLaunchRequest2).mergeFrom(remoteAppLinkLaunchRequest).buildPartial();
                    } else {
                        this.remoteAppLinkLaunchRequest_ = remoteAppLinkLaunchRequest;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(remoteAppLinkLaunchRequest);
                }
                return this;
            }

            public Builder clearRemoteAppLinkLaunchRequest() {
                if (this.remoteAppLinkLaunchRequestBuilder_ == null) {
                    this.remoteAppLinkLaunchRequest_ = null;
                    onChanged();
                } else {
                    this.remoteAppLinkLaunchRequest_ = null;
                    this.remoteAppLinkLaunchRequestBuilder_ = null;
                }
                return this;
            }

            public RemoteAppLinkLaunchRequest.Builder getRemoteAppLinkLaunchRequestBuilder() {
                onChanged();
                return getRemoteAppLinkLaunchRequestFieldBuilder().getBuilder();
            }

            @Override
            public RemoteAppLinkLaunchRequestOrBuilder getRemoteAppLinkLaunchRequestOrBuilder() {
                SingleFieldBuilderV3<RemoteAppLinkLaunchRequest, RemoteAppLinkLaunchRequest.Builder, RemoteAppLinkLaunchRequestOrBuilder> singleFieldBuilderV3 = this.remoteAppLinkLaunchRequestBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RemoteAppLinkLaunchRequest remoteAppLinkLaunchRequest = this.remoteAppLinkLaunchRequest_;
                return remoteAppLinkLaunchRequest == null ? RemoteAppLinkLaunchRequest.getDefaultInstance() : remoteAppLinkLaunchRequest;
            }

            private SingleFieldBuilderV3<RemoteAppLinkLaunchRequest, RemoteAppLinkLaunchRequest.Builder, RemoteAppLinkLaunchRequestOrBuilder> getRemoteAppLinkLaunchRequestFieldBuilder() {
                if (this.remoteAppLinkLaunchRequestBuilder_ == null) {
                    this.remoteAppLinkLaunchRequestBuilder_ = new SingleFieldBuilderV3<>(getRemoteAppLinkLaunchRequest(), getParentForChildren(), isClean());
                    this.remoteAppLinkLaunchRequest_ = null;
                }
                return this.remoteAppLinkLaunchRequestBuilder_;
            }

            @Override
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }
}
