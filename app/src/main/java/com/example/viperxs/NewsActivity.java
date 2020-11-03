package com.example.viperxs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

    public void onClickAction_regisr(MenuItem item) {
        Intent intent = new Intent(this, RegistrActivity.class);
        startActivity(intent);
    }

    public void onClickAction_Settings(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("В разработке").setTitle("Упс");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onClickAction_Help(MenuItem item) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void onClickAction_Develop(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/ilya_bobrow"));
        startActivity(intent);
    }
}