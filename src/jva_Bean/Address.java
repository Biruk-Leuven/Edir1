package jva_Bean;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address  {


        private String subCity;
        private int houseNo;

        public String getSubCity() {
            return subCity;
        }

        public void setSubCity(String subCity) {
            this.subCity = subCity;
        }

        public int getHouseNo() {
            return houseNo;
        }

        public void setHouseNo(int houseNo) {
            this.houseNo = houseNo;
        }
    }

