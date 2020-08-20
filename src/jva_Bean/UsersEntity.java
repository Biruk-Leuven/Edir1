package jva_Bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NamedQuery(name = "findAllUsers", query = "Select u From UsersEntity u ORDER BY u.userFullName ASC")
@DiscriminatorColumn(name = "userType")
@Table(name = "Users", schema = "dbEdir", catalog = "")
public class UsersEntity {
    private long userId;
    private String userFullName;
    private String userName;
    private String password;
    private String userType;
   // private String subCity;
   // private Integer hoseNo;
   private Address address;
    private List<Long> phoneNo = new ArrayList<>();


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userFullName")
    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "userType")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /*@Basic
    @Column(name = "subCity")
    public String getSubCity() {
        return subCity;
    }

    public void setSubCity(String subCity) {
        this.subCity = subCity;
    }

    @Basic
    @Column(name = "hoseNo")
    public Integer getHoseNo() {
        return hoseNo;
    }

    public void setHoseNo(Integer hoseNo) {
        this.hoseNo = hoseNo;
    }
*/

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "PhoneNo")
    @Column(name = "phoneNo")
    public List<Long> getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(List<Long> phoneNo) {
        this.phoneNo = phoneNo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return userId == that.userId &&
                Objects.equals(userFullName, that.userFullName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userType, that.userType) ;

               //Objects.equals(address.getSubCity(), that.address.getSubCity()) &&
               //Objects.equals(address.getHouseNo(), that.address.getHouseNo());
    }

    @Override
    //public int hashCode() {
        //return Objects.hash(userId, userFullName, userName, password, userType, subCity, hoseNo);
        public int hashCode() {
        return Objects.hash(userId, userFullName, userName, password, userType);
          //return Objects.hash(userId, userFullName, userName, password, userType,address.getSubCity(),address.getHouseNo());
    }
}
