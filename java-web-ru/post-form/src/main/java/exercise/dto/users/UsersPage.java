package exercise.dto.users;

import exercise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

// BEGIN
@AllArgsConstructor
@Getter
public class UsersPage {
    private final List<User> users;
}
// END
