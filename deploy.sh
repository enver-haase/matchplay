mvn -Pproduction clean package
TARGET=/etc/systemd/jar/flipperstudio.jar
sudo cp target/matchplay-1.0-SNAPSHOT.jar $TARGET
sudo chmod 755 $TARGET
sudo service flipperstudio restart
