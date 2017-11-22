package Infrastructure;

import ASL.UserArticleMappingDAO;
import Domain.UserArticleMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserArticleMappingImplTest {
    UserArticleMappingImpl uaDAO;
    UserArticleMapping ua;

    @BeforeEach
    void setUp() {
        uaDAO = new UserArticleMappingImpl();
        ua = findUserArticleMappingThatDoesNotExistInDB();
    }

    /**
     * Tries to find a userArticlieMapping that does not
     * exist in the Database
     *
     * @return UserArticleMapping that does not exist in the Database
     * null if it could not find a UserArticleMapping that does not
     * exist in the Database
     *
     */
    private UserArticleMapping findUserArticleMappingThatDoesNotExistInDB(){
        UserArticleMapping ua;
        List<UserArticleMapping> uaList = uaDAO.readAllUserArticleMappings();
        boolean doesExist = false;

        // try three times
        for(int i=0; i<3; i++){
            ua = new UserArticleMapping(3000+i, 3000+i);

            // Check if this UserArticleMapping exists in the Database
            for(int j=0; j<uaList.size(); j++){
                if(ua.getU_id() == uaList.get(j).getU_id()
                        && ua.getA_id() == uaList.get(j).getA_id()){
                    doesExist = true;
                }
            }

            if(!doesExist){
                return ua;
            }
        }

        return null;
    }

    @Test
    void createNewUserArticleMapping() {
        // Run test only if we have found
        // a userArticleMapping that does not exist in the Database
        if(ua != null) {
            uaDAO.createNewUserArticleMapping(ua);
            boolean uaFoundInDB = false;

            // Check if the returned list from the Database
            // does contain our object that we have inserted before
            for(UserArticleMapping uaFromDB : uaDAO.readAllUserArticleMappings())
            {
                if(uaFromDB.getU_id() == ua.getU_id()
                        && uaFromDB.getA_id() == ua.getA_id()){
                    uaFoundInDB = true;
                }
            }

            assertTrue(uaFoundInDB);

            uaDAO.deleteUserArticleMapping(ua.getU_id(), ua.getA_id());
        }
        else{
            assertFalse(true);
        }

    }

    @Test
    void readAllUserArticleMappings() {
        if(ua != null){
            System.out.println("Hello world");
            List<UserArticleMapping> uaList1 = uaDAO.readAllUserArticleMappings();

            uaDAO.createNewUserArticleMapping(ua);

            // List that has one UserArticleMapping more
            List<UserArticleMapping> uaList2 = uaDAO.readAllUserArticleMappings();

            for(UserArticleMapping ua1 : uaList1){

                // the second list must have at least all the
                // UserArticleMappings from the first list
                if(! uaList2.contains(ua1)){
                    assertFalse(true);
                }
            }

            // Plus the one we have inserted before
            assertTrue(uaList2.contains(ua));

            uaDAO.deleteUserArticleMapping(ua.getU_id(), ua.getA_id());
        }
        else{
            assertFalse(true);
        }
    }

}