package jva_Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@NamedQuery(name = "findAllMems", query = "Select m From MemberEntity m ORDER BY m.userId ASC")
@NamedQuery(name = "findAllMems", query = "Select m.userId,m.userFullName,m.userName,m.password,m.userType,m.address.subCity,m.address.houseNo,m.userId,m.status From MemberEntity m ORDER BY m.userId ASC")
@DiscriminatorValue("Member")
@Table(name = "Member", schema = "dbEdir", catalog = "")
public class MemberEntity extends UsersEntity {
    private long userId;
    private String status;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity that = (MemberEntity) o;
        return userId == that.userId &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, status);
    }
}
