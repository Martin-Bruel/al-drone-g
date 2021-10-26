Feature: launch delivery
  Ce scénario met en oeuvre le lancement d'une livraison

 Scenario: Récupération des assignations drones-colis et lancement du drone
    Given Un conducteur, 3 drones, 3 colis et sa tablette
    When  le conducteur demande les assignations
    Then Il y a 3 assignations
    When Le conducteur lance le drone avec son colis
    Then Le drone part effectuer sa livraison
    When  le conducteur demande les assignations
    Then Il y a 2 assignations

