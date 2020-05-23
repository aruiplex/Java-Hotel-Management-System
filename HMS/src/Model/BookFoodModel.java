package Model;

public class BookFoodModel {
    /** 订单号 */
    private Integer id;
    /** 食物号 */
    private Integer foodId;
    private Integer guestId;
    private String foodName;
    private String foodTime;
    /** 是否上菜 */
    private String status;

    public BookFoodModel(Integer id, Integer foodId, String foodTime, String status) {
        this.foodTime = foodTime;
        this.id = id;
        this.foodId = foodId;
        this.status = status;
    }

    public BookFoodModel() {
    }

    public BookFoodModel id(Integer id) {
        this.id = id;
        return this;
    }

    public BookFoodModel foodId(Integer foodId) {
        this.foodId = foodId;
        return this;
    }

    public BookFoodModel guestId(Integer guestId) {
        this.guestId = guestId;
        return this;
    }

    public BookFoodModel foodName(String foodName) {
        this.foodName = foodName;
        return this;
    }

    public BookFoodModel foodTime(String foodTime) {
        this.foodTime = foodTime;
        return this;
    }

    public BookFoodModel status(String status) {
        this.status = status;
        return this;
    }

    public Integer getGuestId() {
        return this.guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodId() {
        return this.foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodTime() {
        return this.foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}