package app.fiipractic.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.lang.reflect.Array;

@Getter
@Setter
public class Post {

    @NonNull
    private Long postId;

    @NonNull
    private String content;

    @NonNull
    private Long userId;

    private String orderByTime;

    private String[] mentions;
}