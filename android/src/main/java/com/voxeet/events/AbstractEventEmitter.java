package com.voxeet.events;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

public class AbstractEventEmitter {

    private ReactContext context;
    private EventBus eventBus;
    private HashMap<Class, EventFormatterCallback> transformers;

    private AbstractEventEmitter() {
        transformers = new HashMap<>();
    }

    protected AbstractEventEmitter(@NonNull ReactContext context, @NonNull EventBus eventBus) {
        this();

        this.context = context;
        this.eventBus = eventBus;
    }


    protected <TYPE> AbstractEventEmitter register(EventFormatterCallback<TYPE> callback) {
        transformers.put(callback.getKlass(), callback);
        return this;
    }

    protected <TYPE> AbstractEventEmitter emit(TYPE object) {
        EventFormatterCallback<TYPE> callback = transformers.get(object.getClass());
        WritableMap map = new WritableNativeMap();
        callback.transform(map, object);

        WritableMap event = new WritableNativeMap();
        WritableMap eventEvent = new WritableNativeMap();
        eventEvent.merge(map);

        event.putMap("event", eventEvent);
        event.putString("name", callback.name());

        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(callback.name(), map.copy());

        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("VoxeetEvent", event);

        return this;
    }

    public void register() {
        if (!eventBus.isRegistered(this))
            eventBus.register(this);
    }

    public void unRegister() {
        if (eventBus.isRegistered(this))
            eventBus.unregister(this);
    }
}
