package us.bojie.appstorebo.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.common.imageloader.ImageLoader;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.ui.widget.DownloadButtonController;
import us.bojie.appstorebo.ui.widget.DownloadProgressButton;

/**
 * Created by bojiejiang on 5/1/17.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo, BaseViewHolder> {

    private Builder mBuilder;

    private AppInfoAdapter(Builder builder) {
        super(builder.layoutId);
        mBuilder = builder;
        openLoadAnimation();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(ApiService.BASE_IMG_URL + item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, item.getDisplayName());

        TextView txtViewPosition = helper.getView(R.id.txt_position);
        if (txtViewPosition != null) {
            txtViewPosition.setVisibility(mBuilder.isShowPosition ? View.VISIBLE : View.GONE);
            txtViewPosition.setText(item.getPosition() + 1 + ". ");
        }


        TextView txtViewCategory = helper.getView(R.id.txt_category);
        if (txtViewCategory != null) {
            txtViewCategory.setVisibility(mBuilder.isShowCategoryName ? View.VISIBLE : View.GONE);
            txtViewCategory.setText(item.getLevel1CategoryName());
        }


        TextView txtViewBrief = helper.getView(R.id.txt_brief);
        if (txtViewBrief != null) {
            txtViewBrief.setVisibility(mBuilder.isShowBrief ? View.VISIBLE : View.GONE);
            txtViewBrief.setText(item.getBriefShow());
        }

        TextView textViewSize = helper.getView(R.id.txt_apk_size);
        if (textViewSize != null) {
            textViewSize.setText((item.getApkSize() / 1024 / 1024) + "MB");
        }

        DownloadProgressButton downloadProgressButton = helper.getView(R.id.btn_download);
        DownloadButtonController.handClick(downloadProgressButton, item);

    }

    public static class Builder {
        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;

        private int layoutId = R.layout.template_appinfo;


        public Builder showPosition(boolean b) {
            this.isShowPosition = b;
            return this;
        }

        public Builder showCategoryName(boolean b) {
            this.isShowCategoryName = b;
            return this;
        }

        public Builder showBrief(boolean b) {
            this.isShowBrief = b;
            return this;
        }

        public Builder setLayoutId(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public AppInfoAdapter build() {
            return new AppInfoAdapter(this);
        }
    }
}
