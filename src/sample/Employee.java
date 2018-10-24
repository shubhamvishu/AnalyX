package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private final SimpleIntegerProperty eid  ;
    private final SimpleStringProperty ename;
    private final SimpleStringProperty phno  ;
    private final SimpleStringProperty address  ;
    private final SimpleIntegerProperty sal  ;
    private final SimpleIntegerProperty up  ;
    private final SimpleIntegerProperty down  ;
    private final SimpleIntegerProperty  sid;
    private final SimpleStringProperty doj;


    public Employee(Integer eid, String ename, String phno,String address,Integer sal,Integer up,Integer down,Integer sid,String doj) {

        this.eid=new SimpleIntegerProperty(eid);
        this.ename=new SimpleStringProperty(ename);
        this.phno=new SimpleStringProperty(phno);
        this.address=new SimpleStringProperty(address);
        this.sal=new SimpleIntegerProperty(sal);
        this.up=new SimpleIntegerProperty(up);
        this.down=new SimpleIntegerProperty(down);
        this.sid=new SimpleIntegerProperty(sid);
        this.doj=new SimpleStringProperty(doj);

    }

    public Integer getEid() { return eid.get();
    }

    public String getEname() { return ename.get();
    }

    public String getPhno() { return phno.get();
    }

    public String getAddress() { return address.get();
    }

    public Integer getSal() { return sal.get();
    }

    public Integer getUp() { return up.get();
    }

    public Integer getDown() { return down.get();
    }

    public Integer getSid() { return sid.get();
    }

    public String getDoj() { return doj.get();
    }
}
