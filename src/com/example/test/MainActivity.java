package com.example.test;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	Thread getLotteryThread;
	TextView titleText;
	TextView ansTable;
	String titleString;
	int runtimeSize;
	Boolean threadShouldContinue;
	ProgressDialog mypDialog;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        //Button1 點擊動作
        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		Intent myIntent = new Intent(MainActivity.this, BigLottery.class);
        		startActivity(myIntent);
        	}
        });
        
        //Button2 點擊動作
        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		Intent myIntent = new Intent(MainActivity.this, SuperLottery.class);
        		startActivity(myIntent);
        	}
        });

        //Button3 點擊動作
        Button btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		Intent myIntent = new Intent(MainActivity.this, DailyCash.class);
        		startActivity(myIntent);
        	}
        });
        
      //Button4 點擊動作
        Button btn4 = (Button)findViewById(R.id.button4);
        btn4.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		Intent myIntent = new Intent(MainActivity.this, Calculator.class);
        		startActivity(myIntent);
        	}
        });
    }
	
	//設定選單建立
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
    	menu.add(0, 0, 0, "捲動至頂");
    	menu.add(0, 1, 1, "捲動至底");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //設定選單動作
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //依據itemId來判斷使用者點選哪一個item
    	ScrollView scrollTable = (ScrollView) findViewById(R.id.scrollView1);
    	switch(item.getItemId()) {
            case 0:
				scrollTable.fullScroll(0);
                break;
            case 1:
            	scrollTable.fullScroll(ScrollView.FOCUS_DOWN);
                //finish();
                break;
            default:
        }
    	AlertDialog.Builder infoMsg = new AlertDialog.Builder(MainActivity.this);
    	switch(item.getOrder()) {
    		case 100:
    			infoMsg.setTitle("關於");
    			infoMsg.setMessage("版本：2.0\n作者：吳家漢\n功能：\n\t1.提供大樂透與威力彩亂數結果產生功能\n\t2.改善運算效果與功能\n\t3.增設可隨時中斷運算\n\t4.更改介面使介面感到更友善");
    			infoMsg.setCancelable(false);
    			infoMsg.setPositiveButton("確定", null);
    			infoMsg.show();
    			break;
    		case 200:
    			infoMsg.setTitle("關閉 App");
    			infoMsg.setMessage("您確定要關閉應用程式嗎?");
    			infoMsg.setCancelable(false);
    			infoMsg.setPositiveButton("確定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
		    			finish();
					}
				});
    			infoMsg.setNegativeButton("取消", null);
    			infoMsg.show();
    			break;
    		default:
    	}
    	
        return super.onOptionsItemSelected(item);
    }
}
