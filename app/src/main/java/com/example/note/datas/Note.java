package com.example.note.datas;

/**
 * Created by 李志军 on 2017/5/11.
 */

public class Note {

    //id
    private int id;

    //标题
    private String title;

    //内容
    private String content;

    //创建日期
    private String createDate;

    //更新日期
    private String updateDate;


    public Note(int id, String title, String content, String createDate, String updateDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Note(String title, String content, String createDate, String updateDate) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Note(int id, String title, String content, String updateDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.updateDate = updateDate;
    }

    public Note() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
