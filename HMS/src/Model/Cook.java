package Model;

public class Cook {
    private Integer id;
    /** 名字 */
    private String name;

    public Cook(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /** 厨师号 */
    public Integer getId() {
        return this.id;
    }

    /** 厨师号 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 名字 */
    public String getName() {
        return this.name;
    }

    /** 名字 */
    public void setName(String name) {
        this.name = name;
    }
}