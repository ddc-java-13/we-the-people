package edu.cnm.deepdive.wethepeople.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import edu.cnm.deepdive.wethepeople.service.LawOrBillRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final LawOrBillRepository repository;
  private final MutableLiveData<List<LawOrBill>> searchResults;
  private final MutableLiveData<LawOrBill> item;
  private final MutableLiveData<LawOrBill> randomReg;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  /**
   *
   * @param application Inflates all view models and populates searches
   */

  public MainViewModel(@NonNull Application application) {
    super(application);
    repository = new LawOrBillRepository(application);
    searchResults = new MutableLiveData<>();
    item = new MutableLiveData<>();
    randomReg = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    loadRandomReg();
  }

  public LiveData<List<LawOrBill>> getSearchResults() {
    return searchResults;
  }

  public LiveData<List<LawOrBill>> getSaved() {
    return repository.getAll();
  }

  public LiveData<LawOrBill> getItem() {
    return item;
  }

  public void setItem(LawOrBill item) {
    this.item.setValue(item);
  }

  public LiveData<LawOrBill> getRandomReg() {
    return randomReg;
  }

  private void loadRandomReg() {
    pending.add(
        repository
            .getRandom()
            .subscribe(
                randomReg::postValue,
                throwable::postValue
            )
    );
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void search(String searchTerm) {
    throwable.setValue(null);
    pending.add(
        repository
            .search(searchTerm)
            .subscribe(
                searchResults::postValue,
                this::setThrowable
            )
    );
  }

  public void toggleBookmark(LawOrBill item) {
    throwable.setValue(null);
    pending.add(
        repository
            .toggle(item)
            .subscribe(
                () -> {
                },
                this::setThrowable
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();

  }

  private void setThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}