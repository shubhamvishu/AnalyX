package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private final SimpleIntegerProperty pid  ;
    private final SimpleStringProperty pname;
    private final SimpleIntegerProperty pcost;
    private final SimpleIntegerProperty profit;
    private final SimpleIntegerProperty catid;


    public Product(Integer pid, String pname,Integer pcost,Integer profit,Integer catid) {
        System.out.println("here CID="+pid);
        this.pid=new SimpleIntegerProperty(pid);
        this.pname=new SimpleStringProperty(pname);
        this.pcost=new SimpleIntegerProperty(pcost);
        this.profit=new SimpleIntegerProperty(profit);
        this.catid=new SimpleIntegerProperty(catid);

    }

    public Integer getPid() { return pid.get();
    }

    public String getPname() { return pname.get();
    }

    public Integer getPcost() { return pcost.get();
    }
    public Integer getProfit() { return profit.get();
    }
    public Integer getCatid() { return catid.get();
    }


}
