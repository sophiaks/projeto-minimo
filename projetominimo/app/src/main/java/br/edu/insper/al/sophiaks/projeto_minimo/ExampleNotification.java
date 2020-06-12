package br.edu.insper.al.sophiaks.projeto_minimo;

public class ExampleNotification {
    private int mImageResource;
    private String mText1;
    private String mText2;
    public ExampleNotification(String text1, String text2) { //Pode adicionar entrada de imagem nessa classe no futuro
        mImageResource = R.drawable.icon_semente;
        mText1 = text1;
        mText2 = text2;
    }
    public int getImageResource() {
        return mImageResource;
    }
    public String getText1() {
        return mText1;
    }
    public String getText2() {
        return mText2;
    }
}
