package ph.edu.up.flowers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.provider.SyncStateContract.Constants;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerActivity extends AppCompatActivity  {
    private ListView listView;
    private ListFlowerAdapter flowerAdapter;
    private List<Flower> flowerArrayList;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        sqLiteHelper = new SQLiteHelper(this);
        setContentView(R.layout.activity_flower);
        listView =(ListView) findViewById(R.id.layout_main);
        /*Flower flower = new Flower();
        flower.setName("Vanilla");
        flower.setEase("Hard");
        flower.setInstructions("Regulate temp and humidity regularly.");
        sqLiteHelper.updateRecords(flower);*/
        List<Flower> flowerList = sqLiteHelper.getAllRecords();
        Log.i("SENDER", String.valueOf(flowerList.get(0).getId()));
        Log.i("SENDER", String.valueOf(flowerList.get(0).getName()));
        Log.i("SENDER", String.valueOf(flowerList.get(0).getEase()));
        Log.i("SENDER", String.valueOf(flowerList.get(0).getInstructions()));
        /*Log.i("SENDER", String.valueOf(flowerList.get(1).getId()));
        Log.i("SENDER", String.valueOf(flowerList.get(1).getName()));
        Log.i("SENDER", String.valueOf(flowerList.get(1).getEase()));
        Log.i("SENDER", String.valueOf(flowerList.get(1).getInstructions()));*/

//        File database = getApplicationContext().getDatabasePath(sqLiteHelper.DATABASE_NAME);
//        if(false == database.exists()) {
//            sqLiteHelper.getReadableDatabase();
//            if (copyDB(this)) {
//                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }

//        flowerArrayList = sqLiteHelper.getAllRecords();
//        flowerAdapter = new ListFlowerAdapter(this, flowerArrayList);
//        listView.setAdapter(flowerAdapter);
    }

//    private boolean copyDB(Context context) {
//        try {
//            InputStream inputStream = context.getAssets().open(sqLiteHelper.DATABASE_NAME);
//            String outFileName = SQLiteHelper.DB_LOCATION + SQLiteHelper.DATABASE_NAME;
//            OutputStream outputStream = new FileOutputStream(outFileName);
//            byte[]buff = new byte[1024];
//            int length = 0;
//            while ((length = inputStream.read(buff)) > 0) {
//                outputStream.write(buff, 0, length);
//            }
//            outputStream.flush();
//            outputStream.close();
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flower, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) { //action_edit
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public final static String USERNAME = "ph.edu.up.floweractivity.USERNAME";
    public final static String TEXT= "ph.edu.up.floweractivity.TEXT";

    public void viewPhotos(View view) {

        int layout_id = view.getId();
        LinearLayout parent_layout =  (LinearLayout) findViewById(layout_id);

        ImageView imageView = (ImageView) parent_layout.getChildAt(0);

        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Bundle bundle = new Bundle();
        bundle.putParcelable("imageBundle", bitmap);

        LinearLayout layout = (LinearLayout) parent_layout.getChildAt(1);

        TextView textView = (TextView) layout.getChildAt(0);
        String username = textView.getText().toString();

        TextView textView1 = (TextView) layout.getChildAt(1);
        String text= textView1.getText().toString();

        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra(USERNAME, username);
        intent.putExtra(TEXT, text);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void saveChanges(View view) {
        Flower flower = new Flower();
        LinearLayout parent_layout = (LinearLayout) findViewById(R.id.layoutAddUpdate);

        TextView textName = (TextView) parent_layout.getChildAt(0);
        String flowerName = textName.getText().toString();

        TextView textEase = (TextView) parent_layout.getChildAt(1);
        String flowerEase = textEase.getText().toString();

        TextView textInst = (TextView) parent_layout.getChildAt(2);
        String flowerInst = textInst.getText().toString();

        flower.setName(flowerName);
        flower.setEase(flowerEase);
        flower.setInstructions(flowerInst);

        sqLiteHelper.insertRecords(flower);
    }
}
