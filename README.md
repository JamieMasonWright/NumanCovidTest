Numan Technical Test
================
Your task, if you choose to accept it, is to build an app that helps a user view COVID vaccination 
data for any country using this API. How long you take to complete this task is totally up to you 
but you should aim to spend no more than 6 hours.

Requirements
- The app should allow the user to be able to browse all the countries returned from the endpoint 
  (GET /vaccines) and view their vaccination data.
- The user should be able to “favorite” countries to make them quickly accessible.
- The user should be able to “unfavorite” countries.
- Favorited countries should be persisted so that they stay favorited between app restarts.
  What data you use and how you display it is up to you.
  What we’ll be looking for is
- A functioning app with tidy, production worthy code.
- An excellent understanding of good app architecture.
- Good test coverage.
- A well thought out user experience.
  Instructions
- Use the repo that you were invited to, to push your project.
- Create a README with an overview of your design and technical decisions.

Architecture
==========
The separation of the code in MVVM is divided into View, ViewModel and Model:

This architecture has been chose due to its level of separation between concerns and its ability
to be more scalable and maintainable if components are required to change.

View is the collection of visible elements, which also receives user input. This includes user
interfaces (UI), animations and text. The content of View is not interacted with directly to change 
what is presented. ViewModel is located between the View and Model layers. This is where the 
controls for interacting with View are housed, while binding is used to connect the UI elements in 
View to the controls in ViewModel. Model houses the logic for the program, which is retrieved by the
ViewModel upon its own receipt of input from the user through View.

Libraries Used
==========

Room
-------
Room is a database layer on top of an SQLite database and takes care of mundane tasks that used to 
be handled with an SQLiteOpenHelper. By default, to avoid poor UI performance, Room doesn't allow 
you to issue queries on the main thread. When Room queries return LiveData, the queries are 
automatically run asynchronously on a background thread. Room provides compile-time checks of SQLite 
statements.

Retrofit
--------
Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload 
JSON (or other structured data) via a REST based webservice. In Retrofit you configure which 
converter is used for the data serialization. Typically for JSON you use GSon, but you can add 
custom converters to process XML or other protocols. Retrofit uses the OkHttp library for HTTP 
requests.

Coroutines
-------
On Android, coroutines help to manage long-running tasks that might otherwise block the main thread
and cause the app to become unresponsive. Coroutines can be useful when a system performs two or 
more tasks that would be most naturally described as a series of long-running steps that involve a 
lot of waiting.






