package com.olko123.android.androidtest;

import android.app.Application;

public class LefigaroApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		initModel();
	}

	private void initModel() {
		AppModel.initModel();
	}
	
}
