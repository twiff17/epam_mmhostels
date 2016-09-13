package by.epam.hostelbeta.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Class JsonReader. Class for reading json object from url
 */
public class JsonReader {

	/** The Constant ENCODING. */
	private static final String ENCODING = "UTF-8";

	/**
	 * Reads a string from a stream.
	 *
	 * @param rd
	 *            the reader
	 * @return the read string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * Reads json object from url.
	 *
	 * @param url
	 *            the url
	 * @return the JSON object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JSONException
	 *             the JSON exception
	 */
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName(ENCODING)));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
}
