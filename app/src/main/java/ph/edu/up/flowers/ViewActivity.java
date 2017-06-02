package ph.edu.up.flowers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        String username = intent.getStringExtra(FlowerActivity.USERNAME);
        String text = intent.getStringExtra(FlowerActivity.TEXT);

        Bundle extras = getIntent().getExtras();
        Bitmap bitmap = (Bitmap) extras.getParcelable("imageBundle");

        TextView userView = (TextView) findViewById(R.id.username);
        userView.setText(username);

        TextView userText = (TextView) findViewById(R.id.text);
        userText.setText(text);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
    }
}
