package Models.Entities;

public class Product {
    private int id;
    private String title;
    private String cost;
    private String description;
    private String category;
    private String imgUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Name) {
        this.title = Name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String Cost) {
        this.cost = Cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    public String getCategory (){
        return category;
    }
    public void setCategory(String Category){
        this.category = Category;
    }
    public String getUrl (){
        return imgUrl;
    }

    public void setProductImgUrl(String ImgUrl) {
        this.imgUrl = ImgUrl;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +","+
                "\"title\":" + "\"" + title +"\"" +","+
                "\"cost\":" + "\"" + cost + "\"" +","+
                "\"description\":" + "\"" + description  + "\"" + "," +
                "\"category\":" + "\"" + category  + "\"" + "," +
                "\"Img\":" + "\"" + imgUrl  + "\"" +
                "}";
    }
}