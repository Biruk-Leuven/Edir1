package jva_Bean;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "findAllBDLs", query = "Select bd From BorrowDetailEntity bd ORDER BY bd.dno ASC")
@Table(name = "BorrowDetail", schema = "dbEdir", catalog = "")
public class BorrowDetailEntity {
    private long dno;
    private int noOfItems;
    //related tables
    private ItemEntity sno;
    private BorrowListEntity BL;
    //transient property
    private double pricePerItem;
    //not important-only for test purpose
    private double price;
    //optional to work on
    /*@ManyToOne
    @JoinColumn(name="borId")
    private BorrowListEntity BL;*/
    //the borid is already there implicitly bcoz of the r/p

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DNO")
    public long getDno() {
        return dno;
    }

    public void setDno(long dno) {
        this.dno = dno;
    }

    @Basic
    @Column(name = "noOfItems")
    public int getNoOfItems() {
        return noOfItems;
    }
    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serialNo")
    public ItemEntity getSno(){
        return sno;
    }
    public void setSno(ItemEntity sno){
        this.sno=sno;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borId",insertable=false, updatable=false)//owner is this table but access foreign from other not editable by itself
    public BorrowListEntity getBL(){
        return BL;
    }
    public void setBL(BorrowListEntity Bl){
        this.BL=BL;
    }

    @Transient
    public double getPricePerItem() {
        pricePerItem=noOfItems*sno.getBorrowUnitPrice();
        return pricePerItem;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowDetailEntity that = (BorrowDetailEntity) o;
        return dno == that.dno &&
                noOfItems == that.noOfItems &&
                Double.compare(that.price, price) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(dno, noOfItems, price);
    }
}
