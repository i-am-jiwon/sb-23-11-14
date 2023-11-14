package com.ll.sb231114;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {
    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }

    @GetMapping("/article/doWrite")
    @ResponseBody
    String doWrite(String title, String body) {
        return "게시물이 작성되었습니다.";
    }
}

@AllArgsConstructor
@Getter
class Article {
    private long id;
    private String title;
    private String body;

}