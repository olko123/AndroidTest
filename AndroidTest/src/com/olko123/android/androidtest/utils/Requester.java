package com.olko123.android.androidtest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Requester {

	public static <T> T getParsedObject(URL requestUrl, Class<T> clazz) throws IOException, JsonSyntaxException {
		HttpURLConnection connection = (HttpURLConnection) requestUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		InputStream is = connection.getInputStream();
		Reader reader = new InputStreamReader(is);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(reader).getAsJsonObject();

		return gson.fromJson(jsonObject, clazz);
	}
}
