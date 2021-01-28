package mm.etopup.com.base.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<T> datas);

    @Update
    void update(T data);

    @Delete
    void delete(T data);

    @Delete
    void deleteAll(List<T> datas);
}
