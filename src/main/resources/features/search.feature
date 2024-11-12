Feature: search

    As a customer
    I want be able to search for products
    So that I can order the ones I like

    @chrome @msedge
    Scenario Outline: search for a product
    Given products page is opened
    When I search for <searchterm>
    Then I should see an item with description <description> and a price of <price>
    And it should be possible to add this item to my cart

    Examples:
    | searchterm | description                   | price    |
    | t-shirt    | Pure Cotton V-Neck T-Shirt    | Rs. 1299 |
#    | cotton     | Pure Cotton Neon Green Tshirt | Rs. 850  |

    @chrome @msedge
    Scenario Outline: add a product to shopping cart
    Given products page is opened
    When I add a product with <description> and a price of <price> to my shopping cart
    Then This product should be available in my shopping cart

    Examples:
        | description                   | price    |
        | Pure Cotton V-Neck T-Shirt    | Rs. 1299 |
