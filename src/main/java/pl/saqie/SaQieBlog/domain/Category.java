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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 30, message = "Nazwa musi zawierać od 2 do 30 znaków")
    private String name;
    @NotEmpty(message = "Opis nie może być pusty !")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> posts;


}
