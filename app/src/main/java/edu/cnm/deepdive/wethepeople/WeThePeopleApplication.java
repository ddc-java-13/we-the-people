package edu.cnm.deepdive.wethepeople;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.wethepeople.service.WeThePeopleDatabase;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Initializes (in the {@link #onCreate()} method) application-level resources. This class
 * <strong>must</strong> be referenced in {@code AndroidManifest.xml}, or it will not be loaded and
 * used by the Android system.
 */
public class WeThePeopleApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    WeThePeopleDatabase.setContext(this);
    WeThePeopleDatabase
        .getInstance()
        .getLawOrBillDao()
        .delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

}
