# NahButton
[![](https://jitpack.io/v/joelnah/NahButton.svg)](https://jitpack.io/#joelnah/NahButton)



implementation 'com.github.joelnah:NahButton:Tag'

        NahImageView iv = findViewById(R.id.iv);
        iv.setOnTouchListerer(new TransTouchListener() {
            @Override
            public void onTouch() {
                Toast.makeText(MainActivity.this, "iv click", Toast.LENGTH_SHORT).show();
            }
        });


스크롤뷰 사용시 NahScrollViewH / NahScrollViewV 사용
    
    <nah.prayer.translib.NahScrollViewV
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:orientation="vertical"
        tools:context=".MainActivity">

        <nah.prayer.translib.NahImageView
            android:id="@+id/iv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:image_img="@mipmap/ic_launcher"
            app:image_duration="500"
            app:image_imgPress="@mipmap/ic_launcher"
            app:image_scale="0.9"
            app:image_colorFilter="#88234987"/>

        <nah.prayer.translib.NahImageButton
            android:id="@+id/iv_btn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:image_img="@mipmap/ic_launcher"
            app:image_imgPress="@mipmap/ic_launcher"
            app:image_scale="0.9"
            app:image_colorFilter="#88234987"/>

        <nah.prayer.translib.NahButton
            android:id="@+id/btn"
            android:layout_width="wrap_content"
            android:layout_height="50dp"
            android:padding="10dp"
            android:drawableEnd="@mipmap/ic_launcher_round"
            android:drawableRight="@mipmap/ic_launcher_round"
            app:nah_startColor="@android:color/holo_green_dark"
            app:nah_midColor="@android:color/white"
            app:nah_endColor="@android:color/holo_red_dark"
            app:nah_upEffect="true"
            app:nah_animationType="gradient"
            app:nah_labelText="NahButton"
            app:nah_stroke="8"
            app:nah_duration="200"
            app:nah_strokeColor="@android:color/holo_red_dark"
            app:nah_labelColorClick="@android:color/holo_orange_dark"
            app:nah_labelSize="16"
            app:nah_labelStyle="bold"
            app:drawableHeight="36dp"
            app:drawableWidth="36dp"
            app:nah_scale="0.9"
            app:nah_round="20"
            tools:text="NahButton"/>
    <LinearLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
        <nah.prayer.translib.NahLayout
            android:id="@+id/layout"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="20dp"
            app:layout_duration="500"
            app:layout_scale="0.9">
            <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="vertical">
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="tsetsetset"/>
            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@mipmap/ic_launcher_round"/>
            </LinearLayout>
        </nah.prayer.translib.NahLayout>
    </LinearLayout>
    </LinearLayout>
        <nah.prayer.translib.NahTextView
            android:id="@+id/tv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="10dp"
            android:drawableTop="@mipmap/ic_launcher_round"
            app:nah_startColor="@android:color/holo_green_dark"
            app:nah_midColor="@android:color/white"
            app:nah_endColor="@android:color/holo_red_dark"
            app:nah_upEffect="true"
            app:nah_animationType="none"
            app:nah_labelText="NahTextView"
            app:nah_stroke="4"
            app:nah_strokeColor="@android:color/holo_red_dark"
            app:nah_labelColorClick="@android:color/holo_orange_dark"
            app:nah_labelSize="16"
            app:nah_labelStyle="bold"
            app:drawableHeight="50dp"
            app:drawableWidth="50dp"
            app:nah_scale="0.9"
            app:nah_round="20"
            tools:text="NahTextView"/>

    </LinearLayout>
    </nah.prayer.translib.NahScrollViewV>
