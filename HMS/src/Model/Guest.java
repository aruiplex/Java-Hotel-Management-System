package Model;

import java.util.Date;

public class Guest {
    /** user ID */
    private Integer id;
    /** user unreal name */
    private String name;
    /** user real name */
    private String realname;
    /** user 密码 */
    private String password;
    /** 护照号 */
    private String passportId;
    /** 创建时间 */
    private Date signUpTime;

    public Guest(Integer id, String name, String realname, String password, String passportId, Date signUpTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.passportId = passportId;
        this.signUpTime = signUpTime;
        this.realname = realname;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    /** user ID */
    public Integer getId() {
        return this.id;
    }

    /** user ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** user 姓名 */
    public String getName() {
        return this.name;
    }

    /** user 姓名 */
    public void setName(String name) {
        this.name = name;
    }

    /** user 密码 */
    public String getPassword() {
        return this.password;
    }

    /** user 密码 */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 护照号 */
    public String getPassportId() {
        return this.passportId;
    }

    /** 护照号 */
    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    /** 创建时间 */
    public Date getSignUpTime() {
        return this.signUpTime;
    }

    /** 创建时间 */
    public void setSignUpTime(Date signUpTime) {
        this.signUpTime = signUpTime;
    }
}