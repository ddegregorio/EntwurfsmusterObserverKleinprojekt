package ASL;

import Domain.User;
import Domain.UserArticleMapping;

import java.util.List;

/**
 * Represents an interface for the Data access object
 * which is used to retrieve UserArticleMapping objects from
 * the Database
 */
public interface UserArticleMappingDAO {

    void createNewUserArticleMapping(UserArticleMapping ua);

    List<UserArticleMapping> readAllUserArticleMappings();

    void deleteUserArticleMapping(int u_id, int a_id);
}
