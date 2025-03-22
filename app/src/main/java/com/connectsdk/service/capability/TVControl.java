package com.connectsdk.service.capability;

import com.connectsdk.core.ChannelInfo;
import com.connectsdk.core.ProgramInfo;
import com.connectsdk.core.ProgramList;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceSubscription;

import java.util.List;


public interface TVControl extends CapabilityMethods {
    public static final String Any = "TVControl.Any";
    public static final String Channel_Get = "TVControl.Channel.Get";
    public static final String Channel_Set = "TVControl.Channel.Set";
    public static final String Channel_Up = "TVControl.Channel.Up";
    public static final String Channel_Down = "TVControl.Channel.Down";
    public static final String Channel_List = "TVControl.Channel.List";
    public static final String Channel_Subscribe = "TVControl.Channel.Subscribe";
    public static final String Program_Get = "TVControl.Program.Get";
    public static final String Program_List = "TVControl.Program.List";
    public static final String Program_Subscribe = "TVControl.Program.Subscribe";
    public static final String Program_List_Subscribe = "TVControl.Program.List.Subscribe";
    public static final String Get_3D = "TVControl.3D.Get";
    public static final String Set_3D = "TVControl.3D.Set";
    public static final String Subscribe_3D = "TVControl.3D.Subscribe";
    public static final String[] Capabilities = {Channel_Get, Channel_Set, Channel_Up, Channel_Down, Channel_List, Channel_Subscribe, Program_Get, Program_List, Program_Subscribe, Program_List_Subscribe, Get_3D, Set_3D, Subscribe_3D};


    public interface ChannelListListener extends ResponseListener<List<ChannelInfo>> {
    }


    public interface ChannelListener extends ResponseListener<ChannelInfo> {
    }


    public interface ProgramInfoListener extends ResponseListener<ProgramInfo> {
    }


    public interface ProgramListListener extends ResponseListener<ProgramList> {
    }


    public interface State3DModeListener extends ResponseListener<Boolean> {
    }

    void channelDown(ResponseListener<Object> listener);

    void channelUp(ResponseListener<Object> listener);

    void get3DEnabled(State3DModeListener listener);

    void getChannelList(ChannelListListener listener);

    void getCurrentChannel(ChannelListener listener);

    void getProgramInfo(ProgramInfoListener listener);

    void getProgramList(ProgramListListener listener);

    TVControl getTVControl();

    CapabilityPriorityLevel getTVControlCapabilityLevel();

    void set3DEnabled(boolean enabled, ResponseListener<Object> listener);

    void setChannel(ChannelInfo channelNumber, ResponseListener<Object> listener);

    ServiceSubscription<State3DModeListener> subscribe3DEnabled(State3DModeListener listener);

    ServiceSubscription<ChannelListener> subscribeCurrentChannel(ChannelListener listener);

    ServiceSubscription<ProgramInfoListener> subscribeProgramInfo(ProgramInfoListener listener);

    ServiceSubscription<ProgramListListener> subscribeProgramList(ProgramListListener listener);
}
