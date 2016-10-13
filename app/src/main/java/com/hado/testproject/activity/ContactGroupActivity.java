package com.hado.testproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hado.testproject.ConstantData;
import com.hado.testproject.model.ContactGroup;
import com.hado.testproject.adapter.ContactGroupAdapter;
import com.hado.testproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ContactGroupActivity extends Activity {

    EditText edtGroupName;

    Button btnAddNew;

    ListView lvGroupContacts;

    ContactGroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_group);
        initView();
        initListener();
    }

    private void initListener() {
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupName = edtGroupName.getText().toString();
                if (groupName != null && !groupName.isEmpty() && checkExistGroup(groupName)) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    ConstantData.contactGroups.add(new ContactGroup(groupName, format.format(Calendar.getInstance().getTime())));
                    groupAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ContactGroupActivity.this, "Group name is empty or duplicate!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvGroupContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ContactGroupActivity.this, DetailGroupActivity.class);
                intent.putExtra(DetailGroupActivity.GROUP_NAME, ConstantData.contactGroups.get(i).groupName);
                startActivity(intent);
            }
        });
    }

    private boolean checkExistGroup(String groupName) {
        for (ContactGroup group : ConstantData.contactGroups) {
            if (group.groupName.equals(groupName)) {
                return false;
            }
        }
        return true;
    }

    private void initView() {
        edtGroupName = (EditText) findViewById(R.id.edt_contact_name);
        btnAddNew = (Button) findViewById(R.id.btn_add_new);
        lvGroupContacts = (ListView) findViewById(R.id.lv_group_contact);
        groupAdapter = new ContactGroupAdapter(this, R.layout.item_contact_group, ConstantData.contactGroups);
        lvGroupContacts.setAdapter(groupAdapter);
    }
}
