package hurtado.luis.ejemplo.cinefest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luis.hurtado on 07/05/2018.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context context ;
    private List<ViewPagerResponse.ImagenesBean> imageUrls;

    public ViewPagerAdapter(Context context, List<ViewPagerResponse.ImagenesBean> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageView = new ImageView(context);

        Picasso.with(context)
                .load(imageUrls.get(position).getUrl())
                .fit().placeholder(R.drawable.place_holder)
                .into(imageView);

        container.addView(imageView);



        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
