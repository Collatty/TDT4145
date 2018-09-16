# Databaseprosjekt 

_Oscar Carl Vik, Bjørn Løvland Manheim,  Håkon Collett Bjørgan_

#### Litt om appen

Applikasjonen vår er skrevet i Java. Databasen i MySQL. Håndteringen dem i mellom skjer ved hjelp av [JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)  Brukergrensesnittet er bygget med SceneBuilder og hånderes av applikasjonen gjennom [JavaFX](https://docs.oracle.com/javase/8/javafx/api/toc.htm).

Applikasjonen kjøres ved å kjøre `ApplicationDemo.main()`. Databasen er tilgjengelig gjennom phpMyAdmin på NTNU sine servere - mysqladmin.ntnu.it.no/...


#### Use Cases

Vi har løst følgende use cases:

  * Kåre vil logge benkpressøkten sin.
    * Dette er løst ved å legge inn funksjonalitet i appliksjonen som lar Kåre opprette benkpress som øvelse. Opprette en ny treningsøkt, hvor han kan legge inn benkpress som en av øvelsene i økten sin.
  * Hilde vil vite om flere øvelser for gitte muskelgrupper.
    * Dette er løst ved å legge inn funksjonalitet som gjør at hilde kan se en oversikt over lagrede øvelsesgrupper, trykke på ønsket gruppe og få en oversikt over tilhørende øvelser.


#### Kode

I prosjektet vårt finner man følgende klasser:

(...)src/
 
  * `DatabaseConnector`
    * Metoder for å utføre spørringer til og oppdateringer av databasen. 
  * `JDBCExample`
    * Eksempelklasse for bruk av JDBC. Trengs ikke i vårt prosjekt.
  * `OvelseIOkt`
    * Muliggjør å opprette objekter av typen `OvelseIOkt`. Brukes blant annet i `OktCOntroller`
  * `Treningsøkt`
    *  Muliggjør å opprette objekter av typen `Treningsøkt`. Brukes blant annet i `ovelseHistorikkController`.

(...)ui/

  * `ApparatController`
    * Forbinder `Apparat.fxml` med `DatabaseConnector` og sørger for at riktige metoder kalles ved riktig interaksjon fra bruker.
  * `Database`
    * Overflødig klasse
  * `homeScreenController`
    * Styrer hjemskjermer og redirecter til riktig skjerm på de forskjellige knappene.
  * `OktController`
    * Forbinder `Okt.fxml` med `DatabaseConnector` og sørger for at riktige metoder kalles ved riktig interaksjon fra bruker.
  * `OvelseController`
    * Forbinder `Ovelse.fxml` med `DatabaseConnector` og sørger for at riktige metoder kalles ved riktig interaksjon fra bruker.
  * `OvelseGruppeController`
    * Forbinder `OvelseGruppeController.fxml` med `DatabaseConnector` og sørger for at riktige metoder kalles ved riktig interaksjon fra bruker.
  * `ovelseHistroikkController`
    * Forbinder `ovelseHistorikk.fxml` med `DatabaseConnector` og sørger for at riktige metoder kalles ved riktig interaksjon fra bruker.
  * `ScreensController`
    * Handles the different screens and holds `DatabaseConnector`.
  * `SokOvelseGruppeController`
    * Forbinder `SøkOvelseGruppe.fxml` med `DatabaseConnector` og sørger for at riktige metoder kalles ved riktig interaksjon fra bruker.
