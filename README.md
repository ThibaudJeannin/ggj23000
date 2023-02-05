# ggj23000

Jeu pour la Global Game Jam 2023

Jeu de gestion clicker d'un foret. Le but est d'obtenir la racine magique pour sauver toutes les forets du monde, tout en protégeant sa foret. Vos actions ont des competences3.

Jouable gratuitement : https://foret3000.cleverapps.io/

- Assets from https://www.kenney.nl/

## Installation

- Installer jdk 7
- Installer gradle
- Installer docker
- Lancer la base postgres  
`docker run -it --name db-ggj23 -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres`
- Dans Server.kt, décommenté la line `setupDatabase(dbUrl, dbBase, dbUser, dbPassword)`
- Lancer l'application `./gradlew run` => la base est crée
- Recommenter la ligne

## Créateur.ices

- Dynamic Team Consultant : https://github.com/BaptisteM-lvd
- Corporate Response Architect : https://github.com/BrunoFL
- Lead Functionality Liason : https://github.com/Chrisdu25
- Chief Optimization Facilitator : https://github.com/ThibaudJeannin
- Tooltip operation facilitator : https://github.com/MarieSmo
- Global Web Engineer : Yohann
