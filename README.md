# 3.Iterazioa

## Bets21 Proiektuaren 3.Iterazioa: Apostuak egiteko sistema baten inplementazioa javan idatzita. 

- Egileak: **Xabier urretxua** (Scrum Master) eta **Mikel Álvarez**. 
- Web zerbitzuak partzialki inplemetatuta. Xml-ko inplementazio guztia egin dugu behar ziren klase guztietan, baina hala eta guztiz ere web zerbitzuko url-a ez zuen aurkitzen eta  Java Persistence errorea ematen zuen. Errore hau zuzentzeko ez gara gai izan eta gainera beste ikasgaietan azterketak izango genituenez ezin izan dugu denbora gehiegirik dedikatu.
- Internalizazioa ez dugu inplementatu, bakarrik main-eko interfazean internalizazioa daukagu.

### Sarrera

Proiektu honen helburua, hiru mailako software arkitektura batean diseinatutako apustuak kudeatzen dituen informazio sistema baten garapena izan da. Proiektu hau kontuak, gertaerak, apostuak eta pronostikoak sortzea, hainbat apostu egitea etab ahalbideratzen du. 

Proiektu hau hiru iterazioetan garatu dugu eta azkenengo iterazio honetan ondorengoa egin dugu; alde batetik, hiru erabilpen kasu berri inplementatu ditugu: Apostu anitzak egin, erabiltzailea erreplikatu eta Erabiltzaile erreplikatuenak lortu (gure EK berria) . Hiru erabilpen kasu hauek garatzeko lehenik zirriborro batekin hasi gara eta erabilpen kasu bakoitzaren sekuentzia diagrama egin ditugu. Behin erabilpen kasu bakoitzaren ideia lortuta implementazioarekin hasi gara eta kodigoan nahi genituen zuzenketa edo hobekuntzak aldatu ditugu. Behin inplementazio guztia bukatuta eta frogatuta ondo doala lan guztiaren diseinua StarUML-ra pasatu dugu eta azkenik, proiektuaren azkenengo iterazioaren dokumentazioa egin dugu.

### Eskakizunen Bilaketa

#### Domeinuaren eredua (Entitateak/Erlazioak)
|**Klasea**|**Deskripzioa**|**Erlazioa**|
|---|---|---|
|**Event**|Partidoak adierazteko data zehatz batean sortu daitekeen gertaera klasea.|Questionekin erlazioa dauka, Event klaseak gertaera horren galdera guztiak gordetzen dituelako.|
|**Question**|Gertaera batean partidari buruzko galderen klasea.|Erlazioak: Event-rekin erlazioa dauka galdera zein gertaerarena den jakiteko eta Pronostikoarekin galderaren pronostikoak gordetzeko.|
|**Pronostikoa**|Galdera bati buruzko pronostikoen klasea.|Apusturekin eta Apustuanitzekin erlazioa dauka erabiltzaileak zein pronostikoan apostu egin duen jakiteko.|
|**Apostua**|Pronostiko batean apostu bat egiteko klasea.|Pronostikoekin eta Erabiltzaileekin erlazioa du.|
|**Apostu Anitzak**|Pronostiko batean apostu anitz egiteko klasea.|Pronostikoekin eta Erabiltzaileekin erlazioa du.|
|**Erregistratua**|Erabizaile erregistratua sortzeko klasea.|Apostua, Apostu Anitzekin eta Erabiltzailearekin erlazioa dago erabiltzaileak bere apostuak gordeko dituelako.|
|**Erabiltzailea**|Erabiltzaile klase abstraktua.|Erregistratua eta Adminekin erlazioa dauka bi hauen klase gurasoa delako.|
|**Admin**|Administradore motako erabiltzailea.|Erabiltzailearekin erlazioa dago admina erabiltzailearen klase umea delako.|

#### Erabilpen kasuen eredua

##### Gertaera sortu
Erabilpen kasu honerako GertaerakSortuGUI interfaze grafikoa erabiltzen dugu. Hor data eta azalpena sartzen dugu.

###### Gertaera fluxua
1. *Admin* data bat eta gertaeraren azalpena sartzen du
2. *System* data eta azalpena hartzen du, gertaera sortzen du eta datu basean sartzen du.

###### Fluxu alternatiboa
1. Sartutako data ez da existitzen


##### Erregistratu
Erabilpen kasu honerako RegisterGUI erabiltzen dugu eta hor erabiltzaileak behar diren datu guztiak sartzen ditu.

###### Gertaera fluxua
1. *Erabiltzailea* izena, pasahitza, adina eta NAN zenbakia sartzen du eta Erregistratua edo Admin izateko aukeretatik bat aukeratzen du.
2. *System* erabiltzaileak sartutako datuekin Erregistratua edo Admin bat sortzen du eta datu basean sartzen du.
3. *System* errorerik ez badago erabiltzaileari main interfaze grafikora bueltatuko dio logeatzeko.

###### Fluxu alternatiboa
1. Sartutako izena eta pasahitza badago datu basean.
2. Sartutako adina ez da zenbaki bat.


##### Logeatu
Erabilpen kasu honerako LoginGUI interfaze grafikoa erabiltzen dugu eta interfaze honetan erabiltzaileak datuak sartzen ditu logeatzeko.
###### Gertaera fluxua
1. *Erregistratua* izena, pasahitza eta erabiltzaile mota sartzen du.
2. *System* datuak hartzen ditu eta datu basean begiratzen du ea norbaitek dagoen datu basean dagoen sartutako datuekin.
3. *System* true bada erabiltzaileak Erregistratua bada ErregistratuGUI interfaze grafikora eramaten dio eta admin bada AdminGUI-ra.

###### Fluxu Alternatiboa
1. Ez da existitzen erabiltzailerik sartutako datuekin.


##### Dirua sartu
Erabilpen kasu honerako DiruaSartuGUI interafze grafikoa daukagu eta hor gure kontuan sartu nahi dugun dirua sartzen dugu.

###### Gertaera fluxua
1. *Erregistratua* sartu nahi duen dirua ezartzen du.
2. *System* sartutako dirua hartzen du, erregistratuak kontuan duen dirua hartzen du eta bien arteko batura egiten du. Ondoren, batura datu basean eguneratzen du.

###### Fluxu alternatiboa
1. Sartutako dirua ez da zenbaki bat.


##### Apostua Egin
Honetarako ApostuakSortuGUI interfaze grafikoa daukagu. Interfaze honetan egutegi bat daukagu data aukeratzeko. Gero aukeratutako datan dauden gertaera guztiak ScrollPane batean agertuko dira eta aukeratutako gertaeran galderekin gauza bera. Behin galdera aukeratuta JComboBox baten bidez agertuko dira pronostiko guztiak kuota eta emaitza adieraziz eta horretatik bat aukeratuko duzu. Azkenik, dirua sartzen duzu eta apostua egiten duzu.

###### Gertaera fluxuak
1. *Erregistratua* Data aukeratzen du.
2. *System* Data horretarako dauden gertaerak pantailaratzen ditu.
3. *Erregistratua* Gertaera bat aukeratzen du.
4. *System* Gertaera horretarako dauden galdera guztiak pantailaratzen ditu.
5. *Erregistratua* Galdera bat aukeratzen du.
6. *System* galderarako pronostikoak pantailaratzen ditu.
7. *Erregistratua* pronostikoa aukeratu, dirua sartu eta apustua egin.
8. *System* Datu guztiekin apostua sortu eta datu basean sartu.
9. *System* Erabiltzaileari erreplikatzen ari dioten beste erabiltzaileak apostu bera egiten dute.

###### Fluxu alternatiboa
1. Ez dago gertaerarik aukeratutako datan.
2. Ez dago galderarik aukeratutako gertaeran.
3. Ez dago pronostikorik aukeratutako galderan.
4. Sartutako dirua kontuan duzuna baino handiagoa da.


##### Emaitzak ipini
Kasu honetan bi interfaze grafiko erabiltzen ditugu. Alde batetik, GertaerakAukeratu eta bestetik, EmaitzakIpiniGUI.
GertaerakAukeratu interfazean egutegiaren bidez emaitzak ipini nahi dituzun gertaerari aukertuko diozu eta behin gertaera aukeratuta EmaitzakIpniGUI interfaze grafikora joango zara. EmaitzakIpniGUI-n gertaerak dituen galderen kopuruen aldiz exekutatuko da, hau da, lehenengo aldian lehenengo galdera eta galdera horietatik dauden pronostikoetatik(emaitzekin) bat aukertu behar izango duzu. Gauza bera errepikatuko duzu galdera guztiekin.

###### Gertaera fluxua
1. *Admin* Data aukeratzen du.
2. *System* Data horretarako dauden gertaerak pantailaratzen ditu.
3. *Admin* Gertaera bat aukeratzen du.
4. *System* galdera guztiak lista batean sartzen ditu, lehenengoa hartzen du eta galderak dituen pronostiko guztiak pantailaratzen ditu.
5. *Admin* emaitza posibleetatik emaitza ona aukeratzen du eta hurrengorako botoia ematen du.
6. *System* aukeratutako pronostikorako zeuden apostu normal eta anitz guztiak dagokien kuotekin irabazi duten dirua bere kontuetan sartzen dira. 
7. Aurreko hiru pausoak errepikatzen dira galderak guztiekin azkenarekin izan ezik.
8. *Admin* Azken galderarekin emaitza aukeratu.
9. *System* aukeratutako pronostikorako zeuden apostu normal eta anitz guztiak dagokien kuotekin irabazi duten dirua bere kontuetan sartzen dira.
10. *System* aukeratutako gertaera desagertzen da eta gertaera horrekin erlazio duten beste datuak ere desagertzen dira.
11. *System* administratzailea bere hasierako interfazea irekitzen dio.

###### Fluxu alternatiboa
1. Ez dago gertaerarik aukeratutako datan.
2. Ez dago galderarik aukeratutako gertaeran.
3. Ez dago pronostikorik aukeratutako galderan.
4. Ez dago apustu normalik pronostiko honerako.
5. Ez dago apustu anitzik pronostiko honerako.



##### Pronostikoak Sortu
Kasu honetan interfaze bakar bat erabili dugu PronostikoakSortuGUI izenekoa. Interfaze honetan egutegia berriz erabiltzen dugu data sartzeko eta aukeratutako datan gertaera guztiak JScrollPane batean pantailaratzen dira. Ondoren, aukeratutako gertaeran gauza bera gertatzen da galderekin. Azkenik, aukeratutako galderan emaitza eta kuota sartzen duzu eta pronostikoa sortzen duzu.

###### Gertaera fluxua
1. *Admin* Data aukeratzen du.
2. *System* Data horretarako dauden gertaerak pantailaratzen ditu.
3. *Admin* Gertaera bat aukeratzen du.
4. *System* Gertaera horretarako dauden galderak pantailaratzen ditu.
5. *Admin* Galdera aukeratzen du.
6. *Admin* emaitza eta kuota sartzen ditu.
7. *System* sartutako datuekin pronostikoa sortzen du eta datu basean sartzen du.
8. *System* uneko interfazea ixten du eta administradorearen hasierako interfazea irekitzen du.

###### Fluxu alternatiboak
1. Ez dago gertaerarik aukeratutako datan.
2. Ez dago galderarik aukeratutako gertaeran.
3. kuota ez da zenbaki bat.


##### Apostu Anitza sortu
Kasu honetan ere interfaze bakar bat erabili dut ApostuAnitzaSortu izenekoa. Apostu normal baterako interfaze ia berdina erabili dut, hau da, datarako egutegi batekin eta bi JScrollPane gertaererako eta galdererako. Ondoren, pronostikoetarako JComboBox bat du eta azkenik bi botoi ditugu, bat apostua gehitzeko eta beste bat apostu anitza amaitzeko.

###### Gertaera fluxua
1. *Erregistratua* Data aukeratzen du.
2. *System* Data horretarako dauden gertaerak pantailaratzen ditu.
3. *Erregistratua* Gertaera bat aukeratzen du.
4. *System* Gertaera horretarako dauden galderak pantailaratzen ditu.
5. *Erregistratua* Galdera aukeratzen du.
6. *System* Pronostiko guztiak pantailaratzen ditu.
7. *Erregistratua* Pronostikoa aukeratzen du, dirua sartzen du eta apostua gehitzen du.
8. Erregistratuak beste apostu bat egin nahi badu aurreko pausoetatik batzuk errepikatu behar izango du. (guztiak beste datakoa bada, 3-tik aurrera beste gertaerakoa bada, 5-tik aurrera beste galderakoa bada eta 7-tik aurrera beste pronostikoa baldin bada)

###### Fluxu alternatiboa
1. Ez dago gertaerarik aukeratutako datan.
2. Ez dago galderarik aukeratutako gertaeran.
3. Ez dago pronostikorik aukeratutako galderan.
4. Sartutako dirua ez da zenbaki bat.
5. Sartutako dirua kontuan duzuna baino handiagoa da.


##### Erabiltzailea erreplikatu
Honetarako interfaze bat erabiltzen dugu ErabiltzaileaErreplikatuGUI izenekoa.

###### Gertaera fluxua
1.*System* Erregistratu guztiak hartzen ditu datu basetik eta pantailaratzen ditu interfazean.
2.*Erregistratua* erreplikatu nahi duen erabiltzailea aukeratzen du.
3.*System* aukeratutako erabiltzailean duen erreplikatuen listan *Erregistratua* sartzen du eta datu basea eguneratzen du.
4.*System* uneko interfazea ixten du eta erregistratuaren hasierako orria irekintzen du.

###### Fluxu alternatiboa
1. Ez dago erregistratu gehiagorik datu basean
2. Aukeratutako erregistratua erreplikatzen ari zara.


##### Erabiltzaile erreplikatuenak lortu
Kasu honetan RankingGui interfaze grafikoa erabiltzen dugu. Interfaze honetan 3-ko ranking bat sortu dugu Jlabel-en bidez eta botoi bat sartu dugu hiru hoberenei bonoak banatzeko eta hasierako orrira joateko.

###### Getaera fluxua
1. *System* datu basetik erregistratu guztiak hartzen ditu eta haietatik erreplikatu gehien dituzten hiru erregistratuak aukeratzen ditu.
2. *System* lortutako hiru hoberenak ranking-ean sartu.
3. *Admin* sariak banatzeko botoiari eman.
4. *System* irabazleen bono kopurua eguneratu datu basean.
5. *System* uneko interfazea itxi eta administratzailearen hasierako interfazea ireki.

###### Fluxu alternatiboa
1. Ez dago erregistraturik erreplikatuekin.



### Diseinua

#### Sekuentzia diagramak
Sekuentzia diagrama batzuk oso handiak dira eta ez dira ondo ikusten. Horretarako, irudian klikatu eta irudiaren repositoriora eramango dizu eta hor irudiari "zoom" egin ahal izango duzu hobeto ikusteko.

##### Erregistratu

![Erregistratu](/Diagrams/Erregistratu.jpg)

Erabiltzaileak sartu behar diren datu guztiak ondo sartu ondoren sistemak ondorengoa egiten du. Lehenik, aukeratutako motaren arabera createAdmin edo createErregistratuaren bidez deitzen dio negozio logikari. Gero negozio logikak DataAccess klaseari deitzen dio eta azkenean datu basean Admin edo Erregistratua motako datua sortzen du eta datu basean sartzen du.


##### Logeatu

![Logeatu](/Diagrams/logeatu.jpg) 

Behin erabiltzaileak bere izena, pasahitza eta erabiltzaile mota ezarri ondoren, sistemak begiratuko du ea administratzailea edo erregistratua existitzen den datu basean. Horretarako, erabiltzaileZuzena funtzioaren bidez negozio logikari deituko dio eta ondoren DataAccess klaseari deituko dio. Datu basean badago erabiltzailea funtzioak true itzuliko du eta sistemak true jasotzen badu uneko interfazea itxiko du eta administrado bat bada AdminGUI irekiko du , bestela ErregistratuGUI irekiko du.


##### Gertaera sortu

![Gertaera Sortu](/Diagrams/GertaeraSortu.jpg)

Lehenik administratzaileak gertaeraren azalpena eta data sartu behar izango du. Ondoren, sistemak negozio logikari deituko dio gertaeraSortu funtzioaren bitartez eta negozio logikak DataAccess klaseari deituko dio. Azkenik, DataAccess-eko funtzioak gertaera motako datua sortuko du administratzaileak sartutako datuekin eta datu basean sartuko du.


##### Dirua Sartu

![Dirua Sartu](/Diagrams/diruaSartu.jpg)

Lehenik, erregistratuak bere kontuan sartu nahi duen diru kopurua ipiniko du eta dirua sartzeko botoiari emango dio. Ondoren, sistemak diru hori eta erregistratuak kontuan lehen zuen diru kopuruarekin gehitzen du. Behin aurreko guztia eginda, sistemak negozio logikari deituko dio diruaSartu metodoaren bitartez eta negozio logikak DataAccess-eri deituko dio. Azkenik, DataAccess-eko funtzioak diru hori erabiltzailean eguneratuko du.


##### Apostua normala eta Apostu Anitza egin

![Apostu normala edo Anitza egin](/Diagrams/ApustuaEginEtaApostuAnitzaEgin.jpg)

Lehenik erabiltzaileak apostu mota aukeratuko du. Lehenik apostu normalaren sekuentzia diagrameren atala azalduko dut.

Apostu hau egiteko lehenik erabiltzaileak data bat aukeratuko du. Orduan, hartutako datarekin sistemak getEvets funtzioaren bitartez datu basean sartutako datarako dauden gertaera guztiak hartu eta interfazean pantailaratzen ditu. Gertaera horietatik erregistratuak bat aukeratuko du eta Event klasea duen getQuestion funtzioaren bitartez gertaera horretarako dauden galdera guztiak hartu eta inetrfazean pantailaratuko ditu. Erregistratuak galdera bat aukeratu eta gero galdera horretarako dauden pronostikoak aterako dira pronostikoaren emaitza eta kuota adieraziz. Azkenik, erregistratuak bera nahi duen emaitza aukeratuko du eta apostu horretarako sartu nahi duen dirua ipiniko du eta apostua egiteko botoiari emango dio. Botoi horri ematean sistemak begiratuko du ea erabiltzaileak bonori dituen ala ez. Bonoak baditu kontuan diruaren erdia kenduko dio, gero erabiltzailearen bono kopurua datu basean eguneratuko du eta azkenik apustua sortu eta datu basean sartuko du.

Erregistratuak bere apustuak erreplikatzen ari den erabiltzailerik badago sistemak apustu bera sortu eta erabiltzaile horren izenean apustua sortu eta datu basean sartuko du bonoak dituen ala ez kontuan izan barik.

Beste aldetik, apostu anitza aukeratzen badu apostu normal batean egindako pauso guztiak berdinak izango dira baina bi botoi egongo dira bat egon beharrean. Botoi bat apostua gehitzeko balio du, hau da, egindako apostua pilaratzen du(bonoena kontuan hartuta) eta hurrengo apostua egiteko prest gaude. Beste botoia sakatzen badugu apustu anitza bukatzen eta momentura arte egindako apostu anitza datu basera igotzen da apostuAnitzaEgin funtzioaren bitartez. Azkenik, erabiltzaileak bere apostuak beste batek erreplikatzen ari bada eta erabiltzaile hori behar duen diru nahikoa kontuan badu apostu anitza bera egingo du ere bai(bonoak kontuan hartu izan gabe).



##### Emaitzak ipini

![Emaitzak ipini](/Diagrams/EmaitzakIpini.jpg)

Honetarako lehenik egutegiaren bidez data aukeratzen dugu eta sistema getEvents-en bitartez data horretarako dauden gertaera guztiak pantailaratuko ditu. Orduan, sistemak EmaitzakIpiniGUI interfazera irekiko du eta galderak baditu lehena hartu eta galdera horretarako dauden emaitzak(pronostikoak) hartuko ditu getP-ren bitartez eta JComboBox batean sartuko ditu. Ondoren, administratzaileak emaitza guztietatik bat aukeratuko du eta hurrengo galdera otoiari emango dio. Botoiari ematean, sistemak pronostiko horrerako dauden apostu normal eta anitz guztiak hartuko ditu. Apostu normala bada kuota sartutako diruarekin biderkatu eta sartuDirua funtzioarekin erabiltzailearen dirua eguneratuko da.
Apostu anitza bada emaitza egokia bada pronostikoarekin zeukan erlazioa ezabatuko da eta emaitza ez bada egokia datu basean apostu anitzaren egoera falsera aldatuko du erabilgarritasunaKendu funtzioaren bitartez. Azkenik, behin apostu anitzetan aldaketa hauek eginda galderarako apostu anitz guztiak begiratuko ditu berriz. 
Apostu anitzak ez baditu eralzio gehiagorik pronostikoekin eta egoera true bada orduan erregistratuak apostu anitza irabazi duela suposatzen dugu eta erregistratuari apostu anitzan sartutako dirua bider kuota metatua satzen dugu bere kontuan. Apostu anitzaren egoera false bada, orduan erabiltzaileak apostu anitza galdu duela suposatzen dugu eta apostu anitza datu basetik kentzen dugu apostuAnitzaKendu metodoaren bitartez. Azkenik, sistemak EmaitzakIpiniGUI ixten eta irekitzen du aukeratutako gertaeraren hurrengo galderari emaitza ipintzeko. Azkenengo galderan bagaude, hurrengo galdera botoiaren gaitasuna kentzen da eta Sartu emaitza botoia aktibatzen da. Botoi hau sakatzean beste botoiaren berdina egiten du azkenengo galderarekin. Behin galderaren emaitza ipinita gertaera datu basetik kentzen du gertaeraKendu metodoaren bitartez eta uneko interfazea ixten du eta administratzailearen hasierako interfazea irekitzen du.



##### Pronostikoa sortu

![Pronostikoa sortu](/Diagrams/pronostikoaIpini.jpg)

Kasu honetan, berriz administratzailea data bat aukeratzen du eta sistema data horretan dauden gertaera guztiak erakutziko ditu. Orduan, administratzaileak gertaera bat aukeratuko du eta sistemak gertaera horretan dauden galdera guztiak erakutziko ditu. Azkenik, administratzailea pronostikoa sortu nahi duen galderan aukeratuko du, pronostikoaren emaitza eta kuota jarriko du eta botoiari emango dio. Orduan, sistemak pronostikoaSortu metodoaren bitartez Pronostikoa datu mota sortu eta datu basean sartuko du.


##### Erabiltzailea erreplikatu

![Erabiltzailea erreplikatu](/Diagrams/ErabiltzaileaErreplikatu.jpg)

Erregistratuak erabiltzailea erreplikatzeko botoiari eman ondoren sistemak erabiltzaileGuztiakLortu funtzioaren bitartez datu basean dauden erregistratu guztiak hartu eta JComboBox batean ipiniko ditu. Ondoren, erregistratuak haietatik bat aukeratuko du eta orduan erreplikatu metodoaren bitartez datu basean erreplikatu nahi duen erregistratuaren erreplikatu listan berak sartuko da eta egindako aldaketa datu basean eguneratuko da.


##### Erabiltzaile erreplikatuenak lortu

![Erabiltzaile erreplikatuenak lortu](/Diagrams/ErabiltzaileErreplikatuenakLortu.jpg)

Administratzailea erabiltzaile erreplikatuneak lortzeko botoiari ematean erabiltzaileGuztiakLortu funtzioaren bitartez erregistratu guztiak lortuko ditu eta haietatik erregistratu gehien dituzten hiru erregistratuak lortuko ditu. Ondoren, RankingGUI interfaze grafikoa irekitzen da eta hiru erregistratuak 3-ko raking batean agertuko dira eta administratzaileak sariak banatu botoiari ematean bonoakEguneratu metodoaren bitartez lehengoari 10, bigarrenari 6 eta hirugarrenari 3 bono gehituko diogu bere kontuan. Bono hauek egindako apostuan %50-eko deskontua ematen du eta bakarrik aktibatuko dira erregistratuak erreplikatu gabeko apostu bat egitean.



#### Klase Diagrama

![klase diagrama](/Diagrams/Design!ClassDiagram_0.jpg)



## Inplementazioa

Negozio logikako metodoen azalpena.

### **createQuestion**
Metodo honek gertaera baterako galdera sortzen du. Galderaren textua, gertaera eta apostu minimoa sartu behar dira. Galdera objektua bueltatzen du edo null gertaera jada pasa bada edo galdera existitzen bada.

### **createAdmin**
Metodo honek admin motako erabiltzailea sortzen du. Izena, NA, adina, pasahitza eta admin zenbakia sartu behar dira. Admin motako erabiltzaile objektua bueltatzen du edo null admina existitzen bada.

### **createErregistratua**
Metodo honek erregistratuta motako erabiltzailea sortzen du. Izena, NA, adina eta pasahitza sartu behar dira. Erregistratua motako erabiltzailea bueltatzen du edo null erabiltzailea existitzen bada.

### **erabiltzaileaBadago**
Metodo honek erabiltzailea bilatzen du izena eta pasahitza sartuz. Erabiltzailea bueltatzen du aukitu bada edo null ez badago.

### **erabiltzaileZuzena**
Metodo honek login egiteko behar den konprobaketa egiten du. Izena eta pasahitza sartuz, true edo false itzuliko du erabiltzailea existitzen bada eta pasahitza zuzena bada bakarrik.

### **getEvents**
Metodo hau gertaerak datu basetik lortzeko balio du. Gertaeraren data pasa behar da. Egun horren gertaeren bektorea itzultzen du.

### **getEventsMonth**
Metodo hau gertaerak datu basetik lortzeko balio du. Gertaeraren dataren hilabetea pasa behar da. Hilabete horren gertaeren bektorea itzultzen du.

### **initializeDB**
Metodo honek DataAccesseko datu basea abiaratzen du.

### **getGertaeraHandienaLortu**
Metodo honek izenak dioen bezala gertaera handiena lortzen du. Int motako zenbakia bueltatuko du, gertaeraren identifikadorea.

### **diruaSartu**
Metodo hau dirua sartzeko balio du. Diru kopurua, erabiltzaile izena eta honen pasahitza sartu behar dira.

### **gertaeraSortu**
Metodo hau gertaerak sortzeko balio du. Gertaeraren azalpena, data eta zenbakia (identifikadorea) sartu behar dira.

### **pronostikoaSortu**
Metodo hau pronostikoak sortzeko balio du. Pronostikoaren kuota, zenbakia (identifikadorea) eta emaitza sartu behar dira.

### **apustuaEgin**
Metodo honekin apustuak egin daitezke. Pronostikoa, emaitza, dirua, erabiltzailearen NA eta apustuaren zenbakia sartu behar dira.

### **zenbakiHandienaLortu**
Metodo honek apustu zenbaki handiena lortzen du. Int motako zenbakia itzultzen du.

### **emaitzaIpini**
Metodo honekin gertaera bukatzerakoan emaitza jartzeko balio du. Galdera eta emaitza sartu behar dira.

### **ErregistratuaBilatu**
Metodo honek erregistratu motako erabiltzailea itzultzen du. Erabiltzailearen NA sartu behar da.

### **gertaeraKendu**
Metodo honek gertaera ezabatuko du behin bukatuta. Gertaera sartu behar da.

### **galderaLortu**
Metodo honek galdera bueltatzen du. Galderaren identifikadorea sartu behar da.

### **pronostikoHandienaLortu**
Metodo honek pronostiko handienaren identifikazioa bueltatzen du.

### **zenbakiAnitzHandienaLortu**
Metodo honek apustu anitzetako identifikadore handiena lortzen du.

### **apustuAnitzaEgin**
Metodo hau apustu anitzak egiteko erabiltzen da. Apostu zenbakia, dirua, NA, kuota eta apostuen arraylist bat pasa behar zaio. 

### **listatikKendu**
Metodo honekin apustu anitz batetik apustu bat kendu daiteke. Apustuaren zenbakia (identifikadorea) eta pronostikoa sartu behar dira.

### **erabilgarritasunaKendu**
Metodo apustuaren egoera falsera aldatzen du. Apostuaren zenbakia sartu behar da.

### **apostuAnitzaLortu**
Metodo hau apustu anitza objektua bueltatzen du. Apustuaren identifikadorea sartu behar da.

### **apostuAnitzaLortu**
Metodo hau apustu anitza ezabatzeko balio du. Apustuen identifikadoreak sartu behar dira.

### **erreplikatu**
Metodo hau apustu erreplikatuak sortzeko balio du. Erreplikatuko den erabiltzailearen NA eta objektua bera sartu behar dira.

### **erabiltzaileGuztiakLortu**
Metodo honek erabiltzaile guztien lista bueltatzen du.

### **kenduBonoBat**
Metodo hau sartutako erabiltzaileari bono bat kenduko dio (0 ez den bitartean noski). Erabiltzailearen NA sartu behar da.

### **bonoakEguneratu**
Metodo honek bono kopurua eguneratzen du. Sartutako zenbakia bono kopuru berria izango da. Erabiltzailearen NA sartu behar da ere.

## Ondorioak
Proiektu honetan milaka zailtasun izan ditugu eta hala ere azkeneko iterazio honetan gure %100a eman ondoren, proiektuari bukaera eman diogu. Bidetik taldeko herena galdu dugu eta honen ondorioz kodearen berrikuspenak luzeagoak izan dira eta kodearen funtzionalitatea murriztu egin da. Taldearen dinamika ona izan da, nire (Mikel) ustez eta komunikazioa oso erraza izan. Proiektuaren konplexutasuna ez da oso handia izan baina ordu gehiegi jan ditu beste ikasgaietatik. Lortutako emaitzak onak izan dira: Eskatu zaizkigun eskakizunak egitea lortu dugu (Web zerbitzuak izan ezik).

## Bideoaren URL-a

## Kodearen URL-a
https://github.com/xabiu/3.Iterazioa
