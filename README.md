# Marvel API Client
Client application to extract some data from the Marvel API service and enrich it with some Marvel Wiki data. I use the data for demos as superhero data is a lot more exciting than business data.

To read up about how the Marvel API works go [here](http://developer.marvel.com "Marvel Developer Pages")

You are going to need to get your own public and private key to use the client.

##Things to improve
1. Running single threaded at the moment.
2. The Marvel API is flaky and returns 500 errors often, need to add a automatic way of handling it.
3. Writing backup files to make up for the flakiness, makes it slow and forces it to run single threaded. Need a better way to handle it.
4. Make the data being extracted configurable.
5. Add support for date values.
6. Make the data transformation plugable.

