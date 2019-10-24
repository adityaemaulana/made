package com.example.subfinal.preference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.PreferenceFragmentCompat;

import com.example.subfinal.R;
import com.example.subfinal.widget.ReminderReceiver;

public class ReminderPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private ReminderReceiver receiver;

    private String KEY_DAILY, KEY_RELEASE;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);

        receiver = new ReminderReceiver();

        KEY_DAILY = getResources().getString(R.string.key_pref_daily);
        KEY_RELEASE = getResources().getString(R.string.key_pref_release);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_DAILY)) {
            boolean remindDaily = sharedPreferences.getBoolean(KEY_DAILY, false);
            if (remindDaily) {
                receiver.setDailyReminder(getContext());
            } else {
                receiver.cancelAlarm(getContext(), ReminderReceiver.TYPE_DAILY_REMINDER);
            }
        }

        if (key.equals(KEY_RELEASE)) {
            boolean remindDaily = sharedPreferences.getBoolean(KEY_RELEASE, false);
            if (remindDaily) {
                receiver.setReleaseReminder(getContext());
            } else {
                receiver.cancelAlarm(getContext(), ReminderReceiver.TYPE_RELEASE_REMINDER);
            }
        }
    }
}
