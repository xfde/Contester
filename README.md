# Contester
Contester is Android application written in Java by two high school students that acts as an aggregator for contests, olympiads and other extracurricular activities.

  Behind the app is a real-time cloud database powered by Google Firebase that can send push notifications to users' phones whenever a new contest is added to it or an existing one is updated. Users can create periodical reminders for themselves for upcoming competitions which are also displayed in an easy-to-read way in the built-in calendar. 
  The main target audience for this app is high school students from Romania that are interested in participating in extracurricular activities. Currently, a similar solution does not exist and there is a real need for this app, according to feedback from several students that claim they keep missing activities for various reasons that they would have been interested in.
  The mobile app is also accompanied by a sister-website, created in React using Material-UI and hosted on Heroku, in which organizers can submit requests via forms for their competitions to be added to the database. At the moment, all requests are automatically approved, but, in the future, they will be subject to a vetting process.


The website can be found at the address: <link>http://web-contester.herokuapp.com/<link>

The react.js is also available on github : https://github.com/xfde/contester_reactjs







![](https://firebasestorage.googleapis.com/v0/b/contester-6c94d.appspot.com/o/images%2Frecord.gif?alt=media&token=b5d3a633-1596-4171-8e06-d4baf1d1c373)








-----------------------------
InfoEducatie Informatii

-```Ilustratiile au fost generate cu unDraw```
-```Iconitele au fost importate de pe FlatIcon```
-```Textele descriptive cat si Imaginile reprezentative(Actual nu in forma finala) pentru fiecare concurs/olimpiada nu sunt orginale, procurate de pe siteurile oficiale sau web```


Proiect Realizat in echipa de Cazacu Alexandru-Cristian si Constantin Alexandru:
* `Cazacu Alexandru-Cristian`
  * `Realizat Design Aplicatie si Site Web`
  * `Realizat Meniu navigare Aplicatie`
  * `Realizat Documentare Concursuri`
  
 
* `Constantin Alexandru`
  * `Realizat Baza de date firebase`
  * `Realizat Continutul Fragmentelor si Meniurilor+functionalitati`
  * `Realizat Site Web`
  
  Documentatie tehnica:
  Contester este o aplicatie pentru sistemul de operare Android realizata in Android Studio (Limbaj de Programare:```Java```). Aceasta consta in trei interfete principale(```Home```,```Concursuri```,```Calendar```) si un Panou de Control cu mai multe optiuni(```Setari```,```About```,```Add a Contest```, etc.). Navigarea in aplicatie se realizeaza prin fragmente avand activitati doar cand se schimba focusul principal al actiunilor(```Register```,```Login```,```Main```,```Control Panel```). Scopul principal al aplicatiei este de a oferi un loc centralizat si usor de folosit pentru toate concursurile adresate liceenilor. Astfel, aplicatia foloseste pentru back-end o baza de date in timp real (```Google Firebase```), in care sunt stocate concursurile, detaliile, imaginile dar si informatiile utilizatorului. Prin intermediul bazei de date putem trimite notficari(atat pe email cat si pe telefon) pentru a anunta deadlineuri si modificari. Pentru adougarea concursurilor in baza de date, am realizat o interfata Web cu ```react.js```, utilizand in preponderenta biblioteca ```MaterialUI``` pentru crearea elemetelor de design. Interfata Web este destinata in mod exclusiv organizatorilor de concursuri, care se pot autentifica cu un cont aprobat de catre Administratorii platformei si pot adouga noi concursuri sau updata pe cele existente .
