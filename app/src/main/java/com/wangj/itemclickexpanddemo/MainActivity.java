package com.wangj.itemclickexpanddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnOne = (Button) findViewById(R.id.btn_oneExpand);
		btnOne.setOnClickListener(this);
		Button btnMulti = (Button) findViewById(R.id.btn_MultiExpand);
		btnMulti.setOnClickListener(this);
		Button btnTable = (Button) findViewById(R.id.btn_tableExpand);
		btnTable.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_oneExpand:
			intent.setClass(this, OneExpandActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_MultiExpand:
			intent.setClass(this, MultiExpandActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_tableExpand:
			intent.setClass(this, TableExpandActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
