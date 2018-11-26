package com.example.studia.projekt;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.User;
import com.example.studia.projekt.utils.AppExecutors;

public class RegistrActivity extends AppCompatActivity {

  private AppDatabase database;
  private EditText loginEditText;
  private EditText passEditText;
  private EditText secondPassEditText;
  private MutableLiveData<String> mutableLiveData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registr);

    mutableLiveData = new MutableLiveData<>();

    database = AppDatabase.getInstance(getApplicationContext());

    loginEditText = findViewById(R.id.login);
    passEditText = findViewById(R.id.pass);
    secondPassEditText = findViewById(R.id.cpass);
    Button button = findViewById(R.id.button_register);

    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        final String login = loginEditText.getText().toString();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
          @Override
          public void run() {
            User user = database.userDao().findUserByLogin(login);
            String databaseLogin = (user != null) ? user.getLogin() : "";
            mutableLiveData.postValue(databaseLogin);
          }
        });
      }
    });

    mutableLiveData.observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String databaseLogin) {

        String login = loginEditText.getText().toString();
        String pass = passEditText.getText().toString();
        String secondPass = secondPassEditText.getText().toString();

        if (login.equals("") || pass.equals("") || secondPass.equals("")) {

          Toast.makeText(getApplicationContext(), "Nie można zostawić pustych pól!",
              Toast.LENGTH_SHORT).show();

        } else if (databaseLogin.equals(login)) {

          Toast.makeText(getApplicationContext(), "Podany użytkownik już istnieje!",
              Toast.LENGTH_SHORT).show();

        } else if (!pass.equals(secondPass)) {

          Toast.makeText(getApplicationContext(), "Podane hasła muszą być takie same!",
              Toast.LENGTH_SHORT)
              .show();

        } else {

          final User newUser = new User(login, pass);

          AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
              database.userDao().insert(newUser);
            }
          });

          Toast.makeText(getApplicationContext(), "Zakończono logowanie",
              Toast.LENGTH_SHORT)
              .show();

          finish();
        }
      }
    });

  }

}


