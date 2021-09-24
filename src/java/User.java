

import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="userInfo")
public class User {
    @Id
    @Column(name="uaadhar")
    private String aadhar;
    @Column(name="uname")
    private String name;
    @Column(name="umobile")
    private String mobile;
    @Column(name="uemail")
    private String email;
    @Column(name="upassword")
    private String password;

    
    
    public String getPassword(){
    return password;
    }
    public void setPassword(String password){
    this.password=password;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}//class
