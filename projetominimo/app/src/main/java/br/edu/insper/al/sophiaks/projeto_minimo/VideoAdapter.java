package br.edu.insper.al.sophiaks.projeto_minimo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {


    public static class VideoViewHolder extends RecyclerView.ViewHolder{
        public WebView mWebView;
        public TextView mTextView1;
        public TextView mTextView2;


        public VideoViewHolder(View itemView) {
            super(itemView);
            mWebView = itemView.findViewById(R.id.webView);
            mTextView1 = itemView.findViewById(R.id.categoryVideo);
            mTextView2 = itemView.findViewById(R.id.titleVideo);
        }
    }


    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent, false);
        VideoViewHolder evh2 = new VideoViewHolder(v2);
        return evh2;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
