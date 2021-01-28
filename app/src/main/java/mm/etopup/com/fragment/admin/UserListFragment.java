package mm.etopup.com.fragment.admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.adapters.UserListAdapter;
import mm.etopup.com.database.entity.UserEntity;
import mm.etopup.com.presenter.AdminPresenter;

public class UserListFragment extends Fragment {

    @BindView(R.id.userList)
    RecyclerView userList;

    AdminPresenter adminPresenter;
    UserListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, root);
        initPresenter();
        setUpRecyclerView();
        return root;
    }

    private void initPresenter()
    {
        adminPresenter= ViewModelProviders.of(this).get(AdminPresenter.class);
        adminPresenter.initPresenter(getActivity());
    }

    public void setUpRecyclerView()
    {

        adapter = new UserListAdapter(getActivity());
        userList.setAdapter(adapter);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        userList.setLayoutManager(llm);

        adminPresenter.getAllUser().observe(getActivity(), new Observer<List<UserEntity> >() {
            @Override
            public void onChanged(@Nullable final List<UserEntity> userlist) {
                if (userlist != null) {
                    adapter.setNewData(userlist);
                    userList.setScrollContainer(true);
                    userList.computeVerticalScrollExtent();
                   }
            }
        });

    }
}