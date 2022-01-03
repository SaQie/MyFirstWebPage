package pl.saqie.SaQieBlog.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private boolean locked = false;
    private boolean enabled = true;
    private String role;
    private Integer writedPosts;
    private Integer writedComments;
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;


    public List<String> getRoles(){
        if (this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }

    public User(String username, String password, String email, String firstName, String lastName, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.writedPosts = 0;
        this.writedComments = 0;
        this.createdDate = LocalDateTime.now();
    }
}
