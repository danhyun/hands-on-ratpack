import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    /*
    * TODO register DefaultBookService as the implementation of BookService in the registry
    *
    * Take a look at:
    * `ratpack.groovy.handling.GroovyChain#register(registry)`
    * `ratpack.registry.Registries#just(object)`
    */

    prefix("book/:isbn") {
      /*
      * TODO add your new common handler here
      *
      * Take a look at:
      * `ratpack.handling.Context#next(registry)`
      */

      get("title") {
        // Objects added to the registry by upstream handlers are available via type-lookup
        BookService bookService = context.get(BookService)

        //TODO refactor this into a common handler for this chain
        long isbn = allPathTokens.asLong("isbn")
        Book b = bookService.getBook(isbn)

        response.send b.title
      }

      get("author") { BookService bookService -> // Registry objects can also be "injected" into handler closures
        //TODO refactor this into a common handler for this chain
        long isbn = allPathTokens.asLong("isbn")
        Book b = bookService.getBook(isbn)

        response.send b.author
      }
    }
  }
}
