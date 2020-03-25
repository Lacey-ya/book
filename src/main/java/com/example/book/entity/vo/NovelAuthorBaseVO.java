package com.example.book.entity.vo;

/**
 * 小说和作者数据整合类
 */
public class NovelAuthorBaseVO {
    private String novelName;
    private int status;
    private String authorCode;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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
