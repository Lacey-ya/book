package com.example.book.entity.po;


import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 小说作者关联实体类
 */
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
