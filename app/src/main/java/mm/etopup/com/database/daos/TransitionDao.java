package mm.etopup.com.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import mm.etopup.com.base.dao.BaseDao;
import mm.etopup.com.database.entity.TransitionHistory;
import mm.etopup.com.database.entity.UserEntity;
import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface TransitionDao extends BaseDao<TransitionHistory> {

    @Transaction
    @Query("SELECT * FROM transition  WHERE user_email = :email")
    LiveData<List<TransitionHistory>> getAllHistoryByEmail(String email);

    @Insert(onConflict = REPLACE)
    long insertTransition(TransitionHistory transitionHistory);

    @Query("Delete from transition")
    public void deleteAll();
}
