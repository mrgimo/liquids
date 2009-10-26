Liquids
*******

1. Build
--------

Das Projekt verfügt über ein ant-script (build.xml), nach dessen Ausführung
ein neuer Ordner "realease" erstellt wird. Darin befinden sich alle nötigen
Dateien, um das Spiel auszuführen. (info: http://ant.apache.org/)


2. Konfiguration
----------------

Das config-file (data/liquids.config) ist in yaml und kann beliebig angepasst werden.
(info: http://www.yaml.org/)

"window" enthält die Option "fullscreen", welche den Vollbildmodus aktiviert bzw.
deaktiviert. Mögliche Werte sind "true" für die Aktivierung und "false" für die
Deaktivierung. Unter Windows ist der Vollbildmodus eventuell nicht möglich.

"map" enthält Informationen zur Karte. Die Option "name" verweist auf den Namen
eines Verzeichnisses im Verzeichnis "data/maps". Hier können auch eigene Karten
hinzugefügt werden. Um eine Karte laden zu können, muss das angegebene Verzeichnis
über zwei files verfügen (bounds.* und texture.*).
Die Option "gridSize" beeinflusst die physikalische Grösse der einzelnen Partikel
und regelt somit, wann eine Kollision entsteht.

"particles" gruppiert Optionen, welche die Partikel betreffen. So kann mit "quantity"
die Anzahl (pro Spieler), mit "healing" die Heilungskraft, mit "damage"
der Schaden und mit "size" die Darstellungsgrösse der Partikel beeinflusst werden.

"players" enthält eine Liste der Spieleroptionen. Spieler können beliebig zur Liste
hinzugefügt oder entfernt werden. Pro Spieler müssen zwei Optionen angegeben werden.
Die Option "color" setzt die Farbe des Spielers. Mögliche Werte sind "black", "white",
"red", "green", "blue", "yellow", "cyan" und "magenta".
Die Option "device" bestimmt das Steuergerät des Spielers. Die verfügbaren Steuergeräte
sind Maus, Beschleunigungsmesser und Tastatur. Die dazugehörenden Werte sind "mouse"
für Maus, "accelerometer" für Beschleunigungsmesser und vier Zeichen für die Tastatur.
Die vier Zeichen, welche für die Nutzung der Tastatur angegeben werden müssen, bestimmen
welche Tasten verwendet werden sollen. Die Zeichen werden in der Reihenfolge vor, zurück,
links, rechts angegeben.


3. Start
--------

Für die unterstützten Betriebssysteme sind start-scripts vorhanden.
Nach Doppelklick auf das entsprechende script startet das Spiel automatisch.
Um das Spiel zu starten, wird das "Java SE Runtime Environment" (min. Version 6)
benötigt. (download: http://java.sun.com/javase/downloads/index.jsp)
