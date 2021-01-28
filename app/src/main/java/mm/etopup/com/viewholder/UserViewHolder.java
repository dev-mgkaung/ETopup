package mm.etopup.com.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.base.views.holders.BaseViewHolder;
import mm.etopup.com.database.entity.UserEntity;

public class UserViewHolder <T> extends BaseViewHolder<T> {

    @BindView(R.id.list)
    LinearLayout list;

    public UserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(T data) {

    }

    public void bindData(T data, ArrayList<UserEntity> dataList, int position) {
        if (data instanceof UserEntity){
            UserEntity item = (UserEntity) data;
            list.removeAllViews();
            UserListItemView userListItemView = (UserListItemView) LayoutInflater.from(itemView.getContext()).inflate(R.layout.user_list_item_layout,list,false);
            userListItemView.bind(item);
            list.addView(userListItemView);
        }
    }

    @Override
    public void onClick(View v) {

    }
}