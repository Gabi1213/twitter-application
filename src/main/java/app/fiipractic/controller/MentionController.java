package app.fiipractic.controller;
import app.fiipractic.service.MentionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mention")
public class MentionController {


    private final MentionService mentionService;

    public MentionController(MentionService mentionService) {
        this.mentionService = mentionService;
    }


    @PostMapping("/{mentionedUser}")
    public List<String> getMentionsForUser(@PathVariable String mentionedUser) {
        return mentionService.getMentionsForUser(mentionedUser);
    }

}
