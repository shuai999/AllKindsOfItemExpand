package com.wangj.itemclickexpanddemo;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class TableExpandActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_expand);
		requestData();
	}
	
	private void requestData() {
		
		HashMap<String, String> accountInfo = new HashMap<String, String>();
		accountInfo.put("type", "数码产品");
		accountInfo.put("repayAccount", "622200******0000");
		
		ArrayList<HashMap<String, String>> datas = new ArrayList<HashMap<String,String>>();
		for(int i = 1; i <= 10; i++){
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("data1", i + "");
			item.put("data2", 510.50 + "");
			item.put("data3", "20160" + i + "21");
			item.put("data4", "500");
			item.put("data5", "10.00");
			datas.add(item);
		}

		showData(accountInfo);
		
		ListView lvRepayInfo = (ListView) findViewById(R.id.lv_repayInfoList);
		TableExpandAdapter adapter = new TableExpandAdapter(this, datas);
		lvRepayInfo.setAdapter(adapter);
	}

	private void showData(HashMap<String, String> maps) {
		TextView tvType = (TextView) findViewById(R.id.tv_type);
		TextView tvRepayAccount = (TextView) findViewById(R.id.tv_repayAccount);
		
		if(maps != null){
			tvType.setText(maps.get("type"));
			tvRepayAccount.setText(maps.get("repayAccount"));
		}
	}

}
