package pl.saqie.SaQieBlog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 30, message = "Nazwa musi zawierać od 2 do 30 znaków")
    private String topic;
    @NotEmpty(message = "Treść posta nie może być pusta")
    @Column(length = 5000)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comments> commentsList;

    public Post(String topic, String content, User user, Category category) {
        this.topic = topic;
        this.content = content;
        this.user = user;
        this.category = category;
    }
}
