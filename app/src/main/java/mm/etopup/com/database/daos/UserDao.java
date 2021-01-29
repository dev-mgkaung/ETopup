package mm.etopup.com.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;
import mm.etopup.com.base.dao.BaseDao;
import mm.etopup.com.database.entity.UserEntity;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao extends BaseDao<UserEntity> {

    @Transaction
    @Query("SELECT * FROM user")
     LiveData<List<UserEntity>> getAllUser();

    @Insert(onConflict = REPLACE)
    long insertUser(UserEntity user);

    @Transaction
    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    LiveData<UserEntity> checkLoginUser(String email , String password);

    @Transaction
    @Query("SELECT * FROM user WHERE phone_number = :phone_number OR email = :email LIMIT 1")
    LiveData<UserEntity> checkPhoneNumber(String phone_number , String email );

    @Transaction
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    LiveData<UserEntity> getOneUser(String email );
}
