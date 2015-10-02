package com.example.day3;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlantActivity extends Activity {
	private String []tdata;
	private String intro[];
	private int num;
	private TextView name1,address,tv_abstract;
	private ImageView img;
	private Bitmap mImageBitmap1;
	static MySQLiteHelper myHelper;
	private String data;
	private DataActivity Data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant);
		Data=new DataActivity();
		name1=(TextView) findViewById(R.id.name);
		address=(TextView) findViewById(R.id.address);
		tv_abstract=(TextView) findViewById(R.id.intro);
		
		img=(ImageView) findViewById(R.id.photo);
		
		String qdata[];
		for(int i=0;i<15;i++)
		{
			data=Data.queryData(myHelper, i);
			qdata=data.split("#");
			tdata[i]=qdata[0];
			intro[i]=qdata[1];
		}
		
		 Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent  
	        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据  
	        String str=bundle.getString("tdata");//getString()返回指定key的值  


	        num= Integer.parseInt(str);


	        address.setText("");
		
	        
	        
	        switch(num)
	        {
	        case 0:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic1));
	        name1.setText(tdata[0]);
	        tv_abstract.setText(intro[0]);
	        break;
	        case 1:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic2));
	        name1.setText(tdata[1]);
	        tv_abstract.setText(intro[1]);
	        break;
	        case 2:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic3));
	        name1.setText(tdata[2]);
	        tv_abstract.setText(intro[2]);
	        break;
	        case 3:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic4));
	        name1.setText(tdata[3]);
	        tv_abstract.setText(intro[3]);
	        break;
	        case 4:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic5));
	        name1.setText(tdata[4]);
	        tv_abstract.setText(intro[4]);
	        break;
	        
	        case 5:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic6));
	        name1.setText(tdata[5]);
	        tv_abstract.setText(intro[5]);
	        break;
	        case 6:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic7));
	        name1.setText(tdata[6]);
	        tv_abstract.setText(intro[6]);
	        break;
	        case 7:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic8));
	        name1.setText(tdata[7]);
	        tv_abstract.setText(intro[7]);
	        break;
	        case 8:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic9));
	        name1.setText(tdata[8]);
	        tv_abstract.setText(intro[8]);
	        break;
	        case 9:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic10));
	        name1.setText(tdata[9]);
	        tv_abstract.setText(intro[9]);
	        break;
	        
	        case 10:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic11));
	        name1.setText(tdata[10]);
	        tv_abstract.setText(intro[10]);
	        break;
	        case 11:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic12));
	        name1.setText(tdata[11]);
	        tv_abstract.setText(intro[11]);
	        break;
	        case 12:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic13));
	        name1.setText(tdata[12]);
	        tv_abstract.setText(intro[12]);
	        break;
	        case 13:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic14));
	        name1.setText(tdata[13]);
	        tv_abstract.setText(intro[13]);
	        break;
	        case 14:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic15));
	        name1.setText(tdata[14]);
	        tv_abstract.setText(intro[14]);
	        break;
	        case 15:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic16));
	        name1.setText(tdata[15]);
	        tv_abstract.setText(intro[15]);
	        break;
	        }
	        img.setImageBitmap(mImageBitmap1);
	}


	
	public void bc(View view)
	{
//		Intent intent = new Intent();
//		intent.setClass(this, PlantActivity.class);
//
//		startActivity(intent);
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plant, menu);
		return true;
	}

}
