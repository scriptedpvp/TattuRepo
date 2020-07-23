Feature: Jira creation Bugs

  Scenario Outline: Jira bug post
    Given the user creates jiraStroy by providing "<summary>","<description>", "<issueType>"
    When the user verify key, "<summary>", "<description>"
    Then the user get key, "<summary>","<description>"
    Examples:
      | summary                    | description                      | issueType |
      | bug creation api           | creating a bug from api          | Bug       |
      | ApiTest                    | trying to post from API          | Bug       |
      | Jira bug hasnt been posted | unable to post Jira bug from api | Bug       |
      | Jira Bug                   | creating a buG                   | Bug       |
      | Bug is money               | fixing Bug                       | Bug       |



