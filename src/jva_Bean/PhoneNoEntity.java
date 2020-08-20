package jva_Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "findAllPhones", query = "Select p.phoneNo From PhoneNoEntity p ORDER BY p.phoneNo ASC")
@Table(name = "PhoneNo", schema = "dbEdir", catalog = "")
public class PhoneNoEntity {
    private long phoneNo;
    private long userId;

    @Id
    @Column(name = "phoneNo")
    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "userId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNoEntity that = (PhoneNoEntity) o;
        return phoneNo == that.phoneNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNo);
    }
}
