Feature: Search and Place the order for Product

Scenario: Search experience for product search in both home and Offers page

Given User is on GreenCart Landing Page
When  User searched with shortname "Tom" and extracted actual name of product
Then  User searched for "Tom" shortname in offers page 
And validate product name in offers page matches with product name in Landing Page


