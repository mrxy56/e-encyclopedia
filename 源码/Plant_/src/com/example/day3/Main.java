package com.example.day3;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.day3.DataActivity;;

public class Main extends Activity {
	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// ÅÄÕÕ
	static MySQLiteHelper myHelper;
	private Button button0, button1;
	private ImageView imageView = null;
	private TextView text;
	private Bitmap mImageBitmap;
	private boolean flag = false;
	private DataActivity Data;

	static public double [][]data;
	
//	static public String []tdata={"ÒøĞÓ","èÁèË","ÌÒ×Ó","×ÏÒ¶Àî","»Æ¸ğÊ÷","ÁøÊ÷","Àæ»¨","ÓñÀ¼","Öñ×Ó","Ê¯Áñ","·ãÒ¶","Ğ¡Ò¶éÅ","É£Ò¶","¶ÅÓ¢","èÙ×Ó»¨"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button0 = (Button) this.findViewById(R.id.doPhoto);
		button1 = (Button) this.findViewById(R.id.upPhoto);
		text = (TextView) this.findViewById(R.id.name);
		imageView = (ImageView) this.findViewById(R.id.showPhoto);
		
		Data=new DataActivity();
		
		for(int i=0;i<15;i++)
		{
			String pdata=Data.queryData(myHelper, i);
			String []qdata=pdata.split(" ");
			
			for(int k=2;k<9;k++)
			{
				data[i][k-2]=Double.parseDouble(qdata[k]);
			}
		}
		
		text.setText(data[0][0]+"      "+data[0][1]+"      "+data[0][2]);
		button0.setOnClickListener(new View.OnClickListener() {

			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				// takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
				// Uri.fromFile(new
				// File(Environment.getExternalStorageDirectory(),"temp.jpg")));
				startActivityForResult(takePictureIntent, PHOTOHRAPH);
				
				
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == NONE)
			return;
		if (requestCode == PHOTOHRAPH) {
			// File picture = new File(Environment.getExternalStorageDirectory()
			// + "/temp.jpg");
			// startPhotoZoom(Uri.fromFile(picture));
			Bundle extras = data.getExtras();
			mImageBitmap = (Bitmap) extras.get("data");
			imageView.setImageBitmap(mImageBitmap);
			flag = true;
		}

	}

	@SuppressWarnings("null")
	public void identf(View view) {
		if (flag) {
			// mImageBitmap = BitmapFactory.decodeStream(getResources()
			// .openRawResource(R.drawable.pic10));
			int w = mImageBitmap.getWidth(), h = mImageBitmap.getHeight();
			// imageView.setImageBitmap(mImageBitmap);
			int[] pix = new int[w * h];

			mImageBitmap.getPixels(pix, 0, w, 0, 0, w, h);

			double[] resultInt = LibImgFun.ImgFun(pix, w, h);
			double temp;
			double tmin1 = 9999999, tmin2 = 9999999, tmin3 = 9999999;
			int tres1 = 0, tres2 = 0, tres3 = 0;
			if (resultInt != null) {

				for (int i = 0; i < 15; i++) {
					temp = 0;

					for (int j = 0; j < 7; j++) {
						temp += Math.abs(resultInt[j] - data[i][j])
								/ data[i][j];
					}
					if (temp < tmin1) {
						tmin1 = temp;

						tres1 = i;
					}

				}

					for (int i = 0; i < 15; i++) {
						temp = 0;
						for (int j = 0; j < 7; j++) {
							temp += Math.abs(resultInt[j] - data[i][j])
									/ data[i][j];
						}
						if (temp < tmin2) {
							if (i == tres1)
								continue;
							tmin2 = temp;
							tres2 = i;
						}
					}


				for (int i = 0; i < 15; i++) {
					temp = 0;
					for (int j = 0; j < 7; j++) {
						temp += Math.abs(resultInt[j] - data[i][j])
								/ data[i][j];
					}
					if (temp < tmin3) {
						if (i == tres1 || i == tres2)
							continue;
						tmin3 = temp;
						tres3 = i;
					}
				}



				Intent intent = new Intent();
				intent.setClass(this, ShowActivity.class);
				intent.putExtra("data", tres1 + "&" + tres2 + "&" + tres3);
				startActivity(intent);
			
			} else {
				Toast.makeText(Main.this, "Ç×£¬Ã»ÓĞÊı¾İ£¡", 0).show();
			}

		} else {
			Toast.makeText(Main.this, "Ç×£¬»¹Ã»ÅÄÕÕÅ¶£¡", 0).show();
		}
	}
	
	public void qiut(View view)
	{
		finish();
	}

}
