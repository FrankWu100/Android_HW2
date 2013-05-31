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
		
        //Button1 �I���ʧ@
        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		Intent myIntent = new Intent(MainActivity.this, BigLottery.class);
        		startActivity(myIntent);
        	}
        });
        
        //Button2 �I���ʧ@
        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		Intent myIntent = new Intent(MainActivity.this, SuperLottery.class);
        		startActivity(myIntent);
        	}
        });

        //Button3 �I���ʧ@
        Button btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		Intent myIntent = new Intent(MainActivity.this, DailyCash.class);
        		startActivity(myIntent);
        	}
        });
        
      //Button4 �I���ʧ@
        Button btn4 = (Button)findViewById(R.id.button4);
        btn4.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		Intent myIntent = new Intent(MainActivity.this, Calculator.class);
        		startActivity(myIntent);
        	}
        });
    }
	
	//�]�w���إ�
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
    	menu.add(0, 0, 0, "���ʦܳ�");
    	menu.add(0, 1, 1, "���ʦܩ�");
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
            default:
        }
    	AlertDialog.Builder infoMsg = new AlertDialog.Builder(MainActivity.this);
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
}
