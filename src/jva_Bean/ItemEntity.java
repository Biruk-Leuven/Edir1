package jva_Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "findAllItems", query = "Select it From ItemEntity it ORDER BY it.name DESC")
@Table(name = "Item", schema = "dbEdir", catalog = "")
public class ItemEntity {
    private long serialNo;
    private String category;
    private String name;
    private Double borrowUnitPrice;
    private Integer noOfItems;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serialNo")
    public long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(long serialNo) {
        this.serialNo = serialNo;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "borrowUnitPrice")
    public Double getBorrowUnitPrice() {
        return borrowUnitPrice;
    }

    public void setBorrowUnitPrice(Double borrowUnitPrice) {
        this.borrowUnitPrice = borrowUnitPrice;
    }

    @Basic
    @Column(name = "noOfItems")
    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return serialNo == that.serialNo &&
                Objects.equals(category, that.category) &&
                Objects.equals(name, that.name) &&
                Objects.equals(borrowUnitPrice, that.borrowUnitPrice) &&
                Objects.equals(noOfItems, that.noOfItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNo, category, name, borrowUnitPrice, noOfItems);
    }
}
