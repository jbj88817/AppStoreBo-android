package us.bojie.appstorebo.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.Category;
import us.bojie.appstorebo.common.imageloader.ImageLoader;
import us.bojie.appstorebo.data.http.ApiService;


public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {


    public CategoryAdapter() {

        super(R.layout.template_category);

    }


    @Override
    protected void convert(BaseViewHolder helper, Category item) {

        helper.setText(R.id.text_name, item.getName());

        ImageLoader.load(ApiService.BASE_IMG_URL + item.getIcon(), (ImageView) helper.getView(R.id.img_icon));
    }


}
