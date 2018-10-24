package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Owner {
    private final SimpleIntegerProperty oid  ;
    private final SimpleStringProperty oname;
    private final SimpleStringProperty phno;
    private final SimpleStringProperty address;



    public Owner(Integer oid, String oname, String phno, String address ) {
        this.oid=new SimpleIntegerProperty(oid);
        this.oname=new SimpleStringProperty(oname);
        this.phno=new SimpleStringProperty(phno);
        this.address=new SimpleStringProperty(address);


    }

    public Integer getOid() { return oid.get();
    }

    public String getOname() { return oname.get();
    }

    public String getPhno() { return phno.get();
    }

    public String getAddress() { return address.get();
    }

}
