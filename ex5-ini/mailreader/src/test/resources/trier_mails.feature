Feature: Trier les mails

L'utilisateur de mailreader doit pouvoir trier ses mails pour les retrouver plus facilement

# Régles de Gestion (RG) :
# - On tri les mails par importance puis statut puis sujet puis date (plus recente en premier)
# Les statuts sont par ordre d'importance croissants : PAS_ENVOYE, ENVOYE, LU
# Les dates sont données au format ISO8601 à la seconde et en UTC.


Scenario Outline: Tri de deux mails
Given un premier mail avec l'importance "<important1>", le statut "<statut1>", le sujet "<sujet1>" et la date "<date1>"
Given un second mail avec l'importance "<important2>", le statut "<statut2>", le sujet "<sujet2>" et la date "<date2>"
When je trie
Then le tri doit retourner "<resu>"

# Les resulats peuvent être : MAIL1_AVANT, MAIL1_APRES, EGAUX
	
Examples:
| important1 | important2 | statut1    | statut2 | sujet1  		    | sujet2        | date1                | date2                | resu        |
| true       | false	  | PAS_ENVOYE | LU      | aaaaa			| bbbbbb 		| 2017-01-01T14:03:00Z | 2017-01-01T14:03:34Z | MAIL1_AVANT |  
| true       | true	      | PAS_ENVOYE | LU      | aaaaa			| bbbbbb 		| 2017-01-01T14:03:00Z | 2017-01-01T14:03:34Z | MAIL1_APRES |
| true       | true	      | LU		   | LU      | aaaaa			| aaaaa 		| 2017-01-01T14:03:00Z | 2017-01-01T14:03:34Z | MAIL1_APRES | 
    
    
Scenario: ordre d'une liste de mails
Given les mails :
| important  | statut   		 | sujet			  		| date                 | 
| true       | PAS_ENVOYE 		 | aaaaa					| 2017-01-01T14:03:00Z |
| false      | PAS_ENVOYE 		 | aaaaa					| 2017-01-01T14:03:00Z |
| false      | LU		 		 | bbbbb					| 2016-12-01T14:03:00Z |

When je trie
Then la liste ordonnée doit être :
| important  | statut   		 | sujet			  		| date                 |
| true       | PAS_ENVOYE 		 | aaaaa					| 2017-01-01T14:03:00Z |
| false      | LU		 		 | bbbbb					| 2016-12-01T14:03:00Z | 
| false      | PAS_ENVOYE 		 | aaaaa					| 2017-01-01T14:03:00Z |


