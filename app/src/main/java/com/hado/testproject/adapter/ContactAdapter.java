package com.hado.testproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hado.testproject.model.Contact;
import com.hado.testproject.adapter.viewholder.ContactViewHolder;

import java.util.ArrayList;

/**
 * Created by Hado on 05-Oct-16.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context mContext;
    private int mLayoutID;
    private ArrayList<Contact> contacts;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutID = resource;
        contacts = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactViewHolder contactViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mLayoutID, parent, false);
            contactViewHolder = new ContactViewHolder();
            contactViewHolder.bindView(convertView);
            convertView.setTag(contactViewHolder);
        } else {
            contactViewHolder = (ContactViewHolder) convertView.getTag();
        }

        contactViewHolder.bindData(contacts.get(position));

        return convertView;
    }

}
