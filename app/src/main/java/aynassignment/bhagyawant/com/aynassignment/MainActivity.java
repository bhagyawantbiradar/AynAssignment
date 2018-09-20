package aynassignment.bhagyawant.com.aynassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import aynassignment.bhagyawant.com.aynassignment.fragments.AddUserFragment;
import aynassignment.bhagyawant.com.aynassignment.fragments.UserListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.frameLayout, new AddUserFragment()).commitAllowingStateLoss();
    }
}
