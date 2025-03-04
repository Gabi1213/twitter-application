package app.fiipractic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reply {

    private Long id;

    private String content;

    private boolean isPublic;

    private int parentPostId;
}
