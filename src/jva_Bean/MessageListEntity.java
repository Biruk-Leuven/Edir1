package jva_Bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "MessageList", schema = "dbEdir", catalog = "")
public class MessageListEntity {
    private long msgNo;
    private String content;
    private Timestamp msgTime;
    private String msgCategory;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msgNo")
    public long getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(long msgNo) {
        this.msgNo = msgNo;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "msgTime")
    public Timestamp getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Timestamp msgTime) {
        this.msgTime = msgTime;
    }

    @Basic
    @Column(name = "msgCategory")
    public String getMsgCategory() {
        return msgCategory;
    }

    public void setMsgCategory(String msgCategory) {
        this.msgCategory = msgCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageListEntity that = (MessageListEntity) o;
        return msgNo == that.msgNo &&
                Objects.equals(content, that.content) &&
                Objects.equals(msgTime, that.msgTime) &&
                Objects.equals(msgCategory, that.msgCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgNo, content, msgTime, msgCategory);
    }
}
