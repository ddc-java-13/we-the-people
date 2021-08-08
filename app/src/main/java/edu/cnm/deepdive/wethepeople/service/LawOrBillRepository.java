package edu.cnm.deepdive.wethepeople.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.wethepeople.BuildConfig;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.model.dao.LawOrBillDao;
import edu.cnm.deepdive.wethepeople.model.dto.SearchResult;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LawOrBillRepository {

  private final Context context;
  private final WebServiceProxy proxy;
  private final LawOrBillDao lawOrBillDao;
  private final Random random;
  private final String[] randomSearchTerms;

  public LawOrBillRepository(Context context) {
    this.context = context;
    WeThePeopleDatabase database = WeThePeopleDatabase.getInstance();
    lawOrBillDao = database.getLawOrBillDao();
    proxy = WebServiceProxy.getInstance();
    random = new Random();
    randomSearchTerms = context.getResources().getStringArray(R.array.random_search_terms);
  }

  public Single<LawOrBill> getRandom() {
    String searchTerm = randomSearchTerms[random.nextInt(randomSearchTerms.length)];
    return search(searchTerm)
        .map((results) -> results.get(random.nextInt(results.size())));
  }

  public Single<List<LawOrBill>> search(String searchTerm) {
    return proxy
        .getHits(searchTerm, BuildConfig.API_KEY)
        .map(SearchResult::getData)
        .subscribeOn(Schedulers.io());
  }

  public LiveData<List<LawOrBill>> getAll() {
    return lawOrBillDao.selectAll();
  }

  public LiveData<Set<LawOrBill>> getAllAsSet() {
    return lawOrBillDao.selectAllAsSet();
  }
}
