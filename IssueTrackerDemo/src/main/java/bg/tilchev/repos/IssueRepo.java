package bg.tilchev.repos;

import bg.tilchev.models.Issue;
import bg.tilchev.models.IssueDto;
import bg.tilchev.models.UserDto;

import java.util.List;

public interface IssueRepo {

    boolean persist(IssueDto issue);

    boolean persist(Issue issue);

    List<IssueDto> getIssuesByName(String name);

    Issue getIssueByExactName(String name);

    void closeConnection();
}
