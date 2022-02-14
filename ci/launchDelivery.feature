Feature: launch delivery
  Ce scénario met en oeuvre le lancement d'une livraison

 Scenario: Assignations drones-colis et livraison
    Given un conducteur, 3 drones, 1 colis et sa tablette
    When  le conducteur demande les assignations
    Then il y a 1 assignations
    When le conducteur lance le drone avec son colis
    Then le drone part effectuer sa livraison
    Then  le colis est livré
    When  le conducteur demande les assignations
    Then il y a 0 assignations


  Scenario: Livraison du colis malgré déconnexion
    Given un conducteur, 3 drones, 1 colis et sa tablette
    When  le conducteur demande les assignations
    And le conducteur lance le drone avec son colis
    Then le camion perd la connexion avec le drone
    And le drone n'est pas localisable
    When le camion retrouve la connexion avec le drone
    Then  le colis est livré

   Scenario: Assignations flotte-colis et livraison
    Given un conducteur, 1 flotte de 3 drones, 3 colis avec la même adresse et la tablette
    When  le conducteur demande les assignations pour une flotte
    Then il y a 3 assignations pour la flotte
    When le conducteur lance la flotte avec les colis
    Then la flotte part effectuer sa livraison
    Then  les colis sont livrés
    When  le conducteur demande les assignations pour une flotte
    Then il y a 0 assignations pour la flotte



