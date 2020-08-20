package test1;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class cart1 implements Serializable {
    private List<ProdEntity> pd=new ArrayList<>();
    public void add(ProdEntity p){
        pd.add(p);
    }

    public void remove(ProdEntity p){
        pd.remove(p);
    }

    public int getCartCount() {
        return pd.size();
    }
}
