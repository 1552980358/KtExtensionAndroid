package lib.github1552980358.ktExtension.androidx.preference

import androidx.annotation.StringRes
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import androidx.preference.SwitchPreferenceCompat

/**
 * Get [Preference] instance with [resId]
 **/
fun PreferenceFragmentCompat.preference(@StringRes resId: Int) = preference(getString(resId))

/**
 * Get [Preference] instance with [name]
 **/
fun PreferenceFragmentCompat.preference(name: String) = findPreference<Preference>(name)

/**
 * Get [Preference] instance with [resId], and with extra execution block
 **/
fun PreferenceFragmentCompat.preference(@StringRes resId: Int, runPreference: Preference.() -> Unit) =
    preference(resId)?.runPreference()

/**
 * Get [Preference] instance with [name], and with extra execution block
 **/
fun PreferenceFragmentCompat.preference(name: String, runPreference: Preference.() -> Unit) =
    preference(name)?.runPreference()

/**
 * Get [SwitchPreference] instance with [resId]
 **/
fun PreferenceFragmentCompat.switchPreference(@StringRes resId: Int) = switchPreference(getString(resId))

/**
 * Get [SwitchPreference] instance with [name]
 **/
fun PreferenceFragmentCompat.switchPreference(name: String) = preference(name) as SwitchPreference?

/**
 * Get [SwitchPreference] instance with [resId], and with extra execution block
 **/
fun PreferenceFragmentCompat.switchPreference(@StringRes resId: Int, runPreference: SwitchPreference.() -> Unit) =
    switchPreference(resId)?.runPreference()

/**
 * Get [SwitchPreference] instance with [name], and with extra execution block
 **/
fun PreferenceFragmentCompat.switchPreference(name: String, runPreference: SwitchPreference.() -> Unit) =
    switchPreference(name)?.runPreference()

/**
 * Get [SwitchPreferenceCompat] instance with [resId]
 **/
fun PreferenceFragmentCompat.switchPreferenceCompat(@StringRes resId: Int) = switchPreferenceCompat(getString(resId))

/**
 * Get [SwitchPreferenceCompat] instance with [name]
 **/
fun PreferenceFragmentCompat.switchPreferenceCompat(name: String) = preference(name) as SwitchPreferenceCompat?

/**
 * Get [SwitchPreferenceCompat] instance with [resId], and with extra execution block
 **/
fun PreferenceFragmentCompat.switchPreferenceCompat(@StringRes resId: Int, runPreference: SwitchPreferenceCompat.() -> Unit) =
    switchPreferenceCompat(resId)?.runPreference()

/**
 * Get [SwitchPreferenceCompat] instance with [name], and with extra execution block
 **/
fun PreferenceFragmentCompat.switchPreferenceCompat(name: String, runPreference: SwitchPreferenceCompat.() -> Unit) =
    switchPreferenceCompat(name)?.runPreference()

/**
 * Get [EditTextPreference] instance with [resId]
 **/
fun PreferenceFragmentCompat.editTextPreference(@StringRes resId: Int) = editTextPreference(getString(resId))

/**
 * Get [EditTextPreference] instance with [name]
 **/
fun PreferenceFragmentCompat.editTextPreference(name: String) = preference(name) as EditTextPreference?

/**
 * Get [EditTextPreference] instance with [resId], and with extra execution block
 **/
fun PreferenceFragmentCompat.editTextPreference(@StringRes resId: Int, runPreference: EditTextPreference.() -> Unit) =
    editTextPreference(resId)?.runPreference()

/**
 * Get [EditTextPreference] instance with [name], and with extra execution block
 **/
fun PreferenceFragmentCompat.editTextPreference(name: String, runPreference: EditTextPreference.() -> Unit) =
    editTextPreference(name)?.runPreference()