package us.bojie.appstorebo.common.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.common.exception.ApiException;

/**
 * Created by bojiejiang on 4/25/17.
 */

public class RxHttpReponseCompat {

    public static <T> ObservableTransformer<BaseBean<T>, T> compatResult() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(final BaseBean<T> tBaseBean) throws Exception {
                        if (tBaseBean.isSucessful()) {
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> e) throws Exception {
                                        e.onNext(tBaseBean.getData());
                                        e.onComplete();
                                }
                            });
                        } else {
                            return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
