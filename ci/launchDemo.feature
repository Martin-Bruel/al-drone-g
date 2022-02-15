Feature: launch delivery
  Ce scénario illustre la livraison par une flotte de drones avec des déconnexions

   Scenario: Assignations flotte-colis et livraison
    Given un conducteur, 1 flotte de 5 drones, 5 colis
    When  le conducteur demande les assignations pour une flotte
    Then il y a 5 assignations pour la flotte
    When le conducteur lance la flotte avec les colis
    Then la flotte part effectuer sa livraison
    Then  les colis sont livrés
    When  le conducteur demande les assignations pour une flotte
    Then il y a 0 assignations pour la flotte



