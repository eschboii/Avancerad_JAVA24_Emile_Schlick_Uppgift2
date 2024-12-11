# Emiles lilla skola, studenthanteringssystem
Detta är ett konsolbaserat system för att hantera studentuppgifter byggt i Java.
Programmet låter användaren skapa, visa och spara studentdata i fil. Programmet låter även användaren läsa studentinformation från sparad fil. Programmet använder sig av objektorienterad programmering för att organisera och hantera studentdata.

---

## Funktioner
**Skapa och hantera studenter:**
- Användaren kan skapa nya studenter genom att ange namn, betyg och ID som sparas i en LinkedHashMap.

**Sök efter sparad studenter:**
- Användaren kan söka efter student genom att mata in studentens ID.

**Visa sparade studenter:**
- Användaren kan visa alla studenter som finns sparade i systemet.

**Spara studenter till fil:**
- Användaren kan spara alla studenter till fil så att information kan bevaras även när programmet avslutas.

**Läsa studenter från fil**
- Informationen som sparats i filen kan läsas upp.

**Dynamiskt användargränssnitt:**
- Programmet presenterar tillgängliga alternativ på ett tydligt och dynamiskt sätt, vilket gör det lätt för användaren att navigera.

### Krav
- Java 8 eller senare

---

## Programflöde
Användaren interagerar med programmet genom flera menyer:

**1. Huvudmeny:**
- Välj att lägga till en student, visa alla studenter, spara till fil, ladda studenter från fil eller avsluta programmet.

**2. Lägg till student:**
- Skapa en ny student genom att ange namn, betyg och ID.

**3. Sök efter student:**
- Sök efter student via ID

**4. Visa sparade studenter:**
- Se alla studenter som är sparade i systemet.

**5. Spara till fil:**
- Spara alla studenter i en fil för framtida användning.

**6. Ladda studenter från fil:**
- Ladda studenter från en fil om de finns sparade.

**7. Avsluta:**
- Programmet avslutas och tar bort den skapta textfilen.

---

### Hur använder man programmet?
- Efter att programmet startats kommer användaren att presenteras med en meny.
- Följ instruktionerna och välj ett alternativ genom att skriva in siffran som motsvarar den åtgärd du vill utföra.
- För att lägga till en student, visa alla studenter, spara eller ladda från fil, välj rätt alternativ.
- För att avsluta, välj alternativet för att stänga programmet.

### Exempel på användning
**Starta programmet**
1. Välj "1" för att lägga till en student, skriv in namn, betyg och ID.
2. Välj "2" för att söka efter student via deras ID
3. Välj "3" för att visa alla sparade studenter.
4. Välj "4" för att spara studenter till en fil.
5. Välj "5" för att ladda och skriva ut studenter från en fil.
6. Avsluta genom att välja "6"

---

## Hur fungerar programmet?

### 1. Starta programmet
För att starta programmet kör ``Main``

### 2. Huvudmenyn
När programmet startar kommer användaren till huvudmenyn:
```
Hej, välkommen till Emiles lilla skola!
Var vänlig välj ett av alternativen
            
1. Lägg till nya studentposter via menyn
2. Sök efter en student via deras ID
3. Visa alla sparade studenter
4. Godkänn och spara studenter till fil
5. Läs sparade studenter från fil
6. Avsluta programmet
```

### 3. Lägg till en student
Välj **Alternativ 1** för att lägga till en student. Programmet ber om namn, betyg och ID för studenten och sparar sedan informationen.
```
Ange studentens namn: Emile
Ange studentens betyg: A
Ange studentens ID: 1337

Studenten John Doe med ID 1337 och betyg A har lagts till.
```

### 4. Sök student efter ID
Användaren kan välja **Alternativ 2** för att söka efter en student genom deras ID.
```
Ange studentens ID: 1337
Student hittad:

ID: 1337, Namn: Emile, Betyg: A
```

### 5. Visa sparade studenter
Genom att välja **Alternativ 3** för att visa alla sparade studenter och matar ut en lista på alla studenter som har lagts till.
```
Sparade studenter:

ID: 1337, Namn: Emile, Betyg: A
ID: 420, Namn: Oliver, Betyg: F
```

### 6. Visa sparade studenter
Vi kan spara studenter till fil med **Alternativ 4** för att spara våra inmatade studenter i en fil.
```
Studenter har sparats till filen "Student.txt".
```

### 7. Visa sparade studenter
Vi kan läsa de sparade studenterna från filen med **Alternativ 5**.
```
Studenter har lästs in från filen:

ID: 1337, Namn: Emile, Betyg: A
ID: 420, Namn: Oliver, Betyg: F
```

### 8. Avsluta programmet
Med **Alternativ 8** i huvudmenyn så avslutas programmet samt att textfilen med de sparade studenterna tas bort.

---

### Bidra
Vill du bidra till det här projektet är det bara att göra en pull requests.






