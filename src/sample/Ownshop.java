package sample;

import javafx.beans.property.SimpleIntegerProperty;

public class Ownshop {
    private final SimpleIntegerProperty oid  ;
    private final SimpleIntegerProperty sid;



    public Ownshop(Integer oid, Integer sid ) {
        this.oid=new SimpleIntegerProperty(oid);
        this.sid=new SimpleIntegerProperty(sid);
    }

    public Integer getOid() { return oid.get();
    }

    public Integer getSid() { return sid.get();
    }
}
