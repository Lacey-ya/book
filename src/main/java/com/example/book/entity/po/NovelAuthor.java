package com.example.book.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("novel_author")
public class NovelAuthor {

    private String novelCode;
    private String authorCode;

    public String getNovelCode() {
        return novelCode;
    }

    public void setNovelCode(String novelCode) {
        this.novelCode = novelCode;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }
}
