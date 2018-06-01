package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_sunshine);

        PreferenceScreen prefScreen = getPreferenceScreen();
        SharedPreferences shPref = prefScreen.getSharedPreferences();
        int count = prefScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)) {
                setPreferenceSummary(p, shPref.getString(p.getKey(), ""));
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);

        if (!(pref instanceof CheckBoxPreference)){
            setPreferenceSummary(pref, sharedPreferences.getString(pref.getKey(), ""));
        }
    }

    private void setPreferenceSummary(Preference pref, Object obj){
        String value = obj.toString();
        if (pref instanceof ListPreference){
            ListPreference listPreference = (ListPreference) pref;

            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0){
                pref.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else if (pref instanceof EditTextPreference){
            pref.setSummary(value);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
