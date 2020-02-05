package symphome.de.turnierapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TurnierDBAdapter turnierDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        turnierDBAdapter = new TurnierDBAdapter(this);
        turnierDBAdapter.open();

        TurnierArrayAdapter arrayAdapter = new TurnierArrayAdapter(
                this,
                R.layout.icon_text_rowlayout,
                getTurniere()
        );
        ((ListView)findViewById(R.id.lv_turniere)).setAdapter(arrayAdapter);

    }

    private List<Turnier> getTurniere() {
        List<Turnier> turniere = new ArrayList<>();

        Cursor cursor = turnierDBAdapter.query(
                new String[] {TurnierDBAdapter.KEY, TurnierDBAdapter.COLUMN_DESCRIPTION, TurnierDBAdapter.COLUMN_IMAGE_URI},
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            turniere.add(new Turnier(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }

        turniere.add(new Turnier("0", "sdfghjkl", "fghjklö"));


        return turniere;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_tournament:
                Intent intent = new Intent(this, NewTournamentActivity.class);
                this.startActivity(intent);
                this.finish();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
