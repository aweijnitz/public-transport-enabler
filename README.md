# MVV Public Transport REST Webservice
Query the Munich public transportation (MVV) API for upcoming departures for a given bus, U-Bahn or S-Bahn stop.
Uses the Public Transport Enabler library, known from the Öffi App.

## Motivation
The Public Transport Enabler (PTE) library is very generic and a bit unwieldy to work with, so I decided to cut down on complexity and 
wrap it in a REST webservice suited for my needs. This makes writing clients a whole lot easier. It also means it is hardcoded to query the MVV API.

If you are looking for a flexible wrapper to do fuzzy searches and check alternative routes to a destination, please use the PTE library directly, or just 
got to one of the web apps [fahrplanauskunft-efa](http://www.muenchen.de/verkehr/elektronische-auskunft/fahrplanauskunft-efa.html) or 
[mvv-muenche](http://www.mvv-muenchen.de/). The Öffi App and MVV App are also good alternatives.

The service does only two things:

- Provides the upcoming departures for a stop, given the exact id of that stop (for example a bus stop)
- Provides a basic way to find out the exact id of stops in a given area, based on latitude and longitude

See examples below.


This repository relies on two sub projects:

 * [__A REST webservice__](timetogoservice):
     A [SpringBoot REST service](https://spring.io/guides/gs/rest-service/) to query departures in the Munich MVV network.
 * [__The enabler library__](enabler):
     This is an unmodified fork of the [Public Transport Enabler](https://github.com/schildbach/public-transport-enabler), a Java library allowing you to get data from public transport providers
     See the subproject's [README](enabler/README.md) for more information.


## Building and running

`gradle clean build`

`gradle bootRun`

## Example calls

**Query upcoming departures for Plattlinger Straße**
[http://127.0.0.1:8080/departures?stopId=1001403](http://127.0.0.1:8080/departures?stopId=1001403)

**Query available stops within a given radius in meters**
The coordinates are latitude and longitude multiplied by 1000000
[http://127.0.0.1:8080/near-point?lat=48078804&long=11512663&range=500&maxResults=10](http://127.0.0.1:8080/near-point?lat=48078804&long=11512663&range=100&maxResults=10)

See [AppRouter.java](timetogoservice/src/main/java/awe/AppRouter.java) for more details.