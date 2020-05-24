package Model;

public class Room {
    /** 房间号 */
    private int id;
    /** 房间类型 */
    private String roomType;
    /** 房间状态 */
    private int status;

    public Room(int id, String roomType, int status) {
        this.id = id;
        this.roomType = roomType;
        this.status = status;
    }
    public Room(String roomType, int status) {
        this.roomType = roomType;
        this.status = status;
    }
    public Room(int id, int status) {
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
    public int getstatus() {
        return this.status;
    }

    /** 房间状态 */
    public void setstatus(int status) {
        this.status = status;
    }
}