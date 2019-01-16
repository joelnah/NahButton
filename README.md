# NahButton
[![](https://jitpack.io/v/joelnah/NahButton.svg)](https://jitpack.io/#joelnah/NahButton)



implementation 'com.github.joelnah:NahButton:Tag'


스크롤뷰 사용시 NahScrollViewH / NahScrollViewV 사용
    
    <nah.prayer.translib.NahImageView
        android:id="@+id/iv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:imageview_img="@mipmap/ic_launcher"
        app:imageview_imgPress="@android:drawable/ic_media_play"
        app:imageview_scale="0.9"
        app:imageview_colorFilter="#88234987"/>

    <nah.prayer.translib.NahImageButton
        android:id="@+id/iv_btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:imageview_img="@mipmap/ic_launcher"
        app:imageview_imgPress="@android:drawable/ic_media_play"
        app:imageview_scale="0.9"
        app:imageview_colorFilter="#88234987"/>

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
        app:nah_stroke="4"
        app:nah_strokeColor="@android:color/holo_red_dark"
        app:nah_labelColorClick="@android:color/holo_orange_dark"
        app:nah_labelSize="16"
        app:nah_labelStyle="bold"
        app:drawableHeight="36dp"
        app:drawableWidth="36dp"
        app:nah_scale="0.9"
        app:nah_round="20"
        tools:text="NahButton"/>

    <nah.prayer.translib.NahLayout
        android:id="@+id/layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="20dp"
        app:layout_startColor="@color/colorPrimaryDark"
        app:layout_midColor="@color/colorPrimary"
        app:layout_endColor="@color/colorAccent"
        app:layout_upEffect="true"
        app:layout_animationType="single"
        app:layout_scale="0.9"
        app:layout_round="20">
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

    <nah.prayer.translib.NahTextView
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
