package app.fiipractic.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class SearchUser {
    @NonNull
    private String userName;
}
