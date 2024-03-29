# KtExtensionAndroid

An extension library of Kotlin especially designed for Android platform

[![](https://jitpack.io/v/1552980358/KtExtensionAndroid.svg)](https://jitpack.io/#1552980358/KtExtensionAndroid)

### Required dependencies
Following two dependencies should be implemented, otherwise the whole project won't work

- org.jetbrains.kotlin:kotlin-stdlib-jdk8

    Dependency `org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4-M2`

- com.github.1552980358:KtExtension

    [![](https://jitpack.io/v/1552980358/KtExtension.svg)](https://jitpack.io/#1552980358/KtExtension)
    
### Extended Class
Should implement dependencies into your module `build.gradle` when the specific extensions are needed

1. Android
    - android.app
        - `Activity`
    - android.content
        - res
            - `Resources`
        - `BroadcastReceiver`
        - `Content`
        - `Intent`
        - `SharedPreferences`
    - android.graphics
        - `Bitmap`
            - Dependency `androidx.core:core-ktx:1.3.0`
        - `Drawable`
            - Dependency `androidx.core:core-ktx:1.3.0`
    - android.util
        - `Log`
    - android.view
        - `View`
        - `ViewParent`
    - android.widget
        - `Toast`
2. AndroidX
   - androidx.fragment
     - `Fragment`
     - `SimpleFragmentPagerAdapter`
   - androidx.palette
       - `Palette`
           - Dependency `androidx.palette:palette-ktx:1.0.0`
   - android.viewpager.widget
     - `ViewPager`
3. Kotlin
    - `ByteArray`
4. Java
    - `File`
    - `InputStream`
5. More Extension is on the way...

### Open Source License - [Apache License 2.0](https://github.com/1552980358/KtExtensionAndroid/blob/master/LICENSE)
```
                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/

   TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
```