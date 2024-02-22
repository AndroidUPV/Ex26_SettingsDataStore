# Ex26_SettingsDataStore

Lecture 06 - Local Storage

The app displays a greetings message and an icon.
An action element gives access to a settings screen that manages the user preferences about her user name, the text color, and the icon visibility.
The settings screen has been designed using the Preferences library.
The user's preferences are stored in local storage using DataStore instead of SharedPreferences.
The PreferenceDataStore interface must be implemented and provided to the PreferenceManager BEFORE creating the preference hierarchy from the XMl resource file.
