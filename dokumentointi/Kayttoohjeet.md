# Hiukkaskasvusimulaattorin käyttöohje

Hiukkaskasvusimulaattori voidaan käynnistää joko ajettavasta jar-tiedostosta "Hiukkaskasvusimulaattori.jar" komennolla java -jar Hiukkaskasvusimulaattori.jar. Toinen mahdollisuus ohjelman ajamiseen on ajaa projekti NetBeans-ohjelmassa. Kayttoliittyma-paketissa oleva luokka Main toteuttaa main-metodin, joka ajamalla koko ohjelma käynnistyy.

Hiukkaskasvusimulaattorin varsinainen käyttö on helppoa, mutta aerosolihiukkasen, tiivistyvän kaasun ja ilmakehän ominaisuuksien asettaminen voi tuottaa aiheeseen pidemmälle perehtymättömälle ongelmia. Alempana tarjotaankin esimerkkiarvot, jotka vastaavat todellista ilmakehässä vallitsevaa tilannetta. Ennen sitä käydään kuitenkin läpi jokaisen nappulan toiminta. 

Aloittaen vasemmalta ylhäältä käyttöliittymässä näkyy ensin neljän nappulan rivi. Nämä ovat hiukkaskasvusimulaattorin käyttöä ohjaavat nappulat. "Luo hiukkanen"- ja "Luo kaasu" -nappuloista voidaan vaihtaa ilmakehässä olevien aerosolihiukkasen ja tiivistyvän kaasun ominaisuuksia. "Aja simulaatio" -nappulaa painamalla voi ajaa yhden simulaation. Ennen simulaation käynnistymistä käyttäjän on annettava ilmakehän lämpötila ja paine sekä lopetusehdot simulaatiolla (suurin sallittu aerosolihiukkasen säde tai simulaation aika), joiden toisen täyttyessä simulaatio pysäytetään. Simulaation ajamisen jälkeen oikealle alas piirtyy kuvaaja, joka kuvaa hiukkasen säteen kehitystä ajan funktiona. Vastaavasti koko simulaation ajon aikana hiukkaskasvusimulaattori päivittää oikealla ylhäällä valkoiselle pohjalle piirtyvää oranssia ympyrää, joka kuvaa aerosolihiukkasen kokoa. Kannattaa muistaa, että yhden ajon jälkeen ensinnäkin seuraava ajoa aloitettaessa aikasempi data tuhoutuu (simulaattori varmistaa tämän) ja lisäksi viimeisen ajon tiedot ovat vielä simulaatiossa. Esimerkiksi jos hiukkanen on kasvatettu yli 500 nm:n ei simulaatiota saa enää käyntiin kuin luomalla uuden hiukkasen, jonka säde on pienempi.

"Tallenna data" -nappulasta käyttäjä pystyy tallentamaan datan, mikäli dataa on jo syntynyt kirjoittamalla haluamansa tiedostonnimen simulaattorin kysyessä sitä. Simulaattori tallentaa tiedon "kayttajanAntamaNimi.dat" muotoon, eli tiedoston pääte on aina ".dat".

"Päivitä"-nappulasta voidaan päivittää hiukkasen, kaasun ja ilmakehän ominaisuuksista kertovat tekstilaatikot.

Simulaattori ei tällä hetkellä piirrä enempää kuvaajia kuin hiukkasen säteen ajan funktiona. Tällaisena se soveltuu parhaiten ilmakehässä olevien kaasujen tiivistymisen ja siihen vaikuttavien ominaisuuksien kvalitatiiviseen tutkimiseen. 

### Simulaattorin minimi- ja maksimiarvot

#### Aerosolihiukkanen
Säde: 0.1-500 nm
Tiheys: 0.1-10000 kg/m^3

#### Kaasu
Moolimassa: 0.00001-100 kg/mol
Tiheys: 0.1-10000 kg/m^3
Diffuusiotilavuus: 1-1000
Pitoisuus: 0 - 10^12 molekyyliä / cm^3

#### Ilmakehä
Lämpötila: 0.1 - 350 K
Paine: 0.1-10 atm
Aika: 0.0-24 h


### Esimerkkiarvot
Esimerkkiarvot vastaavat tilannetta, jossa tiivistyvä kaasu on rikkihappo, jonka tiheys vaihtelee veden ja rikkihapon tiheyden välillä. Aerosolihiukkasen tiheys vastaa myös tätä tiheyttä, mutta sitä ei missään tapauksessa tarvitse asettaa samaan arvoon kuin kaasun tiheyttä. Rikkihappo on yksi merkittävimmistä kaasuista, jotka voivat tiivistyä jo muutaman nanometrin kokoisiin hiukkasiin kohtalaisen pienillä pitoisuuksilla (luokkaa 10^6 cm^-3)


#### Aerosolihiukkanen
Säde: kokeile erilaisia sallittuja arvoja
Tiheys: 1000-1800 kg/m^3

#### Kaasu
Moolimassa: 0.09807 kg/mol
Tiheys: 1000-1800 kg/mol
Diffuusiotilavuus: 50.17
Pitoisuus: kokeile arvoja väliltä 10^6-10^8 molekyyliä / cm^3 (nämä vastaavat ilmakehässä tehtyjä mittauksia, kokeile myös maksimiarvoa)

#### Ilmakehä
Lämpötila: kokeile mikä ero säteen kehittymiseen on a) talvella (esim. 263.15 K) b) keväällä (esim. 283.15 K) c) Kesällä (esim 293.15K)
Paine: 1.0 atm

Näiden lisäksi hiukkasen, kaasun ja ilmakehän ominaisuuksilla voi leikkiä vapaasti ja katsoa minkälaisella kombinaatiolla hiukkasen saa kasvamaan parhaiten.

Lopuksi vielä sananen siitä, miksi tämä on mielenkiintoista. Aerosolihiukkaset vaikuttavat ihmisen terveyteen ja ilmastoon. Tällä simulaattorilla voidaan hyvin kvalitatiivisilla tasolla tutkia aerosolihiukkasten tiivistymisestä johtuvaan kasvuun vaikuttavia tekijöitä. Kasvaessaan suuremmiksi aerosolihiukkasten vaikutus maapallon globaaliin säteilypakotteeseen kasvaa, sillä mitä suuremmaksi ne kasvavat sitä enemmän ne sirottavat valoa. Toisaalta suuret (kokoluokkaa 100 nm) olevat aerosolihiukkaset voivat toimia pilvien tiivistymisytiminä, jotka vaikuttavat vielä voimakkaammin em. säteilypakotteeseen ja sitä kautta maapallon lämpötasapainoon.

