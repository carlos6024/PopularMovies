package practice.c4.com.popularmovies.activities;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import practice.c4.com.popularmovies.R;

public class SettingActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenceSortFragment()).commit();
    }



    public static class PreferenceSortFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            PreferenceManager.setDefaultValues(getActivity(),
                    R.xml.preference_general, false);

            addPreferencesFromResource(R.xml.preference_general);
        }
    }


    private void bindPreferenceSummaryToValue(Preference preference){
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
        .getString(preference.getKey(),""));

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue =  value.toString();
        if(preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);

            if(prefIndex>=0){
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            } else{
                preference.setSummary(stringValue);
            }

        }
        return true;
    }
}
