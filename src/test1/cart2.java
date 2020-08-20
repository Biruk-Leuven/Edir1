package test1;

import javax.ejb.Local;

@Local
public interface cart2 {
    void addProductToCart(ProdEntity product);

    void checkOut();
}
