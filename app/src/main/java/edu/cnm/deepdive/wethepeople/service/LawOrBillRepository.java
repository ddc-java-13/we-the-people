package edu.cnm.deepdive.wethepeople.service;

import android.content.Context;
import edu.cnm.deepdive.wethepeople.BuildConfig;
import edu.cnm.deepdive.wethepeople.model.dao.LawOrBillDao;
import edu.cnm.deepdive.wethepeople.model.dto.SearchResult;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Random;

public class LawOrBillRepository {

  private final Context context;
  private final WebServiceProxy proxy;
  private final LawOrBillDao lawOrBillDao;
  private final Random random;
  private final static String WORD_FILE = "truck";

  public LawOrBillRepository(Context context) {
    this.context = context;
    WeThePeopleDatabase database = WeThePeopleDatabase.getInstance();
    lawOrBillDao = database.getLawOrBillDao();
    proxy = WebServiceProxy.getInstance();
    random = new Random();
  }

  public Single<LawOrBill> getRandom() {
    return search(WORD_FILE)//TODO use a randomly selected search term
     .map((results) -> {
      return results.get(random.nextInt(results.size()));
    });
  }

  public Single<List<LawOrBill>> search(String searchTerm) {
    return proxy
        .getHits(searchTerm, BuildConfig.API_KEY)
        .map(SearchResult::getData)
        .subscribeOn(Schedulers.io());
  }
}
