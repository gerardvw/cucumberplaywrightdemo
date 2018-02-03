Feature: search

    @chrome
    Scenario Outline: search for t-shirts
    Given homepage is opened
    When I search for <searchterm>
    And I choose to view the search results for being viewed in a list
    Then I should see an item with description <description> and a price of <price>
    And this item should be <availability>
    And it should be possible to add this item to my cart

    Examples:
    | searchterm | description                 | price   | availability |
    | t-shirts   | Faded Short Sleeve T-shirts | $16.51  | In stock     |

