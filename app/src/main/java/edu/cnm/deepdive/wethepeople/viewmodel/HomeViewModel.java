package edu.cnm.deepdive.wethepeople.viewmodel;

import android.app.Application;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import edu.cnm.deepdive.wethepeople.service.LawOrBillRepository;
import io.reactivex.disposables.CompositeDisposable;
import org.jetbrains.annotations.NotNull;

public class HomeViewModel extends AndroidViewModel {

  private final MutableLiveData<LawOrBill> randomReg;
  private final MutableLiveData<Throwable> throwable;
  private final LawOrBillRepository repository;
  private final CompositeDisposable pending;

  public HomeViewModel(@NonNull Application application) {
    super(application);
    repository = new LawOrBillRepository(application);
    randomReg = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    loadRandomReg();
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
}