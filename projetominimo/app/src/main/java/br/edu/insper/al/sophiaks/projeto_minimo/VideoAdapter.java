package br.edu.insper.al.sophiaks.projeto_minimo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
<<<<<<< HEAD
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
=======
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ExampleViewHolder> {
    private ArrayList<ExampleVideo> mExampleList;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public WebView mWebView;
        public TextView mTitulo;
        public TextView mCategoria;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mWebView = itemView.findViewById(R.id.urlVideo);
            mTitulo = itemView.findViewById(R.id.tituloVideo);
            mCategoria = itemView.findViewById(R.id.categoriaVideo);
        }
    }
    public VideoAdapter(ArrayList<ExampleVideo> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public VideoAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_video, parent, false);
        VideoAdapter.ExampleViewHolder evh = new VideoAdapter.ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(VideoAdapter.ExampleViewHolder holder, int position) {
        ExampleVideo currentItem = mExampleList.get(position);
        holder.mWebView.loadUrl(currentItem.getUrl());
        holder.mTitulo.setText(currentItem.getTitulo());
        holder.mCategoria.setText(currentItem.getCategoria());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

>>>>>>> 2bc8c5599c158d8cb82f33ae8226541be6f026df
