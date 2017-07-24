# Web programming
# lab1 

La rezolvarea acestui laborator se vor folosi următoarele tag-uri: a, table (tr, th, td), div, strong, ul, ol, li, form, input, select, option, b, textarea, h1 (… h6), img, center, fieldset, legend, p, br, span şi (absolut toate) următoarele atribute: href, name, target, border, colspan, align, valign, width, height, bgcolor, type, start, checked, disabled, maxlength, readonly, size, value, multiple, selected, cols, rows, max, alt, step, height, width, src, title. Nu se va folosi CSS.

# lab2 

```lab2/1``` Cerinte:

* Site-ul va fi stilizat folosind CSS;
* Header-ul va avea o inaltime fixa de 100 de pixeli;
* Meniul va fi redat printr-o lista, latimea meniului fiind de 150 de pixeli;
* Fiecare element din cadrului meniului va avea o inaltime fixa de 25 de pixeli;
* Footer-ul va avea o inaltime fixa de 60 de pixeli;
* Div-ul principal (de culoare verde, avand continutul Main View in exemplu) va ocupa toata latimea ferestrei (minus latimea meniului din dreapta) si toata inaltimea disponibila a acesteia (dupa ce se scade inaltimea header-ului si a footer-ului). Daca este cazul, acest div poate avea o inaltime care sa se adapteze la continut, mai mare ca inaltimea ferestrei browserului, caz in care browserul va afisa bara de scroll verticala din partea dreapta. Inaltimea div-ului principal poate fi dictata si de inaltimea meniului daca acesta contine un numar ridicat de elemente (sitatie care poate conduce si ea la afisare scroll barului vertical al browseru-lui). Daca div-ul princial are continut minim, div-ul ce reprezinta footer-ul se va regasi intotdeauna in partea de jos a ferestrei. Geometria site-ului se va pastra la redimensionarea ferestrei browser-ului.

```lab2/1``` 
Write a web page with a transparrent div in the center containing various text/information. The transparrent div should have round corners; do this with and without using an image.

# lab3 

``lab3/1``
Sa se scrie o pagina HTML ce contine un tabel cu cel putin trei coloane si cel putin trei linii. Prima coloana reprezinta antetul. Cand utilizatorul va da click pe o celula din antet, elementele din tabel se vor sorta crescator sau descrescator in functie de valorile prezente pe linia corespunzatoare antetului pe care s-a dat click. Codul JavaScript va fi reutilizabil si va permite crearea unui comportament de tabel sortabil pentru mai multe tabele existente in cadrul paginii. Nu se vor folosi biblioteci de functii, jQuery, pluginuri, etc. Exemplu de tabel ce se doreste a fi sortat (sortarea se va face alfabetic dupa numele fructului, dupa pret sau dupa cantitate):
<table class="full" style="width: 180px; margin-top: 1em;">
<tbody>
<tr>
<th>Fructe</th>
<td>Mere</td>
<td>Pere</td>
</tr>
<tr>
<th>Pret</th>
<td>3</td>
<td>4</td>
</tr>
<tr>
<th>Cantitate</th>
<td>8</td>
<td>6</td>
</tr>
</tbody>
</table>

``lab3/2`` Write an html document which contains 2 buttons and at least 10 links and a javascript so that when the user clicks the first button the page's background will change (there are 5 background images which are rotated, one at a time) and when the user clicks the second button the shape and color of all the links from the document will change.

# lab4 

``lab4/1``
Enunţurile problemelor de la laboratorul de jQuery sunt aceleaşi ca la laboratorul de JavaScript. Nu se vor folosi alte biblioteci de funcţii, plugin-uri, etc în afara de jQuery (jquery.min.js). Se cere o rezolvare a problemelor de la laboratorul de JavaScript însă cât mai „jQuery oriented”.

``lab4/2``Write a web page for a small game. The web page should display at random times images placed in random positions on the browser window. Each image lasts on the browser window for a short period of time and then it dissapears. If the user clicks an image before it dissapears, he/she gets a point. The game ends when the user wins 10 points.

# lab5

```lab5/1```
Sa se scrie o pagina HTML care contine un formular cu urmatoarele campuri: Nume, Prenume, Telefon, E-mail, precum si doua butoane Next si Previous. Server side se mentine o baza de date cu aceleasi campuri. La actionarea butoanelor Next si Previous se vor actualiza campurile din formular conform inregistrarilor din baza de date. La modificarea formularului se vor actualiza si inregistrarile din baza de date.

```lab5/2```
Write a web application for managing a multimedia file collection. The application should maintain various information about a multimedia file in the database (i.e. title, format type, genre, path in the file system etc.). The application should implement: multimedia file browsing (use AJAX for retrieving titles from a specific category/genre), adding, removing, updating a multimedia file from the collection. The files themselves does not have to be stored in the database.

# lab6

Folosind servlet şi pagini JSP, să se parcurgă înregistrările unei tabele dintr-o baza de date. Înregistrarea curenta va fi afişata într-un formular prin intermediul unui custom tag JSP. Formularul va conţine două butoane de “Next” şi “Previous” care permit trecerea la următoarea, respectiv anterioara înregistrare. Dacă se modifica datele în formular, se va actualiza înregistrarea curentă.

# lab7 (php)

O tabela dintr-o baza de date accesata server side contine echipe de fotbal din liga I impreuna cu datele necesare afisarii clasamentului ligii: nume echipa, meciuri jucate, victorii, egaluri, infrangeri, goluri marcate, goluri primite, punctaj. Un script php va afisa clasamentul actual, iar altul va permite completarea bazei de date cu meciurile din actuala etapa. Dupa completarea actualei etape se va reafisa clasamentul.

# lab8 (php + ajax)

```lab8/1```
Sa se scrie o pagina HTML care contine un formular cu urmatoarele campuri: Nume, Prenume, Telefon, E-mail, precum si doua butoane Next si Previous. Server side se mentine o baza de date cu aceleasi campuri. La actionarea butoanelor Next si Previous se vor actualiza campurile din formular conform inregistrarilor din baza de date. La modificarea formularului se vor actualiza si inregistrarile din baza de date.

```lab8/2```
Write a web application for managing a multimedia file collection. The application should maintain various information about a multimedia file in the database (i.e. title, format type, genre, path in the file system etc.). The application should implement: multimedia file browsing (use AJAX for retrieving titles from a specific category/genre), adding, removing, updating a multimedia file from the collection. The files themselves does not have to be stored in the database.
