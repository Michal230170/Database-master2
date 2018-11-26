package com.example.studia.projekt;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.User;
import com.example.studia.projekt.utils.AppExecutors;

public class LoginActivity extends AppCompatActivity {

  private AppDatabase database;
  private EditText loginEditText;
  private EditText passwordEditText;
  private MutableLiveData<User> mutableLiveData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mutableLiveData = new MutableLiveData<>();

    database = AppDatabase.getInstance(getApplicationContext());

    loginEditText = findViewById(R.id.login);
    passwordEditText = findViewById(R.id.password);
    Button buttonLogin = findViewById(R.id.button);
    Button buttonRegister = findViewById(R.id.button2);

    buttonLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        final String login = loginEditText.getText().toString();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
          @Override
          public void run() {
            User user = database.userDao().findUserByLogin(login); // szuka w bazie po loginie uzytkownika
            mutableLiveData.postValue(user);
          }
        });
      }
    });

    mutableLiveData.observe(this, new Observer<User>() {
      @Override
      public void onChanged(@Nullable User user) {

        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        String userLogin = (user != null) ? user.getLogin() : "";
        String userPassword = (user != null) ? user.getPassword() : "";

        if ((!login.equals("") && !password.equals("")) && userLogin.equals(login) && userPassword
            .equals(password)) {

          Toast.makeText(getApplicationContext(), "Zalogowano pomyślnie", Toast.LENGTH_SHORT)
              .show();

          Intent intent = new Intent(LoginActivity.this, MainFieldsActivity.class);
          intent.putExtra("userId", user.getId()); // przesyla doo mainfieldsactivity id uzytkownika
          startActivity(intent);

        } else {

          Toast.makeText(getApplicationContext(), "Niepoprawne dane", Toast.LENGTH_SHORT)
              .show();
        }
      }
    });

    buttonRegister.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegistrActivity.class);
        startActivity(intent);
      }
    });
  }
}
