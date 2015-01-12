package com.olko123.android.androidtest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Requester {
	private static final String TAG = "Requester";
	private static final String[] emptyObjects = { "author" };

	public static JsonElement getJsonObject(URL requestUrl) throws IOException {
		Log.d(TAG, "getJsonObject() called with URL=" + requestUrl.toString());
		HttpURLConnection connection = (HttpURLConnection) requestUrl
				.openConnection();
		Log.i(TAG,
				"getJsonObject() - connection to URL " + requestUrl.toString()
						+ " opened ");
		connection.setRequestMethod("GET");
		connection.connect();

		InputStream is = connection.getInputStream();
		Reader reader = new InputStreamReader(is);

		JsonParser jsonParser = new JsonParser();
		return jsonParser.parse(reader);
	}

	public static <T> T getParsedObject(URL requestUrl, Class<T> clazz)
			throws IOException, JsonSyntaxException {
		Log.d(TAG, "getParsedObject() called with URL=" + requestUrl.toString());
		JsonElement element = getJsonObject(requestUrl);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		if (clazz.isArray()) {
			element = element.getAsJsonArray();
		} else {
			element = element.getAsJsonObject();
			element = fixJsonObject((JsonObject) element);
		}

		T ret = gson.fromJson(element, clazz);
		return ret;
	}

	private static JsonObject fixJsonObject(JsonObject jsonObject) {
		for (String key : emptyObjects) {
			if (jsonObject.get(key).isJsonArray()) {
				jsonObject.addProperty(key, "");
			}
		}

		return jsonObject;
	}
}
