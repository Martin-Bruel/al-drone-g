Feature: Follow delivery
  Ce scénario met en oeuvre le suivi d'une livraison

   Scenario: Notificiation livraison
    Given un conducteur, 1 flotte de 3 drones, 3 colis avec la même adresse et la tablette
    When  le conducteur demande les assignations pour une flotte
    Then il y a 3 assignations pour la flotte
    When le conducteur lance la flotte avec les colis
    Then les colis sont en livrasion
    When les drones arrivent à leur addresse de livraison
    Then les colis sont livrés

   Scenario: Notificiation livraison avec le drone leader intermédiaire
    Given un conducteur, 1 flotte de 5 drones, 5 colis avec la même adresse et la tablette
    When  le conducteur demande les assignations pour une flotte
    Then il y a 5 assignations pour la flotte
    When le conducteur lance la flotte avec les colis
    Then les colis sont en livrasion
    When le drone follower perd la connexion avec le camion
    Then le drone leader envoi au camion le status de l'autre drone



