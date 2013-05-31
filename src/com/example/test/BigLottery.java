package com.example.test;

import java.text.DecimalFormat;
import java.util.Arrays;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class BigLottery extends Activity {
	Thread getLotteryThread;
	TextView titleText;
	TextView ansTable;
	String titleString;
	int runtimeSize;
	Boolean threadShouldContinue;
	ProgressDialog mypDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動產生的方法 Stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biglottery);
		
        titleText = (TextView) findViewById(R.id.textView3);
        ansTable = (TextView) findViewById(R.id.textView1);
        titleText.setText("");
        ansTable.setText("");
		
		Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		EditText txt1 = (EditText)findViewById(R.id.editText1);
        		if (txt1.getText().length() > 0)
        		{
        			setControlEnabled(false);
        			ansTable.setText("");
        			
        			runtimeSize = Integer.parseInt(txt1.getText().toString());
        			titleString = addSpace("組", Integer.toString(runtimeSize).length()-2)
        						+ "\t\t大樂透  號碼";
        			
        			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        			imm.hideSoftInputFromWindow(txt1.getWindowToken(), 0);   
        			
        			showProgressDialog();
        			
        			getLotteryThread = new getBigLottery();
        			getLotteryThread.start();
        	        
            		//getBigLottery(runtimeSize);
        			
        			ScrollView scrollTable = (ScrollView) findViewById(R.id.scrollView1);
        			scrollTable.fullScroll(0);        			
        		}
        	}
        });
	}
	//設定選單建立
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
    	menu.add(0, 0, 0, "捲動至頂");
    	menu.add(0, 1, 1, "捲動至底");
    	//menu.add(0, 2, 2, "停止運算");
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
            case 2:
    			threadShouldContinue = false;
    			setControlEnabled(true);
                break;
            default:
        }
    	AlertDialog.Builder infoMsg = new AlertDialog.Builder(BigLottery.this);
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
	 void setControlEnabled(Boolean bool){
	    	
	    	bool = true;
	    	Button btn1 = (Button)findViewById(R.id.button1);
	    	Button btn3 = (Button)findViewById(R.id.button3);
	    	
	    	findViewById(R.id.editText1).setEnabled(bool);
	    	if (bool)
	    	{
	        	btn1.setVisibility(View.VISIBLE);
	        	btn3.setVisibility(View.INVISIBLE);
	    	}
	    	else
	    	{
	        	btn1.setVisibility(View.INVISIBLE);
	        	btn3.setVisibility(View.VISIBLE);
	    	}
	    }

	    void showProgressDialog()
	    {
	    	mypDialog = new ProgressDialog(BigLottery.this);
			mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mypDialog.setTitle("計算中");
			mypDialog.setMessage("Loading...");
			mypDialog.setMax(runtimeSize);
			mypDialog.setIndeterminate(false);
			mypDialog.setCancelable(false);
			mypDialog.setButton(DialogInterface.BUTTON_POSITIVE, "中斷", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自動產生的方法 Stub
	    			threadShouldContinue = false;
	    			setControlEnabled(true);
				}
			});
			mypDialog.show();
			mypDialog.onStart();  
			mypDialog.setProgress(0);
	    }
	 int[] getRandomAns(int size, int range)
	    {
	    	int[] ans = new int[size];
	    	for (int count = 0; count < size;)
			{
				int num = (int)(Math.random()*range+1);
				if (check(ans, count, num))
				{
					ans[count] = num;
					++count;
				}
			}
			return ans;
	    }    
		
	    //檢查是否重複
		protected boolean check(int[] array, int size, int num)
		{
			for (int i = 0; i < size; ++i)
			{
				if (array[i] == num)
					return false;
			}
			return true;
		}
		
		//排列美觀用修正

		String addSpace(String str, int size)
	    {
	    	String newStr = new String();
			for (int i = 0; i < size ; ++i)
			{
				newStr = newStr + " ";
			}
			newStr = newStr + str;
			for (int i = 0; i < size ; ++i)
			{
				newStr = newStr + " ";
			}
			return newStr;
	    }
		
		
		//取締n組之數字
		protected String getAnsNo(int no, int size)
		{
			String merger = new String();
			int start = Integer.toString(no).length();
			int end = Integer.toString(size).length();
			if (end == 1)
				end = 2;
			
			int spaceSize = end - start; 
			
			merger = addSpace(Integer.toString(no), spaceSize);
			return merger;
		}
		
		
		//整合並排序產生之亂數陣列
		protected String getMergeAns(int[] ans)
		{
			String merger = new String();
			Arrays.sort(ans);
			for (int i=0; i<ans.length; ++i)
			{
				if (Integer.toString(ans[i]).length() == 1)
					merger = merger + addSpace(Integer.toString(ans[i]), 1);
				else
					merger = merger + Integer.toString(ans[i]);
				merger = merger + "\t";
			}
			return merger;
		}

		
		//執行序所需之Handler1, 輸出結果
		@SuppressLint("HandlerLeak")
		private Handler setAnsTable = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				ansTable.setText(msg.getData().getString(null));
				
				String oldText = titleText.getText().toString();
				titleText.setText(oldText + "\n" + titleString);
				
				mypDialog.hide();
				setControlEnabled(true);
			}
		};
		
		//執行序所需之Handler2, 輸出執行進度與標題
		@SuppressLint("HandlerLeak")
		private Handler setTitleText = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				int temp = msg.getData().getInt(null);
				if (temp == -99)
				{
					String oldText = titleText.getText().toString();
					titleText.setText(oldText + "\t\t使用者中斷了運算");
				}
				else
				{
					String temp2 = new DecimalFormat("#.#").format((double)temp/runtimeSize*100);
					titleText.setText(Integer.toString(temp) + "/" + runtimeSize + " (" + temp2 + "%)");
					//int fixProgress = temp - mypDialog.getProgress();
	        		//mypDialog.incrementProgressBy(fixProgress);
					mypDialog.incrementProgressBy(1);
				}
			}
		};
		
		//新大樂透Function 執行序
		class getBigLottery extends Thread {
			public getBigLottery () {
				threadShouldContinue = true;
			}
			public void run() {
				super.run();
				try {
					String tableString = new String();
					int[] ans = new int[6];

					Bundle countBundle = new Bundle();
	                Message msg = new Message();
	                
					for (int times = 0; times < runtimeSize && threadShouldContinue; ++times)
					{	
	                    Thread.sleep(10);
						ans = getRandomAns(6, 49);
						String merger = new String(getAnsNo(times+1, runtimeSize) + "\t\t" + getMergeAns(ans));
						tableString = tableString + merger;
						if (times + 1 != runtimeSize)
							tableString = tableString + "\n";
						
						//times

						countBundle = new Bundle();
		                msg = new Message();
		                countBundle.putInt(null, times+1);
		                msg.setData(countBundle);
		                setTitleText.sendMessage(msg);
					}
					if (!threadShouldContinue)
					{
						countBundle = new Bundle();
						msg = new Message();
		                countBundle.putInt(null, -99);
		                msg.setData(countBundle);
		                setTitleText.sendMessage(msg);
		                tableString = tableString + "------中斷------";
					}
					countBundle = new Bundle();
	                msg = new Message();
	                countBundle.putString(null, tableString);
	                msg.setData(countBundle);
	                setAnsTable.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

}
