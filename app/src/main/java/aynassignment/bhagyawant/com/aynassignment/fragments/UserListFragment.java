package aynassignment.bhagyawant.com.aynassignment.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import aynassignment.bhagyawant.com.aynassignment.R;
import aynassignment.bhagyawant.com.aynassignment.adapters.UserListAdapter;
import aynassignment.bhagyawant.com.aynassignment.utils.DatabaseHelper;


public class UserListFragment extends Fragment implements View.OnClickListener{

    LinearLayoutManager linearLayoutManager;
    UserListAdapter userListAdapter;
    RecyclerView recyclerView;
    Button btnAddUser;
    DatabaseHelper databaseHelper;

    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_list, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        databaseHelper = new DatabaseHelper(getActivity());
        recyclerView = v.findViewById(R.id.recyclerView);
        btnAddUser = v.findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        userListAdapter = new UserListAdapter(databaseHelper.getUsers(), getActivity());
        recyclerView.setAdapter(userListAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnAddUser){
            getFragmentManager().beginTransaction().replace(R.id.frameLayout,new AddUserFragment()).commitAllowingStateLoss();
        }
    }
}
