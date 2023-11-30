package Models.Entities;

public class Bucket {
    private int id;
    private int ownerId;
    private int itemId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" + "," +
                "\"ownerId\":" + "\"" + ownerId + "\"" + "," +
                "\"itemId\":" + "\"" + itemId + "\"" +
                "}";
    }

}