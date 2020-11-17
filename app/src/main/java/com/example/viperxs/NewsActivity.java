package com.example.viperxs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.example.viperxs.ViewDetailItemActivity.REQUEST_CODE_COMMENT;

public class NewsActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ListView mainListView;
    Button buttonCreateItemLW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        //принимаем объект из предыдущей активити
        try {
            Bundle arg = getIntent().getExtras();
            final User user;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            if (arg != null) {
                user = arg.getParcelable(User.class.getSimpleName());
                builder.setMessage(user.login + "\n" + user.email).setTitle("Добро пожаловать!").create().show();
            } else {
                /*User user = new User;
                user.setLogin("adminLogin");
                user.setEmail("adminEmail");
                user.setPhone("8976650756");
                user.setPass("1111");*/
                builder.setMessage("Ваши данные:\n"+"Данные отсутствуют").setTitle("Вы читер и вошли без авторизации").create().show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_gallery, R.id.nav_timer, R.id.nav_time_manager, R.id.nav_plan, R.id.nav_settings, R.id.nav_out)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    private void onCreateLisView() {
        mainListView = (ListView) findViewById(R.id.ListViewNews);
//        mainListView.addHeaderView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickActionMenu(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                startActivity(new Intent(this, ViewDetailItemActivity.class));
                break;
            case R.id.action_develop:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/ilya_bobrow")));
                break;
            case R.id.action_calculate:
                startActivity(new Intent(this, CalculateActivity.class));
                break;
            case R.id.action_regisr:
                startActivity(new Intent(this, RegistrActivity.class));
                break;
            case R.id.action_settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("В разработке").setTitle("Упс").create().show();
                break;
            case R.id.action_product_calc:
                startActivity(new Intent(this, ProductCalcActivity.class));
                break;
            default:
                break;
        }
    }

    public void onClickCreateNews(View view) {
        startActivity(new Intent(this, ViewDetailItemActivity.class));
    }
    public void onClickForum(View view) {
        startActivity(new Intent(this, ProductCalcActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_COMMENT) {
                String h1 = data.getStringExtra("h1");
                String p = data.getStringExtra("p");
                Toast.makeText(this, "Данные получены", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Без изменений", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {

        } catch (Exception e) {
            Toast.makeText(this, "error: code 3", Toast.LENGTH_LONG).show();
        }
    }
}