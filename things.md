
Current Status:
 - exista o lista predefinita in Firebase care este afisata pentru fiecare din Olimpiade/Concursuri/Alte Activitati
 
Definitia Obiectelor: 
 - master-detail -> liste:
    Olimpiade (concursuri specific scoalare)/ Concursuri (hackathons, workshops)
      - denumire: (text)
      - materie: (lista)
      - locatia: (text / lat,lon)
      - data: (data si ora)
      - organizator (text)
      - descriere: (text)
      - link oficial: (link)
      - [photo]: (link)
      - canal chat : [messages]
      - [cui se adreseaza]: (lista: gimnaziu/liceu)
    Alte Activitati (orice nu se incadreaza in cele de mai sus - poate sa fie voluntariat

TODO:
- authentication system
- discord "like" forum (!? https://github.com/hieuapp/android-firebase-chat)
- web management platform pentru configurarea/incarcarea evenimentelor - 


Roluri:
 - Event Manager: creaza/incarca/configureaza evenimentele in platforma, aproba evenimente introduse ad-hoc
 - Guest User: introduce evenimente ad-hoc, ce sunt aprobate ulterior pentru a putea fi vizualizate de catre student
 - Student: identifica evenimentele de interes pe baza: domenii de interes, locatie 

Ce probleme rezolva:
 - un loc centralizat pentru cei care vor sa participe la activitatile extrascolare






