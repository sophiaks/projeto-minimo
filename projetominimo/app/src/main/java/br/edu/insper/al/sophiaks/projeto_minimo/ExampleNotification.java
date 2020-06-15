package br.edu.insper.al.sophiaks.projeto_minimo;

public class ExampleNotification {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public ExampleNotification(String titulo, String corpo) { //Pode adicionar entrada de imagem nessa classe no futuro
        mImageResource = R.drawable.bell;
        mText1 = titulo;
        mText2 = corpo;
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
