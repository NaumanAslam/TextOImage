package mwx.textoimage;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nauman.aslam on 12/22/2016.
 */


public class TextoImage extends AsyncTask<String, Void, String> {
    public AsyncResponse delegate = null;
    public ProgressDialog progressDialog;
    Bitmap mIcon11 = null;

    String server_response = "", URL_String = "";
    ArrayList<String> _dataForOriginal = new ArrayList<>();
    int identifier = 0;
    Context context;
    String url = "";
ImageView imageView;
    //get constuctor
    public TextoImage(Context context, int identifier, String url) {
         this.context = context;
        this.URL_String = url;
        this.identifier=identifier;

    }
    public TextoImage(Context context, int identifier, String url, ImageView imageView) {
        this.context = context;
        this.URL_String = url;
        this.identifier=identifier;
        this.imageView=imageView;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            URL_String = URL_String.replace(" ", "%20");
            url = new URL(URL_String);
            if (identifier == 0) {

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(25000);
                urlConnection.setConnectTimeout(25000);
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    server_response = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient", server_response);
                }
            } else if (identifier == 1) {
                try {
                    InputStream in = new URL(URL_String).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        }

        return server_response;
    }

    @Override
    protected void onPostExecute(String result) {

        if (identifier == 0)

            delegate.dataLoaded(result);
        else if (identifier == 1)
            imageView.setImageBitmap(mIcon11);

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        Log.v("ProgressFromAsync", values[0] + "");

    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}

