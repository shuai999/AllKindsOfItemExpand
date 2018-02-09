package com.wangj.itemclickexpanddemo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 点击item展开隐藏部分,再次点击收起 可展开多条 Item
 * 
 * @author WangJ
 * @date 2016.02.01
 */
public class MultiExpandAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<HashMap<String, String>> list;
	private boolean[] showControl; // 用一个布尔数组记录list中每个item是否要展开

	public MultiExpandAdapter(Context context,
			ArrayList<HashMap<String, String>> list) {
		super();
		this.context = context;
		this.list = list;
		showControl = new boolean[list.size()]; // 构造器中初始化布尔数组
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_2,
					parent, false);
			holder = new ViewHolder();
			//需要显示的部分
			holder.showArea = (LinearLayout) convertView
					.findViewById(R.id.layout_showArea);
			//HTC M8
			holder.tvPhoneType = (TextView) convertView
					.findViewById(R.id.tv_phoneType);
			//9
			holder.tvDiscount = (TextView) convertView
					.findViewById(R.id.tv_discount);
			//2000
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);

			//隐藏部分的控件

			//需要隐藏的布局
			holder.hideArea = (RelativeLayout) convertView
					.findViewById(R.id.layout_hideArea);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time); //活动截止时间
			holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);  //888
			holder.btnBuy = (Button) convertView.findViewById(R.id.btn_buy); //立即抢购


			holder.iv_down = (ImageView) convertView
					.findViewById(R.id.iv_down);
			holder.iv_up = (ImageView) convertView
					.findViewById(R.id.iv_up);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final HashMap<String, String> item = list.get(position);

		// 注意：我们在此给响应点击事件的区域（我的例子里是 showArea 的线性布局）添加Tag，
		// 为了记录点击的 position，我们正好用position 设置 Tag
		holder.showArea.setTag(position);

		holder.tvPhoneType.setText(item.get("phoneType"));
		holder.tvDiscount.setText(item.get("discount"));
		holder.tvPrice.setText(item.get("price"));
		holder.tvTime.setText(item.get("time"));
		holder.tvNum.setText(item.get("num"));

		// list依次加载每个item，加载的同时查看showControl控制数组中对应位置的true/false
		// true显示隐藏部分
		// false不显示
		if (showControl[position]) {
			holder.hideArea.setVisibility(View.VISIBLE);
			holder.iv_up.setVisibility(View.VISIBLE);
			holder.iv_down.setVisibility(View.GONE);

		} else {
			holder.hideArea.setVisibility(View.GONE);
			holder.iv_up.setVisibility(View.GONE);
			holder.iv_down.setVisibility(View.VISIBLE);
		}

		holder.showArea.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// 根据点击位置改变控制数组中对应位置的布尔值
				int tag = (Integer) view.getTag();
				// 如果已经是true则改为false，反过来同理（即点击展开，再次点击收起）
				if (showControl[tag]) {
					showControl[tag] = false;
				} else {
					showControl[tag] = true;
				}
				//通知adapter数据改变需要重新加载
				notifyDataSetChanged(); //必须要有一步
			}
		});

		// 对于 Item 中子控件的监听（区别于整个Item 比如Button、ImageView、CheckBox等等子控件）都是在适配器类中添加，
		// 而整个Item的监听 (即就是给ListView的整个item设置点击事件) 则是在Activity中给ListView添加setOnItemClickListener实现的
		holder.btnBuy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(context, "快快下单！剩余" + item.get("num") + "台",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		return convertView;
	}

	private static class ViewHolder {
		private LinearLayout showArea; //固定显示的区域

		private TextView tvPhoneType;
		private TextView tvDiscount;
		private TextView tvPrice;
		private TextView tvTime;
		private TextView tvNum;
		private Button btnBuy;
		private ImageView iv_down ;
		private ImageView iv_up ;

		private RelativeLayout hideArea; //可隐藏的区域
	}
}
