package Model;

import java.util.Date;

public class BookRoomModel {
    /** 订单号 */
    private Integer id;
    /** 房间号 */
    private int roomId;
    /** 顾客号 */
    private Integer guestId;
    /** 开始时间 */
    private Date startTimeDate;
    private String startTime;
    /** 入住时长 */
    private int lastTime;

    private String roomType;
    // booking status, 0 is passed (and invaild) booking, 1 is vaild booking.
    private int status;

    public BookRoomModel() {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Integer getGuestId() {
        return this.guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public Date getStartTimeDate() {
        return this.startTimeDate;
    }

    public void setStartTimeDate(Date startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BookRoomModel id(Integer id) {
        this.id = id;
        return this;
    }

    public BookRoomModel roomId(int roomId) {
        this.roomId = roomId;
        return this;
    }

    public BookRoomModel guestId(Integer guestId) {
        this.guestId = guestId;
        return this;
    }

    public BookRoomModel startTimeDate(Date startTimeDate) {
        this.startTimeDate = startTimeDate;
        return this;
    }

    public BookRoomModel startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public BookRoomModel lastTime(int lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public BookRoomModel roomType(String roomType) {
        this.roomType = roomType;
        return this;
    }

    public BookRoomModel status(int status) {
        this.status = status;
        return this;
    }

}