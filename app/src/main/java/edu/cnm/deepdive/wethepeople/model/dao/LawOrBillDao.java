package edu.cnm.deepdive.wethepeople.model.dao;

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
import java.util.Set;

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

  @Query("SELECT * FROM law_or_bill ORDER BY creation_date DESC")
  LiveData<List<LawOrBill>> selectAll();

  @Query("SELECT * FROM law_or_bill ORDER BY creation_date DESC")
  LiveData<Set<LawOrBill>> selectAllAsSet();

//  @Query("SELECT * FROM law_or_bill WHERE attribute = :attribute ORDER BY creation_date")
//  LiveData<List<LawOrBill>> selectByAttribute(Attribute attribute);

}
