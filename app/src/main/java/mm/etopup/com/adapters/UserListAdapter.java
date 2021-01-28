package mm.etopup.com.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import mm.etopup.com.R;
import mm.etopup.com.base.adapter.BaseRecyclerAdapter;
import mm.etopup.com.base.views.holders.BaseViewHolder;
import mm.etopup.com.database.entity.UserEntity;
import mm.etopup.com.viewholder.UserViewHolder;

public class UserListAdapter<T extends BaseViewHolder, W> extends BaseRecyclerAdapter<T, W> {

    public UserListAdapter(Context context ) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_layout, parent, false);
        return (T) new UserViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        if (holder instanceof UserViewHolder) {
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            if(userViewHolder!=null) {
                userViewHolder.bindData(mData.get(position),(ArrayList<UserEntity>)mData,position);
            }
        }
    }

}
