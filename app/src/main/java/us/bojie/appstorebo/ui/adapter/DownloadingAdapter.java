package us.bojie.appstorebo.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.common.imageloader.ImageLoader;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.ui.widget.DownloadButtonController;
import us.bojie.appstorebo.ui.widget.DownloadProgressButton;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;


public class DownloadingAdapter extends BaseQuickAdapter<DownloadRecord, BaseViewHolder> {

    private DownloadButtonController mDownloadButtonConntroller;

    public DownloadingAdapter(RxDownload rxDownload) {
        super(R.layout.template_app_downloading);
        mDownloadButtonConntroller = new DownloadButtonController(rxDownload);
        openLoadAnimation();
    }


    @Override
    protected void convert(BaseViewHolder helper, DownloadRecord item) {
        AppInfo appInfo = mDownloadButtonConntroller.downloadRecord2AppInfo(item);

        ImageLoader.load(ApiService.BASE_IMG_URL + appInfo.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, appInfo.getDisplayName());

        helper.addOnClickListener(R.id.btn_download);
        View viewBtn = helper.getView(R.id.btn_download);

        if (viewBtn instanceof DownloadProgressButton) {
            DownloadProgressButton btn = (DownloadProgressButton) viewBtn;
            mDownloadButtonConntroller.handClick(btn, item.getUrl());
        }
    }
}
