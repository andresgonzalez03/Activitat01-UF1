# Encarrecs d'una botiga

Aquesta petita aplicació serveix com a gestor d'encàrrecs d'una botiga, on cada client pot fer els encàrrecs que vulgui. Els encàrrecs es gestionen mitjançant diferents classes i es guarden en diferents formats: un fitxer de text amb format de factura, un fitxer CSV i un fitxer en format binari.

## Contingut
- Descripció del programa
- Classes del programa
- Com funciona?
- Funcionament intern del programa
- Crèdits

## Descripció del programa
Aquest programa permet als usuaris gestionar encàrrecs, incloent la creació d'articles amb diverses unitats, la validació de dades d'entrada i l'emmagatzematge de les dades en formats de fitxer diferenciats. Inclou la funcionalitat per generar un albarà en text, un fitxer CSV per al tractament de dades i un fitxer binari per a un emmagatzematge eficient.

## Classes del programa

### 1. Article
La classe Article conté les propietats d'un article dins d'una comanda d'un client. Les seves propietats inclouen:
- **nom**: El nom de l'article.
- **unitat**: La unitat de mesura de l'article, representada per l'enumeració Unitat.
- **quantitat**: La quantitat de l'article.

**Mètodes destacats**:
- `toString()`: Retorna una representació textual de l'article.
- `toCSV()`: Retorna una representació en format CSV de l'article.

### 2. Unitat
L'enumeració Unitat defineix les diferents unitats que es poden utilitzar per mesurar articles, com ara KILO, LITRE, UNITAT, AMPOLLES i GRAM.

**Mètodes destacats**:
- `fromString(String nom)`: Converteix una cadena a una unitat, llençant una excepció si la cadena no és vàlida.

### 3. UtilString
La classe UtilString inclou mètodes utilitaris per manipular cadenes de text, incloent la normalització de noms.

**Mètodes destacats**:
- `normalitzaString(String cadena)`: Normalitza un nom eliminant espais en blanc addicionals i llençant excepcions si la cadena no és vàlida.

### 4. Encarrec
La classe Encarrec representa un encàrrec d'un client, incloent el nom del client, telèfon, data, i una llista d'articles.

**Mètodes destacats**:
- `generarAlbara()`: Genera un albarà amb les dades de l'encàrrec.
- `generarCSV()`: Genera una cadena en format CSV amb les dades de l'encàrrec.
- `afegirArticle(Article article)`: Afegeix un article a l'encàrrec.

### 5. Gestor
La classe Gestor s'encarrega de la creació de directoris, i l'escriptura i lectura de fitxers en els diferents formats.

**Mètodes destacats**:
- `crearDirectoris()`: Crea directoris per emmagatzemar fitxers.
- `EscriureAlbara(Encarrec encarrec)`: Escriu un albarà a un fitxer de text.
- `EscriureCSV(Encarrec encarrec)`: Escriu un fitxer CSV amb les dades de l'encàrrec.
- `llegirCSV(String ruta)`: Llegeix les dades d'un fitxer CSV i les converteix en un objecte Encarrec.

### 6. Main
La classe Main conté el punt d'entrada de l'aplicació, que mostra un menú interactiu per gestionar encàrrecs.

## Com funciona?
- **Generació d'encàrrecs**: L'usuari pot crear un nou encàrrec introduint el nom del client, telèfon, data i articles.
- **Generació de fitxers**: Un cop creat, l'encàrrec es pot desar com a albarà, fitxer CSV o fitxer binari.
- **Lectura de fitxers**: L'usuari pot llegir encàrrecs existents en qualsevol dels formats suportats.

## Funcionament Intern del Programa

### 1. Inicialització del Programa
En iniciar el programa, s'executa la classe `Main`, que presenta un menú a l'usuari amb diverses opcions per gestionar els encàrrecs.

### 2. Entrada de Dades
L'usuari pot introduir les dades de l'encàrrec:
- **Nom del client**
- **Telèfon**
- **Data**
- **Articles**: Cada article es crea a partir de la classe `Article`, que requereix especificar el nom, la unitat i la quantitat.

### 3. Validació de Dades
Es fan servir mètodes de la classe `UtilString` per validar i normalitzar les entrades, assegurant que les dades siguin correctes. Per exemple, es comprova que els noms no estiguin buits i s'eliminen els espais extres.

### 4. Creació de l'Encàrrec
Un cop recollides les dades, es crea un objecte de la classe `Encarrec`, on s'emmagatzema la informació del client i els articles afegits. Es poden realitzar operacions com afegir més articles a la llista.

### 5. Generació de Documents
Al finalitzar l'entrada de dades, el programa permet a l'usuari generar documents en diferents formats:
- **Albarà**: Es genera un albarà en format de text pla mitjançant el mètode `generarAlbara()` de la classe `Encarrec`.
- **CSV**: Es crea un fitxer CSV que conté totes les dades de l'encàrrec a través de `generarCSV()`.
- **Binari**: Tot i que no s'especifica al codi anterior, es pot implementar un mètode per escriure l'encàrrec en un fitxer binari.

### 6. Emmagatzematge de Dades
La classe `Gestor` gestiona la creació de directoris i l'escriptura dels fitxers generats a les ubicacions designades. Inclou mètodes per escriure tant l'albarà com el CSV.

### 7. Lectura de Dades Existents
El programa també permet llegir encàrrecs ja existents. Utilitza el mètode `llegirCSV()` per llegir les dades des d'un fitxer CSV i convertir-les de nou en objectes de la classe `Encarrec`, permetent que l'usuari revisi o editi encàrrecs previs.

### 8. Interacció Contínua
El menú es repeteix, permetent a l'usuari realitzar múltiples operacions de gestió d'encàrrecs sense necessitat de reiniciar el programa.

## Crèdits
Desenvolupat per Andrés González.
