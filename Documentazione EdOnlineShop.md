###Elaborato di Progettazione del Software

#Documento di Software Design

![Logo](https://www.giornalistitalia.it/wp-content/uploads/2015/09/Universit%c3%a0-del-Sannio.jpg)
#
**Docente: Prof. Giuseppe Antonio Di Lucca Studenti**
#
**Studenti:**
***863000636 Vincenzo Mancinelli***
***863000660 Floriano Prete***
#  
#

_____

* [Indicazioni dei casi d'uso](#UseCase)
** [Raffinamento dei casi d'uso](#1.)
** [Use case Diagram](#UseCaseD)
* [Software Design](#Software)
** [Design class Diagram](#Design)
** [Design Pattern utilizzati](#DPattern)
** [Sequence Diagram](#Sequence)
* [Software Implementation](#Implementation)
** [Component Diagram](#Component)
** [DataBase Relazionale- Schema Logico](#DataBase)
* [Human Computer Interation](#HCI)
** [Welcome](#welcome)
** [HomePage Proprietario](#homePageP)
** [Gestione Cliente](#GestioneC)
** [Gestione Dipendente](#GestioneD)
** [Gestione Prodotti](#GestioneProd)
** [HomePage Dipendente](#homePageD)
** [HomePage Cliente](#homePageC)
** [Carrello](#carrello)


-----------------------------------------------

# <a name="UseCase"></a>Indicazioni casi d'uso
## <a name="1."></a>Raffinamento dei casi d'uso

####Login Proprietario
![Login_proprietario](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600437936/EdOnlineShop/Login_Proprietario_rkwpye.png)

####Login Cliente
![Login_clienti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600437462/EdOnlineShop/UseCase_Login_Cliente.png)


####Login Dipendente
![Login_dipendente](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438000/EdOnlineShop/Login_Dipendente_oo25e5.png)

####Aggiungi nuovo prodotto
![Add_prodotto](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Aggiungi_nuovo_prodotto_a9tsaa.png)

####Acquista Libro
![Buy_book](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Acquista_Libro_ht9ddh.png)

####Elimina Richiesta
![delete_r](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Elimina_richiesta_wvtobe.png)

####Aggiorna prezzo prodotto
![Update_p](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Aggiorna_prezzo_prodotto_xvbjst.png)

####Visualizza Prodotti nel carrello
![View_Cart](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Visualizza_prodotto_nel_carrello_vwvn42.png)

####Aggiungi libro al carrello
![Add_libro](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Aggiungi_libro_Carrello_iewggb.png)



# <a name="UseCaseD"></a>Use case Diagram


![Use Cases Diagram](https://www.plantuml.com/plantuml/png/RP9DJyCm38Rl_HNUxbl5Fc47j6cTE33GL6dSizHhBTAaE0c8_dgoiKgb9RT7UfpNiREsJh3pfOgYGOPdqWZ99dr-IdUxrsX0MYgVeMS5XNELNSKnYwznJgxm9s-HfT4EjHjBKyO9YtSi3SSfPWcZjQE34jeTN_PnAQn3ZgtxLZYkhcchuwKGLv72imDdW2al76J4A1qP7KK08K4AYp3Scvmq7DXKJ1WQ9ZC4OU6ulUjRMqMNYs_2f2yaLA7EMiwqxMpodBpE2RGtdmOgDfbnhicR_L7bXwSmFzZJYHjZtZPsYahI0bAI1M5GRzAYcsWuM1LZNVSYbsttZQmNYkfQD5fe51L8mOnADJNt_xxesYQ8dlrmcVNlEc3qU9jgcoSTddPurk5vXnSTNdRuliEXeSjPXpM5l_83 "Use Cases Diagram")



# <a name="Software"></a>Software Design

## <a name="Design"></a>Design class Diagram
![Class diagram](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600439437/EdOnlineShop/EdOnlineShop_glcfl6.png)

#

![Design class](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600439400/EdOnlineShop/Design_class_offhbz.png)

####Model-View-Control
![MVC](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600446454/EdOnlineShop/Model-View-Control_rz7zjm.png)

## <a name="DPattern"></a>Design Pattern utilizzati



+ **Grasp-Controller:** è uno dei Key Five Grasp pattern. Il controller assegna la gestione di un evento di sistema ad una classe rappresentate, la quale si occuperà di invocare l’opportuno metodo della business logic. Il controller consente di disaccoppiare gli oggetti dell’interfaccia con gli oggetti specifici del dominio applicativo. E’ stato utilizzato molto nella applicazione, sia per la gestione della comunicazione con l’interfaccia grafica, sia nella gestione delle operazioni specifiche per ogni use case.
+ **Grasp- Pure Fabrication:** è uno dei Grasp pattern avanzati. Delega una serie di responsabilità altamente coese tra loro ad una classe che non rappresenta nessun oggetto del dominio applicativo, ma deputata solo alla gestione di tali responsabilità , in modo tale da garantire un alto grado di coesione e riuso. E’ stato utilizzato nella generazione di GestoreDB, la classe che si occupa di memorizzare i dati persistenti sul DB e di accedervi all’atto dell’inizializzazione del sistema.
+ **Creazionali- Singleton:** il pattern Singleton è utilizzato quando si vuole che esista una sola istanza di una certa classe, garantendo così un unico punto di accesso globale alle risorse e le informazioni gestite dalla classe stessa. E’ stato implementato con il metodo statico getInstance() e con il costruttore privato. Nella nostra applicazione è stato utilizzato per il GestoreDB

## <a name="Sequence"></a>Sequence Diagram

####Sequence Login Proprietario

![Sequence Login Proprietario](https://www.plantuml.com/plantuml/png/TP5FRy8m3CNl-HJcjg6LtdkOc0JYCBOX4BT9asaCHEHFvRYWVVibZAajqdAB_V9xpyuq2hAqpYerqhGtdc3sidvThEVpTpKolhQjffOj74MQM9KbusLoC79iTsqaheCNyZAfWojdZ2ToWSirwHbJZCNAefVjsx9q68MuZF9jQT9StR2MmB3YqB2XbC64N39d51fMhwTVWU8PDlMHsA7Wrp8SZ8SAvlizYRG8ne8DXyBuZdUdLZar1miRas8vLFUqRF3RIk0pMgCJPPl6VKWbOv-WmHWlWVKZA4t_sdIDcq_2u0vZNl67KdyMoItTazCLNa4umQTVtHpojGFbTrbRz1Bs4-H-3zi56knlFAvgfgJlQmUA2bo8mWWn4J9W-2NzcM-ZAJKbhzFF_W00 "Sequence Login Proprietario")

####Sequence Login Cliente


![Sequence Login](https://www.plantuml.com/plantuml/png/TL7BRW8n3BpdAyojHLtsleUAgY0EfIq2SQjKcOs1Y3oGumNrxvlGXLqXDRVns3DZPnm5MHfdbHfesXfFC7bPlisMq-c76XXVsqPJmnRs8iTObINZURGpicysJIIkWnVoCgg3AoUC1tA1ooNf2LECnSAYb_Nxl7GOXRYCycDfTBouOIs1uTMQD4uPcHCAtGgNrm8edc5LxuaT2dxFmyvug62wtP98Ws0isB0hZEzKxhfLZid1miga70xLlLes-AEIy0cjqKbbdVPxI2LZd-28CPu3wqTGclwrwOZM9ydW1cE-wQTILulabd1okeXN40xmvHUjGuQTK9xBlKKlOJz1vdkoNQ3hwOR3gcPAhVssArc12r4O8QQvF7OxVt_9TXSr9g_Jr_q2 "Sequence Login")


####Sequence Login Dipendente

![Sequence Login Dipendente](https://www.plantuml.com/plantuml/png/TL71Ri8m3BtdAynjGokzzp0ngTKEOni2SPiqcSP0H9fKYGlQtyzXJCsGvflzxFUUxNba33nqLgc9ffrn1FNJwkLvrJHlQc9SQmTDGx1mOEvZLPO1pxEzuSEm7IA5rZicnxFMTsKTy4YT3-MAT1qenc9fqV7cTL5s69b26VdBqgo_g67BFa1jUd9Q62WLp0cPyjebnXoAHrYt1meTCduk_DuugA3PxOXvG30MhDyNnerSDzqg-GtUmjg8eGxLBLiI-864-8JMQ67PoA9tKZBs0NgCyUo3lWUbwL-P4RZg20rkCQRZlYlrAo5gaelIXRm2VuGFjxmgf3GxYiobtY9pc3j8U4Ox6igkDvrMRI3f_cDWn2leVEI046Kw3UUloBUzBgNc0ieplm40 "Sequence Login Dipendente")

####Sequence aggiungi nuovo prodotto

![Sequence aggiungi nuovo prodotto](https://www.plantuml.com/plantuml/png/bLJ1Rjim3BthAzXRKYJntOUX0n8KmxejID7RW4Ar64UeB7aKtMBzcltBVcoIuyPox0oRJpP9lVVuIFd6ioHkAYF4bSAzjWZhZxlFjxlDvgkuqhOmZSA631oOQvzd6SdNLQdvq3mr7gbmbj7ogd1LjYRvZ9MZR8TgJUZzSckavOSlTrabFIDbddyQNDKjcopO4Mp9rQGnQD0kXlIBP1n6soUDmF83t1S7f4goVBnpfRQGmsQ_H-P6WZPWNBdKjiSxgnPHDJa3zph8ggGuHui4nrG0Vf56gu3o4DgTXP0s2wYbzw-Er1o4meiqVQBZ2J3oIVfeyJSXtYa2M_WEJ2ru3kuPljjjnn0_Uw1uBjOk4uMfWfXFO7j10_-kh_E2CDIdjOEA72hdcIJuW101XYD9U-vQkpoWC-2Qi4BWi10EIlIiNLYvcfnop6vgP0Fc2x297zC3cTwpq_nx6suxIO_lalx7BNCqhAK4fIVw-mVdnWfo-6J3ycbV86YrxS8B-D64wwNvzo-83FZsbhGsGX5ZtCardoKC9u95XpzF6nXJ_CtOuufBTKAVJRQoE7ardrZxL71_F6zlp0cYlK4Xq6a-Pe_l8CjIEx9oE8gpQpFQsCwlUBYnqrCKuWQj2d-sFm00 "Sequence aggiungi nuovo prodotto")


####Sequence Acquista Libro

![Sequence Acquista Libro](https://www.plantuml.com/plantuml/png/hLRDKjim4BxxAPPJ0qD8ppumC0dJGqERXd9ZfgDOmjb1be8aXscUfk_I5-lA_y5AI2WtMxlwTlVxLYjTMSUCopCPHITSFA8ICBcUV_qolxdv5fsWIcJEHMuaB9rRsNWqCkpb8aMtp1Uv5IRHoWdbBXATZIQ6FObCcz5Sy8aHrWvdaYbtVpiTPSmwOKRM_PRYObL4OudJ1iOIQRlmVxXcJZGBKLHzmV0IxfAbC1bpxDTKfwWWXfl7H-5SpW0bI9qEKRKGhxmZdwFH4kwGaiXOz1hD1oXD1Bnc4ZcXt5Dn0rf2UGuhPksBDlmK8YvsXcaDLHo2OGjcFQ7VewWEGT7edo8Lu37e9tXGiog2_ss1_3xlE-nasCt0spkmRK8rTMTdSM84kNRTQcCCcRRECB2qpszhkEyMMTKILgE5MdczKLFts5p2AfUMUSCQRKw4RZOC92uCXaIge_PXjuIfL9GQY5SS89_YuhHFUe_cml5DNe-YDGQxr2zJSgdJ230MBAV7lIJlcfjWRzNilC4_AkfNj8cWUe4BMK1wgYgB4Q1eDM762EBm0tHWaqQ6StZEIMvqV_y45DbRrcwvMipBp--LheJCL_v0_wZX1g5aZyDjUIzfPccAkNBqqQ7u7QTerfoYvWGTejZk0NUGWcEw08xLxQ3PLeos4Z_-cBbMaK63pTTHZhP3-oiuzmhlbkIMhq3GxIdNdMtZVIBLjW1YNnP6orkoXAjhCFl7fs3xVwwHRPuO5QsvTWwt1WW8JyPce-6dTam6xunmixTitYKY9HSAmub7Ee_0aUxQb6MqxGEuz2tUWk-zAeBvMU5kDP_L2GsQr4xVKwrD3Au5o4_8vvWiKNXk2nQpefrOyfmJr16rcHfcq84y-23tMwZieFe1j0EqQQPkIXtNOdJlwQx6yReizwqtIbCa4LHkGBL6gY0uNwFeIYXExyj_ "Sequence Acquista Libro")


####Sequence Elimina Richiesta

![Sequence Elimina Richiesta](https://www.plantuml.com/plantuml/png/nLHDRzim3BtxLx2tjbZYkmz31oGOXgPRaQAt0GDhCJPHMJ8aES7owqTvIQMaBehkCjt4Z_V8zsJV-80k39qkYWj56pO4Yo_hsw_hvV9xSS6creEYmMbeG-XzLPOET_E6Gpiy3PvSRKqW4-QrxSg5msVgh2lNf1QEl9_TQpJXyMvLTkW3kTA7tvhc_SY6TR0E5joJKO90CS1R39J7nfFkCFiC3tLBhiE0lrQsOGCLB3SR2c50O0tQDZCs2UkikeZpEglXWMMW3ejpj4ZmDoN0MzIi1ELH5hsK4EjFqAFtE-lK5HIAtgH9YGEFmE0J-YZkZw8uKWYRt8Lf1A_0FiDFStzWYDTC5EcBjRDimdo2cCzWqq2PUjVNLUr8gay6IFaAEkk3G_3I7PjpA_9j3qkzPSm9PE-eesXDFsYFCRPbFuYo-ps2uxfbaYSuvLX6FebzujLehDO8cbvGwRKFhvJ_fjPI7KTuL--Fobs1R-rk9LMNYUNgFUtZ2bA1e4YVxFAl1dZIj6ThHkWNmK4XaEQE3JgILqV9g20VgpqcFo9zsl9_2t_KCjCFi6dOEeDdKau-jscBadW7SG9EkIBdHdhaB_S7 "Sequence Elimina Richiesta")


####Sequence aggiorna prezzo prodotto

![Sequence Update Price](https://www.plantuml.com/plantuml/png/bLJ1JkCm4BtxAuOR8DhSSrY1r0gj5jYgY1iICl4qjN3iQ3m1BL-_TfesJfCYoAaPZzzxy-QbrvubSLCP8SuKRhH5MDoi_zokbyi7SQPjOHg53HdOCjS-ppAI7_DIyxPvRJnIuIoZvNdXgcn1yWqhHzaQrOBG-zdAICjFztTP9JqZPPx_6PpNBPiis16ioDMaCMZGBfRqksGSLjidhS3i5pmMMwHAidovSwMsaCDoiq7cHe8sO5mvqxR7E-eMKJKv0uywoAgaE4QB1BkZ0FmkZLO1vIcCUnvAsbn1BRt_SAGkG2WyITCVT3m1HhvA7ot-AyIU8h25zy3KWkVWtk3PhZg6-De3nNknTvOeJ1N4ymIs5pJmx_8oBmX3VzexwCYXSfv9WWy84M2uadJcRhJJ2pe2hWah10w1S52YP-r2v6fooZ6xgPKDc4_09dvCBsGwPuVzzpRSTf9Uzf9-ufRP6TPIWj8Jytt3kR6277xR43xj2mIjLljoXCNFpsIUqLKn1flCzdaFUzMXNgHQny1VURXBionBxSZAaSf3Y5CblO0yv3zy0DsrlFqQThyFa4CZZq8_NCsND_VQ3yyrMXN-dVu3 "Sequence Update Price")


####Sequence visualizza prodotto nel carrello

![Sequence Carrello](https://www.plantuml.com/plantuml/png/hLHDRzim3BthLx2tjbZYkmz3XoReOTqMfEXjmC1QJ29KbWo9Ih7--b69lvev6x3rPl7ZFV8zmZUHCN2ghL8NcZR64Im-hJ_VhPVBh-h2kCecJIbOs34tiIoAW2_phU5TUagHGkKTa-DvvUjY4V2PQX-ADUb5e1XdAukE7x_S5pL6fb14_cbftXpOi68VuDOQQQVyCdjawWDAjLym-mWFrOv2ZOm_xltME2XXkTaGSq8m5gpVpemR84wgLPunU0iFHeQeKPsYPO9ZIe3tQ8qMb4TPxb92ndw01cDyyK5VWT9qbcP8j3m2WqyOiw3VbEeeX4tUmdG0By4_mtUtQXdoSm3AVRbsDffmF47EZs27WJhfhg_BAf2KZikwP0crZnmGelJbjbxxyPBjBcVi6B1MsN3ZvNoQP2FcO8KXaBL-ofcEwhmtOxxUdRRSuWBPN1fzAxTxzQ_A7zlBzr6ytLKapsCUHPyybcxplpbog1uq7EIO82sXwUMF2VOc9ZdZrrUnz4yUTBa9nDzLHmc_KNrwknxpLFHMxVyvyBSweIpjjMSsuCWEIarSxmqvBJ-TNm00 "Sequence Carrello")


####Sequence aggiungi libro al carrello

![Sequence aggiungi libro al carrello](https://www.plantuml.com/plantuml/png/hPJFRjim3CRlUWhsbXPDlBCFHOSbw67PrgNeRS30s4n2L9Oy_KcmFitUPIysAh5Zht5NTAXFbaXz97-Vx6iVq8LOQQNEIbgn8PY-NtoyMSncdzKPcqB7agBJi0cXzdcMETnDrXmsSHazkSAQG2PC2bjbKuSFL5cNBQYSEl9-VAlHXFjFywn27yXbFlpKDAdtrR08ri47pNASqegt6EYueLJp1kChk2isv2eC-7rkrsmWXzbgHI541DQWxNhCffDuagrIZyvgk6DfeaBrL2qLE8H4U8kQIr6vb-56iiNw4chqVcTTUGwgf6VBT86cZiZW4dq2-aMfjeHKaxLKseldO1_WcxbjAgHb9vJEfTnnhyD-1odUa-qQQj5TNEI58qdjfxN172hhWqFmSYuTExBl3zdCCkn69rKdlzdAxV5HGnsrnnJOiey2zF4HGVFIyP19RTLJsR-CQLpK5eGhZxYSy_By5Fe9vdtYYrnVXJK7lx6xkQIqRGmG6npdXBsMx9RDuDrgwRp0dutSL_O5oRnGajvB9bHDn14OsItGEHA6R-03JeysNCAFA7Pp-Frhm95_ZlMyNPtcrRl_jUuW6UlqGNzjvKP3pRvEj-D-m8phDKSJvAM7UEWhakUQJ2a_m3y0 "Sequence aggiungi libro al carrello")




# <a name="Implementation"></a>Software Implementation
## <a name="Component"></a>Component Diagram



![Component Diagram](https://www.plantuml.com/plantuml/png/TL1DJyCm3BtdLtYxjxaZD6si1J4eL9tW0WXvZMchfKb9NCQ4-E-a4nKW3PyiF_jzUEuP7VUj5cAYwAKn1CcYkBeeqlH6J1fJwbvHxpJKp9q_aTBXBguQhljDxycLrZ0PZalRoiJXbbhhP44gSUHzb6iqV9TTonOzavEUzvhYxg3MORd5Yc2QEriHiuLKMQE3WNLjEpWDZ9qrWHiIyarbPbFu44A0GiOD-d2OxTUlUfX2AF4PG66i-iKuOWy_P3DuD27WxTDH80ld-ZYq346TrT5zGxjXGmoA1mSGmIevMpulyainDc5sN7M5RtZ-_j_ANn4nflgkEHaLNlS5 "Component Diagram")




Il component digram appena mostrato evidenzia l’organizzazione delle componenti del progetto. 

Il pattern architetturale utilizzato è stato il Model-View-Controller. Nello specifico , la componente View si occupa non solo della visualizzazione dello stato dell’applicazione, ma anche della interazione dell’utente. Sulla base di questa interazione, la View si occuperà di propagare gli input forniti al Controller. Riceve inoltre notifiche di aggiornamento dello stato da parte del Model, che si concretizzeranno poi in un aggiornamento dei dati visualizzati. Il Controller rappresenta la componente di coordinamento del pattern. Si occupa infatti di analizzare l’input proveniente dalla View e intervenire conseguentemente sulla componente Model. Inoltre, in seguito alla modifica di stato e alle interazioni con l’utente, seleziona la View adeguata da visualizzare. La componente Model si occupa di incapsulare lo stato dell’applicazione e, come detto, di notificare alla View le modifiche di stato coordinate dal controller. Il pattern, quindi, è molto utile quando si ha necessità di separare gli strati logici di Presentazione ed Elaborazione, rendendoli così indipendenti gli uni dagli altri. Così facendo, sarà possibile mostrare i dati in modalità diverse e di rendere indipendente la modifica degli stessi dalla loro visualizzazione.

Nel nostro caso, abbiamo la ***View*** ed il ***Controller*** fuse in un unico component. Le classi che modellano gli elementi del dominio quali *Utente*, *Proprietario*, *Cliente*, *Dipendente*, *Prodotto*, *Libri*, *ArticoliC*, *Richieste*, *Pagamento*, *CartaFedeltà*, *SingIn* e *Carrello*  definiscono la componente di Model di gestione dello stato dell’applicazione. Le restanti classi, quali *Gestione utenti*, *GestoreDB*, ed tutti i controller della View, causa l'utilizzo di ***JavaFX*** che necessita di un controller per ogni View,  rappresentano i ***Controller*** ed i ***View*** della nostra applicazione. Particolarmente importante è la classe *GestoreDB*, la quale si occupa della comunicazione della base di dati e, conseguentemente, dell’inizializzazione dello stato della nostra applicazione.

Il diagramma mostra inoltre l’utilizzo di Java DataBase Connectivity, un insieme di interfacce che costituiscono una API per l’intenzione con un DB relazionale.
Il DBMS utilizzato è *MySQL*, il quale a sua volta mette a disposizione un connettore compatibile con JDBC.
Nel diagramma e mostrato anche l'utilizzo delle librerie grafiche *JavaFx* utilizzate per la parte grafica del nostro progetto.

###Boundary Use Case 
La classe *GestoreDB* è la classe deputata alla comunicazione con il DB. All’avvio del sistema una istanza di GestoreDB viene generata e associata agli elementi del dominio che richiedono i suoi servizi. All’avvio del sistema, il GestoreDB caricherà dal DB stesso le informazioni riguardanti gli oggetti da instanziare, garantendo che le operazioni avvengano tutte su oggetti caricati in memoria.

## <a name="DataBase"></a>DataBase Relazionale- Schema Logico

-*Utenti*: (<u>Email</u>, Nome, Cognome, Password)
-*Proprietario*: (<u>Email</u>, Nome, Cognome, Password, Prodotti)
-*Clienti*: (<u>Email</u>, Nome, Cognome, Password, idCarrello, idRichiesta,idCartaFedeltà )
-*Dipendenti*: (<u>Email</u>, Nome, Cognome, Password, Richieste)
-*Prodotti*: (<u>IdProdotto</u>, Prezzo, Quantità, Email)
-*Libri*: (<u>IdProdotto</u>, Prezzo, Quantità, Email, Titolo, Autore, Case Editrice, ISBN )
-*Articoli*: (<u>IdProdotto</u>, Prezzo, Quantità, Email, Tipo)
-*Accesso*: (<u>Email</u>)
-*Carrelli*: (<u>IdCart</u>, Prodotti, Prezzo, Quantità, Email)
-*Carte*: (<u>IdCarte</u>, Datas, Punti, Email)
-*Richieste*: (<u>IdAcquisto</u>, Data, Prezzo, MetodoPagamento, EmailDipendente, EmailCliente)

***Utenti***

![DAO Utenti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Utenti_ctrpne.jpg)

**Proprietario**

![DAO Proprietario](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Proprietario_e8hng2.jpg)

**Clienti**

![DAO Clienti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Clienti_vvfxci.jpg)

**Dipendenti**

![DAO Dipendenti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689583/EdOnlineShop/DAO_Dipendente_aj97kp.jpg)

**Prodotti**

![DAO Prodotti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Prodotti_c4kbcc.jpg)


**Libri**

![DAO Libri](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Libri_yr0wl6.jpg)

**Articoli**

![DAO Articoli](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Articoli_est30g.jpg)

**Accesso**

![DAO Accesso](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Accesso_syrmrq.jpg)

**Carrelli**

![DAO Carrelli](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Carrelli_xdpwtz.jpg)

**Carte**

![DAO Carte](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Carte_n09znu.jpg)

**Richiesta**

![DAO Richieste](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Richiesta_awax2s.jpg)
###
# <a name="HCI"></a>Human Computer Interation

##Welcome

![Welcome](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Welcome_ztuxd2.jpg)

***Questa è l’interfaccia di avvio del sistema. Da qui, l'utente potrà effettuare l'accesso, premendo il pulsante <u>accedi</u> ed in base alla propria registrazione mostrera la homepage di riferimento.***

## <a name="homePageP"></a>HomePage Proprietario

![HomePage Proprietario](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/HomePage_Proprietario_nfwerp.jpg)

***Questa è l'interfaccia che si ottine dopo l'accesso del proprietario e si possono vedere le liste di <u>clientit</u>, <u>dipendenti</u> e dei <u>prodotti</u> premendo i rispettivi pulsanti.
Premendo sui pulsanti <u>Gestione Clienti</u>, <u>Gestione Dipendenti</u> e <u>Gestione Prodotti</u> si andra nelle rispettive schermate di gestione.***

## <a name="GestioneC"></a>Gestione Cliente

![Gestione Clienti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Gestione_Clienti_zv3nx9.jpg)

***Questa interfaccia rappresenta la gestione, da parte del proprietario, del cliente. In questa schemata e possibile aggiungere un nuovo utente tramite L'apposito pulsante, si possono visualizzare la lista di utenti già registrati, la lista di carte fedeltà già usate e la lista dei clienti registrati. Tramite il tasto <u>Aggiungi Carta Fedeltà</u> è possibile dare una carta fedelta ad un utente e tramite il tasto <u>modifica</u> si può aggiornare la data di scadenza della carta ed i propri punti. Infine con il tasto <u>Aggiungi Cliente</u> si va ad aggiungere un codice identificativo del carrello all'utente e viene classificato come cliente.***


## <a name="GestioneD"></a>Gestione Dipendente

![Gestione Dipendenti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Gestione_Dipendenti_cohjdo.jpg)

***L'interfaccia gestione dipendente rappresenta la possibilità di aggiungere nuovi dipendenti tramite il pulsante <u>Aggiungi Dipendente</u> e tramite il tasto <u>Elimina dipendente</u> l'eliminazione del dipendente.***

## <a name="GestioneProd"></a>Gestione Prodotti

![Gestione Prodotti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Gestione_Prodotti_s4jri6.jpg)

***In gestione prodotti abbiamo l'interfaccia che gestisce l'inserimento di nuovi prodotti tramite il pulsante <u>Aggiungi Prodotto</u> e tramita <u>Modifica Prodotto</u> è possibile modificare il prezzo e la quantità del prodotto e d automaticamente le aggiorna il prezzo e la quantità dei libri e degli articoli di cancelleria.
Tramite <u>Aggiungi Libro</u> possiamo aggiungere tutti i dati del libro, così come <u>Aggiungi Articolo</u> aggiungiamo i dati degli articoli.
Infine con il pulsante <u>Visualizza Lista prodotti</u> è possibile visualizzare la lista di tutti i prodotti aggiunti.***

## <a name="homePageD"></a>HomePage Dipendenti

![HomePage Dipendenti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/HomePage_Dipendente_lvisx0.jpg)

***Nella Homepage del dipendente abbiamo due pulsanti, il primo pulsatnte <u>Visualizza Richiesta</u> permette di visualizzare tutte le richieste di acquisto da evadere e tramite il pulsante <u>Elimina Richiesta</u> è possibile eliminare la richiesta scrivendo nell'apposita casella l'id della richiesta da eliminare***

## <a name="homePageC"></a>HomePage Clienti

![HomePage Clienti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/HomePage_Cliente_nwrbym.jpg)

***L'interfaccia Homepage Clienti rappresenta la schermata iniziale che si presenta al cliente dopo il login, abbiamo due pulsanti per la visualizzazione dei prodotti, <u>Visualizza libri</u> per i libri e <u>Visualizza articoli</u> per la visualizzazione degli articoli di cancelleria. Il pulsante <u>Inserisci nel carrello</u> per l'inserimento del prodotto scelto, inserendo Id del prodotto e la quantità nelle apposite caselle, nel carrello.
L'ultimo tasto che troviamo è <u>Vai al carrello</u> che permetti di andare nell'interfaccia del carrello***

##Carrello

![Carrello](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Carrello_gz254w.jpg)

***Questa intefaccia rappresenta il carrello di prodotti che ha il cliente, tramite il pulsante <u>Visualizza il tuo carrello</u> il cliente potrà vedere i proditti aggiunti in precedenza nel carrello. Tramite il tasto <u>elimina</u> possiamo rimuovere un prodotto dal carrello inserendo i dati del prodotto negli appositi campi. E' possibile scegliere il metodo di pagamento nell'apposito menu ed infine dopo aver scelto un nome per la propria richiesta è possibile inviare l'ordine al dipendente tramite il pulsante <u>Crea richiesta aqcuisto</u>***

