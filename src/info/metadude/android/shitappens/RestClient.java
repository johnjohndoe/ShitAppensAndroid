package info.metadude.android.shitappens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.util.Log;

public class RestClient {

	/**
	 * Defines the accepted MIME type for the server response.
	 */
	private static final String ACCEPT = "Accept";
	/**
	 * Defines the MIME type that is sent with the HTTP POST request.
	 */
	private static final String CONTENT_TYPE = "Content-type";
	/**
	 * Defines the accepted encoding method.
	 */
	private static final String ACCEPT_ENCODING = "Accept-Encoding";
	/**
	 * MIME type JSON.
	 */
	private static final String MIME_TYPE_JSON = "application/json";
	/**
	 * Encoding method GZIP.
	 */
	private static final String GZIP = "gzip";
	/**
	 * Character set UTF-8.
	 */
	private static final String UTF8 = "UTF-8";


	public static JSONObject sendHttpPost(String url, JSONObject message) {

		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(message.toString(), UTF8);
			stringEntity.setContentType(MIME_TYPE_JSON);

			httpPost.setHeader(ACCEPT, MIME_TYPE_JSON);
			httpPost.setHeader(CONTENT_TYPE, MIME_TYPE_JSON);
			httpPost.setHeader(ACCEPT_ENCODING, GZIP);
			httpPost.setEntity(stringEntity);

			long startTime = System.currentTimeMillis();
			HttpResponse response = (HttpResponse)httpClient.execute(httpPost);
			Log.i(RestClient.class.getName(), "HTTPResponse received in [" + (System.currentTimeMillis() - startTime) + "ms]");

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				Header contentEncoding = response.getFirstHeader("Content-Encoding");
				if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase(GZIP)) {
					inputStream = new GZIPInputStream(inputStream);
				}
				String resultString = convertStreamToString(inputStream);
				inputStream.close();
//				resultString = resultString.substring(1,resultString.length()-1); // remove wrapping "[" and "]"

				// Transform the String into a JSONObject
				JSONObject jsonObjRecv = new JSONObject(resultString);
				// Raw DEBUG output of our received JSON object:
				Log.i(RestClient.class.getName(), "<JSONObject>\n"+jsonObjRecv.toString()+"\n</JSONObject>");

				return jsonObjRecv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the BufferedReader.readLine()
		 * method. We iterate until the BufferedReader return null which means
		 * there's no more data to read. Each line will appended to a StringBuilder
		 * and returned as String.
		 *
		 * (c) public domain: http://senior.ceng.metu.edu.tr/2009/praeda/2009/01/11/a-simple-restful-client-at-android/
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}