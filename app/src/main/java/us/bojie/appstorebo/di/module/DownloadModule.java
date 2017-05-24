package us.bojie.appstorebo.di.module;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import us.bojie.appstorebo.common.Constant;
import us.bojie.appstorebo.common.util.ACache;
import zlc.season.rxdownload2.RxDownload;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * Created by bojiejiang on 5/22/17.
 */

@Module
public class DownloadModule {

    @Provides
    @Singleton
    public RxDownload provideRxDownload(Application application, Retrofit retrofit, File downDir) {

        ACache.get(application).put(Constant.APK_DOWNLOAD_DIR, downDir.getPath());
        return RxDownload.getInstance(application)
                .defaultSavePath(downDir.getPath())
                .retrofit(retrofit)
                .maxDownloadNumber(10)
                .maxThread(10);

    }

    @Provides
    @Singleton
    File provideDownloadDir() {
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);
    }
}
