package com.voxeet.specifics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.voxeet.sdk.push.center.management.Constants;
import com.voxeet.uxkit.activities.VoxeetAppCompatActivity;

public class BounceVoxeetActivity extends VoxeetAppCompatActivity {

    private static Class<? extends Activity> ActivityClass;

    public static void registerBouncedActivity(Class<? extends Activity> activityClass) {
        ActivityClass = activityClass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, ActivityClass);

        //- - - - - - - - - - - - - - - - - - - -
        //code from the VoxeetAppCompatActivity
        //- - - - - - - - - - - - - - - - - - - -

        intent.putExtra(Constants.CONF_ID, getExtraVoxeetBundleChecker().getConferenceId())
                .putExtra(Constants.INVITER_NAME, getExtraVoxeetBundleChecker().getUserName())
                .putExtra(Constants.INVITER_ID, getExtraVoxeetBundleChecker().getExternalUserId())
                .putExtra(Constants.INVITER_EXTERNAL_ID, getExtraVoxeetBundleChecker().getExternalUserId())
                .putExtra(Constants.INVITER_URL, getExtraVoxeetBundleChecker().getAvatarUrl());

        //TODO check usefullness
        intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
        startActivity(intent);

        finish();
    }
}
