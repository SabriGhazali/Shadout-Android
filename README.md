﻿# Shadout Library for Android


![Language](https://img.shields.io/badge/language-Kotlin-orange.svg)


# 💻Example

![Alt Text](https://media.giphy.com/media/XDvfnuMFgMMsqzA7lA/giphy.gif)





# 💻Usage


# Gradle dependency:

```groovy
	allprojects {
	   repositories {
           	maven { url "https://jitpack.io" }  //Make sure to add this in your project
	   }
	}
```
		
	
```groovy
	implementation 'com.github.SabriGhazali:Shadout-Android:1.0.2'
```
	
	
# Java
# Set Color Programmatically

```groovy
	shadout.setMShadowColor(getResources().getColor(R.color.color_blue_shadow));  color_blue_shadow : #660075EB
```

	
# XML

```groovy
	<com.github.sablasvegas.shadout.Shadout
        android:id="@+id/shadout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:shadowColor="@color/color_pink_shadow"  //#660075EB Starting shadow color with opacity 66 in our case and pink color is 0075EB
        app:Dy="7dp"
        app:Dx="5dp"
        app:shadowRadius="10dp"
        app:cornerRadius="100dp">
        //Put your elements here ! 
       </com.github.sablasvegas.shadout.Shadout>
```
	
