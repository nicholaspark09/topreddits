package com.example.vn008xw.reddit.util;

import android.support.annotation.NonNull;

// Contract that defines the methods for a permissions helper implementation
public interface PermissionsHelperContract {

    void makeRequest(@NonNull String permission);

    void onPermissionRequestResult(int requestCode, @NonNull String[] permissions, @NonNull int[] results);

    boolean isPermissionDeniedForever(@NonNull String permission);

    boolean isPermissionGranted(@NonNull String permission);

    boolean isRationaleNeeded(@NonNull String permission);

    interface Callback {

        void onPermissionGranted(@NonNull String permission);

        void onPermissionDeclined(@NonNull String permission);

        void onPermissionNeedsRationale(@NonNull String permission);

        void onPermissionDeclinedForever(@NonNull String permission);
    }
}