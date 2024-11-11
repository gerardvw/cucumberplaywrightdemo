Feature: searchapi

    As a customer
    I want be able to search for products
    So that I can order the ones I like

    @api
    Scenario Outline: search for a product by api
#       Given
        When I search by api for <searchterm>
        Then I should get an item with description <description> and a price of <price>

        Examples:
            | searchterm | description                   | price    |
            | t-shirt    | Pure Cotton V-Neck T-Shirt    | Rs. 1299 |
