package Model;

public class Room {
    /** 房间号 */
    private int id;
    /** 房间类型 */
    private String roomType;
    /** 房间状态 */
    private String status;

    public Room(int id, String roomType, String status) {
        this.id = id;
        this.roomType = roomType;
        this.status = status;
    }
    public Room(String roomType, String status) {
        this.roomType = roomType;
        this.status = status;
    }
    public Room(int id, String status) {
        this.status = status;
        this.id = id;
    }


    /** 房间号 */
    public int getId() {
        return this.id;
    }

    /** 房间号 */
    public void setId(int id) {
        this.id = id;
    }

    /** 房间类型 */
    public String getRoomType() {
        return this.roomType;
    }

    /** 房间类型 */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /** 房间状态 */
    public String getstatus() {
        return this.status;
    }

    /** 房间状态 */
    public void setstatus(String status) {
        this.status = status;
    }
}