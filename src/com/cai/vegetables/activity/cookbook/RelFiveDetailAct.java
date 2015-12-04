package com.cai.vegetables.activity.cookbook;

import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.CookBook;
import com.cai.vegetables.entity.CookStep;
import com.cai.vegetables.entity.Food;
import com.cai.vegetables.widget.ToastCommom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.json.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * 第五步详情界面
 * 
 * @author yang
 *
 */
public class RelFiveDetailAct extends CookBase {
	@ViewInject(R.id.ed_choosefood)
	private EditText ed_choosefood;
	@ViewInject(R.id.ed_chooseuse)
	private EditText ed_chooseuse;
	
	//步骤描述
	@ViewInject(R.id.ed_stepdes)
	private EditText ed_stepdes;
	private Intent gochoose;

	//选择食材
	public final static int CHOOSEFOOD=0;
	//选择用具
	public final static int CHOOSEUSE=1;	
	
	private CookStep stepinfo;
	@Override
	public void setCookLayout() {
		setContentView(R.layout.relfivedetailact);
	}

	@Override
	public void initCook(Bundle savedInstanceState) {
		setTopTitle("步骤说明");
		stepinfo=(CookStep) getIntent().getSerializableExtra("COOKHISTORYSTEP");
		if(stepinfo!=null){
			if(stepinfo.stepdes!=null)
				ed_stepdes.setText(stepinfo.stepdes);
			if(stepinfo.stepfoodlist!=null)
				ed_choosefood.setText(stepinfo.stepfoodlist.toString());
			if(stepinfo.stepkitchenlist!=null)
				ed_chooseuse.setText(stepinfo.stepkitchenlist.toString());
		}
		else
		stepinfo=new CookStep();
	}

	@OnClick({ R.id.fivedetail_save, R.id.choose_food, R.id.choose_work,R.id.rl_stepphoto})
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.fivedetail_save:
			stepinfo.stepdes=ed_stepdes.getText().toString().trim();
			Intent reintent=getIntent();
			reintent.putExtra(COOKSTEPDES, stepinfo);
			setResult(Activity.RESULT_OK, reintent);
			ToastCommom.createToastConfig().ToastShow(RelFiveDetailAct.this, "保存成功");
			finish();
			break;
		case R.id.choose_food:
			gochoose=new Intent(this,ChooseFoodAct.class);
			startActivityForResult(gochoose, CHOOSEFOOD);
			break;
		case R.id.choose_work:
			gochoose=new Intent(this,ChooseWorkAct.class);
			startActivityForResult(gochoose, CHOOSEUSE);
			break;
		case R.id.rl_stepphoto:
			
			break;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		switch (arg0) {
		//选择食材
		case CHOOSEFOOD:
			if(arg1==Activity.RESULT_OK){
			stepinfo.stepfoodlist=(List<Food>) arg2.getSerializableExtra(COOKFOOD);
			ed_choosefood.setText(stepinfo.stepfoodlist.toString());
			}
			break;
		//选择用具
		case CHOOSEUSE:
			if(arg1==Activity.RESULT_OK){
			stepinfo.stepkitchenlist=(List<String>) arg2.getSerializableExtra(COOKUSE);
			ed_chooseuse.setText(stepinfo.stepkitchenlist.toString());
			}
			break;
			
		}
	}
	
}
