package com.example.day3;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends Activity {
	private String []tdata;
	private int num1,num2,num3;
	private DataActivity Data;
	static MySQLiteHelper myHelper;
	private ImageView img1,img2,img3;
	private TextView name1,name2,name3;
	private String data;
	private Bitmap mImageBitmap1,mImageBitmap2,mImageBitmap3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		Data=new DataActivity();
		img1=(ImageView) findViewById(R.id.photo1);
		img2=(ImageView) findViewById(R.id.photo2);
		img3=(ImageView) findViewById(R.id.photo3);
		
		name1=(TextView) findViewById(R.id.name1);
		name2=(TextView) findViewById(R.id.name2);
		name3=(TextView) findViewById(R.id.name3);
		
//		text1=(TextView) findViewById(R.id.text1);
//		text2=(TextView) findViewById(R.id.text2);
		String []qdata;
		
		for(int i=0;i<15;i++)
		{
			data=Data.queryData(myHelper, i);
			qdata=data.split("#");
			tdata[i]=qdata[0];
		}
		
		
		 Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent  
	        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据  
	        String str=bundle.getString("data");//getString()返回指定key的值  
	        String []s=str.split("&");
	        name1.setText(s[0]);
	        name2.setText(s[1]);
	        name3.setText(s[2]);
	        num1= Integer.parseInt(s[0]);
	        num2= Integer.parseInt(s[1]);
	        num3=Integer.parseInt(s[2]);
	        switch(num1)
	        {
	        case 0:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic1));
	        name1.setText(tdata[0]);
	        break;
	        case 1:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic2));
	        name1.setText(tdata[1]);
	        break;
	        case 2:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic3));
	        name1.setText(tdata[2]);
	        break;
	        case 3:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic4));
	        name1.setText(tdata[3]);
	        break;
	        case 4:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic5));
	        name1.setText(tdata[4]);
	        break;
	        case 5:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic6));
	        name1.setText(tdata[5]);
	        break;
	        case 6:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic7));
	        name1.setText(tdata[6]);
	        break;
	        case 7:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic8));
	        name1.setText(tdata[7]);
	        break;
	        case 8:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic9));
	        name1.setText(tdata[8]);
	        break;
	        case 9:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic10));
	        name1.setText(tdata[9]);
	        break;
	        case 10:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic11));
	        name1.setText(tdata[10]);
	        break;
	        case 11:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic12));
	        name1.setText(tdata[11]);
	        break;
	        case 12:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic13));
	        name1.setText(tdata[12]);
	        break;
	        case 13:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic14));
	        name1.setText(tdata[13]);
	        break;
	        case 14:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic15));
	        name1.setText(tdata[14]);
	        break;
	        case 15:mImageBitmap1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic16));
	        name1.setText(tdata[15]);
	        break;
	        }
	        
	        
	        switch(num2)
	        {
	        case 0:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic1));
	        name2.setText(tdata[0]);
	        break;
	        case 1:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic2));
	        name2.setText(tdata[1]);
	        break;
	        case 2:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic3));
	        name2.setText(tdata[2]);
	        break;
	        case 3:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic4));
	        name2.setText(tdata[3]);
	        break;
	        case 4:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic5));
	        name2.setText(tdata[4]);
	        break;
	        case 5:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic6));
	        name2.setText(tdata[5]);
	        break;
	        case 6:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic7));
	        name2.setText(tdata[6]);
	        break;
	        case 7:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic8));
	        name2.setText(tdata[7]);
	        break;
	        case 8:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic9));
	        name2.setText(tdata[8]);
	        break;
	        case 9:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic10));
	        name2.setText(tdata[9]);
	        break;
	        case 10:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic11));
	        name2.setText(tdata[10]);
	        break;
	        case 11:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic12));
	        name2.setText(tdata[11]);
	        break;
	        case 12:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic13));
	        name2.setText(tdata[12]);
	        break;
	        case 13:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic14));
	        name2.setText(tdata[13]);
	        break;
	        case 14:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic15));
	        name2.setText(tdata[14]);
	        break;
	        case 15:mImageBitmap2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic16));
	        name2.setText(tdata[15]);
	        break;
	        }
	        
	        switch(num3)
	        {
	        case 0:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic1));
	        name3.setText(tdata[0]);
	        break;
	        case 1:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic2));
	        name3.setText(tdata[1]);
	        break;
	        case 2:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic3));
	        name3.setText(tdata[2]);
	        break;
	        case 3:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic4));
	        name3.setText(tdata[3]);
	        break;
	        case 4:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic5));
	        name3.setText(tdata[4]);
	        break;
	        case 5:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic6));
	        name3.setText(tdata[5]);
	        break;
	        case 6:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic7));
	        name3.setText(tdata[6]);
	        break;
	        case 7:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic8));
	        name3.setText(tdata[7]);
	        break;
	        case 8:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic9));
	        name3.setText(tdata[8]);
	        break;
	        case 9:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic10));
	        name3.setText(tdata[9]);
	        break;
	        case 10:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic11));
	        name3.setText(tdata[10]);
	        break;
	        case 11:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic12));
	        name3.setText(tdata[11]);
	        break;
	        case 12:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic13));
	        name3.setText(tdata[12]);
	        break;
	        case 13:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic14));
	        name3.setText(tdata[13]);
	        break;
	        case 14:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic15));
	        name3.setText(tdata[14]);
	        break;
	        case 15:mImageBitmap3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.pic16));
	        name3.setText(tdata[15]);
	        break;
	        }
	        
	        img1.setImageBitmap(mImageBitmap1);
	        img2.setImageBitmap(mImageBitmap2);
	        img3.setImageBitmap(mImageBitmap3);
	}


	
	public void back(View view)
	{
	
		Intent intent = new Intent();
		intent.setClass(this, Main.class);

		startActivity(intent);
		finish();
	}
	
	public void bt1click(View view)
	{
		Intent intent = new Intent();
		intent.setClass(this, PlantActivity.class);
		intent.putExtra("tdata",""+ num1);
		startActivity(intent);
	}
	
	public void bt2click(View view)
	{
		Intent intent = new Intent();
		intent.setClass(this, PlantActivity.class);
		intent.putExtra("tdata", ""+num2);
		startActivity(intent);
	}
	
	public void bt3click(View view)
	{
		Intent intent = new Intent();
		intent.setClass(this, PlantActivity.class);
		intent.putExtra("tdata", ""+num3);
		startActivity(intent);
	}
}
