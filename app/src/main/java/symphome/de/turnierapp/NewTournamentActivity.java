package symphome.de.turnierapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewTournamentActivity extends AppCompatActivity {

    Button btTakePic, btGallary, btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tournament);

        btGallary = findViewById(R.id.btChooseGallery);
        btTakePic = findViewById(R.id.btTakePicture);
        btSave = findViewById(R.id.btSave);

        // setzt das Attribut uris
        getImageUris();

        btGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView pics = findViewById(R.id.lvPictures);
                ImageArrayAdapter adapter = new ImageArrayAdapter(
                        NewTournamentActivity.this,
                        R.layout.image_rowlayout,
                        getImageUris()
                );
                pics.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    101
            );
            btTakePic.setEnabled(false);
            btGallary.setEnabled(false);

        } else {
            btTakePic.setEnabled(true);
            btGallary.setEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 101 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            btTakePic.setEnabled(true);
            btGallary.setEnabled(true);
        }
    }

    private List<String[]> getImageUris() {
        ContentResolver cr = NewTournamentActivity.this.getContentResolver();
        String[] projection = {MediaStore.Images.Media._ID , MediaStore.Images.Media.DATA};
        Cursor cursor = cr.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null,
                null
        );

        List<String[]> pictures = new ArrayList<>();

        int c = 0;
        String[] s = new String[4];
        while (cursor.moveToNext()) {
            String col = cursor.getString(1);
            s[c] = col;
            c++;
            if(c % 4 == 0) {
                pictures.add(s);
                s = new String[4];
                c = 0;
            }
        }
        if(c > 0) {
            pictures.add(s);
        }
        return pictures;
    }
}
