package mm.etopup.com.database.daos;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import mm.etopup.com.base.dao.BaseDao;
import mm.etopup.com.database.entity.UserEntity;

@Dao
public interface UserDao extends BaseDao<UserEntity> {

//    @Transaction
//    @Query("SELECT * FROM user_table")
//    public LiveData<List<NewsOffline>> getAllNews();
//
//    @Transaction
//    @Query("SELECT * FROM news_table where syskey = :syskey")
//    public LiveData<NewsOffline> getNewsById(long syskey);
//
//    @Query("UPDATE news_table SET like_status = :like_status,like_count = :like_count WHERE syskey = :syskey")
//    public int updateLike(long like_status, long syskey, long like_count);
//
//    @Query("UPDATE news_table SET comment_count = :comment_count WHERE syskey = :syskey")
//    public int updateCommentCount(long syskey, long comment_count);
//
//    @Query("UPDATE news_table SET share_status = :share_status WHERE syskey = :syskey")
//    public int updateShare(long share_status, long syskey);
//
//    @Query("UPDATE news_table SET save_status = :save_status WHERE syskey = :syskey")
//    public int updateSave(long save_status, long syskey);
//
//    @Query("Delete from news_table")
//    public void deleteAll();
//
//    @Query("Delete from news_table WHERE syskey = :syskey")
//    public void deletebyID(long syskey);
//
//    @Query("UPDATE news_table SET question_official = :question_official WHERE syskey = :syskey")
//    public int updateQuizStatus(long question_official, long syskey);
//
//    @Query("UPDATE news_table SET highlight_status = :highlightstatus WHERE syskey = :possyskey")
//    public int updateHighlightStatus(long possyskey, long highlightstatus);

}
