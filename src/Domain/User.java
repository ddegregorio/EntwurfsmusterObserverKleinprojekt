package Domain;

/**
 * Represents a Data tranfer object for the table
 * users from the Database shopkonditoreidb
 */
public class User implements Observer{
    private int u_id;
    private String username;
    private String password;
    private boolean admin;

    public User(String username, String password, boolean admin)
    {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }



    /**
     * This method will be called when articles, on which
     * this user has registered, are set on sale.
     * @param a the article that has been set on sale
     */
    @Override
    public void notify(Article a) {

        System.out.println("Email to " + this.username + " has been sent to inform him about the sale on article \""+ a.getArticleName()+"\".");

    }

    /**
     * Compares this object with the provided object
     * and returns true if the provided object has the same data in
     * the following fields: username, password.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if(o == null)
        {
            return false;
        }

        if(! (o instanceof User)){
            return false;
        }

        User userToCompare = (User) o;
        if((userToCompare.username.equals(this.username))
                && (userToCompare.password.equals(this.password)))
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
        return (username + password).hashCode();
    }
}
