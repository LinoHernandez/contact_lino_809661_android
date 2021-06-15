package com.example.contact_lino_809661_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contact_lino_809661_android.model.Contact;
import com.example.contact_lino_809661_android.model.ContactViewModel;

import java.util.Arrays;

public class AddContactActivity extends AppCompatActivity{

    public static final String NAME_REPLY = "name_reply";
    public static final String LASTNAME_REPLY = "lastname_reply";
    public static final String EMAIL_REPLY = "email_reply";
    public static final String PNUMBER_REPLY = "phumber_reply";
    public static final String ADRESS_REPLY = "adress_reply";

    private EditText etName, etLastName, etEmail, etPNumber, etAdress;


    private boolean isEditing = false;
    private int contactId = 0;
    private Contact contactTobeUpdated;

    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication())
                .create(ContactViewModel.class);

        etName = findViewById(R.id.et_name);
        etLastName = findViewById(R.id.et_lastname);
        etEmail = findViewById(R.id.et_email);
        etPNumber = findViewById(R.id.et_pnumber);
        etAdress = findViewById(R.id.et_address);

        Button addUpdateButton = findViewById(R.id.btn_add_contact);

        addUpdateButton.setOnClickListener(v -> {
            addUpdateContact();
        });

        if (getIntent().hasExtra(MainActivity.CONTACT_ID)) {
            contactId = getIntent().getIntExtra(MainActivity.CONTACT_ID, 0);
            Log.d("TAG", "onCreate: " + contactId);

            contactViewModel.getContact(contactId).observe(this, contact -> {
                if (contact != null) {
                    etName.setText(contact.getName());
                    etLastName.setText(contact.getLast_name());
                    etEmail.setText(contact.getEmail());
                    etPNumber.setText(String.valueOf(contact.getPnumber()));
                    etAdress.setText(contact.getAddress());
                    contactTobeUpdated = contact;
                }
            });
            TextView label = findViewById(R.id.label);
            isEditing = true;
            label.setText(R.string.update_label);
            addUpdateButton.setText(R.string.update_contact_btn_text);
        }
    }

    private void addUpdateContact() {
        String name = etName.getText().toString().trim();
        String lastname = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String pnumber = etPNumber.getText().toString().trim();
        String adress = etAdress.getText().toString().trim();
        if (name.isEmpty()) {
            etName.setError("Name field cannot be empty");
            etName.requestFocus();
            return;
        }

        if (lastname.isEmpty()) {
            etLastName.setError("Last name field cannot be empty");
            etLastName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email field cannot be empty");
            etEmail.requestFocus();
            return;
        }

        if (pnumber.isEmpty()) {
            etPNumber.setError("Phone number field cannot be empty");
            etPNumber.requestFocus();
            return;
        }

        if (adress.isEmpty()) {
            etAdress.setError("Address field cannot be empty");
            etAdress.requestFocus();
            return;
        }

        if (isEditing) {
            Contact contact = new Contact();
            contact.setId(contactId);
            contact.setName(name);
            contact.setLast_name(lastname);
            contact.setEmail(email);
            contact.setPnumber(pnumber);
            contact.setAddress(adress);
            contactViewModel.update(contact);
        } else {
            Intent replyIntent = new Intent();
            replyIntent.putExtra(NAME_REPLY, name);
            replyIntent.putExtra(LASTNAME_REPLY, lastname);
            replyIntent.putExtra(EMAIL_REPLY, email);
            replyIntent.putExtra(PNUMBER_REPLY,pnumber);
            replyIntent.putExtra(ADRESS_REPLY,adress);
            setResult(RESULT_OK, replyIntent);

            Toast.makeText(this, "Employee added", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
