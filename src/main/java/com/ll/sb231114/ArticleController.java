package com.ll.sb231114;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }

    @GetMapping("/article/doWrite")
    String doWrite(String title, String body) {
        return "article/write";
    }
}

@AllArgsConstructor
@Getter
class Article {
    private long id;
    private String title;
    private String body;

}