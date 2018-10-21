package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Buy {
    private final SimpleIntegerProperty cid;
    private final SimpleIntegerProperty pid;
    private final SimpleIntegerProperty sid;
    private final SimpleStringProperty dop;
    private final SimpleIntegerProperty qty;



    public Buy(Integer cid,Integer pid,Integer sid,String dop,Integer qty ) {
        System.out.println("here CID="+cid);
        this.cid=new SimpleIntegerProperty(cid);
        this.pid=new SimpleIntegerProperty(pid);
        this.sid=new SimpleIntegerProperty(sid);
        this.dop=new SimpleStringProperty(dop);
        this.qty=new SimpleIntegerProperty(qty);
        System.out.println("here CID="+cid);

    }

    public Integer getCid() { return cid.get();
    }
    public Integer getPid() { return pid.get();
    }
    public Integer getSid() { return sid.get();
    }
    public String getDop() { return dop.get();
    }
    public Integer getQty() { return qty.get();
    }

}
