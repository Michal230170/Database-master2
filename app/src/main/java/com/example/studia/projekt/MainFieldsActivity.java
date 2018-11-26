package com.example.studia.projekt;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.FieldRecord;
import com.example.studia.projekt.db.model.FieldRecords;

import java.util.ArrayList;
import java.util.List;

public class MainFieldsActivity extends AppCompatActivity {

    public ListView listView;            //przewijana lista to wszysko
    private ActionBarDrawerToggle mToggle;
    private SimpleArrayAdapterFields adapter;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fields);
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppDatabase database = AppDatabase.getInstance(getApplicationContext());

        listView = findViewById(R.id.fieldRecordsList); //field records list to nazwa list view

        userId = getIntent().getIntExtra("userId", 0); // odczytujemy id uzytkownika

        database.fieldRecordDao().findFieldRecordsByUserId(userId).observe(this,
                new Observer<List<FieldRecords>>() {
                    @Override
                    public void onChanged(@Nullable List<FieldRecords> fieldRecords) {
                        adapter = new SimpleArrayAdapterFields(getApplicationContext(),
                                (ArrayList<FieldRecords>) fieldRecords);
                        listView.setAdapter(adapter);
                    }
                }
        );

        listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final FieldRecords item = (FieldRecords) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainFieldsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle b = new Bundle(); // odczytywanie id klikniętego rekordu ?
                // musimy przesłać recordFieldsId
                b.putInt("fieldId", item.getRecordFieldsId());
                b.putInt("userId", item.getUserId());
                intent.putExtras(b);
                finish();
                try {
                    startActivity(intent);
                } catch (Exception ex) {
                    String str = "aaa";
                }
            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.DodawanieButton:

                intent = new Intent(MainFieldsActivity.this, DodawanieFieldsActivity.class);
                intent.putExtra("userId", userId);
                break;
        }

        startActivity(intent);

    }

    public void onclick(MenuItem item) {
        Intent intent = new Intent(MainFieldsActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onclickInfo(MenuItem item) {
        Intent intent = new Intent(MainFieldsActivity.this, InfoActivity.class);
        startActivity(intent);
    }

}
