package com.hado.testproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hado.testproject.ConstantData;
import com.hado.testproject.R;
import com.hado.testproject.adapter.ContactAdapter;
import com.hado.testproject.dialog.MessengerDialog;
import com.hado.testproject.model.Contact;
import com.hado.testproject.model.ContactGroup;

import java.util.ArrayList;

public class DetailGroupActivity extends Activity {

    EditText edtName;

    EditText edtPhoneNumber;

    Button btnAddNew;

    Button btnChose;

    Button btnSendAll;

    ListView lvContacts;

    ContactAdapter contactAdapter;

    ArrayList<Contact> contacts = new ArrayList<>();

    public static final String GROUP_NAME = "group_name";
    public static final int CHOSE_CONTACT_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_group);
        initData();
        initView();
        initListener();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String groupName = intent.getStringExtra(GROUP_NAME);
            if (groupName != null) {
                getActionBar().setTitle(groupName);
                for (ContactGroup group : ConstantData.contactGroups) {
                    if (group.groupName.equalsIgnoreCase(groupName)) {
                        contacts = group.contacts;
                    }
                }
            }
        }
    }

    private void initListener() {
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phoneNumber = edtPhoneNumber.getText().toString();
                if (!name.isEmpty() && !phoneNumber.isEmpty() && checkExistPhoneNumber(phoneNumber)) {
                    contacts.add(new Contact(name, phoneNumber));
                    contactAdapter.notifyDataSetChanged();
                    edtPhoneNumber.setText("");
                    edtName.setText("");
                } else {
                    Toast.makeText(DetailGroupActivity.this, "Name or Phone Number is empty or duplicate!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(contactPickerIntent, CHOSE_CONTACT_REQUEST);
            }
        });

        btnSendAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!contacts.isEmpty()) {
                    String numbers = "";
                    for (Contact contact : contacts) {
                        numbers += contact.phone + ";";
                    }
                    numbers = numbers.substring(0, numbers.length() - 1);
                    new MessengerDialog(DetailGroupActivity.this, numbers).show();
                } else {
                    Toast.makeText(DetailGroupActivity.this, "Group phone number is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private boolean checkExistPhoneNumber(String phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.phone.equalsIgnoreCase(phoneNumber)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CHOSE_CONTACT_REQUEST) {
            Cursor cursor;
            String phoneNo;
            String name;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            edtPhoneNumber.setText(phoneNo);
            edtName.setText(name);
        }
    }

    private void initView() {
        edtName = (EditText) findViewById(R.id.edt_name);
        edtPhoneNumber = (EditText) findViewById(R.id.edt_phone);
        btnAddNew = (Button) findViewById(R.id.btn_add_new);
        btnChose = (Button) findViewById(R.id.btn_open_contact);
        btnSendAll = (Button) findViewById(R.id.btn_send_all);
        lvContacts = (ListView) findViewById(R.id.lv_contact);
        contactAdapter = new ContactAdapter(this, R.layout.item_contact_group, contacts);
        lvContacts.setAdapter(contactAdapter);
    }
}
