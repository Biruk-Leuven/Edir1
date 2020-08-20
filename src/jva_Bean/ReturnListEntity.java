package jva_Bean;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@NamedQuery(name = "findAllRLs", query = "Select r From ReturnListEntity r ORDER BY r.retId ASC")
@Table(name = "ReturnList", schema = "dbEdir", catalog = "")
public class ReturnListEntity {
    private long retId;
    private Date returnDate;

    private CoordinatorEntity corId;
    private BorrowListEntity borId;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retId")
    public long getRetId() {
        return retId;
    }

    public void setRetId(long retId) {
        this.retId = retId;
    }

    @Basic
    @Column(name = "returnDate")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corId")
    public CoordinatorEntity getCorId(){
        return corId;
    }
    public void setCorId(CoordinatorEntity corId){
        this.corId=corId;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borId")
    public BorrowListEntity getBorId(){
        return borId;
    }
    public void setBorId(BorrowListEntity borId){
        this.borId=borId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnListEntity that = (ReturnListEntity) o;
        return retId == that.retId &&
                Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retId, returnDate);
    }
}
