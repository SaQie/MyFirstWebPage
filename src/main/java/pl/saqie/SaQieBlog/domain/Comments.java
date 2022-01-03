package pl.saqie.SaQieBlog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(columnDefinition = "user_id")
    private User user;

    public Comments(String text, Post post, User user) {
        this.text = text;
        this.post = post;
        this.user = user;
    }
}
