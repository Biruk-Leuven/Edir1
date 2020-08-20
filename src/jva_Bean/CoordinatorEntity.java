package jva_Bean;

import javax.persistence.*;
import java.util.Objects;
enum CoordinatorRole{
    chairman,storekeeper
}
@Entity
//@NamedQuery(name = "findAllCoords", query = "Select c From CoordinatorEntity c ORDER BY c.userId ASC")
@NamedQuery(name = "findAllCoords", query = "Select c.userId,c.userFullName,c.userName,c.password,c.userType,c.address.subCity,c.address.houseNo,c.userId,c.allowance,c.role From CoordinatorEntity c ORDER BY c.userId ASC")
@DiscriminatorValue("Coordinator")
@Table(name = "Coordinator", schema = "dbEdir", catalog = "")
public class CoordinatorEntity extends UsersEntity {
    private long userId;
    private double allowance;
    //private String role;
    private CoordinatorRole role;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "allowance")
    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

   @Enumerated(EnumType.STRING)
   public CoordinatorRole getRole()
   {return role;}
    public void setRole(CoordinatorRole role){
        this.role=role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatorEntity that = (CoordinatorEntity) o;
        return userId == that.userId &&
                Double.compare(that.allowance, allowance) == 0 &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, allowance, role);
    }
}
