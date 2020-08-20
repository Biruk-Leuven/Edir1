package jva_Bean;

import javax.persistence.*;
import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
//@NamedQuery(name = "findAllBLs", query = "Select bl.borId,bl.issueDate,bl.noOfDays,bl.reason,bl.status,bl.memId,bl.corId From BorrowListEntity bl,MemberEntity m,CoordinatorEntity c where (m.userId=bl.memId.userId) and (c.userId=bl.corId.userId) ORDER BY bl.borId ASC")
@NamedQuery(name = "findAllBLs", query = "Select bl From BorrowListEntity bl ORDER BY bl.borId ASC")
@Table(name = "BorrowList", schema = "dbEdir", catalog = "")
public class BorrowListEntity {
    private long borId;
    private Date issueDate;
    private int noOfDays;
    private String reason;
    private String status;
    private Date deliveryDate;

    private MemberEntity memId;
    private CoordinatorEntity corId;
    private List<BorrowDetailEntity> bod=new ArrayList<>();


    //transient
    private int noRemainingDays;


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borId")
    public long getBorId() {
        return borId;
    }

    public void setBorId(long borId) {
        this.borId = borId;
    }

    @Basic
    @Column(name = "issueDate")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Basic
    @Column(name = "noOfDays")
    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "deliveryDate")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memId",nullable = false)
    public MemberEntity getMemId(){
        return memId;
    }
    public void setMemId(MemberEntity memId)
    {this.memId=memId;}
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corId")
    public CoordinatorEntity getCorId(){
        return corId;
    }
    public void setCorId(CoordinatorEntity corId){
        this.corId=corId;
    }
   //@OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true,fetch = FetchType.LAZY)//think fetch?
    //@OneToMany(mappedBy = "borrowListEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    //@OneToMany
    @OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true,fetch = FetchType.LAZY)//think fetch?
    @JoinColumn(name = "borId")
    public List<BorrowDetailEntity> getBod(){
        return bod;
    }
    public void setBod(List<BorrowDetailEntity> bod){
        this.bod=bod;
    }
    public void addBorrowList(BorrowDetailEntity bd){
        bod.add(bd);
        bd.setBL(this);
    }
    public void removeBorrowList(BorrowDetailEntity bd){
        bod.remove(bd);
        bd.setBL(null);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowListEntity that = (BorrowListEntity) o;
        return borId == that.borId &&
                noOfDays == that.noOfDays &&
                Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(status, that.status)&&
                Objects.equals(deliveryDate, that.deliveryDate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(borId, issueDate, noOfDays, reason, status,deliveryDate);
    }
    @Transient
    public int getNoRemainingDays() {
            if(status.equalsIgnoreCase("Delivered")) {
                java.util.Date today = new java.util.Date();
                noRemainingDays=deliveryDate.getDate()-today.getDate()+noOfDays;
               // java.util.Date today = new java.util.Date();
                //noRemainingDays = today.getDate() - deliveryDate.getDate();
            }
        return noRemainingDays;
    }


}
