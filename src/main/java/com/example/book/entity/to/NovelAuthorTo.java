package com.example.book.entity.to;

/**
 * 获取小说名称和作者code
 *
 */
public class NovelAuthorTo {

    private String novelName;
    private String authorCode;

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }
}
