package com.cai.vegetables.activity.light;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.User;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 选择收货人和发货人
 * 
 * @author yang
 *
 */
public class SelectLinkManAct extends BaseActivity {
	@ViewInject(R.id.selectlinkman_lv)
	private ListView selectlinkman_lv;
	@ViewInject(R.id.slectlinkman_tv1)
	private TextView slectlinkman_tv1;// 收发货人文本
	@ViewInject(R.id.slectlinkman_tv)
	private TextView slectlinkman_tv;// 历史收发货人文本
	@ViewInject(R.id.slectlinkman_tv2)
	private TextView slectlinkman_tv2;// 联系电话
	@ViewInject(R.id.slectlinkman_rl)
	private RelativeLayout slectlinkman_rl;// 通讯录

	private int flag;
	private MyAdapter adapter;
	private static final int CONTACT = 1;
	@Override
	public void setLayout() {
		setContentView(R.layout.selectlinkman);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		initInfo();
		adapter = new MyAdapter();
		selectlinkman_lv.setAdapter(adapter);
	}

	private void initInfo() {
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				flag = bundle.getInt("flag");
			}
		}
		switch (flag) {
		case 1:
			setTopTitle("发货人");
			slectlinkman_tv1.setHint("发货人姓名");
			slectlinkman_tv.setText("历史发货人");
			break;
		case 2:
			setTopTitle("收货人");
			slectlinkman_tv1.setHint("收货人姓名");
			slectlinkman_tv.setText("历史收货人");
			break;

		default:
			break;
		}

	}
	
	@OnClick(R.id.slectlinkman_rl)
	public void todo(View v){
		switch (v.getId()) {
		case R.id.slectlinkman_rl:
			Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(intent, CONTACT);
			break;

		default:
			break;
		}
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(SelectLinkManAct.this, R.layout.selectlinkman_item, null);
			return convertView;
		}

	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CONTACT:
			if (resultCode == RESULT_OK && data != null) {
				Uri contactData = data.getData();
				Cursor cursor = managedQuery(contactData, null, null, null, null);
				cursor.moveToFirst();
				slectlinkman_tv2.setText(getContact(cursor).mobileNo.replace(" ", ""));
				slectlinkman_tv1.setText(getContact(cursor).userName);
			}
			break;

		default:
			break;
		}
	}
	
	/**
	 * 获取联系人信息
	 * 
	 * @param cursor
	 * @return
	 */
	private User getContact(Cursor cursor) {
		User user = new User();
		int nameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
		user.userName = cursor.getString(nameColumn);
		int phoneColumn = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
		int phoneNum = cursor.getInt(phoneColumn);
		String result = "";
		if (phoneNum > 0) {
			// 获得联系人的ID号
			int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
			String contactId = cursor.getString(idColumn);
			// 获得联系人电话的cursor
			Cursor phone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
					null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
			if (phone.moveToFirst()) {
				for (; !phone.isAfterLast(); phone.moveToNext()) {
					int index = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
					int typeindex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
					int phone_type = phone.getInt(typeindex);
					String phoneNumber = phone.getString(index);
					result = phoneNumber;
					switch (phone_type) {
					case 2:
						result = phoneNumber;
						break;

					default:
						break;
					}
				}
				if (!phone.isClosed()) {
					phone.close();
				}
			}
		}
		user.mobileNo = result;
		return user;
	}
}
