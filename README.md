# Binome : Gr�goire BEJJAJI & Nabil CHOUKRI


# Pré-requis 
* Disposer d'au moins un compte github par binome
* Apporter son portable (les postes de l'université n'ont pas accès au proxy depuis Eclipse). Pas de préférence pour l'OS (Linux, OSX ou Windows).
* Disposer d'un accès internet (université ou data)
* JDK 8 ou 9
* Eclipse recent (de préférence Oxygen) installé
* Plugins Eclipse à installer : infinitest, natural (chercher "cucumber")

Si vous passez par le proxy de l'université, configurer votre Eclipse (Window-> Preferences -> chercher 'proxy") et votre conf maven  `~/.m2/settings.xml` :
```
<settings>
  <proxies>
    <proxy>
      <id>univ-nantes</id>
      <active>true</active>
      <protocol>http</protocol>
        <host>xxx</host>
	<port>3128</port>
     </proxy>
    </proxies>
</settings>
```
* Forker sur Github le projet `bflorat/tp1-miage` puis cloner votre dépôt Github dans votre workspace
* Dans le `README.md` de votre projet, mentionner votre nom et celui de votre binome __(ne pas oublier !)__

# Exercice 1 - Refactoring
_Temps estimé : 20 mins_

__Travailler dans le projet fourni mailreader-ex1-ini__

1) Réusiner la classe `MailComparator`

Raccourcis clavier à connaître : 
* ALT-SHIFT-S : fonctions Eclipse de génération de sources (ex : constructeurs)
* ALT-SHIFT-T : fonctions de réusinage
* ALT-SHIFT-M : extraction de méthode (sur sélection)
* ALT-SHIFT-R : renommage (sur sélection)

# Exercice 2 - Découpage en couches
_Temps estimé : 20 mins_

1) Réorganiser le code dans les couches standards. 

Faire en sorte par exemple que divers frontends puisse récupérer les mails. Nous aurons dans ce TP un seul frontend : un CLI (ligne de commande) qui sera implémenté sous la forme d'une classe `ClientMail` avec `main()`. 
Cette méthode main attend deux arguments : un booleen `production` qui précise si le mail doit vraiment être envoyé (`true`) ou si nous sommes en environnement de recette (`false`). Le second argument est le sujet du mail.

Rappel : exemple de méthode main qui parse un boolean : 
```
public static void main(String[] args) {
   production = Boolean.parseBoolean(args[0]);
   ...		
```
Conception :

![diag sequence](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/bflorat/tp1-miage/master/diag1.puml&ttt=1)
![diag classe](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/bflorat/tp1-miage/master/diag2.puml&ttt=1)

Prévoir une cinquième couche `commun` pour les éléments communs à toutes les couches comme les exceptions. 

`MailSender` est une interface, le choix de l'implémentation est fait par l'application en fonction de la valeur du booléen `production`.

# Exercice 3 - Injection de dépendances avec Guice
_Temps estimé : 20 mins_

Point de synchro : repartir du projet fourni `mailreader-ex3-ini`

Guice est le framework d'injection de dépendance en Java de Google. Il est léger et la configuration se fait en java (et non par fichier XML ou par annotations). Ses concurrents en Java sont principalement Spring Core et CDI dans le monde JEE. Ce pattern est également présent dans la plupart des languages, comme dans Symphony en PHP ou AngularJS en JavaScript.

La méthode `configure()` de la classe `MailReaderModule` contient la configuration de Guice. C'est ici qu'on associe une interface à la classe contrète qui sera injectée. Exemple :
```
bind(MonInterface.class).to(MaClasseConcrete.class)
```
Il est bien sûr également possible d'injecter des classes concrètes (comme ici le `MailService`).

1) Compléter la méthode `configure()`
Observer la méthode `ClientMail.main()` : elle charge la configuration et créé l'objet de haut niveau de l'arbre d'injection : un `InterpreteurLigneCommande`.

2) L'objet `InterpreteurLigneCommande` a besoin d'un `MailService`. Lui injecter (injection par constructeur) via l’annotation (standard java) `@Inject`.

3) Faire de même pour l'injection du `MailSender` dans le `MailService`.

Noter l'arbre d'injection que forme les objets injectés depuis  `InterpreteurLigneCommande`.

# Exercice 4 - TU
_Temps estimé : 30 mins_

1) Compléter les tests unitaires ou en écrire de nouveaux dans les test cases `MailTest` et `MailComparatorTest`. Enlever les annotations `@Ignore` s'il y en a.

2) Exécuter vos tests si besoin (automatique si vous pratiquez le test continu avec infinitest).

# Exercice 5 - BDD avec Cucumber-jvm
_Temps estimé : 40 mins_

Point de synchro : repartir du projet fourni `mailreader-ex5-ini`

cucumber-jvm est l'implémentation java de cucumber, un framework de BDD (Behavioral Driven Development) très populaire. Il est existe d'autres : JBehave (l'original, très similaire), Concordion, JGiven ...

Pour les besoins du TP, nous utilisons ici les notions de Scenario Outline, de Data Table et de Transformer permettant l'utilisation de données tabulaires et de formats custom.

1) Compléter la classe `MailComparaisonStep`
2) Lancer le test `CucumberRunnerTest` en junit
3) Ouvrir dans un navigateur `target/cucumber/index.html`
4) Ajouter des cas de test dans la feature `trier_mail.feature` : faut-il ecrire de nouvelles méthodes de test comme en tests unitaires ?

5) optionnel :
Ecrire un scenario simple au format textuel et les steps correspondants.

# Cleanup
Si vous le désirez, vous pourrez supprimer votre projet github mais pas avant fin juin (noté)

