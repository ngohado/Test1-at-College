package com.hado.testproject.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.hado.testproject.R;

/**
 * Created by Hado on 12-Oct-16.
 */

public class MessengerDialog extends Dialog {

    String mNumbers;

    EditText edtBody;

    public MessengerDialog(Context context, String numbers) {
        super(context);
        mNumbers = numbers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_send_sms);
        initView();
    }

    private void initView() {
        edtBody = (EditText) findViewById(R.id.edt_sms);
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtBody.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Body is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent sendMsg = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: " + mNumbers));
                sendMsg.putExtra("sms_body", edtBody.getText().toString());
                getContext().startActivity(sendMsg);
            }
        });
    }
}
