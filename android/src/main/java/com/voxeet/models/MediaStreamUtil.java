package com.voxeet.models;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.voxeet.android.media.MediaStream;
import com.voxeet.video.RNVideoViewManager;

public final class MediaStreamUtil {
    private MediaStreamUtil() {

    }

    @NonNull
    public static WritableMap toMap(@NonNull MediaStream stream) {
        WritableMap map = new WritableNativeMap();
        map.putString(RNVideoViewManager.PEER_ID, stream.peerId());
        map.putString(RNVideoViewManager.LABEL, stream.label());
        map.putString(RNVideoViewManager.STREAM_TYPE, stream.getType().name());
        return map;
    }
}
