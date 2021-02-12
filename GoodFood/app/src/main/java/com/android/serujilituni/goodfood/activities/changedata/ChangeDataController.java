package com.android.serujilituni.goodfood.activities.changedata;

import android.widget.Button;
import android.widget.EditText;

import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.credentials.CredentialsManager;
import com.android.serujilituni.goodfood.model.User;
import com.android.serujilituni.goodfood.store.DBManager;

public class ChangeDataController {
    private Button[] btn;
    private EditText nameEt;

    public ChangeDataController(Button[] btn, EditText nameEt) {
        this.btn = btn;
        this.nameEt = nameEt;
        initButtons();
    }

    private void initButtons() {
        initChangeNameBtn();
        initChangePasswordBtn();
    }

    private void initChangePasswordBtn() {
        this.btn[Constants.CHANGE_DATA_PASSWORD_BTN].setOnClickListener(v -> CredentialsManager.resetPassword(CredentialsManager.getUserEmail()));
    }

    private void initChangeNameBtn() {
        this.btn[Constants.CHANGE_DATA_NAME_BTN].setOnClickListener(v -> {
            String name = nameEt.getText().toString();
            if(!name.isEmpty()) {
                DBManager.getInstance().storeUser(CredentialsManager.getUserUid(), new User(name, CredentialsManager.getUserEmail()), true);
            }
        });
    }
}
