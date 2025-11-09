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
        És A bejelentkezés oldalon maradok
        Példák:
        | felhasználó     | jelszó    | hibaüzenet                                                |
        | apa             | XY1234567 |  Kérjük, adj meg egy érvényes e-mail címet.               |
        | apa@gmail.com   | xy        |  A szövegnek legalább 8 karakter hosszúnak kell lennie.   |
        | apa@gmail.com   | XY1234567 |  Kérjük, ellenőrizd a megadott adatokat.                  |
        | teszt@gmail.com | abc1234567|  Kérjük, ellenőrizd a megadott adatokat.                  |

