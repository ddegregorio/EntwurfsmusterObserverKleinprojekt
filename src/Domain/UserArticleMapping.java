package Domain;

/**
 * Represents a Data tranfer object for the table
 * userarticlemappings from the Database shopkonditoreidb
 */
public class UserArticleMapping {
    private int u_id;
    private int a_id;

    public UserArticleMapping(int u_id, int a_id) {
        this.u_id = u_id;
        this.a_id = a_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }
}
