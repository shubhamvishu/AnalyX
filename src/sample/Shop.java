package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Shop {
    private final SimpleIntegerProperty sid  ;
    private final SimpleStringProperty sname;
    private final SimpleIntegerProperty rev;
    private final SimpleIntegerProperty catid;


    public Shop(Integer sid, String sname, Integer rev,Integer catid) {
        System.out.println("here CID="+catid);
        this.sid=new SimpleIntegerProperty(sid);
        this.sname=new SimpleStringProperty(sname);
        this.rev=new SimpleIntegerProperty(rev);
        this.catid=new SimpleIntegerProperty(catid);

    }

    public Integer getSid() { return sid.get();
    }

    public String getSname() { return sname.get();
    }

    public Integer getRev() { return rev.get();
    }

    public Integer getCatid() { return catid.get();
    }



}
