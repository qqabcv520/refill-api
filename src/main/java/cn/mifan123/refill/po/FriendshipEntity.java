package cn.mifan123.refill.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "friendship", schema = "refill", catalog = "")
public class FriendshipEntity {
    private Integer id;
    private Integer userId;
    private Integer friendId;
    private Integer intimacy;
    private Integer intimacyToday;
    private Date lastInteractive;

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
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "friend_id", nullable = false)
    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    @Basic
    @Column(name = "intimacy", nullable = false)
    public Integer getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(Integer intimacy) {
        this.intimacy = intimacy;
    }

    @Basic
    @Column(name = "intimacy_today", nullable = false)
    public Integer getIntimacyToday() {
        return intimacyToday;
    }

    public void setIntimacyToday(Integer intimacyToday) {
        this.intimacyToday = intimacyToday;
    }

    @Basic
    @Column(name = "last_interactive", nullable = true)
    public Date getLastInteractive() {
        return lastInteractive;
    }

    public void setLastInteractive(Date lastInteractive) {
        this.lastInteractive = lastInteractive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipEntity that = (FriendshipEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(friendId, that.friendId) &&
                Objects.equals(intimacy, that.intimacy) &&
                Objects.equals(intimacyToday, that.intimacyToday) &&
                Objects.equals(lastInteractive, that.lastInteractive);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, friendId, intimacy, intimacyToday, lastInteractive);
    }
}
