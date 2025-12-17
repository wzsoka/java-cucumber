# language: hu

Jellemző: Autentikáció

  Háttér:
    Adott A webalkalmazás kezdő oldalán állok

  Szabály: Minden kötelező mező kitöltésével lehet regisztrálni

      @poz @smoke
      Példa: Sikeres regisztráció
        Amennyiben A Regisztráció oldalra navigálok
        Amikor Teljesen kitöltöm és beküldöm a regisztrációt
        Akkor A fiókom létrejön

      @neg @smoke
      Példa: Sikertelen regisztráció hiányos adatok miatt
        Amennyiben A Regisztráció oldalra navigálok
        Amikor Hiányosan kitöltöm és beküldöm a regisztrációt
        Akkor Hibaüzenet jelzi a kitöltendő mezőt
        És A Regisztráció oldalon maradok

      @neg
      Példa: Sikertelen regisztráció már regisztrált email címmel
        Amennyiben A Regisztráció oldalra navigálok
        Amikor Korábban regisztrált email címmel próbálok regisztrálni
        Akkor Hibaüzenet jelzi hogy már létezik fiók az email címmel
        És A Regisztráció oldalon maradok

  Szabály: Regisztrált email címmel és megfelelő jelszóval lehet belépni

      @poz @smoke
      Példa: Sikeres belépés
        Amennyiben A Bejelentkezés oldalra navigálok
        Amikor Bejelentkezek korábban létrehozott felhasználóval
        Akkor A fiókomba jutok

      @neg
      Forgatókönyv vázlat: Sikertelen belépés
        Amennyiben A Bejelentkezés oldalra navigálok
        Amikor Bejelentkezek "<felhasználó>" és "<jelszó>" adatokkal
        Akkor "<hibaüzenet>" szöveg jelenik meg
        És A bejelentkezés oldalon maradok, amit bezárok
        Példák:
        | felhasználó     | jelszó    | hibaüzenet                                                                                      |
        | apa             | XY1234567 |  A bejelentkezés sikertelen. Kérjük, ellenőrizd bejelentkezési adataidat, és próbáld meg újra.  |
        | apa@gmail.com   | xy        |  A jelszónak legalább 6 karakterből kell állnia, abból legalább 1 betű kell, hogy legyen.       |
        | apa@gmail.com   | XY1234567 |  A bejelentkezés sikertelen. Kérjük, ellenőrizd bejelentkezési adataidat, és próbáld meg újra.  |
        | teszt@gmail.com | abc1234567|  A bejelentkezés sikertelen. Kérjük, ellenőrizd bejelentkezési adataidat, és próbáld meg újra.  |
        

