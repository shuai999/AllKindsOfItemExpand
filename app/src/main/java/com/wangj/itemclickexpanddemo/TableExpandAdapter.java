package com.wangj.itemclickexpanddemo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 点击item展开隐藏部分,再次点击收起
 * 只可展开一条记录
 * 
 * @author WangJ
 */
public class TableExpandAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<HashMap<String, String>> list;
	private int currentItem = -1; //用于记录点击的 Item 的 position

	public TableExpandAdapter(Context context,
			ArrayList<HashMap<String, String>> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_1, parent, false);
			holder = new ViewHolder();
			holder.table = (TableLayout) convertView.findViewById(R.id.table);
			holder.tvRepayCycle = (TextView) convertView
					.findViewById(R.id.tv_repayCycle);
			holder.tvTotal = (TextView) convertView
					.findViewById(R.id.tv_total);
			holder.tvRepayDate = (TextView) convertView
					.findViewById(R.id.tv_repayDate);
			holder.tvNotRepayPrincipal = (TextView) convertView
					.findViewById(R.id.tv_notRepayPrincipal);
			holder.tvNotRepayInterest = (TextView) convertView
					.findViewById(R.id.tv_notRepayInterest);
			holder.imgMore = (ImageView) convertView.findViewById(R.id.img_more);
			
			//** 把要隐藏的控件"装起来"——开始 **
			holder.splitLine1 = convertView.findViewById(R.id.splitLine1);
			holder.rowRepayDate = (TableRow) convertView
					.findViewById(R.id.rowRepayDate);
			holder.splitLine2 = convertView.findViewById(R.id.splitLine2);
			holder.rowNotRepayPrincipal = (TableRow) convertView
					.findViewById(R.id.rowPrinciple);
			holder.splitLine3 = convertView.findViewById(R.id.splitLine3);
			holder.rowNotRepayInterest = (TableRow) convertView
					.findViewById(R.id.rowInterest);
			holder.hideViews.add(holder.splitLine1);
			holder.hideViews.add(holder.rowRepayDate);
			holder.hideViews.add(holder.splitLine2);
			holder.hideViews.add(holder.rowNotRepayPrincipal);
			holder.hideViews.add(holder.splitLine3);
			holder.hideViews.add(holder.rowNotRepayInterest);
			//** 把要隐藏的控件"装起来"——结束 **
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		HashMap<String, String> item = list.get(position);
		
		// 注意：我们在此给响应点击事件的区域（我的例子里是 table 布局）添加Tag，为了记录点击的 position，我们正好用 position 设置 Tag
		holder.table.setTag(position);
		
		holder.tvRepayCycle.setText(item.get("data1"));
		holder.tvTotal.setText(item.get("data2"));
		holder.tvRepayDate.setText(item.get("data3"));
		holder.tvNotRepayPrincipal.setText(item.get("data4"));
		holder.tvNotRepayInterest.setText(item.get("data5"));

		//根据 currentItem 记录的点击位置设置"对应Item"的可见性
		if (currentItem == position) {
			setViewsVisibility(holder.hideViews, true);
			holder.imgMore.setVisibility(View.GONE); //item展开时让箭头不可见
		} else {
			setViewsVisibility(holder.hideViews, false);
			holder.imgMore.setVisibility(View.VISIBLE); //item收起时让箭头可见
		}

		holder.table.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				//用 currentItem 记录点击位置
				int tag = (Integer) view.getTag();
				if (tag == currentItem) {
					currentItem = -1;
				} else {
					currentItem = tag;
				}
				//通知adapter数据改变需要重新加载
				notifyDataSetChanged();
			}
		});
		return convertView;
	}

	/**
	 * 一次性设置一系列控件的可见性
	 * 
	 * @param views
	 *            ArrayList<View>类型，要设置可见性的控件封装
	 * @param visivility
	 *            boolean类型，true表示可见，false表示不可见
	 */
	private void setViewsVisibility(ArrayList<View> views, boolean visivility) {
		for (View view : views) {
			view.setVisibility(visivility ? View.VISIBLE : View.GONE);
		}
	}

	private static class ViewHolder {
		private TableLayout table;

		private TextView tvRepayCycle;
		private TextView tvTotal;
		private TextView tvRepayDate;
		private TextView tvNotRepayPrincipal;
		private TextView tvNotRepayInterest;

		//** 需要隐藏控件——开始 **
		private View splitLine1;
		private TableRow rowRepayDate;
		private View splitLine2;
		private TableRow rowNotRepayPrincipal;
		private View splitLine3;
		private TableRow rowNotRepayInterest;
		//** 需要隐藏控件——结束 **
		private ArrayList<View> hideViews = new ArrayList<View>(); //用来封装隐藏的控件，使便于管理
		
		private ImageView imgMore; //向下展开的箭头
	}
}
