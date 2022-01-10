# AlbumApplication
Application Android-Kotlin qui affiche la liste de titres d'albums dans l'url suivant : https://static.leboncoin.fr/img/shared/technical-test.json

L'architecture utilisée est la clean architecture. Le projet est divisé en 3 modules:
- APP : une couche qui interagit avec l'interface utilisateur, principalement des éléments Android tels que des activités, des fragments, un modèle de vue, etc. Elle comprendrait à la fois des couches de domaine et de données.
- Domaine : contient la logique métier de l'application.
- Data : Il implémente l'interface exposée par la couche de domaine et distribuerait les données à la couche App.

Les librairies utilisées : 
- Dagger 2 : injection de dépendances
- Retrofit 2 : Un client HTTP pour les appels de l'API
- ViewModel : classe responsable de la préparation et de la gestion des données d'une activité ou d'un fragment.
- Picasso : affichage des images
- Lottie animation : animation du splash screen 
