package Model;

import java.util.Date;

public class Stuff {
    /** id;员工id */
    private Integer id;
    /** name;员工名字 */
    private String name;
    /** password;员工密码 */
    private String password;
    /** telephone_number;telephone_number */
    private String telephoneNumber;
    /** signUpTime;signUpTime */
    private Date updatedTime;

    public Stuff(Integer id, String name, String password, String telephoneNumber, Date updatedTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.updatedTime = updatedTime;
    }

    /** id;员工id */
    public Integer getId() {
        return this.id;
    }

    /** id;员工id */
    public void setId(Integer id) {
        this.id = id;
    }

    /** name;员工名字 */
    public String getName() {
        return this.name;
    }

    /** name;员工名字 */
    public void setName(String name) {
        this.name = name;
    }

    /** password;员工密码 */
    public String getPassword() {
        return this.password;
    }

    /** password;员工密码 */
    public void setPassword(String password) {
        this.password = password;
    }

    /** telephone_number;telephone_number */
    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    /** telephone_number;telephone_number */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /** signUpTime;signUpTime */
    public Date getUpdatedTime() {
        return this.updatedTime;
    }

    /** signUpTime;signUpTime */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}