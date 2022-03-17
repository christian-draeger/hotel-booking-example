# Exercise

We already have a repository of hotels.
Hotels can be added (`POST /add`), listed (`GET /all`) and removed (`DELETE /remove/{id}`) via our Rest Endpoints already.

Furthermore, we can add reviews to a certain hotel (`POST /add-review/{id}`)

Similar to the already provided reviews functionality we want to be able to add rooms to a given hotel by its id like this:
`POST /add-room/{id}` and pass room number as body

To be able to do so please extend the Hotel model that it can have multiple rooms.
A room should have a room number and a status (FREE (default), NEEDS_CLEANING, OCCUPIED) (tip have a look at enum types in kotlin).

Afterward please create another endpoint that allows us to change the status of a given hotels room.

!!! please write tests
