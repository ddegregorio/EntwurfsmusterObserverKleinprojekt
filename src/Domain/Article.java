package Domain;

/**
 * Represents a Data tranfer object for the table
 * articles from the Database shopkonditoreidb
 */
public class Article extends ArticleOnSale{
    private int a_id;
    private String articleName;
    private double price;
    private boolean discount;

    public Article(int a_id, String articleName, double price, boolean discount)
    {
        this.a_id = a_id;
        this.articleName = articleName;
        this.price = price;
        this.discount = discount;
    }

    public void setA_id(int a_id){
        this.a_id = a_id;
    }

    public int getA_id() {
        return a_id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return discount;
    }

    /**
     * Sets the discount for the article to true
     * and notifies users that are interested.
     * @param discount
     */
    public void setDiscount(boolean discount) {
        this.discount = discount;

        // notify user if the discount has been
        // set to true
        if(discount){
            notifyObservers(this);
        }
    }

    /**
     * Compares this object with the provided object
     * and returns true if the provided object has the same data in
     * the following fields: a_id, articleName, price, discount.
     *
     * @param o object that should be compared
     * @return true if equal, false if not.
     */
    @Override
    public boolean equals(Object o){
        if(o == null)
        {
            return false;
        }

        if(! (o instanceof Article)){
            return false;
        }

        Article articleToCompare = (Article) o;
        if(articleToCompare.a_id == this.a_id
                && articleToCompare.articleName.equals(this.articleName)
                && articleToCompare.price == this.price
                && articleToCompare.discount == this.discount)
        {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the hashCode of this object
     *
     * @return hashCode of this object
     */
    @Override
    public int hashCode()
    {
        return (articleName + price + discount).hashCode();
    }

    /**
     * Returns a String representation of this object
     * @return String representation of this object
     */
    @Override
    public String toString()
    {
        return String.format("%1$4s", a_id)+"\t\t" + String.format("%1$34s", articleName) + "\t\t\t" + price + "\t\t\t" + discount;
    }
}