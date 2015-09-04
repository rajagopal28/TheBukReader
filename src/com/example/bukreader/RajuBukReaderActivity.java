package com.example.bukreader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RajuBukReaderActivity extends Activity {
	private String myFileName="sample.buk";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		final TextView tv=new TextView(this);	
        Button read=(Button)findViewById(R.id.button1);
        read.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					FileOutputStream out=openFileOutput(myFileName,Context.MODE_PRIVATE);
					DataOutputStream dout=new DataOutputStream(out);
					dout.writeUTF("Hello World");
					dout.writeUTF("I'm Fine");
					dout.writeUTF("Bye Bye!!!");
					dout.close();
					out.close();
					FileInputStream fin=openFileInput(myFileName);
					DataInputStream din=new DataInputStream(fin);
					int noOfLine=getLineCount(myFileName);
					int i=0;
					String line=new String();
					while(i<noOfLine)
					{
						line+=din.readLine();
						i++;
					}
					tv.setText(line);
					setContentView(tv);
					din.close();
					fin.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					try {
						FileOutputStream out=openFileOutput(myFileName,Context.MODE_PRIVATE);
						DataOutputStream dout=new DataOutputStream(out);
						dout.writeChars("Hello World");
						dout.close();
						out.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						try {
							FileOutputStream out=openFileOutput(myFileName,Context.MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE|MODE_APPEND);
							DataOutputStream dout=new DataOutputStream(out);
							dout.writeUTF("Hello World");
							dout.close();
							out.close();
						} catch (FileNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException ex21) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						e1.printStackTrace();
					} catch (IOException e12) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
    }
    public int getLineCount(String fName)
    {
    	int count=0;
    	try {
			FileInputStream fsin=openFileInput(fName);
			byte[] buffer = new byte[1024];
			while(fsin.read(buffer)>0)
			{
				count++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return count;
    }
}