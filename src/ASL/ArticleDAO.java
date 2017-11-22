package ASL;

import Domain.Article;
import Domain.ArticleOnSale;

import java.util.List;

/**
 * Represents an interface for the Data access object
 * which is used to retrieve article objects from
 * the Database
 */
public interface ArticleDAO  {

    void createNewArticle(Article a);

    void deleteArticle(Article a);

    void updateArticle(Article a);

    List<Article> readAllArticles();

    Article getArticleById(int a_id);
}
