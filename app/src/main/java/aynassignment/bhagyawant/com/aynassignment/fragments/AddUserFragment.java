package aynassignment.bhagyawant.com.aynassignment.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import aynassignment.bhagyawant.com.aynassignment.R;
import aynassignment.bhagyawant.com.aynassignment.pojo.User;
import aynassignment.bhagyawant.com.aynassignment.utils.DatabaseHelper;


public class AddUserFragment extends Fragment implements View.OnClickListener {

    EditText etUserName, etMobileNumber, etEmail;
    TextInputLayout tilUserName, tilMobileNumber, tilEmail;
    Button btn, btnViewUsers;
    DatabaseHelper databaseHelper;
    int userID = -1;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            Bundle bundle = getArguments();
            userID = bundle.getInt("ID");
        } catch (Exception e) {
        }

        View v = inflater.inflate(R.layout.fragment_add_user, container, false);
        databaseHelper = new DatabaseHelper(getActivity());
        init(v);
        return v;
    }

    private void init(View v) {
        etEmail = v.findViewById(R.id.etEmail);
        etMobileNumber = v.findViewById(R.id.etMobileNumber);
        etUserName = v.findViewById(R.id.etUserName);
        tilUserName = v.findViewById(R.id.tilUserName);
        tilMobileNumber = v.findViewById(R.id.tilMobileNumber);
        tilEmail = v.findViewById(R.id.tilEmail);
        btn = v.findViewById(R.id.btn);
        btnViewUsers = v.findViewById(R.id.btnViewUsers);
        btn.setOnClickListener(this);
        btnViewUsers.setOnClickListener(this);

        if (userID != -1) {
            btn.setText("UPDATE");
            Log.d("ID ", "init: " + userID);
            User user = databaseHelper.getUser(userID);
            etEmail.setText(user.getEmailId());
            etMobileNumber.setText(user.getMobileNo());
            etUserName.setText(user.getUserName());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                if (isValidated()) {
                    User user = new User(etUserName.getText().toString(), etMobileNumber.getText().toString(), etEmail.getText().toString());
                    if (userID == -1) {
                        databaseHelper.addUser(user);
                    } else {
                        user.setUserID(userID);
                        databaseHelper.updateUser(user);
                    }
                    getActivity().getFragmentManager().beginTransaction().replace(R.id.frameLayout, new UserListFragment()).commitAllowingStateLoss();
                }
                break;

            case R.id.btnViewUsers:
                getActivity().getFragmentManager().beginTransaction().replace(R.id.frameLayout, new UserListFragment()).commitAllowingStateLoss();
                break;
        }

    }

    private boolean isValidated() {
        tilEmail.setError(null);
        tilMobileNumber.setError(null);
        tilUserName.setError(null);

        if (etUserName.getText().toString().equals("")) {
            tilUserName.setError("Please enter username");
            return false;
        } else if (etMobileNumber.getText().toString().length() != 10) {
            tilMobileNumber.setError("Please enter valid mobile number");
            return false;
        } else if (!etEmail.getText().toString().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            tilEmail.setError("Please enter valid email id");
            return false;
        } else return true;
    }
}
