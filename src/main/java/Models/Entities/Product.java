package Models.Entities;

public class Product {
    private int id;
    private String productTitle;
    private String productCost;
    private String productDescription;
    private String productCategory;
    private String productImgUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return productTitle;
    }

    public void setTitle(String productName) {
        this.productTitle = productName;
    }

    public String getCost() {
        return productCost;
    }

    public void setCost(String productCost) {
        this.productCost = productCost;
    }

    public String getDescription() {
        return productDescription;
    }

    public void setDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCategory (){
        return productCategory;
    }
    public void setCategory(String productCategory){
        this.productCategory = productCategory;
    }
    public String getUrl (){
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +","+
                "\"title\":" + "\"" + productTitle +"\"" +","+
                "\"cost\":" + "\"" + productCost + "\"" +","+
                "\"description\":" + "\"" + productDescription  + "\"" + "," +
                "\"category\":" + "\"" + productCategory  + "\"" + "," +
                "\"Img\":" + "\"" + productImgUrl  + "\"" +
                "}";
    }
}