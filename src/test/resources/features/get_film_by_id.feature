Feature: Test Dummy Calculator

    Scenario Outline: Send a request to get film by id
        Given the following film
            | id | title                              | description                                                           |
            | 1  | Star Wars: The Empire Strikes Back | Darth Vader is adamant about turning Luke Skywalker to the dark side. |
        And the following Actors
             |id | name     | lastName |
             |1  | Mark     | Hamill   |
             |2  | Harrison | Ford     |


        When the user sends a request to get film by id <id>
        Then the response will return the film

        Examples:

        #film id

        | id|
        | 1 |
