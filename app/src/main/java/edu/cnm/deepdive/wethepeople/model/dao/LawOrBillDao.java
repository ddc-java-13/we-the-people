package edu.cnm.deepdive.wethepeople.model.dao;

import android.renderscript.Type;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;

import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface LawOrBillDao {

  @Insert
  Single<Long> insert(LawOrBill lawOrBill);

  @Insert
  Single<List<Long>> insert(LawOrBill... lawOrBills);

  @Insert
  Single<List<Long>> insert(Collection<? extends LawOrBill> lawOrBills);

  @Update
  Single<Integer> update(LawOrBill lawOrBill);

  @Update
  Single<Integer> update(LawOrBill... lawOrBills);

  @Update
  Single<Integer> update(Collection<? extends LawOrBill> lawOrBills);

  @Delete
  Single<Integer> delete(LawOrBill lawOrBill);

  @Delete
  Single<Integer> delete(LawOrBill... lawOrBills);

  @Delete
  Single<Integer> delete(Collection<? extends LawOrBill> lawOrBills);


  @Query("SELECT * FROM law_or_bill WHERE law_or_bill_id = :id")
  LiveData<LawOrBill> select(long id);

  @Query("SELECT * FROM law_or_bill WHERE attribute = :type ORDER BY attribute")
  LiveData<List<LawOrBill>> selectByType(Type type);
}
