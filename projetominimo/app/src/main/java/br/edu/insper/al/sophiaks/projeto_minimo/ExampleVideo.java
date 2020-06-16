package br.edu.insper.al.sophiaks.projeto_minimo;

public class ExampleVideo {
    private String Url;
    private String titulo;
    private String categoria;
    public ExampleVideo(String Url, String titulo, String categoria){
        this.Url = "https://player.vimeo.com/video/"+Url;
        this.titulo = titulo;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTitulo() {
        return titulo;
    }
    public String getUrl() {
        return Url;
    }
}
