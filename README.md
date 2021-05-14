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
Bai klase diagramarako, bai sekuentzia diagrama guztietarako esteketan utziko ditugula erabaki dugu argazkiak dokumentuan txertatzea kasu batzuetan ez dela ia ezer ikusten sekuentziak duen tamainagatik.

#### Sekuentzia diagramak

##### Erregistratu

Sekuentzia diagramaren argazkia -> https://drive.google.com/file/d/1zykMjoK2aO4rpOhTWWgLs9fJT0UZ9gzw/view?usp=sharing

Erabiltzaileak sartu behar diren datu guztiak ondo sartu ondoren sistemak ondorengoa egiten du. Lehenik, aukeratutako motaren arabera createAdmin edo createErregistratuaren bidez deitzen dio negozio logikari. Gero negozio logikak DataAccess klaseari deitzen dio eta azkenean datu basean Admin edo Erregistratua motako datua sortzen du eta datu basean sartzen du.
