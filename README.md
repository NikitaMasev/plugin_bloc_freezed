# plugin_bloc_freezed
Plugin for Android Studio, minimum required version 2021.2.1. Generating Bloc(flutter_bloc) with Freezed states and events.

## Introduction

BlocFreezed plugin for  [Android Studio](https://developer.android.com/studio/) with support for the [Bloc Library](https://bloclibrary.dev) and [Freezed](https://pub.dev/packages/freezed). Allow to create some boilerplate folder, bloc and states with events files. 

## Installation

You can install jar file of plugin manualy:
1. Download file by link.

	![download_link](https://github.com/NikitaMasev/plugin_bloc_freezed/build/libs/bloc_freezed-1.0.jar)
	
2. In Android Studio open `File - Settings - Plugins`.  And choose `Install Plugin From Disk`, select jar file.

![select_plugin_file](https://github.com/NikitaMasev/plugin_bloc_freezed/blob/main/assets/plugin_install.png)

3. Click `Apply` and `OK`. 

### How to use

Simply right click on the File Project view, go to `New -> Bloc Freezed`. Also you can use shortcut `CTRL+NUMPAD-2`

![plugin_action](https://github.com/NikitaMasev/plugin_bloc_freezed/blob/main/assets/plugin_action.png)

Give it a name in dialog windows, prefer using CameCase format.

![plugin_dialog_window](https://github.com/NikitaMasev/plugin_bloc_freezed/blob/main/assets/plugin_dialog_window.png)

Click `OK`. And after you can see new folder and files in File Project View.

![plugin_created_files](https://github.com/NikitaMasev/plugin_bloc_freezed/blob/main/assets/plugin_created_files.png)

Generated files:

![plugin_created_bloc](https://github.com/NikitaMasev/plugin_bloc_freezed/blob/main/assets/plugin_created_bloc.png)

![plugin_created_events_states](https://github.com/NikitaMasev/plugin_bloc_freezed/blob/main/assets/plugin_created_events_states.png)

It remains only to generate `freezed` models by: 

```
dart run build_runner build
```