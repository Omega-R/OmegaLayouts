[![](https://jitpack.io/v/Omega-R/OmegaLayouts.svg)](https://jitpack.io/#Omega-R/OmegaLayouts)
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

# OmegaLayouts
Several different layout to make your life easier.

# Installation
To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency
```
dependencies {
    implementation 'com.github.Omega-R:R:OmegaLayouts:0.0.1'
}
```

# Usage

## Percents
Layouts which support percentage based dimensions and margins. You can specify dimension or a margin of child by using attributes with "Percent" suffix. 

Supported classes:
* com.omega_r.libs.layouts.percent.PercentLinearLayout
* com.omega_r.libs.layouts.percent.PercentRelativeLayout
* com.omega_r.libs.layouts.percent.PercentFrameLayout

Follow this example:
```
<com.omega_r.libs.layouts.percent.PercentFrameLayout
         android:layout_width="match_parent"
         android:layout_height="match_parent">
     <ImageView
         app:layout_widthPercent="50%"
         app:layout_heightPercent="50%"
         app:layout_marginTopPercent="25%"
         app:layout_marginLeftPercent="25%"/>
 </com.omega_r.libs.layouts.percent.PercentFrameLayout>
```

## Shadow

Shadow layouts draw shadow inside your layout.

Supported classes:
* com.omega_r.libs.layouts.shadow.ShadowFrameLayout
* com.omega_r.libs.layouts.shadow.ShadowLinearLayout
* com.omega_r.libs.layouts.shadow.ShadowRelativeLayout


<p align="left">
    <img src="/images/shadow.png?raw=true" width="300" height="533" />
</p>

```
    <com.omega_r.libs.layouts.shadow.ShadowFrameLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:background="@color/pale_grey_two"
        app:shadowTopHeight="10dp"
        app:shadowLeftHeight="10dp"
        app:shadowRightHeight="10dp"
        app:shadowBottomHeight="10dp"
        
        // If you want to use your own color use this parameters.
        app:startColor="@color/colorAccent"
        app:centerColor="@color/colorPrimary"
        app:endColor="@color/colorAccent">
```

## AutoOrientationLinearLayout

AutoOrientationLinearLayout it's subclass of LinearLayoutCompat that change orientation automatically if you don't have necessary width for all views on small screens. 
Follow this example:

<img src="/images/auto_orientation_vertical.png?raw=true" width="300" height="533" />    <img src="/images/auto_orientation_horizontal.png?raw=true" width="533" height="300" align="top"/>

```
    <com.omega_r.libs.layouts.AutoOrientationLinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@color/colorPrimary"
            android:padding="10dp"
            android:text="This is first text"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@color/colorAccent"
            android:padding="10dp"
            android:text="This is second text"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

    </com.omega_r.libs.layouts.AutoOrientationLinearLayout>
```


# License
```
The MIT License

Copyright 2017 Omega-R

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
associated documentation files (the "Software"), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, 
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
