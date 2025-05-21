package com.example.jobly;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName, emailText, phoneText, addressText;
    private Button editProfileButton;

    private String name = "Ahmed Rashed";
    private String email = "ar1245@example.com";
    private String phone = "+972589553656";
    private String address = "1234 Elm Street, Springfield, IL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profileImage);
        profileName = findViewById(R.id.profileName);
        emailText = findViewById(R.id.emailText);
        phoneText = findViewById(R.id.phoneText);
        addressText = findViewById(R.id.addressText);
        editProfileButton = findViewById(R.id.editProfileButton);

        setProfileData();

        editProfileButton.setOnClickListener(view -> showEditDialog());
    }

    private void setProfileData() {
        profileName.setText(name);
        emailText.setText(email);
        phoneText.setText(phone);
        addressText.setText(address);
    }

    private void showEditDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_edit_profile, null);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        EditText nameInput = dialogView.findViewById(R.id.editName);
        EditText emailInput = dialogView.findViewById(R.id.editEmail);
        EditText phoneInput = dialogView.findViewById(R.id.editPhone);
        EditText addressInput = dialogView.findViewById(R.id.editAddress);

        Button updateButton = dialogView.findViewById(R.id.updateButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        ImageView backArrow = dialogView.findViewById(R.id.backArrow);

        nameInput.setText(name);
        emailInput.setText(email);
        phoneInput.setText(phone);
        addressInput.setText(address);

        updateButton.setOnClickListener(v -> {
            String newName = nameInput.getText().toString().trim();
            String newEmail = emailInput.getText().toString().trim();
            String newPhone = phoneInput.getText().toString().trim();
            String newAddress = addressInput.getText().toString().trim();

            if (newName.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            name = newName;
            email = newEmail;
            phone = newPhone;
            address = newAddress;

            setProfileData();

            try {
                JSONObject data = new JSONObject();
                data.put("name", name);
                data.put("email", email);
                data.put("phone", phone);
                data.put("address", address);

                sendProfileToServer(data);
            } catch (Exception e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        });

        cancelButton.setOnClickListener(v -> dialog.dismiss());
        backArrow.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void sendProfileToServer(JSONObject json) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your-server.com") // غيّر هذا إلى عنوان السيرفر الحقيقي
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        ProfileData profile = new ProfileData(
                json.optString("name"),
                json.optString("email"),
                json.optString("phone"),
                json.optString("address")
        );

        Call<Void> call = api.updateProfile(profile);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "تم تحديث البيانات في السيرفر", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "فشل التحديث: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "خطأ في الاتصال: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
