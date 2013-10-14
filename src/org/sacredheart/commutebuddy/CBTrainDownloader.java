package org.sacredheart.commutebuddy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class CBTrainDownloader extends IntentService {
	public CBTrainDownloader() {
		super("CBTrainDownloader");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		ResultReceiver rec = intent.getParcelableExtra("receiverTag");
		System.out.println("intent handled");
		
		 URL url;
	     HttpURLConnection conn = null;
	     InputStream is = null;
	     String xmlString = "";
	     
	     try {
	    	url = new URL("http://www.mta.info/status/serviceStatus.txt");
			conn = (HttpURLConnection) url.openConnection();
    
			conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
			
	        conn.setDoInput(true);
	        conn.connect();
	        
	      //  int response = conn.getResponseCode();
	      //  System.out.println("The response is: " + response);
	        is = conn.getInputStream();

	        // Convert the InputStream into a string
	        xmlString = readIt(is, 100000);
	        
	        if (is != null) {
	            is.close();
	        } 
	    
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		Bundle b = new Bundle();
		b.putString("xmlString", xmlString);
		rec.send(0, b);
	}
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");        
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
	
}