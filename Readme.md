### Elaborato di Progettazione del Software

# Documento di Software Design

<br>

<br>

<br>

<br>

![Logo](https://www.giornalistitalia.it/wp-content/uploads/2015/09/Universit%c3%a0-del-Sannio.jpg)
<br>

<br>

**Docente: Prof. Giuseppe Antonio Di Lucca Studenti**

<br>

<br>
**Studenti:**
***863000636 Vincenzo Mancinelli***
***863000660 Floriano Prete***

<br>

<br>

<br>



---

<br>

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

  <br>


-----------------------------------------------

# <a name="UseCase"></a>Indicazioni casi d'uso
## <a name="1."></a>Raffinamento dei casi d'uso

#### Login Proprietario
![Login_proprietario](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600437936/EdOnlineShop/Login_Proprietario_rkwpye.png)

#### Login Cliente
![Login_clienti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600437462/EdOnlineShop/UseCase_Login_Cliente.png)

#### Login Dipendente
![Login_dipendente](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438000/EdOnlineShop/Login_Dipendente_oo25e5.png)

#### Aggiungi nuovo prodotto
![Add_prodotto](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Aggiungi_nuovo_prodotto_a9tsaa.png)

#### Acquista Libro
![Buy_book](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Acquista_Libro_ht9ddh.png)

#### Elimina Richiesta
![delete_r](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Elimina_richiesta_wvtobe.png)

#### Aggiorna prezzo prodotto
![Update_p](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Aggiorna_prezzo_prodotto_xvbjst.png)

#### Visualizza Prodotti nel carrello
![View_Cart](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Visualizza_prodotto_nel_carrello_vwvn42.png)

#### Aggiungi libro al carrello
![Add_libro](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600438330/EdOnlineShop/Aggiungi_libro_Carrello_iewggb.png)



# <a name="UseCaseD"></a>Use case Diagram


![Use Cases Diagram](https://www.plantuml.com/plantuml/png/RP9DJyCm38Rl_HNUxbl5Fc47DBHD0wcWQMBSizHhBTAaE0c8_dgoiKgb9RT7UfpNiREqJh3pXOgYNeedqWZ9q_RnTRlVR5wY7ccfV8gU5UJEbNOHnoo-XXcvt1-zHPP6EzHkA4qHhrcyOs4utcEwPhHsi5D2kyDp4XV2EkJOkc-5m_8IjpYS2t4HAJmvS0QOijn1IepIaT5H110IfB08_SHaf677fcJ2q36PFWWBXzLDrrefEbzy5KPTOKqbwhJXZ9lEGvQHrnc1zkRJGCacDSxLUPC_glpm50O82HsvDgPDOwEe82rWJp8d3EfLchKJ3GUhPAogJkIywRwHzK9HLObQ2usY0YcOKQcwvlRV5rtQ1D6nxywp_bq73EwlKstogCNZ5azQF6tnhCNp5j-sE3HqFikmfl1NVW00 "Use Cases Diagram")

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>



# <a name="Software"></a>Software Design

## <a name="Design"></a>Design class Diagram
![Class diagram](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1602491651/EdOnlineShop/EdOnlineShop_gk8vw2.png)

<br>

![Design class](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1602490543/EdOnlineShop/Design_class_xdesup.png)

#### Model-View-Control
![MVC](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1602491917/EdOnlineShop/Model-View-Control_h0a2ia.png)

![MVC](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1602491928/EdOnlineShop/View-Control_csxoxe.png)

<br>

<br>

## <a name="DPattern"></a>Design Pattern utilizzati



+ **Grasp-Controller:** è uno dei Key Five Grasp pattern. Il controller assegna la gestione di un evento di sistema ad una classe rappresentate, la quale si occuperà di invocare l’opportuno metodo della business logic. Il controller consente di disaccoppiare gli oggetti dell’interfaccia con gli oggetti specifici del dominio applicativo. E’ stato utilizzato molto nella applicazione, sia per la gestione della comunicazione con l’interfaccia grafica, sia nella gestione delle operazioni specifiche per ogni use case.
+ **Grasp- Pure Fabrication:** è uno dei Grasp pattern avanzati. Delega una serie di responsabilità altamente coese tra loro ad una classe che non rappresenta nessun oggetto del dominio applicativo, ma deputata solo alla gestione di tali responsabilità , in modo tale da garantire un alto grado di coesione e riuso. E’ stato utilizzato nella generazione di GestoreDB, la classe che si occupa di memorizzare i dati persistenti sul DB e di accedervi all’atto dell’inizializzazione del sistema.
+ **Creazionali- Singleton:** il pattern Singleton è utilizzato quando si vuole che esista una sola istanza di una certa classe, garantendo così un unico punto di accesso globale alle risorse e le informazioni gestite dalla classe stessa. E’ stato implementato con il metodo statico getInstance() e con il costruttore privato. Nella nostra applicazione è stato utilizzato per il GestoreDB



<br>

<br>

<br>

<br>

<br>

<br>

## <a name="Sequence"></a>Sequence Diagram

#### Sequence Login Proprietario

![Sequence Login Proprietario](https://www.plantuml.com/plantuml/png/TP51Ry8m38Nl-HNcjg6LtdkOc4J59h4DmRXDcanZ829DAiS5xTylOKnjaPQRxPVlFIVJ8CZIrbQfaQQTSGIBb_dpntnLbczgP5nbMqqjMpY8DA78SyRpP6_aq6xRG5nv9-HaKlawdp4UgVQShqZFc4B8bXQTR5uNUOr1YFCWtvOcpSKEA_4CI_OD6ueXZ4yjSqAXOVTo-Xt87c5T7OXh5FnQ-BrnK42vsv58Ys0iMB_FZEjuDsgLKhEti3OnLetgbfOCVaSHV49hTAHiuhftiMNi0pGOmjcplWUbwL-RRd3rYHZSOY2OFRqhzMSHtM8TdIxm0lmHFjtowf3A3fJk9MtMIzXFaEOzR1Tey7xZSL4nHNrVEr0KKFiWZ10Y8G66NzBV-RgQKbDoElxi3m00 "Sequence Login Proprietario")

<br>

<br>

<br>

#### Sequence Login Cliente


![Sequence Login](https://www.plantuml.com/plantuml/png/TL5DRy8m3BtdLvZRXbPwxs5Y6XMRn3O4upPfCes1Y3H1ZWlQlr_22gsGbjkp_JxiZ8CWIrDRfGQQDiOHp5wdBv_JHLc-gu5nbMqqDMnX9t88HPupdaPR8xjctGJYoZiX9wFAr_c4SK-rvtn1Ui8KGZQtw6JrDijh34AS1_cnD3gSxR0Ip_1iJQHJGkQ8GjV2-RK0iaTOLZlY6WM_Ptvh71HGRZOaqY0O2zPlC-CwbPjfbMAojx0qCKUDwbOj6Vorel0HhT5HPHNtkuibOn_WW26SFEjxK9h-jUaQhK-KmJK6WidJXr8NY-WMSNGwYnVWz_3bvgr3WfrGugNPh9Umdo3rUx9TeClfXiEYOegZ_R5Biu3Q1s6447c9TZr_Vybs5pKcf-FN_G80 "Sequence Login")

<br>

<br>

<br>

#### Sequence Login Dipendente

![Sequence Login Dipendente](https://www.plantuml.com/plantuml/png/TL5BJm913BxFhoWt9IvxtuF1188cg0JaPcBAJe49yzZCT27-Uxk8sP64kRLV-pqwuyGOkNLMgO6chV44yvVPyyTiEPs-gO7njMqrjT72dhb9LLb6F8rsXlVjfaqKw-2PF8_guCf9n0Ev4CibwKcab8g5HS_hrtdfC375Cl6tfL5pbiEQGuI9QSXhOQ2kOOx8bFVEhw-XU8HLlQVeaF5h7dR6GmNJxPQOMmHZmOPTONpFTJMjEhynM5WPCUHGNRDr0h-G41_H6YqiQmbw9orZ7w31b4uXwdjGccxAzC15HsXmWqd2FRqhzISXQbABqfcyWd20Jx-uA7HbTXJPwsQBp67keCCpsjvGThtXiAeZoVG_0prUWGk98qAIxMuv_ueyxIMK6Wied_a3 "Sequence Login Dipendente")

<br>

<br>

<br>

#### Sequence aggiungi nuovo prodotto

![Sequence aggiungi nuovo prodotto](https://www.plantuml.com/plantuml/png/bLJ1Rjim3BthAzXRKYJntOUY0n9a1R8rQDVRW4Ar64UeBBaItQBzcltBVcoIxTfox0oRJpP9lVVuIFdQ4RTK5Ogn2u47gH5sDzklFxPtcyrtTY5rfYg1bLLm92fTcYIMlwroISVggN9eCwC9DQqoKoHhov-nC3QvGx6swDnohxYcXs-xfE2Eq2QEtXMkofgEPsGixAqfhKGlGfeGag-SS1YjdpW2ookupuve2qxySMTogI65pU60H1K7gK2PV2brZtTIpO9gQnJSIo-huEmKBH0qAG_yofKK7kN1jplp8QaMK7BdtemLSs02pzBqYPR7m_0dxX3MNsuP-wJmRFxRCzNWAPXd-AdtBKFux872kL2xZ1J62a8-Wkq53Voxl4mpYxu-hXrKf50OHvQ3ymW1O3YIkEUsjVC3EW4kBHO8v1V2G8wEfF4xLreZ396P6jc0-GnivCVqGARth9j_RyEsbVJuAUb_t5ADOJKb23dHtpyuDrQGmetsooTTXY35lWqluANorqlIxryG6F3Z8sfjXCB6k9DhFeiOEe95Yp-F6nXJ_CtOPiMvwD1dawqiEg_c4siV2-wFf_MDwI3g6-G3hUOcsxm3ptDfhER3KPnScz76jdw5mvMUdY9ZrwY5_xFz0G00 "Sequence aggiungi nuovo prodotto")

<br>

<br>

<br>

#### Sequence Acquista Libro

<br>

<br>

<br>

![Sequence Acquista Libro](https://www.plantuml.com/plantuml/png/hLPBRjim4Dth59DLdI2EkzOYI607QG6dTVFP5IXeaP47eKI77mVrQNgNNgn3_HtHZfrc9t66RsRU6mvvOIpJrcKoYewuUCHSmFJhzPVxwzkhgs_H4UQ9T5muBM5XxTB4ev5cBsSfseMREoDqed8hSdkMg6mqqUn9P4gFRWMVQ67CS2PPRXzkfgEC6IlqoDZVKfmjYt0iiKh3M29j5_uFLyoAPY6AgYyOdiDTiX0wOvRzcgeKSuZXwl5HMEiOe0IfqY7cBSGhxyZdg9M4EwGaCXQzHlC1IXC1hvX4JYWFLDo0bb2Umf8Pyw8qFuQ8YwrXMaCLXs3Od1a1ayllKLI7e6ZqJv4Ay1ZK4_pCPrK4_zi2-NtUTzZ9i9k1jtTWsuHgwavEuaGBSkswrSOOCcMiPc1edz_MSDyjigebh4OBjVHwewBsCKx2qad3l665nX6XwpK3YNED8P7gg7tO3M4g5QK2uXK7oASuF-wJtgEvS7oJruDeZS4irCkKNEeq0em5o-bnBycxvYROMpKxR_2FEVKhcaHGlS25B20zLPL52yXfDM5Q2-Bm0tHWaqQ6KtXs93VQlty2YkmiQxjSBURvv_TALqAwfJ_GFsguGIZPmt1RtakQMPgYoorzT2X-nocQDQUeEK7xABPzmEsbu9XkW4Drssks5QEjn8y_PgvLPD1WytMKestV_WhElSAx9RdbAn1qSyfrPzjubqYrBO3ObuNHyeOiuUeQpFxnATZ-dsjaaoT6HIkkhCMD0GB2ax5UAxXNbidWdH5kzfRDkqIav49XEF58vn4uqbsRieosVG2NliLRy9rNHJ0_8-oDuhCweK6Jsl5xgZM9nfK0-GdvBIOB59xRWiMiQ2UMF3k2Eg0sNSCCEf1x7-H-2vKTL3-0je0spTHDgUDQZEuTtTKuNfRbllL6QOeaWieDcA-GAWZEroYw43cdz-K_ "Sequence Acquista Libro")

<br>

<br>

<br>

#### Sequence Elimina Richiesta

<br>

<br>

<br>

![Sequence Elimina Richiesta](https://www.plantuml.com/plantuml/png/nLHDJ-Cm4BtdLmmtGDlcdWDYfLQ0L3vKbjjAgo6U9XR-Y6odrVRNxpZROhS48RZWc-VZlPdtd5pwW2xqMXN5YQ2LD0IBcwlhNrVB-VokE96cLhsWtYbeG-XyLPOEDzD6XhP_xZsvsff09anhgykPmnVIrfLB4ZD7tayU59hmTBieDVf0hlJXhw9fDz1X7Qo3cUp824QW691h39J7XfFkCBc0nxebfp7WduLjf84AvgiLXT0ZI0NADXDf4jPHTH7dTLR1e-I1D1R7Q97WVug1rwYaO9GdNlIKGrBzW0wztrWdpg0Gz2vDIknu60QVqVDoF--BOa_1R7ndfW6y0li2lyt3ZY5UCr6uBzPEiWdp2M8-Wqq3PUgTdrUr8wu-623bAz3M1uVWkJisvrRas-wMUi-O0yZEaQPeJTShZp6sbhvdPRTR12VhLXA_mJ77Cl9Hx0El1cELGb3qYaflVNYZ_8rOSdKSuKEzFojt1RwrcmLNdIQMiu-qZojm1O8WTR3BLmtmf6WhhHcWNmK7WK1AQcdG4RywIaO5_bZjFlaPwTEMtotyNijCFy2caTOPF99ozBcDMvJ4suaJSCmLFfVSmt-vVm00 "Sequence Elimina Richiesta")

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

#### Sequence aggiorna prezzo prodotto

<br>

<br>

<br>

<br>

![Sequence Update Price](https://www.plantuml.com/plantuml/png/bPHDJ-Cm48Rl_XN33H1jxZciMAbL5waB5IotbP29fwc5OqVZ2MZvzMkdQUCqAThDgHcFd_UTZ_JMioHkAYF4XSAjjWZhkzMFNwl7vV9UN6XRc4PXGmPspBNFiupanxpKl6jU6uzKECjeULwuAbkGVCFAKVQ8Qa7e_MnZfENdd-kiafwHCiz_3CxhLauMx0Wsv6hI64ne5qFwNJ8Ee-sJHc3s3PwA7L8bMRwiNQajvB3SRf6vaQ0D65VEjErv9zaYkYPdu4a7MvKKfxGei3yAu7Tfj0gKvr3kPGXfSmErzFx3aRe2eV2iJ7_GwGICV9KUOV7zGOY3H50Bxq6fXUVWtk2ttNGAyRK7nNinTvOuJ1t4ymJR6nhqx_ewBmX3VfexoCYXSfv9WW-421YE9AsvA-tyW4x0DM65m64X79JeMRkmSpKvvPZTrCW6ocUmIJ-c1pAzPyVvzsrOTPPU3fR-frjctx1M4fIUgEyVEZTsaCET3SkdVO6WrUOGZbZy_4pg6LqLOzZaRb_ss9kEUfLw7OE_wk5-csLPQaTMZbmUbpXrqXl8m_w73w2xbhTVm_xt4JbivCdI3qVpvSs3z-DpYrQ5VwU_ "Sequence Update Price")

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

#### Sequence visualizza prodotto nel carrello

<br>

<br>

<br>

![Sequence Carrello](https://www.plantuml.com/plantuml/png/hLHDJnin4BtxLmmtG4tsleUAYaIqKWeHb5kbQjYT91PUUsMFWyglxpZPBy869EXjFH_lpRmtsel0w3bMHgcJabRQ4Yn-N7t_TNKxdr-h4sqB4qkAti26kGvvbdbycgurR-933EGBPvaiJmjNPJEFZrGvdzrIEVCKmcHfqFBzpqLMOM3oMU2_XgRrZWuBTXukZPPsIY-zHQOke5Jp1PElS5Tio5V8-6VXrjf23lFLYfWZWZPWt7gYRGznKAtIZDuPkDCoH8Ng42qHx5C2l4MZIq6vb-LE9QJD5wWnX2VdopDG9HsbwHCDZy3W0mQ2sRSRfLeAOPEtCEt0St2FyDik6uRqx85INwgT32OSJf3o0zX-e5Qwy_Eyy2Ibmx8scKFb0dk487sfhTD-k6Ipon4xUgnbCbmxkPywce0fMA3tP8mRSwQbEkxDaAzpfoatE4FMfxgybECz-wZo-_Ry_oZUx2gIfp7teeyUIxlvUuxigdiDUpb6I7EeE_b3XAqEKSxu-LaiVSk3DZU2-5frbF0BrSUtwp0FHM_K_io1lzG9PMbNEcODbaolrCZrNf0jvQVp3m00 "Sequence Carrello")

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

#### Sequence aggiungi libro al carrello

<br>

<br>

<br>

![Sequence aggiungi libro al carrello](https://www.plantuml.com/plantuml/png/hPJFRjD04CRlVehfBQsQcBCFLH692fA1qj9R9JIn9ywewrspVnBHf-5TU35c4pisZKjLX4_UdTblPdxVsfVEe_MXLaboKjAADK7-uUhzrwkRnU9JSiAwKA6aO1MilMzSbgOMjxEA_JeiWoDR6Er9-rbXwdHkyO5gOzCRAkUMd9jUAzJ-xcEUrkWysTJv7ufcpQuS5jvOUATOZbDSyGOz7JQIf7s3wGNS5ckoDNhybfkADMImMAt8-u302fIffgnxYIVPIUpH6WMtB4tKc3nLYmNs8H7UeE9ILEvak8biiJg71ftR6bkUGbBIisNwG5j7P729Zc3-zdEIT2Macgobqauy0_C0z_gwhH2NlL0y5tEdWmw77SJuGBPlg4DtTfOLbYHrcDO5CwYDynR1oRbuxC1-E6GxoxWRlLGJ_MOZryS51KrG3cDWmou8qCT71CLBosCcTLMFPVymfdLH6H2kFE4ov-NfCVGZpBl45xc-2ci6RcsskQHqROmG6ntdYBsIx8xDwDtgwBp0dxNSLtO5oRnGajf9HbHjn19esItGMXA6_y47d1ziE8VlGUnc_-ldY2D_7UjvkthDYpV_QjrUCZJnW_xIoKt6cdsTRizzZnchYeFsyZ90FFOLoND9kfGVu6y0 "Sequence aggiungi libro al carrello")





<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

<br>

# <a name="Implementation"></a>Software Implementation

## <a name="Component"></a>Component Diagram

<br>

<br>



![Component Diagram](https://www.plantuml.com/plantuml/png/TP1DJyCm38Rl-HNUxctl24qRQncRLgWsu38GyXhJLakJahYC2V7VIIV6XrHyi2A_plFQ7Zj6ossjX1X8UguqmN8-kxgThPBaMWmgdQjMKciLbCoDEuiYY_kmgBXijwqZcnlDf3dCJHt55dTK6nkjICQMd0ioXPhlqcLKesEoaUE3eh0vsZMOxx0W66RM5CHi8956Ap_1kZGDd7jYOxHdGqokAlHe2Ez22938k4NdFwQ7zOlggk13V7XHQ2D_4R-rBcr-Y2Duq11FRXvxXTGpLByqzTjQeuBxYlPTX-XiZsD008luOleqoURYzF2rVjS5lkBbstyjVqt4QRMl69EM_dwV "Component Diagram")




Il component digram appena mostrato evidenzia l’organizzazione delle componenti del progetto. 

Il pattern architetturale utilizzato è stato il Model-View-Controller. Nello specifico , la componente View si occupa non solo della visualizzazione dello stato dell’applicazione, ma anche della interazione dell’utente. Sulla base di questa interazione, la View si occuperà di propagare gli input forniti al Controller. Riceve inoltre notifiche di aggiornamento dello stato da parte del Model, che si concretizzeranno poi in un aggiornamento dei dati visualizzati. Il Controller rappresenta la componente di coordinamento del pattern. Si occupa infatti di analizzare l’input proveniente dalla View e intervenire conseguentemente sulla componente Model. Inoltre, in seguito alla modifica di stato e alle interazioni con l’utente, seleziona la View adeguata da visualizzare. La componente Model si occupa di incapsulare lo stato dell’applicazione e, come detto, di notificare alla View le modifiche di stato coordinate dal controller. Il pattern, quindi, è molto utile quando si ha necessità di separare gli strati logici di Presentazione ed Elaborazione, rendendoli così indipendenti gli uni dagli altri. Così facendo, sarà possibile mostrare i dati in modalità diverse e di rendere indipendente la modifica degli stessi dalla loro visualizzazione.

Nel nostro caso, abbiamo la ***View*** ed il ***Controller*** fuse in un unico component. Le classi che modellano gli elementi del dominio quali *Utente*, *Proprietario*, *Cliente*, *Dipendente*, *Prodotto*, *Libri*, *ArticoliC*, *Richieste*, *Pagamento*, *CartaFedeltà*, *SingIn* e *Carrello*  definiscono la componente di Model di gestione dello stato dell’applicazione. Le restanti classi, quali *Gestione utenti*, *GestoreDB*, ed tutti i controller della View, causa l'utilizzo di ***JavaFX*** che necessita di un controller per ogni View,  rappresentano i ***Controller*** ed i ***View*** della nostra applicazione. Particolarmente importante è la classe *GestoreDB*, la quale si occupa della comunicazione della base di dati e, conseguentemente, dell’inizializzazione dello stato della nostra applicazione.

Il diagramma mostra inoltre l’utilizzo di Java DataBase Connectivity, un insieme di interfacce che costituiscono una API per l’intenzione con un DB relazionale.
Il DBMS utilizzato è *MySQL*, il quale a sua volta mette a disposizione un connettore compatibile con JDBC.
Nel diagramma e mostrato anche l'utilizzo delle librerie grafiche *JavaFx* utilizzate per la parte grafica del nostro progetto.

### Boundary Use Case 
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

<br>

<br><br>

<br><br>

<br><br>

<br><br>

<br>

**Clienti**

<br>

![DAO Clienti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Clienti_vvfxci.jpg)

**Dipendenti**

<br>

![DAO Dipendenti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689583/EdOnlineShop/DAO_Dipendente_aj97kp.jpg)

**Prodotti**

<br>

![DAO Prodotti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Prodotti_c4kbcc.jpg)

<br>

<br><br>

<br><br>

<br><br>

<br>

<br>

<br><br>

<br><br>

<br><br>

<br>

**Libri**

<br>

![DAO Libri](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Libri_yr0wl6.jpg)

**Articoli**

<br>

![DAO Articoli](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Articoli_est30g.jpg)

**Accesso**

<br>

![DAO Accesso](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Accesso_syrmrq.jpg)

<br><br><br><br><br><br><br><br>

**Carrelli**

<br>

![DAO Carrelli](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Carrelli_xdpwtz.jpg)

**Carte**

<br>

![DAO Carte](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Carte_n09znu.jpg)

**Richiesta**

<br>

![DAO Richieste](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600689582/EdOnlineShop/DAO_Richiesta_awax2s.jpg)
<br>

<br><br><br><br><br><br><br>

# <a name="HCI"></a>Human Computer Interation

<br>

## Welcome

<br><br>

![Welcome](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Welcome_ztuxd2.jpg)

<br>

<br>

***Questa è l’interfaccia di avvio del sistema. Da qui, l'utente potrà effettuare l'accesso, premendo il pulsante <u>accedi</u> ed in base alla propria registrazione mostrera la homepage di riferimento.***

<br><br><br><br><br><br><br><br><br><br><br>

<br><br>

## <a name="homePageP"></a>HomePage Proprietario

<br>

![HomePage Proprietario](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/HomePage_Proprietario_nfwerp.jpg)

<br>

***Questa è l'interfaccia che si ottine dopo l'accesso del proprietario e si possono vedere le liste di <u>clientit</u>, <u>dipendenti</u> e dei <u>prodotti</u> premendo i rispettivi pulsanti.
Premendo sui pulsanti <u>Gestione Clienti</u>, <u>Gestione Dipendenti</u> e <u>Gestione Prodotti</u> si andra nelle rispettive schermate di gestione.***

<br><br><br><br><br><br><br><br><br><br><br>

<br><br>

<br><br>

<br><br>

## <a name="GestioneC"></a>Gestione Cliente

<br>

![Gestione Clienti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Gestione_Clienti_zv3nx9.jpg)

<br>

***Questa interfaccia rappresenta la gestione, da parte del proprietario, del cliente. In questa schemata e possibile aggiungere un nuovo utente tramite L'apposito pulsante, si possono visualizzare la lista di utenti già registrati, la lista di carte fedeltà già usate e la lista dei clienti registrati. Tramite il tasto <u>Aggiungi Carta Fedeltà</u> è possibile dare una carta fedelta ad un utente e tramite il tasto <u>modifica</u> si può aggiornare la data di scadenza della carta ed i propri punti. Infine con il tasto <u>Aggiungi Cliente</u> si va ad aggiungere un codice identificativo del carrello all'utente e viene classificato come cliente.***

<br><br><br><br><br><br><br><br><br><br><br>

<br><br>

<br><br>

<br><br>

## <a name="GestioneD"></a>Gestione Dipendente

<br>

![Gestione Dipendenti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Gestione_Dipendenti_cohjdo.jpg)

<br>

***L'interfaccia gestione dipendente rappresenta la possibilità di aggiungere nuovi dipendenti tramite il pulsante <u>Aggiungi Dipendente</u> e tramite il tasto <u>Elimina dipendente</u> l'eliminazione del dipendente.***

<br><br><br><br><br><br><br><br><br><br><br>

<br><br>

<br><br>

<br><br>

## <a name="GestioneProd"></a>Gestione Prodotti

<br>

![Gestione Prodotti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Gestione_Prodotti_s4jri6.jpg)

<br>

***In gestione prodotti abbiamo l'interfaccia che gestisce l'inserimento di nuovi prodotti tramite il pulsante <u>Aggiungi Prodotto</u> e tramita <u>Modifica Prodotto</u> è possibile modificare il prezzo e la quantità del prodotto e d automaticamente le aggiorna il prezzo e la quantità dei libri e degli articoli di cancelleria.
Tramite <u>Aggiungi Libro</u> possiamo aggiungere tutti i dati del libro, così come <u>Aggiungi Articolo</u> aggiungiamo i dati degli articoli.
Infine con il pulsante <u>Visualizza Lista prodotti</u> è possibile visualizzare la lista di tutti i prodotti aggiunti.***

<br><br><br><br><br><br><br><br><br><br><br>

<br><br>

<br><br>

<br><br>

## <a name="homePageD"></a>HomePage Dipendenti

<br>

![HomePage Dipendenti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/HomePage_Dipendente_lvisx0.jpg)

<br>

***Nella Homepage del dipendente abbiamo due pulsanti, il primo pulsatnte <u>Visualizza Richiesta</u> permette di visualizzare tutte le richieste di acquisto da evadere e tramite il pulsante <u>Elimina Richiesta</u> è possibile eliminare la richiesta scrivendo nell'apposita casella l'id della richiesta da eliminare***

<br><br><br><br><br><br><br><br><br><br><br>

<br><br>

<br><br>

<br><br>

## <a name="homePageC"></a>HomePage Clienti

<br>

![HomePage Clienti](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/HomePage_Cliente_nwrbym.jpg)

<br>

***L'interfaccia Homepage Clienti rappresenta la schermata iniziale che si presenta al cliente dopo il login, abbiamo due pulsanti per la visualizzazione dei prodotti, <u>Visualizza libri</u> per i libri e <u>Visualizza articoli</u> per la visualizzazione degli articoli di cancelleria. Il pulsante <u>Inserisci nel carrello</u> per l'inserimento del prodotto scelto, inserendo Id del prodotto e la quantità nelle apposite caselle, nel carrello.
L'ultimo tasto che troviamo è <u>Vai al carrello</u> che permetti di andare nell'interfaccia del carrello***

<br><br><br><br><br><br><br><br><br><br><br>

<br><br>

<br><br>

<br><br>

## Carrello

<br>

![Carrello](https://res.cloudinary.com/dqh1pnrdx/image/upload/v1600694850/EdOnlineShop/Carrello_gz254w.jpg)

<br>

***Questa intefaccia rappresenta il carrello di prodotti che ha il cliente, tramite il pulsante <u>Visualizza il tuo carrello</u> il cliente potrà vedere i proditti aggiunti in precedenza nel carrello. Tramite il tasto <u>elimina</u> possiamo rimuovere un prodotto dal carrello inserendo i dati del prodotto negli appositi campi. E' possibile scegliere il metodo di pagamento nell'apposito menu ed infine dopo aver scelto un nome per la propria richiesta è possibile inviare l'ordine al dipendente tramite il pulsante <u>Crea richiesta aqcuisto</u>***

