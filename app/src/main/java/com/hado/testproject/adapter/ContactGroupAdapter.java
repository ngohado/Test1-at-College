package com.hado.testproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hado.testproject.model.ContactGroup;
import com.hado.testproject.adapter.viewholder.ContactGroupViewHolder;

import java.util.ArrayList;

/**
 * Created by Hado on 12-Oct-16.
 */

public class ContactGroupAdapter extends ArrayAdapter<ContactGroup> {
    private Context mContext;
    private int mLayoutID;
    private ArrayList<ContactGroup> contacts;

    public ContactGroupAdapter(Context context, int resource, ArrayList<ContactGroup> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutID = resource;
        contacts = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactGroupViewHolder contactViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mLayoutID, parent, false);
            contactViewHolder = new ContactGroupViewHolder();
            contactViewHolder.bindView(convertView);
            convertView.setTag(contactViewHolder);
        } else {
            contactViewHolder = (ContactGroupViewHolder) convertView.getTag();
        }

        contactViewHolder.bindData(contacts.get(position));

        return convertView;
    }

}
