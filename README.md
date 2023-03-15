

- I used Hexgaonal architecture with Domain Driven Design
- Aggregate roots: Customer and Book
- Value objects: EuroMoney, CustomerId, BookId, LoyaltyPoints, Percentage, BookType's
- I used Joda Money to represent Customer balance and Book price but its wrapped with VO EuroMoney
- Using Swagger, Loggs and Unit tests
- As persistence layer i have H2 Database with Hibernate And Spring JPA
- There could be some refactoring of variable names, maybe even code design but as requirements are not fully defined i had freedom to design simple as possible


How tu run application?
Just press run in your favorite IDE, if you want to change data use `resource/data-h2.sql` file

How to use application?
There is 3 endpoints as stated in task
- To get Customer details use `localhost:8080/api/v1/customers/{customer-id}`
- To get All books details use `localhost:8080/api/v1/books`
- To create payment use `localhost:8080/api/v1/customers/{customer-id}/checkout?booksId={book-id},{book-id}`
- Swagger link `localhost:8080/swagger-ui.html`