package com.bastor.facebookwebview;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;


public class FacebookWebViewPackage implements ReactPackage {
	private FacebookWebViewManager manager;
	private FacebookWebViewModule module;
	
	@Override
	public List<Class<? extends JavaScriptModule>> createJSModules() {
		return Collections.emptyList();
	}

	@Override
	public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
		manager = new FacebookWebViewManager();
		manager.setPackage(this);
		return Arrays.<ViewManager>asList(manager);
	}

	@Override
	public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
		List<NativeModule> modules = new ArrayList<>();
		
		module = new FacebookWebViewModule(reactContext)
		module.setPackage(this);
		modules.add(module);
		
		return modules;
	}
	
	public FacebookWebViewManager getManager() {
		return manager;
	}
	
	public FacebookWebViewModule getModule() {
		return module;
	}
}
