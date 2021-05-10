# 3.Iterazioa

## Bets21 Proiektuaren 3.Iterazioa: Apostuak egiteko sistema baten inplementazioa javan idatzita. 

- Egileak: **Xabier urretxua** (Scrum Master) eta **Mikel Álvarez**. 
- Web zerbitzuak eta internalizazioa inplemetatuta.

### Sarrera

Proiektu honen helburua, hiru mailako software arkitektura batean diseinatutako apustuak kudeatzen dituen informazio sistema baten garapena izan da. Proiektu hau kontuak, gertaerak, apostuak eta pronostikoak sortzea, hainbat apostu egitea etab. ahalbideratzen du.

### Eskakizunen Bilaketa

#### Domeinuaren eredua (Entitateak/Erlazioak)
|**Klasea**|**Deskripzioa**|**Erlazioa**|
|---|---|---|
|**Event**|Partidoak adierazteko data zehatz batean sortu daitekeen gertaera klasea.|Questionekin erlazioa dauka, Event klaseak gertaera horren galdera guztiak gordetzen dituelako.|
|**Question**|Gertaera batean partidari buruzko galderen klasea.|Erlazioak: Event-rekin erlazioa dauka galdera zein gertaerarena den jakiteko eta Pronostikoarekin galderaren pronostikoak gordetzeko.|
|**Pronostikoa**|Galdera bati buruzko pronostikoen klasea.|Apusturekin eta Apustuanitzekin erlazioa dauka erabiltzaileak zein pronostikoan apostu egin duen jakiteko.|
|**Apostua**|Pronostiko batean apostu bat egiteko klasea.|Ez.|
|**Apostu Anitzak**|Pronostiko batean apostu anitz egiteko klasea.|Ez.|
|**Erregistratua**|Erabizaile erregistratua sortzeko klasea.|Apostua, Apostu Anitzekin eta Erabiltzailearekin erlazioa dago erabiltzaileak bere apostuak gordeko dituelako.|
|**Erabiltzailea**|Erabiltzaile klase orokorra.|Erregistratua eta Adminekin erlazioa dauka bi hauen klase gurasoa delako.|
|**Admin**|Administradore motako erabiltzailea.|Erabiltzailearekin erlazioa dago admina erabiltzailearen klase umea delako.|

#### Erabilpen kasuen eredua
