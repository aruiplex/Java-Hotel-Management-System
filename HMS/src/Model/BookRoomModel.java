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
    private Date startTime;
    /** 入住时长 */
    private int lastTime;

    private String roomType;
    // booking status, 0 is passed (and invaild) booking, 1 is vaild booking.
    private int status;

    public BookRoomModel(Integer id, int roomId, Integer guestId, Date startTime, int lastTime) {
        this.id = id;
        this.roomId = roomId;
        this.guestId = guestId;
        this.startTime = startTime;
        this.lastTime = lastTime;
    }
    public BookRoomModel( String roomType, Integer guestId, Date startTime, int lastTime) {
        this.roomType = roomType;
        this.guestId = guestId;
        this.startTime = startTime;
        this.lastTime = lastTime;
    }
    public BookRoomModel( int roomId, Integer guestId, Date startTime, int lastTime) {
        this.roomId = roomId;
        this.guestId = guestId;
        this.startTime = startTime;
        this.lastTime = lastTime;
    }
    public BookRoomModel() {

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

    public BookRoomModel startTime(Date startTime) {
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


    /** 订单号 */
    public Integer getId() {
        return this.id;
    }

    /** 订单号 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 房间号 */
    public int getRoomId() {
        return this.roomId;
    }

    /** 房间号 */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /** 顾客号 */
    public Integer getGuestId() {
        return this.guestId;
    }

    /** 顾客号 */
    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    /** 开始时间 */
    public Date getStartTime() {
        return this.startTime;
    }

    /** 开始时间 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /** 入住时间 */
    public int getLastTime() {
        return this.lastTime;
    }

    /** 入住时间 */
    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }


    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
}