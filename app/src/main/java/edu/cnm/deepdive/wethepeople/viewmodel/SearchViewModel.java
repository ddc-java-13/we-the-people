package edu.cnm.deepdive.wethepeople.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import edu.cnm.deepdive.wethepeople.service.LawOrBillRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class SearchViewModel extends AndroidViewModel implements LifecycleObserver {

  private final LawOrBillRepository repository;
  private final MutableLiveData<List<LawOrBill>> searchResults;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public SearchViewModel(@NonNull Application application) {
    super(application);
    repository = new LawOrBillRepository(application);
    searchResults = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<List<LawOrBill>> getSearchResults() {
    return searchResults;
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

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();

  }

  private void setThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}