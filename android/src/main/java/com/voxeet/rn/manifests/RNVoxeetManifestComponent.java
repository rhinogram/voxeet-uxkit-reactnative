package com.voxeet.rn.manifests;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.voxeet.RNVoxeetConferencekitModule;
import com.voxeet.sdk.manifests.AbstractManifestComponentProvider;
import com.voxeet.sdk.preferences.VoxeetPreferences;
import com.voxeet.sdk.utils.VoxeetEnvironmentHolder;
import com.voxeet.specifics.RNRootViewProvider;
import com.voxeet.uxkit.controllers.VoxeetToolkit;
import com.voxeet.uxkit.implementation.overlays.OverlayState;

import org.greenrobot.eventbus.EventBus;

public final class RNVoxeetManifestComponent extends AbstractManifestComponentProvider {

    /**
     * Static instance of the root view provider to be used by the app's voxeet instance
     */
    public static RNRootViewProvider root_view_provider;

    private static final String TAG = RNVoxeetManifestComponent.class.getSimpleName();

    @Override
    protected void init(@NonNull Context context) {
        if (!(context instanceof Application)) {
            Log.d(TAG, "init: ISSUE CONTEXT IS NOT AN Application");
            return;
        }

        Application application = (Application) context;

        RNVoxeetManifestComponent.root_view_provider = new RNRootViewProvider(application, VoxeetToolkit.instance());

        //in sdk mode, no issues
        VoxeetPreferences.init(application, new VoxeetEnvironmentHolder(application));

        RNVoxeetConferencekitModule.initNotificationCenter();
    }

    @Override
    protected String getComponentName() {
        return RNVoxeetManifestComponent.class.getSimpleName();
    }

    @Override
    protected String getDefaultAuthority() {
        return "com.voxeet.rn.manifests.";
    }
}
