package com.example.jobly;



import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar topAppBar;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط الواجهات
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        topAppBar = findViewById(R.id.topAppBar);
        fabAdd = findViewById(R.id.fabAdd);

        // فتح القائمة الجانبية
        topAppBar.setNavigationOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        // التعامل مع عناصر القائمة الجانبية
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.menu_profile:
                    startActivity(new Intent(this, ProfileActivity.class));
                    return true;
                case R.id.menu_settings:
                    startActivity(new Intent(this, Setting.class));
                    return true;
                case R.id.menu_about:
                    startActivity(new Intent(this, AboutMe.class));
                    return true;
                case R.id.menu_logout:
                    showLogoutDialog(); // استدعاء دايلوج تأكيد الخروج
                    return true;
            }
            return false;
        });

        // Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(this, HomeActivity.class));
                    return true;
                case R.id.nav_applications:
                    startActivity(new Intent(this, Applications.class));
                    return true;
                case R.id.nav_notifications:
                    startActivity(new Intent(this, NotificationsActivity.class));
                    return true;
                case R.id.nav_myJob:
                    startActivity(new Intent(this, My_Jobs.class));
                    return true;
            }
            return false;
        });

        // زر إضافة وظيفة
        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddJobActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // دالة عرض دايلوج تأكيد تسجيل الخروج
    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout_diallog, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnConfirm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            dialog.dismiss();
        });

        dialog.show();
    }
}
