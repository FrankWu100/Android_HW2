package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Calculator extends Activity {
	TextView tempText;
	TextView ansText;
	String titleString;
	Boolean haveDat = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動產生的方法 Stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculator);

		tempText = (TextView) findViewById(R.calculatorId.textView1);
		ansText = (TextView) findViewById(R.calculatorId.textView2);
		tempText.setText("");
		ansText.setText("");
		
		Button btn1 = (Button)findViewById(R.calculatorId.button1);
        btn1.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"1");
        	}
        });
        
        Button btn2 = (Button)findViewById(R.calculatorId.button2);
        btn2.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"2");
        	}
        });
        
        Button btn3 = (Button)findViewById(R.calculatorId.button3);
        btn3.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"3");	
        	}
        });
        
        Button btn4 = (Button)findViewById(R.calculatorId.button4);
        btn4.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"4");
        	}
        });
        
        Button btn5 = (Button)findViewById(R.calculatorId.button5);
        btn5.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"5");
        	}
        });
        
        Button btn6 = (Button)findViewById(R.calculatorId.button6);
        btn6.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"6");
        	}
        });
        
        Button btn7 = (Button)findViewById(R.calculatorId.button7);
        btn7.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"7");
        	}
        });
        
        Button btn8 = (Button)findViewById(R.calculatorId.button8);
        btn8.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"8");
        	}
        });
        
        Button btn9 = (Button)findViewById(R.calculatorId.button9);
        btn9.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"9");
        	}
        });
        
        Button btn10 = (Button)findViewById(R.calculatorId.button10);
        btn10.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText(tempText.getText().toString() +"0");
        	}
        });
        
        Button btn11 = (Button)findViewById(R.calculatorId.button11);
        btn11.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		if (!haveDat)
        		{
        			haveDat = true;
        			tempText.setText(tempText.getText().toString() +".");
        		}
        	}
        });
        
        Button btn12 = (Button)findViewById(R.calculatorId.button12);
        btn12.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        				
        		
        	}
        });
        
        Button btn13 = (Button)findViewById(R.calculatorId.button13);
        btn13.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        				
        		
        	}
        });
        
        Button btn14 = (Button)findViewById(R.calculatorId.button14);
        btn14.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        				
        		
        	}
        });

        Button btn15 = (Button)findViewById(R.calculatorId.button15);
        btn15.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        				
        		
        	}
        });

        Button btn16 = (Button)findViewById(R.calculatorId.button16);
        btn16.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		String temp = tempText.getText().toString();
        		if (temp.length() > 0)
        			tempText.setText(temp.substring(0, temp.length()-1));
        	}
        });

        Button btn17 = (Button)findViewById(R.calculatorId.button17);
        btn17.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        		tempText.setText("");
        		ansText.setText("");
    			haveDat = false;
        	}
        });

        Button btn18 = (Button)findViewById(R.calculatorId.button18);
        btn18.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v) {
        				
        		
        	}
        });
        
        
	}
	//設定選單建立
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//    	menu.add(0, 0, 0, "捲動至頂");
//    	menu.add(0, 1, 1, "捲動至底");
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
    	AlertDialog.Builder infoMsg = new AlertDialog.Builder(Calculator.this);
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
