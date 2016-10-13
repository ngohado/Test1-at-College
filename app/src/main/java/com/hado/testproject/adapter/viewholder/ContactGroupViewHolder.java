package com.hado.testproject.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hado.testproject.R;
import com.hado.testproject.model.ContactGroup;

/**
 * Created by Hado on 12-Oct-16.
 */

public class ContactGroupViewHolder {
    public TextView tvName;
    public TextView tvDateTaken;

    public void bindView(View view) {
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvDateTaken = (TextView) view.findViewById(R.id.tv_sub_title);
    }

    public void bindData(ContactGroup contact) {
        tvName.setText(contact.groupName);
        tvDateTaken.setText(contact.dateTaken);
    }
}
