JAVADIR=/usr/share/java
JARNAME=woo-app

all:
	(cd src; javac -cp /mnt/d/po/projeto/project/po-uuilib/po-uuilib.jar:/mnt/d/po/projeto/project/woo-core/woo-core.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`

run:
	java -Dimport=../../tests-final/auto-tests/A-17-01-M-ok.import -cp woo-app.jar:../woo-core/woo-core.jar:../po-uuilib/po-uuilib.jar woo.app.App