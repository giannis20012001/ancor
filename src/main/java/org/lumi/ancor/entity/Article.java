package org.lumi.ancor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 19/12/2017.
 */

@Entity
@Table(name="articles")
public class Article implements Serializable {
    //=================================================================================================================
    //Setters & Getters
    //=================================================================================================================
    public int getArticleId() {
        return articleId;

    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;

    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;

    }

    public String getCategory() {
        return category;

    }

    public void setCategory(String category) {
        this.category = category;

    }
    //=================================================================================================================
    //Constructors
    //=================================================================================================================
    /**
     * Default constructor
     */
    public Article() {
        //Do nothing

    }

    /**
     * Parameterized constructor
     */
    public Article(String title, String category) {
        this.title = title;
        this.category = category;

    }

    //=================================================================================================================
    //=================================================================================================================
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="article_id")
    private int articleId;
    @Column(name="title")
    private String title;
    @Column(name="category")
    private String category;
    //serialization related var
    private static final long serialVersionUID = 1L;

}
