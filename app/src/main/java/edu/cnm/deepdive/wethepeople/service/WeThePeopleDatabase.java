package edu.cnm.deepdive.wethepeople.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.wethepeople.model.dao.LawOrBillDao;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;

import edu.cnm.deepdive.wethepeople.model.entity.Attribute;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill.Links;
import edu.cnm.deepdive.wethepeople.service.WeThePeopleDatabase.Converters;
import java.util.Date;

@Database(
    entities = {LawOrBill.class},
    version = 1,
    exportSchema = true
)

@TypeConverters(value = {Converters.class, LawOrBill.class, LawOrBill.Links.class })
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

  public abstract LawOrBillDao getLawOrBillDao();

  public static class Converters {

    @TypeConverter
    public static Long toLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date toDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

    @TypeConverter
    public static String toString(Links value) {
      return (value != null) ? value.getSelf() : null;
    }

    @TypeConverter
    public static Links from(String value) {
      return (value != null) ? new Links(value) : null;
    }

  }



}
