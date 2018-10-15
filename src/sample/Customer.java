package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private final SimpleIntegerProperty cid  ;
    private final SimpleStringProperty cname;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phno;


    public Customer(int cid, String cname, String email, String phno) {
        System.out.println("here CID="+cid);
        this.cid=new SimpleIntegerProperty(cid);
        this.cname=new SimpleStringProperty(cname);
        this.email=new SimpleStringProperty(email);
        this.phno=new SimpleStringProperty(phno);

    }

    public Integer getCid() { return cid.get();
    }

    public String getCname() { return cname.get();
    }

    public String getEmail() { return email.get();
    }

    public String getPhno() { return phno.get();
    }


}
