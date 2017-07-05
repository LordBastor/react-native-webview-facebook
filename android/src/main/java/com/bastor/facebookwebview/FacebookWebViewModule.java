package com.bastor.facebookwebview;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;


public class FacebookWebViewModule extends ReactContextBaseJavaModule
{
	public Module(ReactApplicationContext reactContext) {
		super(reactContext);
	}
	
	@Override
	public String getName() {
		return "FacebookWebView";
	}
	
	
}