//package com.qgj.juan_05.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.Header;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.example.ytds_yiti.R;
//import com.example.ytds_yiti.activity.OrderDetailsActivity;
//import com.example.ytds_yiti.activity.PastorderActivity;
//import com.example.ytds_yiti.activity.PinglunActivity;
//import com.example.ytds_yiti.bean.Data;
//import com.example.ytds_yiti.bean.Order;
//import com.example.ytds_yiti.bean.RequstClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//
//public class PastorderAdapter extends BaseAdapter{
//	private ArrayList<Order> list;
//	LayoutInflater inflater;
//	Context mContext;
//	private Activity activity;
//	private static int order_type;
//	private int list_position;
//	public PastorderAdapter(List<Order> list,LayoutInflater inflater,Context mContext,Activity activity){
//		this.list=(ArrayList<Order>) list;
//		this.inflater=inflater;
//		this.mContext=mContext;
//		this.activity=activity;
//	}
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return list.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return list.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		ViewHolder holder;
//		list_position = position;
//		if (convertView == null) {
//			convertView = inflater.inflate(R.layout.pastorderlist_item, null);
//			holder = new ViewHolder();
//
//			holder.textView_orderdate = (TextView) convertView
//					.findViewById(R.id.pastorderlist_item_orderdate);
//			holder.textView_linkman = (TextView) convertView
//					.findViewById(R.id.pastorderlist_item_linkman);
//			holder.textView_phone = (TextView) convertView
//					.findViewById(R.id.pastorderlist_item_phone);
//			holder.textView_wateraddress = (TextView) convertView
//					.findViewById(R.id.pastorderlist_item_wateraddress);
//			holder.textView_money = (TextView) convertView
//					.findViewById(R.id.pastorderlist_item_money);
//			holder.imageView_pingjia=(ImageView) convertView.findViewById(R.id.pastorderlist_item_bt_pinglun);
//			holder.tempLinearLayout = (LinearLayout) convertView
//					.findViewById(R.id.pasttemplayout);
//
//			// TODO 这里做个循环，以字符串长度决定
//			for (int i = 0; i < list.get(position).getDeal_length(); i++) {
//
//				RelativeLayout charu = new RelativeLayout(mContext);
//				charu = inlistitem(i, position);
//				((LinearLayout) holder.tempLinearLayout).addView(charu, 1);
//			}
//
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//		order_type=0;
//		testLink(position);
//		holder.textView_orderdate.setText(list.get(position).getDeal_time());
//		holder.textView_money.setText("￥" + list.get(position).getPrice());
//		holder.textView_wateraddress.setText("送水地址："
//				+ list.get(position).getDeal_address());
//		holder.textView_linkman.setText(list.get(position).getDeal_consignee());
//		holder.textView_phone.setText(list.get(position).getDeal_phone());
//		holder.textView_phone.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
//		final Integer p = position;
//
//		if (order_type==1) {
//			holder.imageView_pingjia.setVisibility(View.VISIBLE);
//			holder.imageView_pingjia.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					Intent intent =new Intent(activity,PinglunActivity.class);
//					intent.putExtra("orderId", list.get(position).getDeal_id());
//					activity.startActivity(intent);
//				}
//			});
//		}else if (order_type==0) {
//			holder.imageView_pingjia.setVisibility(View.GONE);
//		}
//
//		holder.tempLinearLayout.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent detailIntent = new Intent(activity,
//						OrderDetailsActivity.class);
//				detailIntent.putExtra("orderId", list.get(p).getDeal_id());
//				activity.startActivity(detailIntent);
//			}
//		});
//
//		holder.textView_phone.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
//						Uri.parse("tel:" + list.get(p).getDeal_phone()));
//				phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				activity.startActivity(phoneIntent);
//			}
//		});
//
//		return convertView;
//	}
//	class ViewHolder{
//		public TextView textView_ordernumber,textView_brandname,textView_orderdate,textView_ordertype,
//		textView_standard,textView_money,textView_wateraddress,textView_linkman,textView_phone;
//		public ImageView imageView_pingjia;
//		public LinearLayout tempLinearLayout;
//	}
//
//	private RelativeLayout inlistitem(int id, int position) {
//		int textview1id = 1, imageView1id = 2, tv_pinpai1id = 3, tv_pinpai2id = 4;
//		RelativeLayout layout_root_relative = new RelativeLayout(mContext);
//		RelativeLayout.LayoutParams root_lp = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		root_lp.topMargin = 20;
//		root_lp.bottomMargin = 20;
//		layout_root_relative.setLayoutParams(root_lp);
//
//		TextView tv = new TextView(mContext);
//		RelativeLayout.LayoutParams LP_WW = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		LP_WW.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//		LP_WW.leftMargin = 10;
//		LP_WW.topMargin = 15;
//		// LP_WW.bottomMargin=10;
//		LP_WW.addRule(RelativeLayout.RIGHT_OF, imageView1id);
//		tv.setText(list.get(position).getStandard_volume()[id] + "L/桶");
//		tv.setTextSize(14);
//		tv.setLayoutParams(LP_WW);
//		layout_root_relative.addView(tv);
//
//		TextView tv_leixing = new TextView(mContext);
//		RelativeLayout.LayoutParams tv_leixing_lp = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		tv_leixing_lp.addRule(RelativeLayout.RIGHT_OF, tv_pinpai2id);
//		tv_leixing_lp.leftMargin = 10;
//		tv_leixing_lp.topMargin = 18;
//		tv_leixing.setId(imageView1id);
//		if (id % 2 == 0) {
//			tv_leixing.setBackgroundColor(Color.parseColor("#27a42f"));
//		} else {
//			tv_leixing.setBackgroundColor(Color.parseColor("#f76c8d"));
//		}
//		tv_leixing.setText(list.get(position).getType_name()[id]);// 這裡接口修改后需要改動
//		tv_leixing.setTextSize(10);
//		tv_leixing.setTextColor(Color.WHITE);
//		tv_leixing.setLayoutParams(tv_leixing_lp);
//		layout_root_relative.addView(tv_leixing);
//
//		TextView tv1 = new TextView(mContext);
//		RelativeLayout.LayoutParams tv1_lp = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		tv1_lp.topMargin = 15;
//		tv1_lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//		tv1_lp.addRule(RelativeLayout.RIGHT_OF, tv_pinpai1id);
//		tv1.setId(tv_pinpai2id);
//		tv1.setText(list.get(position).getBrand_name()[id]);// 這裡接口修改后需要改動
//		tv1.setTextSize(14);
//		tv1.setLayoutParams(tv1_lp);
//		layout_root_relative.addView(tv1);
//
//		TextView tv_pinpai = new TextView(mContext);
//		RelativeLayout.LayoutParams tv_pinpai_lp = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		tv_pinpai_lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//		tv_pinpai_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//		tv_pinpai_lp.topMargin = 15;
//		tv_pinpai.setId(tv_pinpai1id);
//		tv_pinpai.setText("品牌：");// 這裡接口修改后需要改動
//		tv_pinpai.setTextSize(14);
//		tv_pinpai.setLayoutParams(tv_pinpai_lp);
//		layout_root_relative.addView(tv_pinpai);
//
//		TextView tv_jiage = new TextView(mContext);
//		RelativeLayout.LayoutParams tv_jiage_lp = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		tv_jiage_lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//		tv_jiage_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//
//		tv_jiage_lp.topMargin = 15;
//		tv_jiage.setText("¥" + list.get(position).getItem_price()[id] + "X"
//				+ list.get(position).getDeal_number()[id]);// 這裡接口修改后需要改動
//		tv_jiage.setTextSize(14);
//		tv_jiage.setLayoutParams(tv_jiage_lp);
//		layout_root_relative.addView(tv_jiage);
//
//		return layout_root_relative;
//	}
//	private void testLink(int position) {
//		String url =Data.api+"/Api/Biz/remark";
//		RequestParams pm =new RequestParams();
//		pm.put("orderid", list.get(position).getDeal_id());
//		RequstClient.post(url, pm, new AsyncHttpResponseHandler(){
//			@Override
//			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//				// TODO Auto-generated method stub
//				String datasString=new String(arg2);
//				try {
//					JSONObject object =new JSONObject(datasString);
//					if (object.optString("flag").equals("error")) {
//						order_type=0;
//					}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					order_type=1;
//				}
//			}
//		});
//	}
//}
