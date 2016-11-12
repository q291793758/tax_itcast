package cn.itcast.nsfw.complain.entity;

import java.sql.Timestamp;

public class ComplainReply {

    private String replyId;
    private String replyer;
    private String replyDept;
    private Timestamp replyTime;
    private String replyContent;
    private Complain complain;

    public ComplainReply() {
    }

    public ComplainReply(String replyer, String replyDept, Timestamp replyTime, String replyContent, Complain complain) {
        this.replyer = replyer;
        this.replyDept = replyDept;
        this.replyTime = replyTime;
        this.replyContent = replyContent;
        this.complain = complain;
    }

    public ComplainReply(Complain complain) {
        this.complain = complain;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getReplyer() {
        return replyer;
    }

    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }

    public String getReplyDept() {
        return replyDept;
    }

    public void setReplyDept(String replyDept) {
        this.replyDept = replyDept;
    }

    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplainReply that = (ComplainReply) o;

        if (replyId != null ? !replyId.equals(that.replyId) : that.replyId != null) return false;
        if (replyer != null ? !replyer.equals(that.replyer) : that.replyer != null) return false;
        if (replyDept != null ? !replyDept.equals(that.replyDept) : that.replyDept != null) return false;
        if (replyTime != null ? !replyTime.equals(that.replyTime) : that.replyTime != null) return false;
        if (replyContent != null ? !replyContent.equals(that.replyContent) : that.replyContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = replyId != null ? replyId.hashCode() : 0;
        result = 31 * result + (replyer != null ? replyer.hashCode() : 0);
        result = 31 * result + (replyDept != null ? replyDept.hashCode() : 0);
        result = 31 * result + (replyTime != null ? replyTime.hashCode() : 0);
        result = 31 * result + (replyContent != null ? replyContent.hashCode() : 0);
        return result;
    }

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }
}
