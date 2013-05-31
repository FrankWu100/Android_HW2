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
		// TODO �۰ʲ��ͪ���k Stub
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
        			titleString = addSpace("��", Integer.toString(runtimeSize).length()-2)
        						+ "\t\t�j�ֳz  ���X";
        			
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
	//�]�w���إ�
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
    	menu.add(0, 0, 0, "���ʦܳ�");
    	menu.add(0, 1, 1, "���ʦܩ�");
    	//menu.add(0, 2, 2, "����B��");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //�]�w���ʧ@
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //�̾�itemId�ӧP�_�ϥΪ��I����@��item
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
    			infoMsg.setTitle("����");
    			infoMsg.setMessage("�����G2.0\n�@�̡G�d�a�~\n�\��G\n\t1.���Ѥj�ֳz�P�¤O�m�üƵ��G���ͥ\��\n\t2.�ﵽ�B��ĪG�P�\��\n\t3.�W�]�i�H�ɤ��_�B��\n\t4.��虜���Ϥ����P���͵�");
    			infoMsg.setCancelable(false);
    			infoMsg.setPositiveButton("�T�w", null);
    			infoMsg.show();
    			break;
    		case 200:
    			infoMsg.setTitle("���� App");
    			infoMsg.setMessage("�z�T�w�n�������ε{����?");
    			infoMsg.setCancelable(false);
    			infoMsg.setPositiveButton("�T�w", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
		    			finish();
					}
				});
    			infoMsg.setNegativeButton("����", null);
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
			mypDialog.setTitle("�p�⤤");
			mypDialog.setMessage("Loading...");
			mypDialog.setMax(runtimeSize);
			mypDialog.setIndeterminate(false);
			mypDialog.setCancelable(false);
			mypDialog.setButton(DialogInterface.BUTTON_POSITIVE, "���_", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO �۰ʲ��ͪ���k Stub
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
		
	    //�ˬd�O�_����
		protected boolean check(int[] array, int size, int num)
		{
			for (int i = 0; i < size; ++i)
			{
				if (array[i] == num)
					return false;
			}
			return true;
		}
		
		//�ƦC���[�έץ�

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
		
		
		//���ln�դ��Ʀr
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
		
		
		//��X�ñƧǲ��ͤ��üư}�C
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

		
		//����ǩһݤ�Handler1, ��X���G
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
		
		//����ǩһݤ�Handler2, ��X����i�׻P���D
		@SuppressLint("HandlerLeak")
		private Handler setTitleText = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				int temp = msg.getData().getInt(null);
				if (temp == -99)
				{
					String oldText = titleText.getText().toString();
					titleText.setText(oldText + "\t\t�ϥΪ̤��_�F�B��");
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
		
		//�s�j�ֳzFunction �����
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
		                tableString = tableString + "------���_------";
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
