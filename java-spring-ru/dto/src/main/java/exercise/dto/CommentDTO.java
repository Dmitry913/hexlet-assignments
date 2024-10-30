package exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String body;
}
// END
