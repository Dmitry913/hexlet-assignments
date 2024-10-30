package exercise.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
}
// END
