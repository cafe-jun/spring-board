package com.cafejun.springboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
    }
)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false,length = 10000) private String content;

    @ToString.Exclude
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    private Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @Setter @Column(nullable = true) private String hashtag;
    @CreatedDate @Column(nullable = false) private LocalDate createdAt;
    @CreatedBy @Column(nullable = false,length = 100) private String createdBy;
    @LastModifiedDate @Column(nullable = false) private LocalDate modifiedAt;
    @LastModifiedBy @Column(nullable = false,length = 100) private String modifiedBy;

    protected Article() {}

    private Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public Article of(String title, String content) {
        return new Article(title,content);
    }

    // lombok 으로 EqualHashCode를 사용하지 않는 이유
    // Id 만 비교해도 동일한걸 알기 때문에 성능상 Id만 비교한다
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
