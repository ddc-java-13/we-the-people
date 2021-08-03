package edu.cnm.deepdive.wethepeople.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.wethepeople.model.dao.LawOrBillDao;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;

import edu.cnm.deepdive.wethepeople.model.pojo.Attribute;
import edu.cnm.deepdive.wethepeople.service.WeThePeopleDatabase.Converters;
import java.util.Date;

@Database(
    entities = {LawOrBill.class},
    version = 1,
    exportSchema = true
)

@TypeConverters({Converters.class, Attribute.class})
public abstract class WeThePeopleDatabase extends RoomDatabase {

  private static final String DATABASE_NAME = "we-the-people-database";

  public static void setContext(Application context) {
    WeThePeopleDatabase.context = context;
  }

  private static Application context;

  private static class InstanceHolder {

    private static final WeThePeopleDatabase INSTANCE =
        Room.databaseBuilder(context, WeThePeopleDatabase.class, DATABASE_NAME)
            .build();
  }

  public static WeThePeopleDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public static class Converters {

    @TypeConverter
    public static Long toLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date toDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }


  public abstract LawOrBillDao getLawOrBillDao();


}
