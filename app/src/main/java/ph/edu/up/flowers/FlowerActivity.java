package ph.edu.up.flowers;

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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FlowerActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SQLiteHelper sqLiteHelper = new SQLiteHelper(FlowerActivity.this);
    }

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
    public final static String IMAGE= "ph.edu.up.floweractivity.IMAGE";

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

    /*public void onAddRecord() {
        Intent intent = new Intent(FlowerActivity.this, TableManipulationActivity.class);
        intent.putExtra(Constants.DML_TYPE, Constants.INSERT);
        startActivityForResult(intent, Constants.ADD_RECORD);
    }*/
}
