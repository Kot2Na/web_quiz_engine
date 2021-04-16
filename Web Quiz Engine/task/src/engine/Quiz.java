package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Quiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @NotBlank
    @Column(name = "Title")
    private String title;

    @NotBlank
    @Column(name = "Text")
    private String text;

    @Size(min = 2)
    @Column(name = "Options")
    @NotNull(message = "Options mustn't be null.")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "Answer")
    private Integer[] answer;
}
