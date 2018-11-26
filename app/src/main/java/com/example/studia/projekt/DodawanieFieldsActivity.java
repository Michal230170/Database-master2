package com.example.studia.projekt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.FieldRecord;
import com.example.studia.projekt.db.model.FieldRecords;
import com.example.studia.projekt.utils.AppExecutors;

public class DodawanieFieldsActivity extends AppCompatActivity {

    private AppDatabase database;

    private TextView editTextNumber;
    private TextView editTextArea;
    private TextView editTextName;


    private EditText getEditTextNumber;
    private EditText getEditTextArea;
    private EditText getEditTextName;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie_fields);
     

        database = AppDatabase.getInstance(getApplicationContext());
        userId = getIntent().getIntExtra("userId", 0);

        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber.setOnClickListener(new View.OnClickListener() { //budowanie dialogu
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DodawanieFieldsActivity.this);

                builder.setTitle("Wpisz nr działki");

                // nowy edit text do dialog
                getEditTextNumber = new EditText(DodawanieFieldsActivity.this);
                getEditTextNumber.setInputType(InputType.TYPE_CLASS_NUMBER); //klawiatura numeryczna
                builder.setView(getEditTextNumber);

                /// przycisk pozytywny

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String txt = getEditTextArea.getText().toString();

                        editTextNumber.setText(getEditTextNumber.getText().toString());

                    }
                });

                /// przycisk negatywny

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        editTextName = findViewById(R.id.editTextName);
        editTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DodawanieFieldsActivity.this);
                builder.setTitle("Wpisz swoją nazwę");

                //edit text do alertDialog

                getEditTextName = new EditText(DodawanieFieldsActivity.this);
                builder.setView(getEditTextName);

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String txt = getEditTextArea.getText().toString();

                        editTextName.setText(getEditTextName.getText().toString());
                    }
                });

                /// przycisk negatywny

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        editTextArea = findViewById(R.id.editTextArea);
        editTextArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DodawanieFieldsActivity.this);
                builder.setTitle("Wpisz powierzchnię działki [ha]");

                getEditTextArea = new EditText(DodawanieFieldsActivity.this);
                builder.setView(getEditTextArea);

                builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextArea.setText(getEditTextArea.getText().toString());
                    }
                });

                builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    public void addFieldRecord(View view) {

        if (editTextNumber.getText().toString().equals("") || editTextArea.getText().toString().equals("")
                || editTextName.getText().toString().equals("") ){

            Toast.makeText(this, "Uzupełnij pola",
                    Toast.LENGTH_SHORT).show();

        } else {

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {

                    FieldRecords fieldRecord = new FieldRecords(
                            editTextNumber.getText().toString(),
                            editTextArea.getText().toString(),
                            editTextName.getText().toString(),
                            userId);

                    database.fieldRecordDao().insert(fieldRecord);
                }
            });

            Toast.makeText(this, "Zapisano",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(DodawanieFieldsActivity.this, MainFieldsActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);

        }
    }
}

