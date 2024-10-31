Feature: search

    Background:
        Given products page is opened

    @chrome @msedge
    Scenario Outline: search for item in searchbox
    When I search for <searchterm>
    Then I should see an item with description <description> and a price of <price>
    And it should be possible to add this item to my cart

    Examples:
    | searchterm | description                   | price    |
    | t-shirt    | Pure Cotton V-Neck T-Shirt    | Rs. 1299 |
    | cotton     | Pure Cotton Neon Green Tshirt | Rs. 850  |



