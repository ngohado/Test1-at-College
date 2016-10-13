package com.hado.testproject.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hado.testproject.R;
import com.hado.testproject.model.Contact;

/**
 * Created by Hado on 05-Oct-16.
 */

public class ContactViewHolder {
    public TextView tvName;
    public TextView tvPhone;

    public void bindView(View view) {
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvPhone = (TextView) view.findViewById(R.id.tv_sub_title);
    }

    public void bindData(Contact contact) {
        tvName.setText(contact.name);
        tvPhone.setText(contact.phone);
    }

}
