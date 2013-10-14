package org.sacredheart.commutebuddy;

import org.sacredheart.commutebuddy.MyResultReceiver.Receiver;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements Receiver {
	
	public MyResultReceiver mReceiver;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	System.out.println("hello world");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReceiver = new MyResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        
        Intent i = new Intent(this, CBTrainDownloader.class);
        i.putExtra("receiverTag", mReceiver);
        startService(i);
    }
    
    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
    	String xmlString = resultData.getString("xmlString");
    	
    	Serializer ser = new Persister();
    	
    	try {
			Service service = ser.read(Service.class, xmlString);
			
			String status = service.getMnObject().get(8).getStatus();
			
			System.out.println(status);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	    	
    }    
}
