package br.edu.insper.al.sophiaks.projeto_minimo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Browser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.LinkedList;


public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    LinkedList<String> linkedTitle;
    LinkedList<String> linkedVimeo;
    LinkedList<String> linkedThumb;
    LinkedList<String> linkedCategory;
    LayoutInflater inflater;
    public ViewPagerAdapter(Context context, LinkedList<String> linkedTitle, LinkedList<String> linkedVimeo,
                            LinkedList<String> linkedCategory, LinkedList<String> linkedThumb) {
        this.context = context;
        this.linkedTitle = linkedTitle;
        this.linkedVimeo = linkedVimeo;
        this.linkedCategory = linkedCategory;
        this.linkedThumb = linkedThumb;
    }

    @Override
    public int getCount() {
        return linkedTitle.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }


    @SuppressLint("WrongViewCast")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        WebView webView;
        TextView txttitle;
        TextView txtcategory;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ResourceType") View itemView = inflater.inflate(R.layout.fragment_screen_video, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        webView = (WebView) itemView.findViewById(R.id.web);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());


        txttitle = (TextView) itemView.findViewById(R.id.titleVideo);
        txtcategory = (TextView) itemView.findViewById(R.id.categoryVideo);
        // Capture position and set to the TextViews
        txttitle.setText(linkedTitle.get(position));
        txtcategory.setText(linkedCategory.get(position));

//        Capture url and set the webView
        webView.loadUrl("https://player.vimeo.com/video/24577973");

        // Add viewpager viewpager_video.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

}
