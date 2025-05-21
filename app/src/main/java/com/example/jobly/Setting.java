package com.example.jobly;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Setting extends AppCompatActivity {

    TextView tvLanguage, tvPrivacy, tvAboutApp, tvDelete, tvLogout;
    Switch switchDarkMode, switchNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale(); // يجب أن تكون قبل super
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // ربط العناصر
        tvLanguage = findViewById(R.id.tv_language);
        tvPrivacy = findViewById(R.id.tv_privacy_policy);
        tvAboutApp = findViewById(R.id.tv_about_app);
        tvDelete = findViewById(R.id.tv_delete_account);
        tvLogout = findViewById(R.id.tv_logout);
        switchDarkMode = findViewById(R.id.switch_dark_mode);
        switchNotifications = findViewById(R.id.switch_notifications);

        // عند الضغط على اللغة
        tvLanguage.setOnClickListener(v -> showLanguageDialog());

        // الانتقال إلى صفحة سياسة الخصوصية
        tvPrivacy.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this, PrivacyPolicyActivity.class);
            startActivity(intent);
        });

        // عند الضغط على حذف الحساب
        tvDelete.setOnClickListener(v -> showDeleteAccountDialog());
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String lang = prefs.getString("lang", "ar");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    // دايلوج تغيير اللغة
    private void showLanguageDialog() {
        final String[] languageNames = {
                "العربية", "English", "Français", "Español", "Deutsch", "Türkçe",
                "Русский", "中文", "日本語", "한국어", "हिन्दी", "Italiano"
        };
        final String[] languageCodes = {
                "ar", "en", "fr", "es", "de", "tr",
                "ru", "zh", "ja", "ko", "hi", "it"
        };

        SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String savedLang = prefs.getString("lang", "ar");
        final int[] selectedIndex = {0};
        for (int i = 0; i < languageCodes.length; i++) {
            if (languageCodes[i].equals(savedLang)) {
                selectedIndex[0] = i;
                break;
            }
        }

        new android.app.AlertDialog.Builder(this)
                .setTitle("Select language")
                .setSingleChoiceItems(languageNames, selectedIndex[0], (dialog, which) -> selectedIndex[0] = which)
                .setPositiveButton("OK", (dialog, which) -> {
                    String selectedLangCode = languageCodes[selectedIndex[0]];
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("lang", selectedLangCode);
                    editor.apply();

                    loadLocale(); // إعادة تعيين اللغة
                    recreate(); // إعادة تحميل الواجهة
                })
                .setNegativeButton("cancell", null)
                .show();
    }

    // إنشاء دايلوج حذف الحساب
    private void showDeleteAccountDialog() {
        // إنشاء دايلوج مخصص
        final Dialog deleteDialog = new Dialog(this);
        deleteDialog.setContentView(R.layout.layout_dialog_delete_account);  // قم بربط التصميم الذي أنشأته للدايلوج

        // ربط الأزرار داخل الدايلوج
        Button btnConfirm = deleteDialog.findViewById(R.id.btn_confirm);
        Button btnCancel = deleteDialog.findViewById(R.id.btn_cancel);

        // عند الضغط على "نعم، احذف الحساب"
        btnConfirm.setOnClickListener(v -> {
            // تنفيذ منطق حذف الحساب هنا
            deleteAccount();
            deleteDialog.dismiss(); // إغلاق الدايلوج
        });

        // عند الضغط على "إلغاء"
        btnCancel.setOnClickListener(v -> deleteDialog.dismiss()); // إغلاق الدايلوج

        // عرض الدايلوج
        deleteDialog.show();
    }

    // منطق حذف الحساب
    private void deleteAccount() {
        // حذف البيانات المخزنة في SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();  // حذف جميع البيانات المخزنة
        editor.apply();

        // عرض رسالة تأكيد
        Toast.makeText(this, "The account has been successfully deleted.", Toast.LENGTH_SHORT).show();

        // الخروج من التطبيق
        Intent intent = new Intent(Setting.this, MainActivity.class);  // أو يمكنك توجيه المستخدم لواجهة الدخول
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // مسح جميع الأنشطة السابقة
        startActivity(intent);
        finishAffinity();  // إنهاء التطبيق والخروج منه
    }

}
