import java.time.LocalDate;
import java.util.ArrayList;

public class Encarrec {
    private LocalDate data;
    private ArrayList<Article> articles;

    public Encarrec(LocalDate data, ArrayList<Article> articles) {
        setData(data);
        setArticles(articles);
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Sisplau, indica una data correcta en format dd/mm/aaaa");
        }
        this.data = data;
    }
    public ArrayList<Article> getArticles() {return articles;}
    public LocalDate getData() {return data;}
    public void setArticles(ArrayList<Article> articles) {
        if(articles == null || articles.isEmpty()) {
            throw new IllegalArgumentException("Com a mínim pots fer encàrrecs d'un article.");
        }
        this.articles = articles;
    }
}
