package app.fiipractic.model;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter

public class Mention {

    private int mentionId;

    private String post;

    private int userId;
}
