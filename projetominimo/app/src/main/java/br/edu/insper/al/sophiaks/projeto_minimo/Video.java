package br.edu.insper.al.sophiaks.projeto_minimo;

public class Video {
    private int mWebView;
    private String mText1;
    private String mText2;

    public Video(int webView, String text1, String text2){
        mWebView = webView;
        mText1 = text1;
        mText2 = text2;
    }
    public int getWebView(){
        return mWebView;
    }

    public String getmText1(){
        return mText1;
    }

    public String getmText2(){
        return mText2;
    }
}
