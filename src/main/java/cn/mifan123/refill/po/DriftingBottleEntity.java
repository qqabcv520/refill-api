package cn.mifan123.refill.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "drifting_bottle", schema = "refill", catalog = "")
public class DriftingBottleEntity {
    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private Date sendTime;
    private Date receiveTime;
    private Integer state;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sender_id", nullable = false)
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "receiver_id", nullable = true)
    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "send_time", nullable = true)
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "receive_time", nullable = true)
    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriftingBottleEntity that = (DriftingBottleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(senderId, that.senderId) &&
                Objects.equals(receiverId, that.receiverId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(sendTime, that.sendTime) &&
                Objects.equals(receiveTime, that.receiveTime) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, senderId, receiverId, content, sendTime, receiveTime, state);
    }
}
