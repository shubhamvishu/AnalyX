package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Category {
    private final SimpleIntegerProperty catid  ;
    private final SimpleStringProperty catname;
    private final SimpleStringProperty desp;


    public Category(Integer catid, String catname, String desp) {
        System.out.println("here CID="+catid);
        this.catid=new SimpleIntegerProperty(catid);
        this.catname=new SimpleStringProperty(catname);
        this.desp=new SimpleStringProperty(desp);

    }

    public Integer getCatid() { return catid.get();
    }

    public String getCatname() { return catname.get();
    }

    public String getDesp() { return desp.get();
    }



}
