package app.fiipractic.controller;

import app.fiipractic.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

   @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/addreply/{parentPostId}")
    public ResponseEntity<String> addReply(
            @PathVariable int parentPostId,
            @RequestParam String content,
            @RequestParam(defaultValue = "false") boolean isPublic) {

        try {
            replyService.addReply(parentPostId, content, isPublic);
            return ResponseEntity.ok("Reply added!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
